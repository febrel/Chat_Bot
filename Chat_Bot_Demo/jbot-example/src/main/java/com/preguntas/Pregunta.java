package com.preguntas;

public class Pregunta {
	private String camino;
	private Integer responder;

	public Pregunta() {

	}

	public Pregunta(String camino, Integer responder) {
		this.camino = camino;
		this.responder = responder;
	}

	public String getCamino() {
		return camino;
	}

	public void setCamino(String camino) {
		this.camino = camino;
	}

	public Integer getResponder() {
		return responder;
	}

	public void setResponder(Integer responder) {
		this.responder = responder;
	}

}
