
/**
 * 
 */

import java.util.Scanner;

/**
 * @author segarci & albamig
 */
public class Main {

    private static final String WELCOME = "Bienvenido al generador aleatorio de textos";
    private static final String INTRODUCE_LINEA = "Introduzca el numero de caracteres que tendra el texto que desea generar:";
    private static final String INTRODUCE_TEXTO = "Introduzca el nombre del fichero que contiene el texto base:";
    private static final String ERROR_LECTURA_ENTERO = "Error al introducir el numero";

    private static Scanner sc;
    private static String fileName;
    private static int textLength;

    //Variable para hacer llamadas a clase Text
    private static Text text;

    private static String finalText;

    /**
	 * @param args
	 */
	public static void main(String[] args) {

        text = new Text();

        System.out.println(WELCOME);

        //Leemos el numero de lineas que tendra el fichero
        textLength = writeInt(INTRODUCE_LINEA, ERROR_LECTURA_ENTERO);

        //Leemos el nombre de fichero
        fileName = writeString(INTRODUCE_TEXTO);

        //Leemos el contenido del fichero
        finalText = text.readFile(fileName);

        System.out.println(finalText);

	}

    public static int writeInt(String message, String errorMessage){
        System.out.println(message);

        try{
            sc = new Scanner(System.in);
            textLength = sc.nextInt();

        }catch (Exception InputMismatchException){
            System.out.println(errorMessage);
            textLength = writeInt(message, errorMessage);

        }

        return textLength;
    }

    public static String writeString(String message){
        System.out.println(message);
        sc = new Scanner(System.in);

        return sc.nextLine();
    }

}
