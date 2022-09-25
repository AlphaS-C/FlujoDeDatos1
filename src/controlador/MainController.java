package controlador;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

public class MainController {

	Creador creador = new Creador();
	
	public void createFile() {
		if (creador.crearArchivo(getNombreArchivo())) {
			System.out.println("ha creao");
		} else System.out.println("no ha creao");
	}
	
	public void createFolder() {
		if (creador.crearCarpeta(getNombreCarpeta())) {
			System.out.println("ha creao");
		} else System.out.println("no ha creao");
	}
	
	public void printFolder() {
		System.out.println(getNombreCarpeta());
		creador.imprimirContenidos(getNombreCarpeta());
	}
	
	public void createProperties() {
		if (creador.crearArchivo("configuration.properties")) {
			System.out.println("Archivo de propiedades creado con exito");
		} else System.out.println("Archivo de propiedades ya existe");
	}
	
	
	@FXML TextField pNombre; 
	@FXML TextField pValor;
	@FXML Text tPropiedades;
	public void saveProperties() throws IOException{
		try {
			creador.guardarPropiedades(pNombre.getText(), pValor.getText());
			}
		 catch (NullPointerException e){
			 e.printStackTrace();
			 tPropiedades.setText("Por favor ingrese algun valor");
		 	}
		
	}
	
	public void printProperties() throws IOException {
		tPropiedades.setText( creador.imprimirPropiedades() );
	}
	
	
	@FXML TextField nombreArchivo;
	public String getNombreArchivo() {
		return nombreArchivo.getText();
	}

	public void selectFile () {
		Stage stage = (Stage) nombreArchivo.getScene().getWindow();
		File file = creador.seleccionarArchivo(stage);
		nombreArchivo.setText(file.getAbsolutePath());
	}
	
	
	@FXML TextField nombreCarpeta;
	public String getNombreCarpeta() {
		return nombreCarpeta.getText();
	}
	
}
