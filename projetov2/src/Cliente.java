import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cliente{
	private String usuario;
	private String senha;
	private int codigoCliente;
	private ArrayList<Produto> produto = new ArrayList<Produto>();
	private Double valorCompra;
	
	public Cliente() {	}

	public void addCompraCarrinho(Produto produto) {
		Produto db;
		db = ProdutoBD.procurar(produto.getNomeProduto());
		if (db != null && db.getQtdProdutoEstoque() > 0) {
			this.produto.add(db);
			//this.comprarProduto();
		}
		else{
			System.out.println("Acabou o estoque do produto: "+produto.getNomeProduto());
		}	
	}

	public void addProdutos(Produto produto){
		Scanner in = new Scanner(System.in); 
		exibirProdutosLoja();
		System.out.println("Escolha um produto para adicionar ao carrinho");
		String key = in.nextLine();
		Produto db = ProdutoBD.procurar(key);
		if (db != null && db.getQtdProdutoEstoque() != 0){
			produto.setNomeProduto(db.getNomeProduto());
			produto.setCodigoProduto(db.getCodigoProduto());
			produto.setPrecoProduto(db.getPrecoProduto());
			produto.setQtdProdutoEstoque(db.getQtdProdutoEstoque());
			//produto = db;
		} 
		else{
			System.out.println("Acabou o estoque do produto: "+produto.getNomeProduto());
		}
	}

	public void addProdutos(ArrayList<Produto> produtos){
		for (Produto produto : produtos) {
			addProdutos(produto);
		}
	}

	public void comprarProduto(Produto produto){
		Produto db;
		db = ProdutoBD.procurar(produto.getNomeProduto());
		
		if (db != null && db.getQtdProdutoEstoque() != 0) {
			int menosProduto = db.getQtdProdutoEstoque() - 1;
			db.setQtdProdutoEstoque(menosProduto);
			ProdutoBD.alterar(db);
		}
		else{
			System.out.println("Acabou o estoque do produto: "+produto.getNomeProduto());
		}
	}

	public void comprarProduto(){
		Double temp = 0D;
		ArrayList<Produto> produtos = this.produto;
		for (Produto prod : produtos) {
			this.comprarProduto(prod);
			temp += prod.getPrecoProduto();
		}
		this.valorCompra = temp;
		this.pagamento();
	}
	
	public void comprarProduto(boolean flagDesconto){
		Double temp = 0D;
		ArrayList<Produto> produtos = this.produto;
		for (Produto prod : produtos) {
			this.comprarProduto(prod);
			temp += prod.getPrecoProduto();
		}
		this.pagamento(flagDesconto);
	}

	public Double valorTotal(ArrayList<Produto> produtos){
		Double vTotal = 0D;
		for (Produto produto : produtos) {
			vTotal += produto.getPrecoProduto();
		}
		return vTotal;
	}

	public void pagamento(){
		TipoPagamento tipoPagamento;
		Pagamento novoPagamento;
		Double temp = 0D;
		temp = valorTotal(this.produto);
		Scanner in = new Scanner(System.in); 
		System.out.println("VALOR TOTAL: R$"+Double.toString(temp));
		System.out.println("Digite o Metodo de Pagamento :");
		System.out.println("1 - CREDITO\n2 - DEBITO\n3 - BOLETO\nQUALQUER TECLA - COMPRA CANCELADA");
		String key = in.nextLine();
		switch (key) {
			case "1":
				tipoPagamento = TipoPagamento.CREDITO;
				novoPagamento = new Pagamento(temp, tipoPagamento);
				PagamentoBD.inserir(novoPagamento);
				System.out.println("Compra Finalizada !!!");
				break;
				
			case "2":
				tipoPagamento = TipoPagamento.DEBITO;
				novoPagamento = new Pagamento(temp, tipoPagamento);
				PagamentoBD.inserir(novoPagamento);
				System.out.println("Compra Finalizada !!!");
				break;
			case "3":
				tipoPagamento = TipoPagamento.BOLETO;
				novoPagamento = new Pagamento(temp, tipoPagamento);
				PagamentoBD.inserir(novoPagamento);
				System.out.println("Compra Finalizada !!!");
				break;
		
			default:
				System.err.println("Compra Cancelada !!!");
				break;
		}
	}
	
	public void pagamento(boolean flagDesconto) {
		Double temp = 0D;
		temp = valorTotal(this.produto);
		TipoPagamento tipoPagamento;
		Desconto novoPagamento;
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Digite o Metodo de Pagamento :");
		System.out.println("1 - CREDITO\n2 - DEBITO\n3 - BOLETO\nQUALQUER TECLA - COMPRA CANCELADA");
		int key = scanner.nextInt();
		switch (key) {
			case 1:
				tipoPagamento = TipoPagamento.CREDITO;
				novoPagamento = new Desconto(temp, tipoPagamento, flagDesconto);
				PagamentoBD.inserir(novoPagamento);
				System.out.println("Compra Finalizada !!!");
				break;
			case 2:
				tipoPagamento = TipoPagamento.DEBITO;
				novoPagamento = new Desconto(temp, tipoPagamento, flagDesconto);
				PagamentoBD.inserir(novoPagamento);
				System.out.println("Compra Finalizada !!!");
				break;
			case 3:
				tipoPagamento = TipoPagamento.BOLETO;
				novoPagamento = new Desconto(temp, tipoPagamento, flagDesconto);
				PagamentoBD.inserir(novoPagamento);
				System.out.println("Compra Finalizada !!!");
				break;		
		
			default:
				System.err.println("Compra Cancelada !!!");
				break;
		}
	}

	
	public void qtdDeProdutos(ArrayList<Produto> produtos) {
		int i = 0;
		for (Produto produto : produtos) {
			i++;
		}
		System.out.println("Qtd de Produtos: "+i);
	}

	public void verCarrinhoProdutos() {
		ArrayList<Produto> produtos = this.produto;
		for (Produto produto : produtos) {
			System.out.println(produto.getNomeProduto());
		}
	}

	public void verCarrinhoProdutos(ArrayList<Produto> produtos) {
		for (Produto produto : produtos) {
			System.out.println(produto.getNomeProduto());
		}
	}

	public void exibirProdutosLoja(){
		ProdutoBD.exibirXml();
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigo(int codigoCliente) {
		this.codigoCliente = codigoCliente;
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
}