/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiTracNghiem;

/**
 *
 * @author lamqu
 */
public class CauHoi {
    private int cauhoi;
    private String TrinhDo, NoiDung, CauA, CauB,CauC,CauD,DapAn;

    public CauHoi() {
    }

    public CauHoi(int cauhoi, String TrinhDo, String NoiDung, String CauA, String CauB, String CauC, String CauD, String DapAn) {
        this.cauhoi = cauhoi;
        this.TrinhDo = TrinhDo;
        this.NoiDung = NoiDung;
        this.CauA = CauA;
        this.CauB = CauB;
        this.CauC = CauC;
        this.CauD = CauD;
        this.DapAn = DapAn;
    }

    public int getCauhoi() {
        return cauhoi;
    }

    public void setCauhoi(int cauhoi) {
        this.cauhoi = cauhoi;
    }

    public String getTrinhDo() {
        return TrinhDo;
    }

    public void setTrinhDo(String TrinhDo) {
        this.TrinhDo = TrinhDo;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String NoiDung) {
        this.NoiDung = NoiDung;
    }

    public String getCauA() {
        return CauA;
    }

    public void setCauA(String CauA) {
        this.CauA = CauA;
    }

    public String getCauB() {
        return CauB;
    }

    public void setCauB(String CauB) {
        this.CauB = CauB;
    }

    public String getCauC() {
        return CauC;
    }

    public void setCauC(String CauC) {
        this.CauC = CauC;
    }

    public String getCauD() {
        return CauD;
    }

    public void setCauD(String CauD) {
        this.CauD = CauD;
    }

    public String getDapAn() {
        return DapAn;
    }

    public void setDapAn(String DapAn) {
        this.DapAn = DapAn;
    }
    
    
}
