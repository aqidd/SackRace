/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.staticvalue;

/**
 * berisi static identifier untuk pembeda jenis item/obstacle
 *
 * @author QED
 */
public class TypeList {
    //kind of obstacles
    public static final int BAT = 1;
    public static final int BIRD = 1;
    public static final int BALLOON =1;
    public static final int CLOUND =1;
    public static final int HYDRANT = 2;
    public static final int ROCK = 2;
    public static final int POOP = 2;
    public static final int HOLE = 2;
    //type of obstacles 
    public static final int UP = 1;
    public static final int DOWN = 2;
    //type of items
    public static final int COIN = 1;
    public static final int BERSERK = 2;
    public static final int LIFE = 3;
    //type of objectives
    public static final int TIME = 1;
    public static final int COINS = 2;
    public static final int DISTANCE = 3;
    public static final int BLOOD = 4;
    //type of player state
    public static final int NORMAL = 0;
    public static final int JUMP = 1;
    public static final int SLIDE = 2;
}