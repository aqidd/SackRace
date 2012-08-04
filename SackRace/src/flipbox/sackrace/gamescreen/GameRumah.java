/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.gamescreen;

import flipbox.sackrace.game.GameMidlet;
import flipbox.sackrace.game.IGameScene;
import flipbox.sackrace.nongamescreen.StartMenuScene;
import flipbox.sackrace.object.Player;
import flipbox.sackrace.staticvalue.StaticData;
import flipbox.sackrace.ui.AnimatedSprite;
import flipbox.sackrace.ui.ButtonImageItem;
import flipbox.sackrace.ui.ImageItem;
import java.io.IOException;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
/**
 *
 * @author Yulistiyan Wardhana
 */
public class GameRumah implements IGameScene {

    private Player player;
    ImageItem backgroundImage, buttonCoin, buttonLife;
    ButtonImageItem buttonSlide;
    //AnimatedSprite sprite;
    boolean start;
    private boolean hasInit;
    boolean soundOn;
    private GameMidlet midlet;
    private static long timeLapsed = 0;
    private boolean hasRenderBackground;

    public void setGameMidlet(GameMidlet midelet) {
        midlet = midelet;
    }

    private void initPlayer() {
        try {
            player = new Player();
            Image bagong = StaticData.rotateImage(Image.createImage("/resource/chars/bagong_lompat.png"), 90);
            player.setSprite(new AnimatedSprite(bagong, 76, 55, 3));
            player.setJumpHeight(10);
            player.setName("Bagong");
            player.setBloodLevel(5);
            player.getSprite().setPosition(17, 150);
            player.getSprite().play();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initBackground() throws IOException {
        backgroundImage = new ImageItem("/resource/rumah/Sack Runner-01.jpg");
    }

    private void initButton() throws IOException {
        buttonSlide = new ButtonImageItem("/resource/button/slide.png", "/resource/button/slide.png");
        buttonCoin = new ImageItem("/resource/button/coin.png");
        buttonLife = new ImageItem("/resource/button/heart.png");
        //buttonSlide.setX(10).setY(10);
        buttonCoin.setX(230).setY(10 + 230);
//        buttonCoin.setX(Graphics.TOP).setY(Graphics.RIGHT);
//        buttonLife.setX(Graphics.TOP).setY(Graphics.LEFT);
    }

    public void initResource() throws IOException {
        initPlayer();
        initBackground();
        initButton();
        hasInit = true;
        start = true;
    }

    public void render(Graphics g) {
        if (!hasInit) {
            return;
        }
        if (!hasRenderBackground) {
            clear(g);
            g.drawImage(backgroundImage.getImage(), 0, 0, Graphics.LEFT | Graphics.TOP);
            g.drawImage(backgroundImage.getImage(), 0, backgroundImage.getWidth() - 40, Graphics.LEFT | Graphics.TOP);
            g.drawImage(buttonCoin.getImage(), buttonCoin.getX(), buttonCoin.getY(), Graphics.RIGHT | Graphics.TOP);
            
            hasRenderBackground = true;
        }
        if (start) {
            //clear(g);
            //System.out.println(backgroundImage.getWidth());
            //System.out.println(player.getSprite().getFrame() + "Frame " + player.getSprite().getFrameSequenceLength());

            Font font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
            g.setFont(font);
            g.setColor(42339);
            
            g.drawString("nilai", buttonCoin.getX(), buttonCoin.getY(), Graphics.RIGHT | Graphics.TOP);
            if (buttonSlide.isOnPressed()) {
                if (player.getSprite().getFrame() == player.getSprite().getFrameSequenceLength() - 1) {
                    System.out.println(player.getSprite().getFrame());
                    player.getSprite().setFrame(player.getSprite().getFrameSequenceLength() - 1);
                } else {
                    player.getSprite().update(timeLapsed);
                    timeLapsed++;
                }
            } else {
                player.getSprite().update(timeLapsed);
                timeLapsed++;
            }

            player.getSprite().paint(g);
            if (buttonSlide.isVisible()) {
                g.drawImage(buttonSlide.getImage(), buttonSlide.getX(), buttonSlide.getY(), Graphics.TOP | Graphics.LEFT);
            }

            hasRenderBackground = false;
        }
    }

    public void pointerPressed(int x, int y) {
        if (buttonSlide.isCanClick()
                && x >= buttonSlide.getX() && x <= (buttonSlide.getX() + buttonSlide.getWidth())
                && y >= buttonSlide.getY() && y <= (buttonSlide.getY() + buttonSlide.getHeight())) {
            buttonSlide.setOnPressed(true);
            try {
                setNgesot();//GameMidlet.gameCanvas.setGameScene(new StartMenuScene());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pointerReleased(int x, int y) {
        if (buttonSlide.isOnPressed()
                && x >= buttonSlide.getX() && x <= (buttonSlide.getX() + buttonSlide.getWidth())
                && y >= buttonSlide.getY() && y <= (buttonSlide.getY() + buttonSlide.getHeight())) {
            //resetButton();
            try {
                setNormal();
                buttonSlide.setOnPressed(false);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void setNgesot() throws Exception {
        Image bagong = StaticData.rotateImage(Image.createImage("/resource/chars/bagong_ngesot.png"), 90);
        player.setSprite(new AnimatedSprite(bagong, 76, bagong.getHeight() / 4, 4));
        player.getSprite().setPosition(17, 150);
    }

    private void setNormal() throws Exception {
        Image bagong = StaticData.rotateImage(Image.createImage("/resource/chars/bagong_lompat.png"), 90);
        player.setSprite(new AnimatedSprite(bagong, 76, 55, 3));
        player.getSprite().setPosition(17, 150);
        //resetButton();
    }

    private void resetButton() {
        buttonSlide.setOnPressed(false);
    }

    public void pointerDragged(int x, int y) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, 240, 320);
        hasRenderBackground = false;
    }
}
