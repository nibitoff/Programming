package commands;

import data.Organization;
/**
 * Class for command "count_by_full_name"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Count_by_full_name {
    /**
     * Method for realizing this command
     * @param fullName - string representation of organization's full name
     */
    public void count(String fullName) throws InterruptedException {
        Organization newOrg = new Organization(0, null, null, "0",
                fullName.trim(), null, null, null);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("count_by_full_name");
        sender.setOrg(newOrg);
        sender.sending(sender);
    }
}
