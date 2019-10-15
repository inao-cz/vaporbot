package me.inao.botforgod.utils;

import lombok.Getter;
import me.inao.botforgod.classes.ExceptionCatcher;

import java.sql.*;

@Getter
public class SQLite {
    private Connection connection;

    public void openConnection(){
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:captcha.sqlite")){
            if(connection != null){
                System.out.println("Cannot detect captcha.sqlite.. Creating..");
            }
            this.connection = connection;
            return;
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
        this.connection = null;
    }
    public boolean execute(PreparedStatement stmt){
        try{
            if(stmt.execute()){
                return true;
            }
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
        return false;
    }

    public ResultSet getResults(PreparedStatement stmt){
       try{
           return stmt.executeQuery();
       }catch (Exception e){
           new ExceptionCatcher(e);
       }
       return null;
    }
}
