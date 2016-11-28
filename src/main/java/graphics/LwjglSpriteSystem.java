package graphics;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by taras on 11/24/16.
 */
public class LwjglSpriteSystem {

    enum LwjglSprite {

        ZERO("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
        SEVEN("7"), EIGHT("8"), SPACE("space"), MINE("bomb"), FLAG("flag"), BROKEN_FLAG("broken_flag"),
        EXPLOSION("explosion");

        private Texture texture;

        LwjglSprite(String texturename) {
            try {
                this.texture = TextureLoader.getTexture("PNG", new FileInputStream(new File("resources/" + texturename + ".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public Texture getTexture() {
            return this.texture;
        }
    }

    public static final LwjglSprite[] spriteByNumber = {
            LwjglSprite.ZERO,
            LwjglSprite.ONE,
            LwjglSprite.TWO,
            LwjglSprite.THREE,
            LwjglSprite.FOUR,
            LwjglSprite.FIVE,
            LwjglSprite.SIX,
            LwjglSprite.SEVEN,
            LwjglSprite.EIGHT
    };

}
