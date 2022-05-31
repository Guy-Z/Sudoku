package love.xuqinqin.sudoku.entity.cell;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Guy_Z
 * @date 2022-05-18 13:50
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cell {

    private Integer value;

    private List<Integer> mark;

    public static Cell instance(Integer value) {
        return new Cell(value, new ArrayList<>(9));
    }

    public boolean isNotSure() {
        return this.value == null || value.equals(0);
    }

    public void clearMark(){
        this.mark.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return Objects.equals(value, ((Cell) o).value);
    }
}
