package Logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {
    private static FileHandler fileHandler;

    public static void setupLogger(Logger logger) {
        logger.setUseParentHandlers(false);
        logger.addHandler(fileHandler);
        logger.setLevel(Level.INFO);
    }

    static {
        try {
            fileHandler = new FileHandler("log.txt");
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}


