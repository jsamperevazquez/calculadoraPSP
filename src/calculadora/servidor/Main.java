package calculadora.servidor;

/**
 * Main para poner servidor a escucha
 * Mediante uso de hilos
 */
public class Main {

    public static void main(String[] args) {
        int puerto = 6000; // Número de puerto de escucha para la conexión de clientes

            Servidor servidor = new Servidor(puerto);

            //Arrancamos hilo el para múltiples conexiones
            servidor.start();
        }
    }
