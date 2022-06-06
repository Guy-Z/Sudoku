package love.xuqinqin.sudoku.common;

import lombok.extern.slf4j.Slf4j;
import love.xuqinqin.sudoku.entity.cell.Cell;
import love.xuqinqin.sudoku.entity.cell.Sudoku;
import love.xuqinqin.sudoku.entity.position.Position;
import love.xuqinqin.sudoku.sudoku.AnalyzeRules;
import love.xuqinqin.sudoku.util.SudokuUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author Guy_Z
 * @date 2022-06-06 11:19
 */
@Slf4j
@Configuration
public class CustomBeans {

    @Bean
    public AnalyzeRules analyzeRules() {
        return new AnalyzeRules().with(this::rule1);
    }

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
        if (rawMark.size() == 1) cell.setValue(rawMark.get(0));
        cell.setMark(rawMark);
    }

}
