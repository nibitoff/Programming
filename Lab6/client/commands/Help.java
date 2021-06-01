package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import data.Organization;

import java.io.IOException;

public class Help {
    public void help() throws InterruptedException {

        ClientTCP sender = new ClientTCP();
        sender.setCommand("help");
        sender.sending(sender);

    }
}
