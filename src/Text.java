import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
/**
 *
 * @author segarci & albamig
 */
public class Text {

    private StringBuilder text;
    private int lenghtText;
    public static StringBuilder oriText;

    
    //Constructor que crea el objeto texto a partir de otro anterior
    public Text(String fileName, int refi, int lenghtText) {
        oriText = readFile(fileName);
        this.lenghtText = lenghtText;
        this.text = genText(refi, lenghtText);

    }


    public StringBuilder getText() {
        return text;
    }


    //Lee desde fichero y genera el texto con el contenido que hay en ��l
    public static StringBuilder readFile(String fileName) {
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
            while ((linea = br.readLine()) != null) {
                textBuilder.append(linea).append('\n');
            }

        } catch (Exception FileNotFoundException) {
            System.out.println(ERROR_FICHERO);
            fileName = Main.writeString(Main.INTRODUCE_TEXTO);
            Text.readFile(fileName);

        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }


        // Para evitar problemas de límite
        textBuilder.deleteCharAt(textBuilder.length() - 1);

        return textBuilder;
    }


    //genera un texto aleatorio a partir de los parametros que se le manda
    private StringBuilder genText(int refi, int lenght) {

        StringBuilder texto = new StringBuilder();

        WordList[] multiMatriz = WordList.newInstance(refi);




        //Pinta los distintos niveles de profundidad de la lista

        int rand;


        if (refi == 0){
            while (lenght > 0 ){
                rand = (int)(Math.random() * multiMatriz.length);

                texto.append(multiMatriz[rand].getLetter());
                lenght--;
            }
        } else {

            while (texto.length() < lenghtText ){
                texto = putChar(texto, multiMatriz);
            }
        }

        return texto;
    }


    private StringBuilder putChar(StringBuilder stringBuilder, WordList[] multiMatriz){
        int i, valor, numLetters, rand;

        numLetters = WordList.numeroDeLetras(multiMatriz);

        if (stringBuilder.length() < lenghtText && numLetters != 0) {

            rand = (int) (Math.random() * numLetters);
            i = 0;
            valor = multiMatriz[0].getNumLetter();

            while (valor <= rand ) {
                i++;
                valor += multiMatriz[i].getNumLetter();
            }

            stringBuilder.append(multiMatriz[i].getLetter());
            stringBuilder = putChar(stringBuilder, multiMatriz[i].getContinueLetter());

        }

        return stringBuilder;
    }

}