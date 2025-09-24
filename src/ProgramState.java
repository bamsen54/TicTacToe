
public enum ProgramState {

    MENU(0),
    PLAY(1),
    GAMEOVER(2);

    final int state;

    ProgramState(int state) {

        this.state = state;
    }
}
