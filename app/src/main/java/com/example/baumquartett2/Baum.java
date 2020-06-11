package com.example.baumquartett2;

public class Baum {

    private String kartenNr;
    //private int bildSelektor

    private String name;
    private String familie;
    private String gattung;
    private String beschreibung;
    private String blattaufbau; //Nadelbaum, Laubbaum

    private int minWuchshoehe,maxWuchshoehe,aktuellWuchshoehe; //0: 0-14m ; 1: 15-30m ; 2: 31-40m ; 3: 40m+
    private int seltenheit;
    private int minGartenwert,maxGartenwert,aktuellGartenwert;
    private int minFrosthaerte,maxFrosthaerte,aktuellFrosthaerte;
    private int minHolzwert,maxHolzwert,aktuellHolzwert;
    private int giftigkeit; //0-keine 1-leicht 2-mittel 3-stark

    public Baum() //default Constructor
    {
        seltenheit=0;
        kartenNr = "Z4";
        beschreibung ="";
        name =  "";
        familie =  "";
        gattung =  "";
        blattaufbau =  "";
        minWuchshoehe = 0;
        maxWuchshoehe = 0;
        aktuellWuchshoehe = 0 ;
        minGartenwert = 0;
        maxGartenwert = 0;
        aktuellGartenwert = 0;
        minFrosthaerte = 0;
        maxFrosthaerte = 0;
        aktuellFrosthaerte = 0;
        minHolzwert = 0;
        maxHolzwert = 0;
        giftigkeit = 0;
    }

    //define all values by constructor

    public Baum(String nr, String tmpBesch, String tmpName, String tmpFamilie, String tmpGattung, String tmpBlatt, int tseltenheit, int minW, int maxW, int minG, int maxG, int minF, int maxF, int minH, int maxH, int tmpGift)
    {
        seltenheit = tseltenheit;
        kartenNr = nr;
        beschreibung = tmpBesch;
        name = tmpName;
        familie = tmpFamilie;
        gattung = tmpGattung;
        blattaufbau = tmpBlatt;
        minWuchshoehe = minW;
        maxWuchshoehe = maxW;
        aktuellWuchshoehe = minW;
        minGartenwert = minG;
        maxGartenwert = maxG;
        aktuellGartenwert = minG;
        minFrosthaerte = minF;
        maxFrosthaerte = maxF;
        aktuellFrosthaerte = minF;
        minHolzwert = minH;
        maxHolzwert = maxH;
        aktuellHolzwert = minH;
        giftigkeit = tmpGift;

        aktuellWuchshoehe= (int)(Math.random() * (maxWuchshoehe-minWuchshoehe) + minWuchshoehe);
        aktuellGartenwert= (int)(Math.random() * (minGartenwert-maxGartenwert) + maxGartenwert);
        aktuellFrosthaerte= (int) (Math.random() * (maxFrosthaerte-minFrosthaerte) + minFrosthaerte);
        aktuellHolzwert = (int)(Math.random() * (minHolzwert-maxHolzwert) + maxHolzwert);
    }

    //Comfortable Constructor (with all values actually in the game atm)
    //define all values by constructor

    public Baum(String nr, String tmpBesch, String tmpName, String tmpFamilie, String tmpGattung, String tmpBlatt, int tseltenheit, int maxW, int maxH, int tmpGift)
    {
        seltenheit = tseltenheit;
        kartenNr = nr;
        beschreibung = tmpBesch;
        name = tmpName;
        familie = tmpFamilie;
        gattung = tmpGattung;
        maxWuchshoehe = maxW;
        maxHolzwert = maxH;
        blattaufbau = tmpBlatt;
        giftigkeit = tmpGift;
    }


    /*
    copy constructor
    public Baum(Baum tmpBaum) {
        this(tmpBaum.getBild(), tmpBaum.getName(),tmpBaum.getFamilie(),tmpBaum.getGattung(),tmpBaum.getBlatt(),tmpBaum.getMinHoehe(),tmpBaum.getMaxHoehe(),tmpBaum.getMinGW(),tmpBaum.getMaxGW(),tmpBaum.getMinFH(),tmpBaum.getMaxFH(),tmpBaum.getMinHW(),tmpBaum.getMaxHW(),tmpBaum.getGiftigkeit());
    }
    */


    //public void setBild (int auswahl) {bildSelektor=auswahl;};
    public void setName (String tmpName) {name=tmpName;};
    public void setFamilie (String tmpFamilie) {familie=tmpFamilie;};
    public void setGattung (String tmpGattung) {gattung=tmpGattung;};
    public void setBlatt (String tmpBlatt) {blattaufbau=tmpBlatt;};

    public void mittelWerteBerechnen()
    {
        this.aktuellWuchshoehe= (int)(Math.random() * (maxWuchshoehe-minWuchshoehe) + minWuchshoehe);
        this.aktuellGartenwert= (int)(Math.random() * (minGartenwert-maxGartenwert) + maxGartenwert);
        this.aktuellFrosthaerte= (int) (Math.random() * (maxFrosthaerte-minFrosthaerte) + minFrosthaerte);
        this.aktuellHolzwert = (int)(Math.random() * (minHolzwert-maxHolzwert) + maxHolzwert);
    }

    //public int getBild () {return bildSelektor;};
    public String getName () {return name;};
    public String getBeschreibung () {return beschreibung;};
    public String getFamilie () {return familie;};
    public int getSeltenheit(){return seltenheit;};
    public int getGiftigkeit(){return giftigkeit;};
    public String getGattung () {return gattung;};
    public String getBlatt () {return blattaufbau;};
    public int getMaxWuchshoehe () {return maxWuchshoehe;};
    public int getGartenwert () {return aktuellGartenwert;};
    public int getFrosthaerte () {return aktuellFrosthaerte;};
    public int getHolzwert () {return maxHolzwert;};
    public String getKartenNr () {return kartenNr;};
}



