/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package standards;

/**
 *
 * @author jobinrjohnson
 */
public class StandardSet {

    public static final int C = 0;
    public static final int CS = 1;
    public static final int D = 2;
    public static final int DS = 3;
    public static final int E = 4;
    public static final int F = 5;
    public static final int FS = 6;
    public static final int G = 7;
    public static final int GS = 8;
    public static final int A = 9;
    public static final int AS = 10;
    public static final int B = 11;

    public static final int[] STANDARD_NOTES = {C, CS, D, DS, E, F, FS, G, GS, A, AS, B};

    public static int getToneNumber(int note, int octave) {
        int mOctave = octave < 12 && octave >= 0 ? octave : 0;
        return (mOctave * 12) + note;
    }

}
