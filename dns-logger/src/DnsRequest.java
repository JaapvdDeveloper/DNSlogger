import java.time.LocalDateTime;

public class DnsRequest {

    private LocalDateTime timestamp;
    private final String sourceIP;
    private final String requestedDomain;
    private final String responseCode;

    public DnsRequest(String sourceIP, String requestedDomain, String responseCode) {

        this.sourceIP = sourceIP;
        this.requestedDomain = requestedDomain;
        this.responseCode = responseCode;
    }

    public String toString() {
        return "DNS Request: " + requestedDomain + " from " +  sourceIP + " at "  + timestamp + " with response code " + responseCode + "\n";

    }

    public String getRequestDetails() {
        return "The request details are: " + responseCode + " " +  requestedDomain + " " + sourceIP + "\n";

    }

}