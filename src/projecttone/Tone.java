/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package projecttone;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiChannel;
import static projecttone.AudioManager.midiChannels;

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
        public int velocity = 120;
        public AudioManager.Instruments instrument;
    }
    
    public void playTone(Instant[] instants){
        final MidiChannel[] midiChannels = AudioManager.midiChannels;
        for(Instant i : instants){
            new Thread(){

                @Override
                public void run() {
                    super.run(); 
                    int channel = AudioManager.getInstrumentMidiChannel(i.instrument);
                    int toneNumber = StandardSet.getToneNumber(i.note, i.octave);
                    midiChannels[channel].noteOn(toneNumber, i.velocity); 
                    try {
                        Thread.sleep(i.duration_ms);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Tone.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    midiChannels[channel].noteOff(toneNumber);
                }
                
            }.start();
        }
    }
    
    
}
