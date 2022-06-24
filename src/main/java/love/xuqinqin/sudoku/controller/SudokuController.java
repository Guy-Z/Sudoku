package love.xuqinqin.sudoku.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import love.xuqinqin.sudoku.entity.cell.Sudoku;
import love.xuqinqin.sudoku.sudoku.RuleAnalyzer;
import love.xuqinqin.sudoku.util.ReadSudoku;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Guy_Z
 * @date 2022-05-19 15:31
 */
@Slf4j
@RestController
@RequestMapping("sudoku")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class SudokuController {

    private final RuleAnalyzer rulesAnalyze;

    @PostMapping
    public Map<String, Object> sudoku(@RequestBody JSONObject sudokuJSONObject) {
        Sudoku sudoku = ReadSudoku.get(sudokuJSONObject);
//        sudoku.put(Position.by2D(1,1), Cell.instance(9));
        return sudoku.analyze(rulesAnalyze).toJSON();
    }
}
