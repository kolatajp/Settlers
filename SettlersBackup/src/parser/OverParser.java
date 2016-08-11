package parser;

import java.awt.TextArea;
import javax.swing.DefaultComboBoxModel;
import mechanics.GameMechanics;
import net.ServerManager;
import serializer.SaverLoader;
import solver.Solver;

public class OverParser {   //TODO
    GameMechanics mechanics;
    GameParser gameParser;
    GUIParser guiParser;
    PlayerToServerParser p2sp;
    TextArea logBox;
    TextArea chatBox;

    ServerManager serverManager;

    //testowo
    //zle rozwiazanie, ale nie wiem jak lepiej w tym momencie
    int playerNumber = 8;

    //rownie testowo
    boolean setupPhase = false;
    boolean devRoadsEnabled = false;

    //obsluga gry poza powyzszymi
    //przeniesione z serwera
    int playerTurn = -1;
    int setupTurns = 0;
    int setupFinal = 0;
    int setupAdd = 1;

    //reszta obslugi gry, stany itp.
    boolean placingThief = false;
    boolean placingDevThief = false;

    public OverParser( GameParser gameParser, GUIParser guiParser, TextArea logBox, TextArea chatBox, ServerManager serverManager ) {
        this.gameParser = gameParser;
        this.guiParser = guiParser;
        this.p2sp = new parser.PlayerToServerParser( this.gameParser.game );
        this.logBox = logBox;
        this.chatBox = chatBox;
        if ( serverManager != null ) {
            this.serverManager = serverManager;
            this.mechanics = this.gameParser.game.mechanics;
        }
    }

