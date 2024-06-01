package com.example.proiect;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DNACombinationRepository extends JpaRepository<DNACombination, Long> {
}
