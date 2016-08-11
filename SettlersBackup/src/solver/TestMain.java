/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

/**
 *
 * @author Paweł
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        System.out.println( Solver.getNumberFromId( "none_junction_53" ) );
        System.out.println( Solver.getPlayerNumberFromString( "nasjk23nkp5nn3jk12" ) );
        String resString = "o2d50c1iiio5";
        int[] kek = Solver.getResourcesFromString( resString );
        for ( int i=0; i<kek.length; i++ ) {
            System.out.println( "[" + i + "]=" + kek[i] );
        }
        
        int[] numbers = { 0, 1, 2, 3, 4, 5 };
        for ( int i : numbers ) {
            System.out.println( i );
        }
    }
    
}
