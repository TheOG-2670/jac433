/*
 * Name: Omri Golan
 * Program description: Check whether a given string is a palindrome
 * Lab: Workshop 1
 * File: Palindrome.java
 * Date: 9/17/2019
 */


//This class receives a command line string, stores it in a Stack object and determines whether i
public class Palindrome
{
	private static int wordLength = 0;
	
	//reverses order of characters in first Stack object and stores in second Stack object
	public Stack wordReverse(Stack readForward, int size)
	{
		wordLength = size;
		Stack readBackward = new Stack();
		
		//returns 'top' element in 'readForward' object to be pushed into
		//'readbackward' stack object and then removes it
		for (int i = size-1; i >=0 ; i--)
		{
			readBackward.push(readForward.pop());
		}
		
		return readBackward;
	}
	
	//compares elements of each Stack object to check if they are identical
	public boolean isPalindrome(Stack forwardStack, Stack backwardStack)
	{
		int letterCount = 0;
		for (int i=0; i < wordLength; i++)
		{
			//return topremove 'top' element from stack
			if (forwardStack.pop() == backwardStack.pop())
			{
				
				//increase counter on matching elements
				letterCount++;
			}
		}
		
		//if the count is equal to the length of the string array (original word), the word 
		//is a palindrome
		if (letterCount == wordLength)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//prints whether word is a palindrome or not in columnar layout
	public final void display(boolean palindromeStatus, String word)
	{
			//prints palindrome object word
			System.out.print(word);
			
			
			//prints specific number of spaces for columnar output between word and
			//palindrome status
			for (int j=0; j < (18 - wordLength); j++)
			{
					System.out.print(" ");	
			}
			
			//checks palindrome status
			if (palindromeStatus)
			{
				System.out.print(" Yes");
			}
			else
			{
				System.out.print(" No");
			}
			
			//newline for next row
			System.out.println();
		
	}
	
	public static void main(String[] args) 
	{
		System.out.println("Word\t\tPalindrome?");
		System.out.println("---------------------------");
		
		Palindrome palindrome = new Palindrome();
		
		for(int i=0; i < args.length; i++)
		{
			//grab word from command line argument
			String word = args[i];
			//convert word to character array
			char[] wordArray = word.toCharArray();
			//create stack objects to store forward-reading and backward-reading
			//word
			Stack readForward = new Stack(),
				  readForwardCopy = new Stack(),
				  readBackward = new Stack();
			//store boolean value whether word is a palindrome
			boolean palindromeStatus = false;
			
			//copies each character of word into stack objects
			for (int j=0; j < args[i].length(); j++)
			{
				//used for display function
				readForward.push(wordArray[j]);
				//used for word reversal function
				readForwardCopy.push(wordArray[j]);
			}
			
			//store reversed word in stack object
			readBackward = palindrome.wordReverse(readForwardCopy, wordArray.length);
			//store word's palindrome status
			palindromeStatus = palindrome.isPalindrome(readForward, readBackward);
			//display whether word is palindrome
			palindrome.display(palindromeStatus, word);
		}
		
	}

}
