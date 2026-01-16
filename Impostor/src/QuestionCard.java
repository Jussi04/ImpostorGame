public class QuestionCard {
    private String commonQuestion;
    private String impostorQuestion;

    public QuestionCard(String commonQuestion, String impostorQuestion) {
        this.commonQuestion = commonQuestion;
        this.impostorQuestion = impostorQuestion;
    }

    public String getCommonQuestion() {
        return commonQuestion;
    }

    public String getImpostorQuestion() {
        return impostorQuestion;
    }
}
