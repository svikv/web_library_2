package com.weblibrary.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private String user;
    private String password;
    private String url;
    private Connection con;

    public DBManager(String user, String password, String url){
        this.user = user;
        this.password = password;
        this.url = url;
        setConnection();
    }

    private void setConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.con;
    }

    public void closeConnection(){
        try{
            con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "DBManager{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", con=" + con +
                '}';
    }
}
