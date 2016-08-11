package net;

import java.awt.TextArea;
import java.net.ServerSocket;
import java.net.Socket;
import parser.OverParser;

public class ServerManager extends Thread implements Runnable {
    TextArea logBox;
    ServerSideResponderReceiver[] clients;
    int port;
    OverParser op;
    
    boolean allClientsConnected = false;
    
    //pomocniczo
    String saveString;

    public void setSaveString( String saveString ) {
        this.saveString = saveString;
    }
    
    public ServerManager( TextArea logBox, ServerSideResponderReceiver[] clients ) {
        this.logBox = logBox;
        this.clients = clients;
        this.port = 9191;
    }
    
    public ServerManager( int clientsNumber, int port, OverParser op, TextArea logBox ) {
        this.clients = new ServerSideResponderReceiver[clientsNumber];
        this.port = port;
        this.logBox = logBox;
        this.op = op;
    }
    
    @Override
    public void run() {
        try {
            this.logBox.append( "Oczekuję na podłączenie " + this.clients.length + " klientów\n" );
            ServerSocket serverSocket = new ServerSocket( this.port );
            int clientsIndex = 0;
            
            while ( clientsIndex < this.clients.length ) {
                Socket clientSocket = serverSocket.accept();
                this.clients[clientsIndex] = new ServerSideResponderReceiver( clientSocket, this.op, this.logBox );
                //test
                //wyslanie mapy / planszy
                this.clients[clientsIndex].sendBoard( saveString );
                //wyslanie numeru gracza
                this.clients[clientsIndex].send( "9_server_n_" + clientsIndex );
                this.clients[clientsIndex].start();
                this.logBox.append( "Podłączono klienta " + clientsIndex + "\n" );
                clientsIndex++;
            }
            
            this.logBox.append( "Podłączono wszystkich klientów\n" );
            this.allClientsConnected = true;
        } catch ( java.io.IOException e ) {
            this.logBox.append( "Niepowodzenie (" + e + ")\n" );
        }
    }
    
    public void sendToClient( int clientIndex, String message ) {
        this.clients[clientIndex].send( message );
    }
    
    public void sendBytesToClient( int clientIndex, byte[] b ) {
        this.clients[clientIndex].sendBytes( b );
    }

    public boolean isAllClientsConnected() {
        return allClientsConnected;
    }

    public void setAllClientsConnected( boolean allClientsConnected ) {
        this.allClientsConnected = allClientsConnected;
    }
    
    public void sendToAllClients( String message ) {
        for ( int i=0; i<this.clients.length; i++ ) {
            this.sendToClient( i, message );
        }
    }
}
