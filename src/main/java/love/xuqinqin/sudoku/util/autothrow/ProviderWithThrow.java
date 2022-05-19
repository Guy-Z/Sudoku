package love.xuqinqin.sudoku.util.autothrow;

/**
 * @author Guy_Z
 * @date 2022-04-15 13:44
 */
public interface ProviderWithThrow<T> {
    T get() throws Exception;
}
