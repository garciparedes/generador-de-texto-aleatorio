import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author segarci & albamig
 */
public class Text {

	private StringBuilder text;
    private ArrayList<WordList> wordListArrayListOriginal;
    private int textLenght;
    private static StringBuilder oriText;

	//Constructor que crea el objeto texto a partir de otro anterior
	public Text(String fileName, int refi, int lenght){
        oriText = readFile(fileName);
        this.wordListArrayListOriginal = WordList.newInstance(refi);
        this.textLenght = lenght;
        this.text = genText(refi);
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

    public static StringBuilder getOriText() {
        return oriText;
    }

    //genera un texto aleatorio a partir de los parametros que se le manda
	private StringBuilder genText(int refi){

        StringBuilder strText = new StringBuilder();

		ArrayList<WordList> arrayList = WordList.newInstance(refi);


        //Pinta los distintos niveles de profundidad de la lista

        int rand;


        if (refi == 0){
            while (strText.length() < textLenght ){
                rand = (int)(Math.random() * arrayList.size());

                strText.append(arrayList.get(rand).getLetter());
            }
        } else {
            strText = putChar(strText, arrayList);

        }

        //Provisional

        return strText;
    }

    private StringBuilder putChar(StringBuilder strText, ArrayList<WordList> arrayList){
        int i, valor, numLetters, rand;

            if (strText.length() < textLenght ) {

                try {
                    numLetters = WordList.getArrayLenght(arrayList);

                } catch (NullPointerException e){
                    numLetters = 0;
                }


                if (numLetters != 0) {

                    rand = (int) (Math.random() * numLetters);
                    i = 0;
                    valor = arrayList.get(0).getPositions().size();;

                    while (valor <= rand) {
                        i++;
                        valor += arrayList.get(i).getPositions().size();
                    }
                    strText.append(arrayList.get(i).getLetter());
                    strText = putChar(strText, arrayList.get(i).getWordLists());

                } else{

                    char lastLetter = strText.charAt(strText.length()-1);

                    i = WordList.posicionLetra(wordListArrayListOriginal,lastLetter);

                    strText = putChar(strText, wordListArrayListOriginal.get(i).getWordLists());

                }
            }



        return strText;
    }

}