package hw6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class EchoServer {
    public static boolean isMainWait = true;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        Thread inThread = null;
        Thread outThread = null;

        try {
            serverSocket = new ServerSocket(8190);
            System.out.println("Сервер запущен");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream inputData = new DataInputStream(socket.getInputStream());
            DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
            inThread = startServerInputStream(inputData, socket);
            outThread = startServerOutputStream(outputData, socket);
            while (isMainWait){
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(1000);
            if(inThread != null) inThread.interrupt();
            if (outThread != null) outThread.interrupt();
            if(socket != null) socket.close();
            if(serverSocket != null) serverSocket.close();
        }
        System.exit(0);
    }

    private static Thread startServerInputStream(final DataInputStream inputData, final Socket socket) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                while (true){
                    String message = inputData.readUTF();
                    if(message.equalsIgnoreCase("/end")){
                        break;
                    }
                    System.out.println("Клиент: "+ message);
                }
                }
                 catch (IOException e) {
                    System.out.println("Соединение было прервано!");
                } finally {
                    isMainWait = false;
                }

            }
        });
        thread.start();
        return thread;
    }

    private static Thread startServerOutputStream(final DataOutputStream outputData, final Socket socket) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner in = new Scanner(System.in);
                    while (true){
                        String message = in.nextLine();
                        if(message.equalsIgnoreCase("/end")){
                            break;
                        }
                        outputData.writeUTF(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    isMainWait = false;
                }
            }
        });
        thread.start();
        return thread;
    }
}

