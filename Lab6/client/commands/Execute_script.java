package commands;

import data.Organization;

public class Execute_script {
    public void execute_script(String filePath) throws InterruptedException {
        ClientTCP sender = new ClientTCP();
        sender.setCommand("execute_script");
        sender.setFilePath(filePath);
        sender.sending(sender);
    }

}
