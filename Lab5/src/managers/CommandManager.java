package managers;
import data.Organization;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class for managing user`s command
 */

public class  CommandManager {
    /** Field which takes user`s commands */
    private final ConsoleManager consoleManager;
    /** Field which receives user`s command */
    private String command;
    /** Field which separates user's input into a command and an argument to it */
    private String[] commandUser;
    {
        command = "";
    }

    /**
     * Constructor for making a commander
     * @param consoleManager
     * ConsoleManager class object which makes user`s commands
     */
    public CommandManager(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }

    /** Method that starts console mod */
    public void cmdMode() {
        try (Scanner cmdreader = new Scanner(System.in)) {
            while (!command.equals("exit")) {
                System.out.println("Enter a command: ");
                command = cmdreader.nextLine();
                commandUser = command.trim().toLowerCase().split(" ", 2);
                try {
                    switch (commandUser[0]) {
                        case "":
                            break;
                        case "help":
                            consoleManager.help();
                            break;
                        case "info":
                            consoleManager.info();
                            break;
                        case "show":
                            consoleManager.show();
                            break;
                        case "add":
                            consoleManager.add();
                            break;
                        case "update_id":
                            consoleManager.update_id(commandUser[1]);
                            break;
                        case "remove_by_id":
                            consoleManager.remove_by_id(commandUser[1]);
                            break;
                        case "clear":
                            consoleManager.clear();
                            break;
                        case "save":
                            consoleManager.save();
                            break;
                        case "execute_script":
                            consoleManager.execute_script(commandUser[1]);
                            break;
                        case "exit":
                            consoleManager.exit();
                            break;
                        case "remove_first":
                            consoleManager.remove_first();
                            break;
                        case "add_if_min":
                            System.out.println("Enter an element, which will be compared with other elements in collection.");
                            consoleManager.add_if_min(new Organization(consoleManager.makerID(), consoleManager.makerName(),
                                    consoleManager.makerAnnualTurnover(), consoleManager.makerDate().toString(),
                                    consoleManager.makerFullName(), consoleManager.makerOrganizationType(),
                                    consoleManager.makerAddress(), consoleManager.makerCoordinates()));
                            break;
                        case "remove_greater":
                            System.out.println("Enter an element, which will be compared with other elements in collection.");
                            consoleManager.remove_greater(consoleManager.makerAnnualTurnover());
                            break;
                        case "count_by_full_name":
                            System.out.println("Enter organization's full name, which will be compared with element`s full name.");
                            consoleManager.count_by_full_name(consoleManager.makerFullName());
                            break;
                        case "filter_greater_than_annual_turnover":
                            System.out.println("Enter organization's annual turnover, which will be compared with element's annual turnover");
                            consoleManager.filter_greater_than_annual_turnover(consoleManager.makerAnnualTurnover());
                            break;
                        case "print_unique_official_address":
                            consoleManager.print_unique_official_address();
                            break;
                        default:
                            System.out.println("Unknown commmand. Try again! Write 'help' for list of available commands.");
                            break;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    System.out.println("Element is not present in the array. Try again! Write 'help' for list of available commands.");
                }
                }
            }catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program was stopped!");
            System.exit(1);
        }
    }
}


