package userInterfaces;

import defaults.FilesPath;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {

    private String soundName;
    private File audioFile;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;

    public Sounds(String soundName) {
        this.soundName = soundName;
        audioFile = new File(FilesPath.graphicsPath.soundsPath + "/" + soundName);
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void playLoop() {
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playOne() {
        audioClip.loop(1);
    }

    public void stopAudio() {
        audioClip.stop();
    }
}
