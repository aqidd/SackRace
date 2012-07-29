/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.game;

import flipbox.sackrace.level.LevelConstraint;
import flipbox.sackrace.level.LevelGenerator;
import flipbox.sackrace.level.LevelObjective;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author QED
 */
public class Story implements IGameScene {
    //current level
    int level;
    //coins collected
    int coins;
    //level contraints
    LevelConstraint constraint;
    //level objects generator
    LevelGenerator generator;
    //goals
    LevelObjective objective;
    
    //KAGAK TAU LAH INI MAO DIISI APAANS XD

    public void setGameMidlet(GameMidlet midelet) {
        throw new UnsupportedOperationException("Not supported yet.");
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
