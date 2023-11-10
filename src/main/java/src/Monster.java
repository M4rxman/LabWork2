package src;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {
    TextGraphics monsterGraphics;
    char monsterCharacter = 'M';

    Monster(int x, int y) {
        super(x, y);
    }

    void draw(TextGraphics textGraphics) {
        monsterGraphics = textGraphics;
        textGraphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(_position.getX(), _position.getY()), String.valueOf(monsterCharacter));
    }

    Position move() {
        // Implement the logic to move the monster (e.g., random movement)
        // You can use the existing Element methods like moveUp(), moveDown(), moveRight(), and moveLeft()
        // and randomly choose one direction to move the monster.
        // Make sure to check boundaries and avoid collisions with walls and other elements.
        // Return the new position after moving.
        return  _position;
    }
}