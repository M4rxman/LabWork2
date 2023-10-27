package src;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

//my humor is completely broken, I laughed at the word wall, why?
public class Wall extends Element{
    int _height;
    int _width;

    Wall(int width, int height) {
        super(24,4);
        _width = width;
        _height = height;
    }

// this is total fuckery
    /*boolean wallCollision(src.Position position){
        if((position.getX() >= _position.getX() && position.getX() <= _position.getX() + _width)
        && (position.getY() <= _position.getY() && position.getY() >= _position.getY() + _height)){
            return true;
        }
        return false;
    }*/

    void draw(TextGraphics textGraphics){
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        textGraphics.fillRectangle(new TerminalPosition(_position.getX(), _position.getY()), new
                TerminalSize(_width, _height), '#');
    }
}
