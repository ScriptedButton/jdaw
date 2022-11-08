import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Piano extends JPanel {
    private Synth synth = new Synth();
    private Key[] keys = new Key[88];
    private JPanel keyPanel = new JPanel();

    private int octave = 3;
    public Piano() throws MidiUnavailableException {
        keyPanel.setLayout(new GridLayout(1, 88));
        this.setLayout(new BorderLayout());
        for (int i = (octave*12); i < (12*(octave+1)+1); i++) {
            keys[i] = new Key(synth, i, 127);
            keyPanel.add(keys[i]);
        }
        add(keyPanel);
        setFocusable(true);
        requestFocusInWindow();
    }

    void noteClicked() {
        System.out.println("clicked note.");
    }
}