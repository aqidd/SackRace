/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.game;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Yulistiyan Wardhana
 */
public class GameCoba implements IGameScene{

    boolean start;
    boolean hasInit;
    boolean soundOn; 
    private GameMidlet midlet;
    
    public void setGameMidlet(GameMidlet midelet) {
        midlet = midelet;
    }

    public void initResource() throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void render(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void pointerPressed(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void pointerReleased(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void pointerDragged(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
