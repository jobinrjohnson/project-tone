/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package projecttone;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 *
 * @author jobinrjohnson
 */
public class AudioManager {

    public static Synthesizer synthesizer = null;
    public static MidiChannel[] midiChannels = null;

    public static enum Instruments {
        PIANO, UNKNOWN_1, UNKNOWN_2, UNKNOWN_3, UNKNOWN_4, UNKNOWN_5, UNKNOWN_6, UNKNOWN_7, UNKNOWN_8, DRUMS
    }

    public int getInstrumentMidiChannel(Instruments channel) {
        return channel.ordinal();
    }

    public AudioManager() {
        if (synthesizer != null && midiChannels != null) {
            return;
        }
        try {
            synthesizer = MidiSystem.getSynthesizer();
            midiChannels = synthesizer.getChannels();
            synthesizer.open();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(AudioManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void play(int note, int volume, Instruments instrument, int duration) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                int channel = getInstrumentMidiChannel(instrument);
                int playerVolume = 127 * 100 / volume;
                try {
                    midiChannels[channel].noteOn(note, playerVolume); // C note
                    Thread.sleep(duration);
                    midiChannels[channel].noteOff(note);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void closeSynth() {
        if (synthesizer.isOpen()) {
            synthesizer.close();
        }
    }

}
