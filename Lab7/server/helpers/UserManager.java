package helpers;
import data.*;

import java.io.IOException;
import java.sql.*;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sabitov Danil
 * @version 1.0
 * Class describing commands that manage USERS table in database
 */
public class UserManager {
    private Connection c;
    private Statement stmt;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock readLock = readWriteLock.readLock();
    private Lock writeLock = readWriteLock.writeLock();
    private static final Logger logger = Logger.getLogger(UserManager.class.getName());


    public String addingUser(String login, String password){
        String result = "";
        try {
        Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("datebase,"username", "password");
        c.setAutoCommit(false);
        logger.log(Level.INFO,"-- Opened database successfully");
        String sql = "";
        logger.log(Level.INFO,"Login = " + login + " " + "Password = " + password);
        stmt = c.createStatement();
        sql = "INSERT INTO USERS (LOGIN, PASSWORD) VALUES ('"+ login +"', '"+ password +"');";
        //sql = "SELECT L.*, S.Description FROM Log as L LEFT JOIN Stations as S ON L.id_station = S.id_station WHERE S.id_station = 5";


        writeLock.lock();
        //readLock.lock();
        stmt.executeUpdate(sql);
        //readLock.unlock();
        writeLock.unlock();

        ResultSet rs = stmt.executeQuery( "SELECT * FROM USERS;" );
        Integer id = null;
            while ( rs.next() ) {
                id = rs.getInt("id");
            }
        stmt.close();
        c.commit();
        logger.log(Level.INFO,"--New user was added successfully!" + id);
        result +=  "New user was added successfully!" + id;
    }catch (ClassNotFoundException | SQLException |NullPointerException e) {
            logger.log(Level.WARNING,"User was not added! "  + e.getMessage());
    }
        return result;
}

public String checkingID(String command, Integer id, String userId) {
    String result = "";
    try {
    Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("datebase,"username", "password");
    c.setAutoCommit(false);
    stmt = c.createStatement();

        logger.log(Level.INFO,"-- Opened database successfully");
        readLock.lock();
        //writeLock.lock();
        ResultSet rs = stmt.executeQuery("SELECT USERID FROM ORGANIZATIONS WHERE ID = " + id + " ;");
        //writeLock.unlock();
        readLock.unlock();
        result = "$CheckId$=" + command + "=" + id + "=";
        if (rs.next()) {
            String userFromData = rs.getString(1);
            if (!userFromData.equals(userId)){
                result += "NoAccess";
            }else {
                result += "true";
            }
        } else {
            result += "false";
        }
        rs.close();
        stmt.close();
        c.commit();
    }catch (ClassNotFoundException | SQLException e){
        logger.log(Level.WARNING,"Error with checking id!");
    }
    return result;
}


    public String testingUser(String login, String password){
        String result = "";
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("datebase,"username", "password");
            c.setAutoCommit(false);
            logger.log(Level.INFO,"-- Opened database successfully");
            String sql = "";
            logger.log(Level.INFO,"Login = " + login + " " + "Password = " + password);
            stmt = c.createStatement();
            sql = "SELECT * FROM USERS WHERE LOGIN = '"+ login + "' AND PASSWORD = '"+ password +"';";
            //sql = "SELECT L.*, S.Description FROM Log as L LEFT JOIN Stations as S ON L.id_station = S.id_station WHERE S.id_station = 5";
           // System.out.println(sql);

            writeLock.lock();
            ResultSet rs = stmt.executeQuery( sql );
            writeLock.unlock();
            Integer id = null;
            // int count = rs.next();
            if (rs.next()){
                id = rs.getInt("id");
                result = "Welcome registered user!" + id;
            }
            else {
                result = "Users with this login/password don't exist";
            }
            stmt.close();
            c.commit();
        }catch (ClassNotFoundException | SQLException |NullPointerException e) {
            logger.log(Level.INFO,"Error with testing login and password! "  + e.getMessage());
        }
        return result;
    }

}
