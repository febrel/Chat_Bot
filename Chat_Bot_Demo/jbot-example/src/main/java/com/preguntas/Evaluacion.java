package com.preguntas;

import java.util.ArrayList;
import java.util.List;

public class Evaluacion {

	private List<Pregunta> pregunta = new ArrayList<>();
	private String fuente;

	public Evaluacion() {
		
		fuente = "https://";
		
		pregunta.add(new Pregunta("image.ibb.co/b8Qy58/Pregunta1.png", 0));
		pregunta.add(new Pregunta("image.ibb.co/n3VWQ8/Pregunta2.png", 1));
		pregunta.add(new Pregunta("image.ibb.co/kutkk8/Pregunta3.png", 2));
		pregunta.add(new Pregunta("image.ibb.co/kA5y58/Pregunta4.png", 3));
		pregunta.add(new Pregunta("image.ibb.co/mT1udT/Pregunta5.png", 4));
		pregunta.add(new Pregunta("image.ibb.co/hiHZdT/Pregunta6.png", 5));
		pregunta.add(new Pregunta("image.ibb.co/dGjrQ8/Pregunta7.png", 6));
		pregunta.add(new Pregunta("image.ibb.co/dGV9Co/Pregunta8.png", 7));
		pregunta.add(new Pregunta("image.ibb.co/gOErQ8/Pregunta9.png", 8));
		pregunta.add(new Pregunta("image.ibb.co/eqkWQ8/Pregunta10.png", 9));
		pregunta.add(new Pregunta("i.ibb.co/VjxCSWZ/Mapa.png", 10));
		
		
		
		
	}
	
	public List<Pregunta> getPregunta(){
		return pregunta;
	}
	
	public String getFuente() {
		return fuente;
	}

}
