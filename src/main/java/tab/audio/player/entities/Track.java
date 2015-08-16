package tab.audio.player.entities;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.IOException;

/**
 * Created by andrey.tesluk on 05.08.2015.
 */
public class Track {

    private static final String UNKNOWN_ARTIST = "Unknown Artist";
    private static final String NO_TITLE = "No Title";
    private static final String UNKNOWN_TRACK = "Unknown Track";

    private File file;
    private Mp3File mp3File;

    public Track(File f) throws InvalidDataException, IOException, UnsupportedTagException {
        this.file = f;
        this.mp3File = new Mp3File(f);
    }

    public File getFile() {
        return file;
    }

    public String location() {
        return file.getAbsolutePath();
    }

    public String getArtist() {
        if (mp3File.hasId3v2Tag() && mp3File.getId3v2Tag().getArtist() != null) {
            return mp3File.getId3v2Tag().getArtist();
        }
        if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getArtist() != null) {
            return mp3File.getId3v1Tag().getArtist();
        }
        return UNKNOWN_ARTIST;
    }

    public String getTitle() {
        if (mp3File.hasId3v2Tag() && mp3File.getId3v2Tag().getTitle() != null) {
            return mp3File.getId3v2Tag().getTitle();
        }
        if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getTitle() != null) {
            return mp3File.getId3v1Tag().getTitle();
        }
        return NO_TITLE;
    }

    public String getTrack() {
        if (mp3File.hasId3v2Tag() && mp3File.getId3v2Tag().getTrack() != null) {
            return mp3File.getId3v2Tag().getTrack();
        }
        if (mp3File.hasId3v1Tag() && mp3File.getId3v1Tag().getTrack() != null) {
            return mp3File.getId3v1Tag().getTrack();
        }
        return UNKNOWN_TRACK;
    }

    public int getBitrate(){
        return mp3File.getBitrate();
    }

    public String getBitrateString(){
        return String.format("%s kbps", getBitrate());
    }

    public long getDuration() {
        return mp3File.getLengthInSeconds();
    }

    public String getDurationString() {
        long seconds = getDuration();
        if (seconds > 3600) {
            return String.format("%02d:%02d:%02d", seconds / 3600, (seconds / 60) % 60, seconds % 60);
        }
        return String.format("%02d:%02d", (seconds / 60) % 60, seconds % 60);
    }

    public String toString() {
        return String.format("%s - %s (%s) [%s]", getArtist(), getTitle(), getDurationString(), getBitrateString());
    }
}
