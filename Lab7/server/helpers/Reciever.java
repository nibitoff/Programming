package helpers;

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
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;


/**
 * @author Sabitov Danil
 * @version 1.0
 * Class describing connection with client
 */
public class Reciever{
    private static Selector selector;
    private static Sender s;
    ServerSocketChannel socket;
    InetSocketAddress inetAddress;
    private ForkJoinPool answerGet = ForkJoinPool.commonPool();
    ExecutorService service = Executors.newFixedThreadPool(5);



    public void run() throws IOException{
        try {
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
                        SocketChannel client = socket.accept();
                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);

                    } else if (myKey.isReadable()) {
                        SocketChannel client1 = (SocketChannel) myKey.channel();
                       try {
                        ThreadReader threadReader = new ThreadReader();
                        threadReader.ThreadReader(myKey,client1);
                        s = threadReader.compute();
                        //answerGet.execute(new ThreadReader().ThreadReader(client1));
                        /*
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

                         */
                           Future<String> task = service.submit(new ThreadExecuter(s));
                           String toClient ="";
                           while(!task.isDone())
                           {
                               Thread.sleep(1);
                           }
                           try {
                                toClient = task.get();
                           }catch (ExecutionException e) {
                               System.out.println("Error! Something wrong with thread executor");
                           }
                           //String toClient = threadExecuter.run(s);
                           /* выполнение запроса
                            ConsoleManager consoleManager = new ConsoleManager();

                            CommandManager commandManager = new CommandManager(consoleManager);
                            String toClient = commandManager.cmdMode(s);
                            */



                           ThreadSender threadSender = new ThreadSender(client1);
                           threadSender.sendingMessage(toClient);


                           /*
                            byte[] message = toClient.getBytes();
                            ByteBuffer bufferToSend = ByteBuffer.wrap(message);
                            client1.write(bufferToSend);
                            System.out.println("The message was sent to client!");


                            */

                        } catch (NullPointerException exception) {
                        } catch (InterruptedException e) {
                           e.printStackTrace();
                       }


                    }
                    iterator.remove();
                }
            }
        }catch (IOException e){
        }
    }


    public void checkInput() {
        Scanner scanner = new Scanner(System.in);
        Runnable input = () -> {
            try {
                while (true) {
                    String[] userCommand = (scanner.nextLine().trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                    if (userCommand[0].equals("exit")) {
                        System.out.println("Server stopped!");
                        System.exit(0);
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("Server stopped!");
                System.exit(0);
            }
        };
        Thread thread = new Thread(input);
        thread.start();
    }
}


