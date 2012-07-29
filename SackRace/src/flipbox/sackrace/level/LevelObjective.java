/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.level;

/**
 *
 * @author QED
 */
public class LevelObjective {
    //types of objectives
    public static final int COINS_MINIMUM=1; //selesaikan level dengan minimum coin xx
    public static final int TIME_MINIMUM=2; //selesaikan level dengan minimum waktu xx:xx
    public static final int BLOOD_MINIMUM=3; //selesaikan level dengan minimum darah tersisa x
    
    int objectiveType;
    int objectiveScore;
}
