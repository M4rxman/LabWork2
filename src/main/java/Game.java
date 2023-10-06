import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Screen _screen;
    Terminal _terminal;
    TerminalSize _terminalSize;
    DefaultTerminalFactory _terminalFactory;
    Arena _arena;

    Game() {
        try {
            _terminal = new DefaultTerminalFactory().createTerminal();
            _terminalSize = new TerminalSize(40, 20);
            _terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(_terminalSize);

            _screen = new TerminalScreen(_terminal);
            _screen.setCursorPosition(null); // we don't need a cursor
            _screen.startScreen(); // screens must be started
            _screen.doResizeIfNecessary(); // resize screen ifnecessary
            _arena = new Arena(30, 16, _screen);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        _arena.processKey(key);
    }
    private void draw(Screen screen) throws Exception {
        screen.clear();
        _arena.draw(screen);
        screen.refresh();
    }

    /*
    */
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

}
