package calculadoraCliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Creado por @autor: angel
 * El  29 de Enero 2022
 *
 * @version 1.0.9
 */
public class Calculadora {
    /**
     * Panel para agregar componentes
     */
    private JPanel panel1;
    /**
     * Display de la calculadoraCliente
     */
    private JTextField textArea1;
    /**
     * Boton numerico 8 de la calculadoraCliente
     */
    private JButton a8Button;
    /**
     * Boton numerico 2 de la calculadoraCliente
     */
    private JButton button2;
    /**
     * Boton numerico 7 de la calculadoraCliente
     */
    private JButton a7Button;
    /**
     * Boton numerico 9 de la calculadoraCliente
     */
    private JButton a9Button;
    /**
     * Boton numerico 4 de la calculadoraCliente
     */
    private JButton a4Button;
    /**
     * Boton numerico 5 de la calculadoraCliente
     */
    private JButton a5Button;
    /**
     * Boton numerico 6 de la calculadoraCliente
     */
    private JButton a6Button;
    /**
     * Boton * de la calculadoraCliente
     */
    private JButton button8;
    /**
     * Boton numerico 1 de la calculadoraCliente
     */
    private JButton a1Button;
    /**
     * Boton numerico 2 de la calculadoraCliente
     */
    private JButton a2Button;
    /**
     * Boton numerico 3 de la calculadoraCliente
     */
    private JButton a3Button;
    /**
     * Boton - de la calculadoraCliente
     */
    private JButton button12;
    /**
     * Boton numerico 0 de la calculadoraCliente
     */
    private JButton a0Button;
    /**
     * Boton . de la calculadoraCliente
     */
    private JButton button14;
    /**
     * Boton = de la calculadoraCliente
     */
    private JButton button15;
    /**
     * Boton + de la calculadoraCliente
     */
    private JButton button16;
    /**
     * Boton ac de la calculadoraCliente
     */
    private JButton acButton;
    /**
     * Boton off de la calculadoraCliente
     */
    private JButton offButton;
    /**
     * Variable para acumular resultado
     */
    private static Double resultado;
    /**
     * Booleano para comprobar el primer numero
     */
    private boolean primerNumero;

    /**
     * Metodo constructor
     */
    private static final String DIRECCION = "localhost";
    private static final int PUERTO = 6000;

