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

    public void setLetter(char letter) {
        this.letter = letter;
    }
    public void setPositionList(ArrayList<Integer> positionList) {
        this.positionList = positionList;
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

        ArrayList<WordList> clone = new ArrayList<WordList>();

        for (WordList item : defaultWordList){
            clone.add(new WordList(item.getLetter(), new ArrayList<Integer>(), null));
        }

        return clone;

    }

    public static ArrayList<WordList> newInstance(StringBuilder strText, int refi){
        ArrayList<WordList> newWordList = new ArrayList<WordList>();
        ArrayList<Integer> newPositionList;
        char charLetter;

        int iterator ;

        //Genera el primer nivel del arbol
        for (int position = 0; position < strText.length(); position++) {
            charLetter = strText.charAt(position);

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

            for (WordList item: newWordList){
                item.addLevel(refi-1, strText);
            }

        }

        return newWordList;
    }

    public void addLevel(int refi, StringBuilder stringBuilder) {
        ArrayList<WordList> newWordList = getDefaultWordList();

        char charLetter;

        this.setWordLists(newWordList);

        int iterator;

        for (int i = 0 ; i < this.getPositionList().size() ; i++){
            try {

                charLetter = stringBuilder.charAt(this.getPositionList().get(i)+1);

                iterator = containsLetter(newWordList, charLetter);

                this.getWordLists().get(iterator).getPositionList().add(this.getPositionList().get(i) + 1);

            } catch (StringIndexOutOfBoundsException ignored){}

        }

        if (refi > 0){

            for (WordList item : newWordList){
                item.addLevel(refi-1,stringBuilder);
            }
        }
    }
}
