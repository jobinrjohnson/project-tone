/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package projecttone;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public Tone() {
        am = new AudioManager();
    }

    public class Instant {

        public String note;
        public int octave;
        public int duration_ms;
        public long instant;
        public int velocity = 120;
        public AudioManager.Instruments instrument;
    }

    public void playTone(Instant[] instants) {
        final MidiChannel[] midiChannels = AudioManager.midiChannels;
        for (Instant i : instants) {
            new Thread() {

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

    public void saveTone(Instant[] instants, String fileName) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(fileName + ".dat"));
            out.writeObject(instants);
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(Tone.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Tone.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Instant[] retriveTone(String fileName) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName + ".dat"));
            Instant[] array = (Instant[]) in.readObject();
            in.close();
            return array;
        } catch (IOException ex) {
            Logger.getLogger(Tone.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tone.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
