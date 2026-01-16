import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UI {
    Scanner scanner = new Scanner(System.in);
    private List<Player> players = new ArrayList<>();
    private QuestionDeck deck = new QuestionDeck();
    private QuestionCard currentCard;
    private Player impostor;


    public void start() {
        welcomeScreen();
        askPlayers();
        while (true) {
            runRound();
            showResults();

            System.out.println("Seuraava kysymys? (kyllä/ei)");
            if (!scanner.nextLine().trim().equalsIgnoreCase("kyllä")) {
                System.out.println("Peli päättyy.");
                break;
            }
        }
    }

    private void welcomeScreen() {
        System.out.println("====================================");
        System.out.println("      IMPOSTOR - KYSYMYSPELI        ");
        System.out.println("====================================\n");
    }

    private void askPlayers() {
        System.out.print("Kuinka monta pelaajaa? ");
        int count = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= count; i++) {
            System.out.print("Pelaaja " + i + " nimi: ");
            players.add(new Player(scanner.nextLine()));
        }
    }

    private void runRound() {
        for (Player p : players) {
            p.setHasAnswered(false);
            p.setQuestion(null);
            p.setAnswer(null);
        }
        System.out.println("\n--- KIERROS ALKAA! ---\n");

        // Valitaan satunnainen kortti, jota ei ole käytetty aiemmin
        currentCard = deck.drawCard();

        // Arvotaan impostor
        impostor = players.get(new Random().nextInt(players.size()));

        // Jaetaan kysymykset
        for (Player p : players) {
            if (p.equals(impostor)) {
                p.setQuestion(currentCard.getImpostorQuestion());
            } else {
                p.setQuestion(currentCard.getCommonQuestion());
            }
        }

        // Jokainen pelaaja avaa korttinsa
        for (Player p : players) {
            System.out.println("\nPelaaja: " + p.getName());
            System.out.println("Paina ENTER avataksesi kortin...");
            scanner.nextLine();

            if (!p.isHasAnswered()) {
                System.out.println("Kysymyksesi:\n" + p.getQuestion());
                p.setHasAnswered(true);
            }

            System.out.print("Vastauksesi: ");
            p.setAnswer(scanner.nextLine());

            System.out.println("Kortti lukittu.");
        }
    }
    private void showResults() {
        System.out.println("\n====================================");
        System.out.println("          TULOKSET");
        System.out.println("====================================");
        System.out.println("Yleinen kysymys oli: " + currentCard.getCommonQuestion());
        for (Player p : players) {
            System.out.println(p.getName() + " vastasi: " + p.getAnswer());
        }
        System.out.println("Paljasta impostor painamalla enter");
        scanner.nextLine();
        System.out.println("Impostor oli: " + impostor.getName());
        System.out.println("Impostorin kysymys oli: " + impostor.getQuestion());
    }

}
