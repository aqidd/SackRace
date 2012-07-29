/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.data;

import javax.microedition.lcdui.List;

/**
 *
 * @author QED
 */
public class Score {
    //kumpulan score dari level
    int[] levelscores;
    //highest score dari endless mode
    long endless_score;
    
    //history of scores per level
    List[] level_history;
    List endless_history;
    
}
