package keyboard;

import org.lwjgl.input.Keyboard;

/**
 * Created by taras on 11/24/16.
 */
public class LwjglKeyboardHandleModule implements KeyboardHandleModule {

    private boolean wasEscPressed;

    @Override
    public void update() {
        resetValues();

        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                switch(Keyboard.getEventKey()){
                    case Keyboard.KEY_ESCAPE:
                        wasEscPressed = true;
                        break;
                }
            }
        }
    }

    private void resetValues() {
        wasEscPressed = false;
    }

    @Override
    public boolean wasEscPressed() {
        return wasEscPressed;
    }
}
