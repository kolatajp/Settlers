package parser;

import java.util.LinkedList;
import java.util.Random;
import mechanics.Card;
import mechanics.CardClass;
import mechanics.GameMechanics;
import mechanics.JunctionEntity;
import mechanics.JunctionEntityClass;
import mechanics.Player;
import mechanics.Resource;
import solver.Solver;

public class Game {
    GameMechanics mechanics;
    GameParser innerParser;

    private String[] createMessage( String message, String log ) {
        String[] out = new String[2];
        out[0] = message;
        out[1] = log;
        return out;
    }

    public Game( int playerNumber ) {
        this.mechanics = new GameMechanics( playerNumber );
        this.innerParser = new parser.GameParser( this );
    }

    public Game( GameMechanics mechanics ) {
        this.mechanics = mechanics;
        this.innerParser = new parser.GameParser( this );
    }

    //surowce, kostki i zlodziej
    //tylko po stronie serwera
    public String[] roll() {
        int rolledNumber = this.mechanics.rollTwoDices();
        String out = "";
        String logMessage = "";
        if ( rolledNumber != 7 ) {
            //szukam tych, ktore maja rolledNumber i ustawiam odpowiedni id
            int[] ids = new int[2];
            int idsIndex = 0;
            for ( int i = 1; i < this.mechanics.getHexs().length; i++ ) {
                if ( this.mechanics.getHexs()[i].getNumber() == rolledNumber ) {
                    ids[idsIndex] = i;
                    idsIndex++;
                }
            }

            //deb
            /*
             String idsString = "" + ids[0];
             if ( idsIndex == 2 ) {
             idsString += "," + ids[1];
             }
             System.out.println( "wypadlo" + rolledNumber + "(ids=" + idsString );
             */
            int idsQuantity;
            if ( idsIndex == 2 ) {
                idsQuantity = 2;
            } else {
                idsQuantity = 1;
            }

            String p0 = "p0";
            String p1 = "p1";
            String p2 = "p2";
            String p3 = "p3";
            int resourceQuantity;
            String resourceShortName = "";
            JunctionEntity tempJunction;

            //dla dwoch pol lub jednego
            for ( int j = 0; j < idsQuantity; j++ ) {
                //jezeli nie ma tam zlodzieja
                if ( this.mechanics.getHexs()[ids[j]].isEnabled() ) {
                    for ( int i = 0; i < 6; i++ ) {
                        //deb
                        System.out.println( "pobieram z hexa nr" + ids[j] );

                        tempJunction = this.mechanics.getHexs()[ids[j]].getJunctions()[i];
                        if ( tempJunction.getEntityClass() == JunctionEntityClass.TOWN ) {
                            resourceQuantity = 1;
                            //deb
                            System.out.println( "town" );
                        } else if ( tempJunction.getEntityClass() == JunctionEntityClass.CITY ) {
                            resourceQuantity = 2;
                            //deb
                            System.out.println( "city" );
                        } else {
                            resourceQuantity = 0;
                            //deb
                            System.out.println( "nic" );
                        }

                        Resource resource = this.mechanics.getHexs()[ids[j]].getResource();

                        if ( resource == Resource.CLAY ) {
                            resourceShortName = "c";
                        } else if ( resource == Resource.STONE ) {
                            resourceShortName = "s";
                        } else if ( resource == Resource.WHEAT ) {
                            resourceShortName = "h";
                        } else if ( resource == Resource.WOOD ) {
                            resourceShortName = "d";
                        } else if ( resource == Resource.WOOL ) {
                            resourceShortName = "o";
                        }

                        if ( resourceQuantity != 0 ) {
                            if ( tempJunction.getPlayerNumber() == 0 ) {
                                p0 += resourceShortName + resourceQuantity;
                            } else if ( tempJunction.getPlayerNumber() == 1 ) {
                                p1 += resourceShortName + resourceQuantity;
                            } else if ( tempJunction.getPlayerNumber() == 2 ) {
                                p2 += resourceShortName + resourceQuantity;
                            } else if ( tempJunction.getPlayerNumber() == 3 ) {
                                p3 += resourceShortName + resourceQuantity;
                            }
                        }
                    }

                    logMessage = "Wynik rzutu kostka: " + rolledNumber + ",";

                } else {
                    //jednak byl zlodziej
                    /*p0 = "p0";
                     p1 = "p1";
                     p2 = "p2";
                     if ( this.mechanics.getPlayers().length == 4 ) {
                     p3 = "p3";
                     }*/
                    //deb
                    System.out.println( "zlodziej siedzi" );
                    logMessage = "Wynik rzutu kostka: " + rolledNumber + ", jedno pole zablokowane przez zlodzieja";
                }
            }

            out = "rollgive-";
            out += p0 + "," + p1 + "," + p2;
            if ( this.mechanics.getPlayers().length == 4 ) {
                out += "," + p3;
            }

            //powiadomienie o graczach
            String resInfoString = Solver.resourcesString2Info( out.substring( 9 ) );
            if ( resInfoString.length() > 1 ) {
                logMessage += "\n";
                logMessage += resInfoString;
            }

            //faktyczne przydzielanie surowcow
            String[] parted = out.substring( 9 ).split( "," );
            if ( parted[0].length() > 3 ) {
                parted[0] = parted[0].substring( 2 );
                this.mechanics.addResourcesToPlayer( Solver.getResourcesFromString( parted[0] ), 0 );
            }
            if ( parted[1].length() > 3 ) {
                parted[1] = parted[1].substring( 2 );
                this.mechanics.addResourcesToPlayer( Solver.getResourcesFromString( parted[1] ), 1 );
            }
            if ( parted[2].length() > 3 ) {
                parted[2] = parted[0].substring( 2 );
                this.mechanics.addResourcesToPlayer( Solver.getResourcesFromString( parted[2] ), 2 );
            }
            if ( this.mechanics.getPlayers().length == 4 && parted[3].length() > 3 ) {
                parted[3] = parted[3].substring( 2 );
                this.mechanics.addResourcesToPlayer( Solver.getResourcesFromString( parted[3] ), 3 );
            }

            //zwracanie
            return this.createMessage( out, logMessage );
        } else {
            //wypadl zlodziej
            return this.createMessage( "moveThief-", "Wynik rzutu kostka: 7, przesuwa sie zlodziej" );
        }
    }

