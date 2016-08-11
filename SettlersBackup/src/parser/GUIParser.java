package parser;

import boardviewer.BoardViewerPanelManager;
import java.awt.*;
import javax.swing.*;

public class GUIParser {
    BoardViewerPanelManager bvpm;

    TextArea playerInfoArea;

    JButton buildRoadButton;
    JButton buildTownButton;
    JButton buildCityButton;

    JButton devCardBuyButton;
    JButton devCardViewButton;
    JList devCardList;
    JButton devCardButtonUse;

    //trade
    JSpinner tradeWoolGiveSpinner;
    JSpinner tradeWoolGetSpinner;

    JSpinner tradeWoodGiveSpinner;
    JSpinner tradeWoodGetSpinner;

    JSpinner tradeStoneGiveSpinner;
    JSpinner tradeStoneGetSpinner;

    JSpinner tradeWheatGiveSpinner;
    JSpinner tradeWheatGetSpinner;

    JSpinner tradeClayGiveSpinner;
    JSpinner tradeClayGetSpinner;

    JButton tradeBankButton;
    JButton tradePlayerButton;
    JComboBox tradePlayerChooseCombobox;
    JButton tradeZeroAllButton;

    //pozostale
    JButton rollButton;
    JButton eotButton;

    //konstruktory / constructors
    public GUIParser( BoardViewerPanelManager bvpm, TextArea playerInfoArea, JButton buildRoadButton,
                      JButton buildTownButton, JButton buildCityButton, JButton devCardBuyButton,
                      JButton devCardViewButton, JList devCardList, JButton devCardButtonUse,
                      JSpinner tradeWoolGiveSpinner, JSpinner tradeWoolGetSpinner, JSpinner tradeWoodGiveSpinner,
                      JSpinner tradeWoodGetSpinner, JSpinner tradeStoneGiveSpinner, JSpinner tradeStoneGetSpinner,
                      JSpinner tradeWheatGiveSpinner, JSpinner tradeWheatGetSpinner, JSpinner tradeClayGiveSpinner,
                      JSpinner tradeClayGetSpinner, JButton tradeBankButton, JButton tradePlayerButton,
                      JComboBox tradePlayerChooseCombobox, JButton tradeZeroAllButton, JButton rollButton,
                      JButton eotButton ) {
        this.bvpm = bvpm;
        this.playerInfoArea = playerInfoArea;
        this.buildRoadButton = buildRoadButton;
        this.buildTownButton = buildTownButton;
        this.buildCityButton = buildCityButton;
        this.devCardBuyButton = devCardBuyButton;
        this.devCardViewButton = devCardViewButton;
        this.devCardList = devCardList;
        this.devCardButtonUse = devCardButtonUse;
        this.tradeWoolGiveSpinner = tradeWoolGiveSpinner;
        this.tradeWoolGetSpinner = tradeWoolGetSpinner;
        this.tradeWoodGiveSpinner = tradeWoodGiveSpinner;
        this.tradeWoodGetSpinner = tradeWoodGetSpinner;
        this.tradeStoneGiveSpinner = tradeStoneGiveSpinner;
        this.tradeStoneGetSpinner = tradeStoneGetSpinner;
        this.tradeWheatGiveSpinner = tradeWheatGiveSpinner;
        this.tradeWheatGetSpinner = tradeWheatGetSpinner;
        this.tradeClayGiveSpinner = tradeClayGiveSpinner;
        this.tradeClayGetSpinner = tradeClayGetSpinner;
        this.tradeBankButton = tradeBankButton;
        this.tradePlayerButton = tradePlayerButton;
        this.tradePlayerChooseCombobox = tradePlayerChooseCombobox;
        this.tradeZeroAllButton = tradeZeroAllButton;
        this.rollButton = rollButton;
        this.eotButton = eotButton;
    }

    //dla serwera
    public GUIParser( boardviewer.BoardViewerPanelManager bvpm ) {
        this.bvpm = bvpm;
    }

    //zaslepka, mozliwe do uzycia w serwerze
    public GUIParser() {

    }

