package love.xuqinqin.sudoku.common;

/**
 * @author Guy_Z
 * @date 2022-06-06 11:41
 */
@FunctionalInterface
public interface CiConsumer<F, S, T> {

    void accept(F f, S s, T t);

}
