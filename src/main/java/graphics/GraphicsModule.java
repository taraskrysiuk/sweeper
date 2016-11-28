package graphics;

import game.GameField;

/**
 * Created by taras on 11/24/16.
 */
public interface GraphicsModule {

    void draw(GameField field);
    void destroy();
    boolean isCloseRequested();

}
