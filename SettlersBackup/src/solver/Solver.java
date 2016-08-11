package solver;

import com.sun.xml.internal.ws.util.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import mechanics.Card;
import mechanics.CardClass;
import mechanics.CardState;
import mechanics.GameMechanics;
import mechanics.JunctionEntity;
import mechanics.JunctionEntityClass;
import mechanics.Player;
import mechanics.Resource;

public class Solver {

    public static String complementInt( int number, int decimalsNumber ) {
        String decimal = "" + number;
        int kek = (decimalsNumber - decimal.length());
        for ( int i = 0; i < kek; i++ ) {
            decimal = "0" + decimal;
        }
        return decimal;
    }

    public static String complementInt( int number ) {
        return complementInt( number, 2 );
    }

    public static int getNumberFromId( String id ) {
        return Integer.parseInt( id.substring( id.lastIndexOf( "_" ) + 1 ) );
    }

    public static int getPlayerNumberFromString( String s ) {
        return Integer.parseInt( s.substring( s.indexOf( "p" ) + 1, s.indexOf( "p" ) + 2 ) );
    }

    public static int[] getResourcesFromString( String s ) {
        int[] res = new int[5];
        for ( int i = 0; i < 5; i++ ) {
            res[i] = 0;
        }
        int index = -1;
        int quantity = 0;

        for ( int i = 0; i < s.length(); i++ ) {
            if ( s.charAt( i ) == 'o' ) {
                if ( index != -1 ) {
                    res[index] += quantity;
                }
                index = 0;
                quantity = 0;
            } else if ( s.charAt( i ) == 'd' ) {
                if ( index != -1 ) {
                    res[index] += quantity;
                }
                index = 1;
                quantity = 0;
            } else if ( s.charAt( i ) == 's' ) {
                if ( index != -1 ) {
                    res[index] += quantity;
                }
                index = 2;
                quantity = 0;
            } else if ( s.charAt( i ) == 'h' ) {
                if ( index != -1 ) {
                    res[index] += quantity;
                }
                index = 3;
                quantity = 0;
            } else if ( s.charAt( i ) == 'c' ) {
                if ( index != -1 ) {
                    res[index] += quantity;
                }
                index = 4;
                quantity = 0;
            } else {

                try {
                    int tempNum = Integer.parseInt( "" + s.charAt( i ) );
                    quantity = quantity * 10;
                    quantity += tempNum;
                } catch ( NumberFormatException e ) {

                }
            }
        }

        if ( index != -1 ) {
            res[index] += quantity;
        }

        return res;
    }

    public static String resources2String( int[] res ) {
        String out = "";
        if ( res[0] > 0 ) {
            out += res[0] + "o";
        }
        if ( res[1] > 0 ) {
            out += res[1] + "d";
        }
        if ( res[2] > 0 ) {
            out += res[2] + "s";
        }
        if ( res[3] > 0 ) {
            out += res[3] + "h";
        }
        if ( res[4] > 0 ) {
            out += res[4] + "c";
        }
        return out;
    }

    private static String resourcesPlain2Info( String res ) {
        int[] resTab;
        if ( res.length() > 0 ) {
            resTab = Solver.getResourcesFromString( res );
            String out = "";
            if ( resTab[0] > 0 ) {
                out += "Owce:" + resTab[0] + ", ";
            }
            if ( resTab[1] > 0 ) {
                out += "Drewno:" + resTab[1] + ", ";
            }
            if ( resTab[2] > 0 ) {
                out += "Kamien:" + resTab[2] + ", ";
            }
            if ( resTab[3] > 0 ) {
                out += "Zboze:" + resTab[3] + ", ";
            }
            if ( resTab[4] > 0 ) {
                out += "Glina:" + resTab[4];
            }
            return out;
        } else {
            return "";
        }

    }

    public static String getPlayerColorStringFromNumber( int n ) {
        if ( n == 0 ) {
            return "red";
        } else if ( n == 1 ) {
            return "yellow";
        } else if ( n == 2 ) {
            return "blue";
        } else if ( n == 3 ) {
            return "purple";
        }

        return null;
    }

