package mouse;

import java.util.LinkedList;

/**
 * Created by taras on 11/24/16.
 */
public interface MouseHandleModule {

    void update();
    LinkedList<Click> getClicksStack();

}
