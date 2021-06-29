import data.Organization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import helpers.*;
public class Server {
    public static String myResult;
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        try {
            LogManager.getLogManager().readConfiguration(Server.class.getResourceAsStream("logging.properties"));
            logger.log(Level.INFO,"Program started working!");
            CommandManager commandManager = new CommandManager(new CollectionChecker());
            logger.log(Level.INFO,"Server started!");
        } catch (ArrayIndexOutOfBoundsException exception) {
            logger.log(Level.SEVERE,"Something wrong with Server.class");
            System.exit(1);
        }
        Reciever reciever = new Reciever();
        reciever.checkInput();
        reciever.run();
    }
}

