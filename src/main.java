import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class main {
	static class Funcionario
	{
		public Calendar ultimaEntrada;
		public String nome, endereco, taxas_nome[];
		public int tipo,horas_trabalhadas[][];// 1-horista 2-assalariado 3-comissionado
		public Double salario, salario_mensal, comissao,vendas[][],taxas[];


		public Funcionario(){
			horas_trabalhadas = new int[12][31];
			vendas = new Double[12][31];
			taxas_nome = new String[10];
			taxas = new Double[10];
		}
	};

	static Scanner scan = new Scanner(System.in);
	static Calendar calendar = Calendar.getInstance();
	static String data= ""+calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+1+"/"+calendar.get(Calendar.YEAR);;


	public static void main(String[] args) {
		Funcionario funcionarios[] = new Funcionario[20];
		int totalfuncionarios = 0;
		int opcao=0;
		do{
			opcao = menu();
			switch(opcao){
			case 1:
				if(adicionarFuncionario(funcionarios,totalfuncionarios)==1)
					totalfuncionarios++;
				break;
			case 2:
				if(totalfuncionarios>0){
					if(removerFuncionario(funcionarios,totalfuncionarios)==1)
						totalfuncionarios--;
				}
				else
					System.out.println("Nao tem funcionarios cadastrados");
				break;
			case 3:
				lancarCartaoPonto(funcionarios);
			case 4:
				baterPonto(funcionarios);
			case 5:
				resultadoVenda(funcionarios);
			case 6:
				lancarTaxaDeServico(funcionarios);
			case 10:
				System.out.println("Saiu do sistema");
				break;
			case 11:
				scan.nextLine();
				data = scan.nextLine();
				break;
			default:
				System.out.println("Essa opcao nao existe!");
				break;
			}
		}while(opcao!=10);
	}

	private static void lancarTaxaDeServico(Funcionario[] func) {
		listarFuncionariosPorNome(func);
		System.out.println("Digite o numero do funcionario: ");
		int id = scan.nextInt();		
		scan.nextLine();
		System.out.println("Digite o nome da taxa: ");
		String nomeTaxa = scan.nextLine();	
		System.out.println("Digite o valor da taxa em porcentagem: ");
		Double taxa = scan.nextDouble();	
		
		for(int i=0;i<10;i++){
			if(func[id].taxas[i]==0){
				func[id].taxas[i] = taxa;
				func[id].taxas_nome[i] = nomeTaxa;
				System.out.println("Taxa cadastrada com sucesso!");
			}
		}
	}

	private static void resultadoVenda(Funcionario[] func) {
		listarFuncionariosPorNome(func);
		System.out.println("Digite o numero do funcionario: ");
		int id = scan.nextInt();

		System.out.println("Voce deseja:\n\t1-Ver resultado de vendas\n\t2-Lançar venda\n");
		int opc = scan.nextInt();
		if(opc==1){
			System.out.print("Digite o mes: ");
			int mes = scan.nextInt();
			imprimirVendas(func[id],mes);
		}else if (opc==2){
			System.out.print("Digite o valor da venda: ");
			Double valor = scan.nextDouble();
			System.out.print("Digite o mes da venda: ");
			int mes = scan.nextInt();
			System.out.print("Digite o dia da venda: ");
			int dia = scan.nextInt();
			func[id].vendas[mes][dia] += valor;

			System.out.println("Venda cadastrada!");
		}
	}

	private static void imprimirVendas(Funcionario funcionario, int mes) {
		System.out.println("----Resultado vendas mes "+mes+" - "+funcionario.nome+"----");
		for(int i=0;i<31;i++){
			if(funcionario.vendas[mes][i]!=0)
			System.out.println("Dia "+i+1+":"+funcionario.vendas[mes][i]+" reais.");
		}
		
	}

	public static int menu (){
		System.out.println("---- Menu Folha de Pagamento ----   "+data+" \n"
				+ "1- Adição de um empregado\n"
				+ "2- Remoção de um empregado\n"
				+ "3- Lançar um Cartão de Ponto\n"
				+ "4- Assinar ponto do funcionario\n"
				+ "5- Lançar um Resultado Venda\n"
				+ "6- Lançar uma taxa de serviço\n"
				+ "7- Alterar detalhes de um empregado\n"
				+ "8- Rodar a folha de pagamento para hoje\n"
				+ "9- Undo\\Redo\n"
				+ "10- Sair\n"
				+ "10- Mudar data");
		return scan.nextInt();
	}

	private static void baterPonto(Funcionario func[]) {
		listarFuncionariosPorNome(func);
		System.out.println("Digite o numero do funcionario: ");
		int id = scan.nextInt();
		scan.nextLine();
		System.out.println("Digite a hora:");
		String hora = scan.nextLine();
		String dataEHora = data + " " + hora;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(sdf.parse(dataEHora));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(func[id].ultimaEntrada==null){
			System.out.println("Entrada batida: "+cal.get(Calendar.HOUR_OF_DAY) + ":"+cal.get(Calendar.MINUTE));
			func[id].ultimaEntrada = cal;
		}
		else{
			Calendar calentrada = func[id].ultimaEntrada;
			System.out.println("Saida batida: "+cal.get(Calendar.HOUR_OF_DAY) + ":"+cal.get(Calendar.MINUTE));

			long minutos = (cal.getTimeInMillis() - calentrada.getTimeInMillis())/60000;
			System.out.println("min"+minutos);
			func[id].horas_trabalhadas[cal.get(Calendar.MONTH)][cal.get(Calendar.DAY_OF_MONTH)]=(int) minutos;
			func[id].ultimaEntrada=null;
		} 
	}
	public static void imprimirFuncionario(Funcionario func){
		System.out.println("--Funcionario--");
		System.out.println("Nome: "+func.nome);
		System.out.println("Endereco: "+func.endereco);
		System.out.println("Tipo: "+func.tipo);
		System.out.println("Salario: "+func.salario);
		System.out.println("Salario mensal: "+func.salario_mensal);
		System.out.println("Comissao: "+func.comissao);
	}

	public static int adicionarFuncionario(Funcionario func[],int totalfuncionarios){
		System.out.println("Adicionar funcionario");
		func[totalfuncionarios] = new Funcionario();
		System.out.println("Nome: ");
		scan.nextLine();
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
		return 1;
	}

	public static void listarFuncionariosPorNome(Funcionario func[]){
		System.out.println("Lista de funcionarios:");
		for( int i = 0 ; i < func.length ; i++ ){
			if(func[i]!=null){
				System.out.println("Funcionario "+i+" - Nome: "+func[i].nome);
			}
		}
	}

	public static int removerFuncionario(Funcionario func[],int totalfuncionarios){
		listarFuncionariosPorNome(func);
		System.out.println("Digite o numero do funcionario a ser removido: ");
		int id = scan.nextInt();
		if(func[id]!=null){
			func[id]=null;
			return 1;
		}else{
			return 0;
		}
	}

	private static void lancarCartaoPonto(Funcionario func[]) {
		listarFuncionariosPorNome(func);
		System.out.println("Digite o numero do funcionario: ");
		int id = scan.nextInt();
		System.out.println("Digite o mes(1-12): ");
		int mes = scan.nextInt();
		if(func[id]!=null){
			imprimirDiasTrabalhados(func[id],mes);
		}
	}

	private static void imprimirDiasTrabalhados(Funcionario funcionario, int mes) {
		System.out.println("----Dias trabalhados mes "+mes+"----");
		for(int i=0;i<31;i++){
			if(funcionario.horas_trabalhadas[mes][i]!=0)
			System.out.println("Dia "+i+1+":"+funcionario.horas_trabalhadas[mes][i]+" minutos");
		}
	}


}
