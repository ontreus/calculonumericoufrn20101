import java.util.HashMap;

/**
 * 
 */

/**
 * @author Vitor
 *
 */
public class CalculoNumerico {

	public static int getIntegerFromChar(char i)
	{
		switch (i) {
		case '1':
			return 1;			
		case '2':
			return 2;
		case '3':
			return 3;
		case '4':
			return 4;
		case '5':
			return 5;
		case '6':
			return 6;
		case '7':
			return 7;
		case '8':
			return 8;
		case '9':
			return 9;
		case '0':
			return 0;
		default:
			return -1;
		}		
	}
	
	public static String decimalToBinary(float decimal)
	{
		String leftSide = divisao_sucessiva((int)decimal);
		String rightSide = multiplicacao_sucessiva(decimal - (int)decimal);
		return leftSide + "," + rightSide;
	}
	
	public static double[][] multiplicarMatrizes(double[][] multiplicadora,double[][] multiplicando)
	{
		if(multiplicadora[0].length != multiplicando.length)
		{
			System.out.println("O tamanho das matrizes não são compatíveis");
			return null;
		}		
				
		double[][] resultado = new double[multiplicadora.length][multiplicando[0].length];
		for(int i = 0 ; i < resultado.length ; i++)
		{
			for(int j = 0 ; j < resultado[0].length ; j++)
			{
				resultado[i][j] = 0;
			}
		}		
		
		for(int i = 0 ; i < multiplicadora.length ; i++)
		{
			for(int j = 0 ; j < multiplicando[0].length ; j++)
			{
				for(int k = 0 ; k < multiplicando.length ; k++)
				{
					resultado[i][j] += multiplicadora[i][k] * multiplicando[k][j];
				}
			}
		}
		return resultado;
		
	}
	
	public static void printMatriz(double[][] matriz)
	{
		for(int i = 0 ; i < matriz.length ; i++)
			{
				for(int k = 0 ; k < matriz[0].length ; k++)
				{
					System.out.print(matriz[i][k]+ ",");
				}
				System.out.println();
					
			}
	}
	
	public static HashMap<double[][], double[][]> obterMatrizLInversaE_UdaFatoracaoLU(double[][] matriz,double[][] b)
	{
		double[][] U = matriz;
		double[][] temp = new double[0][0];
		for(int i = 0 ; i < matriz.length - 1 ; i++)
		{
			temp = transformadaDeGauss(matriz, i);
			b = multiplicarMatrizes(temp, b);
			matriz = multiplicarMatrizes(temp, matriz);
			temp = matriz;
		}
		System.out.println("Imprimindo matriz U:");
		printMatriz(temp);
		System.out.println("Imprimindo matriz L inversa");
		printMatriz(b);
		
		HashMap<double[][], double[][]> hmap = new HashMap<double[][], double[][]>();
		hmap.put(temp, b);
		return hmap;
	}
	
	
	public static double[][] setIdentidadeToMatriz(double[][] matriz)
	{
		for(int index = 0 ; index < matriz.length ; index++)
		{
			for(int j = 0; j < matriz[0].length ; j++ )
			{
				if(j == index)
				{
					matriz[index][j] = 1;
				}
				else
				{
					matriz[index][j] = 0;
				}
			}
		}
		return matriz;
	}
	
	/**
	 * Algoritmo da folha do Professor.
	 */
	public static void fatoracaoLU(double[][] a,double[][] b,boolean comPivotamentoParcial)
	{
		double[][] u = a;
		double[][] linv = new double[a.length][a[0].length];
		setIdentidadeToMatriz(linv);
		double[][] m = new double[a.length][a[0].length];
		for(int i = 0 ; i < a.length -1 ; i++)
		{
			if(comPivotamentoParcial)
			{
				troca_linha(u);
			}			
			setIdentidadeToMatriz(m);
			for(int j = i + 1 ; j < a.length ; j++)
			{
				m[j][i] = -u[j][i] / u[i][i];
			}
			linv = multiplicarMatrizes(m, linv);
			u = multiplicarMatrizes(m, u);
			b = multiplicarMatrizes(m, b);		
		}
		
		double[][] x = new double[a.length][1];
		
		for(int i = a.length -1 ; i>=0 ; i--)
		{
			x[i][0] = b[i][0];
			for(int j = i + 1; j < a.length ; j++)
			{
				x[i][0] = x[i][0] - u[i][j] * x[j][0];
			}
			x[i][0] = x[i][0] / u[i][i];
		}
		
		System.out.println("Imprimindo linv:");
		printMatriz(linv);
		System.out.println("Imprimindo U");
		printMatriz(u);
		System.out.println("Imprimindo b");
		printMatriz(b);
		System.out.println("Imprimindo x");
		printMatriz(x);
		
	}
	
