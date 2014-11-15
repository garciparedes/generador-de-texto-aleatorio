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


    /**
     * El método newInstance genera un ArrayList formado por objetos WordList
     *
     *
     * @param refi Nivel de profundidad que tendra el arbol
     * @return listLetter un ArrayList<WordList>
     */
	public static ArrayList<WordList> newInstance( int refi){
		ArrayList<WordList> listLetter = new ArrayList<WordList>();
		char charLetter;
		WordList letter;

        //Genera el primer nivel del arbol
        for (int position = 0; position < Text.getOriText().length(); position++){
            charLetter = Text.getOriText().charAt(position);
            letter = new WordList(charLetter,new ArrayList<Integer>(), null);
            if (!letter.belongs(listLetter,position)){
                listLetter.add(letter);
            }
        }

        //Genera los n siguientes niveles
        for(int i = 0; i < listLetter.size(); i++)
            listLetter.get(i).addLetter(refi -1);

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
     * y se llama a si mismo recursivamente mientras refi sea mayor que 0
     * @param refi Nivel de profundidad que tendra el arbol
     */
    private void addLetter( int refi){

        char charLetter;
        WordList letter;
        ArrayList<WordList> listLetter =  new ArrayList<WordList>();
        this.setWordLists(listLetter);

        for (int a = 0; a < this.getPositions().size(); a++) {
            try {

                charLetter = Text.getOriText().charAt(this.getPositions().get(a)+1);

                letter = new WordList(charLetter,new ArrayList<Integer>(), null);

                if (!letter.belongs(listLetter, this.getPositions().get(a) + 1)){
                    listLetter.add(letter);
                }


            } catch (StringIndexOutOfBoundsException e){}
        }
        if (refi > 0){

            for(int i = 0; i < listLetter.size(); i++)
                listLetter.get(i).addLetter( refi -1);


        }
    }

    public static int posicionLetra(ArrayList<WordList> arrayList, char letra){

        for (int i = 0 ;  i < arrayList.size() ; i++){
            if (arrayList.get(i).letter == letra){
                return i;
            }
        }
        return -1;

    }


    public static int getArrayLenght (ArrayList<WordList> arrayList){
        int size = 0;

        for (WordList positions: arrayList){

            try {

                size += positions.getPositions().size();

            }catch (NullPointerException e){}
        }

        return size;
    }
}
