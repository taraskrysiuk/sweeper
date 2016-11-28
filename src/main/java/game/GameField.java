package game;

import mouse.Click;

import java.util.Random;

/**
 * Created by taras on 11/24/16.
 */
public class GameField {

    private Cell[][] theField;

    private short[][] minesNear;

    public GameField(){
        theField = new Cell[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];
        minesNear = new short[Constants.COUNT_CELLS_X][Constants.COUNT_CELLS_Y];

        Random rnd = new Random();

        for(int x = 0; x < Constants.COUNT_CELLS_X; x++){
            for(int y = 0; y < Constants.COUNT_CELLS_Y; y++){
                boolean isMine = rnd.nextInt(100) < Constants.SWAP_CHANCE_OF_MINE;

                if(isMine){
                    theField[x][y] = new Cell(true);

                    for(int i = -1; i < 2; i++){
                        for(int j = -1; j < 2; j++){
                            if( (x+i >= 0) && (x+i < Constants.COUNT_CELLS_X) && (y+j >= 0) && (y+j < Constants.COUNT_CELLS_Y)){
                                minesNear[x+i][y+j]++;
                            }
                        }
                    }
                }else{
                    theField[x][y] = new Cell(false);
                }
            }
        }
    }

    public Cell getCell(int x, int y) {
        return theField[x][y];
    }

    public int getMinesNear(int x, int y) {
        return minesNear[x][y];
    }

    public void recieveClick(Click click) {
        ClickResult clickResult = this.theField[click.x][click.y].recieveClick(click.button);
        switch(clickResult) {
            case EXPLOSED:
                showAll();
                break;
            case OPENED:
                if(getMinesNear(click.x, click.y) == 0) {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if ((click.x + i >= 0) && (click.x + i < Constants.COUNT_CELLS_X)
                                    && (click.y + j >= 0) && (click.y + j < Constants.COUNT_CELLS_Y)) {
                                Click pseudoClick = new Click(click.x + i, click.y + j, click.button);
                                recieveClick(pseudoClick);
                            }
                        }
                    }
                }
                break;
            case REGULAR:
            default:
                //ignore
                break;
        }
    }

    public void show(int x, int y){
        theField[x][y].show();
    }


    public void showAll() {
        for(Cell[] row : theField){
            for(Cell cell : row){
                cell.show();
            }
        }
    }

}
