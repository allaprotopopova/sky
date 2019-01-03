package protopopova.alla.util;

public enum SuccessPhrase {
    GREATE_JOB("Greate job!"),
    GOOD("Good!"),
    DO_SOME_MORE("Yes! Do some more!"),
    EXCELLENT("Excellent!"),
    REALLY_NICE("It's really nice!"),
    I_KNEW_YOU_CAN("I knew that you can!"),
    NOT_BAD("Not bad!");


    String text;

    SuccessPhrase(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
