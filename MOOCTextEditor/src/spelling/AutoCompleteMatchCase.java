package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		
		String firstCap = "";
		if(word.length() > 1) {
		firstCap = word.substring(0, 1).toUpperCase() + word.substring(1);
		}
		boolean retValue;
		
		addNode(word.toUpperCase());
		addNode(word);
		retValue = addNode(firstCap);
		return retValue;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		
		int wordSize = s.length();
		TrieNode prevNode = root;
		TrieNode curNode;
		
		for (int i = 0; i < wordSize; i++){
			char c = s.charAt(i);
			curNode = prevNode.getChild(c);
			
			if(curNode == null) {
				return false;
			}
			if(curNode.getText().equals(s) && curNode.endsWord() == true) {
				return true;
			}
			prevNode = curNode;
			
		}
	    return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 

    	 List<String> compList = new ArrayList<String>();


    	 TrieNode firstNode = new TrieNode();
    	 firstNode = root;

		 int i = 0;
		 while(i < prefix.length()) {
			 try {
				 firstNode = firstNode.getChild(prefix.charAt(i));
			 }
			 catch (Exception e) {
				 firstNode = null;
		     }
			 i++;
		 }
    	 
    	 if(firstNode == null || numCompletions == 0) {
    		 return compList;
    	 }
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 queue.add(firstNode);
    	 while (queue.size() > 0 && compList.size() < numCompletions) {
    		 TrieNode curNode = queue.remove();
    		 if(curNode.endsWord()) {
    			 compList.add(curNode.getText());
    		 }
    		 for (char c: curNode.getValidNextCharacters()) {
    			 queue.add(curNode.getChild(c));
    		 }
    	 }
    	 
         return compList;
     }

     
 	private boolean addNode(String word)
 	{
 	    //TODO: Implement this method.
 		
 		int wordSize = word.length();
 		TrieNode prevNode = root;
 		TrieNode curNode;
 		
 		for (int i = 0; i < wordSize; i++){
 			char c = word.charAt(i);
 			curNode = prevNode.insert(c);
 			
 			if(curNode == null) {
 				curNode = prevNode.getChild(c);
 			}
 			if(curNode.getText().equals(word) && curNode.endsWord() == false) {
 				curNode.setEndsWord(true);
 				this.size = size + 1;
 				return true;
 			}
 			prevNode = curNode;
 			
 		}
 	    return false;
 	}
     
 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	
}