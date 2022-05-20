package love.xuqinqin.sudoku.entity.cell;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import love.xuqinqin.sudoku.common.Constant;
import love.xuqinqin.sudoku.entity.position.Position;
import love.xuqinqin.sudoku.util.SudokuUtil;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @author Guy_Z
 * @date 2022-05-18 13:57
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Sudoku {

    private Map<Position, Cell> cells;

    public static Sudoku instance() {
        return new Sudoku(new HashMap<>(81));
    }

    public void put(Position position, Cell cell) {
        this.cells.put(position, cell);
    }

    public Cell get(Position position) {
        return cells.get(position);
    }

    public Cell get(Integer x, Integer y) {
        return cells.get(Position.by2D(x, y));
    }

    /**
     * 通过条件获取一组数字
     *
     * @param condition
     * @return
     */
    public Map<Position, Cell> get(BiPredicate<Position, Cell> condition) {
        return this.cells.entrySet().stream().filter(
                e -> condition.test(e.getKey(), e.getValue())
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * 获取一行数字
     *
     * @param x
     * @return
     */
    public Map<Position, Cell> getRow(Integer x) {
        return get((position, cell) -> position.getX().equals(x));
    }

    /**
     * 获取一列数字
     *
     * @param y
     * @return
     */
    public Map<Position, Cell> getColumn(Integer y) {
        return get((position, cell) -> position.getY().equals(y));
    }

    /**
     * 获取一堆数字
     *
     * @param i
     * @return
     */
    public Map<Position, Cell> getStack(Integer i) {
        return get((position, cell) -> position.getI().equals(i));
    }

    /**
     * 获取该格子所在行的所有数字
     *
     * @param cell
     * @return
     */
    public Map<Position, Cell> getRow(Cell cell) {
        for (Map.Entry<Position, Cell> entry : cells.entrySet()) {
            if (entry.getValue() == cell) return getRow(entry.getKey().getX());
        }
        return new HashMap<>();
    }

    /**
     * 获取该格子所在列的所有数字
     *
     * @param cell
     * @return
     */
    public Map<Position, Cell> getColumn(Cell cell) {
        for (Map.Entry<Position, Cell> entry : cells.entrySet()) {
            if (entry.getValue() == cell) return getColumn(entry.getKey().getY());
        }
        return new HashMap<>();
    }

    /**
     * 获取该格子所在堆
     *
     * @param cell
     * @return
     */
    public Map<Position, Cell> getStack(Cell cell) {
        for (Map.Entry<Position, Cell> entry : cells.entrySet()) {
            if (entry.getValue() == cell) return getStack(entry.getKey().getI());
        }
        return new HashMap<>();
    }

    private boolean check(Collection<Cell> cells) {
        return cells.containsAll(Constant.NORMAL_SUDOKU);
    }

    private boolean checkRow(Integer x) {
        return check(getRow(x).values());
    }

    private boolean checkColumn(Integer y) {
        return check(getColumn(y).values());
    }

    private boolean checkStack(Integer i) {
        return check(getStack(i).values());
    }

    public boolean checkAll() {
        for (int i = 1; i <= 9; i++) {
            if (!(checkRow(i) && checkColumn(i) && checkStack(i))) return false;
        }
        return true;
    }

    public Sudoku mark() {
        for (int x = 1; x <= 9; x++) {
            for (int y = 1; y <= 9; y++) {
                Cell cell = get(x, y);
                if (!cell.getValue().equals(0)) {
                    cell.getMark().clear();
                    continue;
                }
                List<Integer> rawMark = SudokuUtil.getNormalMark();
                getRow(x).forEach((position, cellForeach) -> {
                    if (cellForeach.getValue() == 0) return;
                    rawMark.remove(cellForeach.getValue());
                });
                getColumn(y).forEach((position, cellForeach) -> {
                    if (cellForeach.getValue() == 0) return;
                    rawMark.remove(cellForeach.getValue());
                });
                getStack(Position.by2D(x, y).getI()).forEach((position, cellForeach) -> {
                    if (cellForeach.getValue() == 0) return;
                    rawMark.remove(cellForeach.getValue());
                });
                cell.getMark().clear();
                if (rawMark.size() == 1) {
                    cell.setValue(rawMark.get(0));
                }
                cell.getMark().addAll(rawMark);
            }
        }
        return this;
    }

    public JSONObject toJSON() {
        List<List<Cell>> collect = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            collect.add(new ArrayList<>());
        }
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                Cell cell = get(x + 1, y + 1);
                collect.get(x).add(cell);
            }
        }
        JSONObject data = JSONObject.parseObject("{}");
        data.put("data", collect);
        return data;
    }
}
