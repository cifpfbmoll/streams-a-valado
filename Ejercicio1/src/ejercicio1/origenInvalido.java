package ejercicio1;

import java.io.FileOutputStream;
import java.io.IOException;

public class origenInvalido extends IOException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		String mensaje = "ERROR: Ruta de fichero inválida";
		return mensaje;	
	}

	public void gestionarError() {
		java.util.Date fecha = new java.util.Date();
		String log_error = "\n"+ getMessage() + " " + Thread.currentThread().getStackTrace() + " " + fecha;
		FileOutputStream error;
		try {
			error = new FileOutputStream("errores.txt", true);
			error.write(log_error.getBytes());
			//El error se registra en errores.txt y el programa finaliza su ejecución.
			error.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
		