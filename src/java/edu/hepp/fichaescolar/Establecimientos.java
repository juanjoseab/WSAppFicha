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
public class Establecimientos {

    public String getEstablecimientos(String init, String rowsNum) {
        //return init;

        String Json = "";
        Integer topSelect = Integer.parseInt(rowsNum) + Integer.parseInt(init);
        SqlConnect conn = new SqlConnect();
        Connection cnx = conn.GetConnection();
        if (cnx != null) {
            System.out.println("conexion establecida");
        } else {
            return "ERROR EN LA CONEXION";
        }

        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT TOP " + rowsNum + " * FROM establecimientos WHERE CodigoUDI NOT IN (SELECT TOP " + String.valueOf(topSelect) + " CodigoUDI FROM establecimientos ORDER BY CodigoUDI) ORDER BY CodigoUDI;");
            ResultsetConvertToJson converter = new ResultsetConvertToJson();
            JSONArray jsonResult;
            jsonResult = converter.convert(rs);
            Json = jsonResult.toString();
            cnx.close();
        } catch (Exception e) {
            return e.getMessage();
        }

        return Json;
    }

    public String getEstablecimientosNum() {
        String response = "";
        SqlConnect conn = new SqlConnect();
        Connection cnx = conn.GetConnection();
        if (cnx != null) {
            System.out.println("conexion establecida");
        }

        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT COUNT(CodigoUDI) AS total FROM establecimientos;");

            while (rs.next()) {
                response = rs.getString("total");
            }
            cnx.close();
        } catch (Exception e) {
            //System.out.println(e.getMessage());
        }

        return response;
    }

    public String getEstablecimientoInfo(String codigoUDI) {
        String Json = "";
        SqlConnect conn = new SqlConnect();
        Connection cnx = conn.GetConnection();
        if (cnx != null) {
            System.out.println("conexion establecida");
        } else {
            return "ERROR EN LA CONEXION";
        }

        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM establecimientos WHERE CodigoUDI = '" + codigoUDI + "';");
            ResultsetConvertToJson converter = new ResultsetConvertToJson();
            JSONArray jsonResult;
            jsonResult = converter.convert(rs);
            Json = jsonResult.toString();
            cnx.close();
        } catch (Exception e) {
            return e.getMessage();
        }

        return Json;
    }

    public String getDatosCalidadEducativaByCodigoUDI(String codigoUDI) {
        String Json = "";
        return Json;
    }

    public String getMatriculaHistoricaAcumuladaByCodigoUDI(String codigoUDI) {
        String Json = "";
        SqlConnect conn = new SqlConnect();
        Connection cnx = conn.GetConnection();
        if (cnx != null) {
            System.out.println("conexion establecida");
        } else {
            return "ERROR EN LA CONEXION";
        }

        try {
            Statement stmt = cnx.createStatement();
            ResultSet rs;
            String sql = "SELECT Anio, "
                    + "SUM(InscritosInicialHombres + InscritosInicialMujeres) AS inscripcionInicialAcumulada,"
                    + "SUM(PromovidosMujeres + PromovidosHombres) AS promovidosAcumulados,"
                    + "SUM(RepitentesHombres + RepitentesMujeres) AS repitentesAcumulados,"
                    + "SUM(InscritosFinal) AS inscripcionFinalAcumulada,"
                    + "SUM(NoPromovidosHombres + NoPromovidosMujeres) AS nopromovidosAcumulados, "
                    + "SUM(RetiradosHombres + RetiradosMujeres) AS retiradosAcumulados "
                    + "FROM matricula "
                    + "WHERE CodigoUDI = '" + codigoUDI + "' "
                    + "GROUP BY Anio";

            rs = stmt.executeQuery(sql);

            //ResultsetConvertToJson converter = new ResultsetConvertToJson();

            JSONArray jsonResult;

            Json = this.toJson(rs);
            //Json = jsonResult.toString();
            cnx.close();
        } catch (Exception e) {
            return e.getMessage();
        }

        return Json;
    }

    private String toJson(ResultSet rs) {
        String str = "";
        try {
            while (rs.next()) {
                if(str.length() > 0){
                    str += ",";
                }else{
                    str += "[";
                }
                str += "{'Anio':'" + rs.getString("Anio") + "',";
                str += "'inscripcionInicialAcumulada':'" + rs.getString("inscripcionInicialAcumulada") + "',";
                str += "'promovidosAcumulados':'" + rs.getString("promovidosAcumulados") + "',";
                str += "'repitentesAcumulados':'" + rs.getString("repitentesAcumulados") + "',";
                str += "'inscripcionFinalAcumulada':'" + rs.getString("inscripcionFinalAcumulada") + "',";
                str += "'nopromovidosAcumulados':'" + rs.getString("nopromovidosAcumulados") + "',";
                str += "'promovidosAcumulados':'" + rs.getString("promovidosAcumulados") + "',";
                str += "'retiradosAcumulados':'" + rs.getString("retiradosAcumulados") + "'}";

            }
        } catch (Exception e) {
            return e.getMessage();
        }

        return str + "]";
    }

}
