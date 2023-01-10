/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hadia
 */
public class DataAccessLayerFinal {
    public static int insert (Player player) throws SQLException{
        int result=0;
    DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
    Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/finalproject","tictactoe","tictactoe");
     PreparedStatement stm=con.prepareStatement("INSERT INTO PLAYERS VALUES (?,?,?,?,?)");
     stm.setString(1, player.getUsername());
     stm.setString(2, player.getEmail());
     stm.setString(3,player.getPassword());
     stm.setInt(4, player.getScore());
     stm.setString(5,player.getStatus() );
     
     result =stm.executeUpdate();
     con.commit();
     stm.close();
     con.close();
    return result;
    }
     public static List<Player>getPlayers () throws SQLException{
        DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/javalastlab","root","root"); 
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery("SELECT * FROM  PLAYERS "); 
        List<Player> list = query(res);
        con.close();                                     
        st.close();
        return list; 
      }

    private static List<Player> query(ResultSet res) throws SQLException {
        List<Player> list =  new ArrayList<>();
        while  (res.next()){   
        Player player = new Player(res.getString(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5));
        list.add(player);
        }
    return list;
    } 
    }
