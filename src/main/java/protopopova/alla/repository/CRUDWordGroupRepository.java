package protopopova.alla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import protopopova.alla.model.Collocation;
import protopopova.alla.model.WordGroup;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CRUDWordGroupRepository extends JpaRepository<WordGroup, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM WordGroup u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    WordGroup save(WordGroup wordGroup);

    @Override
    Optional<WordGroup> findById(Integer id);

    @Override
    List<WordGroup> findAll();

}
