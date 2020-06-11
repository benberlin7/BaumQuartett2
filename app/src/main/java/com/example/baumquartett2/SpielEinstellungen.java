package com.example.baumquartett2;

public class SpielEinstellungen {
    static Long Zeit;
    static boolean ZeitAktiv;
    static int AnzahlKarten;
    static boolean KartenDurchschalten;
    static boolean Ton = true;
    static boolean vsAI;
    static boolean music = false;
    static int maxVolume = 10;
    static int AILevel = 1;
    static int background = 11;
    static String Spieler1 = "S1";
    static String Spieler2 = "S2";

    public SpielEinstellungen(){
        Long Zeit = Long.valueOf(3);
        AnzahlKarten=2;
        ZeitAktiv = false;
        KartenDurchschalten = false;
        Ton = true;
        vsAI = true;
        AILevel = 1;
        Spieler1 = "S1";
        Spieler2 = "S2";
        background = 0;
    }

    public void setZeit(Long tmpZeit){ Zeit = tmpZeit;};
    public Long getZeit(){
        return Zeit;
    };

    public boolean getZeitAktiv(){return ZeitAktiv;};
    public void setZeitAktiv(boolean tmp){ZeitAktiv = tmp;};

    public boolean getKartenDurchschalten(){return KartenDurchschalten;};
    public void setKartenDurchschalten(boolean tmp){KartenDurchschalten = tmp;};

    public boolean getMusic(){return music;};
    public void setMusic(boolean tmp){music = tmp;};

    public boolean getAI(){return vsAI;};
    public void setAI(boolean tmp){vsAI = tmp;};

    public int getAnzahlKarten(){
        return AnzahlKarten;
    };
    public void setAnzahlKarten(int anzahlKarten) {
        AnzahlKarten = anzahlKarten;
    };

    public int getMaxVolume(){
        return maxVolume;
    };
    public void setMaxVolume(int t) {
        maxVolume = t;
    };

    public int getAILevel(){
        return AILevel;
    };
    public void setAILevel(int tmp) {
        AILevel = tmp;
    };

    public int getBackground(){
        return background;
    };
    public void setBackground(int tmp) {
        background = tmp;
    };

    public String getSpieler1() { return Spieler1;};
    public void setSpieler1(String tmp) { Spieler1 = tmp;};

    public String getSpieler2() { return Spieler2;};
    public void setSpieler2(String tmp) { Spieler2 = tmp;};

}
