package com.wipro.candidate.util;


import java.sql.Connection;
import java.sql.DriverManager;


public class DBUtil {
public static Connection getDBConn()
{
	Connection con=null;
	//write code here
	try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection(
            "jdbc:oracle:thin:@localhost:1521:XE",
            "durai",   // replace with your username
            "durai");  // replace with your password
    } catch (Exception e) {
        e.printStackTrace();
    }
	return con;
	
	
	
    
   
	
	
	
	
}
}
