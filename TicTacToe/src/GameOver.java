
public class GameOver {

    private static boolean is_series_win(char[] series) {

        final char first = series[0];

        if( first == ' ' )
            return false;

        for( int k = 1; k < 3; k++ ) {

            if( series[k] != first )
                return false;
        }

        return true;
    }

    // ' ' game not over
    // '-' draw
    // 'x' x won
    // 'o' o won
    public static GameOverStatus get_game_over_status(Game game) {

        for( int row = 0; row < 3; row++ ) {

            if( !is_series_win( game.board[row] ) )
                continue;

            char status = game.board[row][0];

            int[] start = new int[] { 0, row };
            int[] end   = new int[] { 2, row };

            return new GameOverStatus( status, start, end );
        }

        // column wins
        char[] column = new char[3];
        for( int col = 0; col < 3; col++ ) {

            for( int row = 0; row < 3; row++ )
                column[row] = game.board[row][col];

            if( is_series_win(column) ) {

                char status = game.board[0][col];

                int[] start = new int[] { col, 0 };
                int[] end   = new int[] { col, 2 };

                return new GameOverStatus( status, start, end );
            }

            column = new char[3];
        }

        // main diagonal

        char[] diagonal_series = new char[3];

        for( int k = 0; k < 3; k++ )
            diagonal_series[k] = game.board[k][k];

        if( is_series_win( diagonal_series ) ) {

            char status = game.board[0][0];

            int[] start = new int[] { 0, 0 };
            int[] end   = new int[] { 2, 2 };

            return new GameOverStatus( status, start, end );
        }

        // off diagonal

        diagonal_series = new char[3];

        for( int k = 0; k < 3; k++ )
            diagonal_series[k] = game.board[k][2 - k];

        if( is_series_win( diagonal_series ) ) {

            char status = game.board[0][2];

            int[] start = new int[] { 0, 2 };
            int[] end   = new int[] { 2, 0 };

            return new GameOverStatus( status, start, end );

        }

        // check if draw

        boolean is_draw = true;

        for( int row = 0; row < 3; row++ ) {

            for( int col = 0; col < 3; col++ ) {

                if( game.board[row][col] == ' ' )
                    is_draw = false;
            }
        }

        if( is_draw ) {

            char status = '-';

            int[] start = new int[] { };
            int[] end   = new int[] { };

            return new GameOverStatus( status );
        }

        return new GameOverStatus( ' ' );
    }

    public static boolean is_game_over(Game game) {

        GameOverStatus game_over_status = get_game_over_status( game );

        return game_over_status.status != ' ';
    }
}
