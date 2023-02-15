package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDAO {

    public Message insertMessage(Message message){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO message (message_id, message_text, time_posted_epoch, posted_by) VALUES (?, ?, ?, ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
             
            preparedStatement.setInt(1, message.message_id);
            preparedStatement.setString(2, message.message_text);
            preparedStatement.setLong(3, message.time_posted_epoch);
            preparedStatement.setInt(4, message.posted_by) ;

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()){
                return new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"),
                rs.getInt("time_posted_epoch"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



    public List<Message> getAllMessages(){
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            String sql = "SELECT * FROM message";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch"));
                messages.add(message);

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
        
    }

    public Message getMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, message_id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                    rs.getInt("posted_by"),
                    rs.getString("message_text"),
                    rs.getInt("time_posted_epoch"));
                return message;

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
        
    }

    public void deleteMessageById(int message_id){
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "DELETE FROM message WHERE message_id = ?" ;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write preparedStatement's setString and setInt methods here.
            preparedStatement.setInt(1, message_id);
            preparedStatement.executeUpdate();

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        
    }

    public void updateMessageTxtById(int message_id, Message message ) {
        Connection connection = ConnectionUtil.getConnection();
        try {
            //Write SQL logic here
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write PreparedStatement setString and setInt methods here.
            preparedStatement.setString(1, message.message_text);
            preparedStatement.setInt(2, message_id);
            


            preparedStatement.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Message> getMessagesFromUser(int posted_by) {
        Connection connection = ConnectionUtil.getConnection();
        List<Message> messages = new ArrayList<>();
        try {
            //Write SQL logic here
            String sql = "SELECT * FROM message WHERE account_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            //write PreparedStatement setString and setInt methods here.
            preparedStatement.setInt(1, posted_by);
            

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Message message = new Message(rs.getInt("message_id"),
                rs.getInt("posted_by"),
                rs.getString("message_text"),
                rs.getInt("time_posted_epoch"));
                messages.add(message);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return messages;
    }
}
