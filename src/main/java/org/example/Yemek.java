package org.example;

import java.math.BigDecimal;

public class Yemek {

    private String adi;
    private BigDecimal qiymeti;
    private int kalori;

    private YemekKatagory katagory;


    public Yemek(String adi, BigDecimal qiymeti, int kalori, YemekKatagory katagory) {
        this.adi = adi;
        this.qiymeti = qiymeti;
        this.kalori = kalori;
        this.katagory = katagory;

    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public BigDecimal getQiymeti() {
        return qiymeti;
    }

    public void setQiymeti(BigDecimal qiymeti) {
        this.qiymeti = qiymeti;
    }

    public int getKalori() {
        return kalori;
    }

    public void setKalori(int kalori) {
        this.kalori = kalori;
    }

    public YemekKatagory getKatagory() {
        return katagory;
    }

    public void setKatagory(YemekKatagory katagory) {
        this.katagory = katagory;
    }


    @Override
    public String toString() {
        return "Yemek{" +
                "adi='" + adi + '\'' +
                ", qiymeti=" + qiymeti +
                ", kalori=" + kalori +
                ", katagory=" + katagory +
                '}';
    }
}
