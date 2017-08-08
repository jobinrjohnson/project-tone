/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package projecttone;

import java.io.Serializable;

/**
 *
 * @author jobinrjohnson
 */
public class Instant implements Serializable {

    public String note;
    public int octave;
    public int duration_ms;
    public long instant;
    public int velocity = 120;
    public AudioManager.Instruments instrument;
}
