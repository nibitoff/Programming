package helpers;
import java.util.concurrent.Callable;

public class ThreadExecuter implements Callable<String> {
    Sender s;
    public ThreadExecuter(Sender s){
        this.s = s;
    }

    @Override
    public String call() throws Exception {
        ConsoleManager consoleManager = new ConsoleManager();
        CommandManager commandManager = new CommandManager(consoleManager);
        String toClient = commandManager.cmdMode(s);
        return toClient;
    }
}
