package com.db.referencedata.repository;

import com.db.referencedata.entity.Counterparty;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CounterpartyRepository extends JpaRepository<Counterparty,Integer> {
}
