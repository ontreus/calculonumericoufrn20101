import java.io.BufferedReader;
import java.io.InputStreamReader;


public class CN2Unidade {
	
	public static int FUNCAO_1 = 1;
	public static int FUNCAO_2 = 2;
	public static int FUNCAO_3 = 3;	
	
	static double erro = 0.0000000000001;
	Pair<Double, Double> intervaloAproximacao = new Pair<Double, Double>();
	int aproximacao = 0;
	int refinamento = 0;
	int funcao = 0;
	double pontoInicial = 0;
	double deslocamento = 1;
	boolean intervalosPositivos;
	
	public static double funcao1(double x)
	{
		return Math.pow(x, 2) - 2;
	}
	
	public static double funcao1derivada(double x)
	{
		return 2*x;
	}
	
	public static double funcaoG1(double x)
	{
		return (x + 2)/(x + 1);
	}
	
	public static double funcao2(double x)
	{
		return Math.pow(x, 2) + x - 6;
	}
	
	public static double funcaoG2(double x)
	{
		return Math.pow(6 - x, 0.5);
	}
	
	public static double funcao2derivada(double x)
	{
		return 2 * x + 1;
	}	
	
	public static double funcao3(double x)
	{
		return Math.sin(Math.pow(x, 2)) - x;
	}
	
	public static double funcao3derivada(double x)
	{
		return 2 * x * Math.cos(Math.pow(x, 2)) - 1;
	}
	
	public static double funcaoG3(double x)
	{
		return 2 * x * Math.cos(Math.pow(x, 2));
	}
	
	public void executarTrocaDeSinal()
	{
		this.intervaloAproximacao = trocaDeSinal(pontoInicial, deslocamento);
	}
	
