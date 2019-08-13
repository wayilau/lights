package com.tinain.server;

import com.sun.org.apache.bcel.internal.generic.Select;
import io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Alan Lau
 */
public class Reactor implements Runnable {

    final Selector selector;
    final ServerSocketChannel serverSocket;

    Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        sk.attach(new Acceptor());
    }

    @Override public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set selected = selector.selectedKeys();
                Iterator it = selected.iterator();
                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey)it.next();
                    dispatch(key);
                    //remove
//                    selected.remove(key);
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void dispatch(SelectionKey key) {
        Runnable r = (Runnable)key.attachment();
        if (r != null) {
            r.run();
        }
    }

    class Acceptor implements Runnable {
        @Override public void run() {
            try {
                SocketChannel c = serverSocket.accept();
                if (c != null) {
                    new Handler(selector, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}

final class Handler implements Runnable {
    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    Handler(Selector sel, SocketChannel c) throws IOException {
        socket = c;
        c.configureBlocking(false);
        sk = socket.register(sel, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    boolean inputIsComplete() throws IOException{
        input.clear();
        socket.read(input);
        return true;
    }

    boolean outputIsComplete() {
        return true;
    }

    void process() {
        input.flip();
        byte[] bytes = new byte[input.remaining()];

        input.get(bytes);
        System.out.println("server rcv: " + new String(bytes));
    }

    @Override public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void read() throws IOException {
        if (inputIsComplete()) {
            process();
            state = SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);

        }
    }

    void send() throws IOException {
        input.rewind();
        byte[] bytes = new byte[input.remaining()];
        input.get(bytes);
        output.clear();
        output.put(bytes);
        output.flip();
        socket.write(output);
        sk.interestOps(SelectionKey.OP_READ);
        state = READING;
//        if (outputIsComplete()) {
//            sk.cancel();
//        }
    }
}



