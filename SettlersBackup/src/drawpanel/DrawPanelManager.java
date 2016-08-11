package drawpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawPanelManager {
    ArrayList<DrawableObject>[] layers;
    int layersNumber;
    DrawPanel panel;

    int panelOriginalHeight;
    int panelOriginalWidth;

    double xScale = 1.0;
    double yScale = 1.0;

    public DrawPanelManager( DrawPanel panel ) {
        this.layers = new ArrayList[15];
        for ( int i = 0; i < 15; i++ ) {
            this.layers[i] = new ArrayList();
        }
        this.layersNumber = 15;
        this.panel = panel;
        this.panel.dpm = this;

        this.panelOriginalHeight = this.panel.getHeight();
        this.panelOriginalWidth = this.panel.getWidth();
    }

    public DrawPanelManager( DrawPanel panel, int layersNumber ) {
        this.layers = new ArrayList[layersNumber];
        for ( int i = 0; i < layersNumber; i++ ) {
            this.layers[i] = new ArrayList();
        }
        this.layersNumber = layersNumber;
        this.panel = panel;
        this.panel.dpm = this;

        this.panelOriginalHeight = this.panel.getHeight();
        this.panelOriginalWidth = this.panel.getWidth();
    }

    public void drawObject( DrawableObject drawOb, double xScale, double yScale, Graphics g ) {
        if ( drawOb.id.contains( "road" ) ) {
            int x1 = drawOb.getTopLeftX();
            int y1 = drawOb.getTopLeftY();
            int x2 = ((DrawableLine)drawOb).bottomX;
            int y2 = ((DrawableLine)drawOb).bottomY;
            
            int[] xPoints = new int[4];
            int[] yPoints = new int[4];
            
            //jezeli poziome, to tylko poszerz w wysokosci
            if ( Math.abs( y2-y1 ) < 15 ) {
                xPoints[0] = x1;
                xPoints[1] = x1;
                xPoints[2] = x2;
                xPoints[3] = x2;
                
                yPoints[0] = y1-5;
                yPoints[1] = y1+5;
                yPoints[2] = y2+5;
                yPoints[3] = y2-5;
            }
            //jezeli natomiast ukosne to w szerokosci
            else {
                yPoints[0] = y1;
                yPoints[1] = y1;
                yPoints[2] = y2;
                yPoints[3] = y2;
                
                xPoints[0] = x1-5;
                xPoints[1] = x1+5;
                xPoints[2] = x2+5;
                xPoints[3] = x2-5;
            }
            Color color = ((DrawableLine)drawOb).color;
            
            //poprawka na aktualna skale
            for ( int i=0; i<4; i++ ) {
                xPoints[i] = (int)((double)xPoints[i] * xScale);
                yPoints[i] = (int)((double)yPoints[i] * yScale);
            }
            
            g.setColor( color );
            g.fillPolygon(xPoints, yPoints, 4 );
            
        } else {
            g.drawImage( drawOb.image, (int) ((double) drawOb.topLeftX * xScale),
                         (int) ((double) drawOb.topLeftY * yScale),
                         (int) ((double) drawOb.image.getWidth( panel ) * xScale),
                         (int) ((double) drawOb.image.getHeight( panel ) * yScale),
                         panel );
        }
    }

    public void drawObjects( Graphics g ) {
        xScale = (double) this.panel.getWidth() / (double) this.panelOriginalWidth;
        yScale = (double) this.panel.getHeight() / (double) this.panelOriginalHeight;

        //rysowane od konca, tak aby zerowa warstwa byla na wierzchu
        for ( int i = this.layersNumber - 1; i >= 0; i-- ) {
            for ( int j = 0; j < this.layers[i].size(); j++ ) {
                if ( this.layers[i] != null && this.layers[i].get( j ) != null ) {
                    this.drawObject( this.layers[i].get( j ), xScale, yScale, g );
                }
            }
        }
    }

    public void addImageToLayer( DrawableObject drawOb, int layerNumber ) {
        this.layers[layerNumber].add( drawOb );
    }

    public DrawableObject[] getObjects( int x, int y ) {
        ArrayList<DrawableObject> objectsList = new ArrayList();

        for ( int i = 0; i < this.layersNumber; i++ ) {
            if ( this.layers[i] != null ) {
                for ( int j = 0; j < this.layers[i].size(); j++ ) {
                    DrawableObject currentOb = this.layers[i].get( j );
                    if ( currentOb != null
                         && x >= currentOb.topLeftX * xScale
                         && x <= currentOb.topLeftX * xScale + currentOb.image.getWidth( panel ) * xScale
                         && y >= currentOb.topLeftY * yScale
                         && y <= currentOb.topLeftY * yScale + currentOb.image.getHeight( panel ) * yScale ) {
                        objectsList.add( currentOb );
                    }
                }
            }
        }

        return objectsList.toArray( new DrawableObject[objectsList.size()] );
    }

    public DrawableObject getObjectById( String id ) {
        for ( ArrayList list : layers ) {
            for ( int j = 0; j < list.size(); j++ ) {
                if ( id.equals( ((DrawableObject) list.get( j )).id ) ) {
                    return ((DrawableObject) list.get( j ));
                }
            }
        }

        return null;
    }

    public ArrayList<DrawableObject>[] getLayers() {
        return layers;
    }

    public void repaint() {
        this.panel.repaint();
    }

    public DrawableObject removeObjectById( String id ) {
        DrawableObject out = null;

        for ( ArrayList list : layers ) {
            for ( int j = 0; j < list.size(); j++ ) {
                if ( id.equals( ((DrawableObject) list.get( j )).id ) ) {
                    out = ((DrawableObject) list.get( j ));
                    list.remove( j );
                }
            }
        }

        return out;
    }

    public DrawPanel getPanel() {
        return panel;
    }
}
