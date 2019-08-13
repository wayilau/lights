package com.tinain.classic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Alan Lau
 */
public class Server implements Runnable {

    @Override public void run() {
        try {
            ServerSocket ss = new ServerSocket(8080);
            while (!Thread.interrupted()) {
                new Thread(new Handler(ss.accept())).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    static class Handler implements Runnable {
        final Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override public void run() {
            try {
                byte[] input = new byte[1024];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            } catch (IOException ex) {

            }
        }

        static byte[] process(byte[] input) {
            return null;
        }
    }



}