    public Calculadora() {
        primerNumero = true;
        ActionListener escucha = new insertarNumerosPantalla(); // Instancia en clase interna insertarNumerosPantalla
        ActionListener operador = new Operaciones(); // Instancia en la clase interna Operaciones
        // LLamamos al metodo ponerEscuchaBoton y enviamos los botones y el ActionListener deseado.
        ponerEscuchaBoton(escucha, a0Button);
        ponerEscuchaBoton(escucha, a1Button);
        ponerEscuchaBoton(escucha, a2Button);
        ponerEscuchaBoton(escucha, a3Button);
        ponerEscuchaBoton(escucha, a4Button);
        ponerEscuchaBoton(escucha, a5Button);
        ponerEscuchaBoton(escucha, a6Button);
        ponerEscuchaBoton(escucha, a7Button);
        ponerEscuchaBoton(escucha, a8Button);
        ponerEscuchaBoton(escucha, a9Button);
        ponerEscuchaBoton(operador, button2);
        ponerEscuchaBoton(operador, button8);
        ponerEscuchaBoton(operador, button12);
        ponerEscuchaBoton(operador, button15);
        ponerEscuchaBoton(operador, button16);

        // Creamos los Listener desde el form con los botones que deseamos controlar de modo independiente
        acButton.addActionListener(actionEvent -> {
            textArea1.setText(null);
            textArea1.setText("0");
            resultado = 0.0;
            primerNumero = true;
        });
        offButton.addActionListener(actionEvent -> {
            try {
                apagarCalculadora();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        button14.addActionListener(actionEvent -> {
            String texto = textArea1.getText();
            if (texto.length() <= 1 && texto.equals("0")) {
                // Hay el 0 en el textArea (valor por defecto)
                textArea1.setText(textArea1.getText().concat("."));
                primerNumero = false;
            } else {
                // Ya hay numero en el Field
                if (!validarPunto(textArea1.getText())) {
                    textArea1.setText(textArea1.getText().concat("."));

                }
            }


        });
    }


    /**
     * Metodo main Donde instanciamos el JFrame
     *
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculadora");
        frame.setContentPane(new Calculadora().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500); // Le damos un tamano deseado porque el pack() lo pone demasiado pequeno
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); //Situamos el frame en el centro de la pantalla
    }


    /**
     * Metodo que pone a la escucha los botones que recibe como parametro
     *
     * @param escucha ActionListener detecta y maneja eventos de accion (ActionEvent)
     * @param boton   JButon de java.swing
     */
    public void ponerEscuchaBoton(ActionListener escucha, JButton boton) {
        boton.addActionListener(escucha); // Ponemos a la escucha el boton que reciba como parametro con el ActionListener que reciba
    }

    /**
     * Metodo para apagar la calculadoraCliente al darle al boton "off"
     *
     * @throws InterruptedException Para el control de excepciones
     */
    public void apagarCalculadora() throws InterruptedException {
        /**
         * Los segundos que tardara la calculadoraCliente en ser apagada
         */
        int segundos = 3;

        // Mientras los segundos no lleguen a 0
        while (segundos != 0) {
            Thread.sleep(1000); // Pausa el hilo de JAVA (sistema) durante 1000 ms
            segundos--; //Decrementamos los segundos en 1

        }
        // Cuando los segundos lleguen a 0 se sale de la app
        if (segundos == 0) {
            System.exit(0);
        }
    }

    /**
     * Metodo creado desde la paleta para ajustar (custom) los componentes deseados
     */
    private void createUIComponents() {
        // Instanciamos por obligacion al declarar custom los componentes seleccionados
        textArea1 = new JTextField("0"); // Le ponemos 0 como valor en el constructor
    }


    // Metodos de validacion

    /**
     * Metodo que se encarga de validar el . de la calculadoraCliente.
     * No podra ponerse mas de uno.
     * Si se pulsa como boton inicial toma el "0" como primer valor.
     *
     * @param textoLabel con el valor que tine el Display de la calculadoraCliente en ese instante
     * @return booleano para comprobar si la cadena String contiene un .
     */
    public boolean validarPunto(String textoLabel) {
        /**
         * Booleano iniciado en false
         */
        boolean validacion = false;
        //Bucle para comprobar el tama�o del String del display
        for (int i = 0; i < textoLabel.length(); i++) {
            if (textoLabel.substring(i, i + 1).equals(".")) { // Va comprobando uno a uno cada caracter del String con m�todo SubString
                validacion = true; // Si encuentra el . cambiamos el bool a true y rompemos el bucle
                break;
            }
        }
        return validacion;
    }

    /**
     * Clase interna que controlar� los eventos que insertan los n�meros en el Display de la calculadoraCliente.
     * Implementa la interface ActionListener con el metodo actionPerformed (actua al pulsar)
     *
     * @version 0.0.12
     */
    class insertarNumerosPantalla implements ActionListener {
        /**
         * String que almacena entradas teclado calculadoraCliente
         */
        private String entrada;

        /**
         * Metodo de ActionListener para controlar los eventos de botones
         *
         * @param actionEvent para acceder a metodos
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            // Asignamos al String los botones pulsados con getActionCommand()
            entrada = actionEvent.getActionCommand();
            if (primerNumero) { // si el primer numero es true
                textArea1.setText(""); // ponemos la pantalla en blanco
                primerNumero = false;
            }
            textArea1.setText(textArea1.getText().concat(entrada)); // Lo que tengo en la pantalla concatenado con el dato de entrada
        }
    }

    /**
     * Clase interna que controlara los eventos para realizar los Operaciones de la calculadoraCliente
     *
     * @version 0.1.3
     */
    class Operaciones extends Thread implements ActionListener {
        /**
         * String con ultima operacion marcada
         */
        private String ultimaOperacion = "=";
        /**
         * Double para almacenar el dato del display
         */
        private double datoConvertido;
        private static String datoPantalla;
        private ConectarServer conexServer;


        /**
         * Metodo de ActionListener para controlar los eventos de botones
         *
         * @param actionEvent acceso a metodos
         */
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Thread hilo = new Thread(new Thread(() -> {
                try {
                    String operacion = actionEvent.getActionCommand();
                    conexServer = new ConectarServer();
                    datoConvertido = Double.parseDouble(textArea1.getText()); // Pasamos a Double los datos recogidos del display de la calculadoraCliente
                    datoPantalla = String.valueOf(datoConvertido);
                    conexServer.enviarNumerosServidor(datoPantalla.concat(",").concat(operacion));
                    ultimaOperacion = operacion; // Asignamos a ultimaOperacion el valor del boton de operacion pulsado
                } finally {
                    try {
                        conexServer.cerrarConexion();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }));
            hilo.start();
            primerNumero = true; // Dejamos de concatenar en el display
        }
        public void acumularOperacion(Double numero, String operacio){

        }

    }

    /**
     * Clase interna para conexión y envío datos con Server
     * @author joseangelsamperevazquez
     * @version 0.0.3
     */
    class ConectarServer  extends Thread {

        static boolean infintoC = true;
        static Socket skCliente;

        /**
         * Metodo para abrir comunicacion con Servidor
         * Encargado de enviar el número marcado en calculadora y operacion
         * @param numero marcado en teclado de calculadora, concatenada la operacion
         */
        public synchronized void enviarNumerosServidor(String numero) {
                try {
                    skCliente = new Socket(DIRECCION, PUERTO);
                    while (infintoC) {
                        if ("SALIR".equalsIgnoreCase(numero)) {
                            infintoC = false;
                            numero = "salir";
                        } else {
                            OutputStream auxOut = skCliente.getOutputStream();
                            DataOutputStream infoSalida = new DataOutputStream(auxOut);
                            infoSalida.writeUTF(numero); // Enviamos al server el parámetro recibido
                            recibirDatosServer();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        /**
         * Metodo para recibir mensajes de vuelta del servidor
         * Uso de DataImputStream e InputStream
         * @throws IOException
         */
        public void recibirDatosServer() throws IOException {
            // Recibo e imprimo en pantalla el msje q me envía el server
            // infoEntrada -> informcion que ingresa al cliente
            InputStream auxIn = skCliente.getInputStream();
            DataInputStream infoEntrada = new DataInputStream(auxIn);
            String lectura = infoEntrada.readUTF();
            System.out.println(lectura);
            textArea1.setText(lectura);
        }

        /**
         * Metodo encargado de cierre de conexion con Server
         * @throws IOException
         */
        public void cerrarConexion() throws IOException {
            skCliente.close();
        }
    }
}

