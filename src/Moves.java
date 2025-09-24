
import java.util.ArrayList;

public class Moves {

    public static void make_move(Game game, int move) {

        final int[] move_coordinates = Util.move_number_to_coordinates( move );

        final int col = move_coordinates[0];
        final int row = move_coordinates[1];

        game.board[row][col] = game.turn;

        if( game.turn == 'b' )
            game.turn = 'p';

        else
            game.turn = 'b';
    }

    public static void undo_move(Game game, int move) {

        final int[] move_coordinates = Util.move_number_to_coordinates( move );

        final int col = move_coordinates[0];
        final int row = move_coordinates[1];

        game.board[row][col] = ' ';

        if( game.turn == 'b' )
            game.turn = 'p';

        else
            game.turn = 'b';
    }

    public static ArrayList<Integer> get_all_legal_moves(Game game) {

        ArrayList<Integer> legal_moves = new ArrayList<>();

        for( int row = 0; row < 3; row++ ) {

            for( int col = 0; col < 3; col++ ) {

                if( game.board[row][col] == ' ' )
                    legal_moves.add( Util.coordinates_to_move_number( col, row ) );
            }
        }

        return legal_moves;
    }
}
