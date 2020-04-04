package ejercicio1;

import java.io.Serializable;

public class Pelicula implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String titulo;
	private String a�o;
	private String director;
	private String duracion;
	private String sinopsis;
	private String reparto;
	private String sesiones;
	
	public Pelicula(String titulo, String a�o, String director, String duracion, String sinopsis, String reparto, String sesiones) {
		this.setTitulo(titulo);
		this.setA�o(a�o);
		this.setDirector(director);
		this.setDuracion(duracion);
		this.setSinopsis(sinopsis);
		this.setReparto(reparto);
		this.setSesiones(sesiones);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = "-----" + titulo + "-----";
	}

	public String getA�o() {
		return a�o;
	}

	public void setA�o(String a�o) {
		this.a�o = "A�o: " + a�o;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = "Director: " + director;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = "Duraci�n: " + duracion + " minutos";
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis ="Sinopsis: " + sinopsis;
	}

	public String getReparto() {
		return reparto;
	}

	public void setReparto(String reparto) {
		this.reparto = "Reparto: " + reparto;
	}

	public String getSesiones() {
		return sesiones;
	}

	public void setSesiones(String sesiones) {
		this.sesiones = "Sesiones: " + sesiones;
	}
	
	
	
	

}
