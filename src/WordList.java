import java.util.ArrayList;

/**
 * 
 */

/**
 * @author segarci & albamig
 *
 */
public class WordList {
	private String word;
	private ArrayList<Integer> positions;
	private ArrayList<WordList> wordLists;
	private int numWords;
	
	public WordList(String word, ArrayList<Integer> positions, ArrayList<WordList> wordLists, int numwords){
		this.word = word;
		this.positions = positions;
		this.wordLists = wordLists;
		this.numWords = numwords;
	}
	
    /**
     * @param word caracter al que representa el objeto
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @param positions posiciones del texto fuente en que se encuentra el caracter
     */
    public void setPositions(ArrayList<Integer> positions) {
        this.positions = positions;
    }

    /**
     * @param wordLists 
     */
    public void setWordLists(ArrayList<WordList> wordLists) {
        this.wordLists = wordLists;
    }

    /**
     *
     * @param numWords
     */
    public void setNumWords(int numWords) {
        this.numWords = numWords;
    }

    /**
     *
     * @return
     */
    public String getWord() {
        return word;
    }

    /**
     *
     * @return 
     */
    public ArrayList<Integer> getPositions() {
        return positions;
    }

    /**
     *
     * @return
     */
    public ArrayList<WordList> getWordLists() {
        return wordLists;
    }

    /**
     *
     * @return
     */
    public int getNumWords() {
        return numWords;
    }


}
