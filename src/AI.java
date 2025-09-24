
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

    public static int negamax(Game node, int α, int β, int color) {

        if( GameOver.is_game_over( node ) )
            return color * get_game_score( node );

        final ArrayList<Integer> legal_moves = Moves.get_all_legal_moves( node );

        int value = - 1;

        for( int move: legal_moves ) {

            Moves.make_move( node, move );
            value = Math.max( value, - negamax( node, - β, - α,  - color ) );
            Moves.undo_move( node, move);

            α = Math.max( α, value );

            if( α >= β )
                break;
        }

        return value;
    }

    public static int get_best_move(Game node) {


        int color = - ( node.turn == 'b' ? 1 : - 1 ); // we want opposite player's color since after the move is made
                                                      // it is the opposite players turn

        ArrayList<Integer> legal_moves = Moves.get_all_legal_moves( node );

        if( legal_moves.isEmpty() )
            return - 1;

        int best_value = - 1;
        int best_move = legal_moves.getFirst();

        int current_value = - 1 ;

        for( int move: legal_moves ) {

            Moves.make_move( node, move );
            current_value = Math.max( current_value, - negamax( node, - 1, 1, color ) );
            Moves.undo_move( node, move);

            if( current_value > best_value ) {

                best_value = current_value;
                best_move  = move;
            }
        }

        return best_move;
    }
}


