/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hepp.fichaescolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Juan Jose
 */
public class SqlConnect {
    private String estado = "";

    public static Connection GetConnection() {
        Connection conexion = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://107.21.223.125;databaseName=uni_matricula;user=matricula;password=guat3mala2014+;";
            conexion = DriverManager.getConnection(url);
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Error1 en la Conexión con la BD " + ex.getMessage());
            conexion = null;
        } catch (SQLException ex) {
            System.out.println("Error2 en la Conexión con la BD " + ex.getMessage());
            conexion = null;
        } catch (Exception ex) {
            System.out.println("Error3 en la Conexión con la BD " + ex.getMessage());
            conexion = null;
        } finally {
            return conexion;
        }
    }

    
}
