package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexao {

    public Statement ST;
    public ResultSet RS;
    public static String status = "Sem conexao...";

    public static java.sql.Connection getConnectionMySQL() {
        
        Connection c = null;
        
        try {
           String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);

            String url = "jdbc:mysql://localhost:3307/sys_cafe";

            c = DriverManager.getConnection(url,"root","");

            if (c != null) {

                //status = ("STATUS--->Conectado com sucesso!");
                //System.out.println(status);
            } else {

                //status = ("STATUS--->Não foi possivel realizar conexão");
                //System.out.println(status);
            }
            return c;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } 
        
         
        }
            
    }
          


