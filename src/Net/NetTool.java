/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Net;

import View.PPMainBoard;
import java.awt.List;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

/**
 *
 * @author Blossoming
 */
public class NetTool {
    public static void sendUdpBroadCast(String ip,String msg){
        try {
            MulticastSocket ms = new MulticastSocket();
            InetAddress ia = InetAddress.getByName(ip);
            DatagramPacket dp = new DatagramPacket(msg.getBytes(), 0, msg.length(), ia, 1111);
            ms.send(dp);
            ms.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {

        }
    }
}
