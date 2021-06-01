package commands;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class Clear {
    public void clear() throws  InterruptedException {

        ClientTCP sender = new ClientTCP();
        sender.setCommand("clear");
        sender.sending(sender);

    }
}
