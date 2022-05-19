package love.xuqinqin.sudoku.util.autothrow;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author Guy_Z
 * @date 2022-04-02 17:10
 */
public class AutoThrow {

    public static void exec(RunnableWithThrow runnable) {
        exec(runnable, true);
    }

    public static void exec(RunnableWithThrow runnable, boolean isLog) {
        exec(runnable, e -> {
            if (isLog) e.printStackTrace();
        });
    }

    public static void exec(RunnableWithThrow runnable, String message) {
        try {
            runnable.run();
        } catch (Exception e) {
            new Exception(message).printStackTrace();
        }
    }

    public static void exec(RunnableWithThrow runnable, Exception exception) {
        try {
            runnable.run();
        } catch (Exception e) {
            exception.printStackTrace();
        }
    }

    public static void exec(RunnableWithThrow runnable, Consumer<Exception> exceptionHandler) {
        try {
            runnable.run();
        } catch (Exception e) {
            exceptionHandler.accept(e);
        }
    }

    public static <E> Optional<E> exec(ProviderWithThrow<E> provider) {
        try {
            return Optional.of(provider.get());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public static <E> E exec(ProviderWithThrow<E> provider, PredicateWithThrow<Exception, E> predicate) {
        try {
            return provider.get();
        } catch (Exception e) {
            e.printStackTrace();
            return predicate.test(e);
        }
    }
}

