/**
 * Created by garciparedes on 4/12/14.
 */
public class HashWord {

    private Character letter;
    private int numLetter;
    private HashWord[] array;

    private int n = 0;
    private int m = 16;
    private double maxL = 0.8;

    public HashWord(CharSequence charCadena){
        this.letter = charCadena.charAt(0);
        this.array = new HashWord[m];
        introduceLetra(charCadena.subSequence(1, charCadena.length()));
    }

    /**
     * Instanciador de la clase WordList
     *
     * @param dimension Nivel de refinamiento del texto
     * @return WordList[] compuesto por todos los caracteres del texto con la dimension indicada
     */
    public HashWord(int dimension){

        if (dimension ==0){
            dimension = 1;
        }

        CharSequence charCadena;
        this.array = new HashWord[m];

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

    // Devuelve el indice correspondiente a esa clave
    protected int indice(Character c) { return c.hashCode() % m; }

    // Calcula el salto de exploración
    protected int salto(Character c) {
        int s = c.hashCode() / m; return (s % 2 == 0) ? s+1 : s; }


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
                add(new HashWord(charCadena));
            }
            else {
                get(letra).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
        }
    }

    public void add(HashWord hashWord) {

        // Incrementar n y comprobar factor de carga
        n++;
        if ((1.0 * n) / m > maxL)
            reestructurar();
        // Aplicar función de dispersión a la clave
        int i = indice(hashWord.getLetter());
        int d = salto(hashWord.getLetter());
        // Explorar la tabla hasta encontrar un par nulo o
        // una clave nula (borrado perezoso)
        while (array[i] != null && array[i].getLetter() != null) i = (i + d) % m; // Insertar clave en posición
        if (array[i] == null) {
            array[i] = hashWord;
        }
    }


    protected void reestructurar() {
        // Salvamos la tabla anterior
        HashWord[] tmp = array;

        // Creamos una nueva tabla
        n = 0;
        m = 2 * m; // Duplicamos el tamaño
        array = new HashWord[m];
        for (int i = 0; i < m; i++)
            array[i] = null;
        // Recorremos la tabla anterior insertando elementos
        for (int i = 0; i < tmp.length; i++) {
            HashWord par = tmp[i];
            if (par != null) {
                add(par);
            }
        }
    }


    public HashWord get(int i){
        return array[i];
    }

    public HashWord get(char letter){
        return array[indexOf(letter)];
    }



    public int size(){
        return array.length;
    }




    /**
     * Metodo que devuelve entero con la posición de un objeto WorList de la lista.
     *
     * @param letter Letra que buscar.
     * @return position del Objeto.
     */
    public int indexOf( Character letter){

        // Aplicar función de dispersión a la clave
        int i = indice(letter);
        // Calcular el salto de exploración
        int d = salto(letter);
        // Explorar la tabla hasta posición nula o la clave.
        // Una par nulo detiene la exploración, pero una
        // clave nula no (borrado perezoso)
        while(array[i] != null &&
                (array[i].letter == null ||
                        !array[i].letter.equals(letter))) {
            i = (i+d) % m;
        }
        return i;
    }


    /**
     * Metodo boolean que devuelve true o false, según si el elemento pertenece o no a la lista.
     *
     * @param letter Letra que buscar.
     * @return Boolean pertenencia del objeto.
     */
    public boolean contains( char letter){
        return get(letter) != null;
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

    public Character getLetter() {
        return letter;
    }

    public char getLetter(int i) {
        return get(i).letter;
    }
}


