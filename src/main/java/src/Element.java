package src;

public class Element {
    Position _position = new Position(10,10);
    Element(int x, int y){
        _position.setY(y);
        _position.setX(x);
    }
    //methods

    void setPosition(Position position){
        _position.setY(position.getY());
        _position.setX(position.getX());
    }
    public Position moveUp() {return new Position(_position.getX(), _position.getY() - 1);}
    public Position moveDown() {
        return new Position(_position.getX(), _position.getY() + 1);
    }
    public Position moveRight() {
        return new Position(_position.getX()+1, _position.getY());
    }
    public Position moveLeft() {
        return new Position(_position.getX()-1, _position.getY());
    }

}
