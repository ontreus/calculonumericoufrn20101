
public class CN2Unidade {
	
	public double erro = 0.0000000000001;
	
	public CN2Unidade()
	{
		
	}
	
	public double f(double x)
	{
		return x;
	}
	
	public Pair<Double,Double> trocaDeSinal(double x,double d)
	{
		while ( f(x) * f(x+d) > 0 )
		{
			x = x + d;
		}
		return new Pair<Double,Double>(x,x+d);
	}
	
	public double lagrange(double n,double k, double b, double an)
	{
		double pow = n - k;
		double basis = b/an;
		return 1 + Math.pow(basis, 1/pow);		
	}
	
	public boolean condicaoDeParada(double erro,double x)
	{
		if(f(x) < erro)
		{
			return true;
		}
		return false;
	}
	
	
	public double bissecao(double a,double b)
	{
		double inf = a;
		double sup = b;
		double med;
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
		}
		while (!condicaoDeParada(erro, med));
		return med;
	}
	
	public double corda_simples(double a,double b)
	{
		double inf = a;
		double sup = b;
		double cn;
		do
		{
			cn = inf - ((sup - inf)/f(sup) - f(inf)) * f(inf);
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
		}
		while (!condicaoDeParada(erro, cn));
		return cn;
	}
	
	public double g(double x)
	{
		return x;
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
		}
		while(!condicaoDeParada(erro,xap) && i < 2000);
		return xap;		
	}
	
	public double fLinha(double x)
	{
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
		}
		while(!condicaoDeParada(erro, x) && i < 2000);		
		return x;
	}
	

}
