package tab.audio.player;

import maryb.player.Player;
import maryb.player.PlayerEventListener;
import tab.audio.player.entities.PlayList;

/**
 * Created by andrey.tesluk on 05.08.2015.
 */
public class GlobalPlayer implements PlayerEventListener {

    private static GlobalPlayer globalPlayer;

    private Player player = new Player();
    private PlayList playList = new PlayList();

    private GlobalPlayer() {
        player.setListener(this);
        player.setCurrentVolume(0.7f);
    }

    public static GlobalPlayer getPlayer() {
        if (globalPlayer == null) {
            globalPlayer = new GlobalPlayer();
        }
        return globalPlayer;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

    public void play() {
        player.setSourceLocation(playList.currentTrack().location());
        player.play();

        // TODO remove
        System.out.println(String.format("Play track: %s", playList.currentTrack()));
    }

    public void endOfMedia() {
        System.out.println("end of media");
        playList.nextTrack();
        play();
    }

    public void stateChanged() {
    }

    public void buffer() {
        System.out.println("buffer");
    }
}


