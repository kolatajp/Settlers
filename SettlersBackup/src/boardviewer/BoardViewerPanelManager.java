package boardviewer;

import drawpanel.DrawPanel;
import drawpanel.DrawPanelManager;
import drawpanel.DrawableLine;
import drawpanel.DrawableObject;
import java.awt.Color;
import java.util.ArrayList;
import mechanics.GameMechanics;
import mechanics.Hex;
import mechanics.JunctionEntity;
import mechanics.JunctionEntityClass;
import mechanics.Player;
import mechanics.Resource;
import solver.Solver;

public class BoardViewerPanelManager {
    DrawPanelManager dpm;
    GameMechanics mechanics;

    public BoardViewerPanelManager( DrawPanelManager dpm, GameMechanics mechanics ) {
        this.dpm = dpm;
        this.mechanics = mechanics;
    }

    public BoardViewerPanelManager( DrawPanel drawPanel, GameMechanics mechanics ) {
        this.dpm = new DrawPanelManager( drawPanel );
        this.mechanics = mechanics;
    }

    //dodaje obiekty do dpm z planszy
    public void initBoardPanel() {
        //junctions/skrzyzowania
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 322 - 23, 54 - 23, "none_junction_01" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 391 - 23, 54 - 23, "none_junction_02" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 289 - 23, 113 - 23, "none_junction_04" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 424 - 23, 113 - 23, "none_junction_05" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 322 - 23, 172 - 23, "none_junction_09" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 391 - 23, 172 - 23, "none_junction_10" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 216 - 23, 116 - 23, "none_junction_03" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 183 - 23, 175 - 23, "none_junction_08" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 216 - 23, 234 - 23, "none_junction_14" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 285 - 23, 234 - 23, "none_junction_15" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 496 - 23, 115 - 23, "none_junction_06" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 529 - 23, 174 - 23, "none_junction_11" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 427 - 23, 233 - 23, "none_junction_16" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 496 - 23, 233 - 23, "none_junction_17" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 110 - 23, 176 - 23, "none_junction_07" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 77 - 23, 235 - 23, "none_junction_13" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 110 - 23, 294 - 23, "none_junction_19" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 179 - 23, 294 - 23, "none_junction_20" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 322 - 23, 295 - 23, "none_junction_21" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 391 - 23, 295 - 23, "none_junction_22" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 601 - 23, 178 - 23, "none_junction_12" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 634 - 23, 237 - 23, "none_junction_18" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 532 - 23, 296 - 23, "none_junction_23" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 601 - 23, 296 - 23, "none_junction_24" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 216 - 23, 356 - 23, "none_junction_26" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 285 - 23, 356 - 23, "none_junction_27" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 428 - 23, 358 - 23, "none_junction_28" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 497 - 23, 358 - 23, "none_junction_29" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 78 - 23, 358 - 23, "none_junction_25" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 111 - 23, 417 - 23, "none_junction_31" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 180 - 23, 417 - 23, "none_junction_32" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 321 - 23, 419 - 23, "none_junction_33" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 390 - 23, 419 - 23, "none_junction_34" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 639 - 23, 362 - 23, "none_junction_30" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 537 - 23, 421 - 23, "none_junction_35" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 606 - 23, 421 - 23, "none_junction_36" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 216 - 23, 479 - 23, "none_junction_38" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 285 - 23, 479 - 23, "none_junction_39" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 429 - 23, 482 - 23, "none_junction_40" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 498 - 23, 482 - 23, "none_junction_41" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 78 - 23, 482 - 23, "none_junction_37" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 111 - 23, 541 - 23, "none_junction_43" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 180 - 23, 541 - 23, "none_junction_44" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 322 - 23, 543 - 23, "none_junction_45" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 391 - 23, 543 - 23, "none_junction_46" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 638 - 23, 487 - 23, "none_junction_42" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 536 - 23, 546 - 23, "none_junction_47" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 605 - 23, 546 - 23, "none_junction_48" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 217 - 23, 603 - 23, "none_junction_49" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 291 - 23, 606 - 23, "none_junction_50" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 430 - 23, 605 - 23, "none_junction_51" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 499 - 23, 605 - 23, "none_junction_52" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 324 - 23, 665 - 23, "none_junction_53" ), 2 );
        this.dpm.addImageToLayer( new DrawableObject( "/images/junction.png", 393 - 23, 665 - 23, "none_junction_54" ), 2 );

        //inicjalizacja reszty obiektow, jak hexy, zlodziej i porty
        String temporaryId;
        String temporaryResourceName;
        String temporaryPath;
        String temporaryNumberPath;
        int temporaryIndex;
        int temporaryTopLeftX;
        int temporaryTopLeftY;
        int correctNumberXPos = 67 - 30; //poprawka do lokowania numerkow
        int correctNumberYPos = 59 - 30;
        int correctThiefXPos = 67 - 22;
        int correctThiefYPos = 59 - 45;
        //hexy i numerki + zlodziej

        //none_hex_01
        temporaryIndex = 1;
        temporaryId = "none_hex_01";
        temporaryTopLeftX = 289;
        temporaryTopLeftY = 54;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_02
        temporaryIndex = 2;
        temporaryId = "none_hex_02";
        temporaryTopLeftX = 183;
        temporaryTopLeftY = 116;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_03
        temporaryIndex = 3;
        temporaryId = "none_hex_03";
        temporaryTopLeftX = 394;
        temporaryTopLeftY = 115;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_04
        temporaryIndex = 4;
        temporaryId = "none_hex_04";
        temporaryTopLeftX = 77;
        temporaryTopLeftY = 176;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_05
        temporaryIndex = 5;
        temporaryId = "none_hex_05";
        temporaryTopLeftX = 289;
        temporaryTopLeftY = 177;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_06
        temporaryIndex = 6;
        temporaryId = "none_hex_06";
        temporaryTopLeftX = 499;
        temporaryTopLeftY = 178;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_07
        temporaryIndex = 7;
        temporaryId = "none_hex_07";
        temporaryTopLeftX = 183;
        temporaryTopLeftY = 238;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_08
        temporaryIndex = 8;
        temporaryId = "none_hex_08";
        temporaryTopLeftX = 395;
        temporaryTopLeftY = 240;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_09
        temporaryIndex = 9;
        temporaryId = "none_hex_09";
        temporaryTopLeftX = 78;
        temporaryTopLeftY = 299;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_10
        temporaryIndex = 10;
        temporaryId = "none_hex_10";
        temporaryTopLeftX = 288;
        temporaryTopLeftY = 301;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_11
        temporaryIndex = 11;
        temporaryId = "none_hex_11";
        temporaryTopLeftX = 504;
        temporaryTopLeftY = 303;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_12
        temporaryIndex = 12;
        temporaryId = "none_hex_12";
        temporaryTopLeftX = 183;
        temporaryTopLeftY = 361;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_13
        temporaryIndex = 13;
        temporaryId = "none_hex_13";
        temporaryTopLeftX = 396;
        temporaryTopLeftY = 364;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_14
        temporaryIndex = 14;
        temporaryId = "none_hex_14";
        temporaryTopLeftX = 78;
        temporaryTopLeftY = 423;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_15
        temporaryIndex = 15;
        temporaryId = "none_hex_15";
        temporaryTopLeftX = 289;
        temporaryTopLeftY = 425;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_16
        temporaryIndex = 16;
        temporaryId = "none_hex_16";
        temporaryTopLeftX = 503;
        temporaryTopLeftY = 428;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_17
        temporaryIndex = 17;
        temporaryId = "none_hex_17";
        temporaryTopLeftX = 184;
        temporaryTopLeftY = 485;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_18
        temporaryIndex = 18;
        temporaryId = "none_hex_18";
        temporaryTopLeftX = 397;
        temporaryTopLeftY = 487;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //none_hex_19
        temporaryIndex = 19;
        temporaryId = "none_hex_19";
        temporaryTopLeftX = 291;
        temporaryTopLeftY = 547;
        //temporaryResourceHere
        temporaryResourceName = this.resource2string( this.mechanics.getHexs()[temporaryIndex].getResource() );
        temporaryPath = "/images/" + temporaryResourceName + "hex.png";
        //isEnabled option Here
        if ( !this.mechanics.getHexs()[temporaryIndex].isEnabled() ) {
            this.dpm.addImageToLayer( new DrawableObject( "/images/thief.png", temporaryTopLeftX + correctThiefXPos, temporaryTopLeftY + correctThiefYPos ), 1 );
        }
        //numerek
        temporaryNumberPath = "/images/" + this.mechanics.getHexs()[temporaryIndex].getNumber() + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryNumberPath, temporaryTopLeftX + correctNumberXPos, temporaryTopLeftY + correctNumberYPos ), 3 );
        //wlasciwe polecenie dpm add imagetoLayer
        this.dpm.addImageToLayer( new DrawableObject( temporaryPath, temporaryTopLeftX, temporaryTopLeftY, temporaryId ), 4 );

        //!!!!!!!!!!!!!!
        //numerki powinny byc inicjalizowane powyzej!
        //porty (na podstawie stalych umiejscowionych portow na junctions 3-4,5-6,12-18,30-36,47-52,53-54,49-44,37-31,13-7)
        String temporaryHarbourPath;
        String harbourAxis;

        //3-4
        temporaryIndex = 3;
        temporaryResourceName = this.resource2string( this.mechanics.getJunctions()[temporaryIndex].getHarbour() );
        temporaryTopLeftX = 216;
        temporaryTopLeftY = 76;
        harbourAxis = "0";
        temporaryHarbourPath = "/images/h" + temporaryResourceName + harbourAxis + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryHarbourPath, temporaryTopLeftX, temporaryTopLeftY ), 5 );

        //5-6
        temporaryIndex = 5;
        temporaryResourceName = this.resource2string( this.mechanics.getJunctions()[temporaryIndex].getHarbour() );
        temporaryTopLeftX = 427;
        temporaryTopLeftY = 76;
        harbourAxis = "0";
        temporaryHarbourPath = "/images/h" + temporaryResourceName + harbourAxis + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryHarbourPath, temporaryTopLeftX, temporaryTopLeftY ), 5 );

        //12-18
        temporaryIndex = 12;
        temporaryResourceName = this.resource2string( this.mechanics.getJunctions()[temporaryIndex].getHarbour() );
        temporaryTopLeftX = 603;
        temporaryTopLeftY = 165;
        harbourAxis = "1";
        temporaryHarbourPath = "/images/h" + temporaryResourceName + harbourAxis + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryHarbourPath, temporaryTopLeftX, temporaryTopLeftY ), 5 );

        //30-36
        temporaryIndex = 30;
        temporaryResourceName = this.resource2string( this.mechanics.getJunctions()[temporaryIndex].getHarbour() );
        temporaryTopLeftX = 606;
        temporaryTopLeftY = 363;
        harbourAxis = "2";
        temporaryHarbourPath = "/images/h" + temporaryResourceName + harbourAxis + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryHarbourPath, temporaryTopLeftX, temporaryTopLeftY ), 5 );

        //47-52
        temporaryIndex = 47;
        temporaryResourceName = this.resource2string( this.mechanics.getJunctions()[temporaryIndex].getHarbour() );
        temporaryTopLeftX = 501;
        temporaryTopLeftY = 544;
        harbourAxis = "2";
        temporaryHarbourPath = "/images/h" + temporaryResourceName + harbourAxis + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryHarbourPath, temporaryTopLeftX, temporaryTopLeftY ), 5 );

        //53-54
        temporaryIndex = 54;
        temporaryResourceName = this.resource2string( this.mechanics.getJunctions()[temporaryIndex].getHarbour() );
        temporaryTopLeftX = 324;
        temporaryTopLeftY = 666;
        harbourAxis = "3";
        temporaryHarbourPath = "/images/h" + temporaryResourceName + harbourAxis + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryHarbourPath, temporaryTopLeftX, temporaryTopLeftY ), 5 );

        //49-44
        temporaryIndex = 44;
        temporaryResourceName = this.resource2string( this.mechanics.getJunctions()[temporaryIndex].getHarbour() );
        temporaryTopLeftX = 161;
        temporaryTopLeftY = 543;
        harbourAxis = "4";
        temporaryHarbourPath = "/images/h" + temporaryResourceName + harbourAxis + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryHarbourPath, temporaryTopLeftX, temporaryTopLeftY ), 5 );

        //37-31
        temporaryIndex = 31;
        temporaryResourceName = this.resource2string( this.mechanics.getJunctions()[temporaryIndex].getHarbour() );
        temporaryTopLeftX = 55;
        temporaryTopLeftY = 408;
        harbourAxis = "5";
        temporaryHarbourPath = "/images/h" + temporaryResourceName + harbourAxis + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryHarbourPath, temporaryTopLeftX, temporaryTopLeftY ), 5 );

        //13-7
        temporaryIndex = 7;
        temporaryResourceName = this.resource2string( this.mechanics.getJunctions()[temporaryIndex].getHarbour() );
        temporaryTopLeftX = 53;
        temporaryTopLeftY = 163;
        harbourAxis = "5";
        temporaryHarbourPath = "/images/h" + temporaryResourceName + harbourAxis + ".png";
        this.dpm.addImageToLayer( new DrawableObject( temporaryHarbourPath, temporaryTopLeftX, temporaryTopLeftY ), 5 );

        //ocean
        this.dpm.addImageToLayer( new DrawableObject( "/images/oceanSmall.png", 0, 0, "none_ocean_00" ), 6 );
    }

    private String resource2string( Resource r ) {
        if ( r == Resource.CLAY ) {
            return "clay";
        } else if ( r == Resource.DESERT ) {
            return "desert";
        } else if ( r == Resource.NONE ) {
            return "none";
        } else if ( r == Resource.OCEAN ) {
            return "ocean";
        } else if ( r == Resource.STONE ) {
            return "stone";
        } else if ( r == Resource.WHEAT ) {
            return "wheat";
        } else if ( r == Resource.WOOL ) {
            return "wool";
        } else if ( r == Resource.WOOD ) {
            return "wood";
        } else {
            return "unknown";
        }
    }

    public void repaint() {
        this.dpm.repaint();
    }

    public DrawPanelManager getDpm() {
        return dpm;
    }

    private void addTownToJunctionByNumberOfPlayer( int jNumber, int pNumber ) {
        String id = "none_junction_";
        if ( jNumber < 10 ) {
            id += "0";
        }
        id += jNumber;
        DrawableObject tempOb = this.dpm.getObjectById( id );
        int x = tempOb.getTopLeftX();
        int y = tempOb.getTopLeftY();

        String path = "/images/p";
        if ( pNumber == 0 ) {
            path += "red";
        } else if ( pNumber == 1 ) {
            path += "yellow";
        } else if ( pNumber == 2 ) {
            path += "blue";
        } else if ( pNumber == 3 ) {
            path += "purple";
        }

        path += "t.png";

        id = pNumber + "_town_";
        if ( jNumber < 10 ) {
            id += "0";
        }
        id += jNumber;

        this.dpm.addImageToLayer( new DrawableObject( path, x, y, id ), 1 );
    }

    private void addCityToJunctionByNumberOfPlayer( int jNumber, int pNumber ) {
        //usuwanie sterej osady, jezeli taka tam jest
        String oldTownId = "" + pNumber;
        oldTownId += "_town_" + Solver.complementInt( jNumber );
        this.dpm.removeObjectById( oldTownId );
        
        //dodawania nowego miasta
        String id = "none_junction_";
        if ( jNumber < 10 ) {
            id += "0";
        }
        id += jNumber;
        DrawableObject tempOb = this.dpm.getObjectById( id );
        int x = tempOb.getTopLeftX();
        int y = tempOb.getTopLeftY();

        String path = "/images/p";
        if ( pNumber == 0 ) {
            path += "red";
        } else if ( pNumber == 1 ) {
            path += "yellow";
        } else if ( pNumber == 2 ) {
            path += "blue";
        } else if ( pNumber == 3 ) {
            path += "purple";
        }

        path += "c.png";

        id = pNumber + "_city_";
        if ( jNumber < 10 ) {
            id += "0";
        }
        id += jNumber;

        this.dpm.addImageToLayer( new DrawableObject( path, x, y, id ), 1 );
    }

    private void addRoadFromJunctionAToJunctionBToPlayer( int junctionA, int junctionB, int playerNumber ) {
        //TODO
    }
    
    public void reconsiderJunctions() {
        JunctionEntity[] tempJunctions = this.mechanics.getJunctions();
        String tempId;
        
        for ( int i = 1; i < tempJunctions.length; i++ ) {
            
            if ( tempJunctions[i].getEntityClass() == JunctionEntityClass.TOWN ) {
                tempId = tempJunctions[i].getPlayerNumber() + "_" + "town" + "_";
                if ( i < 10 ) {
                    tempId += "0";
                }
                tempId += i;

                //jezeli nie ma to dodaj
                if ( this.dpm.getObjectById( tempId ) == null ) {
                    this.addTownToJunctionByNumberOfPlayer( i, tempJunctions[i].getPlayerNumber() );
                }
            } else if ( tempJunctions[i].getEntityClass()== JunctionEntityClass.CITY ) {
                tempId = tempJunctions[i].getPlayerNumber() + "_" + "city" + "_";
                if ( i < 10 ) {
                    tempId += "0";
                }
                tempId += i;

                //jezeli nie ma to dodaj
                if ( this.dpm.getObjectById( tempId ) == null ) {
                    this.addCityToJunctionByNumberOfPlayer( i, tempJunctions[i].getPlayerNumber() );
                }
            }
        }
    }
    
    public void reconsiderThief() {
        int x, y;
        Hex tempH;
        
        String hId;
        DrawableObject tempHexObject;
        
        for ( int i=1; i<this.mechanics.getHexs().length; i++ ) {
            tempH = this.mechanics.getHexs()[i];
            
            hId = "none_hex_";
            if ( i < 10 ) {
                hId += "0";
            }
            hId += i;
            tempHexObject = this.getDpm().getObjectById( hId );
            
            //deb
            //System.out.println( hId );
            
            if ( !tempH.isEnabled() ) {
                DrawableObject dOb = this.dpm.getObjectById( "/images/thief.png" );
                dOb.setTopLeftX( tempHexObject.getTopLeftX() + 45 );
                dOb.setTopLeftY( tempHexObject.getTopLeftY() + 14 );
            }
        }
    }
    
    public void reconsiderRoads() {
        String id = "";
        String id2 = "";
        String num1, num2;
        for ( int pit = 0; pit < this.mechanics.getPlayers().length; pit++ ) {
            Player tempP = this.mechanics.getPlayers()[pit];
            ArrayList<int[]> tmpR = tempP.getRoads();
            for ( int i=0; i<tmpR.size(); i++ ) {
                id = "";
                if ( pit == 0 ) {
                    id += "red";
                } else if ( pit == 1 ) {
                    id += "yellow";
                } else if ( pit == 2 ) {
                    id += "blue";
                } else if ( pit == 3 ) {
                    id += "purple";
                }
                id += "_road_";
                
                //numerki dwa rozne
                num1 = "";
                num2 = "";
                
                int n1 = tmpR.get( i )[0];
                int n2 = tmpR.get( i )[1];
                
                if ( n1 < 10 ) {
                    num1 += "0";
                }
                if ( n2 < 10 ) {
                    num2 += "0";
                }
                num1 += n1;
                num2 += n2;
                
                id2 = "" + id;
                id += num1 + "_" + num2;
                id2 += num2 + "_" + num1;
                
                //jezeli jest takie id, to zostawiamy, jak nie ma to wstaw droge
                if ( this.dpm.getObjectById( id ) == null && this.dpm.getObjectById( id2 ) == null ) {
                    //wstawiam droge
                    int x1, x2, y1, y2;
                    String junction1, junction2;
                    junction1 = "none_junction_" + num1;
                    junction2 = "none_junction_" + num2;
                    //deb
                    //System.out.println( "j1:" + junction1 );
                    //System.out.println( "j2:" + junction2 );
                    DrawableObject j1ob = this.dpm.getObjectById( junction1 );
                    DrawableObject j2ob = this.dpm.getObjectById( junction2 );
                    x1 = j1ob.getTopLeftX() + 22;
                    y1 = j1ob.getTopLeftY() + 22;
                    x2 = j2ob.getTopLeftX() + 22;
                    y2 = j2ob.getTopLeftY() + 22;
                    DrawableLine temporaryLine = new DrawableLine( id, x1, y1 );
                    temporaryLine.init( x2, y2 );
                    //deb
                    //System.out.println( "dodalem droge o x1y1, x2y2:" + x1 + ":" + y1 + "-" + x2 + ":" + y2 );
                    Color tempColor;
                    if ( pit == 0 ) {
                        tempColor = Color.RED;
                    } else if ( pit == 1 ) {
                        tempColor = Color.YELLOW;
                    } else if ( pit == 2 ) {
                        //deb
                        //zmiana usun
                        //tempColor = Color.BLUE;
                        tempColor = Color.CYAN;
                    } else if ( pit == 3 ) {
                        tempColor = Color.MAGENTA;
                    } else {
                        tempColor = Color.black;
                    }
                    temporaryLine.setColor( tempColor );
                    this.dpm.addImageToLayer( temporaryLine , 3 );
                }
            }
        }
    }
    
    public void reconsiderAll() {
        this.reconsiderJunctions();
        this.reconsiderRoads();
        this.reconsiderThief();
    }

    public GameMechanics getMechanics() {
        return mechanics;
    }
    
    public DrawPanel getDrawPanel() {
        return this.dpm.getPanel();
    }
}
