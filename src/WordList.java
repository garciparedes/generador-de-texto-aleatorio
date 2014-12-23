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
    private MyList<WordList> continueLetter;


    /**
     * Constructor de la clase
     *
     * @param letter char al que representa el objeto
     * @param numLetter numero de caracteres iguales que hay en el texto
     * @param continueLetter WordList[] formado por todos los caracteres que le siguen
     */
    public WordList(char letter, int numLetter, MyList<WordList> continueLetter){
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
    public static MyList<WordList> newInstance(int dimension){

        CharSequence charCadena;
        MyList<WordList> multimatriz = new MyList<WordList>();
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

                if (!contains(multimatriz, letra)){
                    multimatriz.add(new WordList(letra, 1, new MyList<WordList>()));
                    multimatriz.get(multimatriz.size()-1).introduceLetra(charCadena.subSequence(1, charCadena.length()));
                }
                else {
                    posicion = indexOf(multimatriz, letra);
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

    public MyList<WordList> getContinueLetter() {
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

            if (!contains(continueLetter, letter)){
                continueLetter.add(new WordList(letter, 1, new MyList<WordList>()));
                continueLetter.get(continueLetter.size()-1).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
            else {
                posicion = indexOf(continueLetter, letter);
                continueLetter.get(posicion).numLetter++;
                continueLetter.get(posicion).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
        }
    }


    private static int indexOf(MyList<WordList> list, char letter){
        for (int i = 0 ;  i < list.size(); i++){
            if (list.get(i).letter == letter){
                return i;
            }
        }
        return -1;
    }


    private static boolean contains(MyList<WordList> list, char letter){
        return indexOf(list, letter) >= 0;
    }


    /**
     * Metodo que devuelve el numero de letras que hay como esa.
     *
     * @param multimatriz Array en el cual se va a comprobar.
     * @return Integer con el numero de letras.
     */
    public static int numeroDeLetras(MyList<WordList> multimatriz){
        int acumulador = 0;
            for (int i = 0 ; i < multimatriz.size() ; i++){
                acumulador += multimatriz.get(i).getNumLetter();
            }
        return acumulador;
    }

    /**
     * Metodo que devuelve la posicion de la letra indicada y -1 si esta no esta.
     *
     * @param charLetter caracter a partir del cual se quiere saber la posicion-
     * @return Integer con la posicion de la letra
     */
    public static int posicionLetra(MyList<WordList> letterArray,char charLetter){
        for (int i = 0 ; i < letterArray.size() ; i++){
            if (letterArray.get(i).getLetter() == charLetter){
                return i;
            }
        }
        return -1;
    }
}