/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.edunova.ciklicnatablica;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;



public class Tablica {

    private static int COUNTER;
    private int redovi;
    private int stupci;
    private int smjer;
    private int pocetak;
    private int minRedovi;
    private int maxRedovi;
    private int minStupci;
    private int maxStupci;
    private int umnozak;
    private int sirinaStupca;
    private int visinaReda;
    private int ukupnaSirina;
    private int ukupnaVisina;
    private int[][] spiralnaMatrica;
    JTable tablica; 
    JFrame okvir;

    /**
     * Creates new form Tablica
     *
     * @param redovi
     * @param stupci
     * @param smjer
     * @param pocetak
     */
    public Tablica(int redovi, int stupci, int smjer, int pocetak) {
        this.redovi = redovi;
        this.stupci = stupci;
        this.smjer = smjer + 1;
        this.pocetak = pocetak + 1;
        this.minRedovi = 0;
        this.maxRedovi = redovi - 1;
        this.minStupci = 0;
        this.maxStupci = stupci - 1;
        this.umnozak = redovi * stupci;
       
        this.spiralnaMatrica = new int[redovi][stupci];
        COUNTER = 1;
       unesiMjere();
    } 
    
    private void unesiMjere(){
      if(this.umnozak<100){
          this.sirinaStupca = 20;
          this.visinaReda = 20;
      }
      else if(this.umnozak>= 100 && this.umnozak<1000){
          this.sirinaStupca = 25;
          this.visinaReda = 25;
      }
      else if(this.umnozak>=1000){
          this.sirinaStupca = 32;
          this.visinaReda = 32;
      }
    this.ukupnaSirina = sirinaStupca*stupci;
    this.ukupnaVisina = visinaReda*redovi;
    }
    
    public void kreirajTablicu() {
        DefaultTableModel tableModel = new DefaultTableModel();
        TableColumn column;
        for (int i = 1; i <= stupci; i++) {
            column = new TableColumn();
            column.setPreferredWidth(sirinaStupca);
            tableModel.addColumn("");
        }
        String[] data = new String[stupci];
        for (int j = 1; j <= redovi; j++) {
            tableModel.addRow(data);
        }
        tablica = new JTable(tableModel);
        tablica.getTableHeader().setVisible(false);
        tablica.setRowHeight(visinaReda);
        popuniMatricu();    
        
        okvir = new JFrame();
        okvir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        okvir.setSize(ukupnaSirina,ukupnaVisina+38);
        okvir.setResizable(false);
        okvir.setVisible(true);
        okvir.add(tablica);
    }
    
    private void popuniMatricu() {
        // ako je u smjeru kazaljke na satu
        if (smjer == 1) {
            switch (pocetak) {
                case 1 ->
                    kreirajSpiralnuMatricuClockwisePozicija1();
                case 2 ->
                    kreirajSpiralnuMatricuClockwisePozicija2();
                case 3 ->
                    kreirajSpiralnuMatricuClockwisePozicija3();
                case 4 ->
                    kreirajSpiralnuMatricuClockwisePozicija4();
            }
            // ako je u suprotnom smjeru
        } else {
            switch (pocetak) {
                case 1 ->
                    kreirajSpiralnuMatricuCounterClockwisePozicija1();
                case 2 ->
                    kreirajSpiralnuMatricuCounterClockwisePozicija2();
                case 3 ->
                    kreirajSpiralnuMatricuCounterClockwisePozicija3();
                case 4 ->
                    kreirajSpiralnuMatricuCounterClockwisePozicija4();
            }
        }
        popuniTablicu();
    }

