import java.util.ArrayList;


public class WordList {
    private char letter;
    private ArrayList<Integer> positionList;
    private WordList[] wordLists;

    public static WordList[] newLetter(WordList[] oldArray, WordList wordList){
        WordList[] newArray = new WordList[oldArray.length+1];
        for (int i =0 ; i< oldArray.length; i++){
            newArray[i] = new WordList(oldArray[i].getLetter(), oldArray[i].getPositionList(), null);
        }
        newArray[newArray.length-1] = wordList;

        return newArray;
    }

    public WordList(char letter, ArrayList<Integer> positionList, WordList[] wordLists) {
        this.letter = letter;
        this.positionList = positionList;
        this.wordLists = wordLists;
    }


    public void setPositionList(ArrayList<Integer> positionList) {
        this.positionList = positionList;
    }


    public void setWordLists(WordList[] wordLists) {
        this.wordLists = wordLists;
    }


    public char getLetter() {
        return letter;
    }


    public ArrayList<Integer> getPositionList() {
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
        WordList[] newWordList = new WordList[81];
        ArrayList<Integer> newPositionList;
        char charLetter;

        int iterator ;

        //Genera el primer nivel del arbol
        for (int position = 0; position < Text.oriText.length(); position++) {
            charLetter = Text.oriText.charAt(position);

            iterator = containsLetter(newWordList, charLetter);
            if ( iterator != -1) {

                //newWordList[iterator].getPositionList().add(position);

            } else {

                newPositionList = new ArrayList<Integer>();
                newPositionList.add(position);

                newWordList.add(new WordList(charLetter, newPositionList, null));

            }
        }

        if (refi > 1) {

            for (int i = 0; i < newWordList.length; i++){
               // newWordList[i].addLevel(refi-1);
            }

        }

        return newWordList;
    }

    /*
    public void addLevel(int refi) {
        ArrayList<WordList> newWordList = getDefaultWordList();

        ArrayList<Integer> newPositionList;

        ArrayList<Integer> positionList = this.getPositionList();

        char charLetter;

        try {


            for (int i = 0; i < positionList.size(); i++) {
                try {


                    charLetter = Text.oriText.charAt(positionList.get(i)+1);

                    newPositionList = newWordList.get(containsLetter(newWordList, charLetter)).getPositionList();



                    if (newPositionList == null) {

                        newPositionList = new ArrayList<Integer>(positionList.size());
                    }

                    newPositionList.add(positionList.get(i)+1);

                    newWordList.get(containsLetter(newWordList, charLetter)).setPositionList(newPositionList);


                } catch (StringIndexOutOfBoundsException ignored){}


            }

        } catch (NullPointerException ignored){}

        this.setWordLists(newWordList);

        if (refi > 1){

            for (int i = 0; i < newWordList.size(); i++) {
                newWordList.get(i).addLevel(refi - 1);
            }
        }
    }


    */
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
