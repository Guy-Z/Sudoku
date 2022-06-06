package love.xuqinqin.sudoku.sudoku;

import love.xuqinqin.sudoku.common.CiPredicate;
import love.xuqinqin.sudoku.entity.cell.Cell;
import love.xuqinqin.sudoku.entity.cell.Sudoku;
import love.xuqinqin.sudoku.entity.position.Position;

import java.util.ArrayList;

/**
 * @author Guy_Z
 * @date 2022-06-06 10:50
 */
public class AnalyzeRules extends ArrayList<CiPredicate<Sudoku, Cell, Position>>{

    public AnalyzeRules with(CiPredicate<Sudoku, Cell, Position> rule) {
        super.add(rule);
        return this;
    }
}
