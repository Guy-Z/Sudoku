package love.xuqinqin.sudoku.entity.cell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import love.xuqinqin.sudoku.entity.position.Position;

import java.util.HashMap;
import java.util.Map;
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
        HashMap<Position, Cell> cellMap = new HashMap<>(81);
        return new Sudoku(cellMap);
    }

    public void put(Position position, Cell cell) {
        this.cells.put(position, cell);
    }

    public Cell get(Position position) {
        return cells.get(position);
    }

    public Map<Position, Cell> get(BiPredicate<Position, Cell> condition) {
        return this.cells.entrySet().stream().filter(
                e -> condition.test(e.getKey(), e.getValue())
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Position, Cell> getRow(Integer x) {
        return get((position, cell) -> position.getX().equals(x));
    }

    public Map<Position, Cell> getColumn(Integer y) {
        return get((position, cell) -> position.getY().equals(y));
    }
}
