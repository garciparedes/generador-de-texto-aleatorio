import java.util.Arrays;

/**
 * Created by garciparedes on 4/12/14.
 */
public class MyList {

    
    private WordList[] array;


    /**
     *
     */
    public MyList (){
        this.array = new WordList[0];
    }



    /**
     *
     * @param element
     */
    public void add(WordList element){
        array = Arrays.copyOf(array, array.length+1);
        array[array.length-1] = element;

    }


    /**
     *
     * @param i
     * @return
     */
    public WordList get(int i){
        return array[i];
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
            if (get(i).getLetter() == letter){
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


    /**
     * Metodo que devuelve el numero de letras que hay como esa.
     *
     * @return Integer con el numero de letras.
     */
    public int numeroDeLetras(){
        int acumulador = 0;
        for (int i = 0 ; i < size() ; i++){
            acumulador += get(i).getNumLetter();
        }
        return acumulador;
    }
}

