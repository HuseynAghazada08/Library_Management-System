# Library Management System

A Java console simulation of a university library, developed as an object-oriented programming coursework project.

The system models books, theses, online research papers, library members, borrowing and returning, digital copies, and repairs.

## Features

- Physical book and thesis loans with copy availability tracking.
- Member borrowing limits that increase after every three preserved returns.
- Research paper views and downloads.
- Digital book copies with view and download counters.
- Searches by title and author.
- Queries for popular items and active members.
- Repair and archive handling for damaged items.
- Sample input files and JUnit 4 tests.

## Requirements

- JDK 17 or later.
- JUnit 4 to run the test suite.

## Run the demo

From the folder containing this README, enter the Java project directory:

```sh
cd LargeAssigment
```

Compile and run from that directory so the application can find its input files:

```sh
javac -d out src/largeassigment/*.java
java -cp out largeassigment.LargeAssigment
```

Alternatively, open the `LargeAssigment` project in Apache NetBeans and run its main class, `largeassigment.LargeAssigment`.

The application runs a scripted demonstration: it loads the sample files, borrows and returns items, accesses digital materials, searches the collection, and prints usage information. It does not provide an interactive menu.

## Input files

The project directory contains two sample files. Fields are separated by whitespace; underscores represent spaces within names and titles. The files contain data only, without header rows.

`items.txt` uses this format:

```text
ID Title Author Page_Count Topic Type Copies
```

Example:

```text
1 Clean_Code Robert_C_Martin 464 Software_Engineering Book 3
```

Item types are `Book`, `Thesis`, and `ResearchPaper`.

`members.txt` uses this format:

```text
ID Name Email Category
```

Member categories are `Student`, `Faculty`, and `Alumni`.

## Tests

JUnit 4 tests are in `LargeAssigment/test/largeassigment/`. They cover books, theses, members, research papers, repair operations, and library operations.

Configure JUnit 4 in your IDE's test dependencies, then run the test classes. The original suite contains 37 tests.

## Project structure

| Path | Contents |
| --- | --- |
| `LargeAssigment/src/largeassigment/` | Java source code |
| `LargeAssigment/test/largeassigment/` | JUnit 4 tests |
| `LargeAssigment/items.txt` | Sample library items |
| `LargeAssigment/members.txt` | Sample members |
| `LargeAssigment/build.xml` and `LargeAssigment/nbproject/` | Original NetBeans/Ant configuration |
| `FinalClassDiagram.png` | Original class diagram |
| `LargeAssigmentDesignOOP.pdf` | Original coursework documentation |

## Scope and limitations

This is an educational, in-memory simulation. Transactions are not saved back to the input files, and input parsing assumes the supplied record format. Repair outcomes are randomized.

The original implementation is preserved. In this version, damaged returns submitted through `Library.returnItem` enter `BEING_REPAIRED`, but `performRepairCheck` only processes `NEEDS_REPAIR`; those returned items can therefore remain stuck in repair.
