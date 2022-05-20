package love.xuqinqin.sudoku.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import love.xuqinqin.sudoku.common.Constant;
import love.xuqinqin.sudoku.entity.cell.Cell;
import love.xuqinqin.sudoku.entity.cell.Sudoku;
import love.xuqinqin.sudoku.entity.position.Position;
import love.xuqinqin.sudoku.util.autothrow.AutoThrow;

import java.io.File;
import java.io.FileReader;

/**
 * @author Guy_Z
 * @date 2022-05-18 14:25
 */
public class ReadSudoku {

    public static Sudoku get() {
        return AutoThrow.exec(() -> {
            File file = new File(Constant.SUDOKU_DATA_PATH);
            return get(new JSONReader(new FileReader(file)).readObject(JSONObject.class));
        }).orElse(Sudoku.instance());
    }

    public static Sudoku get(JSONObject jsonObject) {
        JSONArray data = jsonObject.getJSONArray("data");
        Sudoku sudoku = Sudoku.instance();
        for (int i = 0; i < data.size(); i++) {
            JSONArray dataChild = data.getJSONArray(i);
            for (int j = 0; j < dataChild.size(); j++) {
                sudoku.put(Position.by2D(i + 1, j + 1), Cell.instance(dataChild.getInteger(j)));
            }
        }
        return sudoku;
    }

}