    public static String resourcesString2Info( String res ) {
        String out = "";

        if ( res.contains( "p" ) ) {
            //to znaczy ze powinien miec graczy
            String[] players = res.split( "," );
            String tempResources = "";
            for ( int i = 0; i < players.length; i++ ) {
                //deb
                System.out.println( "players[:" + i + "]" + players[i] );
                tempResources = Solver.resourcesPlain2Info( players[i].substring( 2 ) );
                if ( tempResources.length() > 0 ) {
                    out += "Gracz " + Solver.getPlayerColorStringFromNumber( Integer.parseInt( players[i].substring( 1, 2 ) ) );
                    out += ": " + tempResources;
                }
            }

            //deb
            //System.out.println( out.charAt( out.length() - 1 ) );
        } else {
            out = Solver.resourcesPlain2Info( res );
        }

        if ( out.length() > 0 && out.charAt( out.length() - 1 ) == '\n' ) {
            out = out.substring( 0, out.length() - 1 );
        }

        return out;
    }

    public static String getResourceStringFromResourceClass( Resource res ) {
        if ( res == Resource.WOOL ) {
            return "o";
        } else if ( res == Resource.WOOD ) {
            return "d";
        } else if ( res == Resource.STONE ) {
            return "s";
        } else if ( res == Resource.WHEAT ) {
            return "h";
        } else if ( res == Resource.CLAY ) {
            return "c";
        }

        return null;
    }

    public static CardClass cardName2cardClass( String cardName ) {
        if ( cardName.contains( "knight" ) ) {
            return CardClass.KNIGHT;
        } else if ( cardName.contains( "monopoly" ) ) {
            return CardClass.MONOPOLY;
        } else if ( cardName.contains( "road" ) ) {
            return CardClass.ROADS;
        } else if ( cardName.contains( "res" ) ) {
            return CardClass.RESOURCES;
        } else if ( cardName.contains( "point" ) ) {
            return CardClass.POINT;
        }

        return null;
    }

    public static Resource getResourceFromString( String resName ) {
        if ( resName.equals( "o" ) ) {
            return Resource.WOOL;
        } else if ( resName.equals( "d" ) ) {
            return Resource.WOOD;
        } else if ( resName.equals( "s" ) ) {
            return Resource.STONE;
        } else if ( resName.equals( "h" ) ) {
            return Resource.WHEAT;
        } else if ( resName.equals( "c" ) ) {
            return Resource.CLAY;
        }

        return null;
    }

    public static String player2InfoStringReal( GameMechanics mechanics, int playerIndex ) {
        String out = "Gracz " + Solver.getPlayerColorStringFromNumber( playerIndex ) + "\n";
        Player p = mechanics.getPlayers()[playerIndex];
        out += p.getPointsPublic() + "pkt +" + p.getPointsHidden() + " kart pktz." + "\n";
        out += "Owce:  " + p.getWool() + "\t" + "Drewno:" + p.getWood() + "\n";
        out += "Kamień:" + p.getStone() + "\t" + "Zboże: " + p.getWheat() + "\n";
        out += "Glina: " + p.getClay() + "\t" + "Razem  " + new Integer( p.getWheat() + p.getWood() + p.getWool()
                                                                         + p.getStone() + p.getClay() ) + "\n";
        out += p.getDevelopementCards().size() + " kart rozwoju";
        return out;
    }

    public static String devCard2InfoReal( Card c ) {
        String out = "";

        if ( c.getType() == CardClass.KNIGHT ) {
            out += "Rycerz";
        } else if ( c.getType() == CardClass.MONOPOLY ) {
            out += "Monopol";
        } else if ( c.getType() == CardClass.NONE ) {
            out += "Nic";
        } else if ( c.getType() == CardClass.POINT ) {
            out += "Punkt zwycięstwa";
        } else if ( c.getType() == CardClass.RESOURCES ) {
            out += "Surowce";
        } else if ( c.getType() == CardClass.ROADS ) {
            out += "Wynalazek (drogi)";
        }

        if ( c.getState() == CardState.DISABLED ) {
            out += " (nieaktywna)";
        } else {
            out += "";
        }

        return out;
    }

    public static Object[] getListFromDevCards( LinkedList<Card> cardStack ) {
        Object[] out = new Object[cardStack.size()];
        for ( int i = 0; i < cardStack.size(); i++ ) {
            out[i] = Solver.devCard2InfoReal( cardStack.get( i ) );
        }
        return out;
    }

    public static String[] getListFromPlayers( GameMechanics mechanics ) {
        String[] out = new String[mechanics.getPlayers().length];
        out[0] = "Czerwony/Red";
        out[1] = "Żółty/Yellow";
        out[2] = "Niebieski/Blue";
        if ( out.length == 4 ) {
            out[3] = "Fioletowy/Purple";
        }
        return out;
    }

