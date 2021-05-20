import com.fasterxml.jackson.core.JsonProcessingException;
import commands.Add;
import commands.Sender;
import commands.Show;
import commands.Update_id;
import data.Organization;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class for managing user`s command
 */

public class  CommandManager {
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

                            break;
                        case "info":

                            break;
                        case "show":
                            Show.show();
                            break;
                        case "add":
                            Add.add();
                            break;
                        case "update_id":
                            Update_id.update(commandUser[1]);
                            break;
                        case "remove_by_id":

                            break;
                        case "clear":

                            break;
                        case "execute_script":

                            break;
                        case "exit":

                            break;
                        case "remove_first":

                            break;
                        case "add_if_min":
                            System.out.println("Enter an element, which will be compared with other elements in collection.");

                            break;
                        case "remove_greater":
                            System.out.println("Enter an element, which will be compared with other elements in collection.");

                            break;
                        case "count_by_full_name":
                            System.out.println("Enter organization's full name, which will be compared with element`s full name.");

                            break;
                        case "filter_greater_than_annual_turnover":
                            System.out.println("Enter organization's annual turnover, which will be compared with element's annual turnover");

                            break;
                        case "print_unique_official_address":

                            break;
                        default:
                            System.out.println("Unknown commmand. Try again! Write 'help' for list of available commands.");
                            break;
                    }
                } catch (ArrayIndexOutOfBoundsException exception) {
                    System.out.println("Element is not present in the array. Try again! Write 'help' for list of available commands.");
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            }catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program was stopped!");
            System.exit(1);
        }
    }
}


