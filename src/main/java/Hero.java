import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {

    Position _position = new Position(10, 10);
    Hero(int x, int y){
        _position.setY(y);
        _position.setX(x);
    }
    //methods
    void setPosition(Position position){
        _position.setY(position.getY());
        _position.setX(position.getX());
    }

    public Position moveUp() {
        return new Position(_position.getX(), _position.getY() - 1);
    }
    public Position moveDown() {
        return new Position(_position.getX(), _position.getY() + 1);
    }
    public Position moveRight() {
        return new Position(_position.getX()+1, _position.getY());
    }
    public Position moveLeft() {
        return new Position(_position.getX()-1, _position.getY());
    }

    void heriocDrawing(Screen screen){
        screen.setCharacter(_position.getX(), _position.getY(), TextCharacter.fromCharacter('X')[0]);
    }



}
