package commands;

import data.Organization;
/**
 * Class for command "remove_annual"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Remove_greater {
    /**
     * Method for realizing this command
     * @param orgAnnual - string representation of organization's annual turnover
     */
    public void remove_annual(String orgAnnual) throws  InterruptedException{
        long annual = Long.parseLong(orgAnnual);
        Organization newOrg = new Organization(0, null, annual, "0",
                null, null, null, null,null);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("remove_greater");
        sender.setOrg(newOrg);
        sender.sending(sender);

    }
}
