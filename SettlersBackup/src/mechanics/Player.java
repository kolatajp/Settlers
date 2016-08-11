package mechanics;

import java.util.ArrayList;
import java.util.LinkedList;
import solver.CardWalker;

public class Player implements java.io.Serializable {
    int id;
    //realne punkty
    int pointsHidden = 0;
    //punkty pokazywane graczom = pkt - karty postepu pkt
    int pointsPublic = 0;
    String name;

    PlayerState playerState;

    //liczba wystawionych miast i osad
    int towns = 0;
    int cities = 0;

    //liczba pozostalych miast i osad
    int townsLeft = 5;
    int citiesLeft = 4;

    //liczba surowcow
    int wood = 0, wool = 0, wheat = 0, clay = 0, stone = 0;

    //dostep do portow
    boolean hasAccessTo3to1Harbour = false;
    boolean hasAccessToWoodHarbour = false;
    boolean hasAccessToWoolHarbour = false;
    boolean hasAccessToWheatHarbour = false;
    boolean hasAccessToClayHarbour = false;
    boolean hasAccessToStoneHarbour = false;

    //liczba rozwojow
    int knightsRevealed = 0;
    LinkedList<Card> developementCards;

    //lista posiadanych drog
    ArrayList<int[]> roads;

    //bonusy punktowe
    boolean hasLongestRoad = false;
    boolean hasTheMostKnights = false;

    //metody
    //do dopracowania w toku
    public Player() {
        this.name = "gracz";
        this.roads = new ArrayList<int[]>();
        this.playerState = PlayerState.INACTIVE;
        this.developementCards = new LinkedList<Card>();
    }

    public Player( String name ) {
        this.name = name;
        this.roads = new ArrayList<int[]>();
        this.playerState = PlayerState.INACTIVE;
        this.developementCards = new LinkedList<Card>();
    }

    //deb
    public String getInfo() {
        String out = "";
        out += this.name + " pkt" + this.pointsPublic + "(h" + this.pointsHidden + ")\n";
        out += "o" + this.wool + "(" + this.shortBoolean( this.hasAccessToWoolHarbour ) + "),";
        out += "d" + this.wood + "(" + this.shortBoolean( this.hasAccessToWoodHarbour ) + "),";
        out += "s" + this.stone + "(" + this.shortBoolean( this.hasAccessToStoneHarbour ) + "),";
        out += "h" + this.wheat + "(" + this.shortBoolean( this.hasAccessToWheatHarbour ) + "),";
        out += "c" + this.clay + "(" + this.shortBoolean( this.hasAccessToClayHarbour ) + "),";
        out += "3:1(" + this.shortBoolean( this.hasAccessTo3to1Harbour ) + ")\n";
        out += "t" + this.towns + "/" + this.townsLeft + "c" + this.cities + "/" + this.citiesLeft + "\n";
        out += "devCards:" + CardWalker.getCardsInfo( this.developementCards ) + "\n";
        out += "longest:" + this.shortBoolean( this.hasLongestRoad ) + ", kni:" + this.shortBoolean( this.hasTheMostKnights );
        return out;
    }

    private String shortBoolean( boolean f ) {
        if ( f ) {
            return "t";
        } else {
            return "n";
        }
    }

    public int getTownsLeft() {
        return townsLeft;
    }

    public int getTowns() {
        return towns;
    }

