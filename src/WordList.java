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
	
	public WordList(String word, ArrayList<Integer> positions, ArrayList<WordList> wordLists){
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

	public static ArrayList<WordList> newInstance(Text text, int refi){

		String strText = text.getText();
		ArrayList<WordList> al = new ArrayList<WordList>();
		String a;
		WordList wl;

		for (int i = 0; i <= strText.length(); i++){
			a = String.valueOf(strText.charAt(i));

			wl = new WordList(a,null, null);

			if (al.contains(wl)){
				wl.setPositions();

			}
			else{
				al.add(wl);

			}


		}

		return  al;
	}
}
