package commands;

import data.Organization;

public class Filter_greater_than_annual_turnover{
    public void filter_annual_turnover(String orgAnnual) throws InterruptedException {
        long annual = Long.parseLong(orgAnnual);
        Organization newOrg = new Organization(0, null, annual, "0",
                null, null, null, null);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("filter_greater_than_annual_turnover");
        sender.setOrg(newOrg);
        sender.sending(sender);
    }
}

