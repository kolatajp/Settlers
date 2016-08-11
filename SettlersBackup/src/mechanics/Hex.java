package mechanics;

public class Hex implements java.io.Serializable {
    Resource resource;
    int number;
    JunctionEntity[] junctions;
    
    //czy ma na sobie zlodzieja
    boolean enabled = true;

    public Hex( Resource resource, int number, JunctionEntity[] junctions ) {
        this.resource = resource;
        this.number = number;
        this.junctions = junctions;
    }
    
    public Hex( Resource resource, int number ) {
        this.resource = resource;
        this.number = number;
    }
    
    public void setJunctions( JunctionEntity[] junctions ) {
        this.junctions = junctions;
    }

    public JunctionEntity[] getJunctions() {
        return junctions;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled( boolean enabled ) {
        this.enabled = enabled;
    }

    public void setResource( Resource resource ) {
        this.resource = resource;
    }

    public Resource getResource() {
        return resource;
    }

    public void setNumber( int number ) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

}
