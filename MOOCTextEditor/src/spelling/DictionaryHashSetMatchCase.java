/**
 * 
 */
package spelling;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;


/**
 * A class that implements the Dictionary interface with a HashSet
 */
public class DictionaryHashSetMatchCase implements Dictionary 
{

    private HashSet<String> words;
	
	public DictionaryHashSetMatchCase()
	{
	    words = new HashSet<String>();
	}
	
    /** Add this word to the dictionary.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
	@Override
	public boolean addWord(String word) 
	{
		String firstCap = "";
		if(word.length() > 1) {
		firstCap = word.substring(0, 1).toUpperCase() + word.substring(1);
		}
		boolean retValue;
		
		words.add(word.toUpperCase());
		words.add(word);
		retValue = words.add(firstCap);
		return retValue;
		
	}

	/** Return the number of words in the dictionary */
    @Override
	public int size()
	{
    	 return words.size();
	}
	
	/** Is this a word according to this dictionary? */
    @Override
	public boolean isWord(String s) {
    	return words.contains(s);
	}
	
   
}
