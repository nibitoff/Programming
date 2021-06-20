package helpers;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveTask;

public class ThreadReader extends RecursiveTask<Sender> {
    SocketChannel client;
    Sender s;
    SelectionKey myKey;

    public void ThreadReader(SelectionKey myKey,SocketChannel client){
        this.client = client;

    }


    @Override
    protected Sender compute() {
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        try {
            client.read(buffer);
        } catch (IOException e) {
            try {
                client.close();
            } catch (IOException ioException) {
                myKey.interestOps(0);
            }
            myKey.interestOps(0);
        }
        String result = new String(buffer.array()).trim();
        try {
            s = new Sender();
            ObjectMapper mapper = new ObjectMapper();
            s = mapper.readerForUpdating(s).readValue(result);
        } catch (NullPointerException | JsonProcessingException exception) {
            System.out.println("Incorrect command");
        }
        return s;
    }
}
