package tab.audio.player.entities;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by andrey.tesluk on 07.08.2015.
 */
public class PlayBackHistory {

    private ArrayDeque<Track> history = new ArrayDeque<Track>();
    private int historySize;
    private int currPos = 0;

    public PlayBackHistory() {
        this(10);
    }

    public PlayBackHistory(int historySize) {
        this.historySize = historySize;
    }

    public void addPrevTrack(Track curr) {
        removeBefore(currPos);
        history.addFirst(curr);
        currPos = 0;
        removeAfter(historySize - 1);
    }

    public void addNextTrack(Track curr) {
        removeAfter(currPos);
        history.addLast(curr);
        removeBefore(history.size() - historySize);
        currPos = history.size() - 1;
    }

    private void removeBefore(int pos) {
        for (int i = 0; i < pos; i++) {
            history.removeFirst();
        }
    }

    private void removeAfter(int pos) {
        for (int i = 0; i < history.size() - pos - 1; i++) {
            history.removeLast();
        }
    }

    @Override
    public String toString() {
        List<String> tracksList = new ArrayList<String>();
        Iterator<Track> iterator = history.iterator();
        for (int i = 0; i < history.size(); i++) {
            tracksList.add(String.format("%s%s) %s", i == currPos ? "> " : "", i + 1, iterator.next()));
        }
        return String.join("\n", tracksList);
    }

    public static void main(String[] args) throws InvalidDataException, IOException, UnsupportedTagException {
        PlayBackHistory his = new PlayBackHistory(5);
        PlayList list = PlayList.fromFolder("D:\\Temp\\mp3");
        System.out.println(his);
        his.addNextTrack(list.nextTrack());
        his.addNextTrack(list.nextTrack());
        System.out.println(his);
        System.out.println();

        his.addPrevTrack(list.nextTrack());
        System.out.println(his);

        for (int i = 0; i < 8; i++) {
            his.addPrevTrack(list.nextTrack());
            System.out.println();
            System.out.println(his);
        }
    }
}
