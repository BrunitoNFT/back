package com.alibou.security.repository;

import com.alibou.security.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ITurnosRepository extends JpaRepository<Turno,Long> {
}
