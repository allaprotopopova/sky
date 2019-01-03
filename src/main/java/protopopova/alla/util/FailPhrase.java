package protopopova.alla.util;

public enum FailPhrase {

    WHOOF("WHOOF! WHOOF!"),
    HIT_HEAD("You'll hit you head!"),
    BE_GENTLE("I must be gentle, be gentle...."),
    PULLING_LEG("You just pulling my leg!"),
    FOCUS("Seriously? Focus!"),
    DO_IT_AGAIN("Wrong! Do it again!"),
    WRONG("Wrong!"),
    ALMOST("Almost....but not!");


    String text;

    FailPhrase(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
