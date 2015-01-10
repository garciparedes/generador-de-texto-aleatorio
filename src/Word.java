import java.util.Arrays;

/**
 * Created by garciparedes on 4/12/14.
 */
public class Word {

    private char letter;
    private int numLetter;
    private Word[] array;



    public Word(CharSequence charCadena){
        this.letter = charCadena.charAt(0);
        this.array = new Word[0];
        introduceLetra(charCadena.subSequence(1, charCadena.length()));
    }

    /**
     * Instanciador de la clase WordList
     *
     * @param dimension Nivel de refinamiento del texto
     * @return WordList[] compuesto por todos los caracteres del texto con la dimension indicada
     */
    public Word(int dimension){

        if (dimension ==0){
            dimension = 1;
        }

        CharSequence charCadena;
        this.array = new Word[0];

        //Rellena el array con los datos obtenidos pasando una segunda vez por el texto
        for(int i = 0 ; i < Text.oriText.length() ; i++ ){
            try {
                charCadena = Text.oriText.subSequence(i, i + dimension);
            }catch (IndexOutOfBoundsException e){
                dimension--;
                charCadena = Text.oriText.subSequence(i, i + dimension);
            }

            introduceLetra(charCadena);

        }

    }


    /**
     * Metodo que incrementa el numero de letras que tiene un objeto WordList.
     *
     * @param charCadena caracteres que quedan por anadir para terminar ese nivel.
     */
    public void introduceLetra ( CharSequence charCadena){
        char letra;
        numLetter++;

        if (charCadena.length() > 0){

            letra = charCadena.charAt(0);

            if (!contains(letra)){
                add(new Word( charCadena));
            }
            else {
                get(letra).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
        }
    }

    /**
     *
     * @param element
     */
    public void add(Word element){
        array = Arrays.copyOf(array, array.length+1);
        array[array.length-1] = element;

    }


    /**
     *
     * @param i
     * @return
     */
    public Word get(int i){
        return array[i];
    }

    public Word get(char letter){
        return array[indexOf(letter)];
    }


    /**
     *
     * @return
     */
    public int size(){
        return array.length;
    }




    /**
     * Metodo que devuelve entero con la posición de un objeto WorList de la lista.
     *
     * @param letter Letra que buscar.
     * @return position del Objeto.
     */
    public int indexOf( char letter){
        for (int i = 0 ;  i < size(); i++){
            if (get(i).letter == letter){
                return i;
            }
        }
        return -1;
    }


    /**
     * Metodo boolean que devuelve true o false, según si el elemento pertenece o no a la lista.
     *
     * @param letter Letra que buscar.
     * @return Boolean pertenencia del objeto.
     */
    public boolean contains( char letter){
        return indexOf( letter) >= 0;
    }

    public int getNumLetter() {
        return numLetter;
    }

    public int getNumLetter(int i) {
        return get(i).numLetter;
    }

    public int getNumLetter(char letter) {
        return get(letter).numLetter;
    }

    public char getLetter() {
        return letter;
    }

    public char getLetter(int i) {
        return get(i).letter;
    }
}

