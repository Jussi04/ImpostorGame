import java.util.*;

public class QuestionDeck {

    private List<QuestionCard> cards = new ArrayList<>();
    private Set<QuestionCard> usedCards = new HashSet<>();
    private Random random = new Random();

    public QuestionDeck() {
        loadCards();
    }

    private void loadCards() {
        cards.add(new QuestionCard(
                "Mikä on paras jäätelömaku?",
                "Mikä on huonoin jäätelömaku?"
        ));

        cards.add(new QuestionCard(
                "Missä maassa haluaisit lomalla käydä?",
                "Missä maassa et ikinä haluaisi käydä?"
        ));

        cards.add(new QuestionCard(
                "Mikä eläin on söpöin?",
                "Mikä eläin on pelottavin?"
        ));
    }


    // Anna satunnainen käyttämätön kortti
    public QuestionCard drawCard() {
        if (usedCards.size() == cards.size()) {
            throw new IllegalStateException("Kaikki kortit on käytetty.");
        }

        QuestionCard card;
        do {
            card = cards.get(random.nextInt(cards.size()));
        } while (usedCards.contains(card));

        usedCards.add(card);
        return card;
    }
}
