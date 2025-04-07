
import java.io.IOException;
//import java.util.logging;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.ConsoleHandler;

public class Logger {
  private static String logFile;
  private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Logger.class.getName());

  public static Logger getLogger(String name) {
    return new Logger();
  }
  public static void setLogFile(String logFile) {
    Logger.logFile = logFile;
    configureFileHandler();
  }




    public void log(DnsRequest request) {
      logger.info(request.toString());

  }

  private static void configureFileHandler() {
    try {
      FileHandler fileHandler = new FileHandler(logFile, true);
      fileHandler.setFormatter(new SimpleFormatter());
      logger.addHandler(fileHandler);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Failed to set up file handler", e);
    }
  }



  public void setLogLevel(String logLevel) {
    Logger.logFile = logFile + "/dnslog.log";
      logger.setLevel(Level.parse(logLevel));
  }

  public void info(String message) {
    logger.info(message);
  }
}




