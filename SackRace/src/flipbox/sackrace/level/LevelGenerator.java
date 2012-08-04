/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.level;

import flipbox.sackrace.object.Obstacle;
import flipbox.sackrace.ui.ImageItem;
import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author QED
 */
public class LevelGenerator {

    static LevelConstraint constraint = new LevelConstraint();
    //baca boolean isInitialized dari constraint buat deteksi apakah constraint sudah disetting
    static Vector obstacleList = new Vector();
    static Vector coinList = new Vector();
    static Vector berserkList = new Vector();
    static Vector bloodList = new Vector();
    static String[] obstaclesUp = {"/resource/obstacles/obstacle_bat.png", "/resource/obstacles/obstacle_bird.png",
        "/resource/obstacles/obstacle_balon.png", "/resource/obstacles/obstacle_awan.png"};
    static String[] obstaclesDown = {"/resource/obstacles/lubangkuburan.png",
        "/resource/obstacles/lubangjalan.png", "/resource/obstacles/obstacle_batu.png", "/resource/obstacles/obstacle_tai.png",
        "/resource/obstacles/obstacle_hidran.png"};

    public static void initConstraints(int minObs, int maxObs, int minBerk, int maxBerk,
            int minBlo, int maxBlo, int minCo, int maxCo) {
        constraint.minObstacles = minObs;
        constraint.maxObstacles = maxObs;
        constraint.minBerserks = minBerk;
        constraint.maxBerserks = maxBerk;
        constraint.minBloods = minBlo;
        constraint.maxBloods = maxBlo;
        constraint.minCoins = minCo;
        constraint.maxCoins = maxCo;
        constraint.isInitialized = true;
    }

    public static void initDistance(int coinDis, int bloodDis, int obsDis, int berDis) {
        constraint.minCoinDistance = coinDis;
        constraint.minBloodDistance = bloodDis;
        constraint.minObstacleDistance = obsDis;
        constraint.minBerserkDistance = berDis;
    }

    public static void generateObstacles() {
        if (constraint.isInitialized) {
            int obstaclesCount = randomValue(constraint.minObstacles, constraint.maxObstacles);
            for (int k = 0; k < obstaclesCount; k++) {
                ImageItem image = null;
                try {
                    int valUp = randomValue(0, obstaclesUp.length);
                    int valDown = randomValue(0, obstaclesDown.length);
                    if (randomValue(0, 1) == 0) {
                        image = new ImageItem(obstaclesUp[valUp]);
                        image.setX(50);
                    } else {
                        image = new ImageItem(obstaclesUp[valDown]);
                        image.setX(10);
                    }
                    image.setY(400 + (k * constraint.minObstacleDistance));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Obstacle obj = new Obstacle();
                obj.setSprite(image);
                obj.setType(0);
                obj.setDamage(1);
                obstacleList.addElement(obj);
            }
        } else {
            System.out.println("constraint not initialized");
        }
    }

    public static void generateBloods() {
        if (constraint.isInitialized) {
            int obstaclesCount = randomValue(constraint.minObstacles, constraint.maxObstacles);
            for (int k = 0; k < obstaclesCount; k++) {
                ImageItem image = null;
                try {
                    int val = randomValue(0, obstaclesUp.length);
                    image = new ImageItem(obstaclesUp[val]);
                    image.setX(50);
                    image.setY(400 + (k * 100));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Obstacle obj = new Obstacle();
                obj.setSprite(image);
                obj.setType(0);
                obj.setDamage(1);
                obstacleList.addElement(obj);
            }
        } else {
            System.out.println("constraint not initialized");
        }
    }

    public static void generateBerserks() {
    }

    public static void generateCoins() {
        if (constraint.isInitialized) {
            int coinCount = randomValue(constraint.minCoins, constraint.maxCoins);
            for (int k = 0; k < coinCount; k++) {
                ImageItem image = null;
                try {
                    image = new ImageItem("/resource/items/coin.png");
                    image.setX(50);
                    image.setY(300 + (k * constraint.minCoinDistance));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Obstacle obj = new Obstacle();
                obj.setSprite(image);
                obj.setType(0);
                obj.setDamage(1);
                coinList.addElement(obj);
            }
        } else {
            System.out.println("constraint not initialized");
        }
    }

    public static void run(Graphics g) {
        for (int x = 0; x < obstacleList.size(); x++) {
            Obstacle obj = (Obstacle) obstacleList.elementAt(x);
            ImageItem img = obj.getSprite();
            if (img.getY() > -150) {
                img.setY(img.getY() - 3);
            }

            if (img.getY() <= 330) {
                g.drawImage(img.getImage(), img.getX(), img.getY(), Graphics.TOP | Graphics.LEFT);
            }
            obj.setSprite(img);
            obstacleList.setElementAt(obj, x);
        }
        for (int x = 0; x < coinList.size(); x++) {
            Obstacle obj = (Obstacle) coinList.elementAt(x);
            ImageItem img = obj.getSprite();
            if (img.getY() > -150) {
                img.setY(img.getY() - 3);
            }

            if (img.getY() <= 330) {
                g.drawImage(img.getImage(), img.getX(), img.getY(), Graphics.TOP | Graphics.LEFT);
            }
            obj.setSprite(img);
            coinList.setElementAt(obj, x);
        }
    }

    public static int randomValue(int min, int max) {
        Random number = new Random();
        float f = number.nextFloat() * 100000;
        number.setSeed(System.currentTimeMillis());
        int val = ((int) f) % max;
        if (val < min) {
            val += min;
        }
        if (val > max) {
            val -= ((val - max) + 1);
        }

        return val;
    }
}
