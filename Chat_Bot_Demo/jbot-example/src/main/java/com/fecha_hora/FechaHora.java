package com.fecha_hora;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FechaHora {

	public String retornaFecha() {
		// Fecha
		Date sisFecha = new Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		String damefecha = formatoFecha.format(sisFecha);
		return damefecha;
	}

	public String retornaHora() {
		// Hora tiempo
		Calendar fecha = Calendar.getInstance();
		int hora = fecha.get(Calendar.HOUR_OF_DAY);
		
		Date sisHora = new Date();
		SimpleDateFormat fechaHora = new SimpleDateFormat(hora + ":mm:ss");
		String dameHora = fechaHora.format(sisHora);

		return dameHora;

	}

}
