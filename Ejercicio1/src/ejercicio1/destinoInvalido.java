package ejercicio1;

import java.io.IOException;

public class destinoInvalido extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	public String getMessage() {
		String mensaje = "ERROR: No ha introducido una ruta. Por favor, vuelva a intentarlo.";
		return mensaje;	
	}

}
