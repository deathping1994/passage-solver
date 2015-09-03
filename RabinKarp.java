/**
 * 
 */
package passagesolver;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Random;
import java.math.BigInteger;

/**
 * @author gaurav
 *
 */
public class RabinKarp {

	    /** String Pattern **/
	    private String pat; 
	    /** pattern hash value **/    
	    private long patHash;    
	    /** pattern length **/
	    private int M;  
	    /** Large prime **/         
	    private long Q; 
	    /** radix **/         
	    private int R;   
	    /** R^(M-1) % Q **/        
	    private long RM;          
	    int pos[]=new int[20];
	    /** Constructor **/
	    public RabinKarp()
	        {

	        }
	    public int[] RK(String txt, String pat) 
	    {
	        this.pat = pat;      
	        R = 256;
	        M = pat.length();
	        Q = longRandomPrime();
	        /** precompute R^(M-1) % Q for use in removing leading digit **/
	        RM = 1;
	        for (int i = 1; i <= M-1; i++)
	           RM = (R * RM) % Q;
	        patHash = hash(pat, M);
	        int pos[] = search(txt);
	        return pos;
	    } 
	    /** Compute hash **/
	    public long hash(String key, int M)
	    { 
	        long h = 0; 
	        for (int j = 0; j < M; j++) 
	            h = (R * h + key.charAt(j)) % Q; 
	        return h; 
	    } 
	    /** Funtion check **/
	    public boolean check(String txt, int i) 
	    {
	        for (int j = 0; j < M; j++) 
	            if (pat.charAt(j) != txt.charAt(i + j)) 
	                return false; 
	        return true;
	    }
	    /** Funtion to check for exact match**/
	    public int[] search(String txt) 
	    {int flag=0;
	    for(int i=0;i<20;i++)
	    	pos[i]=-1;
	        int N = txt.length(); 
	        if (N < M) return pos;
	        long txtHash = hash(txt, M); 
	        /** check for match at start **/
	        if ((patHash == txtHash) && check(txt, 0))
	            pos[0]=0;
	        /** check for hash match. if hash match then check for exact match**/
	        for (int i = M; i < N; i++) 
	        {
	            // Remove leading digit, add trailing digit, check for match. 
	            txtHash = (txtHash + Q - RM * txt.charAt(i - M) % Q) % Q; 
	            txtHash = (txtHash * R + txt.charAt(i)) % Q; 
	            // match
	            int offset = i - M + 1;
	            if ((patHash == txtHash) && check(txt, offset))
	                {
	                pos[flag]=offset;
	                flag++;
	                }
	        }
	        
	        /** no match **/
	        	return pos;
	    }
	    /** generate a random 31 bit prime **/
	    public static long longRandomPrime() 
	    {
	        BigInteger prime = BigInteger.probablePrime(31, new Random());
	        return prime.longValue();
	    }
	    /** Main Function **/
//	     public static void main(String[] args) throws IOException
//	     {    
//	         RabinKarp rk = new RabinKarp(text, pattern);        
//	     }
	}

