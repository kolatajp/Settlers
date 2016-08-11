package drawpanel;

import java.awt.Color;
import java.awt.Image;

public class DrawableLine extends DrawableObject {
    int bottomX, bottomY;
    Color color;
    
    public DrawableLine( String imageName, int topLeftX, int topLeftY, String id ) {
        super( imageName, topLeftX, topLeftY, id );
    }

    public DrawableLine( Image image, int topLeftX, int topLeftY, String id ) {
        super( image, topLeftX, topLeftY, id );
    }

    public DrawableLine( Image image, int topLeftX, int topLeftY ) {
        super( image, topLeftX, topLeftY );
    }

    public DrawableLine() {
        super( "", 0, 0 );
    }
    
    public DrawableLine( String n, int x2, int y2 ) {
        super( n, x2, y2 );
    }
    
    public void init( int x, int y ) {
        this.bottomX = x;
        this.bottomY = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor( Color color ) {
        this.color = color;
    }

}
