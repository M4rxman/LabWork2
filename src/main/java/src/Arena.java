package src;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    Screen _screen;
    private int x = 10;
    private int y = 10;
    Hero _hero = new Hero(x,y);
    int _width;
    int _height;
    private List<Wall> _walls;
    private List<Coin> _coins;


    Arena(int width, int height, Screen screen){
        _width = width;
        _height = height;
        _screen = screen;
        this._walls = createWalls(2, 17);
        this._coins = createCoins();
    }
    private void moveHero(Position position) {
        retrieveCoins(position, _coins);
        boolean collision = false;
        _walls.get(0)._height = _walls.get(_walls.size() - 1)._height;
        _walls.get(0)._width  = _walls.get(_walls.size() - 1)._width;
       for (Wall wall : _walls){
           if(wall._position.equals(position)){
               collision = true;
               System.out.println("Collision!!!");
               break;
           }
       }
       if(!collision){
           _hero.setPosition(position);
       }

    }

    private void retrieveCoins(Position position, List<Coin> coins) {
        _coins.forEach(coin -> coin.retrieveCoins(position, _coins));
    }

    public void processKey(KeyStroke keyStroke) throws IOException {
        System.out.println(keyStroke);

        switch (keyStroke.getKeyType()) {
            case ArrowUp:
                moveHero(_hero.moveUp());
                break;
            case ArrowDown:
                moveHero(_hero.moveDown());
                break;
            case ArrowRight:
                moveHero(_hero.moveRight());
                break;
            case ArrowLeft:
                moveHero(_hero.moveLeft());
                break;
            case EOF:
                throw new IOException("EOF exception");
            default:
                if (keyStroke.getCharacter() == 'q') {
                    _screen.close();
                }
        }
    }

    public void draw(TextGraphics textGraphics) throws Exception {
        try {
            _screen.clear();

            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#08301f"));
            textGraphics.fillRectangle(new TerminalPosition(25, 4),
                    new TerminalSize(_width, _height), ' ');

            for (Wall wall : _walls)
                wall.draw(textGraphics);

            _hero.heriocDrawing(textGraphics);

            for (Coin coin : _coins)
                coin.draw(textGraphics);
            _screen.refresh();

            KeyStroke key = _screen.readInput();
            processKey(key);

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("End of the game");
        }
    }

    private List<Wall> createWalls(int width, int height) {
        List<Wall> walls = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }
        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
                    }
        return walls;
    }
    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(_width - 2) + 1,
                    random.nextInt(_height - 2) + 1));
        return coins;
    }



}
