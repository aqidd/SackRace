/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.object;

import flipbox.sackrace.ui.AnimatedSprite;

/**
 *
 * @author QED
 */
public class Player {
    private int jumpHeight;
    private int bloodLevel;
    private int coinCount;
    String name;
    AnimatedSprite sprite, jumpSprite, slideSprite, normalSprite;
    AnimatedSprite crashedUpSprite, crashedDownSprite;
    int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
    
    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(int coinCount) {
        this.coinCount = coinCount;
    }
    
    public int getBloodLevel() {
        return bloodLevel;
    }

    public void setBloodLevel(int bloodLevel) {
        this.bloodLevel = bloodLevel;
    }

    public int getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(int jumpHeight) {
        this.jumpHeight = jumpHeight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimatedSprite getSprite() {
        return sprite;
    }

    public void setSprite(AnimatedSprite sprite) {
        this.sprite = sprite;
    }

    public AnimatedSprite getJumpSprite() {
        return jumpSprite;
    }

    public void setJumpSprite(AnimatedSprite jumpSprite) {
        this.jumpSprite = jumpSprite;
    }

    public AnimatedSprite getSlideSprite() {
        return slideSprite;
    }

    public void setSlideSprite(AnimatedSprite slideSprite) {
        this.slideSprite = slideSprite;
    }
    public AnimatedSprite getNormalSprite() {
        return normalSprite;
    }

    public void setNormalSprite(AnimatedSprite normalSprite) {
        this.normalSprite = normalSprite;
    }

    public AnimatedSprite getCrashedDownSprite() {
        return crashedDownSprite;
    }

    public void setCrashedDownSprite(AnimatedSprite crashedDownSprite) {
        this.crashedDownSprite = crashedDownSprite;
    }

    public AnimatedSprite getCrashedUpSprite() {
        return crashedUpSprite;
    }

    public void setCrashedUpSprite(AnimatedSprite crashedUpSprite) {
        this.crashedUpSprite = crashedUpSprite;
    }
    
}
