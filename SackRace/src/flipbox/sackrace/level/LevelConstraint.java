/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.level;

import flipbox.sackrace.nongamescreen.MapScene;
import java.util.Vector;


/**
 *
 * @author QED
 */
public class LevelConstraint {
    
    boolean isInitialized;
    
    //untuk menentukan obstacle minimum yang keluar. semua obstacle dianggap sama bobotnya
    int minObstacles, maxObstacles;
    //untuk menentukan jarak minimum obstacle (makin tinggi level makin kecil jaraknya)
    int minObstacleDistance;
    
    //untuk menentukan darah minimum yang keluar
    int minBloods, maxBloods;
    //untuk menentukan jarak minimum item bertipe blood (makin tinggi level makin besar jaraknya)
    int minBloodDistance;
    
    //untuk menentukan koin minimun yang keluar
    int minCoins, maxCoins;
    //untuk menentukan jarak minimum coin
    int minCoinDistance;
    
    //untuk menentukan berserk minimum yang keluar dalam sebuah level
    int minBerserks, maxBerserks;
    //untuk menentukan jarak minimum obstacle (makin tinggi level makin besar jaraknya)
    int minBerserkDistance;
    
    //untuk menentukan kecepatan level
    int speed;
    
    LevelObjective objective;
  
}
