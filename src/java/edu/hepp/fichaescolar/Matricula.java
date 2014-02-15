/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hepp.fichaescolar;

import java.sql.Connection;
import java.sql.*;
import org.json.JSONArray;

/**
 *
 * @author Juan Jose
 */
public class Matricula {
    public String getMatricula(String init, String rowsNum) {
        String Json = "";        
        int topSelect = Integer.parseInt(rowsNum) + Integer.parseInt(init);
        SqlConnect conn = new SqlConnect();
        Connection cnx = conn.GetConnection();
        if (cnx != null) {
            System.out.println("conexion establecida");
        }
        
        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT TOP " + rowsNum + " * FROM matricula WHERE CodigoUDI NOT IN (SELECT TOP " + topSelect + " CodigoUDI FROM matricula WHERE ANIO = 2012 ORDER BY CodigoUDI) AND ANIO = 2012 ORDER BY CodigoUDI;");
            ResultsetConvertToJson converter = new ResultsetConvertToJson();
            JSONArray jsonResult;
            jsonResult = converter.convert(rs);
            Json = jsonResult.toString();
            System.out.println(jsonResult.toString());
            cnx.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return Json;
    }
    
    public String getMatriculaNum() {
        String response = "";
        SqlConnect conn = new SqlConnect();
        Connection cnx = conn.GetConnection();
        if (cnx != null) {
            System.out.println("conexion establecida");
        }
        
        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT COUNT(CodigoUDI) AS total FROM matricula;");
            
            while(rs.next()){
                response = rs.getString("total");
            }            
            //System.out.println(response);
            cnx.close();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }

        return response;
    }
}
