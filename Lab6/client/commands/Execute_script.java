package commands;

import data.Organization;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *  * Class for command "execute_script"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Execute_script {
    /**
     * Method for realizing this command
     * @param filePath - string representation of path to file which contains script
     */
    public void execute_script(String filePath) throws InterruptedException {
        ClientTCP sender = new ClientTCP();
        sender.setCommand("execute_script");
        sender.setFilePath(filePath);
        sender.sending(sender);
    }

}
