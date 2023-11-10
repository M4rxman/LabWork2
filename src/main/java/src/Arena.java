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
    private List<Monster> _monsters;


    Arena(int width, int height, Screen screen){
        _width = width;
        _height = height;
        _screen = screen;
        this._walls = createWalls(2, 17);
        this._coins = createCoins();
        this._monsters = createMonsters();
    }
    private void moveHero(Position position) {

        for(Coin coin : _coins){
            if(coin.retrieveCoins(position, _coins) != null){
                _hero.heroFoundCoin();
                coin.takeCoin(coin, _coins, coin.coinGraphics);
                break;
            }
        }

        boolean collision = false;
        _walls.get(0)._height = _walls.get(_walls.size() - 1)._height;
        _walls.get(0)._width  = _walls.get(_walls.size() - 1)._width;

       for (Wall wall : _walls){
           if(wall._position.equals(position) || wall.wallCollision(position)){
               collision = true;
               System.out.println("Collision!!!");
               break;
           }
       }
       if(!collision){
           _hero.setPosition(position);
       }

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

            for (Coin coin : _coins){
                coin.draw(textGraphics);
                coin.setCoinGraphics(textGraphics);
            }

            for (Monster monster : _monsters) {
                monster.draw(textGraphics);
            }

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

    private List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 3; i++) { // Create three monsters as an example
            monsters.add(new Monster(random.nextInt(_width - 2) + 1, random.nextInt(_height - 2) + 1));
        }
        return monsters;
    }

    public void moveMonsters() {
        for (Monster monster : _monsters) {
            Position newPosition = monster.move();
            // Implement collision detection with walls or other elements here
            // If the new position is valid, update the monster's position
            // Otherwise, the monster stays in the current position
            monster.setPosition(newPosition);
        }
    }

    public boolean verifyMonsterCollisions() {
        for (Monster monster : _monsters) {
            if (_hero.getPosition().equals(monster.getPosition())) {
                // Handle the collision here (e.g., game termination and message)
                System.out.println("Hero touched a Monster! Game over!");
                // You can implement game over logic or other actions here
                return true;
            }
        }
        return false;
    }

}
