package com.max.util;

import java.sql.*;

public abstract class SqlDataBaseTable implements SqlFunctions {
    Connection connect = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String Name = null;
    String[] TableColums;
    int numColums = 0;
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
