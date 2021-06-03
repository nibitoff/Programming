
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


public class Reciever {
    Sender s;
    Selector selector;
    ServerSocketChannel socket;
    InetSocketAddress inetAddress;

    public void run() throws IOException, InterruptedException, ClassNotFoundException {
             selector = Selector.open();
             socket = ServerSocketChannel.open();
             inetAddress = new InetSocketAddress("localhost", 3345);
             socket.bind(inetAddress);
             socket.configureBlocking(false);


            int ops = socket.validOps();
            SelectionKey selectKy = socket.register(selector, ops, null);
            while (true){
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey myKey = iterator.next();
                if (myKey.isAcceptable()) {
                    SocketChannel client = socket.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                }else if (myKey.isReadable()) {
                    SocketChannel client = (SocketChannel) myKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(2048);
                    client.read(buffer);
                    String result = new String(buffer.array()).trim();
                    try {
                        s = new Sender();
                        ObjectMapper mapper = new ObjectMapper();
                        s = mapper.readerForUpdating(s).readValue(result);

                        if (s.getCommand().equals("exit")) {
                            client.close();
                            System.out.println("Client has broken the connection");
                        }
                        ConsoleManager consoleManager = new ConsoleManager();

                        CommandManager commandManager = new CommandManager(consoleManager);
                        String toClient = commandManager.cmdMode(s);


                        System.out.println("The message was sent to client!");
                        byte[] message = toClient.getBytes();
                        ByteBuffer bufferToSend = ByteBuffer.wrap(message);
                        client.write(bufferToSend);
                    } catch (NullPointerException exception) {
                        System.out.println("Incorrect command");
                    }

                }
                iterator.remove();
                }
            }

        }
    }

