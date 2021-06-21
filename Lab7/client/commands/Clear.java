package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.Organization;

import java.io.IOException;
/**
 * Class for command "clear"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Clear {
    /**
     * Method for realizing this command
     */
    public void clear() throws  InterruptedException {
        Organization newOrg = new Organization(null, null, null, "0",
                null, null, null, null, ClientTCP.myUserID);
        ClientTCP sender = new ClientTCP();
        sender.setCommand("clear");
        sender.setOrg(newOrg);
        sender.sending(sender);

    }
}
