package mechanics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class GameMechanics implements java.io.Serializable {
    GameState gameState = GameState.INACTIVE;

    JunctionEntity[] junctions;
    Hex[] hexs;

    Player players[];

    LinkedList<Card> cardStack;
    
    int playerTurn = 0;

    public GameMechanics( int playersNumber ) {
        this.junctions = new JunctionEntity[55];
        this.hexs = new Hex[20];
        this.players = new Player[playersNumber];

        //bezargumentowa inicjalizacja junctions
        for ( int i = 1; i < this.junctions.length; i++ ) {
            this.junctions[i] = new JunctionEntity();
        }

        //losowanie planszy
        //inicjalizacja losowanych wektorow
        LinkedList<Resource> resources = new LinkedList();
        LinkedList<Integer> numbers = new LinkedList();

        for ( int i = 0; i < 4; i++ ) {
            resources.add( Resource.WOOD );
        }
        for ( int i = 0; i < 4; i++ ) {
            resources.add( Resource.WOOL );
        }
        for ( int i = 0; i < 4; i++ ) {
            resources.add( Resource.WHEAT );
        }
        for ( int i = 0; i < 3; i++ ) {
            resources.add( Resource.STONE );
        }
        for ( int i = 0; i < 3; i++ ) {
            resources.add( Resource.CLAY );
        }
        resources.add( Resource.DESERT );

        numbers.add( 2 );
        numbers.add( 3 );
        numbers.add( 3 );
        numbers.add( 4 );
        numbers.add( 4 );
        numbers.add( 5 );
        numbers.add( 5 );
        numbers.add( 6 );
        numbers.add( 6 );
        numbers.add( 8 );
        numbers.add( 8 );
        numbers.add( 9 );
        numbers.add( 9 );
        numbers.add( 10 );
        numbers.add( 10 );
        numbers.add( 11 );
        numbers.add( 11 );
        numbers.add( 12 );

        Random randomGen = new Random();
        int pickedNumber;
        Resource pickedResource;

        //wlasciwa petla
        for ( int i = 1; i < this.hexs.length; i++ ) {
            pickedResource = resources.remove( randomGen.nextInt( resources.size() ) );
            if ( pickedResource != Resource.DESERT ) {
                pickedNumber = numbers.remove( randomGen.nextInt( numbers.size() ) );
            } else {
                pickedNumber = -1;
            }
            this.hexs[i] = new Hex( pickedResource, pickedNumber );
            if ( pickedResource == Resource.DESERT ) {
                this.hexs[i].setEnabled( false );
            }
        }

        //tasowanie kart
        ArrayList<Card> temporaryCardStack = new ArrayList();
        for ( int i = 0; i < 14; i++ ) {
            temporaryCardStack.add( new Card( CardClass.KNIGHT ) );
        }

        for ( int i = 0; i < 2; i++ ) {
            temporaryCardStack.add( new Card( CardClass.MONOPOLY ) );
            temporaryCardStack.add( new Card( CardClass.RESOURCES ) );
            temporaryCardStack.add( new Card( CardClass.ROADS ) );
        }

        for ( int i = 0; i < 5; i++ ) {
            temporaryCardStack.add( new Card( CardClass.POINT ) );
        }

        Card pickedCard;

        this.cardStack = new LinkedList();
        int originalCardStackSize = temporaryCardStack.size();
        for ( int i = 0; i < originalCardStackSize; i++ ) {
            pickedCard = temporaryCardStack.remove( randomGen.nextInt( temporaryCardStack.size() ) );
            this.cardStack.add( pickedCard );
        }

        //inicjalizacja graczy
        //TODO
        for ( int i = 0; i < this.players.length; i++ ) {
            this.players[i] = new Player();
        }

        this.setJunctionsNeighbours();
        this.setAllHexsJunctions();
        this.setRandomHarbours();
    }

    public Player[] getPlayers() {
        return players;
    }

    //uzywaj jako 1
    private void setJunctionsNeighbours() {
        junctions[1].setNeighbourJunctionsNumbers( 4, 2, 0 );
        junctions[2].setNeighbourJunctionsNumbers( 1, 5, 0 );
        junctions[3].setNeighbourJunctionsNumbers( 8, 4, 0 );
        junctions[4].setNeighbourJunctionsNumbers( 3, 1, 9 );
        junctions[5].setNeighbourJunctionsNumbers( 2, 10, 6 );
        junctions[6].setNeighbourJunctionsNumbers( 5, 11, 0 );
        junctions[7].setNeighbourJunctionsNumbers( 13, 8, 0 );
        junctions[8].setNeighbourJunctionsNumbers( 14, 7, 3 );
        junctions[9].setNeighbourJunctionsNumbers( 4, 15, 10 );
        junctions[10].setNeighbourJunctionsNumbers( 9, 16, 5 );
        junctions[11].setNeighbourJunctionsNumbers( 6, 17, 12 );
        junctions[12].setNeighbourJunctionsNumbers( 11, 18, 0 );
        junctions[13].setNeighbourJunctionsNumbers( 7, 19, 0 );
        junctions[14].setNeighbourJunctionsNumbers( 8, 20, 15 );
        junctions[15].setNeighbourJunctionsNumbers( 14, 21, 9 );
        junctions[16].setNeighbourJunctionsNumbers( 10, 22, 17 );
        junctions[17].setNeighbourJunctionsNumbers( 16, 11, 23 );
        junctions[18].setNeighbourJunctionsNumbers( 12, 24, 0 );
        junctions[19].setNeighbourJunctionsNumbers( 13, 25, 20 );
        junctions[20].setNeighbourJunctionsNumbers( 19, 26, 14 );
        junctions[21].setNeighbourJunctionsNumbers( 15, 27, 22 );
        junctions[22].setNeighbourJunctionsNumbers( 21, 28, 16 );
        junctions[23].setNeighbourJunctionsNumbers( 17, 24, 29 );
        junctions[24].setNeighbourJunctionsNumbers( 18, 30, 23 );
        junctions[25].setNeighbourJunctionsNumbers( 19, 31, 0 );
        junctions[26].setNeighbourJunctionsNumbers( 20, 32, 27 );
        junctions[27].setNeighbourJunctionsNumbers( 21, 33, 26 );
        junctions[28].setNeighbourJunctionsNumbers( 22, 34, 29 );
        junctions[29].setNeighbourJunctionsNumbers( 28, 23, 35 );
        junctions[30].setNeighbourJunctionsNumbers( 24, 36, 0 );
        junctions[31].setNeighbourJunctionsNumbers( 25, 37, 32 );
        junctions[32].setNeighbourJunctionsNumbers( 31, 26, 38 );
        junctions[33].setNeighbourJunctionsNumbers( 27, 34, 39 );
        junctions[34].setNeighbourJunctionsNumbers( 28, 33, 40 );
        junctions[35].setNeighbourJunctionsNumbers( 29, 41, 36 );
        junctions[36].setNeighbourJunctionsNumbers( 35, 30, 42 );
        junctions[37].setNeighbourJunctionsNumbers( 31, 43, 0 );
        junctions[38].setNeighbourJunctionsNumbers( 32, 39, 44 );
        junctions[39].setNeighbourJunctionsNumbers( 38, 33, 45 );
        junctions[40].setNeighbourJunctionsNumbers( 34, 46, 41 );
        junctions[41].setNeighbourJunctionsNumbers( 40, 47, 35 );
        junctions[42].setNeighbourJunctionsNumbers( 36, 48, 0 );
        junctions[43].setNeighbourJunctionsNumbers( 37, 44, 0 );
        junctions[44].setNeighbourJunctionsNumbers( 43, 49, 38 );
        junctions[45].setNeighbourJunctionsNumbers( 50, 39, 46 );
        junctions[46].setNeighbourJunctionsNumbers( 45, 51, 40 );
        junctions[47].setNeighbourJunctionsNumbers( 41, 52, 48 );
        junctions[48].setNeighbourJunctionsNumbers( 47, 42, 0 );
        junctions[49].setNeighbourJunctionsNumbers( 44, 50, 0 );
        junctions[50].setNeighbourJunctionsNumbers( 49, 45, 53 );
        junctions[51].setNeighbourJunctionsNumbers( 46, 54, 52 );
        junctions[52].setNeighbourJunctionsNumbers( 51, 47, 0 );
        junctions[53].setNeighbourJunctionsNumbers( 50, 54, 0 );
        junctions[54].setNeighbourJunctionsNumbers( 53, 51, 0 );

    }

    private void setHexJunctions( int hexNum, int a, int b, int c, int d, int e, int f ) {
        this.hexs[hexNum].setJunctions( new JunctionEntity[]{ this.junctions[a], this.junctions[b],
                                                              this.junctions[c], this.junctions[d],
                                                              this.junctions[e], this.junctions[f] } );
    }

    //uzyj jako 2
    private void setAllHexsJunctions() {
        setHexJunctions( 1, 1, 2, 4, 5, 9, 10 );
        setHexJunctions( 2, 3, 4, 8, 9, 14, 15 );
        setHexJunctions( 3, 5, 6, 10, 11, 16, 17 );
        setHexJunctions( 4, 7, 8, 13, 14, 19, 20 );
        setHexJunctions( 5, 9, 10, 15, 16, 21, 22 );
        setHexJunctions( 6, 11, 12, 17, 18, 23, 24 );
        setHexJunctions( 7, 14, 15, 20, 21, 26, 27 );
        setHexJunctions( 8, 16, 17, 22, 23, 28, 29 );
        setHexJunctions( 9, 19, 20, 25, 26, 31, 32 );
        setHexJunctions( 10, 21, 22, 27, 28, 33, 34 );
        setHexJunctions( 11, 23, 24, 29, 30, 35, 36 );
        setHexJunctions( 12, 26, 27, 32, 33, 38, 39 );
        setHexJunctions( 13, 28, 29, 34, 35, 40, 41 );
        setHexJunctions( 14, 31, 32, 37, 38, 43, 44 );
        setHexJunctions( 15, 33, 34, 39, 40, 45, 46 );
        setHexJunctions( 16, 35, 36, 41, 42, 47, 48 );
        setHexJunctions( 17, 38, 39, 44, 45, 49, 50 );
        setHexJunctions( 18, 40, 41, 46, 47, 51, 52 );
        setHexJunctions( 19, 45, 46, 50, 51, 53, 54 );
    }

    //uzyj jako 3
    private void setRandomHarbours() {
        LinkedList<Resource> harbours = new LinkedList<Resource>();
        harbours.add( Resource.WOOD );
        harbours.add( Resource.WOOL );
        harbours.add( Resource.WHEAT );
        harbours.add( Resource.STONE );
        harbours.add( Resource.CLAY );
        harbours.add( Resource.NONE );
        harbours.add( Resource.NONE );
        harbours.add( Resource.NONE );
        harbours.add( Resource.NONE );

        //ustawia porty na wczesniej sprecyzowanych miejscach
        Random generator = new Random();

        //3-4
        int randomNumber = generator.nextInt( harbours.size() );
        this.junctions[3].harbour = harbours.get( randomNumber );
        this.junctions[4].harbour = harbours.get( randomNumber );
        harbours.remove( randomNumber );

        //5-6
        randomNumber = generator.nextInt( harbours.size() );
        this.junctions[6].harbour = harbours.get( randomNumber );
        this.junctions[5].harbour = harbours.get( randomNumber );
        harbours.remove( randomNumber );

        //12-18
        randomNumber = generator.nextInt( harbours.size() );
        this.junctions[12].harbour = harbours.get( randomNumber );
        this.junctions[18].harbour = harbours.get( randomNumber );
        harbours.remove( randomNumber );

        //30-36
        randomNumber = generator.nextInt( harbours.size() );
        this.junctions[30].harbour = harbours.get( randomNumber );
        this.junctions[36].harbour = harbours.get( randomNumber );
        harbours.remove( randomNumber );

        //47-52
        randomNumber = generator.nextInt( harbours.size() );
        this.junctions[47].harbour = harbours.get( randomNumber );
        this.junctions[52].harbour = harbours.get( randomNumber );
        harbours.remove( randomNumber );

        //53-54
        randomNumber = generator.nextInt( harbours.size() );
        this.junctions[53].harbour = harbours.get( randomNumber );
        this.junctions[54].harbour = harbours.get( randomNumber );
        harbours.remove( randomNumber );

        //49-44
        randomNumber = generator.nextInt( harbours.size() );
        this.junctions[44].harbour = harbours.get( randomNumber );
        this.junctions[49].harbour = harbours.get( randomNumber );
        harbours.remove( randomNumber );

        //37-31
        randomNumber = generator.nextInt( harbours.size() );
        this.junctions[31].harbour = harbours.get( randomNumber );
        this.junctions[37].harbour = harbours.get( randomNumber );
        harbours.remove( randomNumber );

        //13-7
        randomNumber = generator.nextInt( harbours.size() );
        this.junctions[7].harbour = harbours.get( randomNumber );
        this.junctions[13].harbour = harbours.get( randomNumber );
        harbours.remove( randomNumber );
    }

    public Hex[] getHexs() {
        return hexs;
    }

    public JunctionEntity[] getJunctions() {
        return junctions;
    }

    //kontrolki nazwane / czyli akcje
    public void setTownAtJunctionToPlayer( int junctionNumber, int playerNumber ) {
        this.junctions[junctionNumber].setEntityClass( JunctionEntityClass.TOWN );
        this.junctions[junctionNumber].setPlayerNumber( playerNumber );
        //deb
        //experiment
        this.players[playerNumber].towns++;
        this.players[playerNumber].townsLeft--;
    }

    public void setCityAtJunctionToPlayer( int junctionNumber, int playerNumber ) {
        this.junctions[junctionNumber].setEntityClass( JunctionEntityClass.CITY );
        this.junctions[junctionNumber].setPlayerNumber( playerNumber );
        //deb
        //experiment
        this.players[playerNumber].cities++;
        this.players[playerNumber].citiesLeft--;
        this.players[playerNumber].towns--;
        this.players[playerNumber].townsLeft++;
    }

    public void addRoadBetweenJunctionsToPlayer( int junctionA, int junctionB, int playerNumber ) {
        this.players[playerNumber].addRoad( junctionA, junctionB );
    }

    //0-wool, 1-wood, 2-stone, 3-wheat, 4-clay
    public void addResourcesToPlayer( int[] resources, int playerNumber ) {
        Player temporaryPlayer = this.players[playerNumber];
        temporaryPlayer.setWool( temporaryPlayer.getWool() + resources[0] );
        temporaryPlayer.setWood( temporaryPlayer.getWood() + resources[1] );
        temporaryPlayer.setStone( temporaryPlayer.getStone() + resources[2] );
        temporaryPlayer.setWheat( temporaryPlayer.getWheat() + resources[3] );
        temporaryPlayer.setClay( temporaryPlayer.getClay() + resources[4] );
    }

    public void removeResourcesFromPlayer( int[] resources, int playerNumber ) {
        Player temporaryPlayer = this.players[playerNumber];
        temporaryPlayer.setWool( temporaryPlayer.getWool() - resources[0] );
        temporaryPlayer.setWood( temporaryPlayer.getWood() - resources[1] );
        temporaryPlayer.setStone( temporaryPlayer.getStone() - resources[2] );
        temporaryPlayer.setWheat( temporaryPlayer.getWheat() - resources[3] );
        temporaryPlayer.setClay( temporaryPlayer.getClay() - resources[4] );
    }

    //tylko nazwa, dziala tka samo
    public void giveResourcesToPlayer( int[] resources, int playerNumber ) {
        this.addResourcesToPlayer( resources, playerNumber );
    }

    public int rollTwoDices() {
        Random randomGen = new Random();
        int output = randomGen.nextInt( 6 )+1;
        output += randomGen.nextInt( 6 )+1;
        return output;
    }

    public void takeThiefFromHex( int hexNumber ) {
        this.hexs[hexNumber].setEnabled( true );
    }

    public void putThiefOnHex( int hexNumber ) {
        this.hexs[hexNumber].setEnabled( false );
    }
    
    public void putThiefOnHexAndReload( int hexNumber ) {
        for ( int i=1; i<this.hexs.length; i++ ) {
            this.hexs[i].setEnabled( true );
        }
        this.hexs[hexNumber].setEnabled( false );
    }

    public GameState getGameState() {
        return gameState;
    }

    public LinkedList<Card> getCardStack() {
        return cardStack;
    }

    public Card giveDevelopementCardToPlayer( int playerNumber ) {
        try {
            Card temporaryCard = this.cardStack.pop();
            Player temporaryPlayer = this.players[playerNumber];
            if ( temporaryCard.type != CardClass.POINT ) {
                temporaryPlayer.developementCards.add( temporaryCard );
            } //else {
           //     temporaryPlayer.pointsHidden++;
            //}

            return temporaryCard;
        } catch ( Exception e ) {
            return null;
        }
    }
    
    public Card removeDevelopementCardFromPlayer( int playerNumber, Card card ) {
        this.players[playerNumber].developementCards.remove( card );
        return card;
    }
    
    public Card removeDevelopementCardFromPlayer( int playerNumber, int cardIndex ) {
        return this.players[playerNumber].developementCards.remove( cardIndex );
    }
    
    public void addDevelopementCardToPlayerExplicite( Card card, int playerNumber ) {
        this.players[playerNumber].developementCards.add( card );
    }
}