    public void parse( String mes ) {
        String[] parts = mes.split( "_" );

        if ( parts[2].contains( "c" ) ) {
            int playerNumber = Integer.parseInt( parts[0] );
            String playerColorName = Solver.getPlayerColorStringFromNumber( playerNumber );
            this.chatBox.append( parts[1] + " (" + playerColorName + "): " + parts[3] + "\n" );

            //tylko w przypadku czatu, odsylam wiadomosc do wszystkich
            if ( this.serverManager != null ) {
                //String chatToSendString = "" + playerNumber + "_" + parts[1] + "_c_" + parts[3];
                String chatToSendString = mes;
                //deb
                if ( this.mechanics == null ) {
                    System.out.println( "mechanics null" );
                } else {
                    System.out.println( "mechanics nie null" );
                }
                System.out.println( this.mechanics.getPlayers().length );
                System.out.println( "nie null serverM" + "(" + chatToSendString + ")" );
                for ( int i = 0; i < this.mechanics.getPlayers().length; i++ ) {
                    //deb
                    System.out.println( "wysylam do" + i );
                    try {
                        this.serverManager.sendToClient( i, chatToSendString );
                    } catch ( java.lang.NullPointerException e ) {
                        //deb
                        System.out.println( "nie ma gracza " + i );
                    }
                }
            } else {
                //deb
                System.out.println( "serverM null" );
            }
        } else if ( parts[2].contains( "g" ) ) {
            this.gameParser.parse( parts[3] );
            //ewentualnie updateuj
            if ( this.guiParser.playerInfoArea != null ) {
                this.guiParser.playerInfoArea.setText( Solver.player2InfoStringReal( this.mechanics, this.playerNumber ) );
            }
        } else if ( parts[2].contains( "u" ) ) {
            this.guiParser.parse( parts[3] );
        } else if ( parts[2].contains( "v" ) ) {    //wczytywanie planszy
            String saveString = parts[3];
            try {
                //inicjalizacja wszystkiego
                this.mechanics = SaverLoader.load( saveString );
                //deb
                System.out.println( "liczba graczy:" + this.mechanics.getPlayers().length );
                this.gameParser = new parser.GameParser( new parser.Game( mechanics ) );
                this.p2sp = new parser.PlayerToServerParser( this.gameParser.game );
                //kontrolka wyboru gracza
                this.guiParser.getTradePlayerChooseCombobox().setModel( new DefaultComboBoxModel( Solver.getListFromPlayers( mechanics ) ) );
                //UWAGA tu moze nie dzialac, sprawdz
                this.guiParser.bvpm = new boardviewer.BoardViewerPanelManager( this.guiParser.bvpm.getDrawPanel(), mechanics );
                this.guiParser.bvpm.initBoardPanel();
                this.guiParser.bvpm.reconsiderAll();
                this.guiParser.bvpm.repaint();
                this.logBox.append( "Wczytano nową grę\n" );

                //wylaczanie kontrolek
                //!!!
                this.guiParser.disableAll();
            } catch ( java.io.IOException | java.lang.ClassNotFoundException e ) {
                this.logBox.append( "Nie udało się wczytać gry\n(" + e + ")\n" );
            }
        } else if ( parts[2].contains( "p" ) ) {
            String[] out = this.p2sp.parse( parts[3] );
            this.writeToBoxes( out );
            //po kazdym komunikacie gry updateuj infobox gracza
            if ( this.guiParser.playerInfoArea != null ) {
                this.guiParser.playerInfoArea.setText( Solver.player2InfoStringReal( this.mechanics, this.playerNumber ) );
            }

            //obsluga specjalna podczas setupu
            //dodaj surowce od drugiej osady
            //deb usun
            System.out.println( "przed ifem(" + parts[3] + ")" );
            if ( this.setupAdd < 0 && parts[3].contains( "nocosttown" ) ) {
                int jNumber = Integer.parseInt( parts[3].substring( 13 ) );
                String resStr = Solver.get3resourcesFromHexAroundJunction( jNumber, this.mechanics );
                //deb usun
                System.out.println( "resS=" + resStr + ",jNumber=" + jNumber );
                //do graczy
                this.serverManager.sendToAllClients( "9_server_g_res-add-p" + parts[0] + resStr );
                this.serverManager.sendToAllClients( "9_server_l_Gracz " + Solver.getPlayerColorStringFromNumber( Integer.parseInt( parts[0] ) )
                                                     + " otrzymuje " + Solver.resourcesString2Info( resStr ) + " za drugą osadę" );
                //do siebie
                this.parse( "9_server_g_res-add-p" + parts[0] + resStr );
                this.parse( "9_server_l_Gracz " + Solver.getPlayerColorStringFromNumber( Integer.parseInt( parts[0] ) )
                            + " otrzymuje " + Solver.resourcesString2Info( resStr ) + " za drugą osadę" );
            }

            //jezeli jestes serwerem == masz serverManager
            //to odeslij wiadomosc dalej, chyba ze to jedna z kilku
            //komend specjalnych:
            if ( this.serverManager != null && !mes.contains( "tradeoffer" ) && !mes.contains( "eot" )
                 && !mes.contains( "roll" ) && !mes.contains( "thiefmove" ) && !mes.contains( "devknight" )
                 && !mes.contains( "devroads" ) ) {
                this.serverManager.sendToAllClients( mes );
            }

            //obsluga roll
            //tylko dla serwera
            if ( this.serverManager != null && parts[3].contains( "roll" ) ) {
                String[] commands = out[0].split( "\n" );
                for ( int i = 0; i < commands.length; i++ ) {
                    //deb usun
                    System.out.println( "Po roll komenda:" + commands[i] );

                    //wyslij do graczy
                    this.serverManager.sendToAllClients( "9_server_g_" + commands[i] );
                    this.serverManager.sendToAllClients( "9_server_l_" + out[1] );
                    //wyslij do siebie (czyli serwer)
                    this.parse( "9_server_g_" + commands[i] );
                    this.parse( "9_server_l_" + out[1] );
                }

                //jezeli wypadlo 7 to wyslij graczowi info
                if ( out[1].contains( "7" ) ) {
                    this.serverManager.sendToClient( this.playerTurn, "9_server_o_rollresult7" );
                } else {
                    String messageToSend = out[1];
                    messageToSend = messageToSend.substring( 20, messageToSend.indexOf( "," ) );
                    this.serverManager.sendToClient( this.playerTurn, "9_server_o_rollresult" + messageToSend );
                }
            }

            //obsluga thiefmove
            //tylko serwer
            if ( this.serverManager != null && parts[3].contains( "thiefmove" ) ) {
                String[] messagesFromOut = out[0].split( "\n" );
                for ( int i = 0; i < messagesFromOut.length; i++ ) {
                    //deb usun
                    System.out.println( "polecenia g:" + messagesFromOut[i] );
                    this.serverManager.sendToAllClients( "9_server_g_" + messagesFromOut[i] );
                }
                String[] logMessages = out[1].split( "\n" );
                for ( int i = 0; i < logMessages.length; i++ ) {
                    this.serverManager.sendToAllClients( "9_server_l_" + logMessages[i] );
                }
            }

            //obsluga devknight
            //tylko serwer
            if ( this.serverManager != null && parts[3].contains( "devknight" ) ) {
                String[] gameCommands = out[0].split( "\n" );
                String[] logMessages = out[1].split( "\n" );
                //na serwerze sie wykonaly, wysylam do wszystkich
                for ( int i = 0; i < gameCommands.length; i++ ) {
                    this.serverManager.sendToAllClients( "9_server_g_" + gameCommands[i] );
                }
                for ( int i = 0; i < logMessages.length; i++ ) {
                    this.serverManager.sendToAllClients( "9_server_l_" + logMessages[i] );
                }
            }

            //obsluga devresources
            //tylko serwer
            if ( this.serverManager != null && parts[3].contains( "devroads" ) ) {
                String[] gameCommands = out[0].split( "\n" );
                String[] logMessages = out[1].split( "\n" );
                int subbmitingPlayerNumber = Integer.parseInt( parts[0] );
                //na serwerze sie wykonaly, wysylam do wszystkich
                //poza graczem od ktorego dostalem wiadomosc
                for ( int i = 0; i < gameCommands.length; i++ ) {
                    if ( i != subbmitingPlayerNumber ) {
                        this.serverManager.sendToAllClients( "9_server_g_" + gameCommands[i] );
                    }
                }
                for ( int i = 0; i < logMessages.length; i++ ) {
                    if ( i != subbmitingPlayerNumber ) {
                        this.serverManager.sendToAllClients( "9_server_l_" + logMessages[i] );
                    }
                }
            }

        } else if ( parts[2].contains( "n" ) ) {
            this.playerNumber = Integer.parseInt( parts[3] );
        } else if ( parts[2].contains( "l" ) ) {    //dopisz do loga
            this.logBox.append( parts[3] + "\n" );
        } else if ( parts[2].contains( "o" ) ) {
            //kazde inaczej
            if ( parts[3].contains( "setup" ) ) {
                int truefalse = Integer.parseInt( parts[3].substring( 5 ) );
                if ( truefalse == 1 ) {
                    this.setupPhase = true;
                } else {
                    this.setupPhase = false;
                }
            } else if ( parts[3].contains( "setplaced" ) ) {    //przestawiamy faze
                this.playerTurn += this.setupAdd;
                if ( this.playerTurn < 0 ) {
                    this.playerTurn = this.getMechanics().getPlayers().length - 1;
                }
                //this.playerTurn = (this.playerTurn) % this.getMechanics().getPlayers().length;
                this.setupTurns++;

                //w tym momencie odwraca sie kolejnosc
                if ( this.setupTurns == this.getMechanics().getPlayers().length ) {
                    this.playerTurn -= 1;
                    this.setupAdd = -1;
                } //jezeli sie nie odwrocila, to przejdz dalej do kolejnego gracza
                //(od poczatku, bo przekroczylo zakres)
                else {
                    this.playerTurn = (this.playerTurn) % this.getMechanics().getPlayers().length;
                }

                //jezeli wszystko wroiclo == wszyscy postawili osady
                //to koniec setupu i zaczynamy gre
                if ( this.setupTurns >= this.getMechanics().getPlayers().length * 2 ) {
                    //popraw ture gracza
                    this.playerTurn += 1;
                    this.playerTurn = this.playerTurn % this.getMechanics().getPlayers().length;
                    //koniec fazy setup
                    this.serverManager.sendToAllClients( "9_server_o_setup0" );
                    this.setupPhase = false;
                    //informacja o turze gracza
                    this.serverManager.sendToAllClients( "9_server_l_Tura gracza " + this.playerTurn );
                    this.parse( "9_server_l_Tura gracza " + this.playerTurn );
                    this.serverManager.sendToClient( this.playerTurn, "9_server_u_enablerollButton" );
                    this.serverManager.sendToClient( this.playerTurn, "9_server_u_enabledevCardViewButton" );
                } //jezeli nie koniec setupu, to wyslij wiadomosc do kolejnego gracza
                else {
                    //powiadomienie do graczy oraz...
                    this.serverManager.sendToAllClients( "9_server_l_Tura gracza " + this.playerTurn + ", ustawianie osady początkowej" );
                    this.parse( "9_server_l_Tura gracza " + this.playerTurn + ", ustawianie osady początkowej" );
                    //wyslanie do tego gracza informacji
                    this.serverManager.sendToClient( playerTurn, "9_server_u_enablebuildRoadButton" );
                    this.serverManager.sendToClient( playerTurn, "9_server_o_setup1" );
                }

                //wyslij do gracza
            } else if ( parts[3].contains( "rollresult" ) ) {   //wynik rzutu kostka
                //jezeli wypadl zlodziej, aktywuj
                if ( parts[3].contains( "7" ) ) {
                    //aktywacja fazy zlodzieja
                    this.placingThief = true;
                    this.guiParser.disableAll();
                    this.logBox.append( "Wybierz hex ze zlodziejem\n" );
                } else {
                    //aktywuj wszystkie przyciski, poza rzutem koscmi
                    this.guiParser.enableAll();
                    this.guiParser.getRollButton().setEnabled( false );
                }
            } else if ( parts[3].contains( "eot" ) ) {  //koniec tury gracza
                //deaktywuj all
                this.serverManager.sendToAllClients( "9_server_u_disableAll" );

                //policz pkt
                String[] out = this.gameParser.game.calculatePoints();
                String[] commands = out[0].split( "\n" );
                String[] logs = out[1].split( "\n" );

                //wyslij log
                for ( int i = 0; i < logs.length; i++ ) {
                    this.serverManager.sendToAllClients( "9_server_l_" + logs[i] );
                }

                //jezeli ktos wygral
                int[] points = new int[this.mechanics.getPlayers().length];
                for ( int i = 0; i < this.mechanics.getPlayers().length; i++ ) {
                    int playerIndex = Integer.parseInt( commands[i].substring( 7, 8 ) );
                    points[playerIndex] = Integer.parseInt( commands[i].substring( 9, 11 ) ) + Integer.parseInt( commands[i].substring( 12 ) );
                    if ( points[playerIndex] >= 10 ) {
                        //ktos wygral
                        //wszyscy disableAll
                        //i wyslij gratulacje
                        this.serverManager.sendToAllClients( "9_server_u_disableAll" );
                        this.serverManager.sendToAllClients( "9_server_l_Gracz " + Solver.getPlayerColorStringFromNumber( playerIndex ) + " wygrywa!\n"
                                                             + "" + commands[i].substring( 7, 8 ) + "pkt +" + commands[i].substring( 9, 11 ) + " kart zwyciestwa" );
                    }
                }

                //wyslij update punktow
                for ( int i = 0; i < commands.length; i++ ) {
                    //deb usun
                    System.out.println( "Punkty command " + commands[i] );
                    this.serverManager.sendToAllClients( "9_server_g_" + commands[i] );
                }

                //przesun ture gracza i wyslij enableRzut koscmi i carddev
                this.playerTurn++;
                this.playerTurn = this.playerTurn % this.mechanics.getPlayers().length;

                this.serverManager.sendToAllClients( "9_server_l_Tura gracza " + this.playerTurn );
                this.serverManager.sendToClient( this.playerTurn, "9_server_u_enablerollButton" );
                this.serverManager.sendToClient( this.playerTurn, "9_server_u_enabledevCardViewButton" );
                this.serverManager.sendToClient( this.playerTurn, "9_server_u_enabledevCardList" );
                this.serverManager.sendToClient( this.playerTurn, "9_server_u_enabledevCardButtonUse" );
            }
        }

        //po wszystkim przelicz wszystkie drogi itp
        this.guiParser.bvpm.reconsiderAll();
        this.guiParser.bvpm.repaint();
    }

