package game;

import graphics.GraphicsModule;
import graphics.LwjglGraphicsModule;
import keyboard.KeyboardHandleModule;
import keyboard.LwjglKeyboardHandleModule;
import mouse.Click;
import mouse.LwjglMouseHandleModule;
import mouse.MouseHandleModule;

import java.util.LinkedList;

/**
 * Created by taras on 11/24/16.
 */
public class Main {

    private static boolean endOfGame;
    private static GraphicsModule graphicsModule;
    private static KeyboardHandleModule keyboardModule;
    private static MouseHandleModule mouseModule;
    private static LinkedList<Click> clicksStack;
    private static GameField gameField;

    public static void main(String[] args) {
        initFields();
        while(!endOfGame){
            input();
            logic();

            graphicsModule.draw(gameField);
        }
        graphicsModule.destroy();
    }

    private static void initFields() {
        endOfGame = false;
        graphicsModule = new LwjglGraphicsModule();
        keyboardModule = new LwjglKeyboardHandleModule();
        mouseModule = new LwjglMouseHandleModule();
        gameField = new GameField();
        clicksStack = new LinkedList<>();
    }

    private static void input() {
        keyboardModule.update();
        mouseModule.update();

        clicksStack = mouseModule.getClicksStack();

        endOfGame = endOfGame || graphicsModule.isCloseRequested() || keyboardModule.wasEscPressed();
    }

    private static void logic() {
        for(Click click : clicksStack) {
            gameField.recieveClick(click);
        }
    }

}
