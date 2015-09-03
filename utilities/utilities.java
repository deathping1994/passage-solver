package utilities;
// ankita.wadhwa@jiit.ac.in
public class utilities {

	public static int [] findmax (int []array)
	{
		int max = array[0];
		int index[]=new int [100];
		for ( int i = 1; i < array.length; i++) {
			if ( array[i] > max) {
				max = array[i];
				}
		}
		int c=0;
		for (int l=0;l<index.length;l++)
			index[l]=-1;
		for(int p=0;p<array.length;p++)
		{
			if(array[p]==max)
			{
				index[c]=p;
				c++;
			}
		}
		return index;
	}
	public static String [] extractStr(String txt,int pos[])
	{String ans=new String();
	String matchs[]= new String[100];
	for(int i=0;i<pos.length;i++)
		{
		if (pos[i] == -1)
			{}
		else
        	{   int temp =pos[i];
            	int start = 0;
            	int end=0;
            	char ch=txt.charAt(temp);
            	while(ch != '.' && temp >0)
            	{
//            	System.out.println(temp);
            		temp--;
            		ch=txt.charAt(temp);
            	}
            start=temp;
            temp=pos[i];
            while(txt.charAt(temp) != '.' && temp <txt.length())
            {
//            	System.out.println(temp);
            	temp++;
            }
            end=temp;
            ans= txt.substring(start, end+1);
            matchs[i]=ans;

        }
		}
	return matchs;
	}
	
}
