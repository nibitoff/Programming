package commands;


import data.Organization;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Sender {
    private String command;
    private Organization org;

    Socket socket = new Socket();
    private DataOutputStream oos;
    private DataOutputStream in;


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


    public void sending(Sender sender)  throws InterruptedException {
       try (
                Socket socket = new Socket("localhost", 3345);
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream())) {

// проверяем живой ли канал и работаем если живой
            while (!socket.isOutputShutdown()) {

// ждём консоли клиента на предмет появления в ней данных
                if (br.ready()) {
// данные появились - работаем
                    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                    String json = ow.writeValueAsString(sender);
                    String toServer = json.replaceAll("[\\\t|\\\n|\\\r]"," ");
                    oos.writeUTF(toServer);
                    oos.flush();

                    String showCommand = in.readUTF();
                    System.out.println(showCommand);
                }
            }
        } catch (
                UnknownHostException e) {
            System.out.println("Error! Unknown host! Try again.");
        } catch (
                IOException e) {
           System.out.println("Error! Try again!");
        }
    }



}


