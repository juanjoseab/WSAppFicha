/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.hepp.fichaescolar;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Juan Jose
 */
@WebService(serviceName = "WSApp")
public class WSApp {

        
    /**
     * Operacion del WS que optiene los establecimientos a partir de una fila de inicio y 
     * la solicitud de cuantas filas a partir de ella.
     */    
    @WebMethod(operationName = "getEstablecimientos")
    public String getEstablecimientos(@WebParam(name = "init") String init, @WebParam(name = "rowsNum") String rowsNum) {
        Establecimientos listado = new Establecimientos();
        String lista = listado.getEstablecimientos(init, rowsNum);
        return lista;
    }
    
    
    /**
     * Operacion del WS que optiene la cantidad de establecimientos abiertos.
     */ 
    @WebMethod(operationName = "getNumEstablecimientos")
    public String getNumEstablecimientos() {
        Establecimientos listado = new Establecimientos();
        String lista = listado.getEstablecimientosNum();
        return lista;
    }
    
    
    /**
     * Operacion del WS que optiene los registros de matricula historica a partir 
     * de una fila de inicio y  la solicitud de cuantas filas a partir de ella.
     */    
    @WebMethod(operationName = "getMatricula")
    public String getMatricula(@WebParam(name = "init") String init, @WebParam(name = "rowsNum") String rowsNum) {
        Matricula listado = new Matricula();
        String lista = listado.getMatricula(init, rowsNum);
        return lista;
    }
    
    
    /**
     * Operacion del WS que optiene la cantidad de registros de matricula historica.
     */ 
    @WebMethod(operationName = "getNumMatricula")
    public String getNumMatricula() {
        Matricula listado = new Matricula();
        String lista = listado.getMatriculaNum();
        return lista;
    }
    
    /**
     * Operacion del WS que optiene la la información de un establecimiento.
     */ 
    @WebMethod(operationName = "getEstablecimientoInfo")
    public String getEstablecimientoInfo(@WebParam(name = "codigoUDI") String codigoUDI) {
        Establecimientos establecimiento = new Establecimientos();
        String lista = establecimiento.getEstablecimientoInfo(codigoUDI);
        return lista;
    }
    
    /**
     * Operacion del WS que optiene la la información de un establecimiento.
     */ 
    @WebMethod(operationName = "getMatriculaHistoricaAcumuladaByCodigoUDI")
    public String getMatriculaHistoricaAcumuladaByCodigoUDI(@WebParam(name = "codigoUDI") String codigoUDI) {
        Establecimientos establecimiento = new Establecimientos();
        String lista = establecimiento.getMatriculaHistoricaAcumuladaByCodigoUDI(codigoUDI);
        return lista;
    }
    
    
}
