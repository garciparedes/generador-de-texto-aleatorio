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
    private WordList[] multiMatrizOriginal;


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

        StringBuilder texto ;

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

        return texto;
    }


    /**
     * Metodo que recursivamente va añadiendo los caracteres al StringBuilder
     *
     * @param stringBuilder Variable a la que se van añadiendo caracteres
     * @param multiMatriz Nivel a partir del cual se va a elegir el caracter a menos que sea el último nivel
     * @return stringBuilder variable con el texto creado hasta el momento
     */
    private StringBuilder putChar(StringBuilder stringBuilder, WordList[] multiMatriz){
        int i;

        if (stringBuilder.length() < lenghtText ) {

            int numLetters;

            numLetters = WordList.numeroDeLetras(multiMatriz);

            if (numLetters != 0) {

                int valor, rand;

                rand = (int) (Math.random() * numLetters);
                i = 0;
                valor = multiMatriz[0].getNumLetter();

                while (valor <= rand) {
                    i++;
                    valor += multiMatriz[i].getNumLetter();
                }

                stringBuilder.append(multiMatriz[i].getLetter());
                stringBuilder = putChar(stringBuilder, multiMatriz[i].getContinueLetter());

            } else {

                char lastLetter = stringBuilder.charAt(stringBuilder.length() - 1);
                i = WordList.posicionLetra(lastLetter);


                //Tratamos el caso de que esa sea la ultima letra, es decir,
                // volvemos a seleccionar completamente al azar.
                if (WordList.numeroDeLetras(multiMatrizOriginal[i].getContinueLetter()) != 0) {
                    stringBuilder = putChar(stringBuilder, multiMatrizOriginal[i].getContinueLetter());
                } else {
                    stringBuilder = putChar(stringBuilder, multiMatrizOriginal);
                }
            }
        }

        return stringBuilder;
    }

    /**
     * Metodo que genera un texto aleatorio eligiendo al azar entre los caracteres.
     * @return StringBuilder con el texto.
     */
    private StringBuilder randomChar(){
        int rand;
        StringBuilder texto = new StringBuilder();
        while (texto.length() < lenghtText ){
            rand = (int)(Math.random() * WordList.getLetterArray().length);

            texto.append(WordList.getLetterArray()[rand]);
        }
        return texto;
    }

    /**
     * Metodo que genera un texto aleatorio eligiendo aleatoriamente y de forma proporcional entre los caracteres.
     *
     * @return StringBuilder con el texto.
     */
    private StringBuilder proporcionalRandom(){
        int rand, i, valor, numLetters;

        StringBuilder texto = new StringBuilder();

        numLetters = WordList.numeroDeLetras(multiMatrizOriginal);

        while (texto.length() < lenghtText ){

            i = 0;
            rand = (int) (Math.random() * numLetters);
            valor = multiMatrizOriginal[0].getNumLetter();

            while (valor <= rand) {
                i++;
                valor += multiMatrizOriginal[i].getNumLetter();
            }

            texto.append(multiMatrizOriginal[i].getLetter());
        }
        return texto;
    }
}