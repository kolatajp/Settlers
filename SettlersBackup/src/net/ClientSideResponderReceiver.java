package net;

import java.awt.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import parser.OverParser;

public class ClientSideResponderReceiver extends Thread implements Runnable {
    Socket serverSocket;
    TextArea logBox;
    
    DataOutputStream outStream;
    DataInputStream inStream;
    
    OverParser overParser;
    
    boolean running = true;
    
    public ClientSideResponderReceiver( Socket serverSocket, OverParser overParser, TextArea logBox ) {
        this.serverSocket = serverSocket;
        this.logBox = logBox;
        this.overParser = overParser;
        
        try {
            this.outStream = new DataOutputStream( this.serverSocket.getOutputStream() );
            this.inStream = new DataInputStream( this.serverSocket.getInputStream() );
        } catch ( java.io.IOException e ) {
            this.logBox.append( "" + e + "\n" );
        }
    }
    
    @Override
    public void run() {
        while ( this.running ) {
            try {
                String read = this.inStream.readUTF();
                this.overParser.parse( read );
                
            } catch ( java.io.IOException e ) {
                this.logBox.append( "Serwer rozłączony " + e + "\n" );
                this.running = false;
            }
        }
    }
    
    public void send( String message ) {
        try {
            this.outStream.writeUTF( message );
        } catch ( java.io.IOException e ) {
            this.logBox.append( "Niepowodzenie wysłania " + e + "\n" );
        }
    }
}