	/**
	 * Algoritmo do Professor
	 * @param x
	 * @param y
	 * @param u
	 */
	public static void substituicao_regressiva(double[][] y,double[][] u)
	{
		double[][] x = new double[y.length][y[0].length];
		for(int i = x.length - 1; i >= 0 ; i--)
		{
			x[i][0] = y[i][0];
			for(int j = i+1; j < x.length ; j++ )
			{
				x[i][0] = x[i][0] - u[i][j] * x[i][0]; 
			}
			x[i][0] = x[i][0] / u[i][i];
		}
		System.out.println("Imprimindo x:");
		printMatriz(x);
	}
	/**
	 * Algoritmo do Professor.
	 * @param l
	 * @param b
	 */
	public static void substituicao_progressiva(double[][] l, double[][] b)
	{
		double[][] y = new double[b.length][b[0].length];
		for(int i = 0 ; i < y.length ; i++)
		{
			y[i][0] = b[i][0];
			for(int j = 0 ; j < i ; j++)
			{
				y[i][0] = y[i][0] - l[i][j] * y[j][0];
			}
			y[i][0] = y[i][0] / l[i][i];
		}
	}
	
	/**
	 * Algoritmo feito por mim, mas que é do trabalho.
	 * @param matriz
	 * @return
	 */
	public static double[][] troca_linha(double[][] matriz)
	{
		for(int i = 0 ; i < matriz[0].length;i++)
		{
			for(int j = i + 1 ; j < matriz.length ; j++)
			{
				if(matriz[j][i] > matriz[i][i])
				{
					for(int z = 0 ; z < matriz.length ; z++)
					{
						double temp = matriz[j][z];
						matriz[j][z] = matriz[i][z];
						matriz[i][z] = temp;
					}
				}
			}
		}
		printMatriz(matriz);
		return matriz;
	}
	/**
	 * 
	 * @param a
	 * @return
	 */
	public static double[][] cholesky(double[][] a)
	{
		double[][] r = a;
		for(int k=0 ; k < a.length ; k++)
		{
			double primeiroSomatorio = 0;
			for(int p = 0 ; p < k ; p++)
			{
				primeiroSomatorio += Math.pow(r[k][p], 2);
			}
			r[k][k] = Math.sqrt( r[k][k] - primeiroSomatorio );
			printMatriz(r);
			System.out.println();
			for(int i = k + 1 ; i < a.length ; i++)
			{
				double segundoSomatorio = 0;
				for(int p = 0 ; p < k ; p++)
				{
					segundoSomatorio += r[i][p] * r[k][p];
				}
				r[i][k] = (r[i][k] - segundoSomatorio) / r[k][k];
				r[k][i] = r[i][k];
			}			
		}
		printMatriz(r);
		return r;
	}
	
	
	
	
	/**
	 * Algoritmo do Professor
	 * @param a
	 * @return
	 */
	public static double[][] algoritmoPivotacaoParcial(double [][] a)
	{
		int p = 0;
		double[][] w = new double[a.length][1]; 
		for(int k = 0; k < a.length-1 ; k++)
		{
			for(int j = k + 1 ; j < a.length ; j++)
			{
				if(a[j][k] > a[k][k])
				{
					p = j;
				}
			}
			for(int j = k ; j < a.length ; j++)
			{
				double temp = a[k][j];
				a[k][j] = a[p][j];
				a[p][j] = temp;			
			}
			for(int j = k + 1; j < a.length ; j++)
			{
				w[j][0] = a[k][j];				
			}
			for(int i = k + 1 ; i < a.length ; i++)
			{
				double n = a[i][k] / a[k][k];
				a[i][k] = n;
				for(int j = k + 1 ; j < a.length ; j++)
				{
					a[i][j] = a[i][j] - n * w[j][0];					
				}				
			}
		}
		printMatriz(a);
		return a;
	}
	
	
	
	
	
