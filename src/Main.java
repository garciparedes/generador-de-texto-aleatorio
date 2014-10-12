
/**
 * 
 */

import java.util.Scanner;

/**
 * @author segarci & albamig
 */
public class Main {

    private static final String WELCOME = "Bienvenido al generador aleatorio de textos";
    private static final String INTRODUCE_NUM_CHAR = "Introduzca el numero de caracteres que tendra el texto que desea generar:";
    private static final String INTRODUCE_NUM_REFI = "Introduzca el nivel de refinamiento que tendra el texto que desea generar:";
    public static final String INTRODUCE_TEXTO = "Introduzca el nombre del fichero que contiene el texto base:";
    private static final String ERROR_LECTURA_ENTERO = "Error: El numero tiene que ser un entero positivo";

    private static Scanner sc;
    private static String fileName;
    private static int textLength;
    private static int textRefi;

    //Variable para hacer llamadas a clase Text
    private static String text;

    private static String finalText;

    /**
	 * @param args
	 */
	public static void main(String[] args) {

        System.out.println(WELCOME);

        //Leemos el numero de lineas que tendra el fichero
        textLength = writeInt(INTRODUCE_NUM_CHAR, ERROR_LECTURA_ENTERO);

		//Leemos el nivel de refinamiento que tendra el fichero
		textRefi = writeInt(INTRODUCE_NUM_REFI, ERROR_LECTURA_ENTERO);

        //Leemos el nombre de fichero
        fileName = writeString(INTRODUCE_TEXTO);

		//Creamos el objeto para el antiguo texto
		text = Text.readFile(fileName);

		//Creamos el objeto para el nuevo texto
		finalText = Text.generateRandomText(text, textRefi, textLength);

		//Imprimimos el resultado en pantalla
        System.out.println(finalText);

	}

    public static int writeInt(String message, String errorMessage){

		int n;

        System.out.println(message);

        try{
            sc = new Scanner(System.in);
            n = sc.nextInt();

            if (n<= 0){return writeInt(message, errorMessage);}

			return n;

        }catch (Exception InputMismatchException){
            System.out.println(errorMessage);
            return writeInt(message, errorMessage);

        }
    }

    public static String writeString(String message){
        System.out.println(message);
        sc = new Scanner(System.in);

        return sc.nextLine();
    }

}
