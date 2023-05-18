package progtetelek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

public class Fuvar {

    private int taxi_id, idotartam;
    private String indulas, fizetes_modja;
    private double tavolsag, viteldij, borravalo;

    public Fuvar(String sor) throws ParseException {
        sor = sor.replace(',', '.');

        String[] s = sor.split(";");
        this.taxi_id = Integer.parseInt(s[0]);
        this.indulas = s[1];
        this.idotartam = Integer.parseInt(s[2]);

        NumberFormat nf = NumberFormat.getInstance();
        Number num = nf.parse(s[3]);
        this.tavolsag = num.doubleValue();

        this.viteldij = Double.parseDouble(s[4]);
        this.borravalo = Double.parseDouble(s[5]);
        this.fizetes_modja = s[6];

    }

    public Fuvar(int taxi_id, String indulas, int idotartam, double tavolsag, double viteldij, double borravalo, String fizetes_modja) {

    }

    public int getTaxi_id() {

        return taxi_id;
    }

    public int getIdotartam() {
        return idotartam;
    }

    public String getIndulas() {
        return indulas;
    }

    public String getFizetes_modja() {
        return fizetes_modja;
    }

    public double getTavolsag() {
        return tavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    @Override
    public String toString() {
        return "Fuvar{" + "taxi_id=" + taxi_id + ", idotartam=" + idotartam + ", indulas=" + indulas + ", fizetes_modja=" + fizetes_modja + ", tavolsag=" + tavolsag + ", viteldij=" + viteldij + ", borravalo=" + borravalo + '}';
    }

}
