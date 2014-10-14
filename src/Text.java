import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

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
	private String text;

	//Constructor que crea el texto desde fichero
	public Text(String fileName){
		this.text = readFile(fileName);
	}

	//Constructor que crea el objeto texto a partir de otro anterior
	public Text(Text oriText, int refi, int lenght){
		this.text = genText(oriText, refi, lenght);
	}

	public String getText(){
		return text;
	}

	//Lee desde fichero y genera el texto con el contenido que hay en él
	private String readFile(String fileName){
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


	//genera un texto aleatorio a partir de los parametros que se le manda
	private String genText(Text oriText, int refi, int lenght){

		String strText;

		ArrayList<WordList> al = WordList.newInstance(oriText,refi);



		return strText;
	}
}
