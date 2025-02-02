package org.example.splitwise.repositories;

import org.example.splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    @Override
    Optional<Group> findById(Long aLong);

    @Override
    <S extends Group> S save(S entity);
}
