import javax.sound.midi.*;

public class Synth {
    Synthesizer synthesizer = MidiSystem.getSynthesizer();
    final MidiChannel[] mc = synthesizer.getChannels();
    Instrument[] instr = synthesizer.getDefaultSoundbank().getInstruments();
    public Synth() throws MidiUnavailableException {
        synthesizer.open();
        synthesizer.loadInstrument(instr[0]);
    }

    public MidiChannel getChannel(int channel) {
        return mc[channel];
    }
}
