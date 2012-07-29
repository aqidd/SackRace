/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chocoarts.shape;

import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author lieenghao
 */
public class Rectangle {
    public int x, y, width, height;
    
    public Rectangle(){}
    
    public Rectangle(Sprite sprite){
        this.x = sprite.getX();
        this.y = sprite.getY();
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
    }
    
    public Rectangle(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean isInside(int px, int py) {
        return (x<px && y<py && px<x+width && py<y+height);
    }
    
    public boolean intersect(Rectangle rect){
        return this.x < rect.x + rect.width &&
                this.x + this.width > rect.x &&
                this.y < rect.y + rect.height && 
                this.y + this.height > rect.y;
    }
}
