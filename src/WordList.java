import java.util.ArrayList;

/**
 * 
 */

/**
 * @author segarci & albamig
 *
 */
public class WordList {
	private char letter;
	private ArrayList<Integer> positions;
	private ArrayList<WordList> wordLists;
	
	public WordList(char letter, ArrayList<Integer> positions, ArrayList<WordList> wordLists){
		this.letter = letter;
		this.positions = positions;
		this.wordLists = wordLists;
	}

    /**
     * Setter de letter
     * @param letter Caracter al que representa el objeto
     */
    public void setLetter(char letter) {
        this.letter = letter;
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
     * Getter de letter
     * @return letter
     */
    public char getLetter() {
        return letter;
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
		char letter;
		WordList wl;


        switch (refi){
            case 0:

                for (int i = 0; i < strText.length(); i++){
<<<<<<< Updated upstream
                    letter = strText.charAt(i);
                    wl = new WordList(letter,null, null);
=======
                    str = String.valueOf(strText.charAt(i));
                    wl = new WordList(str, null, null);
>>>>>>> Stashed changes

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
            if (al.get(i).getLetter() == this.getLetter()){
                return true;
            }
        }
        return false;
    }
}
