import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Piano extends JFrame {
    private Key[] keys = new Key[88];
    private JPanel keyPanel = new JPanel();
    private int octave = 3;
    public Piano(Synthesizer synth, int channelNumber) throws MidiUnavailableException {
        keyPanel.setLayout(new GridLayout(1, 88));
        this.setLayout(new BorderLayout());
        for (int i = (octave*12); i < (12*(octave+1)+1); i++) {
            keys[i] = new Key(synth, i, 127, channelNumber);
            keyPanel.add(keys[i]);
        }
        setTitle("Piano");
        setSize(800, 400);
        getContentPane().add(keyPanel);
        setFocusable(true);
        requestFocusInWindow();

        // add buttons to change octave
        JPanel octavePanel = new JPanel();
        JButton octaveUp = new JButton("Octave Up");
        octaveUp.addActionListener(e -> {
            this.octave++;
            for (Key key : this.keys) {
                if (key != null) {
                    key.setOctave(this.octave - 1);
                }
            }
        });

        JButton octaveDown = new JButton("Octave Down");
        octaveDown.addActionListener(e -> {
            this.octave--;
            for (Key key : this.keys) {
                if (key != null) {
                    key.setOctave(this.octave - 1);
                }
            }
        });

        octavePanel.add(octaveUp);
        octavePanel.add(octaveDown);
        getContentPane().add(octavePanel, BorderLayout.SOUTH);

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                // convert keyboard input to midi note assuming octave 3
                int note = 0;
                switch (e.getKeyChar()) {
                    case 'a': note = 0; break;
                    case 'w': note = 1; break;
                    case 's': note = 2; break;
                    case 'e': note = 3; break;
                    case 'd': note = 4; break;
                    case 'f': note = 5; break;
                    case 't': note = 6; break;
                    case 'g': note = 7; break;
                    case 'y': note = 8; break;
                    case 'h': note = 9; break;
                    case 'u': note = 10; break;
                    case 'j': note = 11; break;
                    case 'k': note = 12; break;
                    case 'o': note = 13; break;
                    case 'l': note = 14; break;
                    case 'p': note = 15; break;
                    case ';': note = 16; break;
                    case '\'': note = 17; break;
                }
                note += octave*12;
                keys[note].doClick();
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
}