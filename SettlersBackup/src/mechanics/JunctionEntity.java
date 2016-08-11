package mechanics;

public class JunctionEntity implements java.io.Serializable {
    private JunctionEntityClass entityClass = JunctionEntityClass.NONE;
    private int playerNumber = -1;
    int neighbourJunctionsNumbers[];
    int id = -1;
    
    //port
    Resource harbour = Resource.NONE;

    //konstruktory, sety i gety    
    public JunctionEntity( int[] neighbourJunctionsNumbers ) {
        this.neighbourJunctionsNumbers = neighbourJunctionsNumbers;
    }
    
    public JunctionEntity( int aJunction, int bJunction, int cJunction ) {
        this.neighbourJunctionsNumbers = new int[]{ aJunction, bJunction, cJunction };
    }
    
    public JunctionEntity( int aJunction, int bJunction, int cJunction, int id ) {
        this.neighbourJunctionsNumbers = new int[]{ aJunction, bJunction, cJunction };
        this.id = id;
    }

    public JunctionEntity() {
        this.neighbourJunctionsNumbers = new int[]{ 0, 0, 0 };
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber( int playerNumber ) {
        this.playerNumber = playerNumber;
    }

    public JunctionEntityClass getEntityClass() {
        return entityClass;
    }

    public void setEntityClass( JunctionEntityClass entityClass ) {
        this.entityClass = entityClass;
    }

    public void setHarbour( Resource harbour ) {
        this.harbour = harbour;
    }

    public Resource getHarbour() {
        return harbour;
    }
    
    public void setNeighbourJunctionsNumbers( int ja, int jb, int jc ) {
        this.neighbourJunctionsNumbers = new int[]{ ja, jb, jc };
    }

    public int[] getNeighbourJunctionsNumbers() {
        return neighbourJunctionsNumbers;
    }

    //metody
}
