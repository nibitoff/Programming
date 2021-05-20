
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import data.Organization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.Map;


public class Reciever {
    public void recieve() throws IOException, InterruptedException {
         Sender s = new Sender();
        try (ServerSocket server = new ServerSocket(3345)) {
            System.out.println("Successful connection!");
            Socket client = server.accept();
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            ConsoleManager consoleManager = new ConsoleManager(s);

            while (!client.isClosed()) {
                String entry = in.readUTF();
                ObjectMapper mapper = new ObjectMapper();
                 s = mapper.readValue(entry, Sender.class);
                System.out.println(s.getCommand());
                switch (s.getCommand()){
                    case "show":

                        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                        String json = ow.writeValueAsString(consoleManager.show());
                        String toClient = json.replaceAll("[\\\t|\\\n|\\\r]"," ");
                        System.out.println(toClient);
                        out.writeUTF(toClient);
                        out.flush();
                        break;
                    default:
                         consoleManager = new ConsoleManager(s);
                        break;
                }
                 consoleManager = new ConsoleManager(s);

            }
            in.close();
            client.close();
        } catch (IOException e) {
            System.out.println("Ошибка чего-то!");
        }


    }
}