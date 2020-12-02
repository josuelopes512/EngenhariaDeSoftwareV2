import java.util.ArrayList;
import java.util.Scanner;


public class Principal {
	public static void vendedor(){
		Vendedor vendedor = new Vendedor();
		//Cadastro.cadastrar(vendedor);
		Login.logar(vendedor);
		Produto produto = new Produto("mouse", "30", 200); //STRING valorPreco
		//Produto produto = new Produto("mouse", 30D, 200); //DOUBLE valorPreco
		vendedor.addProduto(produto);
		//vendedor.removerProduto(produto);
		ProdutoBD.exibirXml();
	}

	public static void cliente(){
		Scanner in = new Scanner(System.in);
		Cliente cliente = new Cliente();
		//Cadastro.cadastrar(cliente);
		
		Login.logar(cliente);
		cliente.exibirProdutosLoja();
		System.out.print("Clique enter para continuar...");
		in.nextLine();
		Produto produto = new Produto();
		produto.setNomeProduto("nomeProduto");
		cliente.addProdutos(produto);
		cliente.addCompraCarrinho(produto);
		cliente.comprarProduto(true); //Desconto
		//cliente.comprarProduto(true);
	}

	public static void main(String[] args) {
		//vendedor();
		cliente();
	}

}
