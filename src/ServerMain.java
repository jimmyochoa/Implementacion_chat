import Controller.NodeController;
import Models.Node;
import Views.NodeView;

// ServerMain.java
public class ServerMain {
    public static void main(String[] args) {
        Node serverNode = new Node("127.0.0.1", 50);
        NodeView view = new NodeView();
        NodeController controller = new NodeController(serverNode, view);

        controller.startServer();
    }
}
