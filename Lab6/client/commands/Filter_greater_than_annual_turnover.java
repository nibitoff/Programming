package commands;

import data.Organization;

import java.io.IOException;

/**
 * Class for command "filter_annual_turnover"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Filter_greater_than_annual_turnover{
    /**
     * Method for realizing this command
     * @param orgAnnual - string representation of organization's annual turnover
     */
    public void filter_annual_turnover(String orgAnnual) throws InterruptedException{
        long annual = Long.parseLong(orgAnnual);
        Organization newOrg = new Organization(0, null, annual, "0",
                null, null, null, null);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("filter_greater_than_annual_turnover");
        sender.setOrg(newOrg);
        sender.sending(sender);
    }
}

