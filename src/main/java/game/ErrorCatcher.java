package game;

/**
 * Created by taras on 11/24/16.
 */
public class ErrorCatcher {

    public static void cantDisplayCellWrongMinesNear() {
        System.err.println("Failed to display a cell -- wrong mines near param.");
        System.exit(-2);
    }

    public static void graphicsFailure(Exception e) {
        System.err.println("GraphicsModule failed.");
        e.printStackTrace();
        System.exit(-3);
    }

}
