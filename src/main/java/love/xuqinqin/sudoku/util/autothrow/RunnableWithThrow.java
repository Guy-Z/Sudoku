package love.xuqinqin.sudoku.util.autothrow;

/**
 * @author Guy_Z
 * @date 2022-04-02 17:15
 */
@FunctionalInterface
public interface RunnableWithThrow {

    void run() throws Exception;

}
