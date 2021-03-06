package protopopova.alla.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import protopopova.alla.model.Collocation;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CRUDCollocationRepository extends JpaRepository<Collocation, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Collocation u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Collocation u WHERE u.wordGroup.id=:groupId")
    int deleteByGroupId(@Param("groupId") int groupId);

    @Override
    Collocation save(Collocation collocation);

    @Override
    Optional<Collocation> findById(Integer id);

    @Query("SELECT c FROM Collocation c WHERE c.wordGroup.id=:groupId ORDER BY c.mainWord DESC")
    List<Collocation> getAll(@Param("groupId") int groupId);
}
