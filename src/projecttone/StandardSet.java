/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package projecttone;

import java.util.Arrays;

/**
 *
 * @author jobinrjohnson
 */
public class StandardSet {

    public static final String[] Notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};

    public int getToneNumber(String Note, int octave) {
        int index = Arrays.asList(Notes).indexOf(Notes);
        if (index > -1) {
            int mOctave = octave < 12 && octave >= 0 ? octave : 0;
            return (mOctave * 12) + index;
        }
        return 0;
    }

}
