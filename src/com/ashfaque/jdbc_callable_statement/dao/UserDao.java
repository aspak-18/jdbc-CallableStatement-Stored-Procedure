package com.ashfaque.jdbc_callable_statement.dao;

import com.ashfaque.jdbc_callable_statement.Connection.UserConnection;
import com.ashfaque.jdbc_callable_statement.dto.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    Connection con = UserConnection.getUserConnection();

    public User insertUserDao(User user) {
        try {
            CallableStatement call = con.prepareCall(("CALL InsertUser(?,?,?,?,?)"));
            call.setInt(1, user.getId());
            call.setString(2, user.getName());
            call.setString(3, user.getEmail());
            call.setString(4, user.getAddress());
            call.setObject(5, user.getDob());

            call.execute();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteUserDao(int id) {
        try {
            CallableStatement call = con.prepareCall("CALL DeleteUser(?)");
            call.setInt(1, id);

            call.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUserDao(int id, String name) {
        try {
            CallableStatement call = con.prepareCall("call UpdateUser(?,?)");
            call.setString(1, name);
            call.setInt(2, id);

            call.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet displayUserByIdDao(int id) {
        try {
            CallableStatement call = con.prepareCall("call DisplayById(?)");
            call.setInt(1, id);
            return call.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> displayAllUser() {
        try {
            CallableStatement call = con.prepareCall("call DisplayAllUser()");
            List<User> users = new ArrayList<>();
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String dob = rs.getString("dob");
                User u = new User(id, name, email, address, LocalDate.parse(dob));
                users.add(u);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
