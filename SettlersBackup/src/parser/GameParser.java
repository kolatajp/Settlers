package parser;

import mechanics.Card;
import mechanics.GameMechanics;
import solver.Solver;

public class GameParser {
    GameMechanics mechanics;
    Game game;

    public GameParser( GameMechanics mechanics ) {
        this.mechanics = mechanics;
        this.game = new parser.Game(mechanics );
    }

    public GameParser( Game game ) {
        this.game = game;
        this.mechanics = this.game.mechanics;
    }
    
    //zaslepka / stub
    public GameParser() {
        
    }

    public void parse( String mes ) {
        //tutaj ifelse na wszystkie komunikaty
        if ( mes.equals( "none" ) ) {

        } else if ( mes.contains( "rollgive" ) ) {  //rozdaj surowce z rzutu kostka
            //jezeli przydzielamy surowce z rzutu kostka
            mes = mes.substring( 9 );
            String[] players = mes.split( "," );
            for ( int i = 0; i < /*this.mechanics.getPlayers().length*/ players.length; i++ ) {
                if ( players[i].length() > 0 ) {
                    players[i] = players[i].substring( 2 );
                    //deb
                    System.out.println( players[i] );

                    if ( players[i].length() > 1 ) {
                        this.mechanics.addResourcesToPlayer( Solver.getResourcesFromString( players[i] ), i );
                    }
                }
            }
        } else if ( mes.contains( "btown" ) ) { //buduj miasto/osade
            int playerNumber, buildJunction;
            playerNumber = Integer.parseInt( mes.substring( 6, 7 ) );
            buildJunction = Integer.parseInt( mes.substring( 8 ) );
            this.mechanics.setTownAtJunctionToPlayer( buildJunction, playerNumber );
        } else if ( mes.contains( "bcity" ) ) {
            int playerNumber, buildJunction;
            playerNumber = Integer.parseInt( mes.substring( 6, 7 ) );
            buildJunction = Integer.parseInt( mes.substring( 8 ) );
            this.mechanics.setCityAtJunctionToPlayer( buildJunction, playerNumber );
        } else if ( mes.contains( "res" ) && !mes.contains( "dev" ) ) {   //dodaj/odejmij surowce od gracza
            int sign = 1;
            if ( mes.contains( "sub" ) ) {
                sign = -1;
            }

            String[] players = mes.substring( mes.lastIndexOf( "-" ) + 1 ).split( "," );
            int playerNumber;
            for ( int i = 0; i < players.length; i++ ) {
                playerNumber = Integer.parseInt( players[i].substring( 1, 2 ) );
                if ( sign > 0 ) {
                    this.mechanics.giveResourcesToPlayer( Solver.getResourcesFromString( players[i].substring( 2 ) ), playerNumber );
                } else {
                    this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( players[i].substring( 2 ) ), playerNumber );
                }
            }
        } else if ( mes.contains( "broad" ) ) { //budowa drogi
            int playerNumber, buildJunction;
            playerNumber = Integer.parseInt( mes.substring( 6, 7 ) );
            int junctionA = Integer.parseInt( mes.substring( 8, 10 ) );
            int junctionB = Integer.parseInt( mes.substring( 11 ) );
            this.mechanics.addRoadBetweenJunctionsToPlayer( junctionA, junctionB, playerNumber );
        } else if ( mes.contains( "thief" ) ) { //ustawienie zlodzieja na miejscu
            int junction = Integer.parseInt( mes.substring( 6 ) );
            for ( int i = 1; i < this.mechanics.getHexs().length; i++ ) {
                this.mechanics.getHexs()[i].setEnabled( true );
                if ( i == junction ) {
                    this.mechanics.getHexs()[i].setEnabled( false );
                }
            }
        } else if ( mes.contains( "thief-" ) ) {    //przestawianie zlodzieja
            int hexNumber = Integer.parseInt( mes.substring( 6 ) );
            this.game.placeThiefOnHex( hexNumber );
        } else if ( mes.contains( "devCard-" ) ) {  //daj lub wystaw karte
            if ( mes.contains( "give" ) ) {         //daj karte graczowi
                int playerNumber = Integer.parseInt( mes.substring( 8, 9 ) );
                if ( mes.contains( "point" ) ) {
                    this.mechanics.getPlayers()[playerNumber].addPointsHidden( 1 );
                } else {
                    this.mechanics.addDevelopementCardToPlayerExplicite( new Card( Solver.cardName2cardClass( mes ) ), playerNumber );
                }
            } else {                                //place - wystaw
                int playerNumber = Integer.parseInt( mes.substring( 8, 9 ) );
                int cardIndex = Integer.parseInt( mes.substring( 16 ) );
                this.mechanics.removeDevelopementCardFromPlayer( playerNumber, cardIndex );
            }
        } else if ( mes.contains( "points" ) ) {
            int playerNumber = Integer.parseInt( mes.substring( 7, 8 ) );
            int publicPoints = Integer.parseInt( mes.substring( 9, 11 ) );
            int hiddenPoints = Integer.parseInt( mes.substring( 12 ) );
            this.mechanics.getPlayers()[playerNumber].setPointsPublic( publicPoints );
            this.mechanics.getPlayers()[playerNumber].setPointsHidden( hiddenPoints );
        } else {
            //TODO
            //defaultowe domyslne zachowanie kek
        }
    }
}
