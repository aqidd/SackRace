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
//    static String[] obstaclesDown = {"/resource/obstacles/lubangkuburan.png",
//        "/resource/obstacles/lubangjalan.png", "/resource/obstacles/obstacle_batu.png", "/resource/obstacles/obstacle_tai.png",
//        "/resource/obstacles/obstacle_hidran.png"};
    static String[] obstaclesDown = {"/resource/obstacles/obstacle_batu.png", "/resource/obstacles/obstacle_tai.png",
        "/resource/obstacles/obstacle_hidran.png"};
    static int distance = 0;
    static boolean damaged = false;
    static int obstacleCounter, coinCounter = 0;
    static boolean paused = false;
    
    static boolean infinite = false;
    
    public static void initConstraints(int minObs, int maxObs, int minBerk, int maxBerk,
            int minBlo, int maxBlo, int minCo, int maxCo, int speed) {
        constraint.minObstacles = minObs;
        constraint.maxObstacles = maxObs;
        constraint.minBerserks = minBerk;
        constraint.maxBerserks = maxBerk;
        constraint.minBloods = minBlo;
        constraint.maxBloods = maxBlo;
        constraint.minCoins = minCo;
        constraint.maxCoins = maxCo;
        constraint.isInitialized = true;
        constraint.speed = speed;
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
        
    public static void initInfinite()
    {
        infinite = true;
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
                    image.setY(400 + (k * randomValue(constraint.minObstacleDistance,
                            constraint.minObstacleDistance * 2)));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Obstacle obj = new Obstacle();
                obj.setSprite(image);
                if (rand >= 15) {
                    obj.setType(TypeList.UP);
                } else {
                    obj.setType(TypeList.DOWN);
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
//                    image.setX(40 + randomValue(7, 20));
                    image.setX(40);
                    image.setY(300 + k * constraint.minCoinDistance);
//                    image.setY(300 + (k * randomValue(constraint.minCoinDistance,
//                            constraint.minCoinDistance * 2)));
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

    public static int run(Graphics g, Player p) {
        for (int x = 0; x < obstacleList.size(); x++) {
            Obstacle obj = (Obstacle) obstacleList.elementAt(x);
            ImageItem img = obj.getSprite();
            if (img.getY() > -150) {
                if (paused) {
                } else {
                    img.setY(img.getY() - constraint.speed);
                }
            }

            if (img.getY() > 330 && constraint.objective.qualifiedValue - distance < 330) {
                img.setVisible(false);
            }

            if (img.getY() <= 330) {
                if (img.isVisible()) {
                    g.drawImage(img.getImage(), img.getX(), img.getY(), Graphics.TOP | Graphics.LEFT);
                }
            }
            obj.setSprite(img);
            obstacleList.setElementAt(obj, x);
        }
        for (int x = 0; x < coinList.size(); x++) {
            Item obj = (Item) coinList.elementAt(x);
            ImageItem img = obj.getSprite();
            if (img.getY() > -150) {
                if (paused) {
                } else {
                    img.setY(img.getY() - constraint.speed);
                }
            }

            if (img.getY() > 330 && constraint.objective.qualifiedValue - distance < 330) {
                img.setVisible(false);
            }

            if (img.getY() <= 330) {
                if (img.isVisible()) {
                    g.drawImage(img.getImage(), img.getX(), img.getY(), Graphics.TOP | Graphics.LEFT);
                }
            }
            obj.setSprite(img);
            coinList.setElementAt(obj, x);
        }
        for (int x = 0; x < bloodList.size(); x++) {
            Item obj = (Item) bloodList.elementAt(x);
            ImageItem img = obj.getSprite();
            if (img.getY() > -150 && constraint.objective.qualifiedValue - distance > 200) {
                if (paused) {
                } else {
                    img.setY(img.getY() - constraint.speed);
                }
            }

            if (img.getY() <= 330 && constraint.objective.qualifiedValue - distance > 200) {
                if (img.isVisible()) {
                    g.drawImage(img.getImage(), img.getX(), img.getY(), Graphics.TOP | Graphics.LEFT);
                }
            }
            obj.setSprite(img);
            bloodList.setElementAt(obj, x);
        }
        if (paused) {
        } else {
            distance += constraint.speed;
        } 
        //CURRENTLY AVAILABLE OBJECTIVE : DISTANCE
        if (constraint.objective.objectiveType == TypeList.DISTANCE) {
            if (constraint.objective.qualifiedValue - distance < 200) {
                char[] finish = {'f', 'i', 'n', 'i', 's', 'h'};
                g.drawChars(finish, 0, 6, 20, 100 + (constraint.objective.qualifiedValue - distance), Graphics.TOP | Graphics.LEFT);
                if (constraint.objective.qualifiedValue - distance <= 0) {
                    //true artinya udah selesai
                    return TypeList.SUCCESS;
                }
            }
        }

        //GAME OVER CONDITION
        if (p.getBloodLevel() == 0) {
            return TypeList.GAMEOVER;
        }

        //CHECK FOR COLLISION?
        Obstacle obj = (Obstacle) obstacleList.elementAt(obstacleCounter);
        ImageItem img = obj.getSprite();
        if (img.getY() <= p.getSprite().getY() + 10 && img.getY() >= p.getSprite().getY() - 5) {
            if (obj.getType() == TypeList.UP) {
                if (p.getState() == TypeList.SLIDE) {
                } else {
                    damaged = true;
                }
            } else if (obj.getType() == TypeList.DOWN) {
                if (p.getState() == TypeList.JUMP) {
                } else {
                    damaged = true;
                }
            }
        }

        if (img.getY() < p.getSprite().getY() - 10) {
            if (damaged && img.isVisible()) {
                p.setBloodLevel(p.getBloodLevel() - 1);
            }
            if (obstacleCounter < obstacleList.size()) {
                obstacleCounter++;
            }

            damaged = false;
        }


        Item coin_obj = (Item) coinList.elementAt(coinCounter);
        ImageItem coin_img = coin_obj.getSprite();
        if (coin_img.getY() <= p.getSprite().getY() + 4 && coin_img.getY() >= p.getSprite().getY() - 4) {
            coin_img.setVisible(false);
            p.setCoinCount(p.getCoinCount() + 1);
            if (coinCounter < coinList.size()) {
                coinCounter++;
            }
        }
//            if (coin_img.getY() == p.getSprite().getY()) {
//                if (p.getState() != TypeList.SLIDE) {
//                    p.setCoinCount(p.getCoinCount()+1);
//                }
//            }

        return 0;
    }

    public static void stop() {
        distance = 0;
        damaged = false;
        obstacleCounter = 0;
        coinCounter = 0;
        paused = false;
        constraint = new LevelConstraint();
        obstacleList.removeAllElements();
        coinList.removeAllElements();
        berserkList.removeAllElements();
        bloodList.removeAllElements();
        infinite = false;
    }

    public static void pause()
    {
        paused = true;
    }
    public static void resume()
    {
        paused = false;
    }
    public static boolean isPaused()
    {
        return paused;
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
