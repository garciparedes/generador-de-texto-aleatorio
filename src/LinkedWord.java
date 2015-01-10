

public class LinkedWord {

    private int size = 0;

    private LinkedWord siguiente;
    private LinkedWord hijo;

    private char letter;
    private int numLetter;


    public LinkedWord(CharSequence charCadena){
        this.letter = charCadena.charAt(0);
        introduceLetra(charCadena.subSequence(1, charCadena.length()));
    }


    public LinkedWord(int dimension){

        if (dimension ==0){
            dimension = 1;
        }

        CharSequence charCadena;

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


    public void introduceLetra ( CharSequence charCadena){
        char letra;
        numLetter++;

        if (charCadena.length() > 0){

            letra = charCadena.charAt(0);

            if (!contains(letra)){
                add(new LinkedWord(charCadena));
            }
            else {
                get(letra).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
        }
    }


    public void add(LinkedWord nuevo){
        if (hijo == null){
            hijo = nuevo;
        } else {
            nuevo.siguiente = hijo.siguiente;
            hijo.siguiente = nuevo;
        }
        size++;
    }


    public LinkedWord get(int posicion){
        LinkedWord lw = hijo;
        for (int i = 0 ; i < posicion ; i++){
            lw = lw.siguiente;
        }
        return lw;
    }


    public LinkedWord get(char letter){
        if (hijo == null){
            return null;
        }
        LinkedWord lw = hijo;

        while (lw.letter != letter){
            lw = lw.siguiente;

            if (lw == null){
                return null;
            }
        }
        return lw;
    }


    public int size(){
        return size;
    }


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


    public char getLetter(int i) {
        return get(i).letter;
    }


}

