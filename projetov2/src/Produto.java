import java.util.Random;

public class Produto{
	private String nomeProduto;
	private Double precoProduto;
	private int qtdProdutoEstoque;
	private Double codigoProduto;

	public Produto() {}
	
	public Produto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Produto(String nomeProduto, Double precoProduto, int qtdProdutoEstoque) {
		this.nomeProduto = nomeProduto;
		this.precoProduto = precoProduto;
		this.qtdProdutoEstoque = qtdProdutoEstoque;
		Random random = new Random();
		Integer i = random.nextInt(1000000);
		this.codigoProduto = i.doubleValue();
	}

	public Produto(String nomeProduto, String precoProduto, int qtdProdutoEstoque) {
		double precoProdutoDouble = Double.parseDouble(precoProduto);
		this.nomeProduto = nomeProduto;
		this.precoProduto = precoProdutoDouble;
		this.qtdProdutoEstoque = qtdProdutoEstoque;
		Random random = new Random();
		Integer i = random.nextInt(1000000);
		this.codigoProduto = i.doubleValue();
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getPrecoProduto() {
		return precoProduto;
	}

	public void setPrecoProduto(Double precoProduto) {
		this.precoProduto = precoProduto;
	}

	public int getQtdProdutoEstoque() {
		return qtdProdutoEstoque;
	}

	public void setQtdProdutoEstoque(int qtdProdutoEstoque) {
		this.qtdProdutoEstoque = qtdProdutoEstoque;
	}

	public Double getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Double codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

}
