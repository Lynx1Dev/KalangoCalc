package sol.kalango.kalangocalc;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppNormalizadorLucrosController {
	// normalizador-lucros.fxml

	// variáveis - começo
	@FXML
	public ChoiceBox<String> choiceBox1NormalizadorLucros, choiceBox2NormalizadorLucros, choiceBox3NormalizadorLucros,
			choiceBox4NormalizadorLucros, choiceBox5NormalizadorLucros;
	@FXML
	public Label combustivel1NormalizadorLucros, combustivel2NormalizadorLucros, combustivel3NormalizadorLucros,
			combustivel4NormalizadorLucros, combustivel5NormalizadorLucros;
	@FXML
	public Button buttonNormalizadorLucrosLucrar, buttonNormalizadorLucrosCancelar;

	private Map<String, Combustivel> combustiveisPorNome;
	private Combustivel etanol, gasolinaComum, gasolinaAditivada, dieselS500, dieselS10;

	private AppController mainController; // referência ao controlador principal AppController.class
	// variáveis - fim

	public void setMainController(AppController mainController) { // // Método para definir a referência do controlador principal
		this.mainController = mainController;
	}

	private void atribuirValoresCena() {
		combustivel1NormalizadorLucros.setText("Etanol");
		combustivel2NormalizadorLucros.setText("Gasolina Comum");
		combustivel3NormalizadorLucros.setText("Gasolina Aditivada");
		combustivel4NormalizadorLucros.setText("Diesel S500 Ad");
		combustivel5NormalizadorLucros.setText("Diesel S10 Ad");

		buttonNormalizadorLucrosLucrar.setText("Lucrar!");
		buttonNormalizadorLucrosCancelar.setText("Cancelar");

		choiceBox1NormalizadorLucros.setItems(FXCollections.observableArrayList("0", "R$ 0,30", "R$ 0,35", "R$ 0,40", "R$ 0,45"));
		choiceBox2NormalizadorLucros.setItems(FXCollections.observableArrayList("0", "R$ 0,30", "R$ 0,35", "R$ 0,40", "R$ 0,45"));
		choiceBox3NormalizadorLucros.setItems(FXCollections.observableArrayList("0", "R$ 0,30", "R$ 0,35", "R$ 0,40", "R$ 0,45"));
		choiceBox4NormalizadorLucros.setItems(FXCollections.observableArrayList("0", "R$ 0,30", "R$ 0,35", "R$ 0,40", "R$ 0,45"));
		choiceBox5NormalizadorLucros.setItems(FXCollections.observableArrayList("0", "R$ 0,30", "R$ 0,35", "R$ 0,40", "R$ 0,45"));
	}

	public void inicializarDados() {
		List<Combustivel> combustiveis = StorageManager.loadCombustiveis();
		this.combustiveisPorNome = new HashMap<>();
		for (Combustivel combustivel : combustiveis) {
			this.combustiveisPorNome.put(combustivel.getNomeCombustivel(), combustivel);
		}

		inicializarCombustiveis();
	}

	private void inicializarCombustiveis() {
		etanol = combustiveisPorNome.get("Etanol");
		gasolinaComum = combustiveisPorNome.get("Gasolina Comum");
		gasolinaAditivada = combustiveisPorNome.get("Gasolina Aditivada");
		dieselS500 = combustiveisPorNome.get("Diesel S500 Ad");
		dieselS10 = combustiveisPorNome.get("Diesel S10 Ad");
	}

	@FXML
	public void buttonNormalizadorLucrosLucrarHandle() {
		float lucro1Normalizar, lucro2Normalizar, lucro3Normalizar, lucro4Normalizar, lucro5Normalizar,
				preco1Normalizar, preco2Normalizar, preco3Normalizar, preco4Normalizar, preco5Normalizar,
				escolhaLucro1, escolhaLucro2, escolhaLucro3, escolhaLucro4, escolhaLucro5;

		Alert alertaNormalizar;

		escolhaLucro1 = Float.parseFloat(choiceBox1NormalizadorLucros.getValue().replace(",", ".").replace("R$ ", ""));
		escolhaLucro2 = Float.parseFloat(choiceBox2NormalizadorLucros.getValue().replace(",", ".").replace("R$ ", ""));
		escolhaLucro3 = Float.parseFloat(choiceBox3NormalizadorLucros.getValue().replace(",", ".").replace("R$ ", ""));
		escolhaLucro4 = Float.parseFloat(choiceBox4NormalizadorLucros.getValue().replace(",", ".").replace("R$ ", ""));
		escolhaLucro5 = Float.parseFloat(choiceBox5NormalizadorLucros.getValue().replace(",", ".").replace("R$ ", ""));

		lucro1Normalizar = Float.parseFloat(etanol.getLucroBruto().replace(",", "."));
		lucro2Normalizar = Float.parseFloat(gasolinaComum.getLucroBruto().replace(",", "."));
		lucro3Normalizar = Float.parseFloat(gasolinaAditivada.getLucroBruto().replace(",", "."));
		lucro4Normalizar = Float.parseFloat(dieselS500.getLucroBruto().replace(",", "."));
		lucro5Normalizar = Float.parseFloat(dieselS10.getLucroBruto().replace(",", "."));

		preco1Normalizar = Float.parseFloat(etanol.getPrecoVenda().replace(",", "."));
		preco2Normalizar = Float.parseFloat(gasolinaComum.getPrecoVenda().replace(",", "."));
		preco3Normalizar = Float.parseFloat(gasolinaAditivada.getPrecoVenda().replace(",", "."));
		preco4Normalizar = Float.parseFloat(dieselS500.getPrecoVenda().replace(",", "."));
		preco5Normalizar = Float.parseFloat(dieselS10.getPrecoVenda().replace(",", "."));

		if (lucro1Normalizar != escolhaLucro1 && escolhaLucro1 != 0) {
			etanol.setPrecoVenda(
					preco1Normalizar + escolhaLucro1 - lucro1Normalizar
			);
		}
		if (lucro2Normalizar != escolhaLucro2 && escolhaLucro2 != 0) {
			gasolinaComum.setPrecoVenda(
					preco2Normalizar + escolhaLucro2 - lucro2Normalizar
			);
		}
		if (lucro3Normalizar != escolhaLucro3 && escolhaLucro3 != 0) {
			gasolinaAditivada.setPrecoVenda(
					preco3Normalizar + escolhaLucro3 - lucro3Normalizar
			);
		}
		if (lucro4Normalizar != escolhaLucro4 && escolhaLucro4 != 0) {
			dieselS500.setPrecoVenda(
					preco4Normalizar + escolhaLucro4 - lucro4Normalizar
			);
		}
		if (lucro5Normalizar != escolhaLucro5 && escolhaLucro5 != 0) {
			dieselS10.setPrecoVenda(
					preco5Normalizar + escolhaLucro5 - lucro5Normalizar
			);
		}

		if (mainController != null) {
			StorageManager.saveCombustiveis(new ArrayList<>(combustiveisPorNome.values()));
			mainController.inicializarDados();
			mainController.atualizarRotulos();
		}

		alertaNormalizar = new Alert(Alert.AlertType.INFORMATION);
		alertaNormalizar.setTitle("Normalizar Lucros");
		alertaNormalizar.setContentText("O lucro dos combustíveis selecionados foram atualizados.");
		alertaNormalizar.setHeaderText("Lucros normalizados!");
		alertaNormalizar.showAndWait();

		Stage stage = (Stage) buttonNormalizadorLucrosLucrar.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void buttonNormalizadorLucrosCancelarHandle(ActionEvent e) {
		final Node source = (Node) e.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void initialize() {
		atribuirValoresCena();
		inicializarDados();
	}
}