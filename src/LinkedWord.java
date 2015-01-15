

/**
 * Clase para representar los caracteres
 *
 * @author Sergio Garcia Prado
 * @author Alberto Amigo Alonso
 */
public class LinkedWord {

    private int size = 0;

    private LinkedWord siguiente;
    private LinkedWord hijo;

    private char letter;
    private int numLetter;


    /**
     * Constructor de la clase
     *
     * @param charCadena cadena sobre la que se van creando los distintos niveles.
     */
    private LinkedWord(CharSequence charCadena) {
        this.letter = charCadena.charAt(0);
        introduceLetra(charCadena.subSequence(1, charCadena.length()));
    }


    /**
     * Constructor de la clase
     *
     * @param dimension Nivel de refinamiento del texto
     */
    public LinkedWord(int dimension) {

        if (dimension == 0) {
            dimension = 1;
        }

        CharSequence charCadena;

        for (int i = 0; i < Text.oriText.length(); i++) {
            try {
                charCadena = Text.oriText.subSequence(i, i + dimension);
            } catch (IndexOutOfBoundsException e) {
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
    public void introduceLetra(CharSequence charCadena) {
        char letra;
        numLetter++;

        if (charCadena.length() > 0) {

            letra = charCadena.charAt(0);

            if (!contains(letra)) {
                add(new LinkedWord(charCadena));
            } else {
                get(letra).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
        }
    }


    /**
     * Metodo que añade objetos LinkedWord al nivel inferior.
     *
     * @param nuevo Letra que se quiere añadir.
     */
    public void add(LinkedWord nuevo) {
        nuevo.siguiente = hijo;
        hijo = nuevo;
        size++;
    }


    /**
     * Metodo para recuperar los objetos almacenados en el nivel inferior
     *
     * @param posicion Indice del objeto.
     * @return LinkedWord letra.
     */
    public LinkedWord get(int posicion) {
        LinkedWord lw = hijo;
        for (int i = 0; i < posicion; i++) {
            lw = lw.siguiente;
        }
        return lw;
    }


    /**
     * Metodo para recuperar los objetos almacenados en el nivel inferior
     *
     * @param letter letra que se quiere recuperar.
     * @return LinkedWord letra.
     */
    public LinkedWord get(char letter) {
        if (hijo == null) {
            return null;
        }
        LinkedWord lw = hijo;

        while (lw.letter != letter) {
            lw = lw.siguiente;

            if (lw == null) {
                return null;
            }
        }
        return lw;
    }


    public int size() {
        return size;
    }


    /**
     * Metodo boolean que devuelve true o false, según si el elemento pertenece o no a la lista.
     *
     * @param letter Letra que buscar.
     * @return Boolean pertenencia del objeto.
     */
    public boolean contains(char letter) {
        return get(letter) != null;
    }


    /**
     * Metodo que devuelve el numero de letras que hay como esa.
     *
     * @return Integer con el numero de letras.
     */
    public int getNumLetter() {
        return numLetter;
    }


    /**
     * Metodo que devuelve el numero de letras que hay como esa en un nivel inferior.
     *
     * @param letter letra que se quiere comprobar.
     * @return Integer con el numero de letras.
     */
    public int getNumLetter(char letter) {
        return get(letter).numLetter;
    }


    /**
     * Getter de Letter en nivel inferior
     *
     * @param posicion Indice del objeto que se quiere comprobar.
     * @return letter
     */
    public char getLetter(int posicion) {
        return get(posicion).letter;
    }


    public int selectRandomLetter(){

        int i;
        int rand = (int) (Math.random() * getNumLetter());

        LinkedWord lw = hijo;
        i = 0;
        int valor = lw.getNumLetter();

        while (valor <= rand) {
            i++;
            lw = lw.siguiente;
            valor += lw.getNumLetter();
        }

        return i;
    }
}

