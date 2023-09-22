package org.example;


public class Wache {
    //FW
    public static int LF;
    public int avalibleLF;


    public static int TLF;
    public int avalibleTLF;


    public static int ELW;
    public int avalibleELW;


    public static int DLK;
    public int avalibleDLK;


    public static int RW;
    public int avalibleRW;


    public static int SW;
    public int avalibleSW;


    public static int GW;
    public int avalibleGW;


    public static int MTW;
    public int avalibleMTW;


    public static int WLF;
    public int avalibleWLF;


    public static int FwK;
    public int avalibleFwK;


    //FW flughafen
    public static int FLF;
    public int avalibleFLF;


    public static int RTF;
    public int avalibleRTF;


    //FW werk
    public static int GWW;
    public int avalibleGWW;


    public static int ULF;
    public int avalibleULF;


    public static int TM50;
    public int avalibleTM50;


    public static int TL;
    public int avalibleTL;


    //RTD
    public static int RTW;
    public int avalibleRTW;


    public static int NEF;
    public int avalibleNEF;


    public static int KTW;
    public int avalibleKTW;


    public static int LNA;
    public int avalibleLNA;


    public static int OrgL;
    public int avalibleOrgL;


    public static int GRTW;
    public int avalibleGRTW;


    public static int NAW;
    public int avalibleNAW;


    public static int ITW;
    public int avalibleITW;


    public static int RTWSchrabSchrab;
    public int avalibleRTWSchrabSchrab;


    //POL
    public static int FuStW;
    public int avalibleFuStW;


    public static int FuStM;
    public int avalibleFuStM;


    public static int BefKw;
    public int avalibleBefKw;


    public static int GruKw;
    public int avalibleGruKw;


    public static int GefKw;
    public int avalibleGefKw;


    public static int WaWe;
    public int avalibleWaWe;


    public static int SEKZF;
    public int avalibleSEKZF;


    public static int SEKMTF;
    public int avalibleSEKMTF;


    public static int MEKZF;
    public int avalibleMEKZF;


    public static int MEKMTF;
    public int avalibleMEKMTF;


    public static int PolSchrabSchrab;
    public int avaliblePolSchrabSchrab;


    public static int ALB;
    public int avalibleALB;


    //THW
    public static int GKW;
    public int avalibleGKW;


    public static int MZW;
    public int avalibleMZW;


    public static int MTWTZ;
    public int avalibleMTWTZ;


    public static int LKWK;
    public int avalibleLKWK;


    public static int MLW;
    public int avalibleMLW;


    public static int BRmGR;
    public int avalibleBRmGR;


    public static int DLE;
    public int avalibleDLE;


    public static int LKWLKr;
    public int avalibleLKWKr;


    public static int TKW;
    public int avalibleTKW;


    public static int MzAB;
    public int avalibleMzAB;


    public static int SchB;
    public int avalibleSchB;


    public static int Mzb;
    public int avalibleMzb;


    public static int MTWOV;
    public int avalibleMTWOV;


    public static int Hund;
    public int avalibleHund;


    //SEG
    public static int GWSan;
    public int avalibleGWSan;


    public static int ELWSEG;
    public int avalibleELWSEG;


    public static int KTWB;
    public int avalibleKTWB;


    //WasserRettung
    public static int GWWasser;
    public int avalibleGWWasser;


    public static int GWTauch;
    public int avalibleGWTauch;


    public static int MzB;
    public int avalibleMzB;


    //rettungshunde
    public static int RHF;
    public int avalibleRHF;




    public Wache(){
    }
    public Wache(int LF,int TLF,int ELW,int DLK,int RW,int SW,int MTW,int GW,int WLF,int FwK){
        //FW
        this.LF = LF;
        this.avalibleLF = LF;
        this.TLF = TLF;
        this.avalibleTLF = TLF;
        this.ELW = ELW;
        this.avalibleELW = ELW;
        this.DLK = DLK;
        this.avalibleDLK = DLK;
        this.RW = RW;
        this.avalibleRW = RW;
        this.SW = SW;
        this.avalibleSW = SW;
        this.MTW = MTW;
        this.avalibleMTW = MTW;
        this.GW = GW;
        this.avalibleGW = GW;
        this.WLF = WLF;
        this.avalibleWLF = WLF;
        this.FwK = FwK;
        this.avalibleFwK = FwK;
    }
    public void printStatus(){
        if(LF>0){
            System.out.print("LF: "+avalibleLF+"/"+LF);
        }
        if(TLF>0){
            System.out.print("TLF: "+avalibleTLF+"/"+TLF);
        }
        if(ELW>0){
            System.out.print(" ELW: "+avalibleELW+"/"+ELW);
        }
        if(DLK>0){
            System.out.print(" DLK: "+avalibleDLK+"/"+DLK);
        }
        if(RW>0){
            System.out.print(" RW: "+avalibleRW+"/"+RW);
        }
        if(SW>0){
            System.out.print(" SW: "+avalibleSW+"/"+SW);
        }
        if(GW>0){
            System.out.print(" GW: "+avalibleGW+"/"+GW);
        }
        if(WLF>0){
            System.out.print(" WLF: "+avalibleWLF+"/"+WLF);
        }
        if(FwK>0){
            System.out.print(" FwK: "+avalibleFwK+"/"+FwK);
        }
        System.out.println("");
    }
}
