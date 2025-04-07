import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;


class DnsLogger
{
    private static DnsListener listener;
    private static Logger logger;

    public static void main( String[] args )
    {
        listener = new DnsListener(1053);
        logger = Logger.getLogger("DNS Logger");//Maak een logger instance aan
        Logger.setLogFile("/home/jaap/dnslog/dnslog.log");
        logger.setLogLevel("INFO");
        start();

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        //Stop de logger wanneer nodig
        //DnsLogger.stop();
    }

    //Start de logger met een prompt die aangeeft "DNS Logger started"
    public static void start() {
        logger.info("DNS Logger started \n");
        DnsListener.startListening();
    }

    //Stop de logger met een prompt die aangeeft "DNS Logger stopped
    public static void stop() {
        logger.info("DNS Logger stopped \n");
        listener.stopListening();
    }

}
