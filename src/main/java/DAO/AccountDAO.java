package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDAO {

    public Account postAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "INSERT INTO account (username, password) VALUES (?, ?) ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            
            preparedStatement.setString(1, account.username);
            preparedStatement.setString(2, account.password);
            
            preparedStatement.executeUpdate();
            ResultSet pkeyResultSet = preparedStatement.getGeneratedKeys();
            if(pkeyResultSet.next()){
                int generated_account_id = (int) pkeyResultSet.getLong(1);
                return new Account(generated_account_id, account.getUsername(), account.getPassword());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
        
        
    }

    public Account getUserAccount(Account account){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, account.username);
            ps.setString(2, account.password);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Account userAccount = new Account(rs.getInt("account_id"),
                    rs.getString("username"),
                    rs.getString("password"));
                return userAccount;

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Account getAccountByUsername(String username){
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT * FROM account WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Account account = new Account(rs.getInt("account_id"),
                rs.getString("username"),
                rs.getString("password"));
                return account;

            }

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean isUsernameDuplicate(String username) {
        boolean exists = false;
        Connection connection = ConnectionUtil.getConnection();
        try {
            String sql = "SELECT COUNT(*) FROM account WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    exists = true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        return exists;
    }
 }


