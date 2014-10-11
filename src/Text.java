import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * Created by garciparedes on 11/10/14.
 */
public class Text {

    private final String ERROR_FICHERO = "Error: Fichero no encontrado";
    private final String ROUTE = "texts/";

    private File file;
    private FileReader fr;
    private BufferedReader br;

    public String readFile(String fileName){
        String text;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            file = new File(ROUTE + fileName);

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            String textBuffer = "";
            while((linea=br.readLine())!=null) {
                textBuffer = textBuffer + linea;
            }
            text = textBuffer;

        }catch(Exception FileNotFoundException){
            System.out.println(ERROR_FICHERO);
            fileName = Main.writeString(Main.INTRODUCE_TEXTO);
            text = readFile(fileName);


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

        return text;
    }


}
