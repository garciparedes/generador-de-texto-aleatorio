import java.util.ArrayList;
import java.util.HashMap;

public class WordList {
    private char letter;
    private int numLetter;
    private WordList[] continueLetter;

    public WordList(char letter, int numLetter, WordList[] continueLetter){
        this.letter = letter;
        this.numLetter = numLetter;
        this.continueLetter = continueLetter;
    }

    public static WordList[] newInstance(int refi){
        WordList[] multimatriz = new WordList[0];
        Character[] letterArray = new Character[0];
        char charLetter;

        for(int i = 0 ; i < Text.oriText.length() ; i++ ){
            charLetter = Text.oriText.charAt(i);
            if (!containsArray(letterArray, charLetter)){
                nuevaLetra(letterArray, charLetter);
            }


        }
        multimatriz =


        return multimatriz;
    }

    public static boolean containsArray(Character[] letterArray, char charLetter){
        for (int i = 0 ; i < letterArray.length ; i++){
            if (letterArray[i] == charLetter){
                return true;
            }
        }

        return false;
    }

    public static Character[] nuevaLetra(Character[] oldLetterArray, char charLetter){
        Character[] newLetterArray = new Character[oldLetterArray.length + 1];

        for (int i =0 ; i < oldLetterArray.length ; i++ ){
            newLetterArray[i] = oldLetterArray[i];
        }
        newLetterArray[newLetterArray.length-1] = charLetter;
    }

    public static WordList[] creaMultiMatriz(Character[] letterArray, int dimension){

        WordList[] multimatriz = new WordList[letterArray.length];

        if (dimension != 0) {

            for (int i = 0; i < letterArray.length; i++) {
                multimatriz[i] = new WordList(letterArray[i], 0, creaMultiMatriz(letterArray, dimension - 1));

            }
            return multimatriz;

        } else { return null; }
    }
}