    /**
     * *********************************
     * ispis U SMJERU kazaljke na satu *
     ***********************************
     */
    // 1.1 PO�ETNA POZICIJA je GORE LIJEVO
    private void kreirajSpiralnuMatricuClockwisePozicija1() {
        petljaWhile:
        while (COUNTER <= umnozak) {
            // ispis reda sa lijeve na desnu stranu
            for (int i = minStupci; i <= maxStupci; i++) {
                spiralnaMatrica[minRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od gore prema dolje
            for (int i = minRedovi + 1; i <= maxRedovi; i++) {
                spiralnaMatrica[i][maxStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa desne na lijevu stranu
            for (int i = maxStupci - 1; i >= minStupci; i--) {
                spiralnaMatrica[maxRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od dolje prema gore
            for (int i = maxRedovi - 1; i >= minRedovi + 1; i--) {
                spiralnaMatrica[i][minStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            smanjiOkvirMatrice();
        }
    }

    // 1.2 POČETNA POZICIJA je GORE DESNO
    private void kreirajSpiralnuMatricuClockwisePozicija2() {
        petljaWhile:
        while (COUNTER <= umnozak) {
            // ispis stupca od gore prema dolje
            for (int i = minRedovi; i <= maxRedovi; i++) {
                spiralnaMatrica[i][maxStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa desne na lijevu stranu
            for (int i = maxStupci - 1; i >= minStupci; i--) {
                spiralnaMatrica[maxRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od dolje prema gore
            for (int i = maxRedovi - 1; i >= minRedovi + 1; i--) {
                spiralnaMatrica[i][minStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa lijeve na desnu stranu
            for (int i = minStupci; i <= maxStupci - 1; i++) {
                spiralnaMatrica[minRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            smanjiOkvirMatrice();
        }
    }

    // 1.3 POČETNA POZICIJA je DOLJE LIJEVO
    private void kreirajSpiralnuMatricuClockwisePozicija3() {
        petljaWhile:
        while (COUNTER <= umnozak) {
            // ispis stupca od dolje prema gore
            for (int i = maxRedovi; i >= minRedovi; i--) {
                System.out.println("[" + i + "][" + minStupci + "] (" + COUNTER + ")");
                spiralnaMatrica[i][minStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa lijeve na desnu stranu
            for (int i = minStupci + 1; i <= maxStupci; i++) {
                System.out.println("[" + minRedovi + "][" + i + "] (" + COUNTER + ")");
                spiralnaMatrica[minRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od gore prema dolje
            for (int i = minRedovi + 1; i <= maxRedovi; i++) {
                System.out.println("[" + i + "][" + maxStupci + "] (" + COUNTER + ")");
                spiralnaMatrica[i][maxStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa desne na lijevu stranu
            for (int i = maxStupci - 1; i >= minStupci + 1; i--) {
                System.out.println("[" + maxRedovi + "][" + i + "] (" + COUNTER + ")");
                spiralnaMatrica[maxRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            smanjiOkvirMatrice();
        }
    }

    // 1.4 POČETNA POZICIJA je DOLJE DESNO
    public void kreirajSpiralnuMatricuClockwisePozicija4() {
        petljaWhile:
        while (COUNTER <= umnozak) {
            // ispis reda sa desne na lijevu stranu
            for (int i = maxStupci; i >= minStupci; i--) {
                spiralnaMatrica[maxRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od dolje prema gore
            for (int i = maxRedovi - 1; i >= minRedovi; i--) {
                spiralnaMatrica[i][minStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa lijeve na desnu stranu
            for (int i = minStupci + 1; i <= maxStupci; i++) {
                spiralnaMatrica[minRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od gore prema dolje
            for (int i = minRedovi + 1; i <= maxRedovi - 1; i++) {
                spiralnaMatrica[i][maxStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            smanjiOkvirMatrice();
        }
    }

    /**
     * **********************************************
     * ispis u smjeru SUPROTNOM od kazaljke na satu *
     ************************************************
     */
    // 2.1 POČETNA POZICIJA je GORE LIJEVO
    private void kreirajSpiralnuMatricuCounterClockwisePozicija1() {

        petljaWhile:
        while (COUNTER <= umnozak) {
            // ispis stupca od gore prema dolje
            for (int i = minRedovi; i <= maxRedovi; i++) {
                spiralnaMatrica[i][minStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa lijeve na desnu stranu
            for (int i = minStupci + 1; i <= maxStupci; i++) {
                spiralnaMatrica[maxRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od dolje prema gore
            for (int i = maxRedovi - 1; i >= minRedovi; i--) {
                spiralnaMatrica[i][maxStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa desne na lijevu stranu
            for (int i = maxStupci - 1; i >= minStupci + 1; i--) {
                spiralnaMatrica[minRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            smanjiOkvirMatrice();
        }
    }

    // 2.2 POČETNA POZICIJA je GORE DESNO
    private void kreirajSpiralnuMatricuCounterClockwisePozicija2() {
        petljaWhile:
        while (COUNTER <= umnozak) {
            // ispis reda sa desne na lijevu stranu
            for (int i = maxStupci; i >= minStupci; i--) {
                spiralnaMatrica[minRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od gore prema dolje
            for (int i = minRedovi + 1; i <= maxRedovi; i++) {
                spiralnaMatrica[i][minStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa lijeve na desnu stranu
            for (int i = minStupci + 1; i <= maxStupci; i++) {
                spiralnaMatrica[maxRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od dolje prema gore
            for (int i = maxRedovi - 1; i >= minRedovi + 1; i--) {
                spiralnaMatrica[i][maxStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            smanjiOkvirMatrice();
        }
    }

    // 2.3 POČETNA POZICIJA je DOLJE LIJEVO
    private void kreirajSpiralnuMatricuCounterClockwisePozicija3() {
        petljaWhile:
        while (COUNTER <= umnozak) {

            // ispis reda sa lijeve na desnu stranu
            for (int i = minStupci; i <= maxStupci; i++) {
                spiralnaMatrica[maxRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od dolje prema gore
            for (int i = maxRedovi - 1; i >= minRedovi; i--) {
                spiralnaMatrica[i][maxStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa desne na lijevu stranu
            for (int i = maxStupci - 1; i >= minStupci; i--) {
                spiralnaMatrica[minRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od gore prema dolje
            for (int i = minRedovi + 1; i <= maxRedovi - 1; i++) {
                spiralnaMatrica[i][minStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            smanjiOkvirMatrice();
        }
    }

    // 2.4 POČETNA POZICIJA je DOLJE DESNO
    private void kreirajSpiralnuMatricuCounterClockwisePozicija4() {
        petljaWhile:
        while (COUNTER <= umnozak) {
            // ispis stupca od dolje prema gore
            for (int i = maxRedovi; i >= minRedovi; i--) {
                spiralnaMatrica[i][maxStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa desne na lijevu stranu
            for (int i = maxStupci - 1; i >= minStupci; i--) {
                spiralnaMatrica[minRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis stupca od gore prema dolje
            for (int i = minRedovi + 1; i <= maxRedovi; i++) {
                spiralnaMatrica[i][minStupci] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            // ispis reda sa lijeve na desnu stranu
            for (int i = minStupci + 1; i <= maxStupci - 1; i++) {
                spiralnaMatrica[maxRedovi][i] = COUNTER;
                COUNTER++;
                if (COUNTER > umnozak) {
                    break petljaWhile;
                }
            }
            smanjiOkvirMatrice();
        }
    }
   
    private void popuniTablicu() {
        for (int i = 0; i < redovi; i++) {
            for (int j = 0; j < stupci; j++) {
                tablica.setValueAt(spiralnaMatrica[i][j], i, j);
            }
        }
    }

    private void smanjiOkvirMatrice() {
        minRedovi++;
        minStupci++;
        maxRedovi--;
        maxStupci--;
    }

   
}

