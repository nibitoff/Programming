
import data.Organization;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static String myResult;

    public static void main(String[] args) throws InterruptedException, IOException {
        class Sender {
            private String command;
            private Organization org;

            public Sender(String command, Organization organization) {
                this.command = command;
                this.org = organization;
            }

            public String getCommand() {
                return command;
            }

            public void setCommand(String command) {
                this.command = command;
            }

            public Organization getOrg() {
                return org;
            }

            public void setOrg(Organization org) {
                this.org = org;
            }
        }
        System.out.println("Server started!");
        Reciever reciever = new Reciever();
        reciever.recieve();
    }
}
