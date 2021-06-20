package com.max.util;

import java.sql.ResultSet;

public interface SqlFunctions {
    public boolean create(String lockedName,String[] Tableformat,String[] TableformatType, String url, String password, String username);
    public void remove(String condition);
    public void delete();
    public void update(String[] val, String condition);
    public ResultSet search(String columbs);
    public void add(String[] val,String[] columbs);
    public void add(String[] val);
    public void connect(String lockedName,String[] Tableformat, String url, String password, String username);
}
