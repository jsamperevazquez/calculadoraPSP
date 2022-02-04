# Uso de Sockets en Java creando calculadora en Servidor

## Clases Sockets Java usadas en proyecto

* ### Socket
Representan los extremos del canal de comunicación. 

La conexión entre sockets es full-duplex. 

Hay 2 tipos: 

* SocketsStream(SocketsTCP): 

  - Los datos son transmitidos en bytes (no son empaquetados en registros o paquetes).
  - Para establecer la comunicación, utilizan el protocolo TCP.
  - La conexión empezaría una vez que los dos sockets estén conectados.
  - Para crear aplicaciones con este socket, Java proporciona dos clases,
    Socket y ServerSocket

* SocketsDatagrama(SocketsUDP): 

  - Los datos son enviados y recibidos en paquetes denominados datagramas.
  - Para establecer la comunicación entre estos sockets se usará el protocolo UDP.
  - Aunque se deben enviar datos adicionales, este socket es más eficiente que el anterior, aunque menos seguro.
  - Para crear aplicaciones con este socket, Java proporciona la clase DatagramSocket. 
  
#### Uso

Pasos de utilización: 

- Instanciarelsocket.
- Abrir el canal de E/S.
- Cerrar el socket. 

Constructores: 

- Socket()=>Creates an unconnected socket, with the system-default type of SocketImpl.
- Socket(InetAddress address, int port) =>Creates a stream socket and connects it to the specified port number at the specified IP address.
- Socket(InetAddress address, int port, InetAddress localAddr, int localPort) =>Creates a socket and connects it to the specified remote address on the specified remote port.
- Socket(SocketImpl impl) =>Creates an unconnected Socket with a user-specified SocketImpl.
- Para la confección de diferentes protocolos o variantes de los mismos Java proporciona un interfaz denominado SocketImplFactory.
- SocketImpl createSocketImpl()=>Crea una instancia de la superclase de sockets. 
- SocketImpl es la superclase de todos las implementaciones de sockets. Un
  objeto de esta clase es usado como argumento para la creación de sockets.
- Socket(String host, int port)=> Creates a stream socket and connects it to the specified port number on the named host.
- Socket(String host, int port, InetAddress localAddr, int localPort) =>Creates a socket and connects it to the specified remote host on the specified remote port.

### ServerSocket

La clase ServerSocket nos ayuda a realizar el proceso de conexión. 

– Instanciar ServerSocket 

– Escuchar en el puerto (bind) 

– ServerSocket escucha hasta que llega una conexión (accept) 

– Cuando llega una conexión devuelve un objeto Socket 

### OutputSream  
Madre de un conjunto de clases para escribir ficheros byte a byte.

![Imagen OutputSream](https://github.com/jsamperevazquez/calculadoraPSP/blob/master/Media/outputStream.png.jpeg)

### InputStream

Madre de un conjunto de clases para leer ficheros byte a byte.

![Imagen InputStream](https://github.com/jsamperevazquez/calculadoraPSP/blob/master/Media/inputStream.png.jpeg)

### DataInputStream

Java DataInputStream es una clase que usamos para leer valores de tipos de datos primitivos.  
Podemos usar este flujo junto con otros flujos de entrada como FileInputStream para leer los datos.  
Dado que lee datos como números en lugar de bytes, lo llamamos DataInputStream.  
Contiene solo una constructor que acepta un InputStream como parámetro.

### DataOutputStream

La clase DataOutputStream en Java nos permite escribir valores de tipo de datos primitivos en un flujo de salida.  
Esta operación es portátil e independiente de la máquina.  
Podemos usar cualquier clase de flujo de salida como FileOutputStream, que es un argumento para DataOutputStream constructor.

## Clases Java creadas en proyecto

### Calculadora

Clase donde se crea la interfaz de la calculadora.
Dicha interfaz ha sido creada con Swing de java, apoyándonos en el uso de formularios.
Contiene todos los listeners necesarios para que en el momento de pulsación de botones realize la operación precisa.
Todos los datos se muestran en pantalla de la interfaz pero se envía al servidor, que es el encargado de 
realizar las operaciones y devolver el resultado.  


![Imagen calculadora](https://github.com/jsamperevazquez/calculadoraPSP/blob/master/Media/calculadora.png)

### Servidor

Clase apoyada en Hilos para poder atender multiples peticiones, tanto de un cliente como varios de ellos realizando
peticiones al mismo.  
Importante seccionar el método Main a clase independiente para poder instanciar hilos en función de las llamadas que vaya recibiendo.  
En el servidor se crea socket, serverSocket y clientSocket para cumplir necesidades.
Se arranca en un puerto que se recoge como parámetro en el Main para controlar interrupción entre puertos.
Todo el código de este servidor se desarrolla en el método sobreescrito Run que ejecuta cada hilo al ejecutar el start.  
Este servidor recibe los números y operaciónes que manda la clase Calculadora, realiza las operaciones y
manda el resultado de las mismas al Cliente.  


## Ejemplo de uso de la APP  

![Gif de uso APP](https://github.com/jsamperevazquez/calculadoraPSP/blob/master/Media/usoCalculadora.gif)


