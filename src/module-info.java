module JavaFX {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml, javafx.controls;
	opens controlador to javafx.fxml;
}
