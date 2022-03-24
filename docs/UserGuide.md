---
layout: page
title: User Guide
---

AddressBook Level 3.14 (Abπ) is a **desktop app for managing contacts, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Abπ can get your contact management tasks done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `abpie.jar` from [here](https://github.com/AY2122S2-CS2103T-T17-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* If a parameter is expected only once in the command but you specified it multiple times, only the last occurrence of the parameter will be taken.<br>
  e.g. if you specify `p/12341234 p/56785678`, only `p/56785678` will be taken.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

</div>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [m/MEMO] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Memo is optional
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 m/Avid free climber`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 m/Partner in crime t/criminal`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [m/MEMO] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* You can remove a person’s memo by typing `m/` without specifying any memo after it.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without specifying any tags after it.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
* `edit 2 m/Avid free climber` edits the memo of the 2nd person to be “Avid free climber”.
* `edit 2 m/` edits the memo of the 2nd person to be empty.

### Locate persons by name, phone number, tags and email :`find`

Finds persons whose names contain any of the given keywords.

Format: `find [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [m/MEMO] [t/TAG]…​`
* The search is case-insensitive. e.g hans will match Hans
* At least 1 parameter must be present.
* Name, phone number, email, address, memo and tags are eligible parameters.
* Specifying the parameter followed by the word to search for helps to scope the search to that specific attribute.
* Name, phone number and email follows a partial word match criteria where "Han" will match with "Hans" and "904" would match with "90400203".
* Tag, address and email follows an exact word match criteria where "Hans" will match with "Hans" or "hans".
* For both search criteria, order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* For both search criteria, as long as there is a word match (partial/exact), the contact would be in the filtered list. 

**Note: A word is defined as consecutive characters that is bounded by whitespaces.**
e.g. "This is a sentence!" contains the word "This", "is", "a" and "sentence!".

Examples:
* `find n/ John` returns john and John Doe
* `find n/alex david` returns Alex Yeoh and David Li and Alexa.
* If David Li has an email davidLi98@gmail.com, then `find e/gmail` would return David Li. 
* If John has a phone number 90400202, then `find p/9040` would return John.
* If John has a phone number 90400202, then `find p/202` would return John.
* If John has a tag family, then `find t/family` would return John.
* If John has a tag family, then `find t/fam` would return no result.

### Invoking recent command : `history`

Invokes the most recent command being used.

Format: `history`
* Invokes the most recent command and autofill it in the textbox.

Examples:
* If the last command is `delete 1`, `invoke` autofills the textbox with `delete 1`.

### Copying Emails : `copyemails`

Copies a comma-separated list of all displayed emails to clipboard.

Format: `copyemails`

Examples:
* `list` followed by `copyemails` will copy all emails currently displayed in the list to your clipboard.
* When you paste from clipboard, a comma-separated list will appear. (E.g. "johndoe@example.com, betsycrowe@example.com")

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find n/Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

### No duplicate entries (phone and email) :

Prevents duplicate entries of phone number and email in Abπ when using the `add` and `edit` commands. All phone numbers and emails in Abπ will be unique.

## Features coming in v1.3

### Undoing commands :

Undo previous commands that modified data, which includes: `add`, `edit`, `delete`, `clear`.

Format: `undo`

Examples:
* `undo` after calling `delete 1` restores the address book to its previous state prior to the deletion.
* `undo` after calling `edit 1 n/Bob` restores the address book to its previous state prior to the edit.

### Redoing commands :

Redo previous undid states.

Format: `redo`

Examples:
* `redo` after calling `undo` restores the address book to its previous undid state prior to undo.

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [m/MEMO] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 m/Peter's friend t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [m/MEMO] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [m/MEMO] [t/TAG]…​` <br> e.g., `find n/James Jake`
**Undo** | `undo`
**Redo** | `redo`
**List** | `list`
**Help** | `help`
**History**| `history`
