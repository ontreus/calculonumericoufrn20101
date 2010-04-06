import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author Vitor
 *
 */
public class CalculoNumerico {

	private static int getIntegerFromChar(char i)
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
	
	public static String decimalToBinary(double decimal)
	{
		String leftSide = divisao_sucessiva((int)decimal);
		String rightSide = multiplicacao_sucessiva(decimal - (int)decimal);
		return leftSide +","+ rightSide;
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
	
//	public static double[][] encontrarAInversa(double[][] l,double[][] u)
//	{
//		double[][] id1 = {{1},{0},{0}};
//		double[][] y1 = substituicao_progressiva(l, id1);
//		double[][] x1 = substituicao_regressiva(y1, u);
//		double[][] id2 = {{0},{1},{0}};
//		double[][] y2 = multiplicarMatrizes(l, id2);
//		double[][] x2 = substituicao_regressiva(y2, u);
//		double[][] id3 = {{0},{0},{1}};
//		double[][] y3 = multiplicarMatrizes(l, id3);
//		double[][] x3 = substituicao_regressiva(y3, u);
//		double[][] result = new double[l.length][l.length];
//		result[0][0] = x1[0][0];
//		result[1][0] = x1[1][0];
//		result[2][0] = x1[2][0];
//		result[0][1] = x2[0][0];
//		result[1][1] = x2[1][0];
//		result[2][1] = x2[2][0];
//		result[0][2] = x3[0][0];
//		result[1][2] = x3[1][0];
//		result[2][2] = x3[2][0];
//		return result;
//	}
	
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
	
    private static double[][] setIdentidadeToMatriz(double[][] matriz)
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
	
    public static double[][] createMatriz(String elements, int numberOfLines)
    {
    	String[] itens = elements.split(",");
    	if(itens.length % numberOfLines != 0)
    	{
    		System.out.println("A quantidade de elementos é incompatível com uma matriz");
    		return null;
    	}
    	int numberOfCols = itens.length / numberOfLines;
    	double[][] matriz = new double[numberOfLines][numberOfCols];
    	int l=0,c=0;
    	for(int i = 0 ; i < itens.length ; i++)
    	{
    		matriz[l][c] = Double.valueOf(itens[i].trim());
    		if((c + 1) % numberOfCols == 0)
    		{
    			c = 0;
    			++l;
    		}
    		else
    		{
    			++c;
    		}    		
    	}
    	System.out.println("Imprinindo a matriz criada");
    	printMatriz(matriz);
    	return matriz;
    }
    
    
	/**
	 * Algoritmo da folha do Professor.
	 */
	public static double[][] fatoracaoLU(double[][] a,double[][] b,boolean comPivotamentoParcial)
	{
		double[][] u = a;
		double[][] linv = new double[a.length][a[0].length];
		linv = setIdentidadeToMatriz(linv);
		double[][] m = new double[a.length][a[0].length];
		for(int i = 0 ; i < a.length -1 ; i++)
		{
			if(comPivotamentoParcial)
			{
				ArrayList<double[][]> AeB = troca_linha(u,b);
				u = AeB.get(0);
				b = AeB.get(1);
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
		return x;
		
	}
	
	/**
	 * Algoritmo do Professor
	 * @param x
	 * @param y
	 * @param u
	 */
	public static double[][] substituicao_regressiva(double[][] y,double[][] u)
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
		return x;
	}
	/**
	 * Algoritmo do Professor.
	 * @param l
	 * @param b
	 */
	public static double[][] substituicao_progressiva(double[][] l, double[][] b)
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
		return y;
	}
	
	/**
	 * Algoritmo feito por mim, mas que é do trabalho. Precisa ser recorrigido
	 * @param matriz
	 * @return
	 */
	public static ArrayList<double[][]> troca_linha(double[][] matriz,double[][] b)
	{
		System.out.println("Imprimindo  matriz antes de troca linha");
		printMatriz(matriz);
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
					for(int z = 0 ; z < b.length ; z++)
					{
						double temp = b[j][0];
						b[j][0] = b[i][0];
						b[i][0] = temp;
					}
				}
			}
		}
		System.out.println("Imprimindo a matriz depois de troca linha");
		printMatriz(matriz);
		ArrayList<double[][]> AeB = new ArrayList<double[][]>();
		AeB.add(matriz);
		AeB.add(b);
		return AeB;
	}
	/**
	 * Algoritmo do Professor
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
	 * Algoritmo do trabalho
	 * @param decimal
	 * @return
	 */
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
	
	/**
	 * Algoritmo do Trabalho
	 * @param fracao
	 * @return
	 */
	public static String multiplicacao_sucessiva(double fracao)
	{
		String binaryRepresentation = "";
		if(fracao == 0)
		{
			return "0";
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
	
	
//	public static int conversaoInteiroBinarioToDecimal(String binaryValue)
//	{
//		int value = 0;
//		for(int i = 0,b = binaryValue.length()-1; i < binaryValue.length() ; i++,b--)
//		{
//			value = (int) (value + getIntegerFromChar(binaryValue.charAt(i)) * Math.pow(2, b));
//		}
//		return value;
//		
//	}
	
	public static int briot_Ruffine_Inteiro(String binaryValue)
	{
		int valor = getIntegerFromChar(binaryValue.charAt(0));
		
		for (int i = 1 ; i < binaryValue.length() ; i++ )
		{
			valor = getIntegerFromChar(binaryValue.charAt(i)) + 2 * valor;			
		}
		return valor;
	}
	
	/**
	 * Algoritmo do Trabalho
	 * @param binaryValue
	 * @return
	 */
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
	
	/**
	 * Algoritmo para o Trabalho
	 * @param binaryValue
	 * @return
	 */
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
