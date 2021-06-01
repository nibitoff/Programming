
import data.Organization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static String myResult;

    public static void main(String[] args) throws InterruptedException, IOException {
        try {
            System.out.println("Program started working!");
            CommandManager commandManager = new CommandManager(new CollectionChecker(args[0]));
            System.out.println("Server started!");

                Reciever reciever = new Reciever();
                reciever.run();

            }catch (IOException e){
                System.out.println("Can't connect to client! The server will shutdown!");
            } catch (ArrayIndexOutOfBoundsException | ClassNotFoundException exception) {
                System.out.println("Incorrect filepath was entered! Try again!");
                System.exit(1);
            }

    }
}
