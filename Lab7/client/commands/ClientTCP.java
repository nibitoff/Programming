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
import helpers.AunthecationSupervisor;
import helpers.CommandManager;
import helpers.User;

/**
 * Class for connection with server
 * @author Sabitov Danil
 * @version 1.0
 */
public class ClientTCP {
    private String command;
    private Organization org;
    private String filePath;

    public static Integer myUserID;
    private String login;
    private String password;

    private String collectionsIDs;

    private String toServer;
    private InetSocketAddress socket;
    private SocketChannel client;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Integer getMyUserID() {
        return myUserID;
    }

    public static void setMyUserID(Integer myUserID) {
        ClientTCP.myUserID = myUserID;
    }

    /**
     * Method for sending and recieving messages from server
     * @param sender - parameter that contains fields
     */
    public void sending(ClientTCP sender)  throws InterruptedException {
        try {
            socket = new InetSocketAddress("localhost", 7342);
            client = SocketChannel.open(socket);

            while (true) {
                while (client.isConnected()) {
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json = ow.writeValueAsString(sender);
                    toServer = json.replaceAll("[\\\t|\\\n|\\\r]", " ");
                    System.out.println(toServer);
                    byte[] message = toServer.getBytes();
                    ByteBuffer buffer = ByteBuffer.wrap(message);

                    try {
                        client.write(buffer);
                        System.out.println("The command was sent!");
                    } catch (Exception e) {
                        System.out.println("ERROR = " + e.getMessage());
                    }
                    buffer.clear();


                        ByteBuffer bufferToRead = ByteBuffer.allocate(2048);
                        client.read(bufferToRead);
                        String result = new String(bufferToRead.array()).trim();
                        if (result.indexOf("New user was added successfully!") > -1){
                            myUserID = Integer.parseInt(result.split("!")[1]);
                            result = "New user was added successfully!";

                        }
                        if (result.equals("Users with this login/password don't exist")){
                            System.out.println(result + ". Try again!");
                            AunthecationSupervisor handle = new AunthecationSupervisor(new Scanner(System.in));
                            handle.handle();
                        }
                        if (result.indexOf("Welcome registered user!") > -1){
                            myUserID = Integer.parseInt(result.split("!")[1]);
                            result = "Welcome registered user!";
                        }
                        if (result.indexOf("$CheckId$=") > -1){
                            String command = result.split("=")[1];
                            String check1 = result.split("=")[3];
                            switch (check1){
                                case "true":
                                    switch (command) {
                                        case "update_id":
                                            Update_id update_id = new Update_id();
                                            update_id.update(result.split("=")[2]);
                                            break;
                                        case "remove_by_id":
                                            Remove_id remove_id = new Remove_id();
                                            remove_id.remove_id(result.split("=")[2]);
                                            break;
                                    }
                                    break;
                                case "false":
                                    result = "Error! Organization with this ID wasn't found!";
                                    break;
                                case "NoAccess":
                                    result = "Error! You have no access to change this record!";
                                    break;
                            }
                        }
                        // collectionsIDs = result.split("|")[1];
                        //  result = result.split("|")[0];
                        System.out.println(result);
                        if (result.equals("The minimal element was found! Enter element's values.")) {
                            Add add = new Add();
                            add.add();
                        }

                    try {
                        CommandManager commandManager = new CommandManager();
                        commandManager.cmdMode();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                client.close();

            }
        } catch (IOException e) {
            System.out.println("Can't connect to the server. Check connection and try again!" + "\n" +
                    "Waiting for connection!");
            Thread.sleep(5 * 1000);
            sending(sender);
        }
    }

    public void closing() throws IOException {
        client.close();
    }


}


