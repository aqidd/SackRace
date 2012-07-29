/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.nongamescreen;

import flipbox.sackrace.game.GameMidlet;
import flipbox.sackrace.game.IGameScene;
import flipbox.sackrace.ui.ButtonImageItem;
import flipbox.sackrace.ui.ImageItem;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author QED
 */
public class MapScene implements IGameScene {

    ButtonImageItem buttonHome, buttonGrave, buttonStage, buttonBack;
    ImageItem backgroundImage;
    boolean start;
    boolean hasInit;
    boolean soundOn;
    public GameMidlet midlet;

    public void initResource() throws IOException {
        soundOn = GameMidlet.isSoundOn();

        backgroundImage = new ImageItem("/resource/map/bg.jpg");
        buttonHome = new ButtonImageItem("/resource/map/home.png", "/resource/map/home.png");
        buttonGrave = new ButtonImageItem("/resource/map/grave.png", "/resource/map/grave.png");
        buttonStage = new ButtonImageItem("/resource/map/stage.png", "/resource/map/stage.png");
        buttonBack = new ButtonImageItem("/resource/nav/back.png", "/resource/nav/back_pressed.png");

        buttonHome.setX(160).setY(60);
        buttonGrave.setX(100).setY(130);
        buttonStage.setX(70).setY(230);
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
            if (buttonHome.isVisible()) {
                g.drawImage(buttonHome.getImage(), buttonHome.getX(), buttonHome.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (buttonGrave.isVisible()) {
                g.drawImage(buttonGrave.getImage(), buttonGrave.getX(), buttonGrave.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (buttonStage.isVisible()) {
                g.drawImage(buttonStage.getImage(), buttonStage.getX(), buttonStage.getY(), Graphics.TOP | Graphics.LEFT);
            }
        }
    }

    public void setGameMidlet(GameMidlet midelet) {
        this.midlet = midlet;
    }

    public void pointerPressed(int x, int y) {
        if (buttonBack.isCanClick()
                && x >= buttonBack.getX() && x <= (buttonBack.getX() + buttonBack.getWidth())
                && y >= buttonBack.getY() && y <= (buttonBack.getY() + buttonBack.getHeight())) {
            buttonBack.setOnPressed(true);
        }
        if (buttonHome.isCanClick()
                && x >= buttonHome.getX() && x <= (buttonHome.getX() + buttonHome.getWidth())
                && y >= buttonHome.getY() && y <= (buttonHome.getY() + buttonHome.getHeight())) {
            buttonHome.setOnPressed(true);
        }
        if (buttonGrave.isCanClick()
                && x >= buttonGrave.getX() && x <= (buttonGrave.getX() + buttonGrave.getWidth())
                && y >= buttonGrave.getY() && y <= (buttonGrave.getY() + buttonGrave.getHeight())) {
            buttonGrave.setOnPressed(true);
        }
        if (buttonStage.isCanClick()
                && x >= buttonStage.getX() && x <= (buttonStage.getX() + buttonStage.getWidth())
                && y >= buttonStage.getY() && y <= (buttonStage.getY() + buttonStage.getHeight())) {
            buttonStage.setOnPressed(true);
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
        if (buttonHome.isOnPressed()
                && x >= buttonHome.getX() && x <= (buttonHome.getX() + buttonHome.getWidth())
                && y >= buttonHome.getY() && y <= (buttonHome.getY() + buttonHome.getHeight())) {
            resetButton();
        }
        if (buttonGrave.isOnPressed()
                && x >= buttonGrave.getX() && x <= (buttonGrave.getX() + buttonGrave.getWidth())
                && y >= buttonGrave.getY() && y <= (buttonGrave.getY() + buttonGrave.getHeight())) {
            resetButton();
        }
        if (buttonStage.isOnPressed()
                && x >= buttonStage.getX() && x <= (buttonStage.getX() + buttonStage.getWidth())
                && y >= buttonStage.getY() && y <= (buttonStage.getY() + buttonStage.getHeight())) {
            resetButton();
        }
    }

    private void resetButton() {
        buttonHome.setOnPressed(false);
        buttonGrave.setOnPressed(false);
        buttonStage.setOnPressed(false);
        buttonBack.setOnPressed(false);
    }

    public void pointerDragged(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