    public static boolean areJunctionsNeighbours( int junctionA, int junctionB ) {
        GameMechanics mech = new GameMechanics( 3 );
        int[] neigh = mech.getJunctions()[junctionA].getNeighbourJunctionsNumbers();
        for ( int i = 0; i < neigh.length; i++ ) {
            //deb usun
            System.out.println( "dla j" + junctionA + " sasiadem jest " + neigh[i] );
            if ( neigh[i] == junctionB ) {
                //deb usun
                System.out.println( "jB=" + junctionB + " jest sasiadem " + junctionA );
                return true;
            }//deb usun
            else {
                System.out.println( "Nie jest to jB=" + junctionB );
            }
        }
        return false;
    }

    public static boolean areThereNoNeighboursAndNoTownAtJunction( int junction, GameMechanics mechanics ) {
        //jezeli cos tam stoi
        if ( mechanics.getJunctions()[junction].getEntityClass() != JunctionEntityClass.NONE ) {
            return false;
        }

        //jezeli ma sasiadow
        int[] neigh = mechanics.getJunctions()[junction].getNeighbourJunctionsNumbers();
        //deb usun
        System.out.println( "Sasiedzi dla " + junction );
        for ( int i = 0; i < neigh.length; i++ ) {
            //deb usun
            System.out.println( "" + neigh[i] + " " );
            if ( neigh[i] != 0 && mechanics.getJunctions()[neigh[i]].getEntityClass() != JunctionEntityClass.NONE ) {
                //deb usun
                System.out.println( "na " + neigh[i] + " jest osada/miasto" );
                return false;
            }
        }

        //deb usun
        System.out.println( "Nie ma sasiadow ani nic na miejscu " + junction );
        return true;
    }

    public static boolean isRoadBetweenJunctionsFree( int jA, int jB, GameMechanics mechanics ) {
        Player[] players = mechanics.getPlayers();

        for ( Player p : players ) {
            for ( int i = 0; i < p.getRoads().size(); i++ ) {
                int[] presentJunctions = p.getRoads().get( i );
                if ( (presentJunctions[0] == jA && presentJunctions[1] == jB)
                     || (presentJunctions[0] == jB && presentJunctions[1] == jA) ) {
                    return false;
                }
            }
        }

        return true;
    }

    public static String get3resourcesFromHexAroundJunction( int junction, GameMechanics mechanics ) {
        String[] junctionsHexes = new String[55];
        junctionsHexes[1] = "1";
        junctionsHexes[2] = "1";
        junctionsHexes[3] = "2";
        junctionsHexes[4] = "1,2";
        junctionsHexes[5] = "1,3";
        junctionsHexes[6] = "3";
        junctionsHexes[7] = "4";
        junctionsHexes[8] = "2,4";
        junctionsHexes[9] = "1,2,5";
        junctionsHexes[10] = "1,3,5";
        junctionsHexes[11] = "3,6";
        junctionsHexes[12] = "6";
        junctionsHexes[13] = "4";
        junctionsHexes[14] = "2,4,7";
        junctionsHexes[15] = "2,5,7";
        junctionsHexes[16] = "3,5,8";
        junctionsHexes[17] = "3,6,8";
        junctionsHexes[18] = "6";
        junctionsHexes[19] = "4,9";
        junctionsHexes[20] = "4,7,9";
        junctionsHexes[21] = "5,7,10";
        junctionsHexes[22] = "5,8,10";
        junctionsHexes[23] = "6,8,11";
        junctionsHexes[24] = "6,11";
        junctionsHexes[25] = "9";
        junctionsHexes[26] = "7,9,12";
        junctionsHexes[27] = "7,10,12";
        junctionsHexes[28] = "8,10,13";
        junctionsHexes[29] = "8,11,13";
        junctionsHexes[30] = "11";
        junctionsHexes[31] = "9,14";
        junctionsHexes[32] = "9,12,14";
        junctionsHexes[33] = "10,12,15";
        junctionsHexes[34] = "10,13,15";
        junctionsHexes[35] = "11,13,16";
        junctionsHexes[36] = "11,16";
        junctionsHexes[37] = "14";
        junctionsHexes[38] = "12,14,17";
        junctionsHexes[39] = "12,15,17";
        junctionsHexes[40] = "13,15,18";
        junctionsHexes[41] = "13,16,18";
        junctionsHexes[42] = "16";
        junctionsHexes[43] = "14";
        junctionsHexes[44] = "14,17";
        junctionsHexes[45] = "15,17,19";
        junctionsHexes[46] = "15,18,19";
        junctionsHexes[47] = "16,18";
        junctionsHexes[48] = "16";
        junctionsHexes[49] = "17";
        junctionsHexes[50] = "17,19";
        junctionsHexes[51] = "18,19";
        junctionsHexes[52] = "18";
        junctionsHexes[53] = "19";
        junctionsHexes[54] = "19";

        String out = "";

        String[] tempHexes = junctionsHexes[junction].split( "," );
        for ( int i = 0; i < tempHexes.length; i++ ) {
            int tempHex = Integer.parseInt( tempHexes[i] );
            if ( mechanics.getHexs()[tempHex].getResource() != Resource.NONE
                 && mechanics.getHexs()[tempHex].getResource() != Resource.DESERT ) {
                out += Solver.getResourceStringFromResourceClass( mechanics.getHexs()[tempHex].getResource() ) + "1";
            }
        }

        return out;
    }

