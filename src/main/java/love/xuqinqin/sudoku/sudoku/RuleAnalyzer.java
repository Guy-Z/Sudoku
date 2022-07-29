package love.xuqinqin.sudoku.sudoku;

import lombok.extern.slf4j.Slf4j;
import love.xuqinqin.sudoku.common.CiConsumer;
import love.xuqinqin.sudoku.entity.cell.Cell;
import love.xuqinqin.sudoku.entity.cell.Sudoku;
import love.xuqinqin.sudoku.entity.position.Position;
import love.xuqinqin.sudoku.util.autothrow.AutoThrow;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Guy_Z
 * @date 2022-06-06 10:50
 */
@Slf4j
public class RuleAnalyzer extends HashMap<String, CiConsumer<Sudoku, Cell, Position>> {

    private RuleAnalyzer with(String key, CiConsumer<Sudoku, Cell, Position> rule) {
        super.put(key, rule);
        return this;
    }

    public <T extends Rules> RuleAnalyzer addRules(Class<T> clazz) {
//        AutoThrow.exec(() -> {
//            T t = clazz.newInstance();
//            for (Method method : clazz.getDeclaredMethods()) {
//                method.setAccessible(true);
//                this.with(method.getName(), (s, c, p) -> AutoThrow.exec(() -> {
//                    method.invoke(t, s, c, p);
//                }));
//            }
//        });
        try {
            this.putAll(getMethods(clazz));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("校验规则计数: {}", this.size());
        log.info("规则: {}", this.keySet());
        return this;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Rules> Map<String, CiConsumer<Sudoku, Cell, Position>> getMethods(Class<T> clazz) throws Exception {
        Map<String, CiConsumer<Sudoku, Cell, Position>> methodMap = new HashMap<>();
        T t = clazz.newInstance();
        for (Method method : clazz.getDeclaredMethods()) {
            String name = method.getName();
            if (!name.contains("rule") || name.contains("lambda")) continue;
            methodMap.put(clazz.getSimpleName() + "." + name, (s, c, p) -> AutoThrow.exec(() -> {
                method.invoke(t, s, c, p);
            }));
        }
        if (hasSuper(clazz)) methodMap.putAll(getMethods((Class<T>) clazz.getSuperclass()));
        return methodMap;
    }

    private static <T extends Rules> boolean hasSuper(Class<T> clazz) {
        return clazz != null && !(Object.class.equals(clazz.getSuperclass()) || clazz.getSuperclass() == null);
    }
}
