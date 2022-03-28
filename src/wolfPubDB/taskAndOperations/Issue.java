package wolfPubDB.taskAndOperations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import wolfPubDB.connect.*;
import wolfPubDB.classes.Issue;

public class Issue {

    public static ArrayList<Issue> selectIssue() {
        try {
            Connection conn = DBConnect.getConnection();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("Select * from issue");
            ArrayList<Issue> output = new ArrayList<>();
            while (res.next()) {
                Issue p = new Issue(res.getString("publicationId"), Date.valueOf(res.getDate("issueDate")), res.getString("type"));
                output.add(p);
            }
            conn.close()
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Issue> selectIssue(String publicationId) {
        try {
            Connection conn = DBConnect.getConnection();
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery("Select * from issue where publicationId = " +  publicationId);
            ArrayList<Issue> output = new ArrayList<>();
            while (res.next()) {
                Issue p = new Issue(res.getString("publicationId"), Date.getDate("issueDate"), res.getString("type"));
                output.add(p);
            }
            conn.close()
            return output;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Boolean addIssue(String publicationId, Date issueDate String type) {
        try {
            Connection conn = DBConnect.getConnection();
            String query = "insert into issue(publicationId, issueDate, type) values (?,?,?)";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setString(1, publicationId);
            stat.setDate(2, issueDate);
            stat.setString(3, type);
            stat.executeUpdate();
            conn.close()
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static Boolean updateIssue(String publicationId, Date issueDate String type) {
        try {
            Connection conn = DBConnect.getConnection();
            String query = "Update issue set issueDate = ?, type = ? where publicationId =?";
            PreparedStatement stat = conn.prepareStatement(query);
            stat.setDate(1, issueDate);
            stat.setString(2, type);
            stat.setString(3, publicationId);
            stat.executeUpdate();
            ResultSet res = stat.executeQuery("Select count(*) as total from issue where publicationId="+publicationId);
            int count = 0;
            while (res.next()) {
                count = res.getInt("total");
            }
            conn.commit();
            if (count!=0){
                conn.close()
                return  true;
            }
            conn.close()
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    public static Boolean deleteIssue(String publicationId) {
        try {
            Connection conn = DBConnect.getConnection();
            Statement stat = conn.createStatement();
            stat.executeUpdate("DELETE FROM issue WHERE publicationId= " + publicationId);
            conn.close()
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}