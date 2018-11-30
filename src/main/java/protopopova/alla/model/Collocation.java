package protopopova.alla.model;

public class Collocation {
    Integer id;
    String mainWord;
    String pairWord;

    public Collocation(Integer id, String mainWord, String pairWord) {
        this.id = id;
        this.mainWord = mainWord;
        this.pairWord = pairWord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMainWord() {
        return mainWord;
    }

    public void setMainWord(String mainWord) {
        this.mainWord = mainWord;
    }

    public String getPairWord() {
        return pairWord;
    }

    public void setPairWord(String pairWord) {
        this.pairWord = pairWord;
    }

    public boolean isNew() {
        return id==null;
    }

    @Override
    public String toString() {
        return "Collocation{" +
                "id=" + id +
                ", mainWord='" + mainWord + '\'' +
                ", pairWord='" + pairWord + '\'' +
                '}';
    }
}