import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class ClienteBD {
    private static ArrayList<Cliente> lista = new ArrayList<Cliente>();
    
    public static void inserir(Cliente novoCliente) {
        lerXml();
        lista.add(novoCliente);
        salvarXml();
    }

    public static void alterar(Cliente clienteAlterado){
        excluir(clienteAlterado.getCodigoCliente());
        inserir(clienteAlterado);
    }

    public static void excluir(int codigo){
        lerXml();
        for(int i=0; i < lista.size(); i++){
            Cliente cadaCliente = lista.get(i);
            if (cadaCliente.getCodigoCliente() == codigo){
                lista.remove(i);
                break;
            }
        }
        salvarXml();
    }

    public static ArrayList<Cliente> listar(){
        lerXml();
        return lista;
    }
    
    
	public static void lerXml(){
        File arquivo = new File("src//bd//listaDeClientes.xml");
        if (arquivo.exists()){
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("listaDeClientes",Cliente.class);
            lista = (ArrayList<Cliente>) xstream.fromXML(arquivo);
        }else{
            lista = new ArrayList<Cliente>();
            
        }               
    }
	
	public static void exibirXml() {
        lerXml();
        for (int i = 0; i < lista.size(); i++) {
            Cliente cl = lista.get(i);
            System.out.println(cl.getUsuario());
        }
	}

    public static void salvarXml(){
        XStream xstream = new XStream(new DomDriver());
        xstream.alias("listaDeClientes", Cliente.class);
        try{
            FileWriter escritor=new FileWriter("src//bd//listaDeClientes.xml");
            escritor.write( xstream.toXML(lista) );
            escritor.close();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }   
    }

    public static Cliente procurarPorUsuarioSenha(String usuario, String senha){
        lerXml();
        Cliente clienteEncontrado = null;
        for(int i=0; i < lista.size(); i++){
            Cliente cliente = lista.get(i);
            if (cliente.getUsuario().equals(usuario) && cliente.getSenha().equals(senha)){
                clienteEncontrado = cliente;
                break;
            }
        }
        return clienteEncontrado;
    }

    public static Cliente procurarPorUsuario(String usuario){
        lerXml();
        Cliente clienteEncontrado = null;
        for(int i=0; i < lista.size(); i++){
            Cliente cliente = lista.get(i);
            if (cliente.getUsuario() != null){
                if (cliente.getUsuario().equals(usuario)){
                    clienteEncontrado = cliente;
                    break;
                }
            }
            
        }
        return clienteEncontrado;
    }
}
