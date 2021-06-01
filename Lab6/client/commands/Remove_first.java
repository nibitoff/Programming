package commands;

import data.Organization;

public class Remove_first {
    public void remove_first() throws  InterruptedException{
        ClientTCP sender = new ClientTCP();
        sender.setCommand("remove_first");
        sender.sending(sender);
    }
}
