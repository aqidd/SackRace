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
