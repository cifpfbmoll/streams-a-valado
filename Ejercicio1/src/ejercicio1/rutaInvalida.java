package ejercicio1;

import java.io.IOException;

public class rutaInvalida extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		String mensaje = "ERROR: Ruta de fichero inválida";
		return mensaje;	
	}

	
}
		