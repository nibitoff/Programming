package commands;

import data.Organization;
/**
 * Class for command "remove_id
 * @author Sabitov Danil
 * @version 1.0
 */
public class Remove_id {
    /**
     * Method for realizing this command
     * @param orgId - string representation of organization's id
     */
    public void remove_id(String orgId) throws  InterruptedException{
        int id = Integer.parseInt(orgId);
        Organization newOrg = new Organization(id, null, null, "0",
                null, null, null, null);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("remove_by_id");
        sender.setOrg(newOrg);
        sender.sending(sender);

    }
}
