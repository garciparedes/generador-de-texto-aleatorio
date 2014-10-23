
/**
 * 
 */

import java.util.Scanner;

/**
 * @author segarci & albamig
 */
public class Main {

    private static final String WELCOME = "Bienvenido al generador aleatorio de textos";
    private static final String INTRODUCE_NUM_CHAR = "Introduzca el numero de caracteres que tendra el texto que desea generar: ";
    private static final String INTRODUCE_NUM_REFI = "Introduzca el nivel de refinamiento que tendra el texto que desea generar: ";
    public static final String INTRODUCE_TEXTO = "Introduzca el nombre del fichero que contiene el texto base: ";
    private static final String ERROR_LECTURA_ENTERO = "Error: El numero tiene que ser un entero positivo.";

    private static Scanner sc;

    /**
	 * @param args
	 */
	public static void main(String[] args) {

        System.out.println(WELCOME);

        //Leemos el numero de lineas que tendra el fichero
        int textLength = writeInt(INTRODUCE_NUM_CHAR, ERROR_LECTURA_ENTERO);

		//Leemos el nivel de refinamiento que tendra el fichero
		int textRefi = writeInt(INTRODUCE_NUM_REFI, ERROR_LECTURA_ENTERO);

        //Leemos el nombre de fichero
        String fileName = writeString(INTRODUCE_TEXTO);

		//Creamos el objeto para el antiguo texto
		Text text = new Text(fileName);

		//Creamos el objeto para el nuevo texto
		Text finalText = new Text(text, textRefi, textLength);

		//Imprimimos el resultado en pantalla
        System.out.println(finalText.getText());

	}
	/**
	 * Función que pide la entrada de un entero con detección de errores
	 * @param message Mensaje de petición que se mostrará
	 * @param errorMessage Mensaje de error que puede surgir
	 * @return Devuelve un número positivo introducido por entrada estandar
	 */
    public static int writeInt(String message, String errorMessage){

        System.out.print(message);

        try{
            sc = new Scanner(System.in);
            int n = sc.nextInt();

            if (n < 0) {
            		return writeInt(message, errorMessage);
            	}

			return n;

        }catch (Exception InputMismatchException){
            System.out.println(errorMessage);
            return writeInt(message, errorMessage);

        }
    }
    /**
     * Función que pide el nombre de un fichero de texto
     * @param message  Mensaje de petición que se mostrará
     * @return Devuelve el nombre de un fichero de texto
     */
    public static String writeString(String message){
        System.out.print(message);
        sc = new Scanner(System.in);

        return sc.nextLine();
    }

}
