
import static com.raylib.Raylib.*;
import static com.raylib.Colors.*;

import static java.io.IO.*;

public static final Game main_game = new Game();

// ai
static boolean blue_is_ai = false;
static boolean pink_is_ai = true;

ProgramState program_state = ProgramState.MENU;

void main() {

    InitWindow( 1024, 720, "TicTacToe");
    SetTargetFPS(60);

    Gui.init_gui(); // has to be called after InitWindow()

    while( !WindowShouldClose() ) {

        BeginDrawing();
        ClearBackground( BLACK );

        Gui.draw_board();
        Gui.draw_discs( main_game );
        Gui.draw_win( main_game );

        EndDrawing();

        if( IsMouseButtonPressed( 0 ) )
            mouse_pressed();

        ai_play();
    }
}

public static void mouse_pressed() {

    int[] mouse_position_as_board_coordinates = Util.get_mouse_position_as_board_coordinates();

    final int col_clicked = mouse_position_as_board_coordinates[0];
    final int row_clicked = mouse_position_as_board_coordinates[1];

    boolean clicked_inside_board = col_clicked >= 0 && col_clicked < 3 && row_clicked >= 0 && row_clicked < 3;

    if( !clicked_inside_board )
        return;

    if( main_game.board[row_clicked][col_clicked] != ' ' )
        return;

    int[] board_coordinates = {col_clicked, row_clicked};

    Moves.make_move( main_game , Util.coordinates_to_move_number( board_coordinates ));
}

public static void ai_play() {

    if( GameOver.is_game_over( main_game ) )
        return;

    if( main_game.turn == 'b' && blue_is_ai ) {

        int best_move = AI.get_best_move( main_game );

        if( best_move >= 0)
            Moves.make_move(main_game, best_move);
    }

    else if( main_game.turn == 'p' && pink_is_ai ) {

        int best_move = AI.get_best_move( main_game );

        if( best_move >= 0)
            Moves.make_move(main_game, best_move);
    }
}

