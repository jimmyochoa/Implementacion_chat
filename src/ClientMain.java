import java.lang.ModuleLayer.Controller;
import java.util.Scanner;

import Controller.NodeController;
import Models.Node;
import Views.NodeView;

// ClientMain.java
public class ClientMain {
    public static void main(String[] args) {
        Node clientNode = new Node("127.0.0.1", 50);
        NodeView view = new NodeView();
        NodeController controller = new NodeController(clientNode, view);

        Scanner input = new Scanner(System.in);

        try {
            while (true) {
                String userInput = input.nextLine();
                controller.setNodeMessage(userInput);
                controller.updateView();
                controller.sendMessage();
            }
        } finally {
            input.close();
        }
    }
}
