import java.util.ArrayList;

/**
 * Clase para representar los caracteres
 *
 * @author Sergio Garcia Prado
 * @author Alberto Amigo Alonso
 */
public class WordList {

    private char letter;
    private int numLetter;
    private MyList continueLetter;


    /**
     * Constructor de la clase
     *
     * @param letter char al que representa el objeto
     * @param numLetter numero de caracteres iguales que hay en el texto
     * @param continueLetter WordList[] formado por todos los caracteres que le siguen
     */
    public WordList(char letter, int numLetter, MyList continueLetter){
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
    public static MyList newInstance(int dimension){

        CharSequence charCadena;
        MyList multimatriz = new MyList();
        char letra;
        int posicion;

        //Rellena el array con los datos obtenidos pasando una segunda vez por el texto
        for(int i = 0 ; i < Text.oriText.length() ; i++ ){
            try {
                charCadena = Text.oriText.subSequence(i, i + dimension);
            }catch (IndexOutOfBoundsException e){
                dimension--;
                charCadena = Text.oriText.subSequence(i, i + dimension);
            }
            
            if (charCadena.length() > 0){

                letra = charCadena.charAt(0);

                if (!multimatriz.contains( letra)){
                    multimatriz.add(new WordList(letra, 1, new MyList()));
                    multimatriz.get(multimatriz.size()-1).introduceLetra(charCadena.subSequence(1, charCadena.length()));
                }
                else {
                    posicion = multimatriz.indexOf( letra);
                    multimatriz.get(posicion).numLetter++;
                    multimatriz.get(posicion).introduceLetra(charCadena.subSequence(1, charCadena.length()));
                }
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
    public MyList getContinueLetter() {
        return continueLetter;
    }


    /**
     * Metodo que incrementa el numero de letras que tiene un objeto WordList.
     *
     * @param charCadena caracteres que quedan por anadir para terminar ese nivel.
     */
    private void introduceLetra ( CharSequence charCadena){
        char letter;
        int posicion;

        if (charCadena.length() > 0){

            letter = charCadena.charAt(0);

            if (!continueLetter.contains( letter)){
                continueLetter.add(new WordList(letter, 1, new MyList()));
                continueLetter.get(continueLetter.size()-1).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
            else {
                posicion = continueLetter.indexOf( letter);
                continueLetter.get(posicion).numLetter++;
                continueLetter.get(posicion).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
        }
    }



}