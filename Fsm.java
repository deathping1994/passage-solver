package passagesolver;
import java.util.Scanner;

public class Fsm
{
    public static final int NO_OF_CHARS = 256;
 
    public static int getNextState(String pat, int M, int state, int x)
    {
        /*
         * If the character c is same as next character in pattern,
         * then simply increment state
         */
        if (state < M && x == pat.charAt(state))
            return state + 1;
        int ns, i;
        /*
         * ns stores the result which is next state
         * ns finally contains the longest prefix which is also suffix
         * in "pat[0..state-1]c"
         * Start from the largest possible value and stop when you find
         * a prefix which is also suffix
         */
        for (ns = state; ns > 0; ns--)
        {
            if (pat.charAt(ns - 1) == x)
            {
                for (i = 0; i < ns - 1; i++)
                {
                    if (pat.charAt(i) != pat.charAt(state - ns + 1 + i))
                        break;
                }
                if (i == ns - 1)
                    return ns;
            }
        }
        return 0;
    }
 
    /*
     * This function builds the TF table which represents Finite Automata for a
     * given pattern
     */
    public static void computeTF(String pat, int M, int[][] TF)
    {
        int state, x;
        for (state = 0; state <= M; ++state)
            for (x = 0; x < NO_OF_CHARS; ++x)
                TF[state][x] = getNextState(pat, M, state, x);
    }
 
    /*
     * Prints all occurrences of pat in txt
     */
    public static int[] search(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();
        int[][] TF = new int[M + 1][NO_OF_CHARS];
        computeTF(pat, M, TF);
        // Process txt over FA.
        int i, state = 0;
        int c=0;
        int pos[]=new int [100];
        for(int k=0;k<100;k++)
        	pos[k]=-1;
        for (i = 0; i < N; i++)
        {//System.out.print(txt.charAt(i));
            state = TF[state][txt.charAt(i)];
            if (state == M)
            {
                pos[c]= (i - M + 1);
                c++;
            }
        }
        return pos;
    }
 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the main string: ");
        String main = sc.nextLine();
        System.out.println("Enter the pattern string: ");
        String pattern = sc.nextLine();
        int p[]=search(pattern, main);
        for(int l=0;l<100;l++)
        {	if(p[l]!=-1)
        	System.out.println(p[l]);
        }
        	sc.close();
    }
}


