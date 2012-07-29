/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.nongamescreen;

import flipbox.sackrace.game.GameMidlet;
import flipbox.sackrace.game.IGameScene;
import flipbox.sackrace.ui.ImageItem;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author agung
 */
public class SplashScreenScene implements IGameScene{
    private boolean hasInit, start;
    private ImageItem background;
    private GameMidlet midlet;
    public SplashScreenScene(GameMidlet midlet){
        this.midlet = midlet;
    }
    public void initResource() throws IOException {
        background = new ImageItem("/resource/splash.jpg");
        
        hasInit = true;
        start = true;
        
        new Thread(new Runnable() {

            public void run() {
                StartMenuScene startMenuScene = new StartMenuScene();
                startMenuScene.setGameMidlet(midlet);
                try{
                    Thread.sleep(1000);
                    GameMidlet.playBackgroundMusic("/resource/music/opening_thenakedgun.mid");
                    Thread.sleep(1000);
                    GameMidlet.gameCanvas.setGameScene(startMenuScene);
                }catch(Exception e){}
                
                
            }
        }).start();
    }

    public void render(Graphics g) {
        
        if(!hasInit){
            g.setColor(255, 255, 255);
            g.fillRect(0, 0, 240, 320);
            return;
        }
        if(start){
            g.drawImage(background.getImage(), (240 - background.getWidth())/2, (320 - background.getHeight())/2, Graphics.TOP|Graphics.LEFT);
        }
    }

    public void pointerPressed(int x, int y) {
        
    }

    public void pointerReleased(int x, int y) {
        
    }

    public void pointerDragged(int x, int y) {
        
    }

    public void clear(Graphics g) {
        
    }
    public void setGameMidlet(GameMidlet midlet){
        this.midlet = midlet;
    }
}
