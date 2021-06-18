package commands;

import data.Organization;
import helpers.User;

public class Login {

    public void sign_up(String login, String password) throws  InterruptedException {
        ClientTCP sender = new ClientTCP();
        sender.setCommand("sign_up");
        //System.out.println("User = " + user);
        sender.setLogin(login);
        sender.setPassword(password);
        sender.sending(sender);
    }

    public void sign_in(String login, String password) throws  InterruptedException {
        ClientTCP sender = new ClientTCP();
        sender.setCommand("sign_in");
        //System.out.println("User = " + user);
        sender.setLogin(login);
        sender.setPassword(password);
        sender.sending(sender);
    }

}
