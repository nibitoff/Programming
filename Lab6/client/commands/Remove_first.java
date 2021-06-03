package commands;

import data.Organization;
/**
 * Class for command "remove_first"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Remove_first {
    /**
     * Method for realizing this command
     */
    public void remove_first() throws  InterruptedException{
        ClientTCP sender = new ClientTCP();
        sender.setCommand("remove_first");
        sender.sending(sender);
    }
}
