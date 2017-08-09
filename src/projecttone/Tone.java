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
    long instant = 0;

    public Tone() {
        am = new AudioManager();
    }

    public void playTone(Instant[] instants) {
        final MidiChannel[] midiChannels = AudioManager.midiChannels;
        new Thread() {
            @Override
            public void run() {
                super.run();
                for (Instant i : instants) {
                    if (instant < i.instant) {
                        try {
                            Thread.sleep(i.instant - instant);
                            instant = i.instant;
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Tone.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
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

        }.start();
    }

    public void saveTone(Instant[] instants, String fileName) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("stored_tones/" + fileName + ".dat"));
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
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("stored_tones/" + fileName + ".dat"));
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
