package protopopova.alla.model;


import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "words_groups", uniqueConstraints = {@UniqueConstraint(columnNames = "name", name = "words_groups_unique_name_idx")})
public class WordGroup extends AbstractBaseEntity {


    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wordGroup")
    private List<Collocation> collocations;

    public WordGroup() {
    }

    public WordGroup(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public WordGroup(String name) {
        this(null, name, null);
    }

    public WordGroup(Integer id, String name, List<Collocation> collocations) {
        super(id);
        this.name = name;
        this.collocations = collocations==null? new ArrayList<>() : collocations;
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
        return "WordGroup{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
