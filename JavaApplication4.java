/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author akeen.bese15seecs
 */
public class JavaApplication4 {

    /**
     * @param args the command line arguments
     */
    static final String DB_URL = "jdbc:mysql://localhost/student";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "seecs@123";
    public static void main(String[] args) {
        // TODO code application logic here
        Connection conn = null;
   Statement stmt = null;
   Statement stmt1 = null;
   String fileName= "C:\\Users\\akeen.bese15seecs\\Downloads\\names.csv";
  
        File file= new File(fileName);
       
        // this gives you a 2-dimensional array of strings
        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;       

        ArrayList<String> values= new ArrayList<String>();
        ArrayList<String> values1= new ArrayList<String>();
        try{
            inputStream = new Scanner(file);
            
            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] i=line.split(",");
                //System.out.println(i[0]);
                values.add(i[0]);
                
            }
           

            inputStream.close();
        
 
        Class.forName("com.mysql.jdbc.Driver");
   
      //STEP 3: Open a connection
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      //conn.setAutoCommit(false);
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      
      stmt = conn.createStatement();
     // stmt1 = conn.createStatement();
    
      

      //STEP 5: Extract data from result set
      
     long lStartTime = System.nanoTime();

		//task
       
		//end
       PreparedStatement stmt2=null;
        for(int i=0;i<4999;i++) {
            
            Random rand = new Random();

            int  n = rand.nextInt(8) + 1;
           
               String sql="Insert into data values('"+values.get(i)+"','"+i+"','"+n+"','SEECS')";
               stmt2=conn.prepareStatement("INSERT INTO data " +
                   "VALUES (?,?,?,?)");
              //String sql = "INSERT INTO data VALUES ('ssda1', 2, 1, 'seecs')";
             stmt2.setString(1,values.get(i));//1 specifies the first parameter in the query  
            stmt2.setInt(2,i);
           stmt2.setInt(3,n);
           stmt2.setString(4,"SEECS");
          // stmt2.addBatch();

      stmt2.executeUpdate(sql);
     //stmt2.executeUpdate();  
      //conn.commit();
         //stmt2.executeBatch();     
            
        }
        
        
        //conn.commit();
         long lEndTime = System.nanoTime();

		//time elapsed
        long output = lEndTime - lStartTime;

        System.out.println("Elapsed time in milliseconds: " + output / 1000000);
    }
    
     // for(int i=0;i<5000;i++){
      //String sql = "INSERT INTO data " +
        //           "VALUES (100, 'Zara', 'Ali', 18)";
      
      //stmt.executeUpdate(sql);
      //conn.commit();
      

   catch(FileNotFoundException | ClassNotFoundException | SQLException e){
   
   }
   
}    
}
