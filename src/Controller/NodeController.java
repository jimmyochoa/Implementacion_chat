package Controller;

import Models.Node;
import Views.NodeView;

import java.io.*;
import java.net.*;

public class NodeController {
    private Node model;
    private NodeView view;

    public NodeController(Node model, NodeView view) {
        this.model = model;
        this.view = view;
    }

    public void setNodeMessage(String message) {
        model.setMessage(message);
    }

    public void updateView() {
        view.printNodeDetails(model.getIp(), model.getPort(), model.getMessage());
    }

    public void sendMessage() {
        try (Socket socket = new Socket(model.getIp(), model.getPort());
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(model.getMessage());
        } catch (IOException e) {
            System.err.println("Error sending message: " + e.getMessage());
        }
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(model.getPort())) {
            System.out.println("Server started on port " + model.getPort());
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                    // Handle each client connection in a new thread
                    Thread clientThread = new Thread(() -> handleClient(clientSocket));
                    clientThread.start();

                } catch (IOException e) {
                    System.err.println("Error accepting client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String receivedMessage;
            while ((receivedMessage = in.readLine()) != null) {
                view.printReceivedMessage(receivedMessage);
            }
        } catch (IOException e) {
            System.err.println("Error receiving message from client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}