	public static void main(String[] args)
	{
		CN2Unidade cn = new CN2Unidade();		
		int choose = 0;
		while(true)
		{
			System.out.println("Por favor, o que deseja fazer? Insira o númeo correspondente à opção e aperte Enter");
			System.out.println("1: Encontrar Raiz de Equação.");
			System.out.println("2: Resolução de Sistema Não-Linear.");
			System.out.println("3: Sair do Programa.");
						
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try
			{
				choose = Integer.parseInt(reader.readLine());
				System.out.println("Voce selecionou a opcao: " + choose);
				if(choose == 1)
				{
					System.out.println("Escolha uma função:");
					System.out.println("1: f(x) = x² - 2");
					System.out.println("2: f(x) = x² + x - 6");
					System.out.println("3: f(x) = senx² - x");					
					System.out.println("4: Voltar ao Menu Principal");
					funcao:while (true)
					{
						choose = Integer.parseInt(reader.readLine());
						if(choose == 1 || choose == 2 || choose == 3)
						{
							cn.funcao = choose;
							while(true)						
							{
								System.out.println("Escolha um dos metodos de Aproximcao");
								System.out.println("1: Troca de Sinal");
								System.out.println("2: Lagrange");
								System.out.println("3: Voltar ao Menu de Funções");
								System.out.println("4: Voltar ao Menu Principal");
								System.out.println("5: Sair do programa");
																
								choose = Integer.parseInt(reader.readLine());
								if(choose == 1)
								{
									System.out.println("Insira o ponto inicial:");
									cn.pontoInicial = Double.parseDouble(reader.readLine());
									System.out.println("Insira o deslocamento:");
									cn.deslocamento = Double.parseDouble(reader.readLine());
									cn.executarTrocaDeSinal();
									System.out.println("Intervalo gerado pela Troca de Sinal:");
									System.out.println("[ " + cn.intervaloAproximacao.getX().toString() + " , " + cn.intervaloAproximacao.getY().toString() + " ]" );
									reader.readLine();						
								}
								else if(choose == 2)
								{
									if(cn.funcao == FUNCAO_3)
									{
										System.out.println("Esta funcao nao pode ser usada com Lagrange.");
										continue;
									}
									System.out.println("Insira 1 para intervalo positivo ou 2 para intervalo negativo");
									{
										choose = Integer.parseInt(reader.readLine());
										if(choose == 1 || choose == 3)
										{
											cn.intervalosPositivos = true;
											
										}
										else if(choose == 2)
										{
											cn.intervalosPositivos = false;
										}
										cn.getLagrange();
										System.out.println("Intervalo gerado por Lagrange:");
										System.out.println("[ " + cn.intervaloAproximacao.getX().toString() + " , " + cn.intervaloAproximacao.getY().toString() + " ]" );
										reader.readLine();
									}
								}
								else if(choose == 3)
								{
									break;
								}
								else if(choose == 4)
								{
									break funcao;
								}
								else if(choose == 5)
								{
									System.out.println("Programa Terminado...");
									return;
								}
								else
								{
									continue;
								}
								System.out.println("Escolha um dos metodos de Refinamento");
								System.out.println("1: Bisseção");
								System.out.println("2: Algoritmo da Corda");
								System.out.println("3: Algoritmo Ponto-Fixo");
								System.out.println("4: Algoritmo de Newton");
								System.out.println("5: Voltar ao Menu Principal");
								System.out.println("6: Sair do Programa");
								double result = 0;
								String s = "Algoritmo de refinamento executado: ";
								while(true)
								{
									choose = Integer.parseInt(reader.readLine());
									if(choose != 5 && choose != 6)
									{
										System.out.println("Agora insira o erro do resultado. Digite qualquer não número para usar o erro padrão.");
										String f = reader.readLine();
										if(SistemaNaoLinear2Unidade.isNumber(f))
											CN2Unidade.erro = Double.parseDouble(f);
										
									}									
									if(choose == 1)
									{
										
										result = cn.executarBissecao();
										s = s + "Bisseção"; 
									}
									else if(choose == 2)
									{
										
										result = cn.executarCordas();
										s = s + "Corda";
									}
									else if (choose == 3)
									{
										result = cn.executarPontoFixo();
										s = s + "Ponto Fixo";
									}
									else if (choose == 4)
									{
										result = cn.executarNewton();
										s = s + "Newton";
									}
									else if (choose == 5)
									{
										break funcao;										
									}
									else if(choose == 6)
									{
										System.out.println("Finalizando programa");
										return;
									}
									System.out.println("Resultado da execução do Algoritmo:");
									System.out.println("Intervalo médio encontrado: " + "[ " + cn.intervaloAproximacao.getX().toString() + " , " + cn.intervaloAproximacao.getY().toString() + " ]");
									System.out.println(s);
									System.out.println("Raiz encontrada: " + result);
									reader.readLine();
									break;									
								}					
						
							}
						}
						else if(choose == 4)
						{
							break;
						}					
					}
				}
				else if(choose == 2)
				{
					SistemaNaoLinear2Unidade s = new SistemaNaoLinear2Unidade();					
					while(true)
					{
						System.out.println("Escolha um Sistema Não-Linear. Digite 3 para Voltar ao Menu Principal.");
						System.out.println("1: f1(x,y) = x² + y² -1");
						System.out.println("   f2(x,y) = x - y²");
						System.out.println("2: g1(x,y) = x² + y² - 2");
						System.out.println("   g2(x,y) = x² - y² - 1");
						choose = Integer.parseInt(reader.readLine());
						if(choose == 1 || choose == 2)
						{
							s.funcao = choose;
							double[][] x = new double[2][1];
							String s1 = "Insira a posicao para x.";
							if(choose == 1)
							{
								s1 = s1 + " Sugestão: 1";
							}
							else 
							{
								s1 = s1 + " Sugestão: 1.2"; 
							}
							System.out.println(s1);
							x[0][0] = Double.parseDouble(reader.readLine());
							s1 = "Insira a posicao para y.";
							if(choose == 1)
							{
								s1 = s1 + " Sugestão: 1";
							}
							else
							{
								s1 = s1 + " Sugestao: 0.7";
							}
							System.out.println(s1);
							x[1][0] = Double.parseDouble(reader.readLine());
							System.out.println("Insira o erro. Insira qualquer valor não numero para o erro padrao.");
							String led = reader.readLine();
							if(SistemaNaoLinear2Unidade.isNumber(led))
							{
								erro = Double.parseDouble(led);
							}
							else
							{
								erro = 0.00001;
							}
							x = s.quaseNewton(x, erro);
							System.out.println("Imprimindo resultado:");
							System.out.println("x: " + x[0][0]);
							System.out.println("y: " + x[1][0]);
							reader.readLine();		
							continue;
						}
						if(choose == 3)
						{
							break;
						}
					}					
				}
				else if(choose == 3)
				{
					System.out.println("Saindo do Programa...");
					return;
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
		}
	}
	
	public double executarPontoFixo()
	{
		return this.pontoFixo(intervaloAproximacao.getX(), intervaloAproximacao.getY());
	}
	
	public double executarCordas()
	{
		return this.corda_simples(this.intervaloAproximacao.getX(), intervaloAproximacao.getY());
	}
	
	public double executarNewton()
	{
		return this.algoritmoDeNewton(intervaloAproximacao.getX(), intervaloAproximacao.getY());
	}
	
	public double f(double x)
	{
		if(funcao == 1)
		{
			return funcao1(x);
		}
		if(funcao == 2)
		{
			return funcao2(x);
		}
		if(funcao == 3)
		{
			return funcao3(x);
		}
		return 0;
	}
	
	public Pair<Double,Double> trocaDeSinal(double x,double d)
	{
		while ( f(x) * f(x+d) > 0 )
		{
			x = x + d;
		}
		return new Pair<Double,Double>(x,x+d);
	}
	
	public void getLagrange()
	{
		Pair<Double, Double> p = new Pair<Double, Double>();
		if(funcao == FUNCAO_1)
		{
			if(intervalosPositivos)
			{
				p.setX(lagrange(2, 0, 2, 1));
				p.setY(1 / lagrange(2, 0, 2, 2));				
			}
			else
			{
				p.setX(- lagrange(2, 0, 2, 1));
				p.setY(-1 / lagrange(2, 0, 2, 2));
			}			
		}
		else if(funcao == FUNCAO_2)
		{
			if(intervalosPositivos)
			{
				p.setX(lagrange(2, 0, 6, 1));
				p.setY(1/lagrange(2, 0, 6, 6));
			}
			else
			{
				p.setX(- lagrange(2, 1, 6, 1));
				p.setY(-1 / lagrange(2, 1, 6, 6));				
			}			
		}
		else System.out.println("Deu problema na hora de pegar o Lagrange");
		this.intervaloAproximacao = p;
	}
	
	
	public double lagrange(double n,double k, double b, double an)
	{
		double pow = n - k;
		double basis = b/an;
		return 1 + Math.pow(basis, 1/pow);		
	}
	
	public boolean condicaoDeParada(double erro,double x)
	{
		if(Math.abs(f(x)) < erro)
		{
			return true;
		}
		return false;
	}
	
	public double executarBissecao()
	{
		return bissecao(this.intervaloAproximacao.getX(), intervaloAproximacao.getY());
	}
	
	
	public double bissecao(double a,double b)
	{
		double inf = a;
		double sup = b;
		double med;
		int i = 0;
		do
		{
			med = (inf + sup)/2;
			if (f(med)*f(inf) > 0)
			{
				inf = med;
			}
			else
			{
				if(f(med) == 0)
				{
					return med;
				}
				else
				{
					sup = med;
				}
			}
			i++;
			if(condicaoDeParada(erro, med))
				break;
		}
		while (i < 1000);
		System.out.println("Quantidade de iterações feitas: " + i);
		return med;
	}
	
	public double corda_simples(double a,double b)
	{
		double inf = a;
		double sup = b;
		double cn;
		double t1;
		double t2;
		double t3;
		int i = 0;
		do
		{
			
			t1 = sup - inf;
			t2 = f(sup) - f(inf);
			t3 = t1 * f(inf);
			cn = inf - t3/t2; //inf - (((sup - inf) * f(inf))/f(sup) - f(inf));
			if( f(cn)*f(inf) > 0)
			{
				inf = cn;
			}
			else
			{
				if(f(cn)==0)
				{
					return cn;
				}
				else
				{
					sup = cn;
				}
			}
			i++;
			if(condicaoDeParada(erro, cn))
				break;
		}
		while (i < 1000);
		System.out.println("Quantidade de iterações feitas: " + i);
		return cn;
	}
	
	public double g(double x)
	{
		if(funcao == FUNCAO_1)
		{
			return funcaoG1(x);
		}
		else if(funcao == FUNCAO_2)
		{
			return funcaoG2(x);
		}
		else if(funcao == FUNCAO_3)
		{
			return funcaoG3(x);
		}
		else
		{
			System.out.println("Deu certo nao aqui na definicao de g");
			return 0;
		}
			
		
	}
	
	
	public double pontoFixo(double a,double b)
	{
		double xap = Math.random();
		xap = xap*(b-a) + a;
		double xapm;
		int i = 0;
		do
		{
			xapm = g(xap);
			xap = xapm;
			i++;
			if(condicaoDeParada(erro, xap))
				break;
		}
		while (i < 1000);
		System.out.println("Quantidade de iterações feitas: " + i);
		return xap;		
	}
	
	public double fLinha(double x)
	{
		if(this.funcao == FUNCAO_1)
		{
			return funcao1derivada(x);
		}
		else if(this.funcao == FUNCAO_2)
		{
			return funcao2derivada(x);
		}
		else if(this.funcao == FUNCAO_3)
		{
			return funcao3derivada(x);
		}
		System.out.println("Deu pau na funcao fLine");
		return x;
	}
	
	public double algoritmoDeNewton(double a,double b)
	{
		double x = Math.random();
		x = x*(b-a) + a;
		double xn;
		int i = 0;
		do
		{
			xn = x - (1/fLinha(x))*f(x);
			x = xn;
			i++;
			if(condicaoDeParada(erro, x))
				break;
		}
		while (i < 1000);
		System.out.println("Quantidade de iterações feitas: " + i);		
		return x;
	}
	

}
