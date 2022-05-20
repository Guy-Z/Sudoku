package love.xuqinqin.sudoku.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Guy_Z
 * @date 2022-05-20 15:44
 */
public class SudokuUtil {

    public static List<Integer> getNormalMark() {
        return new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    }

}
