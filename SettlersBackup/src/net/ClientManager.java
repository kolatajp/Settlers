package net;

import java.awt.TextArea;
import java.net.Socket;
import parser.OverParser;

public class ClientManager extends Thread implements Runnable {
    TextArea logBox;
    ClientSideResponderReceiver respRec;
    OverParser op;
    String address;
    int port;

    public ClientManager( String address, int port, OverParser op, TextArea logBox ) {
        this.logBox = logBox;
        this.address = address;
        this.port = port;
        this.op = op;
    }

    public void connect() {
        this.start();
    }
    
    @Override
    public void run() {
        try {
            this.logBox.append( "Próba łączenia z serwerem " + this.address + ":" + this.port + "\n" );
            Socket serverSocket = new Socket( address, port );
            this.respRec = new ClientSideResponderReceiver( serverSocket, this.op, logBox );
            this.respRec.start();
            this.logBox.append( "Połączono\n" );
        } catch ( java.net.UnknownHostException e ) {
            this.logBox.append( "Błędny adres " + e + "\n" );
        } catch ( java.io.IOException e ) {
            this.logBox.append( "Niepowodzenie " + e + "\n" );
        }
    }
    
    public void send( String message ) {
        this.respRec.send( message );
    }
}
