/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.ui;

import java.io.IOException;
import javax.microedition.lcdui.Image;

/**
 *
 * @author agung
 */
public class Sprite {
    private Image image[];
    private long[] delay;
    private int currentFrameIndex;
    private Thread thread;
    private boolean start;
    
    public Sprite(String[] resource, long[] delay) throws IOException{
        image = new Image[resource.length];
        for(int i = 0; i < resource.length; ++i){
            image[i] = Image.createImage(resource[i]);
        }
        currentFrameIndex = 0;
        start = false;
    }
    
    public Image getCurrentFrame(){
        return this.image[currentFrameIndex];
    }
    
    /**
     * 
     * @param loop , if loop > 0 then will play loop-loops animation
     * if loop < 0 then will play it indefinitely
     */
    public void startPlay(final int loop) throws Exception{
        if(thread!=null && thread.isAlive()){
            throw new Exception("You must call stop before play it again!!");
        }
        start = true;
        if(loop < 0){
            thread = new Thread(new Runnable() {

                public void run() {
                    while(start){
                        for(int i = 0; i  < delay.length && start; ++i){
                            currentFrameIndex = i;
                            System.out.println("frameNow : "+currentFrameIndex);
                            try{
                                Thread.sleep(delay[i]);
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }else{
            thread = new Thread(new Runnable() {

                public void run() {
                    int manyLoops = loop;
                    while(start && manyLoops > 0){
                        for(int i = 0; i  < delay.length && start; ++i){
                            currentFrameIndex = i;
                            try{
                                Thread.sleep(delay[i]);
                            }
                            catch(Exception e){
                                e.printStackTrace();
                            }
                        }
                        manyLoops--;
                    }
                }
            });
        }
        thread.start();
    }
    
    public void stopPlay(){
        if(thread!=null && thread.isAlive()){
            start = false;
        }
    }
    
    public int getCurrentIndexFrame(){
        return this.currentFrameIndex;
    }
}
