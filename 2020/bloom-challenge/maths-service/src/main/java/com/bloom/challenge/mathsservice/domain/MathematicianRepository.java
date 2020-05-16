package com.bloom.challenge.mathsservice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathematicianRepository extends JpaRepository<Mathematician, Long> {
}
