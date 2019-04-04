package com.dat;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DATExamenChat {
	DATConexion data = new DATConexion();

	// Ingresar
	public int ingresarNotas(String nombre, String provincia, String genero, String fecha, String horaInicio,
			String horaFinal, int notas, int aciertos, int errores) {
		int retorno = 0;
		String sentencia = "INSERT INTO usuarios (nombres, provincia, genero, fecha, horaInicio, horaFinal, nota, aciertos, errores) VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = data.AbrirConexion().prepareStatement(sentencia);
			pst.setString(1, nombre);
			pst.setString(2, provincia);
			pst.setString(3, genero);
			pst.setString(4, fecha);
			pst.setString(5, horaInicio);
			pst.setString(6, horaFinal);
			pst.setInt(7, notas);
			pst.setInt(8, aciertos);
			pst.setInt(9, errores);
			retorno = pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}

		return retorno;
	}

	// Consultar

	public ResultSet consultarRegistro() {
		ResultSet rs = null;
		try {
			PreparedStatement pre = data.AbrirConexion().prepareStatement("SELECT * FROM usuarios; ");
			rs = pre.executeQuery();// recupera un un ResultSet y envio la varible a executeQuery

		} catch (Exception e) {
			System.out.println(e);
		}
		return rs;// retornma en un obj ResultSet rs
	}
	
	
	public ResultSet consultarProvinciaGenero(String provincia,String genero) {
        ResultSet rs = null;
        try {//SELECT NOMBRES, PROVINCIA,GENERO,FECHA,NOTA,ACIERTOS,ERRORES FROM USUARIOS WHERE PROVINCIA = 'LOJA' AND GENERO = 'FEMENINO' ;
            PreparedStatement pre = data.AbrirConexion().prepareStatement("SELECT NOMBRES, PROVINCIA,GENERO,FECHA,NOTA,ACIERTOS,ERRORES FROM USUARIOS WHERE PROVINCIA ='" + provincia + "'AND GENERO = '"+genero+"';");
            rs = pre.executeQuery();//recupera un un ResultSet y envio la varible a executeQuery

        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;//retornma en un obj ResultSet rs
    }

	// Eliminar
	public int eliminarUsuario(int id_usuario) {
		int retorno = 0;
		String sentencia = "DELETE FROM usuarios WHERE id_Examenchat= ?";
		try {
			PreparedStatement pst = data.AbrirConexion().prepareStatement(sentencia);
			pst.setInt(1, id_usuario);
			retorno = pst.executeUpdate();
			pst.close();
		} catch (Exception e) {

		}
		return retorno;
	}
}
