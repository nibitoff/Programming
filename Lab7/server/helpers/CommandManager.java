package helpers;
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
        UserManager userManager = new UserManager();
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
                        result = consoleManager.execute_script(sender.getFilePath());
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
                        result = consoleManager.add_if_min(sender.getOrg());
                        consoleManager.save();
                        break;

                    case "remove_greater":
                        result = consoleManager.remove_greater(sender.getOrg());
                        consoleManager.save();
                        break;
                    case "count_by_full_name":
                        result = consoleManager.count_by_full_name(sender.getOrg());
                        consoleManager.save();
                        break;
                    case "filter_greater_than_annual_turnover":
                        result = consoleManager.filter_greater_than_annual_turnover(sender.getOrg());
                        consoleManager.save();
                        break;
                    case "print_unique_official_address":
                        result = consoleManager.print_unique_official_address();
                        break;
                    case "sign_up":
                        result =  userManager.addingUser(sender.getLogin(), sender.getPassword());
                        break;
                    case "sign_in":
                        result =  userManager.testingUser(sender.getLogin(), sender.getPassword());
                        break;
                    case "checkID":
                        result = userManager.checkingID(sender.getOrg().getName(), sender.getOrg().getId(), sender.getOrg().getUserId().toString());
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



