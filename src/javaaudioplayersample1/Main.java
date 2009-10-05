package javaaudioplayersample1;

import maryb.player.Player;

/**
 *
 * @author cy6ergn0m
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main( String[] args ) {
        Player p = new Player();
        p.setCurrentVolume( 0.7f );
        p.setSourceLocation( "/set/path/to/any/mp3/file/here" );
        p.play();
    }

}