    //parser
    public void parse( String mes ) {
        if ( mes.contains( "none" ) ) {
            //TODO
        } else if ( mes.contains( "enablebuildRoadButton" ) ) {
            this.buildRoadButton.setEnabled( true );
        } else if ( mes.contains( "disablebuildRoadButton" ) ) {
            this.buildRoadButton.setEnabled( false );
        } else if ( mes.contains( "enablebuildTownButton" ) ) {
            this.buildTownButton.setEnabled( true );
        } else if ( mes.contains( "disablebuildTownButton" ) ) {
            this.buildTownButton.setEnabled( false );
        } else if ( mes.contains( "enablebuildCityButton" ) ) {
            this.buildCityButton.setEnabled( true );
        } else if ( mes.contains( "disablebuildCityButton" ) ) {
            this.buildCityButton.setEnabled( false );
        } else if ( mes.contains( "enabledevCardBuyButton" ) ) {
            this.devCardBuyButton.setEnabled( true );
        } else if ( mes.contains( "disabledevCardBuyButton" ) ) {
            this.devCardBuyButton.setEnabled( false );
        } else if ( mes.contains( "enabledevCardViewButton" ) ) {
            this.devCardViewButton.setEnabled( true );
        } else if ( mes.contains( "disabledevCardViewButton" ) ) {
            this.devCardViewButton.setEnabled( false );
        } else if ( mes.contains( "enabledevCardList" ) ) {
            this.devCardList.setEnabled( true );
        } else if ( mes.contains( "disabledevCardList" ) ) {
            this.devCardList.setEnabled( false );
        } else if ( mes.contains( "enabledevCardButtonUse" ) ) {
            this.devCardButtonUse.setEnabled( true );
        } else if ( mes.contains( "disabledevCardButtonUse" ) ) {
            this.devCardButtonUse.setEnabled( false );
        } else if ( mes.contains( "enabletradeWoolGiveSpinner" ) ) {
            this.tradeWoolGiveSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeWoolGiveSpinner" ) ) {
            this.tradeWoolGiveSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeWoolGetSpinner" ) ) {
            this.tradeWoolGetSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeWoolGetSpinner" ) ) {
            this.tradeWoolGetSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeWoodGiveSpinner" ) ) {
            this.tradeWoodGiveSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeWoodGiveSpinner" ) ) {
            this.tradeWoodGiveSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeWoodGetSpinner" ) ) {
            this.tradeWoodGetSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeWoodGetSpinner" ) ) {
            this.tradeWoodGetSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeStoneGiveSpinner" ) ) {
            this.tradeStoneGiveSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeStoneGiveSpinner" ) ) {
            this.tradeStoneGiveSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeStoneGetSpinner" ) ) {
            this.tradeStoneGetSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeStoneGetSpinner" ) ) {
            this.tradeStoneGetSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeWheatGiveSpinner" ) ) {
            this.tradeWheatGiveSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeWheatGiveSpinner" ) ) {
            this.tradeWheatGiveSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeWheatGetSpinner" ) ) {
            this.tradeWheatGetSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeWheatGetSpinner" ) ) {
            this.tradeWheatGetSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeClayGiveSpinner" ) ) {
            this.tradeClayGiveSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeClayGiveSpinner" ) ) {
            this.tradeClayGiveSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeClayGetSpinner" ) ) {
            this.tradeClayGetSpinner.setEnabled( true );
        } else if ( mes.contains( "disabletradeClayGetSpinner" ) ) {
            this.tradeClayGetSpinner.setEnabled( false );
        } else if ( mes.contains( "enabletradeBankButton" ) ) {
            this.tradeBankButton.setEnabled( true );
        } else if ( mes.contains( "disabletradeBankButton" ) ) {
            this.tradeBankButton.setEnabled( false );
        } else if ( mes.contains( "enabletradePlayerButton" ) ) {
            this.tradePlayerButton.setEnabled( true );
        } else if ( mes.contains( "disabletradePlayerButton" ) ) {
            this.tradePlayerButton.setEnabled( false );
        } else if ( mes.contains( "enabletradePlayerChooseCombobox" ) ) {
            this.tradePlayerChooseCombobox.setEnabled( true );
        } else if ( mes.contains( "disabletradePlayerChooseCombobox" ) ) {
            this.tradePlayerChooseCombobox.setEnabled( false );
        } else if ( mes.contains( "enabletradeZeroAllButton" ) ) {
            this.tradeZeroAllButton.setEnabled( true );
        } else if ( mes.contains( "disabletradeZeroAllButton" ) ) {
            this.tradeZeroAllButton.setEnabled( false );
        } else if ( mes.contains( "enablerollButton" ) ) {
            this.rollButton.setEnabled( true );
        } else if ( mes.contains( "disablerollButton" ) ) {
            this.rollButton.setEnabled( false );
        } else if ( mes.contains( "enableeotButton" ) ) {
            this.eotButton.setEnabled( true );
        } else if ( mes.contains( "disableeotButton" ) ) {
            this.eotButton.setEnabled( false );
        } else if ( mes.contains( "enableAll" ) ) {
            this.enableAll();
        } else if ( mes.contains( "disableAll" ) ) {
            this.disableAll();
        }
    }

