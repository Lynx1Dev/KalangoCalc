package sol.kalango.kalangocalc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("principal.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 630, 230);
		stage.setTitle("Tabela de Preços");
		stage.setScene(scene);
		stage.show();

		AppController controller = fxmlLoader.getController();
		controller.inicializarDados();
	}

	public static void main(String[] args) {
		StorageManager.loadCombustiveis();

		launch();
	}
}