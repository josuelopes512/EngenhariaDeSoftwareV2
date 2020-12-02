import java.util.Random;
import java.util.Scanner;


public class Cadastro {
		
	public static void cadastrar(Cliente clienteCadastro) {
		Random random = new Random();
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite Seu novo usuario (Cliente): ");
		String usuarioCliente = ler.nextLine();
		System.out.println("Digite sua senha: ");
		String senhaCliente = ler.nextLine();
		int codigoCliente = random.nextInt(100000000);

		Cliente procurarCliente = ClienteBD.procurarPorUsuario(usuarioCliente);
		//System.out.println("teste" + procurarCliente.getUsuario());
		if (procurarCliente != null){
			System.err.println("Nao foi possivel cadastrar o cliente porque ja possui cadastro! ");
			System.out.println("Sera redirecionado para fazer o login! ");
			Login.logar(clienteCadastro);
		}
		else{
			clienteCadastro.setUsuario(usuarioCliente);
			clienteCadastro.setSenha(senhaCliente);
			clienteCadastro.setCodigo(codigoCliente);
			ClienteBD.inserir(clienteCadastro);
			System.out.println("Cadastro Aprovado !");
		}
		//return clienteCadastro;
	}

	public static void cadastrar(Vendedor cadastroVendedor) {
		Random random = new Random();
		Scanner ler = new Scanner(System.in);
		System.out.println("Digite Seu novo usuario (Vendedor): ");
		String usuarioVendedor = ler.nextLine();
		System.out.println("Digite sua senha: ");
		String senhaVendedor = ler.nextLine();
		int codigoVendedor = random.nextInt(100000000);

		Vendedor procurarVendedor = VendedorBD.procurarPorUsuario(usuarioVendedor);
		
		if (procurarVendedor != null) {
			System.err.println("Nao foi possivel cadastrar o cliente porque ja possui cadastro! ");
			System.out.println("Sera redirecionado para fazer o login! ");
			Login.logar(cadastroVendedor);
		}
		else{
			cadastroVendedor.setUsuario(usuarioVendedor);
			cadastroVendedor.setSenha(senhaVendedor);
			cadastroVendedor.setCodigoVendedor(codigoVendedor);
			VendedorBD.inserir(cadastroVendedor);
			System.out.println("Cadastro Aprovado !");	
		}
		//return cadastroVendedor;
	}

}
