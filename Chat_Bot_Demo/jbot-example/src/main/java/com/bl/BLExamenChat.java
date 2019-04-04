package com.bl;

import java.sql.ResultSet;

import com.dat.DATConexion;
import com.dat.DATExamenChat;

import example.jbot.facebook.ExamenChat;

public class BLExamenChat {
	DATExamenChat data = new DATExamenChat();

	// Ingresar
	public int ingresarNotas(ExamenChat objExamen) {

		return data.ingresarNotas(objExamen.getNombre(), objExamen.getProvincia(), objExamen.getGenero(),
				objExamen.getFecha(), objExamen.getHoraInicio(), objExamen.getHoraFinal(), objExamen.getNota(),
				objExamen.getAciertos(), objExamen.getErrores());

	}

	// Consultar
	public ResultSet consultarRegistros() {
		ResultSet rs = data.consultarRegistro();
		return rs;
	}
	

	public ResultSet consultarProvinciaGenero(String provincia,String genero) {
        ResultSet rs = data.consultarProvinciaGenero(provincia, genero);
        return rs;
    }

	// Eliminar
	public int eliminarUsuario(int id_Usuario) {
		int retorno = data.eliminarUsuario(id_Usuario);
		return retorno;
	}
}