    public static int[] getPlayersFromHex( int hexNum, GameMechanics mechanics ) {
        JunctionEntity[] junctions = mechanics.getHexs()[hexNum].getJunctions();
        String junc = "";

        for ( JunctionEntity j : junctions ) {
            int pn = j.getPlayerNumber();
            if ( pn != -1 && !junc.contains( "" + pn ) ) {
                junc += pn + ",";
            }
        }

        if ( junc.length() > 0 ) {
            junc = junc.substring( 0, junc.length() - 1 );
            String[] js = junc.split( "," );
            int[] out = new int[js.length];
            for ( int i = 0; i < js.length; i++ ) {
                out[i] = Integer.parseInt( js[i] );
            }

            return out;
        } else {
            return null;
        }
    }

    public static int getPlayerNumberFromStringColor( String pColor ) {
        if ( pColor.equals( "red" ) ) {
            return 0;
        } else if ( pColor.equals( "yellow" ) ) {
            return 1;
        } else if ( pColor.equals( "blue" ) ) {
            return 2;
        } else if ( pColor.equals( "purple" ) ) {
            return 3;
        }

        return -1;
    }

    public static boolean doesPlayerHaveResources( String res, int playerNumber, GameMechanics mechanics ) {
        Player player = mechanics.getPlayers()[playerNumber];
        int[] resRequired = Solver.getResourcesFromString( res );
        int[] playerRes = Solver.getResourcesFromPlayer( playerNumber, mechanics );
        for ( int i = 0; i < resRequired.length; i++ ) {
            if ( playerRes[i] < resRequired[i] ) {
                return false;
            }
        }
        return true;
    }

    public static int[] getResourcesFromPlayer( int playerNumber, GameMechanics mechanics ) {
        int[] res = new int[5];
        Player player = mechanics.getPlayers()[playerNumber];
        res[0] = player.getWool();
        res[1] = player.getWood();
        res[2] = player.getStone();
        res[3] = player.getWheat();
        res[4] = player.getClay();
        return res;
    }

    public static boolean isRoadConnected( int jA, int jB, int playerNumber, GameMechanics mechanics ) {
        if ( jB == -1 ) {
            jB = jA;
        }
        if ( jA == -1 ) {
            jA = jB;
        }
        Player player = mechanics.getPlayers()[playerNumber];
        if ( mechanics.getJunctions()[jA].getPlayerNumber() == playerNumber
             || mechanics.getJunctions()[jB].getPlayerNumber() == playerNumber ) {
            //stoi tam osada
            return true;
        } else {
            //sprawdz drogi
            ArrayList<int[]> roads = player.getRoads();
            for ( int i = 0; i < roads.size(); i++ ) {
                int[] tempRoad = roads.get( i );
                if ( tempRoad[0] == jA || tempRoad[1] == jA || tempRoad[0] == jB || tempRoad[1] == jB ) {
                    return true;
                }
            }
        }

        //ani osady/miasta na skrzyzowaniu, ani drogi prowadzacej
        //nie mozliwe wybudowanie
        return false;
    }

