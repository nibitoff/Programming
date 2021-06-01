package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Organization;

import java.io.IOException;

public class Show {
    public void show() throws  InterruptedException {

        ClientTCP sender = new ClientTCP();
        sender.setCommand("show");
        sender.sending(sender);

    }
}
