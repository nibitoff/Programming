package commands;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
/**
 * Class for command "info"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Info {
    /**
     * Method for realizing this command
     */
    public void info() throws  InterruptedException {
        ClientTCP sender = new ClientTCP();
        sender.setCommand("info");
        sender.sending(sender);

    }
}
