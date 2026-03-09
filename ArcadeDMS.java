import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
/**
 * Micah Phillip
 * COP 2800 – Java Programming
 * February 14, 2026,
 * ArcadeDMS.java
 * <p>
 * This application functions as an Arcade Token Management System.
 * It allows users to create, update, remove, and view arcade player accounts.
 * Each account tracks a customer's Card ID, name, creation date, total games played,
 * and tokens earned. The program also determines reward eligibility based on
 * tokens collected and can import account data from text files formatted with
 * " - " separators.
 * <p>
 * Features include:
 *  - Adding, updating, and deleting arcade accounts
 *  - Displaying all or single account records
 *  - Checking token-based reward eligibility
 *  - Loading account data from an external file
 *  - Input validation and user-friendly menu navigation
 */

public class ArcadeDMS {
    private static final int CARD_ID_LENGTH = 5; // required number of digits for a valid Card ID
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final List<ArcadeAccount> accounts = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * method: main
     * purpose: Launches the Arcade Token Management System
     * parameters: String[] args – standard main method argument
     * return: void
     */
    public static void main(String[] args) {
        ArcadeDMS app = new ArcadeDMS();
        app.run();
    }

    /**
     * method: run
     * purpose: Displays the main menu and handles user choices until the program exits.
     * parameters: none
     * return: void
     */
    private void run() {
        System.out.println("Welcome to the Arcade Token Management System");
        while (true) {
            printMenu();
            int choice = readMenuChoice();
            switch (choice) {
                case 1 -> addNewAccount();
                case 2 -> removeAccount();
                case 3 -> updateAccount();
                case 4 -> displayAllAccounts();
                case 5 -> displaySingleAccount();
                case 6 -> checkRewardEligibility();
                case 7 -> loadFromFilePrompt();
                case 8 -> {
                    if (confirmExit()) {
                        System.out.println("Exiting. Goodbye!");
                        return;
                    }
                }
            }
        }
    }