    public void disableAll() {
        this.buildRoadButton.setEnabled( false );
        this.buildTownButton.setEnabled( false );
        this.buildCityButton.setEnabled( false );
        this.devCardBuyButton.setEnabled( false );
        this.devCardViewButton.setEnabled( false );
        this.devCardList.setEnabled( false );
        this.devCardButtonUse.setEnabled( false );
        this.tradeWoolGiveSpinner.setEnabled( false );
        this.tradeWoolGetSpinner.setEnabled( false );
        this.tradeWoodGiveSpinner.setEnabled( false );
        this.tradeWoodGetSpinner.setEnabled( false );
        this.tradeStoneGiveSpinner.setEnabled( false );
        this.tradeStoneGetSpinner.setEnabled( false );
        this.tradeWheatGiveSpinner.setEnabled( false );
        this.tradeWheatGetSpinner.setEnabled( false );
        this.tradeClayGiveSpinner.setEnabled( false );
        this.tradeClayGetSpinner.setEnabled( false );
        this.tradeBankButton.setEnabled( false );
        this.tradePlayerButton.setEnabled( false );
        this.tradePlayerChooseCombobox.setEnabled( false );
        this.tradeZeroAllButton.setEnabled( false );
        this.rollButton.setEnabled( false );
        this.eotButton.setEnabled( false );
    }

    public void enableAll() {
        this.buildRoadButton.setEnabled( true );
        this.buildTownButton.setEnabled( true );
        this.buildCityButton.setEnabled( true );
        this.devCardBuyButton.setEnabled( true );
        this.devCardViewButton.setEnabled( true );
        this.devCardList.setEnabled( true );
        this.devCardButtonUse.setEnabled( true );
        this.tradeWoolGiveSpinner.setEnabled( true );
        this.tradeWoolGetSpinner.setEnabled( true );
        this.tradeWoodGiveSpinner.setEnabled( true );
        this.tradeWoodGetSpinner.setEnabled( true );
        this.tradeStoneGiveSpinner.setEnabled( true );
        this.tradeStoneGetSpinner.setEnabled( true );
        this.tradeWheatGiveSpinner.setEnabled( true );
        this.tradeWheatGetSpinner.setEnabled( true );
        this.tradeClayGiveSpinner.setEnabled( true );
        this.tradeClayGetSpinner.setEnabled( true );
        this.tradeBankButton.setEnabled( true );
        this.tradePlayerButton.setEnabled( true );
        this.tradePlayerChooseCombobox.setEnabled( true );
        this.tradeZeroAllButton.setEnabled( true );
        this.rollButton.setEnabled( true );
        this.eotButton.setEnabled( true );
    }

    //get / set
    
    public JButton getBuildCityButton() {
        return buildCityButton;
    }

    public JButton getBuildRoadButton() {
        return buildRoadButton;
    }

    public JButton getBuildTownButton() {
        return buildTownButton;
    }

    public BoardViewerPanelManager getBvpm() {
        return bvpm;
    }

    public JButton getDevCardButtonUse() {
        return devCardButtonUse;
    }

    public JButton getDevCardBuyButton() {
        return devCardBuyButton;
    }

    public JList getDevCardList() {
        return devCardList;
    }

    public JButton getDevCardViewButton() {
        return devCardViewButton;
    }

    public JButton getEotButton() {
        return eotButton;
    }

    public TextArea getPlayerInfoArea() {
        return playerInfoArea;
    }

    public JButton getRollButton() {
        return rollButton;
    }

    public JButton getTradeBankButton() {
        return tradeBankButton;
    }

    public JSpinner getTradeClayGetSpinner() {
        return tradeClayGetSpinner;
    }

    public JSpinner getTradeClayGiveSpinner() {
        return tradeClayGiveSpinner;
    }

    public JButton getTradePlayerButton() {
        return tradePlayerButton;
    }

    public JComboBox getTradePlayerChooseCombobox() {
        return tradePlayerChooseCombobox;
    }

    public JSpinner getTradeStoneGetSpinner() {
        return tradeStoneGetSpinner;
    }

    public JSpinner getTradeStoneGiveSpinner() {
        return tradeStoneGiveSpinner;
    }

