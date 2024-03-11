/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.cudp.calcolatriceudp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author seba2
 */
public class Server {

    public static void main(String[] args) {
        int port = 9980;
        double result = 0;

        try (final DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Listening...");

            byte[] buffer = new byte[2048];
            final DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(packet);

                final Numeri read = (Numeri) new ObjectInputStream(new ByteArrayInputStream(packet.getData())).readObject();
                System.out.println(read.toString());

                if (read.getLhs() == read.getRhs()) {
                    System.out.println(read.getLhs() + " = " + read.getRhs());
                } else if (read.getLhs() > read.getRhs()) {
                    System.out.println(read.getLhs() + " > " + read.getRhs());
                } else if (read.getLhs() < read.getRhs()) {
                    System.out.println(read.getLhs() + " < " + read.getRhs());
                }

                switch (read.getSign()) {
                    case '+' -> {
                        result = read.getLhs() + read.getRhs();
                        System.out.println(result(read.getSign(), read.getLhs(), read.getRhs(), result));
                    }
                    case '-' -> {
                        result = read.getLhs() - read.getRhs();
                        System.out.println(result(read.getSign(), read.getLhs(), read.getRhs(), result));
                    }
                    case '*' -> {
                        result = read.getLhs() * read.getRhs();
                        System.out.println(result(read.getSign(), read.getLhs(), read.getRhs(), result));
                    }
                    case '/' -> {
                        if (read.getRhs() != 0) {
                            result = read.getLhs() / read.getRhs();
                            System.out.println(result(read.getSign(), read.getLhs(), read.getRhs(), result));
                        }
                    }
                    default -> {
                        throw new IllegalArgumentException("Wrong sign!");
                    }
                }

                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(result(read.getSign(), read.getLhs(), read.getRhs(), result));

                byte[] sendData = outputStream.toByteArray();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                socket.send(sendPacket);
            }

        } catch (final SocketException e) {
        } catch (final IOException | ClassNotFoundException e) {
        }
    }

    private static String result(char sign, double num1, double num2, double result) {
        return "Result: " + num1 + " " + sign + " " + num2 + " = " + result;
    }
}
