import java.util.Random;

public class Desconto extends Pagamento {
    TipoPagamento tipoPagamento;
    private double valorPagamento;
    private boolean flagDesconto;
    private double codigoPagamento;

    public Desconto(double valorPagamento, TipoPagamento tipoPagamento){ 
        super(valorPagamento, tipoPagamento);
    }

    public Desconto(double valorPagamento, TipoPagamento tipoPagamento, boolean flagDesconto) {
        super(valorPagamento, tipoPagamento);
        if (flagDesconto) {
			    valorPagamento = valorPagamento - (valorPagamento * 0.3D);
			    System.out.println("Desconto de 30% foi aplicado nas Compras no total");
		    }
        this.valorPagamento = valorPagamento;
        this.tipoPagamento = tipoPagamento;
        this.flagDesconto = flagDesconto;
		    gerarCodigoPag();
    }
    
    public void gerarCodigoPag(){
		  Random random = new Random();
		  Integer i = random.nextInt(1000000);
		  this.codigoPagamento = i.doubleValue();
	  }
}
