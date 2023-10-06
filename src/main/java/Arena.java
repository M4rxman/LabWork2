import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;

public class Arena {
    Screen _screen;
    private int x = 10;
    private int y = 10;
    Hero _hero = new Hero(x,y);
    int _width;
    int _height;

    Arena(int width, int height, Screen screen){
        _width = width;
        _height = height;
        _screen = screen;

        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#806f41"));
        graphics.fillRectangle(new TerminalPosition(5, 2),
                new TerminalSize(_width, _height), ' ');

    }
    private void moveHero(Position position) {
        _hero.setPosition(position);
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

    public void draw(Screen screen) throws Exception {
        try {
            screen.clear();
            _hero.heriocDrawing(screen);
            screen.refresh();

            KeyStroke key = screen.readInput();
            processKey(key);

        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("End of the game");
        }
    }


}
