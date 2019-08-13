package com.tinain.server;

/**
 * @author Alan Lau
 */
public class Server {

    public static void main(String[] args) throws Exception {
        new Thread(new Reactor(8080)).start();
        System.in.read();
    }
}
