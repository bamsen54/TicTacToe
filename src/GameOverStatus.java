
public class GameOverStatus {

    public char  status;
    public int[] start;
    public int[] end;

    // status
    //  'b' - blue won
    //  'p' - pink won
    //  '-' - draw
    //  ' ' - game not over

    // start / end coordinates of the star end end of the tree in row on the board
    GameOverStatus(char _status, int[] _start, int[] _end) {

        status = _status;
        start  = _start;
        end    = _end;
    }

    GameOverStatus(char _status) {

        this.status = _status;

        this.start = new int[] {};
        this.end   = new int[] {};
    }

    public String toString() {

        String str = "status: " + this.status + "\n";

        str += " start: " + start[0] + " " + start[1] + "\n";
        str += " end: "   +   end[0] + " " +   end[1] + "\n";

        return str;
    }
}
