package helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import commands.*;
import data.Organization;

import java.util.*;

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
        try(Scanner cmdreader = new Scanner(System.in)){
           // while(cmdreader.hasNextLine()){
                while (!command.equals("exit") ) {
                    System.out.println("Enter a command: ");
                    command = cmdreader.nextLine();
                    commandUser = command.trim().toLowerCase().split(" ", 2);
                    List<String> specialCommands =
                            new ArrayList<String>(Arrays.asList(new String[]{"execute_script" , "count_by_full_name"}));
                    if (!specialCommands.contains(commandUser[0]) && commandUser.length > 1){
                        int t1 = -1;
                        try {
                            t1 = Integer.parseInt(commandUser[1]);
                        }catch (Exception e){
                            System.out.println("Error the argument must be an Integer!");
                            continue;
                        }
                    }
                    try {
                        System.out.println(commandUser[0]);
                        switch (commandUser[0]) {
                            case "":
                                break;
                            case "help":
                                Help help = new Help();
                                help.help();
                                break;
                            case "info":
                                Info info = new Info();
                                info.info();
                                break;
                            case "show":
                                Show show = new Show();
                                show.show();
                                break;
                            case "add":
                                Add add = new Add();
                                add.add();
                                break;
                            case "update_id":
                                Update_id update_id = new Update_id();
                                update_id.update(commandUser[1]);
                                break;
                            case "remove_by_id":
                                Remove_id remove_id = new Remove_id();
                                remove_id.remove_id(commandUser[1]);
                                break;
                            case "clear":
                                Clear clear =new Clear();
                                clear.clear();
                                break;
                            case "execute_script":
                                Execute_script execute_script = new Execute_script();
                                execute_script.execute_script(commandUser[1]);
                                break;
                            case "exit":
                                System.out.println("Thanks for using my program!");
                                System.exit(1);
                                break;
                            case "remove_first":
                                Remove_first remove_first = new Remove_first();
                                remove_first.remove_first();
                                break;
                            case "add_if_min":
                                Add_if_min add_if_min =new Add_if_min();
                                add_if_min.add_min(commandUser[1]);
                                break;
                            case "remove_greater":
                                Remove_greater remove_greater = new Remove_greater();
                                remove_greater.remove_annual(commandUser[1]);
                                break;
                            case "count_by_full_name":
                                Count_by_full_name count_by_full_name = new Count_by_full_name();
                                count_by_full_name.count(commandUser[1]);
                                break;
                            case "filter_greater_than_annual_turnover":
                                Filter_greater_than_annual_turnover filter_turnover = new Filter_greater_than_annual_turnover();
                                filter_turnover.filter_annual_turnover(commandUser[1]);
                                break;
                            case "print_unique_official_address":
                                Print_unique_official_address print_unique_official_address = new Print_unique_official_address();
                                print_unique_official_address.print_unique_official_address();
                                break;
                            default:
                                System.out.println("Unknown commmand. Try again! Write 'help' for list of available commands.");
                                break;
                        }
                    } catch (ArrayIndexOutOfBoundsException exception) {
                        System.out.println("Element is not present in the array. Try again! Write 'help' for list of available commands.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }catch (NoSuchElementException noSuchElementException) {
            System.out.println("Program was stopped!" + noSuchElementException.getMessage());
            System.exit(1);
        }
    }
}


