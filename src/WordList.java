import java.util.ArrayList;


public class WordList {
    private char letter;
    private ArrayList<Integer> positionList;
    private ArrayList<WordList> wordLists;
    private static ArrayList<WordList> defaultWordList = new ArrayList<WordList>();


    public WordList(char letter, ArrayList<Integer> positionList, ArrayList<WordList> wordLists) {
        this.letter = letter;
        this.positionList = positionList;
        this.wordLists = wordLists;
    }

    public void setWordLists(ArrayList<WordList> wordLists) {
        this.wordLists = wordLists;
    }
    public char getLetter() {
        return letter;
    }
    public ArrayList<Integer> getPositionList() {
        return positionList;
    }
    public ArrayList<WordList> getWordLists() {
        return wordLists;
    }

    private static int containsLetter(ArrayList<WordList> arrayList, char letter){

        for (int i = 0 ; i< arrayList.size() ; i++){
            if (arrayList.get(i).getLetter() == letter){
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<WordList> getDefaultWordList() {

        ArrayList<WordList> clone = new ArrayList<WordList>(defaultWordList.size());

        for (WordList item : defaultWordList){
            clone.add(new WordList(item.getLetter(), new ArrayList<Integer>(), null));
        }

        return clone;

    }

    public static ArrayList<WordList> newInstance(int refi){
        ArrayList<WordList> newWordList = new ArrayList<WordList>();
        ArrayList<Integer> newPositionList;
        char charLetter;

        int iterator ;

        //Genera el primer nivel del arbol
        for (int position = 0; position < Text.oriText.length(); position++) {
            charLetter = Text.oriText.charAt(position);

            iterator = containsLetter(newWordList, charLetter);
            if ( iterator != -1) {

                newWordList.get(iterator).getPositionList().add(position);

            } else {

                newPositionList = new ArrayList<Integer>();
                newPositionList.add(position);

                newWordList.add(new WordList(charLetter, newPositionList, null));

                defaultWordList.add(new WordList(charLetter, null, null));
            }
        }

        if (refi > 1) {

            for (int i = 0; i < newWordList.size(); i++){
                newWordList.get(i).addLevel(refi-1);
            }

        }

        return newWordList;
    }

    public void addLevel(int refi) {
        ArrayList<WordList> newWordList = getDefaultWordList();

        ArrayList<Integer> newPositionList;

        char charLetter;

        try {

            ArrayList<Integer> positionList = this.getPositionList();

            for (int i = 0; i < positionList.size(); i++) {
                try {


                    charLetter = Text.oriText.charAt(this.getPositionList().get(i)+1);

                    newPositionList = newWordList.get(containsLetter(newWordList, charLetter)).getPositionList();


                    /*
                    if (newPositionList == null) {
                        newPositionList = new ArrayList<Integer>();
                    }
                    */


                    newPositionList.add(this.getPositionList().get(i)+1);

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
    public static int getArrayLenght (ArrayList<WordList> arrayList){
        int size = 0;

        for (WordList positions: arrayList){

            try {

                size += positions.getPositionList().size();

            }catch (NullPointerException e){}
        }

        return size;
    }
}
