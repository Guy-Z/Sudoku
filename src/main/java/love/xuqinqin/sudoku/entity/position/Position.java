package love.xuqinqin.sudoku.entity.position;

import lombok.*;

import java.util.Objects;

/**
 * @author Guy_Z
 * @date 2022-05-18 14:46
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Position {

    //二维坐标 1~9
    /**
     * 行
     */
    private Integer x;
    /**
     * 列
     */
    private Integer y;

    //次序坐标 1~9
    /**
     * 堆(指3*3的小9宫格)
     */
    private Integer i;
    /**
     * 位
     */
    private Integer j;

    public static Position by2D(Integer x, Integer y) {
        int xi = x - 1;
        int yi = y - 1;
        return new Position(x, y, xi / 3 * 3 + yi / 3 + 1, xi % 3 * 3 + yi % 3 + 1);
    }

    public static Position byOrder(Integer i, Integer j) {
        int ii = i - 1;
        int ji = j - 1;
        return new Position(ii / 3 * 3 + ji / 3 + 1, ii % 3 * 3 + ji % 3 + 1, i, j);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(x, position.x) &&
                Objects.equals(y, position.y) &&
                Objects.equals(i, position.i) &&
                Objects.equals(j, position.j);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, i, j);
    }
}
