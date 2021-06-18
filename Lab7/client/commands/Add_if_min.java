package commands;

import data.Organization;
/**
 * Class for command "add_if_min"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Add_if_min {
    /**
     * Method for realizing this command
     * @param orgAnnual - string representation of organization's annual turnover
     */
    public void add_min(String orgAnnual) throws InterruptedException {
        long annual = Long.parseLong(orgAnnual);
        Organization newOrg = new Organization(0, null, annual, "0",
                null, null, null, null, null);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("add_if_min");
        sender.setOrg(newOrg);
        sender.sending(sender);
    }
}
