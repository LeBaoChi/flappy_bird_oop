/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.io.File;
import javax.sound.sampled.*;

/**
 *
 * @author phamn
 */
public class SoundPlayer {
    
    private Clip clip;
    AudioInputStream ais;
    DataLine.Info info_wing;
    private boolean sound=true;
    public void play(File path){
        try{

            ais = AudioSystem.getAudioInputStream(path);
            info_wing = new DataLine.Info(Clip.class, ais.getFormat());
            clip = (Clip)AudioSystem.getLine(info_wing);
            clip.open(ais);

        }catch(Exception e){}
        if(clip !=null){
            stop();
//            clip.setFramePosition(0);
            clip.start();
        }else{
            clip.start();
        }
    }
    public void stop(){
        if(clip.isRunning()) clip.stop();
    }
    
    public void close(){
        clip.close();
    }

    public void offSound(){
        sound=false;
    }

    public boolean isSound() {
        return sound;
    }

    public void onSound(){
        sound=true;
    }
}
