---
layout: page
title: User Guide
---

AddressBook Level 3.14 (Abπ) is a **360° all-rounded desktop app for managing contacts,
optimized for use via a Command Line Interface** (CLI). Abπ is catered towards fast-typers
and individuals who want an organized address book with modern features. Its key features
include efficient filtering of contacts based on various attributes (name, email, tags, etc),
copying of all emails in the address book and a memo section that allows you to note down 
notable details about a person. Whether you are an insurance agent who needs to manage numerous
client contacts, or a student leader who needs to contact a large number of students, 
Abπ got you covered. So what are you waiting for? Download and try out Abπ today!

## Table of Contents
1. [Quick start](#Quick start)
2. Features<br/>
   2.1 Notes about the command format:<br/>
   2.2 Viewing help<br/>
   2.3 Listing all persons<br/>
   2.4 Adding a person<br/>
   2.5 Editing a person<br/>
   2.6 Locate persons by name, phone number, tags, email, address, memo and last contacted date<br/>
   2.7 Undoing commands <br/>
   2.8 Redoing commands<br/>
   2.9 Easy navigation among recent commands:<br/>
   2.10 Copying emails<br/>
   2.11 Deleting a person<br/>
   2.12 Clearing all entries<br/>
   2.13 Exiting the program<br/>
   2.14 Preventing duplicate entries (phone and email)<br/>
   2.15 Saving the data<br/>
   2.16 Editing the data file<br/>
3. FAQ
4. Command Summary


--------------------------------------------------------------------------------------------------------------------

## 1. Quick start

1.1 Ensure you have Java `11` or above installed in your Computer.

1.2 Download the latest `abpie.jar` from [here](https://github.com/AY2122S2-CS2103T-T17-4/tp/releases).

1.3 Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

1.4 Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1.5 Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`delete`**`3` : Deletes the 3rd contact shown in the current list.

   * **`clear`** : Deletes all contacts.

   * **`exit`** : Exits the app.

1.6 Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## 2. Features

<div markdown="block" class="alert alert-info">

**2.1 :information_source: Notes about the command format:**<br>

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

* Commands that do not take in parameters (`help`, `list`, `copyemails`, `undo`, `redo`, `previous`, `next`, `clear`, `exit`) will not be recognized if there are extraneous parameters.
  This is to protect from accidental invocations of the wrong command. <br>
  e.g. if the command specifies `clear 1`, it will be interpreted as an invalid command. The proper format is `clear`.

</div>

### 2.2 Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### 2.3 Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### 2.4 Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [c/CONTACTED DATE] [m/MEMO] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
Contacted Date and Memo is optional
</div>

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A person can have any number of tags (including 0)
</div>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 c/01-01-2020 m/Avid free climber`
* `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Prison p/1234567 m/Partner in crime t/criminal`

### 2.5 Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [c/CONTACTED DATE] [m/MEMO] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. 
The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags or memo by typing `t/` or `m/` respectively without specifying text after it.
* You can edit a peron's contacted date to "Not contacted" by typing `c/` without specifying a date after it.

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` edits the phone number and email address of the 1st person 
to be `91234567` and `johndoe@example.com` respectively.
* `edit 2 n/Betsy Crower t/` edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.
* `edit 2 m/Avid free climber` edits the memo of the 2nd person to be `Avid free climber`.
* `edit 2 c/01-01-2020` edits the contacted date of the 2nd person to be `Last contacted on 01-01-2020`.
* `edit 2 m/ c/` edits the memo of the 2nd person to be empty and the contacted date to be `Not contacted`.

### 2.6 Locate persons by name, phone number, tags, email, address, memo and last contacted date:`find`

Finds persons whose names contain any of the given keywords.

Format: `find [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [m/MEMO] [t/TAG] [c/Days]…​`
* The search is case-insensitive. E.g. hans will match Hans
* At least 1 parameter must be present.
* More than 2 whitespace between words are treated as 1 whitespace. So "Alex_ _ _Yeoh" would be treated as "Alex_Yeoh" where "_" represents a single whitespace.
* Name, phone number, email, address, memo, tags and contacted status are eligible parameters.
* Specifying the parameter followed by the word to search for helps to scope the search to that specific attribute.
* Name, phone number and email follows a partial word match criteria where "Han" will match with "Hans" and "904" would match with "90400203".
* For contacted status, the matching depends on the "days" argument given by the user and suppose the number of days specified is n, the find method searches for contacts that were contacted at least n day days ago. 
* For contacted status, if no integer value is given as an argument, the find command would just return contacts that had not been contacted.
* Tag, address and memo follows an exact word match criteria where "Hans" will match with "Hans" or "hans".
* For both search criteria, order of the keywords does not matter. e.g. Hans Bo will match Bo Hans
* For both search criteria, as long as there is a word match (partial/exact), the contact would be in the filtered list. 

<div markdown="block" class="alert alert-info">

**:information_source: A word is defined as consecutive characters that is bounded by whitespaces.
e.g. "This is a sentence!" contains the word "This", "is", "a" and "sentence!".** <br>

Examples:
* `find n/ John` returns john and John Doe
* `find n/alex n/david` returns Alex Yeoh and David Li and Alexa.
* If David Li has an email davidLi98@gmail.com, then `find e/gmail` would return David Li. 
* If John has a phone number 90400202, then `find p/9040` would return John.
* If John has a phone number 90400202, then `find p/202` would return John.
* `find n/alex yeo` would only match with contacts that have "alex yeo" contained within their name. For example, "Alex Yeoh" would be a successful match.
* If John has a tag family, then `find t/family` would return John.
* If John has a tag family, then `find t/fam` would return no result.
* `find c/1` would find contacts that were contacted 1 or more days ago.
* `find c/` would find contacts that had not been contacted.

### 2.7 Undoing commands :

Undo previous commands that modified data, which includes: `add`, `edit`, `delete`, `clear` and `scrub`.

Format: `undo`

Examples:
* `undo` after calling `delete 1` restores the address book to its previous state prior to the deletion.
* `undo` after calling `edit 1 n/Bob` restores the address book to its previous state prior to the edit.

### 2.8 Redoing commands :

Redo previous undid states.

Format: `redo`

Examples:
* `redo` after calling `undo` restores the address book to its previous undid state prior to undo.

### 2.9 Easy navigation among recent commands: 
#### Pressing `UP_ARROW_KEY` or `DOWN_ARROW_KEY`

* navigate among the recent inputs and autofill it in the textbox.

Examples:
* If the recent commands are `find n/Anny` `find n/Bob` `find n/Cathy`
* Pressing `UP_ARROW_KEY` once will fill-in the textbox with "find n/Cathy"
* Then pressing `UP_ARROW_KEY` again will fill-in the textbox with "find n/Bob"
* Then pressing `DOWN_ARROW_KEY` once will fill-in the textbox with "find n/Cathy"

### 2.10 Copying emails : `copyemails`

Copies a comma-separated list of all displayed emails to clipboard.

Format: `copyemails`

Examples:
* `list` followed by `copyemails` will copy all emails currently displayed in the list to your clipboard.
* When you paste from clipboard, a comma-separated list will appear. (E.g. "johndoe@example.com; betsycrowe@example.com")

### 2.11 Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find n/Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### 2.12 Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### 2.13 Exiting the program : `exit`

Exits the program.

Format: `exit`

### 2.14 Preventing duplicate entries (phone and email) :

Abπ helps to manage duplicates by preventing duplicate entries of phone number and email when using the `add` and `edit` commands. All phone numbers and emails in Abπ will be unique.

### 2.15 Saving the data

Abπ data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### 2.16 Editing the data file

Abπ data are saved as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.
</div>

--------------------------------------------------------------------------------------------------------------------

## 3. FAQ

**How do I save in Abπ?**<br>
There is no need to save manually. Abπ automatically saves the data after any command that changes the data.

**Where does Abπ store its data?**<br>
Abπ data is stored in data folder located at Abπ's home directory, the data file name is `addressbook.json`. Specifically `[Abπ location]/data/addressbook.json`.

**How do I transfer my data to another computer?**<br>
Simply overwrite the `addressbook.json` data file with your previous `addressbook.json` data file.

**What happens if I executed a command unintentionally?**<br>
Fret not, Abπ supports the `undo` and `redo` commands, which follows modern application undo and redo functionality.

**Why is my data gone?**<br>
When the data file is corrupted, an empty address book will be shown. Do not worry, Abπ has made a backup of your previous data file, named as `backup.json`, located at `[Abπ location]/data/backup.json`. Fix the `backup.json` into the proper json format, rename it as `addressbook.json` and overwrite the existing one.

--------------------------------------------------------------------------------------------------------------------

## 4. Command summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [c/CONTACTED DATE] [m/MEMO] [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 c/01-01-2020 m/Avid hiker t/friend t/colleague`
**Clear** | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit** | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [c/CONTACTED DATE] [m/MEMO] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find** | `find [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [m/MEMO] [t/TAG] [c/Days]…​` <br> e.g., `find n/James Jake`
**Undo** | `undo`
**Redo** | `redo`
**List** | `list`
**Help** | `help`
**Previous**| Pressing `UP_ARROW_KEY`
**Next**| Pressing `down_ARROW_KEY`
