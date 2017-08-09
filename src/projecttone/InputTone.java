/*
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package projecttone;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiChannel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jobinrjohnson
 */
public class InputTone extends javax.swing.JFrame {

    AudioManager am;
    ArrayList<Instant> instants;
    HashMap<String, Instant> waiting_for_duration;
    InstantCounter counter = null;

    class InstantCounter extends Thread {

        public long timer = 0;
        public boolean terminated = false;

        public void stopTimer() {
            terminated = true;
        }

        public void resetTimer() {
            timer = 0;
        }

        public long getInstant() {
            return timer;
        }

        @Override
        public void run() {
            super.run();
            while (!terminated) {
                try {
                    sleep(1);
                    timer++;
                } catch (InterruptedException ex) {
                    Logger.getLogger(InputTone.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    /**
     * Creates new form InputTone
     */
    
    public String keyToTone(int key){
        String Tone="";
        switch(key){
            case 81:
                Tone=StandardSet.Notes[0];
                break;
            case 87:
                Tone=StandardSet.Notes[1];
                break;
            case 69:
                Tone=StandardSet.Notes[2];
                break;
            case 82:
                Tone=StandardSet.Notes[3];
                break;
            case 84:
                Tone=StandardSet.Notes[4];
                break;
            case 89:
                Tone=StandardSet.Notes[5];
                break;
            case 85:
                Tone=StandardSet.Notes[6];
                break;
            case 73:
                Tone=StandardSet.Notes[7];
                break;
            case 79:
                Tone=StandardSet.Notes[8];
                break;
            case 80:
                Tone=StandardSet.Notes[9];
                break;
            case 91:
                Tone=StandardSet.Notes[10];
                break;
            case 93:
                Tone=StandardSet.Notes[11];
                break;

        }
        return(Tone);
    }
    
    public int keyToNumber(int key){
        int Tone=0;
        switch(key){
            case 81:
                Tone=0;
                break;
            case 87:
                Tone=1;
                break;
            case 69:
                Tone=2;
                break;
            case 82:
                Tone=3;
                break;
            case 84:
                Tone=4;
                break;
            case 89:
                Tone=5;
                break;
            case 85:
                Tone=6;
                break;
            case 73:
                Tone=7;
                break;
            case 79:
                Tone=8;
                break;
            case 80:
                Tone=9;
                break;
            case 91:
                Tone=10;
                break;
            case 93:
                Tone=11;
                break;

        }
        return(Tone);
    }
    
    public InputTone() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        am = new AudioManager();
        final MidiChannel[] midiChannels = AudioManager.midiChannels;

        instants = new ArrayList<Instant>();
        waiting_for_duration = new HashMap<String, Instant>();

        AudioManager.Instruments instrument = AudioManager.Instruments.PIANO;
        int mInstrument = 0;
        int strength = 90;

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (counter == null) {
                    counter = new InstantCounter();
                    counter.start();
                }
                Instant i = new Instant();
                i.instrument = instrument;
                i.instant = counter.getInstant();
                i.octave = 5;
                i.velocity = strength;
                i.duration_ms = 2;
                i.note = "C#"; //TODO - Change here
                instants.add(i);
                waiting_for_duration.put(i.note, i);

                jLabel1.setText("Key : " + keyToTone(key));
                midiChannels[mInstrument].noteOn(keyToNumber(key)+(5*12), strength);
            }

            public void keyReleased(KeyEvent e) {
                int key = e.getKeyCode();
                String note = "C#"; //TODO - Change here
                Instant i = waiting_for_duration.get(note);
                i.duration_ms = (int) (counter.timer - i.instant);
                midiChannels[mInstrument].noteOff(key);
            }
        });

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("No key pressed");

        jButton1.setText("Save");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Discard");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Instant[] castedInstants = instants.toArray(new Instant[0]);
        counter.stopTimer();
        counter = null;
        String name = JOptionPane.showInputDialog("Save as");
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(rootPane, "Empty String");
            return;
        }
        new Tone().saveTone(castedInstants, name.trim());
        instants.clear();
        waiting_for_duration.clear();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        instants.clear();
        waiting_for_duration.clear();
        counter = null;
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InputTone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputTone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputTone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputTone.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputTone().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
