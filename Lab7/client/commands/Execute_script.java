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
        Organization newOrg = new Organization(null, null, null, "0",
                null, null, null, null, ClientTCP.myUserID);
        sender.setCommand("execute_script");
        sender.setOrg(newOrg);
        sender.setFilePath(filePath);
        sender.sending(sender);
    }

}
