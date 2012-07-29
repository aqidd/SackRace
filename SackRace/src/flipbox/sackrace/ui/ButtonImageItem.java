/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.ui;

import flipbox.sackrace.staticvalue.StaticData;
import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 *
 * @author agung
 */
public class ButtonImageItem extends ImageItem {

    protected Image imagePressed;
    protected boolean onPressed;

    /**
     * By default button dapat diclick
     *
     * @param normal
     * @param pressed
     * @throws IOException
     */
    public ButtonImageItem(String normal, String pressed) throws IOException {
        super(normal);
        onPressed = false;
        if (pressed != null) {
            this.imagePressed = Image.createImage(pressed);
            if (StaticData.LANDSCAPE) {
                try {
                    this.imagePressed = StaticData.rotateImage(this.imagePressed, 90);
         //           this.image = StaticData.rotateImage(image, 270);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Image getImage() {
        if (onPressed && this.imagePressed != null) {
            return this.imagePressed;
        }
        return this.image;
    }

    public Image getImagePressed() {
        return imagePressed;
    }

    public ButtonImageItem setImagePressed(Image imagePressed) {
        this.imagePressed = imagePressed;
        if (StaticData.LANDSCAPE) {
            try {
                this.imagePressed = StaticData.rotateImage(this.imagePressed, 90);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }

    public boolean isOnPressed() {
        return onPressed;
    }

    public ButtonImageItem setOnPressed(boolean onPressed) {
        this.onPressed = onPressed;
        return this;
    }
}