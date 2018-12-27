package protopopova.alla.model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "collocations", uniqueConstraints = {@UniqueConstraint(columnNames = {"mainword", "pairword"}, name = "collocations_unique_mainword_pairword_idx")})
public class Collocation extends AbstractBaseEntity {

    @Column(name = "mainword", nullable = false)
    @NotBlank
    @Size(min = 2, max = 140)
    private String mainWord;

    @Column(name = "pairword", nullable = false)
    @NotBlank
    @Size(min = 2, max = 140)
    private String pairWord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private WordsGroup wordsGroup;


    public Collocation() {
    }

    public Collocation(Integer id, String mainWord, String pairWord) {
        super(id);
        this.mainWord = mainWord;
        this.pairWord = pairWord;
    }

    public Collocation(String mainWord, String pairWord) {
        this(null, mainWord, pairWord);
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

    public WordsGroup getWordsGroup() { return wordsGroup; }

    public void setWordsGroup(WordsGroup wordsGroup) {
        this.wordsGroup = wordsGroup;
    }

    public boolean isNew() {
        return id == null;
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
