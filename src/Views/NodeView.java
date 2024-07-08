package Views;

// NodeView.java
public class NodeView {
    public void printNodeDetails(String ip, int port, String message) {
        System.out.println("Node Details:");
        System.out.println("IP: " + ip);
        System.out.println("Port: " + port);
        System.out.println("Message: " + message);
    }

    public void printReceivedMessage(String message) {
        System.out.println("Received Message: " + message);
    }
}
