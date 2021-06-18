
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.*;
import java.net.BindException;
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
    private static Selector selector;
    private static Sender s;
    ServerSocketChannel socket;
    InetSocketAddress inetAddress;

    public void run() throws IOException {
        selector = Selector.open();
        socket = ServerSocketChannel.open();
        inetAddress = new InetSocketAddress("localhost", 7342);
        socket.bind(inetAddress);
        socket.configureBlocking(false);
                socket.accept();
                int ops = socket.validOps();
                socket.register(selector, ops, null);
                while (true) {
                    selector.select();
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey myKey = iterator.next();
                        if (myKey.isAcceptable()) {
                            /*
                            try {
                                handleAccept(socket, myKey);
                            } catch (IOException e) {
                                break;
                            }

                             */

                            SocketChannel client = socket.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);

                        } else if (myKey.isReadable()) {
                            /*
                            try {
                                handleRead(myKey);
                            }catch (IOException e) {
                                break;
                            }

                          */


                            SocketChannel client1 = (SocketChannel) myKey.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(2048);
                            try {
                                client1.read(buffer);
                            } catch (IOException e) {
                                myKey.interestOps(0);
                                client1.close();
                                break;
                            }
                            String result = new String(buffer.array()).trim();
                            try {
                                s = new Sender();
                                ObjectMapper mapper = new ObjectMapper();
                                s = mapper.readerForUpdating(s).readValue(result);
                                if (s.getCommand().equals("exit")) {
                                    client1.close();
                                    System.out.println("Client has broken the connection");
                                }
                                ConsoleManager consoleManager = new ConsoleManager();

                                CommandManager commandManager = new CommandManager(consoleManager);
                                String toClient = commandManager.cmdMode(s);


                                System.out.println("The message was sent to client!");
                                byte[] message = toClient.getBytes();
                                ByteBuffer bufferToSend = ByteBuffer.wrap(message);
                                client1.write(bufferToSend);
                            } catch (NullPointerException exception) {
                                System.out.println("Incorrect command");
                            }


                    }
                        iterator.remove();
                    }
                }

    }
    /*
    private static void handleAccept(ServerSocketChannel mySocket,
                                     SelectionKey key) throws IOException {

        System.out.println("Connection Accepted!");

        SocketChannel client = mySocket.accept();
        client.configureBlocking(false);

        client.register(selector, SelectionKey.OP_READ);
    }

    private static void handleRead(SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        try {
            client.read(buffer);
        }catch (IOException e){
            key.interestOps(0);
            client.close();
            break;
        }
        String result = new String(buffer.array()).trim();
        System.out.println(result);
        try {
            System.out.println("CHECK1");
            s = new Sender();
            System.out.println("CHECK2");
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("CHECK3");
            s = mapper.readerForUpdating(s).readValue(result);
            System.out.println("CHECK4");
            System.out.println(s.getLogin());
            if (s.getCommand().equals("exit")) {
                client.close();
                System.out.println("Client has broken the connection");
            }
            ConsoleManager consoleManager = new ConsoleManager();

            CommandManager commandManager = new CommandManager(consoleManager);
            String toClient = commandManager.cmdMode(s);


            if (toClient == null || toClient ==""){
                toClient = "Sorry. Something gone wrong on server! Try another command!";
            }
            System.out.println("The message was sent to client!");
            byte[] message = toClient.getBytes();
            ByteBuffer bufferToSend = ByteBuffer.wrap(message);
            client.write(bufferToSend);
        } catch (NullPointerException exception) {
            System.out.println("Incorrect command");
        }
    }

     */
}


