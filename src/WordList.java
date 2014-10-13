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
	
	public WordList(String word, ArrayList<Integer> positions, ArrayList<WordList> wordLists, int numwords){
		this.word = word;
		this.positions = positions;
		this.wordLists = wordLists;
	}

    /**
     * Setter de word
     * @param word Caracter al que representa el objeto
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * Setter de position
     * @param positions Posiciones del texto fuente en las que se encuentra el caracter
     */
    public void setPositions(ArrayList<Integer> positions) {
        this.positions = positions;
    }

    /**
     * Setter de wordLists
     * @param wordLists Lista de caracteres que siguen al caracter objeto
     */
    public void setWordLists(ArrayList<WordList> wordLists) {
        this.wordLists = wordLists;
    }

    /**
     * Getter de word
     * @return word
     */
    public String getWord() {
        return word;
    }

    /**
     * Getter de positions
     * @return positions
     */
    public ArrayList<Integer> getPositions() {
        return positions;
    }

    /**
     * Getter de wordLists
     * @return wordLists
     */
    public ArrayList<WordList> getWordLists() {
        return wordLists;
    }
}
