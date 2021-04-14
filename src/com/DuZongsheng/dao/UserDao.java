package com.DuZongsheng.dao;

import com.DuZongsheng.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao implements IUserDao{
    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        String sql="INSERT INTO usertable VALUES (?,?,?,?,?,?);";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setInt(1,user.getId());
        pstmt.setString(2,user.getUsername());
        pstmt.setString(3,user.getPassword());
        pstmt.setString(4,user.getEmail());
        pstmt.setString(5,user.getGender());
        pstmt.setDate(6, (java.sql.Date) user.getBirthday());

        int rs = pstmt.executeUpdate();
        if(rs==1) return true;
        return false;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        String sql="DELETE FROM usertable WHERE id=?;";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setInt(1,user.getId());
        int rs = pstmt.executeUpdate();
        return rs;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        String sql="UPDATE usertable SET username=?,password=?,email=?,gender=?,birthday=? WHERE id=?;";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getPassword());
        pstmt.setString(3,user.getEmail());
        pstmt.setString(4,user.getGender());
        pstmt.setDate(5, (java.sql.Date) user.getBirthday());
        pstmt.setInt(6,user.getId());

        int rs = pstmt.executeUpdate();
        return rs;
    }

    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        String sql="select * from usertable where id=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setInt(1,id);
        ResultSet rs=ps.executeQuery();
        User user=new User();
        if(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday"));
        }
        return user;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        String sql="select * from usertable where username=? and password=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,username);
        ps.setString(2,password);
        ResultSet rs=ps.executeQuery();
        User user=null;
        if(rs.next()) {
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday"));
        }
        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        List<User> userList=new ArrayList<User>();

        String sql="select * from usertable where username=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,username);
        ResultSet rs=ps.executeQuery();
        User user=new User();
        while(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        List<User> userList=new ArrayList<User>();

        String sql="select * from usertable where password=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,password);
        ResultSet rs=ps.executeQuery();
        User user=new User();
        while(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {
        List<User> userList=new ArrayList<User>();

        String sql="select * from usertable where email=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,email);
        ResultSet rs=ps.executeQuery();
        User user=new User();
        while(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        List<User> userList=new ArrayList<User>();

        String sql="select * from usertable where gender=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setString(1,gender);
        ResultSet rs=ps.executeQuery();
        User user=new User();
        while(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        List<User> userList=new ArrayList<User>();

        String sql="select * from usertable where birthday=?";
        PreparedStatement ps=con.prepareStatement(sql);
        ps.setDate(1, (java.sql.Date) birthDate);
        ResultSet rs=ps.executeQuery();
        User user=new User();
        while(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        List<User> userList=new ArrayList<User>();

        String sql="select * from usertable";
        PreparedStatement ps=con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        User user=new User();
        while(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthday(rs.getDate("birthday"));
            userList.add(user);
        }
        return userList;
    }
}
