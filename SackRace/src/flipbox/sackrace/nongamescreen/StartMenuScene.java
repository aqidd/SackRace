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
import javax.microedition.lcdui.Image;

/**
 *
 * @author agung
 */
public class StartMenuScene implements IGameScene {

    ButtonImageItem buttonStart, buttonAbout, buttonHelp, buttonSound, buttonExit;
    ImageItem backgroundImage;
    boolean start;
    boolean hasInit;
    boolean soundOn;
    public GameMidlet midlet;

    public void initResource() throws IOException {

        soundOn = GameMidlet.isSoundOn();

        backgroundImage = new ImageItem("/resource/background_menu.jpg");
        buttonStart = new ButtonImageItem("/resource/button_start.png", "/resource/button_start_pressed.png");
        buttonAbout = new ButtonImageItem("/resource/button_about.png", "/resource/button_about_pressed.png");
        buttonHelp = new ButtonImageItem("/resource/button_help.png", "/resource/button_help_pressed.png");
        buttonExit = new ButtonImageItem("/resource/nav/button_exit.png", "/resource/nav/button_exit_pressed.png");

        if (soundOn) {
            buttonSound = new ButtonImageItem("/resource/button_sound_on.png", null);
        } else {
            buttonSound = new ButtonImageItem("/resource/button_sound_off.png", null);
        }

        buttonStart.setX(90).setY(105);
        buttonAbout.setX(55).setY(105);
        buttonHelp.setX(10).setY(270);
        buttonSound.setX(185).setY(250);
        buttonExit.setX(190).setY(10);
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

            if (buttonStart.isVisible()) {
                g.drawImage(buttonStart.getImage(), buttonStart.getX(), buttonStart.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (buttonAbout.isVisible()) {
                g.drawImage(buttonAbout.getImage(), buttonAbout.getX(), buttonAbout.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (buttonHelp.isVisible()) {
                g.drawImage(buttonHelp.getImage(), buttonHelp.getX(), buttonHelp.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (buttonSound.isVisible()) {
                g.drawImage(buttonSound.getImage(), buttonSound.getX(), buttonSound.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (buttonExit.isVisible()) {
                g.drawImage(buttonExit.getImage(), buttonExit.getX(), buttonExit.getY(), Graphics.TOP | Graphics.LEFT);
            }
        }
    }

    public void pointerPressed(int x, int y) {
        if (buttonStart.isCanClick()
                && x >= buttonStart.getX() && x <= (buttonStart.getX() + buttonStart.getWidth())
                && y >= buttonStart.getY() && y <= (buttonStart.getY() + buttonStart.getHeight())) {
            //start game
            System.out.println("START Pressed");
            buttonStart.setOnPressed(true);
        }
        if (buttonAbout.isCanClick()
                && x >= buttonAbout.getX() && x <= (buttonAbout.getX() + buttonAbout.getWidth())
                && y >= buttonAbout.getY() && y <= (buttonAbout.getY() + buttonAbout.getHeight())) {
            //about game
            buttonAbout.setOnPressed(true);
        }
        if (buttonHelp.isCanClick()
                && x >= buttonHelp.getX() && x <= (buttonHelp.getX() + buttonHelp.getWidth())
                && y >= buttonHelp.getY() && y <= (buttonHelp.getY() + buttonHelp.getHeight())) {
            //help game
            buttonHelp.setOnPressed(true);
        }
        if (buttonSound.isCanClick()
                && x >= buttonSound.getX() && x <= (buttonSound.getX() + buttonSound.getWidth())
                && y >= buttonSound.getY() && y <= (buttonSound.getY() + buttonSound.getHeight())) {
            //exit game
            buttonSound.setOnPressed(true);
        }
        if (buttonExit.isCanClick()
                && x >= buttonExit.getX() && x <= (buttonExit.getX() + buttonExit.getWidth())
                && y >= buttonExit.getY() && y <= (buttonExit.getY() + buttonExit.getHeight())) {
            //exit game
            buttonExit.setOnPressed(true);
        }
    }

    public void pointerReleased(int x, int y) {
        if (buttonStart.isOnPressed()
                && x >= buttonStart.getX() && x <= (buttonStart.getX() + buttonStart.getWidth())
                && y >= buttonStart.getY() && y <= (buttonStart.getY() + buttonStart.getHeight())) {
            //start game
            resetButton();
            buttonStart_Click();
        }
        if (buttonAbout.isOnPressed()
                && x >= buttonAbout.getX() && x <= (buttonAbout.getX() + buttonAbout.getWidth())
                && y >= buttonAbout.getY() && y <= (buttonAbout.getY() + buttonAbout.getHeight())) {
            //about game
            resetButton();
            buttonAbout_Click();
        }
        if (buttonHelp.isCanClick()
                && x >= buttonHelp.getX() && x <= (buttonHelp.getX() + buttonHelp.getWidth())
                && y >= buttonHelp.getY() && y <= (buttonHelp.getY() + buttonHelp.getHeight())) {
            //help game
            resetButton();
            buttonHelp_Click();
        }
        if (buttonSound.isCanClick()
                && x >= buttonSound.getX() && x <= (buttonSound.getX() + buttonSound.getWidth())
                && y >= buttonSound.getY() && y <= (buttonSound.getY() + buttonSound.getHeight())) {
            //sound game
            resetButton();
            buttonSound_Click();
        }

        if (buttonExit.isCanClick()
                && x >= buttonExit.getX() && x <= (buttonExit.getX() + buttonExit.getWidth())
                && y >= buttonExit.getY() && y <= (buttonExit.getY() + buttonExit.getHeight())) {
            //sound game
            resetButton();
            buttonExit_Click();
        }

    }

    public void pointerDragged(int x, int y) {
        //do nothing
    }

    public void clear(Graphics g) {
    }

    private void buttonStart_Click() {
        try {
            GameMidlet.gameCanvas.setGameScene(new MapScene());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonAbout_Click() {
        try {
            System.out.println("About clicked");
            GameMidlet.gameCanvas.setGameScene(new HelpAndAbout(false));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonHelp_Click() {
        try {
            System.out.println("Help clicked");
            GameMidlet.gameCanvas.setGameScene(new HelpAndAbout(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonSound_Click() {
        try {
            if (soundOn) {
                buttonSound.setImage(Image.createImage("/resource/button_sound_off.png"));
                soundOn = false;
            } else {
                buttonSound.setImage(Image.createImage("/resource/button_sound_on.png"));
                soundOn = true;
            }
            GameMidlet.setSoundOn(soundOn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buttonExit_Click() {
        if (midlet != null) {
            midlet.destroyApp(false);
            midlet.notifyDestroyed();
        }
    }

    private void resetButton() {
        buttonStart.setOnPressed(false);
        buttonAbout.setOnPressed(false);
        buttonSound.setOnPressed(false);
        buttonHelp.setOnPressed(false);
    }

    public void setGameMidlet(GameMidlet midlet) {
        this.midlet = midlet;
    }
}
