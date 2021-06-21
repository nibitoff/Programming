package commands;

import data.Organization;
/**
 * Class for command "remove_first"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Remove_first {
    /**
     * Method for realizing this command
     */
    public void remove_first() throws  InterruptedException{

        Organization newOrg = new Organization(null, null, null, "0",
                null, null, null, null, ClientTCP.myUserID);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("remove_first");
        sender.setOrg(newOrg);
        sender.sending(sender);
    }
}
