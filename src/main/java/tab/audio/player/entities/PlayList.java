package tab.audio.player.entities;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrey.tesluk on 05.08.2015.
 */
public class PlayList {

    List<Track> tracks = new ArrayList<Track>();

    private int currTrack = 0;

    public PlayList addTrack(Track track) {
        tracks.add(track);
        return this;
    }

    public Track currentTrack() {
        if (tracks.isEmpty()) {
            return null;
        }
        return tracks.get(currTrack % tracks.size());
    }

    public Track nextTrack() {
        currTrack = (currTrack + 1) % tracks.size();
        return currentTrack();
    }

    public Track prevTrack() {
        currTrack = (currTrack - 1) % tracks.size();
        return currentTrack();
    }

    public static PlayList fromFolder(String path) throws InvalidDataException, IOException, UnsupportedTagException {
        PlayList res = new PlayList();
        File dir = new File(path);
        if (!dir.exists() || !dir.isDirectory()) {
            throw new IllegalStateException(String.format("'%s' is not a dirrectory!", path));
        }

        File[] mp3s = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".mp3");
            }
        });

        for (File mp3 : mp3s) {
            res.addTrack(new Track(mp3));
        }

        System.out.println(String.format("Files found: %s", mp3s.length));

        return res;
    }

}
