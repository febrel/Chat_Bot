package example.jbot.facebook;

public class ExamenChat {
	private String nombre;
	private String provincia;
	private String genero;
	private String fecha;
	private String horaInicio;
	private String horaFinal;
	private int nota;
	private int aciertos;
	private int errores;

	//Constructor
	public ExamenChat(String nombre, String provincia, String genero, String fecha, String horaInicio, String horaFinal,
			int nota, int aciertos, int errores) {
		this.nombre = nombre;
		this.provincia = provincia;
		this.genero = genero;
		this.fecha = fecha;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.nota = nota;
		this.aciertos = aciertos;
		this.errores = errores;
	}

	// Metodos
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	public String getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(String horaFinal) {
		this.horaFinal = horaFinal;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getAciertos() {
		return aciertos;
	}

	public void setAciertos(int aciertos) {
		this.aciertos = aciertos;
	}

	public int getErrores() {
		return errores;
	}

	public void setErrores(int errores) {
		this.errores = errores;
	}

}
