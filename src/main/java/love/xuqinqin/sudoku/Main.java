package love.xuqinqin.sudoku;

import love.xuqinqin.sudoku.entity.cell.Cell;
import love.xuqinqin.sudoku.entity.cell.Sudoku;
import love.xuqinqin.sudoku.entity.position.Position;
import love.xuqinqin.sudoku.util.ReadSudoku;

import java.util.Map;

/**
 * @author Guy_Z
 * @date 2022-03-17 10:26
 */
public class Main {

    public static void main(String[] args) {
        Sudoku sudoku = ReadSudoku.get();
//        sudoku.getCells().forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println(sudoku.getCells().size());
        Map<Position, Cell> cells = sudoku.getColumn(3);
        cells.forEach((k, v) -> System.out.println(k + " : " + v));
    }

}
