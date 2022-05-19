package love.xuqinqin.sudoku.entity.cell;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import love.xuqinqin.sudoku.entity.position.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Guy_Z
 * @date 2022-05-18 13:50
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cell {

    private Integer value;

    private List<Integer> mark;

    public static Cell instance(Integer value) {
        return new Cell(value, new ArrayList<>(9));
    }
}
