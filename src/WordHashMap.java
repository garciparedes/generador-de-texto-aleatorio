import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by garciparedes on 7/11/14.
 */
public class WordHashMap implements Cloneable {
    private ArrayList<Integer> positionList;
    private HashMap<Character, WordHashMap> hashList;
    private static HashMap<Character, WordHashMap> defaultHashList = new HashMap<Character, WordHashMap>();


    public WordHashMap(ArrayList<Integer> newPositionList, HashMap<Character, WordHashMap> newHashList) {
        this.positionList = newPositionList;
        this.hashList = newHashList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static HashMap<Character, WordHashMap> getDefaultHashList() {
        return (HashMap<Character, WordHashMap>) defaultHashList.clone();
    }

    public ArrayList<Integer> getPositionList() {
        return positionList;
    }

    public HashMap<Character, WordHashMap> getHashList() {
        return hashList;
    }

    public static void setDefaultHashList(HashMap<Character, WordHashMap> defaultHashList) {
        WordHashMap.defaultHashList = defaultHashList;
    }

    public void setHashList(HashMap<Character, WordHashMap> hashList) {
        this.hashList = hashList;
    }

    public static HashMap<Character, WordHashMap> newInstance(StringBuilder strText, int refi) {
        HashMap<Character, WordHashMap> newHashList = new HashMap<Character, WordHashMap>();
        HashMap<Character, WordHashMap> newHashList1 ;

        WordHashMap newWordHashMap;

        ArrayList<Integer> newPositionList;

        char charLetter;


        //Genera el primer nivel del arbol
        for (int position = 0; position < strText.length(); position++) {
            charLetter = strText.charAt(position);
            if (newHashList.containsKey(charLetter)) {
                newHashList.get(charLetter).getPositionList().add(position);
            } else {
                newPositionList = new ArrayList<Integer>();
                newPositionList.add(position);
                newHashList.put(charLetter, new WordHashMap(newPositionList, null));
                defaultHashList.put(charLetter, null);
            }
        }

        if (refi > 1) {



            for (Map.Entry<Character, WordHashMap> entry : newHashList.entrySet())
            {

                //newWordHashMap = new WordHashMap(null, getDefaultHashList());
                //entry.setValue(newWordHashMap);
            }
        }
        return newHashList;
    }


}
