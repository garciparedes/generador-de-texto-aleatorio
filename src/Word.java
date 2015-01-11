import java.util.Arrays;


public class Word {

    private char letter;
    private int numLetter;
    private Word[] array;


    public Word(CharSequence charCadena) {
        this.letter = charCadena.charAt(0);
        this.array = new Word[0];
        introduceLetra(charCadena.subSequence(1, charCadena.length()));
    }


    public Word(int dimension) {

        if (dimension == 0) {
            dimension = 1;
        }

        CharSequence charCadena;
        this.array = new Word[0];

        //Rellena el array con los datos obtenidos pasando una segunda vez por el texto
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


    public void introduceLetra(CharSequence charCadena) {
        char letra;
        numLetter++;

        if (charCadena.length() > 0) {

            letra = charCadena.charAt(0);

            if (!contains(letra)) {
                add(new Word(charCadena));
            } else {
                get(letra).introduceLetra(charCadena.subSequence(1, charCadena.length()));
            }
        }
    }


    public void add(Word element) {
        array = Arrays.copyOf(array, array.length + 1);
        array[array.length - 1] = element;

    }


    public Word get(int i) {
        return array[i];
    }


    public Word get(char letter) {
        return array[indexOf(letter)];
    }


    public int size() {
        return array.length;
    }


    public int indexOf(char letter) {
        for (int i = 0; i < size(); i++) {
            if (get(i).letter == letter) {
                return i;
            }
        }
        return -1;
    }


    public boolean contains(char letter) {
        return indexOf(letter) >= 0;
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

