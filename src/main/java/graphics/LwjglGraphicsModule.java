package graphics;

import game.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by taras on 11/24/16.
 */
public class LwjglGraphicsModule implements GraphicsModule {

    private LwjglSpriteSystem spriteSystem;

    public LwjglGraphicsModule() {
        initOpenGL();
        spriteSystem = new LwjglSpriteSystem();
    }

    private void initOpenGL() {
        try {
            Display.setDisplayMode(new DisplayMode(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
            Display.setTitle(Constants.SCREEN_NAME);
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Constants.SCREEN_WIDTH, 0, Constants.SCREEN_HEIGHT, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glClearColor(1, 1, 1, 1);
    }

    public void draw(GameField field) {
        glClear(GL_COLOR_BUFFER_BIT);
        for(int i = 0; i < Constants.COUNT_CELLS_X; i++) {
            for (int j = 0; j < Constants.COUNT_CELLS_Y; j++) {
                drawSprite(Constants.CELL_SIZE*i, Constants.CELL_SIZE*j, calculateSprite(field.getCell(i,j), field.getMinesNear(i,j)) );
            }
        }
        Display.update();
        Display.sync(60);
    }

    private LwjglSpriteSystem.LwjglSprite calculateSprite(Cell cell, int minesNear) {
        if(cell.isMarked()){
            if(!cell.isHidden() && (cell.getState() != CellState.MINE)){
                return LwjglSpriteSystem.LwjglSprite.BROKEN_FLAG;
            }
            return LwjglSpriteSystem.LwjglSprite.FLAG;
        }else if(cell.isHidden()){
            return LwjglSpriteSystem.LwjglSprite.SPACE;
        }else{
            switch (cell.getState()){
                case EXPLOSED:
                    return LwjglSpriteSystem.LwjglSprite.EXPLOSION;
                case MINE:
                    return LwjglSpriteSystem.LwjglSprite.MINE;
                case EMPTY:
                default:
                    if(minesNear>8 || minesNear<0){
                        ErrorCatcher.cantDisplayCellWrongMinesNear();
                    }
                    return  LwjglSpriteSystem.spriteByNumber[minesNear];
            }
        }
    }

    private void drawSprite(int x, int y, LwjglSpriteSystem.LwjglSprite sprite) {
        sprite.getTexture().bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0,0);
        glVertex2f(x,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,0);
        glVertex2f(x+ Constants.CELL_SIZE,y+ Constants.CELL_SIZE);
        glTexCoord2f(1,1);
        glVertex2f(x+ Constants.CELL_SIZE, y);
        glTexCoord2f(0,1);
        glVertex2f(x, y);
        glEnd();
    }

    public void destroy() {
        Display.destroy();
    }

    public boolean isCloseRequested() {
        return Display.isCloseRequested();
    }
}
