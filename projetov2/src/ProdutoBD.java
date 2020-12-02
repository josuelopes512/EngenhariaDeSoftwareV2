import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ProdutoBD {
	private static ArrayList<Produto> lista = new ArrayList<Produto>();

    public static void inserir(Produto novoProduto) {
        lerXml();
        Produto db = procurar(novoProduto.getNomeProduto());
        if(db == null){
            lista.add(novoProduto);
        }
        if(db != null){
            excluir(db);
            inserir(novoProduto);
        }
        salvarXml();
    }

    public static void alterar(Produto produtoAlterado){
        excluir(produtoAlterado);
        inserir(produtoAlterado);
    }

    public static void excluir(Double codigo){
        lerXml();
        for(int i=0; i < lista.size(); i++){
            Produto cadaProduto = lista.get(i);
            if (cadaProduto.getCodigoProduto() == codigo){
                lista.remove(i);
                break;
            }
        }
        salvarXml();
    }
    
    public static void excluir(String nomeProduto){
        lerXml();
        for(int i=0; i < lista.size(); i++){
            Produto cadaProduto = lista.get(i);
            if (cadaProduto.getNomeProduto().equals(nomeProduto)){
                lista.remove(i);
                break;
            }
        }
        salvarXml();
    }

    public static void excluir(Produto produto){
        lerXml();
        for (Produto cadaProduto : lista) {
            if (cadaProduto.getNomeProduto().equals(produto.getNomeProduto())){
                lista.remove(cadaProduto);
                break;
            }
        }
        salvarXml();
    }

    public static ArrayList<Produto> listar(){
        lerXml();
        return lista;
    }
    
	public static void lerXml(){
        File arquivo = new File("src//bd//listaDeProdutos.xml");
        if (arquivo.exists()){
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("listaDeProdutos",Produto.class);
            lista = (ArrayList<Produto>) xstream.fromXML(arquivo);
        }else{
            lista = new ArrayList<Produto>();
            
        }               
    }
	
	public static void exibirXml() {
        lerXml();
        for (int i = 0; i < lista.size(); i++) {
            Produto cl = lista.get(i);
            System.out.println("NOME DO PRODUTO: "+cl.getNomeProduto()+"\n PRECO: R$"+cl.getPrecoProduto()+"\n QTD EM ESTOQUE: "+cl.getQtdProdutoEstoque());
            System.out.println("");
        }
	}

    public static void salvarXml(){
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("listaDeProdutos", Produto.class);
        try{
            FileWriter escritor=new FileWriter("src//bd//listaDeProdutos.xml");
            escritor.write( xstream.toXML(lista) );
            escritor.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }   
    }

    public static Produto procurar(String nomeProduto, Double codigoProduto ){
        lerXml();
        Produto produtoEncontrado = null;
        for(int i=0; i < lista.size(); i++){
			Produto produto = lista.get(i);
			if (produto.getCodigoProduto().equals(codigoProduto) && produto.getNomeProduto().equals(nomeProduto)){
                produtoEncontrado = produto;
                break;
            }
        }
        return produtoEncontrado;
    }

    public static Produto procurar(Double codigoProduto){
        lerXml();
        Produto produtoEncontrado = null;
        for(int i=0; i < lista.size(); i++){
			Produto produto = lista.get(i);
			if (produto.getCodigoProduto().equals(codigoProduto)){
                produtoEncontrado = produto;
                break;
            }
        }
        return produtoEncontrado;
    }

    public static Produto procurar(String nomeProduto){
        lerXml();
        Produto produtoEncontrado = null;
        for(int i=0; i < lista.size(); i++){
            Produto produto = lista.get(i);
            if (produto.getNomeProduto() != null){
                if (produto.getNomeProduto().equals(nomeProduto)){
                    produtoEncontrado = produto;
                    break;
                }
            }
            
        }
        return produtoEncontrado;
    }

}
