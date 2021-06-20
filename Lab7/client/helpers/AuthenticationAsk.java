package helpers;


import exceptions.NotDeclaredLoginException;
import exceptions.NotDeclaredPasswordException;

import java.io.Console;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class for describing login and register actions
 */
public class AuthenticationAsk {
    private Scanner scanner;
    String pattern = "(?=\\S+$).{3,}";

    public AuthenticationAsk(Scanner scanner) {
        this.scanner = scanner;
    }

    /** Method that asks user's login  */
    public String askLogin() {
        String login;
        while (true) {
            try {
                System.out.println("Enter your login:");
                login = scanner.nextLine().trim();
                if (login.equals("")) throw new NotDeclaredLoginException();
                break;
            } catch (NotDeclaredLoginException e) {
                System.out.println("Login shouldn't be empty!");
            }
        }
        return login;
    }

    /** Method that asks user's password  */
    public String askPassword() {
        String password;
        while (true) {
            try {
                System.out.println("Enter password:");
                Console console = System.console();
                if (console == null) {
                    password = scanner.nextLine();
                    password = encryptPassword(password);
                } else {
                    password = String.valueOf(console.readPassword());
                    password = encryptPassword(password);
                }
                if (password.equals("")) throw new NotDeclaredLoginException();
                if (!password.matches(pattern)) throw new NotDeclaredPasswordException();
                break;
            } catch (NotDeclaredLoginException e) {
                System.out.println("Password shouldn't be empty!");
            } catch (NotDeclaredPasswordException e) {
                System.out.println("Password cannot contains spaces and it must be more than 3 characters!");
            }
        }
        return password;
    }

    /** Method that asks is user registered */
    public boolean askQuestion(String question) {
        String finalQuestion = question + " (yes/no):";
        String answer;
        while (true) {
            try {
                System.out.println(finalQuestion);
                answer = scanner.nextLine().trim();
                if (!answer.equals("yes") && !answer.equals("no")) throw new NotDeclaredLoginException();
                if (answer.equals("no")){
                    System.out.println("Please register new account!");
                }
                return answer.equals("yes");
            } catch (NotDeclaredLoginException e) {
                System.out.println("The answer must be either 'yes' or 'no'!");
            }
        }
    }

    /** Method that encrypt password using SHA-384  */
    public String encryptPassword(String password) {
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            byte[] digest = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger numRepr = new BigInteger(1, digest);
            String hashString = numRepr.toString(16);
            while (hashString.length() < 32){
                hashString += "0";
            }
            return hashString;
        } catch (NoSuchElementException | NoSuchAlgorithmException e){
            System.out.println("Something wrong! I can feel it.");
        }
        return null;
    }

}