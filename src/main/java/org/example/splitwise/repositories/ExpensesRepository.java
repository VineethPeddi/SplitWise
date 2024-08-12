package org.example.splitwise.repositories;

import org.example.splitwise.models.Expense;
import org.example.splitwise.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpensesRepository extends JpaRepository<Expense,Long> {
    @Override
    Optional<Expense> findById(Long aLong);

    @Override
    <S extends Expense> S save(S entity);

    List<Expense> findAllByGroup(Group group);
}
