package solver;

import java.util.LinkedList;
import mechanics.Card;
import mechanics.CardClass;

public class CardWalker {
    public static String getCardsInfo( LinkedList<Card> cardList ) {
        String out = "";
        Card c;
        for ( int i=0; i<cardList.size(); i++ ) {
            c = cardList.get( i );
            if ( c.getType() == CardClass.KNIGHT ) {
                out += "k";
            } else if ( c.getType() == CardClass.MONOPOLY ) {
                out += "m";
            } else if ( c.getType() == CardClass.POINT ) {
                out += "p";
            } else if ( c.getType() == CardClass.RESOURCES ) {
                out += "r";
            } else if ( c.getType() == CardClass.ROADS ) {
                out += "d";
            }
        }
        
        return out;
    }
}
