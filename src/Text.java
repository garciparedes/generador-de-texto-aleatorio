import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 *
 * @author segarci & albamig
 */
public class Text {

	private StringBuilder text;
    public static StringBuilder oriText;

    //Constructor que crea el objeto texto a partir de otro anterior
    public Text(String fileName, int refi, int lenght){
        oriText = readFile(fileName);
        this.text = genText( refi, lenght);
    }

	public StringBuilder getText(){
		return text;
	}

	//Lee desde fichero y genera el texto con el contenido que hay en ��l
	public static StringBuilder readFile(String fileName){
		final String ERROR_FICHERO = "Error: Fichero no encontrado";
	    final String ROUTE = "texts/";
	    
	    File file;
	    FileReader fr = null;
	    BufferedReader br;
        
	    StringBuilder textBuilder = new StringBuilder();

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            file = new File(ROUTE + fileName);

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null) {
                textBuilder.append(linea).append('\n');
            }

        }catch(Exception FileNotFoundException){
            System.out.println(ERROR_FICHERO);
            fileName = Main.writeString(Main.INTRODUCE_TEXTO);
            Text.readFile(fileName);

        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }


        // Para evitar problemas de límite
        textBuilder.deleteCharAt(textBuilder.length()-1);

        return textBuilder;
    }


	//genera un texto aleatorio a partir de los parametros que se le manda
    private StringBuilder genText(int refi, int lenght){

        StringBuilder strText = new StringBuilder();

        Date viejo = new Date();
        WordList[] multiMatriz = WordList.newInstance( refi);
        Date nuevo = new Date();

        System.out.println((double)(nuevo.getTime() - viejo.getTime())/1000);

        System.out.println(multiMatriz.length);
        for(int i = 0; i < multiMatriz.length; i++){
            System.out.println(multiMatriz[i].getLetter() + " " + multiMatriz[i].getPositionList().length);
        }

        System.out.println(multiMatriz[0].getWordLists().length);
        for(int i = 0; i < multiMatriz[0].getWordLists().length; i++){
            System.out.println(multiMatriz[0].getWordLists()[i].getLetter() + " "
                    + multiMatriz[0].getWordLists()[i].getPositionList().length
                    );
        }

        int rand;

        if (refi == 0){
            while (lenght > 0 ){
                rand = (int)(Math.random() * multiMatriz.length);

                strText.append(multiMatriz[rand].getLetter());
                lenght--;
            }
        } else {

            while (lenght > 0 ){
                lenght = putChar(lenght, multiMatriz);
            }

        }

        return strText;
    }

    private int putChar(int lenght, WordList[] multiMatriz){

        try {
            int numLetters = WordList.getArrayLenght(multiMatriz);

            if (lenght > 0 && numLetters != 0) {

                int i = 0, valor = 0;

                int rand = (int) (Math.random() * numLetters);

                //Acumulador del numero de letras
                while (rand > valor) {
                    try { valor += multiMatriz[i].getPositionList().length; }
                    catch (NullPointerException e){}
                    i++;
                }

                try {

                    System.out.print(multiMatriz[i - 1].getLetter());
                    lenght = putChar(lenght - 1, multiMatriz[i - 1].getWordLists());

                } catch (IndexOutOfBoundsException ignore){}
            }

        } catch (NullPointerException e){}

        return lenght;
    }

}