    public int getShownPoints() {
        return pointsPublic;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public int getId() {
        return id;
    }

    public int getKnightsRevealed() {
        return knightsRevealed;
    }

    public int getCitiesLeft() {
        return citiesLeft;
    }

    public int getCities() {
        return cities;
    }

    public void setTownsLeft( int townsLeft ) {
        this.townsLeft = townsLeft;
    }

    public void setTowns( int towns ) {
        this.towns = towns;
    }

    public void setShownPoints( int shownPoints ) {
        this.pointsPublic = shownPoints;
    }

    public int getPointsPublic() {
        return pointsPublic;
    }

    public int getPointsHidden() {
        return pointsHidden;
    }

    public void setPointsPublic( int pointsPublic ) {
        this.pointsPublic = pointsPublic;
    }

    public void setPointsHidden( int pointsHidden ) {
        this.pointsHidden = pointsHidden;
    }

    public void setPlayerState( PlayerState playerState ) {
        this.playerState = playerState;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setKnightsRevealed( int knightsRevealed ) {
        this.knightsRevealed = knightsRevealed;
    }

    public void setHasTheMostKnights( boolean hasTheMostKnights ) {
        this.hasTheMostKnights = hasTheMostKnights;
    }

    public void setHasLongestRoad( boolean hasLongestRoad ) {
        this.hasLongestRoad = hasLongestRoad;
    }

    public void setHasAccessToWoolHarbour( boolean hasAccessToWoolHarbour ) {
        this.hasAccessToWoolHarbour = hasAccessToWoolHarbour;
    }

    public void setHasAccessToWoodHarbour( boolean hasAccessToWoodHarbour ) {
        this.hasAccessToWoodHarbour = hasAccessToWoodHarbour;
    }

    public void setHasAccessToWheatHarbour( boolean hasAccessToWheatHarbour ) {
        this.hasAccessToWheatHarbour = hasAccessToWheatHarbour;
    }

    public void setHasAccessToStoneHarbour( boolean hasAccessToStoneHarbour ) {
        this.hasAccessToStoneHarbour = hasAccessToStoneHarbour;
    }

    public void setHasAccessToClayHarbour( boolean hasAccessToClayHarbour ) {
        this.hasAccessToClayHarbour = hasAccessToClayHarbour;
    }

    public void setHasAccessTo3to1Harbour( boolean hasAccessTo3to1Harbour ) {
        this.hasAccessTo3to1Harbour = hasAccessTo3to1Harbour;
    }

    public void setCitiesLeft( int citiesLeft ) {
        this.citiesLeft = citiesLeft;
    }

    public void setCities( int cities ) {
        this.cities = cities;
    }

    public ArrayList<int[]> getRoads() {
        return roads;
    }

    public void addRoad( int a, int b ) {
        int[] road = new int[2];
        road[0] = a;
        road[1] = b;
        this.roads.add( road );
    }

    public int getClay() {
        return clay;
    }

    public int getStone() {
        return stone;
    }

    public int getWool() {
        return wool;
    }

    public int getWood() {
        return wood;
    }

    public int getWheat() {
        return wheat;
    }

    public void setStone( int stone ) {
        this.stone = stone;
    }

    public void setClay( int clay ) {
        this.clay = clay;
    }

    public void setWool( int wool ) {
        this.wool = wool;
    }

    public void setWood( int wood ) {
        this.wood = wood;
    }

    public void setWheat( int wheat ) {
        this.wheat = wheat;
    }

    public void playDevelepomentCard( int index ) {
        if ( this.developementCards.remove( index ).type == CardClass.KNIGHT ) {
            this.knightsRevealed++;
        }
    }

    public boolean isHasTheMostKnights() {
        return hasTheMostKnights;
    }

    public boolean isHasLongestRoad() {
        return hasLongestRoad;
    }

    public boolean isHasAccessToWoolHarbour() {
        return hasAccessToWoolHarbour;
    }

    public boolean isHasAccessToWoodHarbour() {
        return hasAccessToWoodHarbour;
    }

    public boolean isHasAccessToWheatHarbour() {
        return hasAccessToWheatHarbour;
    }

    public boolean isHasAccessToStoneHarbour() {
        return hasAccessToStoneHarbour;
    }

    public boolean isHasAccessToClayHarbour() {
        return hasAccessToClayHarbour;
    }

    public boolean isHasAccessTo3to1Harbour() {
        return hasAccessTo3to1Harbour;
    }

    public void addKnightRevealed() {
        this.knightsRevealed++;
    }

    public void addPointsHidden( int n ) {
        this.pointsHidden += n;
    }

    public void addPointsPublic( int n ) {
        this.pointsPublic += n;
    }

    public LinkedList<Card> getDevelopementCards() {
        return developementCards;
    }

    public void setDevelopementCards( LinkedList<Card> developementCards ) {
        this.developementCards = developementCards;
    }
    
    public void enableAllDevCards() {
        for ( int i=0; i<this.developementCards.size(); i++ ) {
            this.developementCards.get( i ).enable();
        }
    }
}