    public void setGuiParser( GUIParser guiParser ) {
        this.guiParser = guiParser;
    }

    public void setLogBox( TextArea logBox ) {
        this.logBox = logBox;
    }

    public void setChatBox( TextArea chatBox ) {
        this.chatBox = chatBox;
    }

    public void setP2sp( PlayerToServerParser p2sp ) {
        this.p2sp = p2sp;
    }

    public void setGameParser( GameParser gameParser ) {
        this.gameParser = gameParser;
    }

    private void writeToBoxes( String[] mes ) { //TODO popraw dodawanie do czatu
        //this.logBox.append( mes[0] + "\n" );
        this.logBox.append( mes[1] + "\n" );
    }

    private void appendToChat( String mes ) {
        this.chatBox.append( mes + "\n" );
    }

    private void appendToLog( String mes ) {
        this.logBox.append( mes + "\n" );
    }

    public TextArea getChatBox() {
        return chatBox;
    }

    public GameParser getGameParser() {
        return gameParser;
    }

    public GUIParser getGuiParser() {
        return guiParser;
    }

    public TextArea getLogBox() {
        return logBox;
    }

    public GameMechanics getMechanics() {
        return mechanics;
    }

    public PlayerToServerParser getP2sp() {
        return p2sp;
    }

    public void setMechanics( GameMechanics mechanics ) {
        this.mechanics = mechanics;
    }

