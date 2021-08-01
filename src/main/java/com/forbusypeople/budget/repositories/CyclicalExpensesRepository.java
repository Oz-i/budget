package com.forbusypeople.budget.repositories;

import com.forbusypeople.budget.repositories.entities.CyclicalExpensesEntity;
import com.forbusypeople.budget.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CyclicalExpensesRepository extends JpaRepository<CyclicalExpensesEntity, UUID> {

    List<CyclicalExpensesEntity> findAllByUser(UserEntity user);

}
