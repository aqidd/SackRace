/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.nongamescreen;

import flipbox.sackrace.data.GameDataHelper;
import flipbox.sackrace.game.GameMidlet;
import flipbox.sackrace.game.IGameScene;
import flipbox.sackrace.staticvalue.StaticData;
import flipbox.sackrace.staticvalue.TypeList;
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
    boolean gpos, bpos, ppos;
    boolean start;
    boolean hasInit;
    boolean soundOn;
    public GameMidlet midlet;

    public void initResource() throws IOException {

        soundOn = GameMidlet.isSoundOn();

        backgroundImage = new ImageItem("/resource/background_menu.jpg");
        buttonLeftArrow = new ButtonImageItem("/resource/chars/arrow_right.png", "/resource/chars/arrow_right.png");
        buttonRightArrow = new ButtonImageItem("/resource/chars/arrow_left.png", "/resource/chars/arrow_left.png");
        buttonBack = new ButtonImageItem("/resource/nav/back.png", "/resource/nav/back_pressed.png");
        buttonStory = new ButtonImageItem("/resource/button_start.png", "/resource/button_start_pressed.png");

        gareng = new ImageItem("/resource/chars/gareng.png");
        bagong = new ImageItem("/resource/chars/bagong.png");
        petruk = new ImageItem("/resource/chars/petruk.png");
        nameGareng = new ImageItem("/resource/chars/nametag_gareng.png");
        namePetruk = new ImageItem("/resource/chars/nametag_petruk.png");
        nameBagong = new ImageItem("/resource/chars/nametag_bagong.png");

        try {
            backgroundImage.setImage(StaticData.rotateImage(backgroundImage.getImage(), 90));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        gareng.setX(95).setY(115);
        gpos = true;
        bpos = ppos = false;
        bagong.setX(95).setY(115);
        petruk.setX(95).setY(115);

        buttonStory.setX(30).setY(105);
        buttonLeftArrow.setX(90).setY(220);
        buttonRightArrow.setX(90).setY(60);
        buttonBack.setX(190).setY(10);
        hasInit = true;
        start = true;
    }

    public void render(Graphics g) {
        if (!hasInit) {
            return;
        }
        if (start) {
//            System.out.println(" g : " + gareng.isVisible() + " b : " + bagong.isVisible() + " p : " + petruk.isVisible());
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
            if (buttonStory.isVisible()) {
                g.drawImage(buttonStory.getImage(), buttonStory.getX(), buttonStory.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (gareng.isVisible() && gpos) {
                g.drawImage(gareng.getImage(), gareng.getX(), gareng.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (petruk.isVisible() && ppos) {
                g.drawImage(petruk.getImage(), petruk.getX(), petruk.getY(), Graphics.TOP | Graphics.LEFT);
            }
            if (bagong.isVisible() && bpos) {
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
        if (buttonLeftArrow.isCanClick()
                && x >= buttonLeftArrow.getX() && x <= (buttonLeftArrow.getX() + buttonLeftArrow.getWidth())
                && y >= buttonLeftArrow.getY() && y <= (buttonLeftArrow.getY() + buttonLeftArrow.getHeight())) {
            buttonLeftArrow.setOnPressed(true);
        }
        if (buttonRightArrow.isCanClick()
                && x >= buttonRightArrow.getX() && x <= (buttonRightArrow.getX() + buttonRightArrow.getWidth())
                && y >= buttonRightArrow.getY() && y <= (buttonRightArrow.getY() + buttonRightArrow.getHeight())) {
            buttonRightArrow.setOnPressed(true);
        }
        if (buttonStory.isCanClick()
                && x >= buttonStory.getX() && x <= (buttonStory.getX() + buttonStory.getWidth())
                && y >= buttonStory.getY() && y <= (buttonStory.getY() + buttonStory.getHeight())) {
            buttonStory.setOnPressed(true);
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
        if (buttonStory.isOnPressed()
                && x >= buttonStory.getX() && x <= (buttonStory.getX() + buttonStory.getWidth())
                && y >= buttonStory.getY() && y <= (buttonStory.getY() + buttonStory.getHeight())) {
            resetButton();
            try {
                if (gpos) {
                    GameDataHelper.writeHighScore(GameDataHelper.PILIHAN_PLAYER, TypeList.GARENG);
                } else if (ppos) {
                    GameDataHelper.writeHighScore(GameDataHelper.PILIHAN_PLAYER, TypeList.PETRUK);
                } else if (bpos) {
                    GameDataHelper.writeHighScore(GameDataHelper.PILIHAN_PLAYER, TypeList.BAGONG);
                }
                
                GameMidlet.gameCanvas.setGameScene(new MapScene());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (buttonLeftArrow.isOnPressed()
                && x >= buttonLeftArrow.getX() && x <= (buttonLeftArrow.getX() + buttonLeftArrow.getWidth())
                && y >= buttonLeftArrow.getY() && y <= (buttonLeftArrow.getY() + buttonLeftArrow.getHeight())) {
            resetButton();
            if (gpos) {
                ppos = true;
                bpos = gpos = false;
            } else if (ppos) {
                bpos = true;
                gpos = ppos = false;
            } else if (bpos) {
                gpos = true;
                bpos = ppos = false;
            }
        }
        if (buttonRightArrow.isOnPressed()
                && x >= buttonRightArrow.getX() && x <= (buttonRightArrow.getX() + buttonRightArrow.getWidth())
                && y >= buttonRightArrow.getY() && y <= (buttonRightArrow.getY() + buttonRightArrow.getHeight())) {
            resetButton();
            if (gpos) {
                bpos = true;
                ppos = gpos = false;
            } else if (ppos) {
                gpos = true;
                bpos = ppos = false;
            } else if (bpos) {
                ppos = true;
                bpos = gpos = false;
            }
        }
    }

    private void resetButton() {
        buttonBack.setOnPressed(false);
        buttonLeftArrow.setOnPressed(false);
        buttonRightArrow.setOnPressed(false);
        buttonStory.setOnPressed(false);
    }

    public void pointerDragged(int x, int y) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setGameMidlet(GameMidlet midelet) {
        this.midlet = midelet;
    }

    public void clear(Graphics g) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
