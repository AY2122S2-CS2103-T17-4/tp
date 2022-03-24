package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.CommandList;
import seedu.address.model.person.Person;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final StateAddressBook stateAddressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.stateAddressBook = new StateAddressBook(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPersons = new FilteredList<>(this.stateAddressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== AddressBook ================================================================================

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.stateAddressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return stateAddressBook;
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return stateAddressBook.hasPerson(person);
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book,
     * excluding {@code except}.
     */
    @Override
    public boolean hasPersonExcept(Person person, Person except) {
        requireNonNull(person);
        requireNonNull(except);
        return stateAddressBook.hasPersonExcept(person, except);
    }

    @Override
    public void deletePerson(Person target) {
        stateAddressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        stateAddressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        stateAddressBook.setPerson(target, editedPerson);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code stateAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    /** Records the command being executed. */
    @Override
    public void recordCommand(String userInput) {
        CommandList.record(userInput);
    }

    //=========== Undo and redo ==============================================================================

    /**
     * Restores address book to its previous state.
     */
    @Override
    public void undoAddressBook() {
        stateAddressBook.undo();
    }

    /**
     * Restores address book to its previous undid state.
     */
    @Override
    public void redoAddressBook() {
        stateAddressBook.redo();
    };

    /**
     * Returns true if address book is undoable; otherwise returns false.
     *
     * @return true if undoable; false otherwise.
     */
    @Override
    public boolean canUndoAddressBook() {
        return stateAddressBook.isUndoable();
    };

    /**
     * Returns true if address book is redoable; otherwise returns false.
     *
     * @return true if redoable; false otherwise.
     */
    @Override
    public boolean canRedoAddressBook() {
        return stateAddressBook.isRedoable();
    };

    /**
     * Saves the current address book state.
     */
    @Override
    public void saveAddressBookState() {
        stateAddressBook.saveState();
    };

    /**
     * Checks if two {@code ModelManager} is equal.
     *
     * @param obj the other {@code ModelManager} object.
     * @return true if equal; otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return stateAddressBook.equals(other.stateAddressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

}
