import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class main {
	static class Funcionario
	{
		 public String nome, endereco;
		 public int tipo;// 1-horista 2-assalariado 3-comissionado
		 public Double salario, salario_mensal, comissao; 
		 public int horas_trabalhadas[][];
		 public Calendar ultimaEntrada;
	 };
	 
	 static Scanner scan = new Scanner(System.in);
	 static Calendar calendar = Calendar.getInstance();
	 static String data;
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
		func[totalfuncionarios].horas_trabalhadas = new int[12][31];
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
		
		
	}

	public static int menu (){
		System.out.println("---- Menu Folha de Pagamento ----   "+data+" \n"
						 + "1- Adição de um empregado\n"
						 + "2- Remoção de um empregado\n"
						 + "3- Lançar um Cartão de Ponto\n"
						 + "4- Assinar ponto do funcionario\n"
						 + "4- Lançar um Resultado Venda\n"
						 + "5- Lançar uma taxa de serviço\n"
						 + "6- Alterar detalhes de um empregado\n"
						 + "7- Rodar a folha de pagamento para hoje\n"
						 + "8- Undo\n"
						 + "9- Redo\n"
						 + "10- Sair");
		return scan.nextInt();
	}
	public static void main(String[] args) {
		Funcionario funcionarios[] = new Funcionario[20];
		int totalfuncionarios = 0;
		data = ""+calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.get(Calendar.MONTH)+1+"/"+calendar.get(Calendar.YEAR);
		//adicionarFuncionario(funcionarios,totalfuncionarios++);
		//imprimirFuncionario(funcionarios[0]);
		//removerFuncionario(funcionarios);
		int opcao=0;
		do{
			opcao = menu();
			switch(opcao){
				case 1:
					if(adicionarFuncionario(funcionarios,totalfuncionarios)==1)
						totalfuncionarios++;
					break;
				case 2:
					if(totalfuncionarios>0)
						if(removerFuncionario(funcionarios,totalfuncionarios)==1)
							totalfuncionarios--;
					else
						System.out.println("Nao tem funcionarios cadastrados");
					break;
				case 3:
					lancarCartaoPonto(funcionarios);
				case 4:
					baterPonto(funcionarios);
				case 10:
					System.out.println("Saiu do sistema");
					break;
				case 11:
					System.out.println("setar data");
					setarData();
					break;
				default:
					System.out.println("Essa opcao nao existe!");
					break;
			}
		}while(opcao!=10);
	}

	private static void setarData() {
		scan.nextLine();
		data = scan.nextLine();
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
}
