package mouse;

import game.Constants;
import org.lwjgl.input.Mouse;

import java.util.LinkedList;

/**
 * Created by taras on 11/24/16.
 */
public class LwjglMouseHandleModule implements MouseHandleModule {

    LinkedList<Click> stack;

    public void update() {
        resetValues();

        while(Mouse.next()){
            if(Mouse.getEventButton()>=0 && Mouse.getEventButtonState()){
                int x = Mouse.getEventX()/ Constants.CELL_SIZE;
                int y = Mouse.getEventY()/Constants.CELL_SIZE;
                int button = Mouse.getEventButton();

                stack.add(new Click(x, y, button));
            }
        }
    }

    private void resetValues(){
        stack = new LinkedList<>();
    }

    public LinkedList<Click> getClicksStack() {
        return stack;
    }
}
