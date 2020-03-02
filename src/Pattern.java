import java.util.Scanner;

public class Pattern {
	
	public static int[] BruteForce(String txt, String pat)
	{ 
		int arr[] = new int[2];
		int a = txt.length();
		int b = pat.length();
		int c = 0;
		for (int i = 0; i <= a - b; i++)
		{
			int j = 0;
			while (j < b) 
			{
				c++;
				if (txt.charAt(i + j) == pat.charAt(j)) 
				{
					j++;
				}
				else
				{
					break;
				}
				if (j == b) {
					arr[0] = i;
					arr[1] = c;
					return arr;
				}
			}
		}
		return new int[] {-1,c};
	}
	
	
	public static int[] BadSymbol(String pat) { 
		int b = pat.length();
		int table[] = new int[255];
		for (int i = 0; i < 255; i++) {
			table[i] = b;
		}
		for (int j = 0; j < b - 1; j++) {
			table[pat.charAt(j)] = b - 1 - j;
		}
		return table;
	}
	
	public static int[] BMHorspoolMatching(String txt, String pat) { 
		int[] position_and_comparisions = new int[2];
		int c = 0;
		int[] table = BadSymbol(pat);
		int m = pat.length();
		int n = txt.length();
		int i = m - 1;
		while (i < n) {
			int k = 0;
			while (k < m) {
				c++;
				if (pat.charAt(m - 1 - k) != txt.charAt(i - k)) 
				{
					break;
				}
				else 
				{
					k++;
				}
			}
			if (k != m)
			{
				i = i + table[txt.charAt(i)];
			}
			else
			{
				position_and_comparisions[0] = i - m + 1;
				position_and_comparisions[1] = c;
				return position_and_comparisions;
			}
		}
		return new int[] { -1, c };
	}
	
	public static int[] FailureFunction(String pat) { 
		int m = pat.length();
		int i = 1;
		int j = 0;
		int[] failure = new int[m];
		failure[0] = 0;
		while (i < m) {
			if (pat.charAt(j) == pat.charAt(i)) 
			{
				failure[i] = j + 1;
				i++;
				j++;
			} 
			else if (j > 0)
			{
				j = failure[j - 1];
			} 
			else 
			{
				failure[i] = 0;
				i++;
			}
		}
		return failure;
	}

	public static int[] KMPMatch(String txt, String pat) { //KMP matching method
		int[] failure = FailureFunction(pat);
		int[] position_and_comparisions = new int[2];
		int i = 0;
		int j = 0;
		int n = txt.length();
		int m = pat.length();
		int c = 0;
		while (i < n) {
			c++;
			if (pat.charAt(j) == txt.charAt(i)) 
			{
				if (j == m - 1)
				{
					position_and_comparisions[0] = i - m + 1;
					position_and_comparisions[1] = c;
					return position_and_comparisions;
				}
				i++;
				j++;
			} 
			else if (j > 0) 
			{
				j = failure[j - 1];
			} 
			else 
			{
				i++;
			}
		}
		return new int[] { -1, c };
	}

	public static void main(String[] args)
	{	
		String text = "CBBAABAABBCABAAABBBABBAAB";
		String pattern = "ABBAAB";
		Scanner scan=new Scanner(System.in);
		System.out.println("Select the type of Pattern Matching \n 1:Brute Force \n 2:Boyer Moore Horspool \n 3:KMP ");
		int userInput= scan.nextInt();
		switch(userInput)
		{
		case 1:
			int[] BruteForce = BruteForce(text,pattern);
			System.out.println("\n Text :"+text+", Pattern :"+pattern); 
			System.out.print("\t Position: "+BruteForce[0]+", "+"Comparison: "+BruteForce[1]);
			break;
		case 2:
			int[] BMHorspool = BMHorspoolMatching(text, pattern);
			System.out.println("\n Text :"+text+", Pattern :"+pattern);
			System.out.print("\tposition: "+BMHorspool[0]+", "+"comparison: "+BMHorspool[1]);
			break;
		case 3:
			int[] KMP = KMPMatch(text, pattern);
			
			System.out.println("\n Text :"+text+", Pattern :"+pattern); 
			System.out.print("\t position: "+KMP[0]+", "+"comparison: "+KMP[1]);
			break;
		}
		
		
	}

}
