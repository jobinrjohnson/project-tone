/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package projecttone;

/**
 *
 * @author jobinrjohnson
 */
public class Tone {
    
    AudioManager am;
    
    public Tone(){
        am = new AudioManager();
    }
    
    public class Instant{
        public String note;
        public int octave;
        public int duration_ms;
        public long instant;
        
    }
    
    public void playTone(Instant[] instants){
        for(Instant i : instants){
            new Thread(){
                
            }.start();
        }
    }
    
    
}
