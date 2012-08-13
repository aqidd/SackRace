/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.nongamescreen;

import flipbox.sackrace.game.GameMidlet;
import flipbox.sackrace.game.IGameScene;
import flipbox.sackrace.staticvalue.StaticData;
import flipbox.sackrace.ui.ButtonImageItem;
import flipbox.sackrace.ui.ImageItem;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author QED
 */
public class ChooseCharacterScene implements IGameScene {

    ButtonImageItem buttonLeftArrow, buttonRightArrow, buttonBack, buttonStory, buttonInfinite;
    ImageItem backgroundImage, gareng, petruk, bagong, nameGareng, namePetruk, nameBagong;
    boolean start;
    boolean hasInit;
    boolean soundOn;
    public GameMidlet midlet;

    public void initResource() throws IOException {

        soundOn = GameMidlet.isSoundOn();

        backgroundImage = new ImageItem("/resource/background_menu.jpg");
        buttonLeftArrow = new ButtonImageItem("/resource/nav/button_exit.png", "/resource/nav/button_exit_pressed.png");
        buttonRightArrow = new ButtonImageItem("/resource/nav/button_exit.png", "/resource/nav/button_exit_pressed.png");
        buttonBack = new ButtonImageItem("/resource/nav/back.png", "/resource/nav/back_pressed.png");
        buttonStory = new ButtonImageItem("/resource/button_start.png", "/resource/button_start_pressed.png");
        
        gareng = new ImageItem("/resource/chars/gareng.png");
        bagong = new ImageItem("/resource/chars/bagong.png");
        petruk = new ImageItem("/resource/chars/petruk.png");
        nameGareng = new ImageItem("/resource/chars/nametag_gareng.png");
        namePetruk = new ImageItem("/resource/chars/nametag_petruk.png");
        nameBagong = new ImageItem("/resource/chars/nametag_bagong.png");
        
        try {
            backgroundImage.setImage(StaticData.rotateImage(backgroundImage.getImage(), 270));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        gareng.setX(90).setY(105);
        gareng.setVisible(true);
        bagong.setX(90).setY(105);
        bagong.setVisible(false);
        petruk.setX(90).setY(105);
        bagong.setVisible(false);
        
        buttonStory.setX(40).setY(105);
        buttonLeftArrow.setX(185).setY(250);
        buttonRightArrow.setX(190).setY(10);
        buttonBack.setX(190).setY(10);
        hasInit = true;
        start = true;
    }

    public void render(Graphics g) {
        if (!hasInit) {
            return;
        }
        if (start) {
            //drawing background
            g.drawImage(backgroundImage.getImage(), 0, 0, Graphics.LEFT | Graphics.TOP);

            if (buttonBack.isVisible()) {
                g.drawImage(buttonBack.getImage(), buttonBack.getX(), buttonBack.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (buttonLeftArrow.isVisible()) {
                g.drawImage(buttonLeftArrow.getImage(), buttonLeftArrow.getX(), buttonLeftArrow.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (buttonRightArrow.isVisible()) {
                g.drawImage(buttonRightArrow.getImage(), buttonRightArrow.getX(), buttonRightArrow.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if(buttonStory.isVisible()){
                g.drawImage(buttonStory.getImage(), buttonStory.getX(), buttonStory.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if(gareng.isVisible()){
                g.drawImage(gareng.getImage(), gareng.getX(), gareng.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if(petruk.isVisible()){
                g.drawImage(petruk.getImage(), petruk.getX(), petruk.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if(bagong.isVisible()){
                g.drawImage(bagong.getImage(), bagong.getX(), bagong.getY(), Graphics.TOP | Graphics.LEFT);
            }
        }
    }

    public void pointerPressed(int x, int y) {
        if (buttonBack.isCanClick()
                && x >= buttonBack.getX() && x <= (buttonBack.getX() + buttonBack.getWidth())
                && y >= buttonBack.getY() && y <= (buttonBack.getY() + buttonBack.getHeight())) {
            buttonBack.setOnPressed(true);
        }
    }

    public void pointerReleased(int x, int y) {
        if (buttonBack.isOnPressed()
                && x >= buttonBack.getX() && x <= (buttonBack.getX() + buttonBack.getWidth())
                && y >= buttonBack.getY() && y <= (buttonBack.getY() + buttonBack.getHeight())) {
            resetButton();
            try {
                GameMidlet.gameCanvas.setGameScene(new StartMenuScene());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void resetButton() {
        buttonBack.setOnPressed(false);
    }

    public void pointerDragged(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setGameMidlet(GameMidlet midelet) {
        this.midlet = midelet;
    }

    public void clear(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
