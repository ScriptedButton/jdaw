import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.awt.*;

public class MidiChannelPanel extends JPanel {
    private Synthesizer synth;
    private MidiChannel channel;
    private int channelNumber;
    private JButton mute = new JButton("Mute");
    private JButton solo = new JButton("Solo");
    private JButton open = new JButton("Open");
    private final JList<javax.sound.midi.Instrument> instrumentList = new JList<>();

    public MidiChannelPanel (Synthesizer synth, int channelNum) {
        super();
        setLayout(new GridLayout(5, 1));
        this.synth = synth;
        this.channel = synth.getChannels()[channelNum];
        this.channelNumber = channelNum;
        initComponents();

        mute.addActionListener(e -> {
            if (this.channel.getMute()) {
                this.channel.setMute(false);
                mute.setText("Mute");
            } else {
                this.channel.setMute(true);
                mute.setText("Unmute");
            }
        });

        solo.addActionListener(e -> {
            if (this.channel.getSolo()) {
                this.channel.setSolo(false);
                solo.setText("Solo");
            } else {
                this.channel.setSolo(true);
                solo.setText("Unsolo");
            }
        });

        open.addActionListener(e -> {
            try {
                JFrame piano = new Piano(this.synth, this.channelNumber);
                piano.setVisible(true);
            } catch (MidiUnavailableException ex) {
                throw new RuntimeException(ex);
            }
        });

        instrumentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                this.channel.programChange(instrumentList.getSelectedIndex());
            }
        });
    }
    void initComponents () {
        instrumentList.setListData(synth.getAvailableInstruments());
        add(new JLabel ("Channel " + channelNumber, SwingConstants.CENTER));
        add(new JScrollPane(instrumentList));
        add(mute);
        add(solo);
        add(open);
    }
}
