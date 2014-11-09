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
                textBuilder.append(linea);
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
        return textBuilder;
    }


	//genera un texto aleatorio a partir de los parametros que se le manda
	private StringBuilder genText(int refi, int lenght){

        StringBuilder strText = new StringBuilder();

		//HashMap<Character, WordHashMap> wordHashHashMap = WordHashMap.newInstance(oriText, refi);

        Date viejo = new Date();
        ArrayList<WordList> arrayList = WordList.newInstance( refi);

        Date nuevo = new Date();



        System.out.println((double)(nuevo.getTime() - viejo.getTime())/1000);
        //Pinta los distintos niveles de profundidad de la lista

        for (WordList item: arrayList){
            System.out.println(item.getLetter()
                    + " = "
                    + item.getPositionList().size()
            );
        }


        /*
        for (WordList item: arrayList.get(0).getWordLists()){
            System.out.println(item.getLetter()
                            + " = "
                            //+ item.getPositionList().size()
            );
        }

        for (WordList item: arrayList.get(0).getWordLists().get(1).getWordLists()){
            System.out.println(item.getLetter()
                            + " = "
                    //+ item.getPositionList().size()
            );
        }

        for (WordList item: arrayList.get(0).getWordLists().get(1).getWordLists().get(0).getWordLists()){
            System.out.println(item.getLetter()
                            + " = "
                    //+ item.getPositionList().size()
            );
        }
        */

        int a;
        while (lenght > 0 ){
            a = (int)(Math.random() * arrayList.size());

            strText.append(arrayList.get(a).getLetter());
            lenght--;
        }

        //Provisional

		return strText;
	}

}
