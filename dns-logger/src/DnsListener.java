import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

//Importeer de benodigde classes
//Importeer DatagramPacket, DatagramSocket, InetAddress, UnknownHostException, IOException
//Importeer de DnsRequest class
//De DnsRequest class bevat de details van een DNS request zoals het IP adres van de afzender en het opgevraagde domein
//EEn datagramsocket is een entry-point voor het verzenden en ontvangen van datagram packets
//Algemen werking van deze class is
//Defineert een Java-klasse DnsListener die een DNS-verzoek kan ontvangen en verwerken via een UDP-socket
// Een UDP-Socket is een netwerkverbinding die gebruik maakt van het User Datagram Protocol(UDP)
public class DnsListener {
  //Zet twee instantie variabelen port, isRunning
  private int port;
  private static boolean isRunning;

  public static void startListening() {
    try {
      DatagramSocket socket = new DatagramSocket();
      DatagramPacket packet = new DatagramPacket(new byte[512], 512);

      //Sta broadcast toe, met enable/disable SO_BROADCAST
      socket.setBroadcast(true);
      //Haal de packet op vanuit de socket, bevat de IP, port, host sender naam
      socket.receive(packet);
      //Zet geheugen op 512 bytes
      socket.setSendBufferSize(512);
        //Zet de receive buffer op 512 bytes
        socket.setReceiveBufferSize(512);
        //Haal het IP adres op van de machine waar de packet van afkomt
      InetAddress address = packet.getAddress();
      //sla de bytes data op in een byte array
        byte[] data = packet.getData();
        //haal de lengte van het packet op
        int length = packet.getLength();
        //Zet sourceIP, requestedDomain, responseCode op
        String sourceIP = address.getHostAddress();
        String requestedDomain = new String(data, 0, length);
        String responseCode = "NOERROR"; // Response code voor succesful DNS request
        DnsRequest dnsRequest = new DnsRequest(sourceIP, requestedDomain, responseCode);
        System.out.println("Received DNS request from " + sourceIP + ": " + requestedDomain);
        // Process the DNS request
    }
    catch (Exception e) {
      System.out.println("Error starting DNS listener: " + e.getMessage());
    }

    //Maak een socket aan op de opgegeven poort

  }

  public static void isRunning() {

    if(isRunning) {
      System.out.println("Dns listener is running");
      isRunning = true;
    } else {
      System.out.println("DNS Listener is not running");
      isRunning = false;
    }

  }

  public static void main(String[] args) {
    DnsListener listener = new DnsListener();
    //Start de listener
    startListening();

    //Start isRunning method
    isRunning();
  }

  public void onDNsRequest(DnsRequest dnsRequest) {
    System.out.println("The DNS request is: " + dnsRequest.getRequestDetails());
  }

}