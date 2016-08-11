package parser;

import solver.Solver;

public class PlayerToServerParser {
    Game game;

    public PlayerToServerParser( Game game ) {
        this.game = game;
    }
    
    public String[] parse( String mes ) {
        if ( mes.contains( "none" ) ) {
            return null;
        } else if ( mes.contains( "nocosttown" ) ) {
            int playerNumber = Integer.parseInt( mes.substring( 11, 12 ) );
            int junction = Integer.parseInt( mes.substring( 13 ) );
            return this.game.buildTownNoCostAtJunctionToPlayer( junction, playerNumber );
        } else if ( mes.contains( "nocostroad" ) ) {
            int playerNumber = Integer.parseInt( mes.substring( 11, 12 ) );
            int junctionA = Integer.parseInt( mes.substring( 13, 15 ) );
            int junctionB = Integer.parseInt( mes.substring( 16 ) );
            return this.game.buildRoadNoCostAtJunctionABToPlayer( junctionA, junctionB, playerNumber );
        } else if ( mes.contains( "roll" ) ) {
            return this.game.roll();
        } else if ( mes.contains( "thiefmove" ) ) {
            int stealingPlayer = Integer.parseInt( mes.substring( 10, 11 ) );
            int victimPlayer = Integer.parseInt( mes.substring( 12, 13 ) );
            int hexNumber = Integer.parseInt( mes.substring( 14 ) );
            return this.game.resolveThiefMovementOnHexFromPlayerToVictim( hexNumber, stealingPlayer, victimPlayer );
        } else if ( mes.contains( "buildtown" ) ) {
            int playerNumber = Integer.parseInt( mes.substring( 10, 11 ) );
            int junction = Integer.parseInt( mes.substring( 12 ) );
            return this.game.buildTown( junction, playerNumber );
        } else if ( mes.contains( "buildroad" ) ) {
            int playerNumber = Integer.parseInt( mes.substring( 10, 11 ) );
            int junctionA = Integer.parseInt( mes.substring( 12, 14 ) );
            int junctionB = Integer.parseInt( mes.substring( 15 ) );
            return this.game.buildRoad( junctionA, junctionB, playerNumber );
        } else if ( mes.contains( "buildcity" ) ) {
            int playerNumber = Integer.parseInt( mes.substring( 10, 11 ) );
            int junction = Integer.parseInt( mes.substring( 12 ) );
            return this.game.buildCity( junction, playerNumber );
        } else if ( mes.contains( "buydev" ) ) {
            int playerNumber = Integer.parseInt( mes.substring( 7 ) );
            return this.game.buyDevCardToPlayer( playerNumber );
        } else if ( mes.contains( "devknight" ) ) {
            int stealing = Integer.parseInt( mes.substring( 10, 11 ) );
            int victim = Integer.parseInt( mes.substring( 12, 13 ) );
            int hex = Integer.parseInt( mes.substring( 14, 16 ) );
            int card = Integer.parseInt( mes.substring( 17 ) );
            return this.game.resolveKnightFromPlayerOnHexStealFromPlayerCardIndex( stealing, hex, victim, card );
        } else if ( mes.contains( "devmonopoly" ) ) {
            int player = Integer.parseInt( mes.substring( 12, 13 ) );
            String res = mes.substring( 14, 15 );
            int card = Integer.parseInt( mes.substring( 16 ) );
            return this.game.resolveMonopolyFromPlayerWithResourceCardIndex( player, Solver.getResourceFromString( res ), card );
        } else if ( mes.contains( "devresources" ) ) {
            int player = Integer.parseInt( mes.substring( 13, 14 ) );
            String res1 = mes.substring( 15, 16 );
            String res2 = mes.substring( 16, 17 );
            res1 += "1";
            res2 += "1";
            int card = Integer.parseInt( mes.substring( 18 ) );
            return this.game.resolveResourcesDevCardFromPlayerFromStringCardIndex( player, res1 + res2, card );
        } else if ( mes.contains( "devroads" ) ) {
            int player = Integer.parseInt( mes.substring( 9, 10 ) );
            int junctionA = Integer.parseInt( mes.substring( 11, 13 ) );
            int junctionB = Integer.parseInt( mes.substring( 14, 16 ) );
            int junctionC = Integer.parseInt( mes.substring( 17, 19 ) );
            int junctionD = Integer.parseInt( mes.substring( 20, 22 ) );
            int card = Integer.parseInt( mes.substring( 23 ) );
            return this.game.resolveRoadsDevCardFromPlayerAtJunctionABCDCardIndex( player, junctionA, junctionB, junctionC, junctionD, card );
        } else if ( mes.contains( "tradebank" ) ) {
            int player = Integer.parseInt( mes.substring( 10, 11 ) );
            String playerGives = mes.substring( 12, mes.lastIndexOf("-" ) );
            String bankGives = mes.substring( mes.lastIndexOf( "-" )+1 );
            return this.game.tradeWithBankPort( player, playerGives, bankGives );
        } else {
            //TODO
            //default domyslne zachowanie
            return null;
        }
    }
}
