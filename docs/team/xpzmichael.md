---
layout: page
title: Xu Peizhe's Project Portfolio Page
---

### Project: AddressBook Level π (Abπ)

AddressBook pi (Abπ) is a 360° all-rounded desktop app for managing contacts, optimized for use via a Command Line Interface (CLI). Abπ is catered towards fast-typers and individuals who want an organized address book with key features including efficient filtering of contacts based on various attributes (name, email, tags, etc.), copying of all emails in the address book and a memo section that allows you to note down notable details about a person.

The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 9k LoC.

#### My contributions to the project:

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=xpzmichael&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByNone&checkedFileTypes=docs~functional-code~test-code~other)

* **Features and Enhancements implemented:**: <br>
  * Modified constraints for Name, Phone, Address, email, and Tag. 
    * Pull request [\#166](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/166), 
    [\#174](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/174), 
    [\#233](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/233),
    adds upper and lower character limit to Name, Phone, Address, email, and Tag, and allows Name and Tag to have non-alphabetic characters.
    * [\#247](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/235), 
    limits email to have at least one period for domain.
    * [\#248](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/248), 
    [\#258](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/258),
    updates phone to allow `+` and spaces to allow country code.
  * Wrote additional tests to ensure the implementation of constrains for Name, Phone, Address, email, and Tag.
  * Implement new feature: Easy Navigation among input history using up/down errow key.
    * [\#61](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/61), 
    allows user to invoke the most recent command by typing `command keyword`. 
    Modified `CommandResult` using enum to indicate the type of command being executed.
    * [\#132](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/132), [\#146](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/146)
    allows user to go through input history by pressing `up/down arrow key`<br>
    Pressing `up-arrow Key` will auto fill-in the command box with the previous command in the history (if there exist a previous input).<br>
    Pressing `down-arrow Key` will auto fill-in the command box with the next command in the history (if there exist a next input).
    

* **Documentation**:
  * User Guide:
    * Added documentation for `Previous/Next Command` feature.
  * Developer Guide:
    * [\#17](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/17), [\#27](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/27)
    adds use cases, glossary, and NFRs.
    * [\#281](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/281)
    Added implementation details of the `CommandList`, `Previous Command` and `Next Command`.

* **Community**:
  * PRs reviewed: [\#22](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/22),
    [\#37](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/37),
    [\#40](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/40),
    [\#61](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/61),
    [\#66](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/66),
    [\#132](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/132),
    [\#150](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/150),
    [\#165](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/165),
    [\#174](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/174),
    [\#234](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/234),
    [\#237](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/237),
    [\#268](https://github.com/AY2122S2-CS2103T-T17-4/tp/pull/268)
  * Reported bugs and suggestions for other teams in the class (examples: [1](https://github.com/xpzmichael/ped/issues/1), [2](https://github.com/xpzmichael/ped/issues/2), [3](https://github.com/xpzmichael/ped/issues/3), [4](https://github.com/xpzmichael/ped/issues/4), [5](https://github.com/xpzmichael/ped/issues/5), [6](https://github.com/xpzmichael/ped/issues/6), [7](https://github.com/xpzmichael/ped/issues/7), [8](https://github.com/xpzmichael/ped/issues/8), [9](https://github.com/xpzmichael/ped/issues/9), [10](https://github.com/xpzmichael/ped/issues/10), [11](https://github.com/xpzmichael/ped/issues/11),[12](https://github.com/AY2122S2-CS2103T-T17-1/tp/issues/273))
