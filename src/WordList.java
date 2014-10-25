import java.util.ArrayList;

/**
 * @author segarci & albamig
 */

/**
 * @author segarci & albamig
 *
 */
public class WordList {
	private char letter;
	private ArrayList<Integer> positions;
	private ArrayList<WordList> wordLists;

    /**
     * Constructor de la clase WordList
     *
     * @param letter caracter al que representa el objeto
     * @param positions lista que contiene las posiciones en las que se encuentra el texto
     * @param wordLists caracteres que le siguen en el texto
     */
	public WordList(char letter, ArrayList<Integer> positions, ArrayList<WordList> wordLists){
		this.letter = letter;
		this.positions = positions;
		this.wordLists = wordLists;
	}

    /**
     * Setter de wordLists
     * @param wordLists Lista de caracteres que siguen al caracter objeto
     */
    private void setWordLists(ArrayList<WordList> wordLists) {
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

<<<<<<< HEAD

    /**
     * El método newInstance genera un ArrayList formado por objetos WordList
     *
     * @param strText Texto a partir del cual se generara el arbol
     * @param refi Nivel de profundidad que tendra el arbol
     * @return listLetter un ArrayList<WordList>
     */
	public static ArrayList<WordList> newInstance(StringBuilder strText, int refi){
		ArrayList<WordList> listLetter = new ArrayList<WordList>();
		char charLetter;
		WordList letter;
=======
	public static ArrayList<WordList> newInstance(Text text, int refi){
		StringBuilder strText = text.getText();
		ArrayList<WordList> al = new ArrayList<WordList>();
		char letter;
		WordList wl;
>>>>>>> FETCH_HEAD

        //Genera el primer nivel del arbol
        for (int position = 0; position < strText.length(); position++){
            charLetter = strText.charAt(position);
            letter = new WordList(charLetter,new ArrayList<Integer>(), null);
            if (!letter.belongs(listLetter,position)){
                listLetter.add(letter);
            }
        }

<<<<<<< HEAD
        //Genera los n siguientes niveles
        for(int i = 0; i < listLetter.size(); i++)
            listLetter.get(i).addLetter(strText, refi -1);

		return  listLetter;
	}

    /**
     *
     * El metodo belongs es un metodo booleano, que indica si el objeto
     * pertenece o no a la lista que se le pasa como parametro
     * En el caso de que si pertenezca se le anade un nuevo elemento al atributo posicion
     *
     * @param arrayList lista a la que puede que pertenezca el objeto Wordlist
     * @param position posicion en el texto de la letra Wordlist
     * @return Resultado booleano en funcion de si el objeto pertene o no a la lista
     */
    private boolean belongs(ArrayList<WordList> arrayList,int position){
        for (int i = 0; i<arrayList.size(); i++){
            if ( this.getLetter() == arrayList.get(i).getLetter()){
                arrayList.get(i).getPositions().add(position);
                return true;
            }
        }
        this.getPositions().add(position);
        return false;
    }

    /**
     * El metodo addLetter genera un nivel mas de profundidad en el arbol
     * y si refi es mayor que 1 se llama a si mismo recursivamente
     *
     * @param strText Texto a partir del cual se generara el arbol
     * @param refi Nivel de profundidad que tendra el arbol
     */
    private void addLetter(StringBuilder strText, int refi){

        char charLetter;
        WordList letter;
        ArrayList<WordList> listLetter =  new ArrayList<WordList>();
        this.setWordLists(listLetter);

        for (int a = 0; a < this.getPositions().size(); a++) {
            try {

                charLetter = strText.charAt(this.getPositions().get(a)+1);

                letter = new WordList(charLetter,new ArrayList<Integer>(), null);

                if (!letter.belongs(listLetter, this.getPositions().get(a) + 1)){
                    listLetter.add(letter);
                }

            } catch (StringIndexOutOfBoundsException e){}
=======
        switch (refi){
            case 0:

                for (int i = 0; i < strText.length(); i++){
                    letter = strText.charAt(i);
                    wl = new WordList(letter,null, null);

                    if (!wl.contiene(al)){
                        al.add(wl);
                    }
                }

            case 1:


            default:


>>>>>>> FETCH_HEAD
        }
        if (refi > 0){

            for(int i = 0; i < listLetter.size(); i++)
                listLetter.get(i).addLetter(strText, refi -1);

<<<<<<< HEAD

=======

		return  al;
	}

    private boolean contiene(ArrayList<WordList> al){
        for (int i = 0; i<al.size(); i++){
            if (al.get(i).getLetter() == this.getLetter()){
                return true;
            }
>>>>>>> FETCH_HEAD
        }
    }
}
