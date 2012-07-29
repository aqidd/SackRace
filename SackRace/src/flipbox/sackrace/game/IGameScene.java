/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.game;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;


/**
 *
 * @author agung
 */
public interface IGameScene {
    GameCanvas gameCanvas = null;
    
    void setGameMidlet(GameMidlet midelet);
    void initResource() throws IOException;
    void render(Graphics g);
    void pointerPressed(int x, int y);
    void pointerReleased(int x, int y);
    void pointerDragged(int x, int y);
    void clear(Graphics g);
    
   
}
