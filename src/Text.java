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
        genText(refi);
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
    private void genText(int refi) {


        switch (refi){


            case 0:
                randomChar();
                break;

            case 1:
                proporcionalRandom();
                break;

            default:
                putChar(multiMatrizOriginal);

                int i;
                while(text.length() < lenghtText){

                    char lastLetter = text.charAt(text.length() - 1);
                    i = WordList.posicionLetra(multiMatrizOriginal,lastLetter);

                    //Tratamos el caso de que esa sea la ultima letra, es decir,
                    // volvemos a seleccionar completamente al azar.
                    if (WordList.numeroDeLetras(multiMatrizOriginal.get(i).getContinueLetter()) != 0) {
                        putChar(multiMatrizOriginal.get(i).getContinueLetter());
                    } else {
                        putChar(multiMatrizOriginal);
                    }

                }
                break;
        }


    }

    /**
     * Metodo que recursivamente va añadiendo los caracteres al StringBuilder
     *
     * @param multiMatriz Nivel a partir del cual se va a elegir el caracter a menos que sea el último nivel
     * @return stringBuilder variable con el texto creado hasta el momento
     */
    private void putChar(MyList<WordList> multiMatriz){



        if (text.length() < lenghtText ) {



            int i;
            try {
                int numLetters = WordList.numeroDeLetras(multiMatriz);

                int valor, rand;

                rand = (int) (Math.random() * numLetters);
                i = 0;
                valor = multiMatriz.get(i).getNumLetter();

                while (valor <= rand) {
                    i++;
                    valor += multiMatriz.get(i).getNumLetter();
                }

                text.append(multiMatriz.get(i).getLetter());
                putChar( multiMatriz.get(i).getContinueLetter());

            }catch (ArrayIndexOutOfBoundsException ignored){}

        }
    }



    /**
     * Metodo que genera un texto aleatorio eligiendo al azar entre los caracteres.
     * @return StringBuilder con el texto.
     */
    private void randomChar(){
        int rand;
        while (text.length() < lenghtText ){
            rand = (int)(Math.random() * multiMatrizOriginal.size());

            text.append(multiMatrizOriginal.get(rand).getLetter());
        }
    }


    /**
     * Metodo que genera un texto aleatorio eligiendo aleatoriamente y de forma proporcional entre los caracteres.
     *
     * @return StringBuilder con el texto.
     */
    private void proporcionalRandom(){
        int rand, i, valor, numLetters;

        numLetters = WordList.numeroDeLetras(multiMatrizOriginal);

        while (text.length() < lenghtText ){

            i = 0;
            rand = (int) (Math.random() * numLetters);
            valor = multiMatrizOriginal.get(i).getNumLetter();

            while (valor <= rand) {
                i++;
                valor += multiMatrizOriginal.get(i).getNumLetter();
            }

            text.append(multiMatrizOriginal.get(i).getLetter());
        }
    }
}