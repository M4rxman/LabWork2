package src;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

import static com.googlecode.lanterna.Symbols.DIAMOND;

public class Coin extends Element{
    TextGraphics coinGraphics;
    Coin(int x, int y) {
        super(x, y);
    }

    public Coin retrieveCoins(Position position, List<Coin> coins){
        //System.out.println(_position.getX() + " " + _position.getY());

        if(position.equals(_position)){
            Coin foundCoin = new Coin(_position.getX(), _position.getY());
            System.out.println("Lord havens a coin");
            return foundCoin;
        }
        return null;
    }

    public void takeCoin(Coin foundCoin, List<Coin> coins, TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        coins.remove(foundCoin);
        System.out.println("You took a coin");


    }

    public void setCoinGraphics(TextGraphics _coinGraphics){
        coinGraphics = _coinGraphics;
    }
    void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(_position.getX(), _position.getY()), String.valueOf(DIAMOND));
    }
}
