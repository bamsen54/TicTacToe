import static com.raylib.Raylib.*;

public class Util {

    public static int coordinates_to_move_number(int[] coordinate) {

        final int col = coordinate[0];
        final int row = coordinate[1];

        return 3 * row + col; // the number of the square if we count them left to right top to bottom (0-indexed)
    }

    public static int coordinates_to_move_number( int col, int row) {

        return 3 * row + col; // the number of the square if we count them left to right top to bottom (0-indexed)
    }

    public static int[] move_number_to_coordinates(int move_number) {

        // move_number = 3 * row + col

        final int col = move_number % 3;
        final int row = ( move_number - col ) / 3;

        return new int[] {col, row};
    }

    public static int[] get_mouse_position_as_board_coordinates() {

        final float s  = Gui.square_size;
        final float bs = 3 * s;

        final float x_0 = Gui.board_x_position;
        final float y_0 = Gui.board_y_position;

        final Vector2 mouse = GetMousePosition();

        final float x = mouse.x();
        final float y = mouse.y();

        final int col = (int) Math.floor( Util.map_interval( x, x_0, x_0 + bs, 0, 3 ) );
        final int row = (int) Math.floor( Util.map_interval( y, y_0, y_0 + bs, 0, 3 ) );

        return new int [] { col, row };
    }

    public static float map_interval(float x, float a, float b, float alpha, float beta) {

        return alpha + (beta - alpha) * (x - a) / (b - a);
    }
}
