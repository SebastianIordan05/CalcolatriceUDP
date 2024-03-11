/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cudp.calcolatriceudp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author seba2
 */
public class Client {
    public static void main(String[] args) throws IOException {
        try {
            DatagramSocket socket = new DatagramSocket();
            final Numeri data = new Numeri(-127, 100, '+'); // somma
//            final Numeri data = new Numeri(-127, 100, '-'); // sottrazione
            
            final ByteArrayOutputStream outstream = new ByteArrayOutputStream();
            new ObjectOutputStream(outstream).writeObject(data);
            
            final byte[] sendBuffer = outstream.toByteArray();
            final DatagramPacket packet = new DatagramPacket(sendBuffer, sendBuffer.length, InetAddress.getLocalHost(), 9980);
            socket.send(packet);
            
        } catch (SocketException e) {
        } catch (UnknownHostException e) {
        } catch (IOException e) {
        }
    }
}
