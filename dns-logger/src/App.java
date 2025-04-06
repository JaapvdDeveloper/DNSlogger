 class DnsLogger implements DnsListener
{
    private static Logger logger = Logger.getLogger(DnsLogger.class.getName());
  //  Logger logger = DnsLogger.getLogger(DnsLogger.l);


    public static void main( String[] args )
    {
        //Maak een logger instance aan
        DnsLogger.logger = new Logger();

        //roep de logger method aan
        DnsLogger.start();

        //Stop de logger wanneer nodig
        DnsLogger.stop();


    }

    //Start de logger met een prompt die aangeeft "DNS Logger started"
    public static void start() {
        logger.info("DNS Logger started");
    }

    //Stop de logger met een prompt die aangeeft "DNS Logger stopped
    public static void stop() {
        logger.info("DNS Logger stopped");
    }

}
