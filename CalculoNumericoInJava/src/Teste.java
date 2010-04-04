import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Teste {
	
	public static void main(String[] args)
	{
		int choose = 0;
		while(choose != 7)
		{
			System.out.println("Por favor, escolha uma das funções abaixo de acordo com o número:");
			System.out.println("1: Binário para Decimal");
			System.out.println("2: Decimal para Binário");
			System.out.println("3: Fatoração LU SEM Pivotamento Parcial");
			System.out.println("4: Fatoração LU COM Pivotamento Parcial");
			System.out.println("5: Algoritmo Troca-Linha");
			System.out.println("6: Fatoração de Cholesky");
			System.out.println("7: Substituição Progressiva");
			System.out.println("8: Substituição Regressiva");
					

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			try
			{
				choose = Integer.parseInt(reader.readLine());
				if(choose == 1)
				{
					System.out.println("Digite a representação em String do Número Binário");
					String bNumber = reader.readLine().trim();
					double number = CalculoNumerico.binaryToDecimal(bNumber);
					System.out.println("O número decimal para o binário dado é: " + number);
					System.out.println("Digite Enter para continuar...");
					reader.readLine();
				}
				if(choose == 2)
				{
					System.out.println("Digite o número na base decimal");
					double decimal = Double.valueOf(reader.readLine().trim());
					String bNumber = CalculoNumerico.decimalToBinary(decimal);
					System.out.println("Digite Enter para continuar...");
					reader.readLine();
				}
				if(choose == 3)
				{
					System.out.println("Digite os elementos da matriz A a ser calculada");
					String elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz A");
					int lines = Integer.valueOf(reader.readLine().trim());
					double[][] A = CalculoNumerico.createMatriz(elements, lines);
					System.out.println("Digite os elementos da matriz b a ser calculado");
					elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz b");
					lines = Integer.valueOf(reader.readLine().trim());
					double[][] b = CalculoNumerico.createMatriz(elements, lines);
					double[][] res = CalculoNumerico.fatoracaoLU(A, b, false);
					System.out.println("Imprimindo o resultado da Fatoração LU SEM pivotamento parcial:");
					CalculoNumerico.printMatriz(res);
					System.out.println("Digite Enter para continuar...");
					reader.readLine();
				}
				if(choose == 4)
				{
					System.out.println("Digite os elementos da matriz A a ser calculada");
					String elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz A");
					int lines = Integer.valueOf(reader.readLine().trim());
					double[][] A = CalculoNumerico.createMatriz(elements, lines);
					System.out.println("Digite os elementos da matriz b a ser calculado");
					elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz b");
					lines = Integer.valueOf(reader.readLine().trim());
					double[][] b = CalculoNumerico.createMatriz(elements, lines);
					double[][] res = CalculoNumerico.fatoracaoLU(A, b, false);
					System.out.println("Imprimindo o resultado da Fatoração LU COM pivotamento parcial:");
					CalculoNumerico.printMatriz(res);		
					System.out.println("Digite Enter para continuar...");
					reader.readLine();
					
				}
				if(choose == 5)
				{
					System.out.println("Digite a matriz A a ter as linhas trocadas");
					String elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz A");
					int lines = Integer.valueOf(reader.readLine().trim());
					double[][] A = CalculoNumerico.createMatriz(elements, lines);
					System.out.println("Digite a matriz b a ter as linhas trocadas");
					elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz b");
					lines = Integer.valueOf(reader.readLine().trim());
					double[][] b = CalculoNumerico.createMatriz(elements, lines);
					ArrayList<double[][]> res = CalculoNumerico.troca_linha(A,b);
					System.out.println("Imprimindo a matriz  A do resultado do troca-linha");
					CalculoNumerico.printMatriz(res.get(0));
					System.out.println("Imprimindo a matriz b do resultado do troca-linha");
					CalculoNumerico.printMatriz(res.get(1));
					System.out.println("Digite Enter para continuar...");
					reader.readLine();
				}
				if(choose == 6)
				{
					System.out.println("Digite os elementos da matriz A a ser calculada");
					String elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz A");
					int lines = Integer.valueOf(reader.readLine().trim());
					double[][] A = CalculoNumerico.createMatriz(elements, lines);
					double[][] res = CalculoNumerico.cholesky(A);
					System.out.println("Imprimindo a matriz resultado da fatoração de Cholesky (R e R transposta) como no algoritmo do Professor:");
					CalculoNumerico.printMatriz(res);
					System.out.println("Digite Enter para continuar...");
					reader.readLine();
				}
				if(choose == 7)
				{
					System.out.println("Digite os elementos da matriz L inversa");
					String elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz L");
					int lines = Integer.valueOf(reader.readLine().trim());
					double[][] L = CalculoNumerico.createMatriz(elements, lines);
					System.out.println("Digite os elementos da matriz b");
					elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz A");
					lines = Integer.valueOf(reader.readLine().trim());
					double[][] b = CalculoNumerico.createMatriz(elements, lines);
					double[][] res = CalculoNumerico.substituicao_progressiva(L,b);
					System.out.println("Imprimindo a matriz y, resultado da Substituição progressiva");
					CalculoNumerico.printMatriz(res);
					System.out.println("Digite Enter para continuar...");
					reader.readLine();
				}
				if(choose == 8)
				{
					System.out.println("Digite os elementos da matriz y");
					String elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz y");
					int lines = Integer.valueOf(reader.readLine().trim());
					double[][] y = CalculoNumerico.createMatriz(elements, lines);
					System.out.println("Digite os elementos da matriz u");
					elements = reader.readLine();
					System.out.println("Digite a quantidade de linhas que tem a matriz u");
					lines = Integer.valueOf(reader.readLine().trim());
					double[][] u = CalculoNumerico.createMatriz(elements, lines);
					double[][] res = CalculoNumerico.substituicao_regressiva(y, u);
					System.out.println("Imprimindo a matriz y, resultado da Substituição progressiva");
					CalculoNumerico.printMatriz(res);
					System.out.println("Digite Enter para continuar...");
					reader.readLine();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Voce nao digitou um número");
				System.out.println("Saindo do Programa");
				return;
			}	
		}
	}
				


}
