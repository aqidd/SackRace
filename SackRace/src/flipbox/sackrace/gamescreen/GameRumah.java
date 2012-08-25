/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.gamescreen;

import flipbox.sackrace.data.GameDataHelper;
import flipbox.sackrace.game.GameMidlet;
import flipbox.sackrace.game.IGameScene;
import flipbox.sackrace.level.LevelGenerator;
import flipbox.sackrace.nongamescreen.MapScene;
import flipbox.sackrace.nongamescreen.StartMenuScene;
import flipbox.sackrace.object.Player;
import flipbox.sackrace.object.PlayerData;
import flipbox.sackrace.staticvalue.StaticData;
import flipbox.sackrace.staticvalue.TypeList;
import flipbox.sackrace.ui.AnimatedSprite;
import flipbox.sackrace.ui.ButtonImageItem;
import flipbox.sackrace.ui.ImageItem;
import java.io.IOException;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author Yulistiyan Wardhana
 */
public class GameRumah implements IGameScene {

    private Player player;
    ImageItem backgroundImage, awan, buttonCoin, buttonLife1, buttonLife2, buttonLife3;
    ButtonImageItem buttonSlide, buttonJump;
    //AnimatedSprite sprite;
    boolean start;
    private boolean hasInit;
    boolean soundOn;
    boolean finish;
    private GameMidlet midlet;
    private static long timeLapsed = 0;
    private boolean hasRenderBackground;
    private static int BACKGROUND_POSA;
    private static int BACKGROUND_POSB;
    private static int BACKGROUND_POSC;
    private static int BACKGROUND_POSAWAN_A;
    private static int BACKGROUND_POSAWAN_B;
    private static int BACKGROUND_POSAWAN_C;

    public void setGameMidlet(GameMidlet midelet) {
        midlet = midelet;
        System.out.println("selesai set game midlet");
    }

    public void initResource() throws IOException {
        System.out.println("masuk init resource");
        initPlayer();
        initBackground();
        initButton();
        initLevel();
        hasInit = true;
        start = true;
        System.out.println("selesai init resource");
    }

