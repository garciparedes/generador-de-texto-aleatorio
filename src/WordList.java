
public class WordList {
    private char letter;
    private int numLetter;
    private WordList[] continueLetter;
    private static Character[] letterArray = new Character[0];


    public WordList(char letter, int numLetter, WordList[] continueLetter){
        this.letter = letter;
        this.numLetter = numLetter;
        this.continueLetter = continueLetter;
    }


    public static WordList[] newInstance(int dimension){
        WordList[] multimatriz;
        char charLetter;
        CharSequence charCadena;

        for(int i = 0 ; i < Text.oriText.length() ; i++ ){
            charLetter = Text.oriText.charAt(i);
            if (!containsArray( charLetter)){
                letterArray = nuevaLetra(letterArray, charLetter);
            }

        }

        multimatriz = creaMultiMatriz(letterArray, dimension);

        for(int i = 0 ; i < Text.oriText.length() ; i++ ){
            try {

                charCadena = Text.oriText.subSequence(i, i + dimension);

                char letra;
                int posicion;
                if (charCadena.length() > 0){

                    letra = charCadena.charAt(0);

                    posicion = posicionLetra(letra);

                    multimatriz[posicion].numLetter++;

                    multimatriz[posicion].introduceLetra(charCadena.subSequence(1, charCadena.length()));
                }

            }catch (IndexOutOfBoundsException ignored){}

        }

        return multimatriz;
    }


    public char getLetter() {
        return letter;
    }


    public int getNumLetter() {
        return numLetter;
    }


    public WordList[] getContinueLetter() {
        return continueLetter;
    }


    private static boolean containsArray( char charLetter){
        return posicionLetra(charLetter) >= 0;
    }


    private static int posicionLetra(char charLetter){
        for (int i = 0 ; i < letterArray.length ; i++){
            if (letterArray[i] == charLetter){
                return i;
            }
        }
        return -1;
    }


    private static Character[] nuevaLetra(Character[] oldLetterArray, char charLetter){
        Character[] newLetterArray = new Character[oldLetterArray.length + 1];

        System.arraycopy(oldLetterArray, 0, newLetterArray, 0, oldLetterArray.length);

        newLetterArray[newLetterArray.length-1] = charLetter;

        return newLetterArray;
    }


    private static WordList[] creaMultiMatriz(Character[] letterArray, int dimension){

        WordList[] multimatriz = new WordList[letterArray.length];

        if (dimension > 0) {

            for (int i = 0; i < letterArray.length; i++) {
                multimatriz[i] = new WordList(letterArray[i], 0, creaMultiMatriz(letterArray, dimension - 1));

            }
            return multimatriz;

        } else { return null; }
    }


    private void introduceLetra ( CharSequence charCadena){

        char letra;
        int i;
        if (charCadena.length() > 0){

            letra = charCadena.charAt(0);
            i = posicionLetra(letra);

            this.getContinueLetter()[i].numLetter++;

            this.getContinueLetter()[i].introduceLetra(charCadena.subSequence(1, charCadena.length()));
        }
    }


    public static int numeroDeLetras(WordList[] multimatriz){
        int acumulador = 0;
        for(int i = 0 ; i < multimatriz.length ; i++){
            acumulador += multimatriz[i].getNumLetter();
        }
        return acumulador;
    }

}