package passagesolver;

import java.util.Scanner;

public class naive {
	public int[] search(String txt,String pat)
	{	int pos[]=new int [100];
		int c=0;
		for(int i=0;i<100;i++)
		{
			pos[i]=-1;
		}
		for(int j=0;j<txt.length()-pat.length();j++)
		{
			String temp=txt.substring(j, j+pat.length());
			if (temp.equalsIgnoreCase(pat))
			{
				pos[c]=j;
				c++;
			}
		}
		return pos;
	}

}
