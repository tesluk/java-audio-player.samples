package tab.audio.player;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import tab.audio.player.entities.PlayList;

import java.io.IOException;

/**
 * @author cy6ergn0m
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, InvalidDataException, IOException, UnsupportedTagException {
        GlobalPlayer player = GlobalPlayer.getPlayer();

        player.setPlayList(PlayList.fromFolder("D:\\Temp\\mp3"));

        player.play();

//        Player p2 = new Player();
//        p2.setSourceLocation("d:\\Temp\\mp3\\no.mp3");
//        Thread.sleep(500);
//        p2.setCurrentVolume(0.7f);
//        p2.play();

        Thread.sleep(3000);
        player.endOfMedia();

        Thread.sleep(3000);
        player.endOfMedia();

        Thread.sleep(3000);
        player.endOfMedia();
    }

}
