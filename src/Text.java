import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * @author Sergio Garcia Prado
 * @author Alberto Amigo Alonso
 */
public class Text {

    public static StringBuilder oriText;

    private StringBuilder text = new StringBuilder();
    private int lenghtText;
    private LinkedWord wordTadOriginal;


    /**
     * Constructor de la clase texto.
     *
     * @param fileName   Archivo Fuente.
     * @param refi       Nivel de refinamiento del texto.
     * @param lenghtText Numero de caracteres que tendra.
     */
    public Text(String fileName, int refi, int lenghtText) {
        oriText = readFile(fileName);

        while (refi > oriText.length()) {
            System.out.println("Error: El nivel de refinamiento no puede ser mayor que el numero de caracteres del texto.");
            refi = Main.writeInt(Main.INTRODUCE_NUM_REFI, Main.ERROR_LECTURA_ENTERO);
        }
        this.lenghtText = lenghtText;
        this.wordTadOriginal = new LinkedWord(refi);

        genText(refi);
    }


    /**
     * Getter del texto
     *
     * @return text
     */
    public StringBuilder getText() {
        return text;
    }


    /**
     * Metodo readFile que sirve para guardar el fichero txt en una variable Stringbuilder
     *
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
            file = new File(ROUTE
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
     */
    private void genText(int refi) {

        switch (refi) {

            case 0:
                randomChar();
                break;

            case 1:
                proporcionalRandom();
                break;

            default:
                linkedRandom();
                break;
        }
    }


    /**
     * Metodo que recursivamente va añadiendo los caracteres al StringBuilder
     *
     * @param wordTad Nivel a partir del cual se va a elegir el caracter a menos que sea el último nivel
     */
    private void putChar(LinkedWord wordTad) {

        if (text.length() < lenghtText) {

            int i;

            try {

                i = wordTad.selectRandomLetter();

                text.append(wordTad.getLetter(i));
                putChar(wordTad.get(i));

            } catch (NullPointerException ignored) {
            }

        }
    }


    /**
     * Metodo random que depende siempre de la letra anterior,
     * es decir, es enlazado.
     */
    private void linkedRandom() {
        putChar(wordTadOriginal);

        char lastLetter;
        while (text.length() < lenghtText) {

            lastLetter = text.charAt(text.length() - 1);

            //Tratamos el caso de que esa sea la ultima letra, es decir,
            // volvemos a seleccionar completamente al azar.
            if (wordTadOriginal.get(lastLetter).size() > 0) {
                putChar(wordTadOriginal.get(lastLetter));
            } else {
                putChar(wordTadOriginal);
            }
        }
    }


    /**
     * Metodo que genera un texto aleatorio eligiendo al azar entre los caracteres.
     */
    private void randomChar() {
        int rand;
        while (text.length() < lenghtText) {
            rand = (int) (Math.random() * wordTadOriginal.size());
            text.append(wordTadOriginal.getLetter(rand));
        }
    }


    /**
     * Metodo que genera un texto aleatorio eligiendo aleatoriamente y de forma proporcional entre los caracteres.
     */
    private void proporcionalRandom() {
        int i;

        while (text.length() < lenghtText) {

            i = wordTadOriginal.selectRandomLetter();

            text.append(wordTadOriginal.getLetter(i));
        }
    }

}