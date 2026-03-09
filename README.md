Name: Micah Phillip

Course: Advanced Java Programming

CRN: COP-2805C

Program Name: Arcade-Token-Management-System

Description

This Java program simulates an Arcade Token Management System that tracks customer arcade accounts. It demonstrates the use of object-oriented programming, data validation, and file handling. The system allows users to manage accounts by adding, removing, updating, and viewing them directly through a console-based menu. It can also import account data from a text file formatted with " - " separators.

Each account stores information such as a Card ID, customer name, account creation date, total games played, and tokens earned. The program also determines reward eligibility (e.g., Pizza, Soda, or None) based on the number of tokens collected.

Key Concepts and Tools Used

Object-Oriented Programming (ArcadeAccount and ArcadeDMS classes)

File Input and Output using java.nio.file

Input Validation for numeric, date, and ID fields

Java Collections Framework (List, ArrayList, Scanner)

Menu-Driven Console Interface

Defensive Programming and Error Handling

How It Works

When launched, the user is presented with a console-based menu with eight options:

Add New Account – Prompts the user to create a new account with validated input.

Remove Account – Deletes an account by Card ID after confirmation.

Update Existing Account – Allows editing of name, date, games, or tokens.

Display All Accounts – Lists all stored accounts in a formatted view.

Display Single Account – Shows information for one account by Card ID.

Check Reward Eligibility – Displays which reward (if any) a customer qualifies for.

Load and Display Data File – Reads and imports account records from a text file.

Exit Program – Closes the system after user confirmation.

The system validates:

Card IDs must be 5 digits

Dates must follow the yyyy-MM-dd format

Games and tokens must be non-negative integers

Duplicate Card IDs are automatically rejected

Inputs and Outputs

Inputs:

Console input from the user (menu selections, account details)

Text file input for batch account imports

Outputs:

Console messages confirming actions or errors

Neatly formatted account listings

Import summary showing number of successful and failed records

# Arcade-Database-Managment-System
