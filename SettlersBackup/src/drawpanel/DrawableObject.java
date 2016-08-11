package drawpanel;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import javax.imageio.ImageIO;

public class DrawableObject {
    Image image;
    int topLeftX;
    int topLeftY;
    
    String id;
    
    String resourcePath;

    public DrawableObject( Image image, int topLeftX, int topLeftY ) {
        this.image = image;
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.id = "none";
    }

    public DrawableObject( String imageName, int topLeftX, int topLeftY ) {
        this.image = Toolkit.getDefaultToolkit().getImage( getClass().getResource( imageName ) );
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.id = imageName;
        this.resourcePath = imageName;
    }
    
    public DrawableObject( Image image, int topLeftX, int topLeftY, String id ) {
        this.image = image;
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.id = id;
    }

    public DrawableObject( String imageName, int topLeftX, int topLeftY, String id ) {
        this.image = Toolkit.getDefaultToolkit().getImage( getClass().getResource( imageName ) );
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.id = id;
        this.resourcePath = imageName;
    }

    public void setTopLeftY( int topLeftY ) {
        this.topLeftY = topLeftY;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public void setTopLeftX( int topLeftX ) {
        this.topLeftX = topLeftX;
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage( Image image ) {
        this.image = image;
    }
}
