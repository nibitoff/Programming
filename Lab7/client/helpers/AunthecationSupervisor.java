package helpers;


import commands.Login;

import java.util.Scanner;

public class AunthecationSupervisor {
    private final String loginCommand = "sign_in";
        private final String registerCommand = "sign_up";
        private Scanner scanner;

        public AunthecationSupervisor(Scanner scanner) {
            this.scanner = scanner;
        }

        public void handle() throws InterruptedException {
            AuthenticationAsk authAsker = new AuthenticationAsk(scanner);
            String command = authAsker.askQuestion("Have you got an account already?") ? loginCommand : registerCommand;
            Login sign_up = new Login();
            if (command.equals("sign_up")) {
                sign_up.sign_up(authAsker.askLogin(), authAsker.askPassword());
            }
            else {
                sign_up.sign_in(authAsker.askLogin(), authAsker.askPassword());
            }
        }
    }

