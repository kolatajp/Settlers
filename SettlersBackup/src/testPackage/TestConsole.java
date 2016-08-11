package testPackage;

import java.util.Random;

public class TestConsole {
    public static void main( String[] args ) {
        byte b = Byte.parseByte( "+127" );
        byte[] r = new byte[1];
        new Random().nextBytes(r);
        byte a = r[0];
        System.out.println( "a=" + a + ",b=" + b + ",a->str=" + a );
    }
    
}
