import java.util.Scanner;

public class main {
	static class Funcionario
	{
		 public String nome, endereco;
		 public int tipo;// 1-horista 2-assalariado 3-comissionado
		 public Double salario, salario_mensal, comissao; 
	 };
	 
	 static Scanner scan = new Scanner(System.in);
	 
	 public static void imprimirFuncionario(Funcionario func){
			System.out.println("--Funcionario--");
			System.out.println("Nome: "+func.nome);
			System.out.println("Endereco: "+func.endereco);
			System.out.println("Tipo: "+func.tipo);
			System.out.println("Salario: "+func.salario);
			System.out.println("Salario mensal: "+func.salario_mensal);
			System.out.println("Comissao: "+func.comissao);
		}
	
	public static void adicionarFuncionario(Funcionario func[],int totalfuncionarios){
		System.out.println("Adicionar funcionario");
		func[totalfuncionarios] = new Funcionario();
		System.out.println("Nome: ");
		func[totalfuncionarios].nome = scan.nextLine();
		System.out.println("Endereco: ");
		func[totalfuncionarios].endereco = scan.nextLine();
		System.out.println("Tipo: 1-horista 2-assalariado 3-comissionado ");
		int tipo = scan.nextInt();
		while(tipo!=1 && tipo!=2 && tipo!=3){
			System.out.println("Selecione um tipo correto\nTipo: 1-horista 2-assalariado 3-comissionado ");
			tipo = scan.nextInt();
		}
		
		func[totalfuncionarios].tipo = tipo;
		System.out.println("Salario");
		func[totalfuncionarios].salario = scan.nextDouble();
		System.out.println("Salario Mensal: ");
		func[totalfuncionarios].salario_mensal = scan.nextDouble();
		System.out.println("Comissao: ");
		func[totalfuncionarios].comissao = scan.nextDouble();
		totalfuncionarios++;
	}
	
	public static void listarFuncionariosPorNome(Funcionario func[]){
		for( int i = 0 ; i < func.length ; i++ ){
			if(func!=null){
				System.out.println("Funcionario "+i+" Nome: "+func[i].nome);
			}
		}
	}
	
	public static void removerFuncionario(Funcionario func[],int totalfuncionarios){
		listarFuncionariosPorNome(func);
		System.out.println("Digite o numero do funcionario a ser removido: ");
		int id = scan.nextInt();
		if(func[id]!=null)
			func[id]=null;
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
						 + "10- Sair ");
		return scan.nextInt();
	}
	public static void main(String[] args) {
		Funcionario funcionarios[] = new Funcionario[20];
		int totalfuncionarios = 0;
		//adicionarFuncionario(funcionarios,totalfuncionarios++);
		//imprimirFuncionario(funcionarios[0]);
		//removerFuncionario(funcionarios);
		int opcao=0;
		do{
			opcao = menu();
			switch(opcao){
				case 1:
					adicionarFuncionario(funcionarios,totalfuncionarios++);
					break;
				case 2:
					removerFuncionario(funcionarios,totalfuncionarios--);
					break;
				case 10:
					System.out.println("Saiu do sistema");
					break;
				default:
					System.out.println("Essa opcao nao existe!");
			}
		}while(opcao!=10);
		
		
		
	}
	
	
}
