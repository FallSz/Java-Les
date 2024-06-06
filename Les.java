/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author lenovo
 */
public class Kursus {
    private int idKursus;
    private String namaPengajar;
    private int harga;
    private String subjek;

    public Kursus(int idKursus, String namaPengajar, int harga, String subjek) {
        this.idKursus = idKursus;
        this.namaPengajar = namaPengajar;
        this.harga = harga;
        this.subjek = subjek;
    }

    public int getIdKursus() {
        return idKursus;
    }

    public String getNamaPengajar() {
        return namaPengajar;
    }

    public int getHarga() {
        return harga;
    }

    public String getSubjek() {
        return subjek;
    }
}
