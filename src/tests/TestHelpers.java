package tests;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class TestHelpers {
    private static final ByteArrayOutputStream logOutputStream = new ByteArrayOutputStream();
    private static final Handler logHandler = new LogHandler(logOutputStream);

    public static void setupLogging() {
        Logger logger = Logger.getLogger("");
        logger.addHandler(logHandler);
        logger.setLevel(Level.ALL);
    }

    // Retrieve the captured logs as a string
    public static String getCapturedLogs() {
        try {
            return logOutputStream.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getConsoleLog() {
        try {
            return logOutputStream.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Custom log handler to capture logs
    private static class LogHandler extends Handler {

        private final ByteArrayOutputStream outputStream;

        public LogHandler(ByteArrayOutputStream outputStream) {
            this.outputStream = outputStream;
        }


        /**
         * Publish a {@code LogRecord}.
         * <p>
         * The logging request was made initially to a {@code Logger} object,
         * which initialized the {@code LogRecord} and forwarded it here.
         * <p>
         * The {@code Handler}  is responsible for formatting the message, when and
         * if necessary.  The formatting should include localization.
         *
         * @param record description of the log event. A null record is
         *               silently ignored and is not published
         */
        public void publish(LogRecord record) {

        }

        @Override
        public void flush() {
            // Do nothing
        }

        @Override
        public void close() throws SecurityException {
            // Do nothing
        }
    }
}
