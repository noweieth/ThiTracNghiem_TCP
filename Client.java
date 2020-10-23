/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiTracNghiem;

import static ThiTracNghiem.ConnectDtb.getConnect;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamqu
 */
public class Client {

    public static String inputLogin() throws IOException {
        String str = "";
        System.out.println("Nhap tai khoan: ");
        str += Input.input_String();
        str += "///";
        System.out.println("Nhap mat khau: ");
        str += Input.input_String();
        str += "///";

        return str;
    }

    public static void ThiTracNghiem() {
        try {

            Socket socket = new Socket("localhost", 13334);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            CauHoi CH = new CauHoi();
            String strTN = dis.readUTF();
            String[] arrStr = strTN.split("///");
            List<CauHoi> listCH = new ArrayList<CauHoi>();
            int i = 0;
            int dem = 0;
            for (i = 0; i < arrStr.length;) {
                if (dem < 10) {
                    CH = new CauHoi();
                    CH.setCauhoi(Integer.parseInt(arrStr[i]));
                    CH.setTrinhDo(arrStr[i + 1]);
                    CH.setNoiDung(arrStr[i + 2]);
                    CH.setCauA(arrStr[i + 3]);
                    CH.setCauB(arrStr[i + 4]);
                    CH.setCauC(arrStr[i + 5]);
                    CH.setCauD(arrStr[i + 6]);
                    CH.setDapAn(arrStr[i + 7]);
                    listCH.add(CH);
                    i = i + 8;
                }
                dem++;
            }
            dem = 0;
            String anwClient = "";
            boolean checkinput = true;
            for (CauHoi ch : listCH) {
                dem++;
                System.out.println(dem + ": " + ch.getNoiDung());
                System.out.println("A. " + ch.getCauA());
                System.out.println("B. " + ch.getCauB());
                System.out.println("C. " + ch.getCauC());
                System.out.println("D. " + ch.getCauD());
                System.out.println("Nhap dap an: ");
                checkinput = true;
                while (checkinput) {
                    String anw = Input.input_String();
                    if (anw.equals("A") || anw.equals("B") || anw.equals("C") || anw.equals("D")) {
                        anwClient += dem + "///";
                        anwClient += anw + "///";
                        checkinput = false;
                    } else {
                        System.out.println("Nhap sai vui long nhap lai!");
                    }
                }
            }
            dos.writeUTF(anwClient);

            String answer = dis.readUTF();
            System.out.println(answer);

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void Login() {
        boolean checkAthu = false;
        try {
            while (!checkAthu) {
                Socket socket = new Socket("localhost", 13333);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                String str = inputLogin();
                dos.writeUTF(str);

                String strAthu = dis.readUTF();
                if (strAthu.equals("Success")) {
                    System.out.println("DANG NHAP THANH CONG");
                    checkAthu = true;
                    ThiTracNghiem();
                } else {
                    System.out.println("Fail");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException {
        int choice = 0;
        while (true) {
            
            System.out.println("1. Login try challenge");
            System.out.println("0. Exit");
            choice = Input.input_int();
            switch (choice) {
                case 1:
                    Login();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vui long nhap lai");
                    break;
            }
        }

    }
}
