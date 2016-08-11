package mechanics;

public class Card implements java.io.Serializable {
    CardClass type;
    CardState state;

    public Card() {
    }

    public Card( CardClass type, CardState state ) {
        this.type = type;
        this.state = state;
    }
    
    public Card( CardClass type ) {
        this.type = type;
        this.state = CardState.DISABLED;
    }

    public CardClass getType() {
        return type;
    }

    public CardState getState() {
        return state;
    }

    public void setType( CardClass type ) {
        this.type = type;
    }

    public void setState( CardState state ) {
        this.state = state;
    }
    
    public void enable() {
        this.state = CardState.ENABLED;
    }
    
    public boolean isEnabled() {
        if ( this.state == CardState.ENABLED ) {
            return true;
        } else {
            return false;
        }
    }

}
