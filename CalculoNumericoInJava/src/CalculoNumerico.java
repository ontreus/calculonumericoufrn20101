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
