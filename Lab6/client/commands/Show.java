package commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Organization;

public class Show {
    public static void show() throws JsonProcessingException, InterruptedException {

        Sender sender = new Sender();
        sender.setCommand("show");


        ObjectMapper mapper = new ObjectMapper();
        //Converting the Object to JSONString
        String jsonString = mapper.writeValueAsString(sender);
        String toServer = jsonString.replaceAll("[\\\t|\\\n|\\\r]"," ");
        System.out.println(toServer);
        sender.sending(sender);

    }
}
