import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//Importeer de benodigde classes
//Importeer DatagramPacket, DatagramSocket, InetAddress, UnknownHostException, IOException
//Importeer de DnsRequest class
//De DnsRequest class bevat de details van een DNS request zoals het IP adres van de afzender en het opgevraagde domein
//EEn datagramsocket is een entry-point voor het verzenden en ontvangen van datagram packets
//Algemen werking van deze class is
//Defineert een Java-klasse DnsListener die een DNS-verzoek kan ontvangen en verwerken via een UDP-socket
// Een UDP-Socket is een netwerkverbinding die gebruik maakt van het User Datagram Protocol(UDP)

public class DnsListener {
  //Zet twee instantie variabelen port, isRunning en een instantie van de getter getLogger
  private static int port;
  private static boolean isRunning;
  private static final Logger logger = Logger.getLogger("DNS Logger");

  public DnsListener(int port) {
    DnsListener.port = port;
  }
  public static void startListening() {
    // probeer de socket te maken op de opgegeven port
    try (DatagramSocket socket = new DatagramSocket(port)){

      //Sta broadcast toe, met enable/disable SO_BROADCAST
      socket.setBroadcast(true);

      while (isRunning) {
        //Haal de packet op vanuit de socket, bevat de IP, port, host sender naam
        DatagramPacket packet = new DatagramPacket(new byte[512], 512);
        //Haal het IP adres op van de machine waar de packet van afkomt
        socket.receive(packet);
        InetAddress address = packet.getAddress();


        //Zet sourceIP, requestedDomain, responseCode op
        String sourceIP = address.getHostAddress();
        String requestedDomain = new String(packet.getData(), 0, packet.getLength());
        // Response code voor succesful DNS request
        String responseCode = "NOERROR";
        DnsRequest dnsRequest = new DnsRequest(sourceIP, requestedDomain, responseCode);
        System.out.println("Received DNS request from " + sourceIP + ": " + requestedDomain);
        onDnsRequest(dnsRequest);
        // Process the DNS request
      }
    }
    catch (Exception e) {
      System.out.println("Error starting DNS listener: " + e.getMessage());
    }

    //Maak een socket aan op de opgegeven poort

  }

  public void stopListening() {
    isRunning = false;
  }

  public static void onDnsRequest(DnsRequest dnsRequest) {
    logger.log(dnsRequest);
    System.out.println("The DNS request is: " + dnsRequest.getRequestDetails());
  }


  //Controleert of de method 'isRunning' wordt uitgevoerd. Zo ja, toon "DNS listener is running", en zo niet, toon 'DNS Listener is not running'.
//  public static void isRunning() {
//
//    if(isRunning) {
//      System.out.println("Dns listener is running");
//      isRunning = true;
//    } else {
//      System.out.println("DNS Listener is not running");
//      isRunning = false;
//    }
//
//
//  }

//


}