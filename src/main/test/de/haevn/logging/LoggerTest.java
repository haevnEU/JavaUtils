package de.haevn.logging;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

public class LoggerTest {
    @SneakyThrows
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.getConfig().setAutoFlush(false);
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();
        logger.atInfo().forEnclosingMethod().withMessage("Hello World").log();


        logger.flush();
    }
}
