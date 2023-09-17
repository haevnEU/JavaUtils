package de.haevn.logging;

import lombok.SneakyThrows;

public class LoggerTest {
    @SneakyThrows
    public static void main(String[] args) {
        Logger logger = new Logger();
        logger.getConfig().setConsoleOutput(System.out);
        logger.getConfig().setOutput("C:\\Users\\nilsm\\Desktop\\log.txt");

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

        logger.atFatal().withMessage("DUMMY").log();
        logger.atError().forEnclosingMethod().withException(new RuntimeException("Test")).log();

        logger.flush();
    }
}