    public void render(Graphics g) {
        //Tidak bisa dipanggil langsung apabila belum diinisialisasi
        if (!hasInit) {
            return;
        }
        //Merender background jika belum dilakukan pada perenderan sekarang
        if (!hasRenderBackground) {
            renderBackground(3, g);
            renderAwan(1, g);
            g.drawImage(buttonCoin.getImage(), buttonCoin.getX(),
                    buttonCoin.getY(), Graphics.RIGHT | Graphics.TOP);
        }
        //Mulai menjalankan algoritma permainan
        if (start) {
            try {
                //Generate rintangan
                finish = LevelGenerator.run(g, player);

                //Peletakkan gambar nyawa yang dimiliki di layar
                renderLife(g);
                //Akhir dari peletakkan gambar nyawa

                //Peletakkan Score yang didapat
                renderScore(g);
                //Akhir dari peletakkan Score

                //Algoritma jika tombol slide ditekan dan animasi slide
                //sudah mencapai akhir
                if (buttonSlide.isOnPressed()) {
                    if (player.getSprite().getFrame() == player.getSprite().
                            getFrameSequenceLength() - 1) {
//                        System.out.println(player.getSprite().getFrame());
                        player.getSprite().setFrame(player.getSprite().
                                getFrameSequenceLength() - 1);
                    } else {
                        player.getSprite().update(timeLapsed);
                        timeLapsed++;
                    }
                } else {
                    //Algoritma jika karakter berjalan normal
                    player.getSprite().update(timeLapsed);
                    timeLapsed++;
                }

                //menggambar karakter ke layar
                player.getSprite().paint(g);

                //menggambar tombol slide ke layar
                if (buttonSlide.isVisible()) {
                    g.drawImage(buttonSlide.getImage(), buttonSlide.getX(),
                            buttonSlide.getY(), Graphics.TOP | Graphics.LEFT);
                }
                if (buttonJump.isVisible()) {
                    g.drawImage(buttonJump.getImage(), buttonJump.getX(),
                            buttonJump.getY(), Graphics.TOP | Graphics.LEFT);
                }
                //Akhir dari peletakan item di layar, flag penggambaran background 
                //selanjutnya diset false lagi
                hasRenderBackground = false;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (finish) {
            if (GameDataHelper.getHighScore(GameDataHelper.BALAP_KARUNG_RUMAH) < player.getCoinCount()) {
                GameDataHelper.writeHighScore(GameDataHelper.BALAP_KARUNG_RUMAH, player.getCoinCount());
            }
            releaseMemory();
            try {
                GameMidlet.gameCanvas.setGameScene(new MapScene());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void releaseMemory() {
        LevelGenerator.stop();
        player = null;
        backgroundImage = null;
        buttonCoin = null;
        buttonLife1 = buttonLife2 = buttonLife3 = null;
        buttonSlide = null;

        start = false;
        finish = false;
        hasInit = false;
        GameMidlet midlet = null;
        timeLapsed = 0;
        hasRenderBackground = false;
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
        if (buttonJump.isCanClick()
                && x >= buttonJump.getX() && x <= (buttonJump.getX() + buttonJump.getWidth())
                && y >= buttonJump.getY() && y <= (buttonJump.getY() + buttonJump.getHeight())) {
            buttonJump.setOnPressed(true);
            try {
                setLompat();//GameMidlet.gameCanvas.setGameScene(new StartMenuScene());
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
                //buttonSlide.setOnPressed(false);
                resetButton();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (buttonJump.isOnPressed()
                && x >= buttonJump.getX() && x <= (buttonJump.getX() + buttonJump.getWidth())
                && y >= buttonJump.getY() && y <= (buttonJump.getY() + buttonJump.getHeight())) {
            //resetButton();
            try {
                setNormal();
                //buttonSlide.setOnPressed(false);
                resetButton();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void pointerDragged(int x, int y) {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void clear(Graphics g) {
        g.setColor(255, 255, 255);
        g.fillRect(0, 0, 240, 320);
        hasRenderBackground = false;
    }

    /*
     * Metode untuk menginisialisasi rintangan sesuai levelnya
     */
    private void initLevel() {
        LevelGenerator.initConstraints(6, 10, 0, 0, 1, 2, 30, 50);
        LevelGenerator.initDistance(50, 300, 180, 200);
        LevelGenerator.initObjective(TypeList.DISTANCE, 1000);
        LevelGenerator.generateObstacles();
        LevelGenerator.generateCoins();
        System.out.println("selesai init level");
    }

    /*
     * Metode untuk menginisialisasi karaktr permainan
     */
    private void initPlayer() {
        try {
            player = PlayerData.getBagong();
            player.getSprite().setPosition(17, 150);
            player.getSprite().play();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("selesai init player");
    }

    /*
     * Metode untuk menginisialisasi background
     */
    private void initBackground() throws IOException {
        backgroundImage = new ImageItem("/resource/rumah/Sack Runner-01.jpg");
        awan = new ImageItem("/resource/items/awan_1.png");
        resetBackgroundPos();
        resetAwanPos();
        System.out.println("selesai init bg");
    }

    /*
     * Metode untuk mengembalikan posisi background di layar sesuai dengan
     * tampilan awal permainan
     */
    private void resetBackgroundPos() {
        BACKGROUND_POSA = 0;
        BACKGROUND_POSB = backgroundImage.getWidth() - 40;
        BACKGROUND_POSC = 2 * BACKGROUND_POSB;
    }
    
    private void resetAwanPos(){
        BACKGROUND_POSAWAN_A = 0;
        BACKGROUND_POSAWAN_B = backgroundImage.getWidth() - 40;
        BACKGROUND_POSAWAN_C = 2 * BACKGROUND_POSAWAN_B;
    }

    /*
     * Metode untuk menginisialisasi tombol
     */
    private void initButton() throws IOException {
        buttonSlide = new ButtonImageItem("/resource/button/slide.png",
                "/resource/button/slide_pressed.png");
        buttonJump = new ButtonImageItem("/resource/button/jump.png",
                "/resource/button/jump_pressed.png");
        buttonCoin = new ImageItem("/resource/button/coin.png");
        buttonLife1 = new ImageItem("/resource/button/heart.png");
        buttonLife2 = new ImageItem("/resource/button/heart.png");
        buttonLife3 = new ImageItem("/resource/button/heart.png");
        buttonJump.setX(0).setY(250);
        buttonCoin.setX(230).setY(10 + 230);
        buttonLife1.setX(230).setY(10);
        buttonLife2.setX(230).setY(10 + buttonLife2.getWidth() + 10);
        buttonLife3.setX(230).setY(10 + 2 * buttonLife3.getWidth() + 2 * 10);
        System.out.println("selesai init button");
    }

    /*
     * Metode yang dipanggil ketika tombol slide ditekan
     */
    private void setNgesot() throws Exception {
        player.setState(TypeList.SLIDE);
        player.setSprite(player.getSlideSprite());
        player.getSprite().setPosition(17, 150);
    }

    /*
     * Metode yang dipanggil ketika tombol slide ditekan
     */
    private void setLompat() throws Exception {
        player.setState(TypeList.JUMP);
        player.setSprite(player.getJumpSprite());
        player.getSprite().setPosition(17, 150);
    }

    /*
     * Metode untuk menormalisasi tampilan karakter
     */
    private void setNormal() throws Exception {
        player.setState(TypeList.NORMAL);
        player.setSprite(player.getNormalSprite());
        player.getSprite().setPosition(17, 150);
        //resetButton();
    }

    /*
     * Metode untuk mengembalikan semua tombol ke mode tidak ditekan
     */
    private void resetButton() {
        buttonSlide.setOnPressed(false);
        buttonJump.setOnPressed(false);
    }

    /*
     * Metode untuk merender background sesuai dengan kecepatan penampilan
     *
     * @param fpr kecepatan penampilan @param g grafik yang akan digambar
     */
    private void renderBackground(int fpr, Graphics g) {
        clear(g);
        g.drawImage(backgroundImage.getImage(), 0, BACKGROUND_POSA, Graphics.LEFT | Graphics.TOP);
        g.drawImage(backgroundImage.getImage(), 0, BACKGROUND_POSB, Graphics.LEFT | Graphics.TOP);
        g.drawImage(backgroundImage.getImage(), 0, BACKGROUND_POSC, Graphics.LEFT | Graphics.TOP);
        //System.out.println(BACKGROUND_POSA+" hahaha");

        //Algoritma enyesuaian letak background di layar
        if (BACKGROUND_POSB <= 0) {
            System.out.println("Abiezzzz berooh");
            resetBackgroundPos();
        }
        BACKGROUND_POSA -= fpr;
        BACKGROUND_POSB -= fpr;
        BACKGROUND_POSC -= fpr;
        hasRenderBackground = true;
    }

    /*
     * Metode untuk menggambar nyawa di layar
     */
    private void renderLife(Graphics g) {
        if (player.getBloodLevel() >= 1) {
            g.drawImage(buttonLife1.getImage(), buttonLife1.getX(),
                    buttonLife1.getY(), Graphics.RIGHT | Graphics.TOP);
        }
        if (player.getBloodLevel() >= 2) {
            g.drawImage(buttonLife2.getImage(), buttonLife2.getX(),
                    buttonLife2.getY(), Graphics.RIGHT | Graphics.TOP);
        }
        if (player.getBloodLevel() >= 3) {
            g.drawImage(buttonLife3.getImage(), buttonLife3.getX(),
                    buttonLife3.getY(), Graphics.RIGHT | Graphics.TOP);
        }
    }

    /*
     * Metode untuk menggambar Score di layar
     */
    private void renderScore(Graphics g) throws Exception {
        //gambar highscore sebelumnya
        Image mutableImageHigh = Image.createImage(20, 20);
        Graphics grImageHigh = mutableImageHigh.getGraphics();
        grImageHigh.drawString(GameDataHelper.getHighScore(GameDataHelper.BALAP_KARUNG_RUMAH) + "", 0, 0, Graphics.LEFT | Graphics.TOP);
        g.drawImage(StaticData.rotateImage(mutableImageHigh, 90),
                buttonCoin.getX() - 5, buttonCoin.getY() - 30,
                Graphics.RIGHT | Graphics.TOP);

        //gambar score
        Image mutableImage = Image.createImage(20, 20);
        Graphics grImage = mutableImage.getGraphics();
        grImage.drawString(player.getCoinCount() + "", 0, 0, Graphics.LEFT | Graphics.TOP);
        g.drawImage(StaticData.rotateImage(mutableImage, 90),
                buttonCoin.getX() - 5, buttonCoin.getY() + 30,
                Graphics.RIGHT | Graphics.TOP);
    }

    private void renderAwan(int fpr, Graphics g) {
        
        /*
         * Angka-angka yang ada di sini merupakan angka custom. Silahkan diubah
         * sekenanya
         */
        g.drawImage(awan.getImage(), 175, BACKGROUND_POSAWAN_A, Graphics.LEFT | Graphics.TOP);
        g.drawImage(awan.getImage(), 175, BACKGROUND_POSAWAN_B, Graphics.LEFT | Graphics.TOP);
        g.drawImage(awan.getImage(), 175, BACKGROUND_POSAWAN_C, Graphics.LEFT | Graphics.TOP);
        g.drawImage(awan.getImage(), 175, BACKGROUND_POSAWAN_A + 89, Graphics.LEFT | Graphics.TOP);
        g.drawImage(awan.getImage(), 175, BACKGROUND_POSAWAN_B + 89, Graphics.LEFT | Graphics.TOP);
        g.drawImage(awan.getImage(), 175, BACKGROUND_POSAWAN_C + 89, Graphics.LEFT | Graphics.TOP);
        //System.out.println(BACKGROUND_POSA+" hahaha");

        //Algoritma enyesuaian letak background di layar
        if (BACKGROUND_POSAWAN_B<= 0) {
            //System.out.println("abeeeeesss");
            resetAwanPos();
        }
        BACKGROUND_POSAWAN_A -= fpr;
        BACKGROUND_POSAWAN_B -= fpr;
        BACKGROUND_POSAWAN_C -= fpr;
        //hasRenderBackground = true;
    }
}
