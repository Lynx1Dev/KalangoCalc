package sol.kalango.kalangocalc;
import java.text.DecimalFormat;

public class Combustivel {
	public final String nomeCombustivel;
	// Atributos
	public float taxaCartao, frete, precoCompra, precoVenda, lucroBruto;

	// Construtor
	public Combustivel(String nomeCombustivel, float taxaCartao, float frete, float precoCompra, float precoVenda) {
		this.nomeCombustivel = nomeCombustivel;
		this.taxaCartao = taxaCartao;
		this.frete = frete;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.lucroBruto = lucroBruto();
	}

	// Métodos getters
	public String getNomeCombustivel() {
		return nomeCombustivel;
	}

	public String getTaxaCartao() {
		return formatarValor(taxaCartao);
	}

	public String getFrete() {
		return formatarValor(frete);
	}

	public String getPrecoCompra() {
		return formatarValor(precoCompra);
	}

	public String getPrecoVenda() {
		return formatarValor(precoVenda);
	}

	public String getLucroBruto() {
		return formatarValor(lucroBruto);
	}

	// Métodos setters
	public void setTaxaCartao(float taxaCartao) {
		this.taxaCartao = taxaCartao;
		this.lucroBruto = lucroBruto();
	}

	public void setFrete(float frete) {
		this.frete = frete;
		this.lucroBruto = lucroBruto();
	}

	public void setPrecoCompra(float precoCompra) {
		this.precoCompra = precoCompra;
		this.lucroBruto = lucroBruto();
	}

	public void setPrecoVenda(float precoVenda) {
		this.precoVenda = precoVenda;
		this.lucroBruto = lucroBruto();
	}

	// Método para calcular o Lucro Bruto
	private float lucroBruto() {
		return precoVenda - precoCompra - frete - taxaCartao;
	}

	// Método para formatar os valores para duas casas decimais
	private String formatarValor(float valor) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(valor);
	}
}
