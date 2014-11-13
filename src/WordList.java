
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
        WordList[] multimatriz = new WordList[0];
        char charLetter;
        CharSequence charCadena;


        for(int i = 0 ; i < Text.oriText.length() ; i++ ){
            charLetter = Text.oriText.charAt(i);
            if (!containsArray(letterArray, charLetter)){
                letterArray = nuevaLetra(letterArray, charLetter);
            }

        }

        multimatriz = creaMultiMatriz(letterArray, dimension);

        for(int i = 0 ; i < Text.oriText.length() ; i++ ){
            try {


                charCadena = Text.oriText.subSequence(i, i + dimension);
                multimatriz = introduceLetra(multimatriz, charCadena);

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

    private static boolean containsArray(Character[] letterArray, char charLetter){
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

        for (int i =0 ; i < oldLetterArray.length ; i++ ){
            newLetterArray[i] = oldLetterArray[i];
        }
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

    private static WordList[] introduceLetra (WordList[] multimatriz, CharSequence charCadena){
        char letra;
        int i;
        if (charCadena.length() > 0){

            letra = charCadena.charAt(0);
            i = posicionLetra(letra);

            if (charCadena.length() == 1) {
                multimatriz[i].numLetter++;
            }

            return  introduceLetra(multimatriz, charCadena.subSequence(1, charCadena.length()));
        }
        return multimatriz;
    }

    public static int numeroDeLetras(WordList[] multimatriz){
        int acumulador = 0;
        for(int i = 0 ; i < multimatriz.length ; i++){
            acumulador += multimatriz[i].getNumLetter();
        }
        return acumulador;
    }
}