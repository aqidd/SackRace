/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.level;

import flipbox.sackrace.object.Item;
import flipbox.sackrace.object.Obstacle;
import flipbox.sackrace.object.Player;
import flipbox.sackrace.staticvalue.TypeList;
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
    static int distance = 0;

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

    public static void initObjective(int type, int value) {
        LevelObjective objective = new LevelObjective();
        objective.objectiveType = type;
        objective.qualifiedValue = value;
        constraint.objective = objective;
    }

    public static void generateObstacles() {
        if (constraint.isInitialized) {
            int obstaclesCount = randomValue(constraint.minObstacles, constraint.maxObstacles);
            for (int k = 0; k < obstaclesCount; k++) {
                ImageItem image = null;
                int rand = 0;
                try {
                    int valUp = randomValue(0, obstaclesUp.length);
                    int valDown = randomValue(0, obstaclesDown.length);
                    rand = randomValue(0, 30);
                    if (rand >= 15) {
                        image = new ImageItem(obstaclesUp[valUp]);
                        image.setX(70);
                    } else {
                        image = new ImageItem(obstaclesDown[valDown]);
                        image.setX(10);
                    }
                    image.setY(400 + (k * constraint.minObstacleDistance));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Obstacle obj = new Obstacle();
                obj.setSprite(image);
                if (rand >= 15) {
                    obj.setType(TypeList.TREE);
                } else {
                    obj.setType(TypeList.ROCK);
                }
                obj.setDamage(1);
                obstacleList.addElement(obj);
            }
        } else {
            System.out.println("constraint not initialized");
        }
    }

    public static void generateBloods() {
        if (constraint.isInitialized) {
            int bloodCount = randomValue(constraint.minBloods, constraint.maxBloods);
            for (int k = 0; k < bloodCount; k++) {
                ImageItem image = null;
                try {
                    image = new ImageItem("/resource/items/heart.png");
                    image.setX(50);
                    image.setY(400 + (k * constraint.minBloodDistance));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Item obj = new Item();
                obj.setSprite(image);
                obj.setType(TypeList.LIFE);
                bloodList.addElement(obj);
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
                Item obj = new Item();
                obj.setSprite(image);
                obj.setType(TypeList.COIN);
                coinList.addElement(obj);
            }
        } else {
            System.out.println("constraint not initialized");
        }
    }

    public static boolean run(Graphics g, Player p) {
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
            Item obj = (Item) coinList.elementAt(x);
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
        for (int x = 0; x < bloodList.size(); x++) {
            Item obj = (Item) bloodList.elementAt(x);
            ImageItem img = obj.getSprite();
            if (img.getY() > -150) {
                img.setY(img.getY() - 3);
            }

            if (img.getY() <= 330) {
                g.drawImage(img.getImage(), img.getX(), img.getY(), Graphics.TOP | Graphics.LEFT);
            }
            obj.setSprite(img);
            bloodList.setElementAt(obj, x);
        }
        distance += 3;

        //CURRENTLY AVAILABLE OBJECTIVE : DISTANCE
        if (constraint.objective.objectiveType == TypeList.DISTANCE) {
            if (constraint.objective.qualifiedValue - distance < 200) {
                char[] finish = {'f', 'i', 'n', 'i', 's', 'h'};
                g.drawChars(finish, 0, 6, 20, 100 + (constraint.objective.qualifiedValue - distance), Graphics.TOP | Graphics.LEFT);
                if (constraint.objective.qualifiedValue - distance <= 0) {
                    //true artinya udah selesai
                    return true;
                }
            }
        }

        //GAME OVER CONDITION
        if(p.getBloodLevel() == 0)
        {
            return true;
        }
        
        //CHECK FOR COLLISION?
        int frame = p.getSprite().getFrame();
        //kalo jump syaratnya di frame 5. kalo slide di frame 4
        for (int x = 0; x < obstacleList.size(); x++) {
            Obstacle obj = (Obstacle) obstacleList.elementAt(x);
            ImageItem img = obj.getSprite();
            if (img.getY() == p.getSprite().getY() + 10) {
                if (obj.getType() == TypeList.TREE) {
                    if (p.getState() == TypeList.SLIDE) {
                    } else {
                        p.setBloodLevel(p.getBloodLevel() - 1);
                    }
                } else if (obj.getType() == TypeList.ROCK) {
                    if (p.getState() == TypeList.JUMP) {
                    } else {
                        p.setBloodLevel(p.getBloodLevel() - 1);
                    }
                }
                System.out.println("frame " + frame + " bloodlevel : " + p.getBloodLevel());
            }
        }
        return false;
    }

    public static void stop() {
        distance = 0;
        constraint = new LevelConstraint();
        obstacleList.removeAllElements();
        coinList.removeAllElements();
        berserkList.removeAllElements();
        bloodList.removeAllElements();
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
