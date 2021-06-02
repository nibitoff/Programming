
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
             selector = Selector.open(); // selector is open here
             socket = ServerSocketChannel.open();
             inetAddress = new InetSocketAddress("localhost", 3345);
             socket.bind(inetAddress);
             socket.configureBlocking(false);


            int ops = socket.validOps();
            SelectionKey selectKy = socket.register(selector, ops, null);
            System.out.println("CHECK");
            while (true){
                selector.select();
                Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
                Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();
                while (crunchifyIterator.hasNext()) {
                    SelectionKey myKey = crunchifyIterator.next();
                if (myKey.isAcceptable()) {
                    SocketChannel crunchifyClient = socket.accept();
                    crunchifyClient.configureBlocking(false);
                    crunchifyClient.register(selector, SelectionKey.OP_READ);
                }else if (myKey.isReadable()) {
                    SocketChannel crunchifyClient = (SocketChannel) myKey.channel();
                    ByteBuffer crunchifyBuffer = ByteBuffer.allocate(2048);
                    crunchifyClient.read(crunchifyBuffer);
                    String result = new String(crunchifyBuffer.array()).trim();
                    try {
                        s = new Sender();
                        ObjectMapper mapper = new ObjectMapper();
                        s = mapper.readerForUpdating(s).readValue(result);

                        if (s.getCommand().equals("exit")) {
                            crunchifyClient.close();
                            System.out.println("Client has broken the connection");
                        }
                        ConsoleManager consoleManager = new ConsoleManager();

                        CommandManager commandManager = new CommandManager(consoleManager);
                        String toClient = commandManager.cmdMode(s);


                        System.out.println("Что-то там отправляет = " + "\n" + toClient);
                        byte[] message = toClient.getBytes();
                        ByteBuffer buffer = ByteBuffer.wrap(message);
                        crunchifyClient.write(buffer);
                        System.out.println("Sent!!!!!!!!!");
                        //Thread.sleep(2000);
                    } catch (NullPointerException exception) {
                        System.out.println("Incorrect command");
                    }

                }
                crunchifyIterator.remove();
                }
            }

        }
    }

