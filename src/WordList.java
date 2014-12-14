/**
 * Clase para representar los caracteres
 *
 * @author Sergio Garcia Prado
 * @author Alberto Amigo Alonso
 */
public class WordList {

    private char letter;
    private int numLetter;
    private WordList[] continueLetter;
    private static Character[] letterArray = new Character[0];


    /**
     * Constructor de la clase
     *
     * @param letter char al que representa el objeto
     * @param numLetter numero de caracteres iguales que hay en el texto
     * @param continueLetter WordList[] formado por todos los caracteres que le siguen
     */
    public WordList(char letter, int numLetter, WordList[] continueLetter){
        this.letter = letter;
        this.numLetter = numLetter;
        this.continueLetter = continueLetter;
    }

    /**
     * Instanciador de la clase WordList
     *
     * @param dimension Nivel de refinamiento del texto
     * @return WordList[] compuesto por todos los caracteres del texto con la dimension indicada
     */
    public static WordList[] newInstance(int dimension){

        char charLetter;
        CharSequence charCadena;

        //Encuentra el numero de letras distintas que hay en el texto
        for(int i = 0 ; i < Text.oriText.length() ; i++ ){
            charLetter = Text.oriText.charAt(i);
            if (!containsArray( charLetter)){
                letterArray = nuevaLetra(letterArray, charLetter);
            }

        }

        WordList[] multimatriz = creaMultiMatriz(dimension);
        char letra;
        int posicion;

        //Rellena el array con los datos obtenidos pasando una segunda vez por el texto
        for(int i = 0 ; i < Text.oriText.length() ; i++ ){
            try {

                charCadena = Text.oriText.subSequence(i, i + dimension);

            }catch (IndexOutOfBoundsException ignored){
                dimension--;
                charCadena = Text.oriText.subSequence(i, i + dimension);
            }
            
            if (charCadena.length() > 0){

                letra = charCadena.charAt(0);

                posicion = posicionLetra(letra);

                multimatriz[posicion].numLetter++;

                multimatriz[posicion].introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }


        }

        return multimatriz;
    }


    /**
     * Getter de Letter
     *
     * @return letter
     */
    public char getLetter() {
        return letter;
    }


    /**
     * Getter de NumLetter
     *
     * @return numLetter
     */
    public int getNumLetter() {
        return numLetter;
    }


    /**
     * Getter de continueLetter
     *
     * @return continueLetter
     */
    public WordList[] getContinueLetter() {
        return continueLetter;
    }


    /**
     * Metodo que devuelve el numero de letras que hay como esa.
     *
     * @param multimatriz Array en el cual se va a comprobar.
     * @return Integer con el numero de letras.
     */
    public static int numeroDeLetras(WordList[] multimatriz){
        int acumulador = 0;
        try {
            for (WordList elemento : multimatriz) {
                acumulador += elemento.getNumLetter();
            }
        } catch (NullPointerException ignored){}

        return acumulador;
    }


    /**
     * Getter de letterArray.
     *
     * @return letterArray
     */
    public static Character[] getLetterArray() {
        return letterArray;
    }

    /**
     * Metodo que devuelve verdadero en el caso de que el texto contenga la letra indicada
     * @param charLetter caracter que se quiere saber si esta o no
     * @return Boolean
     */
    private static boolean containsArray( char charLetter){
        return posicionLetra(charLetter) >= 0;
    }


    /**
     * Metodo que devuelve la posicion de la letra indicada y -1 si esta no esta.
     *
     * @param charLetter caracter a partir del cual se quiere saber la posicion-
     * @return Integer con la posicion de la letra
     */
    public static int posicionLetra(char charLetter){
        for (int i = 0 ; i < letterArray.length ; i++){
            if (letterArray[i] == charLetter){
                return i;
            }
        }
        return -1;
    }


    /**
     * Metodo que devuelve un array con una caracter mas que el anterior.
     * @param oldLetterArray Array sin el caracter.
     * @param charLetter Caracter que se desea anadir.
     * @return Character[] con el caracter ya anadido.
     */
    private static Character[] nuevaLetra(Character[] oldLetterArray, char charLetter){
        Character[] newLetterArray = new Character[oldLetterArray.length + 1];

        System.arraycopy(oldLetterArray, 0, newLetterArray, 0, oldLetterArray.length);

        newLetterArray[newLetterArray.length-1] = charLetter;

        return newLetterArray;
    }


    /**
     * Metodo recursivo que genera toda la multimatriz que luego se usara para almacenar los caracteres.
     *
     * @param dimension Dimension que tendra la multimatriz.
     * @return multimatriz con todos los niveles necesarios.
     */
    private static WordList[] creaMultiMatriz(int dimension){

        WordList[] multimatriz = new WordList[letterArray.length];

        if (dimension > 0) {

            for (int i = 0; i < letterArray.length; i++) {
                multimatriz[i] = new WordList(letterArray[i], 0, creaMultiMatriz( dimension - 1));

            }
            return multimatriz;

        } else { return null; }
    }


    /**
     * Metodo que incrementa el numero de letras que tiene un objeto WordList.
     *
     * @param charCadena caracteres que quedan por anadir para terminar ese nivel.
     */
    private void introduceLetra ( CharSequence charCadena){

        char letra;
        int i;
        if (charCadena.length() > 0){

            letra = charCadena.charAt(0);
            i = posicionLetra(letra);

            continueLetter[i].numLetter++;

            continueLetter[i].introduceLetra(charCadena.subSequence(1, charCadena.length()));
        }
    }

}