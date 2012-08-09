/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.object;

import flipbox.sackrace.staticvalue.StaticData;
import flipbox.sackrace.ui.AnimatedSprite;
import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 *
 * @author QED
 */
public class PlayerData {

    Player gareng, bagong, petruk, pocong;

    public static Player getGareng() {
        Player gar = new Player();
        gar.setName("Gareng");
        gar.setBloodLevel(3);
        gar.setJumpHeight(10);
        gar.setSprite(null);
        return gar;
    }

    public static Player getPetruk() {
        Player pet = new Player();
        return pet;
    }

    public static Player getBagong() {
        Player bag = new Player();
        bag.setName("Bagong");
        bag.setBloodLevel(3);
        bag.setJumpHeight(10);
        Image sprite = null;
        try {
            sprite = StaticData.rotateImage(Image.createImage("/resource/chars/bagong_lompat.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bag.setSprite(new AnimatedSprite(sprite, 65, 70, 2));
        return bag;
    }

    public static Player getPocong() {
        Player poc = new Player();
        return poc;
    }
}
