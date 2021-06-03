package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Organization;

import java.io.IOException;
/**
 * Class for command "show"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Show {
    /**
     * Method for realizing this command
     */
    public void show() throws  InterruptedException {
        ClientTCP sender = new ClientTCP();
        sender.setCommand("show");
        sender.sending(sender);

    }
}
