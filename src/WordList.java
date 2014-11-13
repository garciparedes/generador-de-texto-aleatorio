import java.util.ArrayList;


public class WordList {
    private char letter;
    private Integer[] positionList;
    private WordList[] wordLists;

    private static WordList[] defaultWordList = new WordList[0];

    public static WordList[] getDefaultWordList() {

        WordList[] newWordList = new WordList[defaultWordList.length];
        for(int i = 0 ; i < defaultWordList.length ; i++){
            newWordList[i] = new WordList(defaultWordList[i].getLetter(), null, null);
        }
        return newWordList;
    }

    public static WordList[] newLetter(WordList[] oldArray, WordList wordList){
        WordList[] newArray = new WordList[oldArray.length+1];
        for (int i =0 ; i< oldArray.length; i++){
            newArray[i] = new WordList(oldArray[i].getLetter(), oldArray[i].getPositionList(), null);
        }
        newArray[newArray.length-1] = wordList;

        return newArray;
    }

    public static Integer[] newPosition(Integer[] oldArray, int newNum){
        Integer[] newArray = new Integer[oldArray.length+1];
        for (int i =0 ; i< oldArray.length; i++){
            newArray[i] = oldArray[i];
        }
        newArray[newArray.length-1] = newNum;

        return newArray;
    }

    public WordList(char letter, Integer[] positionList, WordList[] wordLists) {
        this.letter = letter;
        this.positionList = positionList;
        this.wordLists = wordLists;
    }


    public void setPositionList(Integer[] positionList) {
        this.positionList = positionList;
    }


    public void setWordLists(WordList[] wordLists) {
        this.wordLists = wordLists;
    }


    public char getLetter() {
        return letter;
    }


    public Integer[] getPositionList() {
        return positionList;
    }


    public WordList[] getWordLists() {
        return wordLists;
    }


    private static int containsLetter(WordList[] array, char letter){

        for (int i = 0 ; i< array.length ; i++){
            if (array[i].getLetter() == letter){
                return i;
            }
        }
        return -1;
    }


    public static WordList[] newInstance(int refi){
        WordList[] newWordList = new WordList[0];
        Integer[] newPositionList = new Integer[0];

        char charLetter;

        int iterator ;

        //Genera el primer nivel del arbol
        for (int position = 0; position < Text.oriText.length(); position++) {
            charLetter = Text.oriText.charAt(position);

            iterator = containsLetter(newWordList, charLetter);
            if ( iterator != -1) {

                //newWordList[iterator].getPositionList().add(position);

                newWordList[iterator].setPositionList(newPosition(newWordList[iterator].getPositionList(), position));

            } else {


                newPositionList = newPosition(newPositionList, position);

                newWordList = newLetter(newWordList, new WordList(charLetter, newPositionList, null));

                defaultWordList = newLetter(defaultWordList, new WordList(charLetter, newPositionList, null));

                newPositionList = new Integer[0];

            }
        }

        if (refi > 1) {

            for (int i = 0; i < newWordList.length; i++){
               newWordList[i].addLevel(refi-1);
            }

        }

        return newWordList;
    }


    public void addLevel(int refi) {
        WordList[] newWordList = getDefaultWordList();

        //for(int i = 0; i< newWordList.length)

        Integer[] newPositionList;

        Integer[] positionList = this.getPositionList();

        char charLetter;

        try {


            for (int i = 0; i < positionList.length; i++) {
                try {


                    charLetter = Text.oriText.charAt(positionList[i]+1);

                    newPositionList = newWordList[containsLetter(newWordList, charLetter)].getPositionList();



                    if (newPositionList == null) {

                        newPositionList = new Integer[0];
                    }

                    //newPositionList.add(positionList[i]+1);

                    newPositionList = newPosition(newPositionList, positionList[i]+1);

                    //newWordList[containsLetter(newWordList, charLetter)].setPositionList(newPositionList);


                } catch (StringIndexOutOfBoundsException ignored){}


            }

        } catch (NullPointerException ignored){}

        this.setWordLists(newWordList);

        if (refi > 1){

            for (int i = 0; i < newWordList.length; i++) {
                newWordList[i].addLevel(refi - 1);
            }
        }
    }



    public static int getArrayLenght (WordList[] array){
        int size = 0;

        for (WordList positions: array){

            try {

                size += positions.getPositionList().length;

            }catch (NullPointerException e){}
        }

        return size;
    }
}
