import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class VendedorBD {
    private static ArrayList<Vendedor> lista = new ArrayList<Vendedor>();
    
    public static void inserir(Vendedor novoVendedor) {
        lerXml();
        lista.add(novoVendedor);
        salvarXml();
    }

    public static void alterar(Vendedor VendedorAlterado){
        excluir(VendedorAlterado.getCodigoVendedor());
        inserir(VendedorAlterado);
    }

    public static void excluir(int codigo){
        lerXml();
        for(int i=0; i < lista.size(); i++){
            Vendedor cadaVendedor = lista.get(i);
            if (cadaVendedor.getCodigoVendedor() == codigo){
                lista.remove(i);
                break;
            }
        }
        salvarXml();
    }

    public static ArrayList<Vendedor> listar(){
        lerXml();
        return lista;
    }
    
    
	public static void lerXml(){
        File arquivo = new File("src//bd//listaDeVendedores.xml");
        if (arquivo.exists()){
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("listaDeVendedores",Vendedor.class);
            lista = (ArrayList<Vendedor>) xstream.fromXML(arquivo);
        }else{
            lista = new ArrayList<Vendedor>();
            
        }               
    }
	
	public static void exibirXml() {
        lerXml();
        for (int i = 0; i < lista.size(); i++) {
            Vendedor cl = lista.get(i);
            System.out.println(cl.getUsuario());
        }
	}

    public static void salvarXml(){
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("listaDeVendedores", Vendedor.class);
        try{
            FileWriter escritor=new FileWriter("src//bd//listaDeVendedores.xml");
            escritor.write( xstream.toXML(lista) );
            escritor.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }   
    }

    public static Vendedor procurarPorUsuarioSenha(String usuario, String senha){
        lerXml();
        Vendedor VendedorEncontrado = null;
        for(int i=0; i < lista.size(); i++){
            Vendedor Vendedor = lista.get(i);
            if (Vendedor.getUsuario().equals(usuario) && Vendedor.getSenha().equals(senha)){
                VendedorEncontrado = Vendedor;
                break;
            }
        }
        return VendedorEncontrado;
    }

    public static Vendedor procurarPorUsuario(String usuario){
        lerXml();
        Vendedor VendedorEncontrado = null;
        for(int i=0; i < lista.size(); i++){
            Vendedor Vendedor = lista.get(i);
            if (Vendedor.getUsuario().equals(usuario)){
                VendedorEncontrado = Vendedor;
                break;
            }
        }
        return VendedorEncontrado;
    }
}
