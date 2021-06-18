import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import commands.*;
import helpers.*;

/**
 * Main class for starting a program
 * @author Sabitov Danil
 * @version 1.0
 */
public class ClientMain {
    public static void main(String[] args) throws InterruptedException {
        try {
            System.out.println("Сlient program started!");
            AunthecationSupervisor handle = new AunthecationSupervisor(new Scanner(System.in));
            handle.handle();
            CommandManager commandManager = new CommandManager();
            commandManager.cmdMode();
       } catch(ArrayIndexOutOfBoundsException exception){
            System.out.println("Incorrect filepath was entered! Try again!");
            System.exit(1);
        }
    }
    }
