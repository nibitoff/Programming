package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.Organization;

import java.io.IOException;
/**
 * Class for command "help"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Help {
    /**
     * Method for realizing this command
     */
    public void help() throws InterruptedException {

        ClientTCP sender = new ClientTCP();
        sender.setCommand("help");
        sender.sending(sender);

    }
}
