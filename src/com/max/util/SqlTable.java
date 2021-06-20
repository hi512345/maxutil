package com.max.util;

import java.sql.*;

public class SqlTable extends SqlDataBaseTable {
    @Override
    public boolean create(String lockedName, String[] Tableformat, String[] TableformatType, String url, String password, String username) {
        if (null != connect) {
            return false;
        } else {
            try {
                String prossesedTableFormat = "";
                for (int i = 0; i<TableformatType.length;i++) {
                    if(i!=Tableformat.length) {
                        prossesedTableFormat = prossesedTableFormat + Tableformat[i] + " " + TableformatType[i] + ((TableformatType.length-i == 1)? "" : ", ");
                    } else{
                        prossesedTableFormat = prossesedTableFormat + TableformatType[i];
                    }
                }
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
                connect = DriverManager.getConnection(url + "user=" + username + "&password=" + password);
                // Statements allow to issue SQL queries to the database
                preparedStatement = connect.prepareStatement("create table " + lockedName + " ( "+prossesedTableFormat+")");
                Name = lockedName;
                preparedStatement.executeUpdate();
                TableColums = Tableformat;
            } catch (Exception e) {
                e.printStackTrace();

            }
            return true;
        }
    }

    @Override
    public void remove(String condition) {
        try {
            preparedStatement = connect.prepareStatement("DELETE FROM " + Name + "WHERE ?");
            preparedStatement.setString(1, Name);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try {
            preparedStatement = connect.prepareStatement("DROP table " + Name);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(String[] val, String condition) {
        String change = "";
        for(int i = 0; i< val.length; i++){
           change = change + TableColums[i] + " " + val[i];
        }
        try {
            preparedStatement = connect.prepareStatement("UPDATE " + Name + "SET " + change + "WHERE " + condition);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public ResultSet search(String columbs) {
        try {
            preparedStatement = connect.prepareStatement("SELECT " + columbs + "From " + Name);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }
    public ResultSet search(String columbs, String condition) {
        try {
            preparedStatement = connect.prepareStatement("SELECT " + columbs + "From " + Name + " where " + condition);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public void add(String[] val) {
        try {
            String prossesedval = null;
            for(int i = 0; i<val.length; i++){
                prossesedval = prossesedval + val[i] +((val.length==i+1)? "": ", ");
            }
            preparedStatement = connect.prepareStatement("Insert INTO " + Name + "Values(" + prossesedval + ");");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void add(String[] val, String[] columbs) {
        try {
            String prossesedval = "";
            String prossesedcolumbs = "";
            for(int i = 0; i<val.length; i++){
                prossesedval = prossesedval + "'"+val[i]+"'" +((val.length==i+1)? "": ", ");
            }
            for(int i = 0; i<val.length; i++){
                prossesedcolumbs = prossesedcolumbs + columbs[i] +((columbs.length==i+1)? "": ", ");
            }
            preparedStatement = connect.prepareStatement("Insert INTO " + Name + "("+ prossesedcolumbs +") " + "Values(" + prossesedval + ");");
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public void connect(String lockedName, String[] Tableformat, String url, String password, String username) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                // Setup the connection with the DB
                connect = DriverManager.getConnection( url,username,password);
                // Statements allow to issue SQL queries to the database
                Name = lockedName;
                TableColums = Tableformat;
            } catch (Exception e) {
                e.printStackTrace();

            }

    }
}
