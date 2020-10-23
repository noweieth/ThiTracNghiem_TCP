/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiTracNghiem;

import static ThiTracNghiem.Client.Login;
import static ThiTracNghiem.ConnectDtb.insertData;
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

    public static int ThiTracNghiem() {
        int core = 0;
        try {
            ServerSocket ss = new ServerSocket(13334);
            System.out.println("ThiTracNghiem aldready...");

            Socket socket = ss.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String str = ConnectDtb.getAllCauHoi();
            dos.writeUTF(str);
            String[] cauhoi = str.split("///");
            String anwClient = dis.readUTF();
            System.out.println(anwClient);

            String[] arrAnw = anwClient.split("///");
            int dem = 0;
            String answer = "";
            for (int i = 1; i < arrAnw.length; i = i + 2) {
                dem++;
                answer+=dem+" "+arrAnw[i]+" - "+cauhoi[(dem * 8) - 1]+"\n";
                if (arrAnw[i].equals(cauhoi[(dem * 8) - 1])) {
                    core++;
                }
            }
            answer +="Diem cua ban la: "+core;
            dos.writeUTF(answer);
            ss.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return core;
    }

    public static void AthuLogin() {
        try {
            ServerSocket ss = new ServerSocket(13333);
            System.out.println("Server aldready...");
            while (true) {
                Socket socket = ss.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                String str = dis.readUTF();
                String[] arrStr = str.split("///");
                int core =0;
                if (ConnectDtb.getUser(str)) {
                    dos.writeUTF("Success");
                    core = ThiTracNghiem();
                    insertData(arrStr[0],core);
                    
                } else {
                    dos.writeUTF("Fail");
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        AthuLogin();
//       String str = ConnectDtb.getAllCauHoi();
//       System.out.println(str);
    }
}