    public static boolean isTownConnected( int junction, int playerNumber, GameMechanics mechanics ) {
        Player player = mechanics.getPlayers()[playerNumber];
        ArrayList<int[]> roads = player.getRoads();
        for ( int[] r : roads ) {
            //jezeli jeden wezel z nich to junction
            if ( junction == r[0] || junction == r[1] ) {
                return true;
            }
        }

        return false;
    }

    public static boolean canCityBeBuiltOnJunction( int junction, int playerNumber, GameMechanics mechanics ) {
        JunctionEntity tempJ = mechanics.getJunctions()[junction];
        if ( tempJ.getEntityClass() == JunctionEntityClass.TOWN
             && tempJ.getPlayerNumber() == playerNumber ) {
            return true;
        } else {
            return false;
        }
    }

    public static DefaultListModel getListModelFromDevCards( int playerNumber, GameMechanics mechanics ) {
        DefaultListModel dlm = new DefaultListModel();
        LinkedList<Card> list = mechanics.getPlayers()[playerNumber].getDevelopementCards();
        for ( Card c : list ) {
            dlm.addElement( Solver.devCard2InfoReal( c ) );
        }
        return dlm;
    }

    public static Card getDevCardFromPlayerAtIndex( int playerNumber, int cardIndex, GameMechanics mechanics ) {
        return mechanics.getPlayers()[playerNumber].getDevelopementCards().get( cardIndex );
    }

    //do devcard surowce / resources
    
    public static DefaultListModel setListModelFromString( String s ) {
        String[] rows = s.split( "\n" );
        DefaultListModel dlm = new DefaultListModel();
        for ( String row : rows ) {
            dlm.addElement( row );
        }
        return dlm;
    }
    
    public static String getResourceNameSingleFromIndex( int i ) {
        if ( i == 0 ) {
            return "o";
        } else if ( i == 1 ) {
            return "d";
        } else if ( i == 2 ) {
            return "s";
        } else if ( i == 3 ) {
            return "h";
        } else if ( i == 4 ) {
            return "c";
        }
        
        return null;
    }

    public static String getResourceStringFromListModel( ListModel lm ) {
        String out = "";
        String tmp;
        int[] res = new int[5];
        for ( int i = 0; i < 5; i++ ) {
            res[i] = 0;
        }
        for ( int i = 0; i < 5; i++ ) {
            tmp = (String) lm.getElementAt( i );
            int howManyX = tmp.length() - tmp.replace( "X", "" ).length();
            res[i] += howManyX;
        }
        for ( int i=0; i<5; i++ ) {
            for ( int j=0; j<res[i]; j++ ) {
                out += Solver.getResourceNameSingleFromIndex( i );
            }
        }
        
        return out;
    }
    
    public static String getStringListFromListModel( ListModel lm ) {
        String out = "";
        for ( int i=0; i<lm.getSize(); i++ ) {
            out += lm.getElementAt( i ) + "\n";
        }
        return out;
    }
    
    public static DefaultListModel resourceDevAddXToIndexInList( int index, ListModel lm ) {
        String[] rows = Solver.getStringListFromListModel( lm ).split( "\n" );
        rows[index] += " X";
        String tmp = "";
        for ( int i=0; i<5; i++ ) {
            tmp += rows[i] + "\n";
        }
        return Solver.setListModelFromString( tmp );
    }
    
    public static DefaultListModel resourceDevSubXToIndexInList( int index, ListModel lm ) {
        String[] rows = Solver.getStringListFromListModel( lm ).split( "\n" );
        if ( rows[index].contains( "X" ) ) {
            rows[index] = rows[index].substring( 0, rows[index].length()-2 );
            
            String tmp = "";
            for ( int i=0; i<5; i++ ) {
                tmp += rows[i] + "\n";
            }
            
            return Solver.setListModelFromString( tmp );
        } else {
            return null;
        }
    }
    
    public static int countXOnIndexInList( int index, ListModel lm ) {
        String[] rows = Solver.getStringListFromListModel( lm ).split( "\n" );
        return Solver.countOccurancesInStringWhat( rows[index], "X" );
    }
    
    public static int countOccurancesInStringWhat( String inString, String what ) {
        return inString.length() - inString.replace( what, "" ).length();
    }
    
    public static DefaultListModel getListModelFromStringWithNewLines( String s ) {
        String[] rows = s.split( "\n" );
        DefaultListModel dlm = new DefaultListModel();
        for ( String row : rows ) {
            dlm.addElement( row );
        }
        return dlm;
    }
}
