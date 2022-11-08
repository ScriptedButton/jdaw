import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;

public class GUI {
    public static void main(String[] args) throws MidiUnavailableException {
        JFrame frame = new JFrame("jdaw - Java Digital Audio Workstation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 300);
        frame.add(new Piano(), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
