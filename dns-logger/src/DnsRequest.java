import java.time.LocalDateTime;

public class DnsRequest {

    private LocalDateTime timestamp;
    private String sourceIP;
    private String requestedDomain;
    private String responseCode;

    public DnsRequest(String sourceIP, String requestedDomain, String responseCode) {
        this.timestamp = timestamp;
        this.sourceIP = sourceIP;
        this.requestedDomain = requestedDomain;
        this.responseCode = responseCode;
    }

    public String toString() {

    }

    public String getRequestDetails() {

    }

}