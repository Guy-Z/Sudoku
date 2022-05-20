package love.xuqinqin.sudoku;

import love.xuqinqin.sudoku.entity.cell.Cell;
import love.xuqinqin.sudoku.entity.cell.Sudoku;
import love.xuqinqin.sudoku.entity.position.Position;
import love.xuqinqin.sudoku.util.ReadSudoku;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Guy_Z
 * @date 2022-03-17 10:26
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            SpringApplication.run(Main.class, args);
        } else if ("test".equals(args[0])) {
            System.out.println("!");
            Sudoku sudoku = ReadSudoku.get();
            Cell cell = sudoku.get(Position.by2D(1, 2));
            sudoku.getRow(cell).forEach((position, cell1) -> System.out.println(position + " : " + cell1));
        }
    }
}
