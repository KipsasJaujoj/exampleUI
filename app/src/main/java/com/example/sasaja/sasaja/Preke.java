package com.example.sasaja.sasaja;

/**
 * Created by vaido on 01/10/2018.
 */

public class Preke {
    public String pavadinimas;
    public double kaina;
    public Preke(String p, double k) {
        pavadinimas = p;
        kaina = k;
    }
    public String gautiPavadinima() {
        return pavadinimas;
    }
    public double gautiKaina() {
        return kaina;
    }
}
