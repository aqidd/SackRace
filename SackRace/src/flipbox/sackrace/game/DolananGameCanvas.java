/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.game;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.media.Player;

/**
 *
 * @author agung
 */
public class DolananGameCanvas extends GameCanvas implements Runnable{
    public static final int mDelay = 30;
    private volatile Thread thread;
    private IGameScene currentGameScene;
    
    public DolananGameCanvas(IGameScene gameScene){
        super(true);
        try{
            this.currentGameScene = gameScene;
            this.setFullScreenMode(true);
            this.init();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    void init() throws IOException{
        if(currentGameScene!=null){
            currentGameScene.initResource();
        }
    }
    
    public void run() {
        Graphics g = getGraphics();
        
        while(thread == Thread.currentThread()){
            synchronized(this.currentGameScene){
                this.render(g);
                this.flushGraphics();
                try{
                    Thread.sleep(mDelay);
                }catch(Exception ie){

                }
            }
            
        }
    }
    
    public void start(){
//        thread = new Thread(this);
//        t.start();
    }
    
    public void stop(){
        
    }
    
    public void render(Graphics g){
        if(currentGameScene!=null){
            currentGameScene.render(g);
        }
    }
    
    protected void pointerPressed(int x, int y) {
        super.pointerPressed(x, y);
        currentGameScene.pointerPressed(x, y);
    }

    protected void pointerReleased(int x, int y) {
        super.pointerReleased(x, y);
        currentGameScene.pointerReleased(x, y);
    }

    protected void pointerDragged(int x, int y) {
        super.pointerDragged(x, y);
        currentGameScene.pointerDragged(x, y);
    }
    
    public void setGameScene(IGameScene gameScene) throws IOException {
        if(this.currentGameScene != null) {
            synchronized(this.currentGameScene) {
                this.currentGameScene = gameScene;
                this.currentGameScene.setGameMidlet(midlet);
                this.currentGameScene.initResource();
            }
        }
        else
        {
            this.currentGameScene = gameScene;
        }
    }

    protected void showNotify() {
        thread = new Thread(this);
        thread.start();
    }

    protected void hideNotify() {
        thread = null;
    }
    
    private GameMidlet midlet;
    public void setGameMidlet(GameMidlet midlet){
        this.midlet = midlet;
    }
    
}
