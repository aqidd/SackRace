/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.object;

import flipbox.sackrace.ui.ImageItem;

/**
 *
 * @author QED
 */
public class Item {
    
    int type;
    ImageItem sprite;

    public ImageItem getSprite() {
        return sprite;
    }

    public void setSprite(ImageItem sprite) {
        this.sprite = sprite;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