    public void setPlayerNumber( int playerNumber ) {
        this.playerNumber = playerNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setServerManager( ServerManager serverManager ) {
        this.serverManager = serverManager;
        this.mechanics = this.gameParser.game.mechanics;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    public void setSetupFase( boolean setupFase ) {
        this.setupPhase = setupFase;
    }

    public boolean isSetupFase() {
        return setupPhase;
    }

    public void setDevRoadsEnabled( boolean devRoadsEnabled ) {
        this.devRoadsEnabled = devRoadsEnabled;
    }

    public boolean isDevRoadsEnabled() {
        return devRoadsEnabled;
    }

    public void setPlayerTurn( int playerTurn ) {
        this.playerTurn = playerTurn;
    }

    public void setSetupAdd( int setupAdd ) {
        this.setupAdd = setupAdd;
    }

    public void setSetupFinal( int setupFinal ) {
        this.setupFinal = setupFinal;
    }

    public void setSetupTurns( int setupTurns ) {
        this.setupTurns = setupTurns;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int getSetupAdd() {
        return setupAdd;
    }

    public int getSetupFinal() {
        return setupFinal;
    }

    public int getSetupTurns() {
        return setupTurns;
    }

    public void setPlacingThief( boolean placingThief ) {
        this.placingThief = placingThief;
    }

    public boolean isPlacingThief() {
        return placingThief;
    }

    public boolean isSetupPhase() {
        return setupPhase;
    }

    public void setSetupPhase( boolean setupPhase ) {
        this.setupPhase = setupPhase;
    }

    public boolean isPlacingDevThief() {
        return placingDevThief;
    }

    public void setPlacingDevThief( boolean placingDevThief ) {
        this.placingDevThief = placingDevThief;
    }
}
