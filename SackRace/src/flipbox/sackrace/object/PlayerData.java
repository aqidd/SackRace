/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.object;

import flipbox.sackrace.staticvalue.StaticData;
import flipbox.sackrace.staticvalue.TypeList;
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
        gar.setName("Bagong");
        gar.setBloodLevel(3);
        gar.setJumpHeight(10);
        gar.setCoinCount(0);
        Image sprite = null;
        try {
            sprite = StaticData.rotateImage(Image.createImage("/resource/chars/bagong_lompat.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        gar.setSprite(new AnimatedSprite(sprite, 75, 280/4, 2));
        gar.setNormalSprite(new AnimatedSprite(sprite, 75, 280/4, 2));
        Image bagong_slide = null;
        try {
            bagong_slide = StaticData.rotateImage(Image.createImage(
              "/resource/chars/bagong_ngesot.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        gar.setSlideSprite(new AnimatedSprite(bagong_slide, 70, bagong_slide.getHeight() / 4, 3));
        
        Image bagong_jump=null;
        try {
            bagong_jump = StaticData.rotateImage(Image.createImage(
              "/resource/chars/sprite loncat bagong.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        gar.setJumpSprite(new AnimatedSprite(bagong_jump, 100, bagong_jump.getHeight() / 7, 2));

        return gar;
    }

    public static Player getPetruk() {
        Player pet = new Player();
        pet.setName("Bagong");
        pet.setBloodLevel(3);
        pet.setJumpHeight(10);
        pet.setCoinCount(0);
        Image sprite = null;
        try {
            sprite = StaticData.rotateImage(Image.createImage("/resource/chars/bagong_lompat.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        pet.setSprite(new AnimatedSprite(sprite, 75, 280/4, 2));
        pet.setNormalSprite(new AnimatedSprite(sprite, 75, 280/4, 2));
        Image bagong_slide = null;
        try {
            bagong_slide = StaticData.rotateImage(Image.createImage(
              "/resource/chars/bagong_ngesot.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        pet.setSlideSprite(new AnimatedSprite(bagong_slide, 70, bagong_slide.getHeight() / 4, 3));
        
        Image bagong_jump=null;
        try {
            bagong_jump = StaticData.rotateImage(Image.createImage(
              "/resource/chars/sprite loncat bagong.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        pet.setJumpSprite(new AnimatedSprite(bagong_jump, 100, bagong_jump.getHeight() / 7, 2));

        return pet;
    }

    public static Player getBagong() {
        Player bag = new Player();
        bag.setName("Bagong");
        bag.setBloodLevel(3);
        bag.setJumpHeight(10);
        bag.setCoinCount(0);
        Image sprite = null;
        try {
            sprite = StaticData.rotateImage(Image.createImage("/resource/chars/bagong_lompat.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bag.setSprite(new AnimatedSprite(sprite, 75, 280/4, 2));
        bag.setNormalSprite(new AnimatedSprite(sprite, 75, 280/4, 2));
        Image bagong_slide = null;
        try {
            bagong_slide = StaticData.rotateImage(Image.createImage(
              "/resource/chars/bagong_ngesot.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        bag.setSlideSprite(new AnimatedSprite(bagong_slide, 70, bagong_slide.getHeight() / 4, 3));
        
        Image bagong_jump=null;
        try {
            bagong_jump = StaticData.rotateImage(Image.createImage(
              "/resource/chars/sprite loncat bagong.png"), 90);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        bag.setJumpSprite(new AnimatedSprite(bagong_jump, 100, bagong_jump.getHeight() / 7, 2));

        return bag;
    }

    public static Player getPocong() {
        Player poc = new Player();
        return poc;
    }
}
