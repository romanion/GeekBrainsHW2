package hw6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static boolean isMainWait = true;
    public static void main(String[] args) throws InterruptedException, IOException {
        Thread inThread = null;
        Thread outThread = null;
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8190);
            DataInputStream inputData = new DataInputStream(socket.getInputStream());
            DataOutputStream outputData = new DataOutputStream(socket.getOutputStream());
            inThread = startClientInputStream(inputData, socket);
            outThread = startClientOutputStream(outputData, socket);

            while (isMainWait){
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Thread.sleep(1000);
            if(socket != null) socket.close();
            if(inThread != null) inThread.interrupt();
            if (outThread != null) outThread.interrupt();
        }
        System.exit(0);

    }

    private static Thread startClientInputStream(final DataInputStream inputData, final Socket socket) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        String message = inputData.readUTF();
                        if(message.equalsIgnoreCase("/end")){
                            break;
                        }
                        System.out.println("Сервер: "+ message);
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

    private static Thread startClientOutputStream(final DataOutputStream outputData, final Socket socket) throws InterruptedException {

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
