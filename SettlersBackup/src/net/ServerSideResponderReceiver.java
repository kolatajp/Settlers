package net;

import java.awt.TextArea;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import parser.OverParser;
import serializer.SaverLoader;

public class ServerSideResponderReceiver extends Thread implements Runnable {
    Socket clientSocket;
    TextArea logBox;

    DataOutputStream outStream;
    DataInputStream inStream;

    OverParser overParser;

    boolean running = true;

    //TODO
    public ServerSideResponderReceiver( Socket clientSocket, OverParser overParser, TextArea logBox ) {
        this.clientSocket = clientSocket;
        this.logBox = logBox;
        this.overParser = overParser;

        try {
            this.outStream = new DataOutputStream( this.clientSocket.getOutputStream() );
            this.inStream = new DataInputStream( this.clientSocket.getInputStream() );
        } catch ( java.io.IOException e ) {
            this.logBox.append( e + "\n" );
        }
    }
    
    //wyslanie mapy / planszy
    public void sendBoard( String saveString ) {
        this.send( "9_server_v_" + saveString );
    }

    //TODO
    @Override
    public void run() {
        while ( running ) {
            try {
                String read = this.inStream.readUTF();
                this.overParser.parse( read );

            } catch ( java.io.IOException e ) {
                this.logBox.append( "Klient rozłączony " + e + "\n" );
                this.running = false;
            }
        }
    }

    //TODO
    public void send( String message ) {
        try {
            this.outStream.writeUTF( message );
        } catch ( java.io.IOException e ) {
            this.logBox.append( e + "\n" );
        }
    }

    public void sendBytes( byte[] b ) {
        try {
            this.outStream.write( b );
        } catch ( java.io.IOException e ) {
            this.logBox.append( e + "\n" );
        }
    }
}
