package commands;

import data.Organization;

public class Remove_greater {
    public void remove_annual(String orgAnnual) throws  InterruptedException{
        long annual = Long.parseLong(orgAnnual);
        Organization newOrg = new Organization(0, null, annual, "0",
                null, null, null, null);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("remove_greater");
        sender.setOrg(newOrg);
        sender.sending(sender);

    }
}
