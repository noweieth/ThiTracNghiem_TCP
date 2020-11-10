/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KTL2;

import static KTL2.ConnectDTB.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class Server {

    public static void Login() {
        try {
            ServerSocket ss = new ServerSocket(13333);
            System.out.println("Server Login already...");
            while (true) {
                Socket socket = ss.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String str = dis.readUTF();

                String[] arrStr = str.split("/");
                if (arrStr[0].equals("login")) {// kiem tra coi la login hay register
                    if (checkLogin(str)) {
                        dos.writeUTF("success");
                    } else {
                        dos.writeUTF("failed");
                    }
                } else {
                    if (isExistUser(str)) {
                        dos.writeUTF("exist");
                    } else {
                        if (InsertUser(str)) {
                            dos.writeUTF("success");
                        } else {
                            dos.writeUTF("failed");
                        }
                    }

                }

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Login();
    }
}
