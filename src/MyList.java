import java.util.Arrays;

/**
 * Created by garciparedes on 4/12/14.
 */
public class MyList {

    
    //private WordList[] array;
    private char letter;
    private int numLetter;
    private MyList[] array;


    /**
     *
     */
    public MyList (){
        this.array = new MyList[0];
    }

    public MyList(char letter, int numLetter){
        this.letter = letter;
        this.numLetter = numLetter;
        this.array = new MyList[0];
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

            multimatriz.numLetter++;

            if (charCadena.length() > 0){

                letra = charCadena.charAt(0);

                if (!multimatriz.contains( letra)){
                    multimatriz.add(new MyList(letra, 1));
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
     * Metodo que incrementa el numero de letras que tiene un objeto WordList.
     *
     * @param charCadena caracteres que quedan por anadir para terminar ese nivel.
     */
    public void introduceLetra ( CharSequence charCadena){
        char letter;
        int posicion;

        if (charCadena.length() > 0){

            letter = charCadena.charAt(0);

            if (!contains(letter)){
                add(new MyList(letter, 1));
                get(size() - 1).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
            else {
                //posicion = indexOf(letter);
                get(letter).numLetter++;
                get(letter).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
        }
    }

    /**
     *
     * @param element
     */
    public void add(MyList element){
        array = Arrays.copyOf(array, array.length+1);
        array[array.length-1] = element;

    }


    /**
     *
     * @param i
     * @return
     */
    public MyList get(int i){
        return array[i];
    }

    public MyList get(char letter){
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

