package love.xuqinqin.sudoku.util.autothrow;

/**
 * @author Guy_Z
 * @date 2022-04-15 13:48
 */
public interface PredicateWithThrow<T, E> {

    E test(T t);

}
