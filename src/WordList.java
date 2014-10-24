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


    public void addPosition(Integer position){
        this.positions.add(position);

    }

	public static ArrayList<WordList> newInstance(Text text, int refi){
		StringBuilder strText = text.getText();
		ArrayList<WordList> listLetter = new ArrayList<WordList>();
		char charLetter;
		WordList letter;


        if (refi < 2){

            for (int position = 0; position < strText.length(); position++){

                charLetter = strText.charAt(position);

                letter = new WordList(charLetter,new ArrayList<Integer>(), null);

                letter.addPosition(position);

                if (!letter.belongs(listLetter,position)){

                    listLetter.add(letter);
                }

            }
        }else{
            // TODO Creacion de la lista de manera recursiva
        }

		return  listLetter;
	}

    private boolean belongs(ArrayList<WordList> arrayList,int position){
        for (int i = 0; i<arrayList.size(); i++){
            if ( this.getLetter() == arrayList.get(i).getLetter()){
                arrayList.get(i).addPosition(position);
                return true;
            }
        }
        return false;
    }
}
