import java.io.IOException;

/**
 * Main class for starting a program
 * @author Sabitov Danil
 * @version 1.0
 */

public class Main {
    public static void main(String[] args) throws IOException {
      try{
       System.out.println("Program started working!");
       CommandManager commandManager = new CommandManager(new ConsoleManager(args[0]));
       commandManager.cmdMode();
    } catch(ArrayIndexOutOfBoundsException exception){
        System.out.println("Incorrect filepath was entered! Try again!");
        System.exit(1);
        }
}
    }