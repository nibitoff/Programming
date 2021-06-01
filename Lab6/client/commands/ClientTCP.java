package commands;


import data.Organization;

import java.io.*;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import helpers.CommandManager;

public class ClientTCP {
    private String command;
    private Organization org;

    private String toServer;
    private InetSocketAddress socket;
    private SocketChannel client;


    public String getCommand() {
        return command;
    }
    public void setCommand(String command) {
        this.command = command;
    }

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }



    public void sending(ClientTCP sender)  throws InterruptedException {
       // System.out.println("Socket connected = " + client.isConnected());
            try {
                 socket = new InetSocketAddress("localhost", 3345);
                 client = SocketChannel.open(socket);
            } catch (UnknownHostException e) {
                System.out.println("Unknown server's host and port! Try again!");
                System.out.println(1);
            } catch (IOException e) {
                System.out.println("Can't connect to the server. Check connection and try again!");
                System.exit(1);
            }
        try {
            while (client.isConnected()) {
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json = ow.writeValueAsString(sender);
                    toServer = json.replaceAll("[\\\t|\\\n|\\\r]"," ");
                    byte[] message = toServer.getBytes();
                    ByteBuffer buffer = ByteBuffer.wrap(message);

               System.out.println("toServer =" + toServer);
                   try {
                       client.write(buffer);
                       System.out.println("Sent!!!!!!!!!");
                   }catch (Exception e){
                       System.out.println("ERROR =" +e.getMessage());
                   }
                   buffer.clear();

                   try {
                       ByteBuffer crunchifyBuffer = ByteBuffer.allocate(2048);
                       client.read(crunchifyBuffer);
                       String result = new String(crunchifyBuffer.array()).trim();
                       System.out.println(result);
                   }catch (Exception e){
                       System.out.println(e.getMessage());
                   }

                    try {
                        CommandManager commandManager = new CommandManager();
                        commandManager.cmdMode();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                   client.close();


               } catch (IOException e) {
           System.out.println("Error! Try again!" + e.getMessage());
        }
    }



}


