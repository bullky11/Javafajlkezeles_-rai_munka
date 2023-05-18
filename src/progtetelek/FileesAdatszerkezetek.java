package progtetelek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;

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

            fuvarok[i-1] = new Fuvar(sorok.get(i));

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
       
        int index=0;
        for (int i = 1; i < fuvarok.length-1; i++) {
            if (fuvarok[i].getTavolsag()> fuvarok[i].getTavolsag()) {
                index=i;   
            }
        }
        System.out.println("távolság: "+ fuvarok[index].getTavolsag());
        System.out.println("dátum: "+ fuvarok[index].getIndulas());
    }

    private void feladatok5() {
    }
}