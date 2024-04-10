package sol.kalango.kalangocalc;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppController {
	@FXML
	public Label combustivel1, combustivel2, combustivel3, combustivel4, combustivel5,
			preco1, preco2, preco3, preco4, preco5,
			combustivelEditor;
	@FXML
	public RadioButton lucro1, lucro2, lucro3, lucro4, lucro5;
	@FXML
	public Pane editor;
	@FXML
	public CheckBox valorReal1, valorReal2, valorReal3,valorReal4;

	public Button botaoAlterar, normalizarLucro;
	public TextField precoVenda, precoCompra, frete, taxaCartao;

	public List<Combustivel> combustiveis;
    public Map<String, Combustivel> combustiveisPorNome;

	private Combustivel etanol, gasolinaComum, gasolinaAditivada, dieselS500, dieselS10;

	public void inicializarDados() {
		this.combustiveis = StorageManager.loadCombustiveis();
		this.combustiveisPorNome = new HashMap<>();
		for (Combustivel combustivel : this.combustiveis) {
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
	public void handleLucro1OnAction1() {
		boolean isSelected = lucro1.isSelected();

		lucro2.setSelected(false);
		lucro3.setSelected(false);
		lucro4.setSelected(false);
		lucro5.setSelected(false);

		editor.setVisible(isSelected);

		combustivelEditor.setText("Etanol");
	}

	@FXML
	public void handleLucro1OnAction2() {
		boolean isSelected = lucro2.isSelected();

		lucro1.setSelected(false);
		lucro3.setSelected(false);
		lucro4.setSelected(false);
		lucro5.setSelected(false);

		editor.setVisible(isSelected);

		combustivelEditor.setText("Gasolina Comum");
	}

	@FXML
	public void handleLucro1OnAction3() {
		boolean isSelected = lucro3.isSelected();

		lucro1.setSelected(false);
		lucro2.setSelected(false);
		lucro4.setSelected(false);
		lucro5.setSelected(false);

		editor.setVisible(isSelected);

		combustivelEditor.setText("Gasolina Aditivada");
	}

	@FXML
	public void handleLucro1OnAction4() {
		boolean isSelected = lucro4.isSelected();

		lucro1.setSelected(false);
		lucro2.setSelected(false);
		lucro3.setSelected(false);
		lucro5.setSelected(false);

		editor.setVisible(isSelected);

		combustivelEditor.setText("Diesel S500 Ad");
	}

	@FXML
	public void handleLucro1OnAction5() {
		boolean isSelected = lucro5.isSelected();
		
		lucro1.setSelected(false);
		lucro2.setSelected(false);
		lucro3.setSelected(false);
		lucro4.setSelected(false);

		editor.setVisible(isSelected);

		combustivelEditor.setText("Diesel S10 Ad");
	}

	// botão de alterar
	@FXML
	public void handleBotaoAlterarAction() {
		Combustivel combustivelSelecionado = null;
	
		if (lucro1.isSelected()) {
			combustivelSelecionado = combustiveisPorNome.get("Etanol");
		} else if (lucro2.isSelected()) {
			combustivelSelecionado = combustiveisPorNome.get("Gasolina Comum");
		} else if (lucro3.isSelected()) {
			combustivelSelecionado = combustiveisPorNome.get("Gasolina Aditivada");
		} else if (lucro4.isSelected()) {
			combustivelSelecionado = combustiveisPorNome.get("Diesel S500 Ad");
		} else if (lucro5.isSelected()) {
			combustivelSelecionado = combustiveisPorNome.get("Diesel S10 Ad");
		}
	
		if (combustivelSelecionado != null) {
			if (!precoVenda.getText().isEmpty()) {
				combustivelSelecionado.setPrecoVenda(Float.parseFloat(precoVenda.getText().replace(",", ".")));
			}
			if (!precoCompra.getText().isEmpty()) {
				combustivelSelecionado.setPrecoCompra(Float.parseFloat(precoCompra.getText().replace(",", ".")));
			}
			if (!frete.getText().isEmpty()) {
				combustivelSelecionado.setFrete(Float.parseFloat(frete.getText().replace(",", ".")));
			}
			if (!taxaCartao.getText().isEmpty()) {
				combustivelSelecionado.setTaxaCartao(Float.parseFloat(taxaCartao.getText().replace(",", ".")));
			}
		}

		// Atualiza os rótulos
		atualizarRotulos();

		// Salva as alterações no arquivo JSON
		StorageManager.saveCombustiveis(new ArrayList<>(combustiveisPorNome.values()));
	}

	@FXML
	public void handleValorRealOnAction1() {
		boolean valorReal1Selected = valorReal1.isSelected();

		if(valorReal1Selected) {
			if(lucro1.isSelected()) {
				precoVenda.setText(String.valueOf(etanol.getPrecoVenda()));
			} else {
				if(lucro2.isSelected()) {
					precoVenda.setText(String.valueOf(gasolinaComum.getPrecoVenda()));
				} else {
					if(lucro3.isSelected()) {
						precoVenda.setText(String.valueOf(gasolinaAditivada.getPrecoVenda()));
					} else {
						if(lucro4.isSelected()) {
							precoVenda.setText(String.valueOf(dieselS500.getPrecoVenda()));
						} else {
							if(lucro5.isSelected()) {
								precoVenda.setText(String.valueOf(dieselS10.getPrecoVenda()));
							}
						}
					}
				}
			}
		} else {
			precoVenda.setText("");
		}
	}

	@FXML
	public void handleValorRealOnAction2() {
		boolean valorReal2Selected = valorReal2.isSelected();

		if(valorReal2Selected) {
			if(lucro1.isSelected()) {
				precoCompra.setText(String.valueOf(etanol.getPrecoCompra()));
			} else {
				if(lucro2.isSelected()) {
					precoCompra.setText(String.valueOf(gasolinaComum.getPrecoCompra()));
				} else {
					if(lucro3.isSelected()) {
						precoCompra.setText(String.valueOf(gasolinaAditivada.getPrecoCompra()));
					} else {
						if(lucro4.isSelected()) {
							precoCompra.setText(String.valueOf(dieselS500.getPrecoCompra()));
						} else {
							if(lucro5.isSelected()) {
								precoCompra.setText(String.valueOf(dieselS10.getPrecoCompra()));
							}
						}
					}
				}
			}
		} else {
			precoCompra.setText("");
		}
	}

	@FXML
	public void handleValorRealOnAction3() {
		boolean valorReal3Selected = valorReal3.isSelected();

		if(valorReal3Selected) {
			if(lucro1.isSelected()) {
				frete.setText(String.valueOf(etanol.getFrete()));
			} else {
				if(lucro2.isSelected()) {
					frete.setText(String.valueOf(gasolinaComum.getFrete()));
				} else {
					if(lucro3.isSelected()) {
						frete.setText(String.valueOf(gasolinaAditivada.getFrete()));
					} else {
						if(lucro4.isSelected()) {
							frete.setText(String.valueOf(dieselS500.getFrete()));
						} else {
							if(lucro5.isSelected()) {
								frete.setText(String.valueOf(dieselS500.getFrete()));
							}
						}
					}
				}
			}
		} else {
			frete.setText("");
		}
	}

	@FXML
	public void handleValorRealOnAction4() {
		boolean valorReal4Selected = valorReal4.isSelected();

		if(valorReal4Selected) {
			if(lucro1.isSelected()) {
				taxaCartao.setText(String.valueOf(etanol.getTaxaCartao()));
			} else {
				if(lucro2.isSelected()) {
					taxaCartao.setText(String.valueOf(gasolinaComum.getTaxaCartao()));
				} else {
					if(lucro3.isSelected()) {
						taxaCartao.setText(String.valueOf(gasolinaAditivada.getTaxaCartao()));
					} else {
						if(lucro4.isSelected()) {
							taxaCartao.setText(String.valueOf(dieselS500.getTaxaCartao()));
						} else {
							if(lucro5.isSelected()) {
								taxaCartao.setText(String.valueOf(dieselS10.getTaxaCartao()));
							}
						}
					}
				}
			}
		} else {
			taxaCartao.setText("");
		}
	}

	public void atualizarRotulos() {
		if (etanol != null && gasolinaComum != null && gasolinaAditivada != null && dieselS500 != null && dieselS10 != null) {
			combustivel1.setText(etanol.getNomeCombustivel());
			combustivel2.setText(gasolinaComum.getNomeCombustivel());
			combustivel3.setText(gasolinaAditivada.getNomeCombustivel());
			combustivel4.setText(dieselS500.getNomeCombustivel());
			combustivel5.setText(dieselS10.getNomeCombustivel());

			preco1.setText("R$ " + etanol.getPrecoVenda());
			preco2.setText("R$ " + gasolinaComum.getPrecoVenda());
			preco3.setText("R$ " + gasolinaAditivada.getPrecoVenda());
			preco4.setText("R$ " + dieselS500.getPrecoVenda());
			preco5.setText("R$ " + dieselS10.getPrecoVenda());

			lucro1.setText("R$ " + etanol.getLucroBruto());
			lucro2.setText("R$ " + gasolinaComum.getLucroBruto());
			lucro3.setText("R$ " + gasolinaAditivada.getLucroBruto());
			lucro4.setText("R$ " + dieselS500.getLucroBruto());
			lucro5.setText("R$ " + dieselS10.getLucroBruto());
		}
	}

	public void handleNormalizarLucroAction() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("normalizador-lucros.fxml"));
			final Scene scene = new Scene(fxmlLoader.load(), 300, 300);
			final Stage normalizadorLucros = new Stage();
			normalizadorLucros.setTitle("Normalizador de Lucros");
			normalizadorLucros.setScene(scene);

			// Passa a referência do controlador principal
			AppNormalizadorLucrosController normalizadorLucrosController = fxmlLoader.getController();
			normalizadorLucrosController.setMainController(this);

			normalizadorLucros.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void initialize() {
		inicializarDados();
		atualizarRotulos();
	}
}
