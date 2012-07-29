/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flipbox.sackrace.game;

import flipbox.sackrace.nongamescreen.SplashScreenScene;
import java.io.InputStream;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.Display;

/**
 * @author agung
 */
public class GameMidlet extends MIDlet{
    private Command back;
    private Display display;
    public  static DolananGameCanvas gameCanvas;
    private static Player musicPlayer;
    private static Player effectPlayer;
    private static boolean soundOn;
    private static String currentMusicResource;
   
    public void startApp() {
        gameCanvas = new DolananGameCanvas(new SplashScreenScene(this));
        gameCanvas.setGameMidlet(this);
        soundOn = true;
        back = new Command("Back", Command.BACK, 0);
        
        display = Display.getDisplay(this);
        display.setCurrent(gameCanvas);
    }
    
    public void pauseApp() {
//        if(musicPlayer != null){
//            try{
//                musicPlayer.stop();
//                effectPlayer.stop();
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//        }
    }
    
    public void destroyApp(boolean unconditional) {
        if(musicPlayer != null){
            try{
                musicPlayer.stop();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                musicPlayer.close();
            }
        }
        if(effectPlayer!=null){
            try{
                effectPlayer.stop();
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                effectPlayer.close();
            }
        }
        
    }
    
    public static void playBackgroundMusic(String resourceURL){
        currentMusicResource = resourceURL;
        if(!soundOn)
            return;
        try{
            if(musicPlayer == null){
                InputStream inputStream = GameMidlet.gameCanvas.getClass().getResourceAsStream(resourceURL);
                musicPlayer = Manager.createPlayer(inputStream, "audio/midi");
                
                musicPlayer.setLoopCount(-1);
                musicPlayer.start();
                
            }else{
                if(musicPlayer.getState()!= Player.CLOSED)
                    musicPlayer.stop();
                musicPlayer.close();
                
                InputStream inputStream = GameMidlet.gameCanvas.getClass().getResourceAsStream(resourceURL);
                musicPlayer = Manager.createPlayer(inputStream, "audio/midi");
                
                musicPlayer.setLoopCount(-1);
                musicPlayer.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void stopBackgroundMusic(){
        try{
            if(musicPlayer == null){
                //do nothing
            }else{
                musicPlayer.stop();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void startBackgroundMusic(){
        if(!soundOn)
            return;
        try{
            if(musicPlayer == null){
                //do nothing
            }else{
                musicPlayer.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void closeBackgroundMusic(){
        try{
            if(musicPlayer == null){
                //do nothing
            }else{
                musicPlayer.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void playEffectSound(String resourceURL){
        if(!soundOn)
            return;
        try{
            if(effectPlayer == null){
                InputStream inputStream = GameMidlet.gameCanvas.getClass().getResourceAsStream(resourceURL);
                effectPlayer = Manager.createPlayer(inputStream, "audio/midi");
                
                effectPlayer.setLoopCount(11);
                effectPlayer.start();
                
            }else{
                effectPlayer.stop();
                effectPlayer.close();
                
                InputStream inputStream = GameMidlet.gameCanvas.getClass().getResourceAsStream(resourceURL);
                effectPlayer = Manager.createPlayer(inputStream, "audio/midi");
                
                effectPlayer.setLoopCount(1);
                effectPlayer.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void stopEffectSound(){
        try{
            if(effectPlayer == null){
                //do nothing
            }else{
                effectPlayer.stop();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void startEffectSound(){
        if(!soundOn)
            return;
        try{
            if(effectPlayer == null){
                //do nothing
            }else{
                effectPlayer.start();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void closeEffectSound(){
        try{
            if(effectPlayer == null){
                //do nothing
            }else{
                effectPlayer.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public static void setSoundOn(boolean status){
        soundOn = status;
        if(!soundOn){
            stopBackgroundMusic();
//            closeBackgroundMusic();
            stopEffectSound();
            closeEffectSound();
        }else{
            playBackgroundMusic(currentMusicResource);
        }
        
    }
    
    public static boolean isSoundOn(){
        return soundOn;
    }
    
    
}
