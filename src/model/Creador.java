package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Creador {

	
	public boolean crearArchivo(String ruta) {
		File file = new File(ruta);
		try {
			if (file.createNewFile()) return true;
			else return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean crearCarpeta(String ruta) {
		
		File file = new File(ruta); 
		if (file.mkdir()) return true;
		else return false;
	}

	
	public void imprimirContenidos (String ruta) {
		File file = new File(ruta); 
		imprimirContenidosR(file, 0, 1);
	}
	
	
	public void imprimirContenidosR (File file, int i, int nivel) {
		if (file.isDirectory()) {
			File[] listaArchivos = file.listFiles();
			if (i < listaArchivos.length) {
				if (listaArchivos[i].isDirectory()) {
					System.out.println(getNivel(nivel, "d") + listaArchivos[i].getName());
					imprimirContenidosR(listaArchivos[i], 0, nivel + 1); 
				} else 	{System.out.println(getNivel(nivel, "f") + listaArchivos[i].getName());}
					imprimirContenidosR (file, i + 1, nivel);  
			}
		}
	}
	
	public String getNivel(int nivel, String tipo) {
		String nivelS = "";
		if (nivel <= 0) {
			return nivelS;
		} else {
		for (int i = 0; i < nivel; i++) 
		{
			nivelS = " " + nivelS;
		}
		if (tipo == "d") {
			return nivelS + "↳";
		} else  return nivelS + "-";

		}
	}
	
	public File seleccionarArchivo (Stage stage) {
		FileChooser chooser = new FileChooser();
		File file = chooser.showOpenDialog(stage);
		return file;
	}
	
	public void guardarPropiedades(String nombre, String valor) throws IOException {
		FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        try {
            Properties configProperty = new Properties();

            File file = new File("configuration.properties");
            fileIn = new FileInputStream(file);
            configProperty.load(fileIn);
            configProperty.setProperty(nombre, valor);
            fileOut = new FileOutputStream(file);
            configProperty.store(fileOut, "null");

        } catch (Exception ex) {
           ex.printStackTrace();
        } finally {

            try {
                fileOut.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

	}
	
	
	public String imprimirPropiedades() throws IOException {
		Properties propiedad = getProperties();
		String propiedades = "Propiedades";
		Enumeration em = propiedad.keys();

		 while(em.hasMoreElements()){
			  String str = (String)em.nextElement();
			  propiedades +=  (str + ": " + propiedad.get(str) + "\n");
			  }
		 

		return propiedades;
	}
	
	
	public Properties getProperties() throws IOException {
		
		FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream("configuration.properties");
	         prop = new Properties();
	         prop.load(fis);
				System.out.println("leyendo propiedades");
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
		return prop;
	}
	
}
