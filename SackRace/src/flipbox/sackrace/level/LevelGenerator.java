/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.level;

import flipbox.sackrace.object.Obstacle;
import flipbox.sackrace.ui.ImageItem;
import java.io.IOException;
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

    public static void initConstraint()
    {
        constraint.minObstacles = 2;
        constraint.maxObstacles = 6;
    }
    
    public static void generateObstacles() {
        for (int k = 0; k < constraint.minObstacles; k++) {
            ImageItem image = null;
            try {
                image = new ImageItem("/resource/obstacles/obstacle_bat.png");
                image.setX(50);
                image.setY(400+ (k*100));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Obstacle obj = new Obstacle();
            obj.setSprite(image);
            obj.setType(0);
            obj.setDamage(1);
            obstacleList.addElement(obj);
        }
    }

    public static void generateBloods() {
    }

    public static void generateBerserks() {
    }

    public static void generateCoins() {
    }

    public static void run(Graphics g) {
        for(int x=0; x<obstacleList.size(); x++)
        {
            Obstacle obj = (Obstacle) obstacleList.elementAt(x);
            ImageItem img = obj.getSprite();
            if(img.getY()>-150)
                img.setY(img.getY()-1);
            System.out.println("lokasi img y " + img.getY() + "xx : " + x);
//            if(img.getY() <= 330)
            {
                g.drawImage(img.getImage(), img.getX(), img.getY(), Graphics.TOP | Graphics.LEFT);
            }
            obj.setSprite(img);
            obstacleList.setElementAt(obj, x);
        }
    }
}
