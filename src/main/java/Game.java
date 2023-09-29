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

import java.io.IOException;

public class Game {
    private int x = 10;
    private int y = 10;
    Screen _screen;
    Terminal _terminal;
    TerminalSize _terminalSize;
    DefaultTerminalFactory _terminalFactory;
    Game(){
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

        switch(keyStroke){
            case KeyType.ArrowUp:
                y -= 1;
                break;
            case KeyType.ArrowDown:
                y += 1;
                break;
            case KeyType.ArrowRight:
                x += 1;
                break;
            case KeyType.ArrowLeft:
                x -= 1;
                break;
            case getCharacter() == 'q':
                _screen.close();
                break;
            case KeyType.EOF:

                break ;
        }
    }

    private void draw(Screen screen){
        try {
            screen.clear();
            screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
            screen.refresh();

            KeyStroke key = screen.readInput();
            processKey(key);



        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        while(true){
            draw(Game.this._screen);
        }


    }
}
