package de.haevn.javautils.logging;

import lombok.SneakyThrows;

public class LoggerTest {
    @SneakyThrows
    public static void main(String[] args) {
        LoggerConfig config = new LoggerConfig();
        config.setAutoFlush(false);
        config.setLogSize(1000000000);
        config.setConsoleOutput(System.out);
        config.setOutput("C:\\Users\\nilsm\\Desktop\\log.txt");

        Logger logger = new Logger(config).activateShutdownHook();

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

    }
}
