package serializer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.nio.file.Files.readAllBytes;
import java.nio.file.Paths;
import mechanics.GameMechanics;

public class SaverLoader {
    public static String save( GameMechanics mechanics ) throws java.io.IOException {
        FileOutputStream fos = new FileOutputStream( "save.ser" );
        ObjectOutputStream ous = new ObjectOutputStream( fos );
        ous.writeObject( mechanics );
        
        byte[] bytes = readAllBytes( Paths.get( "save.ser" ) );
        StringBuilder sb = new StringBuilder();
        for ( int i = 0; i < bytes.length; i++ ) {
            if ( (int)bytes[i] > 0 ) {
                sb.append( "+" );
            }
            sb.append( "" + (int)bytes[i] + "," );
        }
        
        String out = sb.toString();
        out = out.substring( 0, out.length()-1 );
        
        return out;
    }

    public static GameMechanics load( String s ) throws java.io.IOException, ClassNotFoundException {
        String[] numericBytes = s.split( "," );
        byte[] outBytes = new byte[numericBytes.length];
        
        for ( int i=0; i<numericBytes.length; i++ ) {
            outBytes[i] = Byte.parseByte( numericBytes[i] );
        }
        
        FileOutputStream fos = new FileOutputStream( "load.ser" );
        fos.write( outBytes );
        fos.close();

        FileInputStream fileIn = new FileInputStream( "load.ser" );
        ObjectInputStream in = new ObjectInputStream( fileIn );
        return (GameMechanics) in.readObject();
    }
}
