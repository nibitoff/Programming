package commands;

import data.Organization;

public class CheckerID {
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
