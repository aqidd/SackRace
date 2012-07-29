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
public class ImageItem {

    protected int width, height;
    protected Image image;
    protected int x, y;
    protected boolean visible;
    protected boolean canClick;

    public ImageItem(String name) throws IOException {
        this.image = Image.createImage(name);
        if (StaticData.LANDSCAPE) {
            try {
                this.image = StaticData.rotateImage(this.image, 90);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.width = image.getWidth();
        this.height = image.getHeight();
        visible = true;
        canClick = true;
    }

    public Image getImage() {
        return image;
    }

    public ImageItem setImage(Image image) {
        this.image = image;
        if (StaticData.LANDSCAPE) {
            try {
                this.image = StaticData.rotateImage(this.image, 90);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }

    public int getWidth() {
        return width;
    }

    public ImageItem setWidth(int width) {
        this.width = width;
        return this;
    }

    public int getHeight() {
        return height;
    }

    public ImageItem setHeight(int height) {
        this.height = height;
        return this;
    }

    public int getX() {
        return x;
    }

    public ImageItem setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public ImageItem setY(int y) {
        this.y = y;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public ImageItem setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isCanClick() {
        return canClick;
    }

    public ImageItem setCanClick(boolean canClick) {
        this.canClick = canClick;
        return this;
    }
}
