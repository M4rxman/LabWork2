import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TabBehaviour;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.EOFException;
import java.io.IOException;

public class Game {
    private int x = 10;
    private int y = 10;
    Hero _hero = new Hero(x,y);
    Screen _screen;
    Terminal _terminal;
    TerminalSize _terminalSize;
    DefaultTerminalFactory _terminalFactory;

    Game() {
        try {
            _terminal = new DefaultTerminalFactory().createTerminal();
            _terminalSize = new TerminalSize(40, 20);
            _terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(_terminalSize);

            _screen = new TerminalScreen(_terminal);
            _screen.setCursorPosition(null); // we don't need a cursor
            _screen.startScreen(); // screens must be started
            _screen.doResizeIfNecessary(); // resize screen ifnecessary


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke keyStroke) throws IOException {
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

    private void draw(Screen screen) throws Exception {
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

    public void run() {
        while (true) {
            try {
                draw(Game.this._screen);
            }
            catch (Exception e){
                break;
            }
        }
    }

    private void moveHero(Position position) {
        _hero.setPosition(position);
    }

}
