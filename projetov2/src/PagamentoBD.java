import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class PagamentoBD {
    private static ArrayList<Pagamento> lista = new ArrayList<Pagamento>();
    
    public static void inserir(Pagamento novoPagamento) {
        lerXml();
        Pagamento db = procurar(novoPagamento.getCodigoPagamento());
        if(db == null){
            lista.add(novoPagamento);
        }
        if(db != null){
            excluir(db);
            inserir(novoPagamento);
        }
        salvarXml();
    }

    public static void alterar(Pagamento pagamentoAlterado){
        excluir(pagamentoAlterado);
        inserir(pagamentoAlterado);
    }

    public static void excluir(Double codigo){
        lerXml();
        for(int i=0; i < lista.size(); i++){
            Pagamento cadaPagamento = lista.get(i);
            if (cadaPagamento.getCodigoPagamento() == codigo){
                lista.remove(i);
                break;
            }
        }
        salvarXml();
    }

    public static void excluir(Pagamento pagamento){
        lerXml();
        for (Pagamento cadaPagamento : lista) {
            if (cadaPagamento.getCodigoPagamento() == pagamento.getCodigoPagamento()){
                lista.remove(cadaPagamento);
                break;
            }
        }
        salvarXml();
    }

    public static ArrayList<Pagamento> listar(){
        lerXml();
        return lista;
    }
    
	public static void lerXml(){
        File arquivo = new File("src//bd//listaDePagamentos.xml");
        if (arquivo.exists()){
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("listaDePagamentos",Pagamento.class);
            lista = (ArrayList<Pagamento>) xstream.fromXML(arquivo);
        }else{
            lista = new ArrayList<Pagamento>();
        }               
    }
	
	public static void exibirXml() {
        lerXml();
        for (Pagamento pagamento : lista) {
            System.out.println(pagamento.getCodigoPagamento());
            System.out.println(pagamento.getTipoPagamento());
            System.out.println(pagamento.getValor());
            System.out.println(" ");
        }
	}

    public static void salvarXml(){
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("listaDePagamentos", Pagamento.class);
        try{
            FileWriter escritor=new FileWriter("src//bd//listaDePagamentos.xml");
            escritor.write( xstream.toXML(lista) );
            escritor.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }   
    }

   
    public static Pagamento procurar(Double codigoPagamento){
        lerXml();
        Pagamento pagamentoEncontrado = null;
        for(int i=0; i < lista.size(); i++){
            Pagamento pagamento = lista.get(i);
			if (pagamento.getCodigoPagamento() == codigoPagamento){
                pagamentoEncontrado = pagamento;
                break;
            }
        }
        return pagamentoEncontrado;
    }
}
