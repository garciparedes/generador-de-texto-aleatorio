import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Sergio Garcia Prado
 * @author Alberto Amigo Alonso
 */
public class Text {

    public static StringBuilder oriText;

    private StringBuilder text;
    private int lenghtText;
    private MyList<WordList> multiMatrizOriginal;


    /**
     * Constructor de la clase texto.
     * @param fileName Archivo Fuente.
     * @param refi Nivel de refinamiento del texto.
     * @param lenghtText Numero de caracteres que tendra.
     */
    public Text(String fileName, int refi, int lenghtText) {
        oriText = readFile(fileName);

        this.lenghtText = lenghtText;
        this.multiMatrizOriginal = WordList.newInstance(refi);
        this.text = genText(refi);
    }


    /**
     * Getter del texto
     * @return text
     */
    public StringBuilder getText() {
        return text;
    }


    /**
     * Metodo readFile que sirve para guardar el fichero txt en una variable Stringbuilder
     * @param fileName Fichero fuente
     * @return textBuilder variable que recoge el contenido del fichero
     */
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
            file = new File(
                    ROUTE
                    + fileName
            );

            fr = new FileReader(file);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                textBuilder.append(linea).append('\n');
            }

            // Para evitar problemas de límite
            textBuilder.deleteCharAt(textBuilder.length() - 1);

        } catch (Exception FileNotFoundException) {
            System.out.println(ERROR_FICHERO);
            fileName = Main.writeString(Main.INTRODUCE_TEXTO);
            textBuilder = readFile(fileName);

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

        return textBuilder;
    }


    /**
     * Metodo que genera el nuevo texto. Lo que hace es elegir que tipo de generacion de texto tiene que seguir.
     *
     * @param refi Nivel de refinamiento del texto nuevo.
     * @return texto StringBuilder con el resultado del texto generado
     */
    private StringBuilder genText(int refi) {

        StringBuilder texto  = new StringBuilder();

        for (int i = 0 ; i < multiMatrizOriginal.size() ; i++){
            System.out.println(
                    multiMatrizOriginal.get(i).getLetter()
                    + " = "
                    + multiMatrizOriginal.get(i).getNumLetter()
            );
        }

        for (int i = 0 ; i < multiMatrizOriginal.get(1).getContinueLetter().size() ; i++){
            System.out.println(
                    multiMatrizOriginal.get(1).getContinueLetter().get(i).getLetter()
                            + " = "
                            + multiMatrizOriginal.get(1).getContinueLetter().get(i).getNumLetter()
            );
        }

        for (int i = 0 ; i < multiMatrizOriginal.get(1).getContinueLetter().get(0).getContinueLetter().size() ; i++){
            System.out.println(
                    multiMatrizOriginal.get(1).getContinueLetter().get(0).getContinueLetter().get(i).getLetter()
                            + " = "
                            + multiMatrizOriginal.get(1).getContinueLetter().get(0).getContinueLetter().get(i).getNumLetter()
            );
        }
        /*
        switch (refi){

            case 0:
                texto = randomChar();
                break;

            case 1:
                texto = proporcionalRandom();
                break;

            default:
                texto = new StringBuilder();
                texto = putChar(texto, multiMatrizOriginal);
                break;
        }
        */

        return texto;
    }


}