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
public class Obstacle {
    
    int type;
    int damage;
    AnimatedSprite sprite;

    public AnimatedSprite getSprite() {
        return sprite;
    }

    public void setSprite(AnimatedSprite sprite) {
        this.sprite = sprite;
    }
    
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
}
