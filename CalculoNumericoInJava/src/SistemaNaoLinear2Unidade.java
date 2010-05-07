import Jama.Matrix;
import Jama.util.Maths;

public class SistemaNaoLinear2Unidade {
	
	public static boolean condicaoDeParada(double[][] matriz,double erro)
	{
		return false;
	}
	
		
	
	
	
	public static double[][] inversaDaMatriz(double[][] matriz)
	{
		Matrix m = new Matrix(matriz);
		m = m.inverse();
		matriz = m.getArray();
		return matriz;
	}
	
	public double[][] F(double[][] x)
	{
		return x;
	}
	
	public double[][] subtrairMatrizes(double[][] a,double[][] b)
	{
		double [][] result = new double[a.length][a[0].length];
		for(int i = 0 ; i < a.length ; i++)
		{
			for(int j = 0 ; j < a[0].length ; j++)
			{
				result[i][j] = a[i][j] - b[i][j];
			}
		}
		return result;
	}	
	
	public static double[] multiplicarMatrizporVetor(double[][] matriz,double[] vetor)
	{
		double[][] vetorMatriz = new double[vetor.length][1];
		for(int i = 0 ; i < vetor.length ; i++)
		{
			vetorMatriz[i][0] = vetor[i];
		}
		vetorMatriz = CalculoNumerico.multiplicarMatrizes(matriz, vetorMatriz);
		for(int i = 0 ; i < vetor.length ; i++)
		{
			vetor[i] = vetorMatriz[i][0];
		}
		return vetor;
	}
	
	public static double[][] criarMatrizSDeVetor(double[] vetor)
	{
		double[][] vetorMatriz = new double[vetor.length][1];
		for(int i = 0 ; i < vetor.length ; i++)
		{
			vetorMatriz[i][0] = vetor[i];
		}
		return vetorMatriz;
	}
	
	public static double[][] dividirMatrizPorNumero(double[][] matriz,double n)
	{
		double[][] result = new double[matriz.length][matriz[0].length];
		for(int i = 0 ; i < matriz.length ; i++)
		{
			for(int j = 0 ; j< matriz[0].length ; j++)
			{
				result[i][j] = matriz[i][j]/n;
			}
		}
		return result;
	}
	
	public static double[][] transpostaDaMatriz(double[][] matriz)
	{
		double[][] result = new double[matriz[0].length][matriz.length];
		for(int i = 0 ; i < matriz.length ; i++)
		{
			for(int j = 0 ; j < matriz[0].length ; j++)
			{
				result[j][i] = matriz[i][j];
			}
		}
		return result;
	}
	
	public static double[][] somarMatrizes(double[][] a,double[][] b)
	{
		double[][] result = new double[a.length][a[0].length];
		for(int i = 0 ; i < a.length ; i++ )
		{
			for(int j = 0 ; j < a[0].length ; j++)
			{
				result[i][j] = a[i][j] + b[i][j];
			}
		}
		return result;
	}
	
	public double[][] quaseNewton(double[][] x,double erro)
	{
		double[][] B = new double[x.length][x.length];
		CalculoNumerico.setIdentidadeToMatriz(B);
		double[][] xqn,y,s,strans,u,Binversa,sT;
		double[][] dividendo;
		double divisor;
		do
		{
			Binversa = inversaDaMatriz(B);
			xqn = subtrairMatrizes(x,CalculoNumerico.multiplicarMatrizes(Binversa,F(x)));
			y = subtrairMatrizes(F(xqn),F(x));
			s = subtrairMatrizes(xqn, x);
			sT = transpostaDaMatriz(s);
			u = dividirMatrizPorNumero
				(subtrairMatrizes
				(y, CalculoNumerico.multiplicarMatrizes(B, s)),CalculoNumerico.multiplicarMatrizes(sT,s)[0][0]);
			dividendo = CalculoNumerico.multiplicarMatrizes
						(CalculoNumerico.multiplicarMatrizes
						(CalculoNumerico.multiplicarMatrizes(Binversa, u),sT),Binversa);
			divisor = 1 + CalculoNumerico.multiplicarMatrizes(CalculoNumerico.multiplicarMatrizes(sT, Binversa),u)[0][0];
			Binversa = subtrairMatrizes(Binversa, dividirMatrizPorNumero(dividendo, divisor));
			B = somarMatrizes(B,CalculoNumerico.multiplicarMatrizes(u, sT));
			x = xqn;
		}
		while(condicaoDeParada(x, 0.000001));
		return x;
	}
	
}
