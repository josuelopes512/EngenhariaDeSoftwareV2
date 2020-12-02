import java.util.ArrayList;
import java.util.Random;

public class Pagamento{
	private TipoPagamento tipoPagamento;
	private double valorPagamento;
	private double codigoPagamento;
	private ArrayList<Produto> produto;
	private boolean flagDesconto;

	public Pagamento() { }

	public Pagamento(double valorPagamento, TipoPagamento tipoPagamento) {
		this.valorPagamento = valorPagamento;
		this.tipoPagamento = tipoPagamento;
		gerarCodigoPag();
	}

	public void gerarCodigoPag(){
		Random random = new Random();
		Integer i = random.nextInt(1000000);
		this.codigoPagamento = i.doubleValue();
	}

	public void exibirInfoPagamento(){
		PagamentoBD.exibirXml();
	}

	public double getCodigoPagamento() {
		return this.codigoPagamento;
	}

	public double getValor() {
		return this.valorPagamento;
	}

	public void setValor(double valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
}
