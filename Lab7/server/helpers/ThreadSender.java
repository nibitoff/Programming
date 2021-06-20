package helpers;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadSender {
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    SocketChannel client;

    public ThreadSender(SocketChannel client) {
        this.client = client;

    }

    public void sendingMessage(String toClient) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    byte[] message = toClient.getBytes();
                    ByteBuffer bufferToSend = ByteBuffer.wrap(message);
                    client.write(bufferToSend);
                    System.out.println("The message was sent to client!");
                } catch (Exception e) {
                    System.out.println("Error! Something wrong with sending message to client!");
                }
            }
        };
        executor.execute(task);

    }
}
