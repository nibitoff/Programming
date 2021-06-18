package commands;

public class Print_unique_official_address {
    /**
     * Class for command "print_unique_official_address"
     * @author Sabitov Danil
     * @version 1.0
     */
    public void print_unique_official_address() throws  InterruptedException{
        /**
         * Method for realizing this command
         */
        ClientTCP sender = new ClientTCP();
        sender.setCommand("print_unique_official_address");
        sender.sending(sender);
    }
}
