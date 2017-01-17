import java.util.Scanner;

public class main {
	static class Funcionario
	{
		 public String nome, endereco, tipo;
		 public Double salario, salario_mensal, comissao; 
	 };
	
	 public static void imprimirFuncionario(Funcionario func){
			System.out.println("--Funcionario--");
			System.out.println("Nome: "+func.nome);
			System.out.println("Endereco: "+func.endereco);
			System.out.println("Tipo: "+func.tipo);
			System.out.println("Salario: "+func.salario);
			System.out.println("Salario mensal: "+func.salario_mensal);
			System.out.println("Comissao: "+func.comissao);
		}
	public static void inicializarFuncionarios(Funcionario funcionarios[]){
		for( int i = 0 ; i < funcionarios.length ; i++ ){
			funcionarios[i] = new Funcionario();
		}
	}
	
	public static void adicionarFuncionario(Funcionario func[],int totalfuncionarios){
		Scanner scan = new Scanner(System.in);
		func[totalfuncionarios].nome = scan.nextLine();
		func[totalfuncionarios].endereco = scan.nextLine();
		func[totalfuncionarios].tipo = scan.nextLine();
		func[totalfuncionarios].salario = scan.nextDouble();
		func[totalfuncionarios].salario_mensal = scan.nextDouble();
		func[totalfuncionarios].comissao = scan.nextDouble();
		totalfuncionarios++;
	}
	
	public static int menu (){
		System.out.println("1- Adição de um empregado\n"
						 + "2- Remoção de um empregado\n"
						 + "3- Lançar um Cartão de Ponto\n"
						 + "4- Lançar um Resultado Venda\n"
						 + "5- Lançar uma taxa de serviço\n"
						 + "6- Alterar detalhes de um empregado\n"
						 + "7- Rodar a folha de pagamento para hoje\n"
						 + "8- Undo\n"
						 + "9- Redo\n"
						 + "10- ");
		
		return 0;
	}
	public static void main(String[] args) {
		
		System.out.println("hello world");
		Funcionario funcionarios[] = new Funcionario[20];
		inicializarFuncionarios(funcionarios);
		int totalfuncionarios = 0;
		//adicionarFuncionario(funcionarios,totalfuncionarios++);
		//imprimirFuncionario(funcionarios[0]);
		//removerFuncionario(funcionarios);
		
		int opcao = menu();
		
		
	}
	
	
}
