package me.inao.botforgod.utils;

import lombok.Getter;
import me.inao.botforgod.classes.ExceptionCatcher;

import java.sql.*;

@Getter
public class SQLite {

    public Connection openConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection("jdbc:sqlite:db.sqlite");
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
        return null;
    }
    public void execute(Connection connection, PreparedStatement stmt){
        try{
            stmt.execute();
            stmt.close();
            connection.close();
        }catch (Exception e){
            new ExceptionCatcher(e);
        }
    }

    public ResultSet getResults(Connection connection, PreparedStatement stmt){
       try{
           return stmt.executeQuery();
       }catch (Exception e){
           new ExceptionCatcher(e);
       }
       return null;
    }
}
