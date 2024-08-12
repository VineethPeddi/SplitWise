package org.example.splitwise.repositories;

import org.example.splitwise.models.Expense;
import org.example.splitwise.models.UserExpense;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserExpensesRepository extends JpaRepository<UserExpense,Long> {
    @Override
    Optional<UserExpense> findById(Long aLong);

    @Override
    <S extends UserExpense> S save(S entity);

    <S extends UserExpense> List<S> findAllByExpense(Expense example);
}
