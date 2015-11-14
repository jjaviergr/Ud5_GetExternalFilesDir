package com.pmdm.ud5_getexternalfilesdir;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import static com.pmdm.ud5_getexternalfilesdir.R.*;

public class GetExternalFilesDir extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(layout.activity_get_external_files_dir);
	}
	public void createExternalStoragePrivateFile(View view) {
		// crea un objeto file apuntando a la ruta  �MemoriaSD�/Android/data/<nombre_paquete>/files/Pictures
		File file = new File(
				getExternalFilesDir(Environment.DIRECTORY_PICTURES),
				"DemoFile.jpg");
		try {
			// flujo de entrada a la imagen de un archivo de recursos
			InputStream is = getResources().openRawResource(drawable.balloons);
			// flujo de salida para almacenar la imagen en la SD
			OutputStream os = new FileOutputStream(file);
			// lee el primer byte
			int b = is.read();
			// mientras hay bytes por leer
			while (b != -1) {
				// escribe en el flujo de salida el byte le�do
				os.write(b);
				// lee siguiente byte
				b = is.read();			}
			// garantiza la escritura de todos os bytes
			os.flush();
			// cierra flujos
			is.close();
			os.close();
		} catch (IOException e) {
			// en caso de error
			Toast.makeText(getApplicationContext(),
					"Error escribiendo " + file + ": " + e, Toast.LENGTH_LONG)
					.show();
		}
	}
	// Obtiene la ruta del fichero externo y lo borra si existe
	public void deleteExternalStoragePrivateFile(View view) {
		File file = new File(
				getExternalFilesDir(Environment.DIRECTORY_PICTURES),
				"DemoFile.jpg");
		if (file != null) {
			file.delete();
		}
	}
	// Comrpueba si el fichero DemoFile.jpg est� en la SD
	public void validateExternalStoragePrivateFile(View view) {
		File file = new File(
				getExternalFilesDir(Environment.DIRECTORY_PICTURES),
				"DemoFile.jpg");
		if (file.exists()) {
			Toast.makeText(getApplicationContext(),
					"El fichero se encuentra en el almacenamiento externo",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getApplicationContext(),
					"El fichero no se encuentra en el almacenamiento externo",
					Toast.LENGTH_LONG).show();
		}
	}
}
