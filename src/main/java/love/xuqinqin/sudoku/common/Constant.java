package love.xuqinqin.sudoku.common;

import love.xuqinqin.sudoku.entity.cell.Cell;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Guy_Z
 * @date 2022-05-18 14:26
 */
public class Constant {

    public static final String SUDOKU_DATA_PATH = "src/main/resources/data1.json";

    public static final List<Cell> NORMAL_SUDOKU = new ArrayList<>(Arrays.asList(
            Cell.instance(1), Cell.instance(2), Cell.instance(3),
            Cell.instance(4), Cell.instance(5), Cell.instance(6),
            Cell.instance(7), Cell.instance(8), Cell.instance(9)
    ));

}
