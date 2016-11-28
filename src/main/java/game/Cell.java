package game;

/**
 * Created by taras on 11/24/16.
 */
public class Cell {

    private CellState state;
    private boolean isHidden;
    private boolean isMarked;

    public Cell(boolean isMine){
        this.isHidden = true;
        this.isMarked = false;
        this.state = isMine ? CellState.MINE : CellState.EMPTY;
    }

    public ClickResult recieveClick(int button){
       if(isHidden){
            if(button == 0 && !this.isMarked){
                if(this.state == CellState.MINE){
                    this.state = CellState.EXPLOSED;
                    return ClickResult.EXPLOSED;
                }
                if(this.state == CellState.EMPTY){
                    this.isHidden = false;
                    return ClickResult.OPENED;
                }
            } else if(button == 1){
                this.isMarked = !this.isMarked;
            }
       }
        return ClickResult.REGULAR;
    }

    public boolean isHidden(){
        return isHidden;
    }

    public boolean isMarked(){
        return isMarked;
    }

    public CellState getState(){
        return state;
    }

    public void show(){
        this.isHidden = false;
    }
}
