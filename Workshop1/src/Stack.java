/*
 * Name: Omri Golan
 * Program description: Check whether a given string is a palindrome
 * Lab: Workshop 1
 * File: Stack.java
 * Date: 9/17/2019
 */


//This class creates and manages dynamically allocated arrays
public class Stack 
{
	//initialization of stack object's character array
	private char[] letterArray = new char[1];
	
	//push characters into 'word' string array
	public void push(char character)
	{
		//temporary array for holding first array's elements (resizing first array)
		char[] temp = new char[letterArray.length];
		
		//loops through 'word' string array and either adds new character to end if space is available or 
		//resizes array
		for (int i=0; i < letterArray.length; i++)
		{
			if (letterArray[i] == '\u0000')
			{
				letterArray[i] = character;
			}
			else if (i == letterArray.length-1)
			{
				for (int j=0; j < letterArray.length; j++)
				{
					temp[j] = letterArray[j];
				}
				
				letterArray = new char[letterArray.length+1];
				
				for (int j=0; j < temp.length; j++)
				{
					letterArray[j] = temp[j];
				}
				
			}
			
		}
	}
	
	//removes last element
	public char pop()
	{
		char lastLetter = '\u0000';
		char[] temp = new char[letterArray.length];
		
		for (int i=0; i < temp.length; i++)
		{
			temp[i] = letterArray[i];
		}
		
		lastLetter = temp[temp.length-1];
		letterArray = new char[temp.length-1];
		
		for (int i=0; i < letterArray.length; i++)
		{
			letterArray[i] = temp[i];
		}
		
		return lastLetter;
	}

}
