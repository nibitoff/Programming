import data.Organization;

import java.util.NoSuchElementException;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class for managing user`s command
 */

public class  CommandManager {
    /**
     * Field which takes user`s commands
     */
    private ConsoleManager consoleManager;
    /**
     * Field which receives user`s command
     */
    private String command;
    /**
     * Field which separates user's input into a command and an argument to it
     */
    private String[] commandUser;

    {
        command = "";
    }

    public CommandManager(){}

    public CommandManager(ConsoleManager consoleManager) {
        this.consoleManager = consoleManager;
    }
    public CommandManager(CollectionChecker collectionChecker) {}


    /**
     * Method that starts console mod
     * @return
     */
    public String cmdMode(Sender sender) {
        String result = "";
        try {
            // commandUser = sender.getCommand();
            try {
                switch (sender.getCommand()) {
                    case "":
                        break;
                    case "help":
                        result = consoleManager.help();//!!!!not working
                        break;
                    case "info":
                        result = consoleManager.info();
                        break;
                    case "show":
                        result = consoleManager.show();
                        break;
                    case "add":
                        System.out.println(consoleManager);
                        consoleManager.add(sender.getOrg());
                        consoleManager.save();
                        result = consoleManager.show();
                        break;
                    case "update_id":
                        Organization org2 = sender.getOrg();
                        result = consoleManager.update_id(org2);
                        consoleManager.save();
                        break;
                    case "remove_by_id":
                        result = consoleManager.remove_by_id(sender.getOrg());
                        consoleManager.save();
                        break;
                    case "clear":
                        result = consoleManager.clear();
                        consoleManager.save();
                        break;
                    case "execute_script":
                        consoleManager.execute_script(commandUser[1]);
                        consoleManager.save();
                        break;
                    case "exit":
                        consoleManager.exit();
                        break;
                    case "remove_first":
                       result = consoleManager.remove_first();
                        consoleManager.save();
                        break;
                    case "add_if_min":
                        System.out.println("Enter an element, which will be compared with other elements in collection.");
                        consoleManager.add_if_min(new Organization(consoleManager.makerID(), consoleManager.makerName(),
                                consoleManager.makerAnnualTurnover(), consoleManager.getDate().toString(),
                                consoleManager.makerFullName(), consoleManager.makerOrganizationType(),
                                consoleManager.makerAddress(), consoleManager.makerCoordinates()));
                        consoleManager.save();
                        break;

                    case "remove_greater":
                        result = consoleManager.remove_greater(sender.getOrg());
                        consoleManager.save();
                        break;
                    case "count_by_full_name":
                        System.out.println("Enter organization's full name, which will be compared with element`s full name.");
                        consoleManager.count_by_full_name(consoleManager.makerFullName());
                        consoleManager.save();
                        break;
                    case "filter_greater_than_annual_turnover":
                        System.out.println("Enter organization's annual turnover, which will be compared with element's annual turnover");
                        consoleManager.filter_greater_than_annual_turnover(consoleManager.makerAnnualTurnover());
                        consoleManager.save();
                        break;
                    case "print_unique_official_address":
                        result = consoleManager.print_unique_official_address();
                        break;
                    default:
                        System.out.println("Unknown commmand. Try again! Write 'help' for list of available commands.");
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Element is not present in the array. Try again! Write 'help' for list of available commands.");
            }
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program was stopped!");
            System.exit(1);
        }
        return result;
    }
}



