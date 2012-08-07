/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.gamescreen;

import flipbox.sackrace.game.GameMidlet;
import flipbox.sackrace.game.IGameScene;
import flipbox.sackrace.level.LevelGenerator;
import flipbox.sackrace.nongamescreen.MapScene;
import flipbox.sackrace.object.Player;
import flipbox.sackrace.object.PlayerData;
import flipbox.sackrace.staticvalue.StaticData;
import flipbox.sackrace.staticvalue.TypeList;
import flipbox.sackrace.ui.AnimatedSprite;
import flipbox.sackrace.ui.ButtonImageItem;
import flipbox.sackrace.ui.ImageItem;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Yulistiyan Wardhana
 */
public class GameKuburan implements IGameScene {

    private Player player;
    ImageItem backgroundImage;
    ButtonImageItem buttonSlide;
    //AnimatedSprite sprite;
    boolean start;
    private boolean hasInit;
    private static int BACKGROUND_POSA;
    private static int BACKGROUND_POSB;
    boolean soundOn;
    private GameMidlet midlet;
    private static long timeLapsed = 0;
    private boolean hasRenderBackground;

    public void setGameMidlet(GameMidlet midelet) {
        midlet = midelet;
    }

    private void initObstacles() {
        LevelGenerator.initConstraints(6, 10, 0, 0, 1, 2, 30, 50);
        LevelGenerator.initDistance(100, 300, 180, 200);
        LevelGenerator.initObjective(TypeList.DISTANCE, 1000);
        LevelGenerator.generateObstacles();
        LevelGenerator.generateCoins();
    }

    private void initPlayer() {
        try {
            player = PlayerData.getBagong();
            player.getSprite().setPosition(17, 50);
            player.getSprite().play();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initBackground() throws IOException {
        BACKGROUND_POSA = 0;
        BACKGROUND_POSB = backgroundImage.getWidth() - 40;
        backgroundImage = new ImageItem("/resource/grave/Sack Runner-03.jpg");
    }

    public void initResource() throws IOException {
        initPlayer();
        initBackground();
        initObstacles();
        hasInit = true;
        start = true;
    }

    boolean finish;
    
    public void render(Graphics g) {
        if (!hasInit) {
            return;
        }
        if (!hasRenderBackground) {
            renderBackground(3, g);
        }
        if (start) {
            //clear(g);
            finish = LevelGenerator.run(g);
            System.out.println(backgroundImage.getWidth());
            player.getSprite().update(timeLapsed);
            timeLapsed++;
            player.getSprite().paint(g);
            hasRenderBackground = false;
        }
        if(finish)
        {
            try {
                GameMidlet.gameCanvas.setGameScene(new MapScene());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pointerPressed(int x, int y) {
        System.out.println(x + "" + y + "");
    }

    public void pointerReleased(int x, int y) {
        System.out.println(x + "" + y + "");
    }

    public void pointerDragged(int x, int y) {
        System.out.println(x + "" + y + "");
    }

    public void clear(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, 240, 320);
        hasRenderBackground = false;
    }

    private void renderBackground(int fpr, Graphics g) {
        clear(g);
        g.drawImage(backgroundImage.getImage(), 0, BACKGROUND_POSA, Graphics.LEFT | Graphics.TOP);
        g.drawImage(backgroundImage.getImage(), 0, BACKGROUND_POSB, Graphics.LEFT | Graphics.TOP);
        System.out.println(BACKGROUND_POSA+" hahaha");
        BACKGROUND_POSA -= fpr;
        BACKGROUND_POSB -= fpr;
        hasRenderBackground = true;
    }
}
