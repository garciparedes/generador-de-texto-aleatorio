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
		StringBuilder strText = text.getText();
		ArrayList<WordList> al = new ArrayList<WordList>();
		String str;
		WordList wl;


        switch (refi){
            case 0:

                for (int i = 0; i < strText.length(); i++){
                    str = String.valueOf(strText.charAt(i));
                    wl = new WordList(str,null, null);

                    if (!wl.contiene(al)){
                        al.add(wl);
                    }
                }

            case 1:


            default:


        }


		return  al;
	}

    private boolean contiene(ArrayList<WordList> al){
        for (int i = 0; i<al.size(); i++){
            if (al.get(i).getWord().equals(this.getWord())){
                return true;
            }
        }
        return false;
    }
}
