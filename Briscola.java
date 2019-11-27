
import giochicarte.briscola.*;
import java.util.*;

public class Briscola {

  public static void main(String[] args) {
    System.out.println("\t *** Benvenuto nel gioco della Briscola ***");
    System.out.println("Scegli il tuo nickname: ");
    System.out.print(">> ");
    Scanner in = new Scanner(System.in);
    Giocatore giocatore = new Giocatore(in.nextLine());
    Giocatore cpu = new CPU();
    System.out.println("\t Benvenuto "+giocatore.getNick());

    Giocatore primoGiocatore = giocatore;
    Giocatore secondoGiocatore = cpu;

    Mazzo mazzo = new Mazzo();
    mazzo.mescola();
    String semeBriscola = mazzo.popBriscola();
    mazzo.distribuisci(primoGiocatore, secondoGiocatore);

    while(giocatore.getSizeMano() != 0 && cpu.getSizeMano() != 0){
      System.out.println("\n*****************************************************\n");

      System.out.println("\tIl seme di briscola è: "+semeBriscola);
      Carta c1 = primoGiocatore.lancia();
      System.out.println("\t"+primoGiocatore.getNick()+" ha lanciato: "+c1);
      Carta c2 = secondoGiocatore.lancia();
      System.out.println("\t"+secondoGiocatore.getNick()+" ha lanciato: "+c2);
      boolean winner = Carta.comparaCarte(c1,c2,semeBriscola);
      Giocatore vincitore = (winner)? primoGiocatore : secondoGiocatore;
      System.out.println("\tVince la mano : "+ vincitore.getNick());
      if (vincitore == secondoGiocatore){
        secondoGiocatore.addPunteggio(c1.getValore() + c2.getValore());
        Giocatore temp = primoGiocatore;
        primoGiocatore = secondoGiocatore;
        secondoGiocatore = temp;
      }else{
        primoGiocatore.addPunteggio(c1.getValore() + c2.getValore());
      }

      mazzo.distribuisci(primoGiocatore);
      mazzo.distribuisci(secondoGiocatore);
      System.out.println("Il tuo punteggio: "+giocatore.getPunteggio());
      System.out.println("Il punteggio della CPU: "+cpu.getPunteggio());

      System.out.println("\n*****************************************************\n");

    }


    System.out.println("Il vincitore è: "+ ((cpu.getPunteggio()>=61)? cpu.getNick() : giocatore.getNick()));


  }
}