    /**
     * method: printMenu
     * purpose: Displays the main menu options for the user.
     * parameters: none
     * return: void
     */
    private void printMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. Add New Account");
        System.out.println("2. Remove Account");
        System.out.println("3. Update Existing Account");
        System.out.println("4. Display All Accounts");
        System.out.println("5. Display Single Account");
        System.out.println("6. Check Reward Eligibility");
        System.out.println("7. Load and Display Data File");
        System.out.println("8. Exit Program");
    }

    /**
     * method: readMenuChoice
     * purpose: Reads and validates the user's menu selection.
     * parameters: none
     * return: int – valid menu choice (1–8)
     */
    private int readMenuChoice() {
        while (true) {
            System.out.print("Choice (1–8): ");
            String input = scanner.nextLine().trim();
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= 8) return choice;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid entry. Please enter a number between 1–8.");
        }
    }

    /**
     * method: addNewAccount
     * purpose: Allows the user to create a new ArcadeAccount with validated input.
     * parameters: none
     * return: void
     */
    private void addNewAccount() {
        System.out.println("\n--- Add New Account ---");

        String cardId;
        while (true) {
            System.out.print("Enter 5-digit Card ID: ");
            cardId = scanner.nextLine().trim();
            if (!cardId.matches("\\d{" + CARD_ID_LENGTH + "}")) {
                System.out.println("Invalid ID. Must be 5 digits.");
                continue;
            }
            if (findByCardId(cardId) != null) {
                System.out.println("Card ID already exists.");
                continue;
            }
            break;
        }

        String name = readNonEmptyString("Enter customer name: ");
        LocalDate date = readDate("Enter creation date (yyyy-mm-dd): ");
        int games = readNonNegativeInt("Enter total games played: ");
        int tokens = readNonNegativeInt("Enter total tokens: ");

        ArcadeAccount acc = new ArcadeAccount(cardId, name, date, games, tokens);
        accounts.add(acc);
        System.out.println("Account added successfully:\n" + acc);
    }

    /**
     * method: removeAccount
     * purpose: Removes an existing ArcadeAccount from the system.
     * parameters: none
     * return: void
     */
    private void removeAccount() {
        System.out.println("\n--- Remove Account ---");
        String id = readCardIdPrompt();
        if (id == null) return;
        ArcadeAccount acc = findByCardId(id);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        if (readYesNo("Confirm delete (y/n): ")) {
            accounts.remove(acc);
            System.out.println("Account removed successfully.");
        }
    }

    /**
     * method: updateAccount
     * purpose: Allows the user to update any field of an existing account.
     * parameters: none
     * return: void
     */
    private void updateAccount() {
        System.out.println("\n--- Update Account ---");
        String id = readCardIdPrompt();
        if (id == null) return;
        ArcadeAccount acc = findByCardId(id);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }

        while (true) {
            System.out.println("\nChoose a field to update:");
            System.out.println("1. Name\n2. Date\n3. Games\n4. Tokens\n5. Done");
            int c = readNonNegativeInt("Choice: ");
            switch (c) {
                case 1 -> acc.setCustomerName(readNonEmptyString("New name: "));
                case 2 -> acc.setAccountCreationDate(readDate("New date (yyyy-mm-dd): "));
                case 3 -> acc.setGamesPlayed(readNonNegativeInt("New games played: "));
                case 4 -> acc.setTokensEarned(readNonNegativeInt("New tokens: "));
                case 5 -> { return; }
                default -> System.out.println("Invalid option.");
            }
            System.out.println("Updated: " + acc);
        }
    }

    /**
     * method: displayAllAccounts
     * purpose: Prints all stored ArcadeAccount records in table format.
     * parameters: none
     * return: void
     */
    private void displayAllAccounts() {
        System.out.println("\n--- Display All Accounts ---");
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        for (ArcadeAccount a : accounts) System.out.println(a);
    }

    /**
     * method: displaySingleAccount
     * purpose: Displays one record based on Card ID.
     * parameters: none
     * return: void
     */
    private void displaySingleAccount() {
        System.out.println("\n--- Display Single Account ---");
        String id = readCardIdPrompt();
        if (id == null) return;
        ArcadeAccount acc = findByCardId(id);
        System.out.println(acc == null ? "Account not found." : acc);
    }

    /**
     * method: checkRewardEligibility
     * purpose: Displays the reward eligibility for a specific account.
     * parameters: none
     * return: void
     */
    private void checkRewardEligibility() {
        System.out.println("\n--- Check Reward Eligibility ---");
        String id = readCardIdPrompt();
        if (id == null) return;
        ArcadeAccount acc = findByCardId(id);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        System.out.printf("%s is eligible for: %s%n", acc.getCustomerName(), acc.getRewardEligible());
    }

    /**
     * method: loadFromFilePrompt
     * purpose: Loads and displays a text file’s contents with error handling.
     *          This version reads files using " - " as the separator instead of commas.
     * parameters: none
     * return: void
     */
    private void loadFromFilePrompt() {
        System.out.println("\n--- Load Data File ---");
        while (true) {
            System.out.print("Enter file path or 'cancel': ");
            String path = scanner.nextLine().trim();
            if (path.equalsIgnoreCase("cancel")) return;
            if (path.isEmpty()) {
                System.out.println("Path cannot be blank.");
                continue;
            }

            try {
                Path filePath = Paths.get(path);
                if (!Files.exists(filePath)) {
                    System.out.println("File not found.");
                    continue;
                }

                List<String> lines = Files.readAllLines(filePath);
                if (lines.isEmpty()) {
                    System.out.println("File is empty.");
                    continue;
                }

                System.out.println("\nFile Contents:");
                for (String line : lines) System.out.println(line);

                boolean importNow = readYesNo("\nWould you like to import these records? (y/n): ");
                if (!importNow) return;

                int imported = 0, failed = 0;

                for (String line : lines) {
                    String trimmed = line.trim();
                    if (trimmed.isEmpty()) continue;

                    // Split using dash with optional spaces (" - ")
                    String[] parts = trimmed.split(" - ");
                    if (parts.length != 5) {
                        failed++;
                        continue;
                    }

                    String cardId = parts[0].trim();
                    String name = parts[1].trim();
                    String dateStr = parts[2].trim();
                    String gamesStr = parts[3].trim();
                    String tokensStr = parts[4].trim();

                    if (!cardId.matches("\\d{" + CARD_ID_LENGTH + "}")) {
                        failed++;
                        continue;
                    }

                    if (findByCardId(cardId) != null) {
                        failed++; // skip duplicates
                        continue;
                    }

                    try {
                        LocalDate date = LocalDate.parse(dateStr, DATE_FORMAT);
                        int games = Integer.parseInt(gamesStr);
                        int tokens = Integer.parseInt(tokensStr);
                        if (games < 0 || tokens < 0) {
                            failed++;
                            continue;
                        }

                        accounts.add(new ArcadeAccount(cardId, name, date, games, tokens));
                        imported++;

                    } catch (Exception e) {
                        failed++;
                    }
                }

                System.out.printf("\nImport complete: %d successful, %d failed.%n", imported, failed);
                return;

            } catch (IOException e) {
                System.out.println("Error reading file. Please try again.");
            }
        }
    }

    /**
     * method: readNonEmptyString
     * purpose: Prompts for and validates a non-empty string.
     * parameters: String prompt – the message shown to the user
     * return: String – validated user input
     */
    private String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine().trim();
            if (!s.isEmpty()) return s;
            System.out.println("Input cannot be blank.");
        }
    }

    /**
     * method: readDate
     * purpose: Reads and validates a date input from the user.
     * parameters: String prompt – message displayed to user
     * return: LocalDate – parsed valid date
     */
    private LocalDate readDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(scanner.nextLine().trim(), DATE_FORMAT);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Use yyyy-mm-dd.");
            }
        }
    }

    /**
     * method: readNonNegativeInt
     * purpose: Reads and validates a non-negative integer input.
     * parameters: String prompt – input message
     * return: int – valid integer (≥ 0)
     */
    private int readNonNegativeInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= 0) return value;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid number. Must be 0 or greater.");
        }
    }

    /**
     * method: readYesNo
     * purpose: Prompts user for a yes/no answer.
     * parameters: String prompt – input message
     * return: boolean – true for yes, false for no
     */
    private boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("y") || response.equals("yes")) return true;
            if (response.equals("n") || response.equals("no")) return false;
            System.out.println("Please enter 'y' or 'n'.");
        }
    }

    /**
     * method: confirmExit
     * purpose: Confirms user intention to exit program.
     * parameters: none
     * return: boolean – true if user confirms exit
     */
    private boolean confirmExit() {
        return readYesNo("Are you sure you want to exit? (y/n): ");
    }

    /**
     * method: readCardIdPrompt
     * purpose: Prompts user for a valid Card ID or allows cancel.
     * parameters: none
     * return: String – valid 5-digit Card ID or null if cancelled
     */
    private String readCardIdPrompt() {
        while (true) {
            System.out.print("Enter 5-digit Card ID or 'cancel': ");
            String id = scanner.nextLine().trim();
            if (id.equalsIgnoreCase("cancel")) return null;
            if (id.matches("\\d{" + CARD_ID_LENGTH + "}")) return id;
            System.out.println("Invalid Card ID.");
        }
    }

    /**
     * method: findByCardId
     * purpose: Searches for an account matching a given Card ID.
     * parameters: String cardId – the ID to search for
     * return: ArcadeAccount – matching account or null if not found
     */
    private ArcadeAccount findByCardId(String cardId) {
        for (ArcadeAccount a : accounts)
            if (a.getCardId().equals(cardId)) return a;
        return null;
    }
}
