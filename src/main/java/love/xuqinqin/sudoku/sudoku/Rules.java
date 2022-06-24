package love.xuqinqin.sudoku.sudoku;

import love.xuqinqin.sudoku.entity.cell.Cell;
import love.xuqinqin.sudoku.entity.cell.Sudoku;
import love.xuqinqin.sudoku.entity.position.Position;
import love.xuqinqin.sudoku.util.SudokuUtil;

import java.util.List;

/**
 * @author Guy_Z
 * @date 2022-06-17 10:05
 */
public class Rules {

    public void rule1(Sudoku sudoku, Cell cell, Position position) {
        List<Integer> rawMark = SudokuUtil.getNormalMark();
        sudoku.getRow(position.getX()).forEach((positionForeach, cellForeach) -> {
            if (cellForeach.isNotSure()) return;
            rawMark.remove(cellForeach.getValue());
        });
        sudoku.getColumn(position.getY()).forEach((positionForeach, cellForeach) -> {
            if (cellForeach.isNotSure()) return;
            rawMark.remove(cellForeach.getValue());
        });
        sudoku.getStack(position.getI()).forEach((positionForeach, cellForeach) -> {
            if (cellForeach.isNotSure()) return;
            rawMark.remove(cellForeach.getValue());
        });
        if (rawMark.size() == 1) {
            cell.setValue(rawMark.get(0));
            cell.clearMark();
            return;
        }
        cell.setMark(rawMark);
    }

    public void rule2(Sudoku sudoku, Cell cell, Position position) {

    }

}