    public JSpinner getTradeWheatGetSpinner() {
        return tradeWheatGetSpinner;
    }

    public JSpinner getTradeWheatGiveSpinner() {
        return tradeWheatGiveSpinner;
    }

    public JSpinner getTradeWoodGetSpinner() {
        return tradeWoodGetSpinner;
    }

    public JSpinner getTradeWoodGiveSpinner() {
        return tradeWoodGiveSpinner;
    }

    public JSpinner getTradeWoolGetSpinner() {
        return tradeWoolGetSpinner;
    }

    public JSpinner getTradeWoolGiveSpinner() {
        return tradeWoolGiveSpinner;
    }

    public JButton getTradeZeroAllButton() {
        return tradeZeroAllButton;
    }

    public void setBuildCityButton( JButton buildCityButton ) {
        this.buildCityButton = buildCityButton;
    }

    public void setBuildRoadButton( JButton buildRoadButton ) {
        this.buildRoadButton = buildRoadButton;
    }

    public void setBuildTownButton( JButton buildTownButton ) {
        this.buildTownButton = buildTownButton;
    }

    public void setBvpm( BoardViewerPanelManager bvpm ) {
        this.bvpm = bvpm;
    }

    public void setDevCardButtonUse( JButton devCardButtonUse ) {
        this.devCardButtonUse = devCardButtonUse;
    }

    public void setDevCardBuyButton( JButton devCardBuyButton ) {
        this.devCardBuyButton = devCardBuyButton;
    }

    public void setDevCardList( JList devCardList ) {
        this.devCardList = devCardList;
    }

    public void setDevCardViewButton( JButton devCardViewButton ) {
        this.devCardViewButton = devCardViewButton;
    }

    public void setEotButton( JButton eotButton ) {
        this.eotButton = eotButton;
    }

    public void setPlayerInfoArea( TextArea playerInfoArea ) {
        this.playerInfoArea = playerInfoArea;
    }

    public void setRollButton( JButton rollButton ) {
        this.rollButton = rollButton;
    }

    public void setTradeBankButton( JButton tradeBankButton ) {
        this.tradeBankButton = tradeBankButton;
    }

    public void setTradeClayGetSpinner( JSpinner tradeClayGetSpinner ) {
        this.tradeClayGetSpinner = tradeClayGetSpinner;
    }

    public void setTradeClayGiveSpinner( JSpinner tradeClayGiveSpinner ) {
        this.tradeClayGiveSpinner = tradeClayGiveSpinner;
    }

    public void setTradePlayerButton( JButton tradePlayerButton ) {
        this.tradePlayerButton = tradePlayerButton;
    }

    public void setTradePlayerChooseCombobox( JComboBox tradePlayerChooseCombobox ) {
        this.tradePlayerChooseCombobox = tradePlayerChooseCombobox;
    }

    public void setTradeStoneGetSpinner( JSpinner tradeStoneGetSpinner ) {
        this.tradeStoneGetSpinner = tradeStoneGetSpinner;
    }

    public void setTradeStoneGiveSpinner( JSpinner tradeStoneGiveSpinner ) {
        this.tradeStoneGiveSpinner = tradeStoneGiveSpinner;
    }

    public void setTradeWheatGetSpinner( JSpinner tradeWheatGetSpinner ) {
        this.tradeWheatGetSpinner = tradeWheatGetSpinner;
    }

    public void setTradeWheatGiveSpinner( JSpinner tradeWheatGiveSpinner ) {
        this.tradeWheatGiveSpinner = tradeWheatGiveSpinner;
    }

    public void setTradeWoodGetSpinner( JSpinner tradeWoodGetSpinner ) {
        this.tradeWoodGetSpinner = tradeWoodGetSpinner;
    }

    public void setTradeWoodGiveSpinner( JSpinner tradeWoodGiveSpinner ) {
        this.tradeWoodGiveSpinner = tradeWoodGiveSpinner;
    }

    public void setTradeWoolGetSpinner( JSpinner tradeWoolGetSpinner ) {
        this.tradeWoolGetSpinner = tradeWoolGetSpinner;
    }

    public void setTradeWoolGiveSpinner( JSpinner tradeWoolGiveSpinner ) {
        this.tradeWoolGiveSpinner = tradeWoolGiveSpinner;
    }

    public void setTradeZeroAllButton( JButton tradeZeroAllButton ) {
        this.tradeZeroAllButton = tradeZeroAllButton;
    }
}
