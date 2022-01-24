package testing;

import player.AbstractPlayer;
import player.Bot;
import player.Player;

public class TestPlayer {
    
    public AbstractPlayer InitTestPlayer(){
        AbstractPlayer  player = new Player(0, null, null, null);
        return player;
    }
    
    public AbstractPlayer InitTestBot(){
        AbstractPlayer bot = new Bot(1, null, null, null);
        return bot;
    }
}
