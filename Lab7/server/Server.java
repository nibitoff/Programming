import data.Organization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import helpers.*;
public class Server {
    public static String myResult;

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        try {
            System.out.println("Program started working!");
            CommandManager commandManager = new CommandManager(new CollectionChecker());
            System.out.println("Server started!");
        } catch (ArrayIndexOutOfBoundsException exception) {
            System.out.println("Incorrect filepath was entered! Try again!");
            System.exit(1);
        }
        Reciever reciever = new Reciever();
        reciever.checkInput();
        reciever.run();


    }
}

