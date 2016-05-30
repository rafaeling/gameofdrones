/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author rafae
 */
public class PlaySound {
    
    private AudioStream audioStream;
    
    
    /*
        Ejemplo !! Sólo tipo wav
        
        PlaySound sonido = new PlaySound("sound/beep1.wav");
        
        sonido.play();
    
        sonido.stop();
    
    */
    public PlaySound(String url) throws FileNotFoundException, IOException
    {
        InputStream in = new FileInputStream(url);
 
        audioStream = new AudioStream(in);
    }
    
    
    /*
        
        Reproduce un sonido
    
    */
    public void play()
    {
        try {
            AudioPlayer.player.start(audioStream);
            } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
    
    /*
        
        Para Sonido
    
    */
    public void stop()
    {
        try {
            AudioPlayer.player.stop(audioStream);
            } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
   
    
}
