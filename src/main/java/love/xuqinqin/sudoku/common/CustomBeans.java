package love.xuqinqin.sudoku.common;

import lombok.extern.slf4j.Slf4j;
import love.xuqinqin.sudoku.sudoku.RuleAnalyzer;
import love.xuqinqin.sudoku.sudoku.Rules;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Guy_Z
 * @date 2022-06-06 11:19
 */
@Slf4j
@Configuration
public class CustomBeans {

    @Bean
    public RuleAnalyzer analyzeRules() {
        return new RuleAnalyzer().addRules(Rules.class);
    }

}
