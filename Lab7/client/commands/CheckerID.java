package commands;

import data.Organization;
/**
 * Class for command "checkID"
 * @author Sabitov Danil
 * @version 1.0
 */
public class CheckerID {
    /**
     * Method for realizing this command
     */
    public void checker(String orgId, String command) throws  InterruptedException{
        int id = Integer.parseInt(orgId);
        Organization newOrg = new Organization(id, command, null, "0",
                null, null, null, null, ClientTCP.myUserID);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("checkID");
        sender.setOrg(newOrg);
        sender.sending(sender);

    }
}
