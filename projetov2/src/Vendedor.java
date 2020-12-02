import java.util.ArrayList;
import java.util.Random;

public class Vendedor {
	private String usuario;
	private String senha;
	private int codigoVendedor;
	private ArrayList<Produto> produtos;

	public Vendedor() {
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getCodigoVendedor() {
		return this.codigoVendedor;
	}

	public void setCodigoVendedor(int codigoVendedor) {
		this.codigoVendedor = codigoVendedor;
	}

	public void addProduto(Produto produto) {
		ProdutoBD.inserir(produto);
	}

	public void removerProduto(Produto produto) {
		ProdutoBD.excluir(produto);
	}

	/*
	public void cadastrarProduto(Produto produto, String nomeProduto, Double precoProduto) {
		Random random = new Random();
		Double codigoProduto = random.nextDouble() * 100D;
		produto.setNomeProduto(nomeProduto);
		produto.setPrecoProduto(precoProduto);
		produto.setCodigoProduto(codigoProduto);
		addProduto(produto);
	}*/
}
