import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

//EEn datagramsocket is een entry-point voor het verzenden en ontvangen van datagram packets

public class DnsListener {
  //Zet twee instantie variabelen port, isRunning
  private int port;
  private boolean isRunning;

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

  }

  public static void isRunning() {



  }

  public static void main(String[] args) {
    DnsListener listener = new DnsListener();
    startListening();

  }

  public void onDNsRequest(dnsRequest: DnsRequest) {

  }

}