package commands;

import com.fasterxml.jackson.core.JsonProcessingException;

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

        ClientTCP sender = new ClientTCP();
        sender.setCommand("clear");
        sender.sending(sender);

    }
}
