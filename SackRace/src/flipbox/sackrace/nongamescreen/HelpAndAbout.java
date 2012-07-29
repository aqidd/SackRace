/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.nongamescreen;

import flipbox.sackrace.game.GameMidlet;
import flipbox.sackrace.game.IGameScene;
import flipbox.sackrace.ui.ButtonImageItem;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author agung
 */
public class HelpAndAbout implements IGameScene {
    private ButtonImageItem buttonBack;
    private Image background;
    private boolean hasInit;
    private boolean isHelp;
    public HelpAndAbout(boolean isHelp){
        this.isHelp = isHelp;
    }
    
    public void initResource() throws IOException {
        
        buttonBack = new ButtonImageItem("/resource/nav/button_exit.png", "/resource/nav/button_exit_pressed.png");
        if(!isHelp){
            buttonBack.setX(192).setY(45);
            background = Image.createImage("/resource/about.jpg");
            System.out.print("about lho***********");
        }
        else{
            buttonBack.setX(192).setY(45);
            background = Image.createImage("/resource/help.jpg");
            System.out.print("help lho***********");
        }
        hasInit = true;
    }

    public void render(Graphics g) {
        if(!hasInit)
            return;
        g.drawImage(background, 0, 0, Graphics.TOP|Graphics.LEFT);
        g.drawImage(buttonBack.getImage(), buttonBack.getX(), buttonBack.getY(), Graphics.TOP|Graphics.LEFT);
    }

    public void pointerPressed(int x, int y) {
        if(buttonBack.isCanClick() &&
                x >= buttonBack.getX() && x <= buttonBack.getX()+buttonBack.getWidth() &&
                y >= buttonBack.getY() && y <= buttonBack.getY()+buttonBack.getHeight()){
            buttonBack.setOnPressed(true);
        }
    }

    public void pointerReleased(int x, int y) {
        if(buttonBack.isOnPressed() &&
                x >= buttonBack.getX() && x <= buttonBack.getX()+buttonBack.getWidth() &&
                y >= buttonBack.getY() && y <= buttonBack.getY()+buttonBack.getHeight()){
            buttonBack.setOnPressed(false);
            try{
                GameMidlet.gameCanvas.setGameScene(new StartMenuScene());
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public void pointerDragged(int x, int y) {
        
    }

    public void clear(Graphics g) {
        
    }
    private GameMidlet midlet;
    public void setGameMidlet(GameMidlet midlet){
        this.midlet = midlet;
    }
}
