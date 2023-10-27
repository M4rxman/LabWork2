package src;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element{

    Hero(int x, int y) {
        super(x, y);
    }

    void heriocDrawing(TextGraphics textGraphics){

        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(_position.getX(), _position.getY()), "H");
    }

}