    public String[] placeThiefOnHex( int hexNumber ) {
        String[] out = new String[2];
        out[0] = "thief-" + Solver.complementInt( hexNumber );
        out[1] = "Przestawiono złodzieja na pole " + hexNumber;
        this.mechanics.putThiefOnHexAndReload( hexNumber );
        return out;
    }

    public String[] giveResourcesToPlayer( int playerNumber, String resString ) {
        this.mechanics.addResourcesToPlayer( Solver.getResourcesFromString( resString ), playerNumber );
        return this.createMessage( "res-add-p" + playerNumber + resString,
                                   "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber )
                                   + " otrzymał " + Solver.resourcesString2Info( resString ) );
    }

    public String[] takeResourcesFromPlayer( int playerNumber, String resString ) {
        this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( resString ), playerNumber );
        return this.createMessage( "res-sub-p" + playerNumber + resString,
                                   "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber )
                                   + " stracił " + Solver.resourcesString2Info( resString ) );
    }

    public String[] stealResourceFromPlayerToPlayer( int stealingPlayerNumber, int victimPlayerNumber ) {
        //jezeli nie kradne od nikogo, czyli 9
        if ( victimPlayerNumber != 9 ) {
            Random randomGen = new Random();
            Player victim = this.mechanics.getPlayers()[victimPlayerNumber];
            Player stealing = this.mechanics.getPlayers()[stealingPlayerNumber];

            //deb usun
            System.out.println( "stealingPlayer:\n" + Solver.player2InfoStringReal( mechanics, stealingPlayerNumber ) );
            System.out.println( "victim:\n" + Solver.player2InfoStringReal( mechanics, victimPlayerNumber ) );
            
            LinkedList<Resource> res = new LinkedList();
            for ( int i = 0; i < victim.getWool(); i++ ) {
                res.add( Resource.WOOL );
            }
            for ( int i = 0; i < victim.getWood(); i++ ) {
                res.add( Resource.WOOD );
            }
            for ( int i = 0; i < victim.getStone(); i++ ) {
                res.add( Resource.STONE );
            }
            for ( int i = 0; i < victim.getWheat(); i++ ) {
                res.add( Resource.WHEAT );
            }
            for ( int i = 0; i < victim.getClay(); i++ ) {
                res.add( Resource.CLAY );
            }

            //deb usun
            System.out.println( "uzbieralem surowcow od gracza " + victimPlayerNumber + " tyle:" + res.size() );
            String resourceString = "";

            if ( res.size() > 0 ) {
                int pickedNumber = randomGen.nextInt( res.size() );
                Resource pickedResource = res.get( pickedNumber );
                if ( pickedResource == Resource.WOOL ) {
                    resourceString = "o1";
                }
                if ( pickedResource == Resource.WOOD ) {
                    resourceString = "d1";
                }
                if ( pickedResource == Resource.STONE ) {
                    resourceString = "s1";
                }
                if ( pickedResource == Resource.WHEAT ) {
                    resourceString = "h1";
                }
                if ( pickedResource == Resource.CLAY ) {
                    resourceString = "c1";
                }
            }

            //deb usun
            System.out.println( "te surowce to:" + resourceString );
            
            String message = "res-sub-p" + victimPlayerNumber + resourceString + "\n"
                             + "res-add-p" + stealingPlayerNumber + resourceString;

            //wykonanie na sobie
            this.parseMultiple( message );

            return this.createMessage( message, "Gracz " + Solver.getPlayerColorStringFromNumber( stealingPlayerNumber )
                                                + " kradnie od gracza " + Solver.getPlayerColorStringFromNumber( victimPlayerNumber )
                                                + " " + Solver.resourcesString2Info( resourceString ) );
        } else {
            return this.createMessage( "none", "" );
        }
    }

    //budowanie
    public String[] buildRoad( int junctionA, int junctionB, int playerNumber ) {
        this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( "c1d1" ), playerNumber );
        this.mechanics.addRoadBetweenJunctionsToPlayer( junctionA, junctionB, playerNumber );

        String mes = "res-sub-p" + playerNumber + "c1d1" + "\n"
                     + "broad-" + playerNumber + "-" + Solver.complementInt( junctionA ) + "-" + Solver.complementInt( junctionB );
        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + " buduje drogę" );
    }

    public String[] buildTown( int junction, int playerNumber ) {
        this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( "o1d1c1h1" ), playerNumber );
        this.mechanics.setTownAtJunctionToPlayer( junction, playerNumber );

        String mes = "res-sub-p" + playerNumber + "o1d1c1h1" + "\n"
                     + "bcity-" + playerNumber + "-" + Solver.complementInt( junction );
        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + " buduje osadę" );
    }

    public String[] buildCity( int junction, int playerNumber ) {
        this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( "s3h2" ), playerNumber );
        this.mechanics.setCityAtJunctionToPlayer( junction, playerNumber );

        String mes = "res-sub-p" + playerNumber + "s3h2" + "\n"
                     + "bcity-" + playerNumber + "-" + Solver.complementInt( junction );
        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + " buduje miasto" );
    }

    //karty rozwojow devCard
    public String[] buyDevCardToPlayer( int playerNumber ) {
        this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( "o1s1h1" ), playerNumber );
        Card card = this.mechanics.giveDevelopementCardToPlayer( playerNumber );

        String cardName = "none";
        if ( card.getType() == CardClass.KNIGHT ) {
            cardName = "knight";
        } else if ( card.getType() == CardClass.MONOPOLY ) {
            cardName = "monopoly";
        } else if ( card.getType() == CardClass.POINT ) {
            cardName = "point";
        } else if ( card.getType() == CardClass.RESOURCES ) {
            cardName = "resources";
        } else if ( card.getType() == CardClass.ROADS ) {
            cardName = "roads";
        }

        String mes = "res-sub-p" + playerNumber + "o1s1h1" + "\n"
                     + "devCard-" + playerNumber + "-" + "give-" + cardName;
        //jezeli to punkt
        if ( cardName.equals( "point" ) ) {
            mes += "\n" + "points-" + playerNumber + "-00-01";
            this.mechanics.getPlayers()[playerNumber].addPointsHidden( 1 );
        }
        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + " kupuje kartę rozwoju za " + Solver.resourcesString2Info( "o1s1h1" ) );
    }

    public String[] resolveKnightFromPlayerOnHexStealFromPlayerCardIndex( int fromPlayer, int onHex, int stealFromPlayer, int cardIndex ) {
        String mes = "";
        this.mechanics.removeDevelopementCardFromPlayer( fromPlayer, cardIndex );
        this.mechanics.getPlayers()[fromPlayer].addKnightRevealed();
        mes += "devCard-" + fromPlayer + "-place-" + Solver.complementInt( cardIndex ) + "\n";
        this.mechanics.putThiefOnHexAndReload( onHex );
        mes += "thief-" + Solver.complementInt( onHex ) + "\n";
        String[] tmpCmdTab = this.stealResourceFromPlayerToPlayer( fromPlayer, stealFromPlayer );
        mes += tmpCmdTab[0];
        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( fromPlayer ) + " wystawia rycerza, przestawia złodzieja i "
                                        + tmpCmdTab[1] );
    }

    public String[] resolveMonopolyFromPlayerWithResourceCardIndex( int playerNumber, Resource res, int cardIndex ) {
        int resourceQuantity = 0;
        String mes = "";

        this.mechanics.removeDevelopementCardFromPlayer( playerNumber, cardIndex );
        mes += "devCard-" + playerNumber + "-place-" + Solver.complementInt( cardIndex ) + "\n";

        for ( int i = 0; i < this.mechanics.getPlayers().length; i++ ) {
            if ( i != playerNumber ) {
                if ( res == Resource.WOOL ) {
                    resourceQuantity += this.mechanics.getPlayers()[i].getWool();
                    int tempQuantity = this.mechanics.getPlayers()[i].getWool();
                    String resString = "o" + tempQuantity;
                    this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( resString ), i );
                    mes += "res-sub-p" + i + resString + "\n";
                } else if ( res == Resource.WOOD ) {
                    resourceQuantity += this.mechanics.getPlayers()[i].getWood();
                    int tempQuantity = this.mechanics.getPlayers()[i].getWood();
                    String resString = "d" + tempQuantity;
                    this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( resString ), i );
                    mes += "res-sub-p" + i + resString + "\n";
                } else if ( res == Resource.STONE ) {
                    resourceQuantity += this.mechanics.getPlayers()[i].getStone();
                    int tempQuantity = this.mechanics.getPlayers()[i].getStone();
                    String resString = "s" + tempQuantity;
                    this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( resString ), i );
                    mes += "res-sub-p" + i + resString + "\n";
                } else if ( res == Resource.WHEAT ) {
                    resourceQuantity += this.mechanics.getPlayers()[i].getWheat();
                    int tempQuantity = this.mechanics.getPlayers()[i].getWheat();
                    String resString = "h" + tempQuantity;
                    this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( resString ), i );
                    mes += "res-sub-p" + i + resString + "\n";
                } else if ( res == Resource.CLAY ) {
                    resourceQuantity += this.mechanics.getPlayers()[i].getClay();
                    int tempQuantity = this.mechanics.getPlayers()[i].getClay();
                    String resString = "c" + tempQuantity;
                    this.mechanics.removeResourcesFromPlayer( Solver.getResourcesFromString( resString ), i );
                    mes += "res-sub-p" + i + resString + "\n";
                }
            }
        }

        this.mechanics.addResourcesToPlayer( Solver.getResourcesFromString( Solver.getResourceStringFromResourceClass( res ) + resourceQuantity ),
                                             playerNumber );
        String resString = Solver.getResourceStringFromResourceClass( res ) + resourceQuantity;
        mes += "res-add-p" + playerNumber + resString;

        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + " używa karty monopol i zyskuje: "
                                        + Solver.resourcesString2Info( resString ) );
    }

    public String[] resolveResourcesDevCardFromPlayerFromStringCardIndex( int playerNumber, String resString, int cardIndex ) {
        String mes = "";

        this.mechanics.removeDevelopementCardFromPlayer( playerNumber, cardIndex );
        mes += "devCard-" + playerNumber + "-place-" + Solver.complementInt( cardIndex ) + "\n";

        this.mechanics.addResourcesToPlayer( Solver.getResourcesFromString( resString ), playerNumber );
        mes += "res-add-p" + playerNumber + resString;

        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + "używa karty zasoby i zdobywa: "
                                        + Solver.resourcesString2Info( resString ) );
    }

    public String[] resolveRoadsDevCardFromPlayerAtJunctionABCDCardIndex( int playerNumber, int junctionA, int junctionB, int junctionC, int junctionD, int cardIndex ) {
        String mes = "";

        this.mechanics.removeDevelopementCardFromPlayer( playerNumber, cardIndex );
        mes += "devCard-" + playerNumber + "-place-" + Solver.complementInt( cardIndex ) + "\n";

        mes += this.buildRoadNoCostAtJunctionABToPlayer( junctionA, junctionB, playerNumber )[0] + "\n";
        mes += this.buildRoadNoCostAtJunctionABToPlayer( junctionC, junctionD, playerNumber )[0];

        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + " używa karty wynalazku i buduje dwie drogi za darmo" );
    }

    //budowanie bez surowcow
    public String[] buildRoadNoCostAtJunctionABToPlayer( int junctionA, int junctionB, int playerNumber ) {
        String mes = "";

        this.mechanics.addRoadBetweenJunctionsToPlayer( junctionA, junctionB, playerNumber );
        mes += "broad-" + playerNumber + "-" + Solver.complementInt( junctionA ) + "-" + Solver.complementInt( junctionB );

        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + " stawia drogę za darmo" );
    }

    public String[] buildTownNoCostAtJunctionToPlayer( int junction, int playerNumber ) {
        String mes = "";

        this.mechanics.setTownAtJunctionToPlayer( junction, playerNumber );
        mes += "btown-" + playerNumber + "-" + Solver.complementInt( junction );

        return this.createMessage( mes, "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + " stawia osadę za darmo" );
    }

    //liczenie punktow
    public String[] calculatePoints() {
        String mes = "";

        for ( int i = 0; i < this.mechanics.getPlayers().length; i++ ) {
            Player p = this.mechanics.getPlayers()[i];
            int pointsPublic = 0;

            pointsPublic += p.getTowns();
            pointsPublic += p.getCities() * 2;
            if ( p.isHasLongestRoad() ) {
                pointsPublic += 2;
            }
            if ( p.isHasTheMostKnights() ) {
                pointsPublic += 2;
            }

            int pointsHidden = p.getPointsHidden();

            p.setPointsPublic( pointsPublic );
            p.setPointsHidden( pointsHidden );
            mes += "points-" + i + "-" + Solver.complementInt( pointsPublic ) + "-" + Solver.complementInt( pointsHidden ) + "\n";
        }

        //usuwam ostatni \n
        mes = mes.substring( 0, mes.length() - 1 );

        return this.createMessage( mes, "Przeliczyłem punkty" );
    }

    //kompleksowe ( complex ) przestawienie zlodzieja - blokada i kradziez
    public String[] resolveThiefMovementOnHexFromPlayerToVictim( int onHex, int stealingNumber, int victimNumber ) {
        String mes = "";
        String chat = "";
        String[] temp;
        temp = this.placeThiefOnHex( onHex );
        mes += temp[0] + "\n";
        chat += temp[1] + "\n";
        //jezeli ==9 to nie kradnie nikomu
        if ( victimNumber != 9 ) {
            //deb usun
            System.out.println( "Gracz " + stealingNumber + " kradnie od " + victimNumber );
            temp = this.stealResourceFromPlayerToPlayer( victimNumber, stealingNumber );
            mes += temp[0];
            chat += temp[1];
        } else {
            //deb usun
            System.out.println( "Nie ma kradziezy, gracz " + stealingNumber + " przestawil zlodzieja" );
            //jezeli nikomu to usuwam \n
            mes = mes.replaceAll( "\n", "" );
            chat = chat.replaceAll( "\n", "" );
        }

        return this.createMessage( mes, chat );
    }

    //handel
    public String[] tradeWithBankPort( int playerNumber, String resGiven, String resObtained ) {
        String mes = "";
        String chat = "Gracz " + Solver.getPlayerColorStringFromNumber( playerNumber ) + " wymienia z bankiem/portem "
                      + Solver.resourcesString2Info( resGiven ) + " za " + Solver.resourcesString2Info( resObtained ) + "\n";
        String[] temp;

        temp = this.takeResourcesFromPlayer( playerNumber, resGiven );
        mes += temp[0] + "\n";
        chat += temp[1] + "\n";

        temp = this.giveResourcesToPlayer( playerNumber, resObtained );
        mes += temp[0];
        chat += temp[1];

        return this.createMessage( mes, chat );
    }

    //TODO
    //handel z graczem
    public String[] tradeWithPlayerLowLevelFunction( String tradeString ) {
        int playerA = Integer.parseInt( "" + tradeString.charAt( 1 ) );
        int playerB = Integer.parseInt( "" + tradeString.charAt( tradeString.indexOf( "," ) + 2 ) );
        String aResString = tradeString.substring( 2, tradeString.indexOf( "," ) );
        String bResString = tradeString.substring( tradeString.indexOf( "," ) + 3 );

        String mes = "";
        String chat = "";
        String[] temp;

        temp = this.takeResourcesFromPlayer( playerA, aResString );
        mes += temp[0] + "\n";
        temp = this.takeResourcesFromPlayer( playerB, bResString );
        mes += temp[0] + "\n";
        temp = this.giveResourcesToPlayer( playerA, bResString );
        mes += temp[0] + "\n";
        temp = this.giveResourcesToPlayer( playerB, aResString );
        mes += temp[0];

        return this.createMessage( mes, "Gracze " + Solver.getPlayerColorStringFromNumber( playerA ) + " i " + Solver.getPlayerColorStringFromNumber( playerB )
                                        + " wymieniają " + Solver.resourcesString2Info( aResString ) + " za " + Solver.resourcesString2Info( bResString ) );
    }

    // wykonanie wielu instrukcji wewnatrz siebie
    private void parseMultiple( String mes ) {
        String[] commands = mes.split( "\n" );
        for ( int i = 0; i < commands.length; i++ ) {
            this.innerParser.parse( commands[i] );
        }
    }
}
