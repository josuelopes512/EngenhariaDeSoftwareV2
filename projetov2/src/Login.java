import java.util.Random;
import java.util.Scanner;


public class Login {
	
	
	public static Cliente logar(Cliente clienteLogin) {
		Scanner ler = new Scanner(System.in);
		
		System.out.println("Digite Seu Usuario (Cliente): ");
		String usuarioCliente = ler.nextLine();
		
		System.out.println("Digite sua senha: ");
		String senhaCliente = ler.nextLine();

		clienteLogin = ClienteBD.procurarPorUsuarioSenha(usuarioCliente, senhaCliente);
		if (clienteLogin == null) {
			System.err.println("Login ou senha estao errados!");
		}
		else{
			System.out.println("Logado !");
		}
		return clienteLogin;
	}

	public static Vendedor logar(Vendedor vendedorLogin) {
		Scanner ler = new Scanner(System.in);

		System.out.println("Digite Seu Usuario (Vendedor): ");
		String usuario = ler.nextLine();
		
		System.out.println("Digite sua senha: ");
		String senha = ler.nextLine();

		vendedorLogin = VendedorBD.procurarPorUsuarioSenha(usuario, senha);
		if (vendedorLogin != null) {
			System.out.println("Logado !");
		}
		else{
			System.err.println("Login ou senha estao errados!");
		}
		return vendedorLogin;
	}

}
