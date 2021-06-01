package commands;

import data.Organization;

public class Remove_id {
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
