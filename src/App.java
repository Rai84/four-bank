import java.util.Scanner;
import model.Cadastro;
import service.CadastroService;

public class App {

    static Scanner scan = new Scanner(System.in);
    static CadastroService cadastroService = new CadastroService(); // Corrigido para usar CadastroService

    static int opcao = 0;

    public static void main(String[] args) {
        System.out.println("Bem-vindo ao sistema de cadastro de clientes!");

        while (opcao != 3) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Buscar cliente");
            System.out.println("3 - Sair");

            opcao = scan.nextInt();
            scan.nextLine(); // Limpar o buffer após a leitura de um inteiro

            switch (opcao) {
                case 1:
                    salvar();
                    break;
                case 2:
                    buscar();
                    break;
                case 3:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
    }

    public static void salvar() {
        System.out.println("Digite o nome do cliente:");
        String nome = scan.nextLine();
        System.out.println("Digite o CPF do cliente:");
        String CPF = scan.nextLine();
        System.out.println("Digite o endereço do cliente:");
        String endereco = scan.nextLine();
        System.out.println("Digite o telefone do cliente:");
        String telefone = scan.nextLine();
        System.out.println("Digite o email do cliente:");
        String email = scan.nextLine();
        System.out.println("Digite a data de nascimento do cliente:");
        String data_nascimento = scan.nextLine();
        System.out.println("Digite a senha do cliente:");
        String senha = scan.nextLine();

        Cadastro cadastro = new Cadastro(nome, CPF, endereco, telefone, email, data_nascimento, senha);
        cadastroService.salvarCadastro(cadastro); // Corrigido para usar CadastroService
    }

    public static void buscar() {
        System.out.println("Buscar cliente pelo ID:");
        int id = scan.nextInt();
        scan.nextLine(); // Limpar o buffer após a leitura de um inteiro

        Cadastro cadastroBuscado = cadastroService.buscarCadastro(id); // Corrigido para usar buscarCadastro
        if (cadastroBuscado != null) {
            System.out.println("Cliente: " + cadastroBuscado.getNome());
            System.out.println("CPF: " + cadastroBuscado.getCPF());
            System.out.println("Endereço: " + cadastroBuscado.getEndereco());
            System.out.println("Telefone: " + cadastroBuscado.getTelefone());
            System.out.println("Email: " + cadastroBuscado.getEmail());
            System.out.println("Data de nascimento: " + cadastroBuscado.getData_nascimento());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
