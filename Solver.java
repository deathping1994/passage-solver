/**
 * 
 */
package passagesolver;

/**
 * @author gaurav
 *
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import utilities.utilities;
public class Solver {

	
	public static String rmQm(String str)
	{
		str=str.replace("?","");
	    str=str.replace("what", "");
	    str=str.replace("which","");
	    str=str.replace("why","");
	    str=str.replace("how","");
	    str=str.replace("when","");
	    str=str.replace("in which","");
	    str=str.replace("are","");
	    str=str.replace("is","");
	    str=str.replace("are","");
	    str=str.replace("of","");
	    str=str.replace("the","");
	    str=str.replace("in","");
	    str=str.replace("do","");
	    str=str.replace("did","");
	    
		return str;
	}

	

	//public static 
	public static void main(String[] args) {
	System.out.println("Enter your choice of algorithm");
	System.out.println("1.Knuth Morris Pratt\n2.Rabin Karp\n3.Finite State Automata\n4.Naive");
	Scanner input = new Scanner(System.in);
	char ch = input.next().charAt(0);
	input.nextLine();
	System.out.println("Enter Paragraph and questions seperated by new line");
			String para = input.nextLine();
			para=para.toLowerCase();
			para=para.replaceAll("[.]", "01PP10");
			para=para.replaceAll("\\p{Punct}", "");
		    para=para.replaceAll("01PP10", " . ");
	
		    
//		    System.out.println("output string");
		   // System.out.println("\n" +para);
		    String[] ans = new String[5];
			String []ques =new String [10];
			long ttime=0;
			int iter=0;
			for(int j=0;j<5;j++)
			{		
			ques[j] = input.nextLine();
			ques[j]=rmQm(ques[j].toLowerCase());
			System.out.println(ques[j]);
			StringTokenizer st = new StringTokenizer(ques[j]," ");
			int i=0;
			String []answers=new String [100] ;
			int count[]=new int[100];
			int ans_size=0;
			while(st.hasMoreTokens())
				{
				
					String token=new String(st.nextToken());
					token =token.toLowerCase();
					int poss_ans[]=new int [100];
					long startTime = System.nanoTime();

					if (ch=='2')
					{
					RabinKarp rk = new RabinKarp();
	            	poss_ans=rk.RK(para,token);
					}
					else if(ch=='1')
					{
					KnuthMorrisPratt kmp =new KnuthMorrisPratt();
					poss_ans=kmp.KMP(para,token);
					}
					else if(ch=='3')
					{//KnuthMorrisPratt kmp =new KnuthMorrisPratt();
					//poss_ans=kmp.KMP(para,token);
					
					
					Fsm fsm=new Fsm();
            	poss_ans=fsm.search(token, para);
					}
					else if(ch=='4')
					{
					naive nv=new naive();
					poss_ans=nv.search(para,token);
					}
					

					long endTime = System.nanoTime();

					long duration = endTime - startTime;
					ttime+=duration;
					iter++;
					String matchs[]=utilities.extractStr(para,poss_ans);
//	            	System.out.println(rk.RK(para,st.nextToken()).toString());
//	            	for(int a=0;a<matchs.length;a++)
//	            	{	if(matchs[a]!=null)
//	            		//System.out.println(matchs[a]+"for token"+token);
//	            	}
	            	if(i==0)
						{for(int l=0;l<poss_ans.length;l++)
	            		if(matchs[l]!=null)
							{answers[ans_size]=new String(matchs[l]);
							count[ans_size]= new Integer(0);
							ans_size++;}
						}
					else
					{for(int z=0;z<matchs.length;z++)
						{ if( matchs[z]!=null)
							{int temp=ans_size;
							int flag=0;
							for(int k=0;k<temp;k++)
								{ 
								if(answers[k]!=null)
									{	//System.out.println(answers[k]+" check "+matchs[z]);
									if(answers[k].equalsIgnoreCase(matchs[z]))
											{//System.out.println(answers[k]+" equals "+matchs[z]);
											count[k]++;
											k=temp;
											flag=1;
											}
									}
								}
							if(flag==0)
							{
								answers[ans_size]=new String(matchs[z]);
								count[ans_size]= new Integer(0);
								ans_size++;
							}
							}
						}
					}
	            	i++;
	        
				}
			System.out.println("Answers for "+ j+" th question");
			int index[]= utilities.findmax(count);
			for(int z=0;z<index.length;z++)
			{if(index[z]!=-1 && answers[index[z]]!=null)
				{System.out.println(answers[index[z]]);}

			}
		

			}

			
//			int i=0;
//			
//			StringTokenizer st1 = new StringTokenizer(para,".");
//			ArrayList <String> ar = new ArrayList <String>();
//			while(st1.hasMoreTokens())
//			{
//				ar.add(st1.nextToken());
//			}
//			int len_of_lines=ar.size();
//			
//			int[][] aseq= new int[5][len_of_lines];
//			int[][] qseq= new int[5][len_of_lines];
//			for(int j=0;j<5;j++)
//			{
//				for(int k=0;k<len_of_lines;k++)
//				{
//					if(ar.get(k).toLowerCase().contains(ques[j].toLowerCase()))
//					{
//						aseq[j][k]=999;
//					}
//					else  
//					{
//						
//					
//				       ArrayList <String> q_word = new ArrayList <String>();
//				       StringTokenizer st2= new StringTokenizer(ques[j]);
//				       while(st2.hasMoreTokens())
//				        {
//					     q_word.add(st2.nextToken());
//				        }
//			
//				       int count_word=q_word.size();
//				       for(int a=0;a<count_word;a++)
//				        {
//				    	   if(ar.get(k).toLowerCase().contains(q_word.get(a).toLowerCase()))
//				    	   {
//				    		   aseq[j][k]+=1;
//				    	   }
//				    	   if(a<count_word-1)
//				    	   {
//				    	   String word_2=q_word.get(a)+" "+q_word.get(a+1);
//				    	   
//				    	      if(ar.get(k).toLowerCase().contains(word_2.toLowerCase()))
//				    	      {
//				    		   aseq[j][k]+=3;
//				    	       }
//				            }
//				    	   if(a<count_word-2)
//				    	   {
//				    		   String word_3=q_word.get(a)+" "+q_word.get(a+1)+" "+q_word.get(a+2);
//					    	   if(ar.get(k).toLowerCase().contains(word_3.toLowerCase()))
//					    	   {
//					    		   aseq[j][k]+=5;
//					    	   }  
//				    	   }
//				    	   
//				        }
//			       }
//				}
//			}
//			for(int j=0;j<5;j++)
//			{
//				for(int k=0;k<len_of_lines;k++)
//				{
//					if(ar.get(k).toLowerCase().contains(ans[j].toLowerCase()))
//					{
//						qseq[j][k]=999;
//					}
//					else  
//					{
//						
//					
//				       ArrayList <String> a_word = new ArrayList <String>();
//				       StringTokenizer st2= new StringTokenizer(ans[j]);
//				       while(st2.hasMoreTokens())
//				        {
//					     a_word.add(st2.nextToken());
//				        }
//			
//				       int count_word=a_word.size();
//				       for(int a=0;a<count_word;a++)
//				        {
//				    	   if(ar.get(k).toLowerCase().contains(a_word.get(a).toLowerCase()))
//				    	   {
//				    		   qseq[j][k]+=1;
//				    	   }
//				    	   if(a<count_word-1)
//				    	   {
//				    		   
//				    	   
//				    	   String word_2=a_word.get(a)+" "+a_word.get(a+1);
//				    	   if(ar.get(k).toLowerCase().contains(word_2.toLowerCase()))
//				    	   {
//				    		   qseq[j][k]+=3;
//				    	   }
//				    	   }
//				    	   if(a<count_word-2)
//				    	   {
//				    		   String word_3=a_word.get(a)+" "+a_word.get(a+1)+" "+a_word.get(a+2);
//					    	   if(ar.get(k).toLowerCase().contains(word_3.toLowerCase()))
//					    	   {
//					    		   qseq[j][k]+=5;
//					    	   }  
//				    	   }
//				    	   
//				        }
//			       }
//				}
//			}
//			
//			int[] q_line = new int[5];
//			int[] a_line = new int[5];
//			int[] line  = new int[len_of_lines];
//			for(int j=0;j<5;j++)
//			{
//				for(int k=0;k<len_of_lines;k++)
//				{
//			for(int j=0;j<5;j++)
//			{
//				for(int k=0;k<len_of_lines;k++)
//				{
//					line[k]=qseq[j][k];
//				{
//					
//					vacant_ans=j;
//	
//				}
//			}
//     int[] flag={0,0,0,0,0};
//		
//		for(int j=0;j<5;j++)
//		{
//			for(int k=0;k<5;k++)
//			{
//				if(ans[j].equalsIgnoreCase(final_ans[k]))
//				{
//					flag[j]=1;
//				}
//			}
//			if(flag[j]==0)
//			{
//				final_ans[vacant_ans]=ans[j];
//			
//			}
//		}
//		
//			for(int j=0;j<5;j++)
//				System.out.println(final_ans[j]);
			input.close();
			
			System.out.println("Average time taken: "+ ttime/iter);
	}


}
