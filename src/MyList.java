import java.util.Arrays;

/**
 * Created by garciparedes on 4/12/14.
 */
public class MyList<E> {

    
    private Object[] array;


    /**
     *
     */
    public MyList (){
        this.array = new Object[0];
    }


    /**
     *
     * @param defSize
     */
    public MyList (int defSize){
        this.array = new Object[defSize];
    }


    /**
     *
     * @param element
     */
    public void add(E element){
        array = Arrays.copyOf(array, array.length+1);
        array[array.length-1] = element;

    }


    /**
     *
     * @param i
     * @return
     */
    public E get(int i){
        return (E) array[i];
    }


    /**
     *
     * @return
     */
    public int size(){
        return array.length;
    }
    
}

