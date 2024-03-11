/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author seba2
 */
public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Scanner sc = new Scanner(System.in);

        int num1, num2;
        char sign;

        try {

            while (true) {
                System.out.print("num1: ");
                num1 = sc.nextInt();

                System.out.print("sign: ");
                sign = sc.next().charAt(0);

                System.out.print("num2: ");
                num2 = sc.nextInt();

                DatagramSocket socket = new DatagramSocket();
                final Numeri data = new Numeri(num1, num2, sign);

                final ByteArrayOutputStream outstream = new ByteArrayOutputStream();
                new ObjectOutputStream(outstream).writeObject(data);

                final byte[] sendBuffer = outstream.toByteArray();
                final DatagramPacket packet = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getLocalHost(), 9980);
                socket.send(packet);

                byte[] receiveData = new byte[2048];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                ByteArrayInputStream inputStream = new ByteArrayInputStream(receivePacket.getData());
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                String result = (String) objectInputStream.readObject();

                System.out.println(result);
            }

        } catch (SocketException e) {
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }
}
