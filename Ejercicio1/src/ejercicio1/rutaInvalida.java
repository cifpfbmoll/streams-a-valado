package ejercicio1;

import java.io.IOException;

public class rutaInvalida extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		String mensaje = "ERROR: Ruta de fichero inv�lida";
		return mensaje;	
	}

	
}
		