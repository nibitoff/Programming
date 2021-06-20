package commands;

import data.Organization;
import helpers.User;
/**
 * Class for command "sign_up" and "sign_in"
 * @author Sabitov Danil
 * @version 1.0
 */
public class Login {
    /**
     * Method for realizing "sign_up" command
     */
    public void sign_up(String login, String password) throws  InterruptedException {
        ClientTCP sender = new ClientTCP();
        sender.setCommand("sign_up");
        //System.out.println("User = " + user);
        sender.setLogin(login);
        sender.setPassword(password);
        sender.sending(sender);
    }

    /**
     * Method for realizing "sign_in" command
     */
    public void sign_in(String login, String password) throws  InterruptedException {
        ClientTCP sender = new ClientTCP();
        sender.setCommand("sign_in");
        //System.out.println("User = " + user);
        sender.setLogin(login);
        sender.setPassword(password);
        sender.sending(sender);
    }

}
