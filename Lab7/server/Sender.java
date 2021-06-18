import data.Organization;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class  Sender {
    private String command;
    private Organization org;
    private String filePath;
    private String ids;

    private String login;
    private String password;

    public Sender(){}

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

}

