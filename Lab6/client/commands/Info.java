package commands;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public class Info {
    public void info() throws  InterruptedException {

        ClientTCP sender = new ClientTCP();
        sender.setCommand("info");
        sender.sending(sender);

    }
}