	public static double[][] transformadaDeGauss(double[][] matriz, int level)
	{
		if(level > matriz.length)
		{
			System.out.println("O Level está fora do range da matriz.");
			return null;
		}
		
		double[][] resultado = new double[matriz.length][matriz[0].length];
		for(int i = 0 ; i < matriz.length ; i++)
		{
			for(int j = 0 ; j < matriz[0].length ; j++)
			{
				if(i == j)
				{
					resultado[i][j] = 1;
				}
				else
				{
					resultado[i][j] = 0;
				}
			}
		}
		for(int i = level + 1 ; i < matriz.length ; i++)
		{
			resultado[i][level] = - matriz[i][level] / matriz[level][level];
		}
		return resultado;		
	}
	
	
	public static String divisao_sucessiva(int decimal)
	{
		String binaryRepresentation = "";
		while(decimal != 0)
		{
			int value = decimal % 2;
			binaryRepresentation = String.valueOf(value) + binaryRepresentation;
			decimal = decimal / 2;
		}
		return binaryRepresentation;
	}
	
	public static String multiplicacao_sucessiva(double fracao)
	{
		String binaryRepresentation = "";
		if(fracao < 1)
		{
			binaryRepresentation = ",";
		}
		while(fracao != 0 && binaryRepresentation.length() < 2000)
		{
			fracao = fracao * 2;
			int decimal = (int)fracao;
			binaryRepresentation = binaryRepresentation + String.valueOf(decimal);
			fracao = fracao - decimal;
		}
		return binaryRepresentation;
	}
	
	
	public static int conversaoInteiroBinarioToDecimal(String binaryValue)
	{
		int value = 0;
		for(int i = 0,b = binaryValue.length()-1; i < binaryValue.length() ; i++,b--)
		{
			value = (int) (value + getIntegerFromChar(binaryValue.charAt(i)) * Math.pow(2, b));
		}
		return value;
		
	}
	
	public static int briot_Ruffine_Inteiro(String binaryValue)
	{
		int valor = getIntegerFromChar(binaryValue.charAt(0));
		
		for (int i = 1 ; i < binaryValue.length() ; i++ )
		{
			valor = getIntegerFromChar(binaryValue.charAt(i)) + 2 * valor;			
		}
		return valor;
	}
	
	public static float briot_Ruffine_Fracao(String binaryValue)
	{
		float valor = getIntegerFromChar(binaryValue.charAt(binaryValue.length()-1));
		for(int i = binaryValue.length()-2; i >= 0 ; i--)
		{
			valor = getIntegerFromChar(binaryValue.charAt(i)) + valor/2;
		}
		valor = valor/2;
		return valor;
	}
	
	public static float[] multiplicarMatrizes(
											  float[] multiplicadora, 
											  int nLinhasMultiplicadora, 
											  float[] multiplicado,
						  					  int nLinhasMultiplicado)
	{
		if(multiplicadora.length % nLinhasMultiplicadora != 0 || multiplicado.length % nLinhasMultiplicado != 0 )
		{
			System.out.println("O Tamnho de linhas não condiz com o tamanho da matriz");
		}
		
		int nColunasMultiplicadora = multiplicadora.length/nLinhasMultiplicadora;
		int nColunasMultiplicado = multiplicado.length/nLinhasMultiplicado;
		for(int i = 0 ; i < nLinhasMultiplicadora ; i++)
		{
			for(int j = 0 ; j < nColunasMultiplicadora ; i++ )
			{
				for(int k = 0 ; k < nLinhasMultiplicadora ; i++)
				{
					
				}
			}
		}		
		return null;		
	}
	
	public static float binaryToDecimal(String binaryValue)
	{
		String[] valores = binaryValue.split(",");
		if(valores.length == 1)
		{
			return briot_Ruffine_Inteiro(binaryValue);
		}
		else
		{
			return briot_Ruffine_Inteiro(valores[0].trim()) + briot_Ruffine_Fracao(valores[1].trim());
		}
	}
	
}
