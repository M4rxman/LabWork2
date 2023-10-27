package src;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class Coin extends Element{
    Coin(int x, int y) {
        super(x, y);
    }

    public void retrieveCoins(Position position, List<Coin> coins){
        System.out.println(_position.getX());
        System.out.println(_position.getY());
        if(position.equals(_position)){
            Coin foundCoin = new Coin(_position.getX(), _position.getY());
            coins.remove(foundCoin);

        }
    }
    void draw(TextGraphics textGraphics){
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFF00"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(_position.getX(), _position.getY()), "0");
    }
}
