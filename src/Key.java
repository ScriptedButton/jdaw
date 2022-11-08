import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Key extends JButton {
    private Synthesizer synth;
    private int note;
    private int velocity;

    public Key(Synthesizer synth, int note, int velocity, int channelNumber) throws MidiUnavailableException {
        this.note = note;
        this.velocity = velocity;
        this.synth = synth;
        this.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                synth.getChannels()[channelNumber].noteOn(Key.this.note, velocity);
            }
        });
        this.setText(getNoteName());
        if (getNoteName().contains("#")) {
            this.setBackground(Color.BLACK);
            this.setForeground(Color.WHITE);
        } else {
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK);
        }
    }

    public int getNote() {
        return note;
    }

    public int getVelocity() {
        return velocity;
    }

    public String getNoteName() {
        String[] notes = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        return notes[note % 12] + (note / 12);
    }

    public void setOctave(int octave) {
        this.note = (octave*12) + (note % 12);
        this.setText(getNoteName());
    }
}
