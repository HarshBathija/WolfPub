package wolfPubDB.taskAndOperations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Statement;

import wolfPubDB.connect.*;
import wolfPubDB.classes.AuthorClass;

public class Author{

    public static ArrayList<AuthorClass> selectAuthor() {
        try {
            Connection conn = DBConnect.getConnection();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("Select * from author");
            ArrayList<AuthorClass> output = new ArrayList<>();
            while (res.next()) {
                AuthorClass row = new AuthorClass(res.getString("staffId"));
                output.add(row);
            }
            conn.close();
            System.out.println("staffId");
                    
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}