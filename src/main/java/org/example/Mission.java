package org.example;

import javax.swing.text.Style;

public abstract class Mission {
    //FW
    public int missionId;
    public static String name;
    public byte LF = 0;
    public byte TLF = 0;
    public byte ELW = 0;
    public byte DLK = 0;
    public byte RW = 0;
    public byte SW = 0;
    public byte GW = 0;
    public byte MTW = 0;
    public byte WLF = 0;
    public byte FwK = 0;
    //FW flughafen
    public byte FLF;
    public byte RTF;
    //FW werk
    public byte GWW;
    public byte ULF;
    public byte TM50;
    public byte TL;
    //RTD
    public byte RTW;
    public byte NEF;
    public byte KTW;
    public byte LNA;
    public byte OrgL;
    public byte GRTW;
    public byte NAW;
    public byte ITW;
    public byte RTWSchrabSchrab;
    //Pol
    public byte FuStW;
    public byte FuStM;
    public byte BefKw;
    public byte GruKw;
    public byte GefKw;
    public byte WaWe;
    public byte SEKZF;
    public byte SEKMTF;
    public byte MEKZF;
    public byte MEKMTF;
    public byte PolSchrabSchrab;
    public byte ALB;
    //THW
    public byte GLW;
    public byte MZW;
    public byte MTWTZ;
    public byte LKWK;
    public byte MLW;
    public byte BRmGR;
    public byte DLE;
    public byte LKWLKr;
    public byte TKW;
    public byte MzAB;
    public byte SchB;
    public byte Mzb;
    public byte MTWOV;
    public byte Hund;
    //SEG
    public byte GWSan;
    public byte ELWSEG;
    public byte KTWB;
    //Wasserrettung
    public byte GWWasser;
    public byte GWTauch;
    public byte MzB;
    //rettungshunde
    public byte RHF;


    public int Personal = 0;
    public int Wasser = 0;
    public void substract(Mission sub){
        //FW
        LF = (byte) (LF - sub.LF);
        TLF = (byte) (TLF - sub.TLF);
        ELW = (byte) (ELW - sub.ELW);
        DLK = (byte) (DLK - sub.DLK);
        RW = (byte) (RW - sub.RW);
        SW = (byte) (SW - sub.SW);
        GW = (byte) (GW - sub.GW);
        MTW = (byte) (MTW - sub.MTW);
        WLF = (byte) (WLF - sub.WLF);
        FwK = (byte) (FwK - sub.FwK);


        ALB = (byte) (ALB - sub.ALB);
        FuStW = (byte) (FuStW - sub.FuStW);
        Personal = Personal - sub.Personal;
        Wasser = Wasser - sub.Wasser;
    }
    public void printStats(){
        System.out.print("ID: "+missionId);
        System.out.print(" Name: "+name);
        //FW
        System.out.print(" LF: "+LF);
        System.out.print(" TLF: "+TLF);
        System.out.print(" ELW: "+ELW);
        System.out.print(" DLK: "+DLK);
        System.out.print(" RW: "+RW);
        System.out.print(" SW: "+SW);
        System.out.print(" GW: "+GW);
        System.out.print(" MTW: "+MTW);
        System.out.print(" WLF: "+WLF);
        System.out.print(" FwK: "+FwK);
        //
        System.out.println("");
    }
}
