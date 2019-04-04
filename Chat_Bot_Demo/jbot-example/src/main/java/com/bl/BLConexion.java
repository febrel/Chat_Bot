package com.bl;

import java.sql.SQLException;

import com.dat.DATConexion;

public class BLConexion {
	//para poder acceder a paquete DAT
    DATConexion ManejadorConexion = new DATConexion();
    public void CerrarConexion() throws SQLException
    {
        ManejadorConexion.CerrarConexion();
    }

}
