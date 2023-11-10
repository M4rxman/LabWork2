package src;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import static com.googlecode.lanterna.Symbols.FACE_BLACK;

public class Hero extends Element{
    TextGraphics heroicGraphics;
    Character heroCharacter = FACE_BLACK;
    String heroicColor;
    Hero(int x, int y) {
        super(x, y);
    }
    // maximal colour in decimal is 16777215
    public void heroFoundCoin(){
        Random rand = new Random();
        heroicColor= "#" + String.format("%06X", 777000 + rand.nextInt(16000215));
        //heroicGraphics.setForegroundColor(TextColor.Factory.fromString(heroicColor));
    }

    void heriocDrawing(TextGraphics textGraphics){
        heroicGraphics = textGraphics;
        textGraphics.setForegroundColor(TextColor.Factory.fromString(heroicColor));
        textGraphics.enableModifiers(SGR.BOLD);
        textGraphics.putString(new TerminalPosition(_position.getX(), _position.getY()), String.valueOf(heroCharacter));
    }

}
