package protopopova.alla.model;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "words_groups", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "words_groups_unique_name_idx")})
public class WordsGroup extends AbstractBaseEntity {


    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wordsGroup")
    private List<Collocation> collocations;

    public WordsGroup() {
    }

    public WordsGroup(Integer id,  String name, List<Collocation> collocations) {
        super(id);
        this.name = name;
        this.collocations = collocations==null? Collections.emptyList() : collocations;
    }

    public String  getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Collocation> getCollocations() {
        return collocations;
    }

    public void setCollocations(List<Collocation> collocations) {
        this.collocations = collocations;
    }

    @Override
    public String toString() {
        return "WordsGroup{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
