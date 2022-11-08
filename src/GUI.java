import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private Synthesizer synth;
    private MidiChannel[] channels;
    private JMenuBar menuBar = new JMenuBar();
    private final JMenu channelsMenu = new JMenu("Channels");
    private final JMenuItem addChannel = new JMenuItem("Add Channel");

    public GUI () throws MidiUnavailableException {
        super();
        JTabbedPane channelPanel = new JTabbedPane();
        synth = MidiSystem.getSynthesizer();
        synth.open();
        channelPanel.addTab("Channel 1", new MidiChannelPanel(synth, 1));

        add(channelPanel);
        channelsMenu.add(addChannel);
        menuBar.add(channelsMenu);
        setJMenuBar(menuBar);
        setSize(800, 400);
        setVisible(true);

        addChannel.addActionListener(e -> {
            channelPanel.addTab("Channel " + (channelPanel.getTabCount()+1), new MidiChannelPanel(synth, channelPanel.getTabCount()+1));
            channelPanel.revalidate();
            channelPanel.repaint();
        });
    }

    public static void main(String[] args) throws MidiUnavailableException {
        GUI gui = new GUI();
    }
}
