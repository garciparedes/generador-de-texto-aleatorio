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
	private StringBuilder text;

	//Constructor que crea el texto desde fichero
	public Text(String fileName){
		this.text = readFile(fileName);
	}

	//Constructor que crea el objeto texto a partir de otro anterior
	public Text(Text oriText, int refi, int lenght){
		this.text = genText(oriText, refi, lenght);
	}

	public StringBuilder getText(){
		return text;
	}

	//Lee desde fichero y genera el texto con el contenido que hay en él
	private StringBuilder readFile(String fileName){
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

        return textBuilder;
    }


	//genera un texto aleatorio a partir de los parametros que se le manda
	private StringBuilder genText(Text oriText, int refi, int lenght){

		StringBuilder strText;

		ArrayList<WordList> al = WordList.newInstance(oriText,refi);


        for (int i = 0; i<al.size();i++){
            System.out.println(al.get(i).getWord());
        }

        //Provisional
        strText = oriText.getText();

		return strText;
	}
}
