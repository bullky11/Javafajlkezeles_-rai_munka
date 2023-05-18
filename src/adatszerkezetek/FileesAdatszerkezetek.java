package adatszerkezetek;

import progtetelek.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.HashSet;

public class FileesAdatszerkezetek {

    private List<String> sorok;
    private Fuvar[] fuvarok;

    public static void main(String[] args) throws IOException, ParseException {
        new FileesAdatszerkezetek().feladatok();
    }

    public FileesAdatszerkezetek() throws IOException, ParseException {
        sorok = Files.readAllLines(Path.of("fuvar.csv"));
        fuvarok = new Fuvar[sorok.size() - 1];
        for (int i = 1; i < sorok.size(); i++) {

            fuvarok[i - 1] = new Fuvar(sorok.get(i));

        }
        assert sorok.size() - 1 == fuvarok.length : "nincs meg minden fuvar!";
        assert fuvarok[0] != null : "első fuvar hibás";
        assert fuvarok[fuvarok.length - 1] != null : "utolsó fuvar hibás";
    }

    private void feladatok() throws IOException, ParseException {
        feladat1();
        feladat2();
        feladat3();
        feladat4();
        feladatok5();
        feladatok6();
        feladatok7();
        feladatok8();

    }

    private void feladat1() throws IOException {

        int i = sorok.size();
        System.out.println("1.feladat: sorok száma");
        System.out.printf("a fájl fejléccel eggyütt %d sort tartalmaz \n", i);
    }

    private void feladat2() throws ParseException {
        System.out.println("2.feladat: 1 fuvar adatait");
        String sor = "5240;2016-12-15 23:45:00;900;2,5;10,75;2,45;bankkártya";
        Fuvar fuvar = new Fuvar(sor);
        System.out.println(fuvar);
    }

    private void feladat3() throws ParseException {
        System.out.println("--- 3. feladat:1 véletlen fuvar adatai");
        int sorszam = (int) (Math.random() * sorok.size());
        sorszam = sorszam == 0 ? ++sorszam : sorszam;
        String sor = sorok.get(sorszam);
        Fuvar fuvar = new Fuvar(sor);
        System.out.println(fuvar);

    }

    private void feladat4() throws ParseException {
        System.out.println("milyen dátumú a legnagyobb távolságú fuvar");
        //maxkiválasztás tétele.

        int index = 0;
        for (int i = 1; i < fuvarok.length - 1; i++) {
            if (fuvarok[i].getTavolsag() > fuvarok[i].getTavolsag()) {
                index = i;
            }
        }
        System.out.println("távolság: " + fuvarok[index].getTavolsag());
        System.out.println("dátum: " + fuvarok[index].getIndulas());
    }

    private void feladatok5() {
        System.out.println("borravalók átlaga");
        double osszeg = 0;
        for (int i = 0; i < fuvarok.length; i++) {
            osszeg += fuvarok[i].getBorravalo();
        }
        Locale loc = Locale.ENGLISH;
        System.out.printf(loc, "%.2f \n ", osszeg / fuvarok.length);
    }

    private void feladatok6() {
        System.out.println("6.feladat minden fizetés bankkártyás");
        int i = 0, N = fuvarok.length;
        while (i < N && fuvarok[i].getFizetes_modja().equals("bankkártya")) {
            i++;
        }
        boolean mind = i > N;
        System.out.println(mind + " index:  " + i + " " + fuvarok[i].getFizetes_modja());
    }

    private void feladatok7() {
        System.out.println("7.feladat minden készpénzes fizetésnél és 0 ás borravaló");
        int i = 0, N = fuvarok.length;
        while (i < N && kpNullaBorravaloval(fuvarok[i])) {
            i++;
        }
    }

    private boolean kpNullaBorravaloval(Fuvar fuvar) {
        boolean kp = fuvar.getFizetes_modja().equals("készpénz");
        boolean borravaloNulla = fuvar.getBorravalo() == 0;
        return kp && borravaloNulla;
    }

    private void feladatok8() throws IOException {
        System.out.println("8.feladat: problémás kérdések");
        System.out.println("készpénzes fuvarok listája,konzolon és fájlban");
        List<Fuvar> kpFuvarok = new ArrayList<>();//mérete jelenleg 0 
        int i = 0;
        for (Fuvar fuvar : fuvarok) {
            if (fuvar.getFizetes_modja().equals("készpénz")) {
                kpFuvarok.add(fuvar);
            }
        }

        assert kpFuvarok.size() > 0 : "üres a listaa";
        String kimenet = "";
        for (Fuvar fuvar : kpFuvarok) {
            kimenet += fuvar + "\n";

        }
        Files.writeString(Path.of("teszt.txt"), kimenet);
        System.out.println("--milyen fizetési módok vanak rögzítve?");
        HashSet<String> fizmodok = new HashSet<>();
        for (Fuvar fuvar : fuvarok) {
            fizmodok.add(fuvar.getFizetes_modja());
        }
        for (String fizmod : fizmodok) {
            System.out.println(fizmod);
        }

    }
}
