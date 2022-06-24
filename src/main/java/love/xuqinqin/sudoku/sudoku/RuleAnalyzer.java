package love.xuqinqin.sudoku.sudoku;

import love.xuqinqin.sudoku.common.CiConsumer;
import love.xuqinqin.sudoku.entity.cell.Cell;
import love.xuqinqin.sudoku.entity.cell.Sudoku;
import love.xuqinqin.sudoku.entity.position.Position;
import love.xuqinqin.sudoku.util.autothrow.AutoThrow;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * @author Guy_Z
 * @date 2022-06-06 10:50
 */
public class RuleAnalyzer extends HashMap<String, CiConsumer<Sudoku, Cell, Position>> {

    private RuleAnalyzer with(String key, CiConsumer<Sudoku, Cell, Position> rule) {
        super.put(key, rule);
        return this;
    }

    public <T extends Rules> RuleAnalyzer addRules(Class<T> clazz) {
        AutoThrow.exec(() -> {
            T t = clazz.newInstance();
            for (Method method : clazz.getDeclaredMethods()) {
                method.setAccessible(true);
                this.with(method.getName(), (s, c, p) -> AutoThrow.exec(() -> {
                    method.invoke(t, s, c, p);
                }));
            }
        });
        return this;
    }
}
