
import java.util.ArrayList;

public class AI {

    private static int get_game_score(Game game) {

        char game_over_status = GameOver.get_game_over_status( game ).status;

        return switch ( game_over_status ) {

            case 'b' ->  1;
            case 'p' -> -1;

            default  -> 0;
        };
    }

    public static int negamax(Game node, int color) {

        if( GameOver.is_game_over( node ) )
            return color * get_game_score( node );

        final ArrayList<Integer> legal_moves = Moves.get_all_legal_moves( node );

        int value = - 2;

        for( int move: legal_moves ) {

            Moves.make_move( node, move );

            value = Math.max( value, - negamax( node, - color ) );

            Moves.undo_move( node, move);
        }

        return value;
    }

    public static int get_best_move(Game node) {

        ArrayList<Integer> legal_moves = Moves.get_all_legal_moves( node );

        if( legal_moves.isEmpty() )
            return - 1;

        int best_value = - 1;
        int best_move = legal_moves.get( 0 );

        int current_value = - 1 ;

        for( int move: legal_moves ) {

            Moves.make_move( node, move );

            int color = node.turn == 'b' ? 1 : - 1;

            current_value = Math.max( current_value, - negamax( node, color ) );

            Moves.undo_move( node, move);

            if( current_value > best_value ) {

                best_value = current_value;
                best_move  = move;
            }
        }

        return best_move;
    }
}


//javac -cp "lib/*" -d out *.java

