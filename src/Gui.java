
import static com.raylib.Colors.WHITE;
import static com.raylib.Raylib.*;
import static java.io.IO.*;

import com.raylib.Raylib.Color;

public class Gui {

    public static float   board_x_position;
    public static float   board_y_position;
    public static float   square_size;

    public static float   line_width;

    public static Color disc_color_blue = new Color();
    public static Color disc_color_pink = new Color();
    public static Color line_color      = new Color();

    public static void init_gui() {

        disc_color_blue.r( (byte) 137 ).g( (byte) 207 ).b( (byte) 240 ).a( (byte) 255 );
        disc_color_pink.r( (byte) 244 ).g( (byte) 194 ).b( (byte) 194 ).a( (byte) 255 );

        line_color.r( (byte) 200 ).g( (byte) 200 ).b( (byte) 200 ).a( (byte) 255 );

        final float vertical_margin = 25.0f;

        line_width = 5.0f;

        final float width  = GetScreenWidth();
        final float height = GetScreenHeight();

        final float shortest_side = Math.min( (int) width, (int) height );

        square_size = ( shortest_side - 2 * vertical_margin ) / 3;

        board_y_position = vertical_margin;
        board_x_position = width / 2 - 3.0f / 2.0f * square_size;
    }

    public static void draw_board() {

        final int number_of_lines = 4; // need 2 * 4 lines to creaet a 3 x 3 board

        final float x_0 = board_x_position;
        final float y_0 = board_y_position;

        final float s  = square_size;
        final float bs = 3 * s;       // board size

        // horizontal lines
        for( int row = 0; row < number_of_lines; row++ ) {

            Vector2 start_pos = new Vector2();
            Vector2 end_pos   = new Vector2();

            start_pos.x( x_0)       .y( y_0 + s * row );
            end_pos  .x( x_0 + bs  ).y( y_0 + s * row );

            DrawLineEx( start_pos, end_pos, line_width, line_color);
        }

        // vertical
        for( int col = 0; col < number_of_lines; col++ ) {

            Vector2 start_pos = new Vector2();
            Vector2 end_pos   = new Vector2();

            start_pos.x( x_0 + col * s)  .y( y_0 );
            end_pos  .x( x_0 + col * s  ).y( y_0 + bs );

            DrawLineEx( start_pos, end_pos, 5, line_color);
        }
    }

    public static void draw_discs(Game game) {

        final float x_0 = board_x_position;
        final float y_0 = board_y_position;

        final float s  = square_size;
        final float bs = 3 * s;

        for( int row = 0; row < 3; row++ ) {

            for( int col = 0; col < 3; col++ ) {

                final float x = x_0 + col * s + s / 2;
                final float y = y_0 + row * s + s / 2;

                final float radius = (float) 0.85 * s / 2;

                final char element_at_row_col = game.board[row][col];

                if( element_at_row_col == 'b' )
                    DrawCircle( (int) x, (int) y, (int) radius, disc_color_blue );

                else if( element_at_row_col == 'p' )
                    DrawCircle( (int) x, (int) y, (int) radius, disc_color_pink );
            }
        }
    }

    // todo input parameter
    public static void draw_win(Game game) {

        GameOverStatus game_over_status = GameOver.get_game_over_status( game );

        final char status = game_over_status.status;

        if( status == ' ' || status == '-' )
            return;


        final float x_0 = board_x_position;
        final float y_0 = board_y_position;

        final float s  = square_size;

        float start_x = x_0 + s * game_over_status.start[0] + s / 2;
        float start_y = y_0 + s * game_over_status.start[1] + s / 2;

        float end_x   = x_0 + s * game_over_status.end[0]   + s / 2;
        float end_y   = y_0 + s * game_over_status.end[1]   + s / 2;

        Vector2 start = new Vector2();

        start.x( start_x );
        start.y( start_y );

        Vector2 end = new Vector2();

        end.x( end_x );
        end.y( end_y );

        DrawLineEx( start, end , 10, WHITE);
    }
}
