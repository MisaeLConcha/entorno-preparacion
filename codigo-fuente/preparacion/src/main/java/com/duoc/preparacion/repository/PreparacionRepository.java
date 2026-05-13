package com.duoc.preparacion.repository;

import com.duoc.preparacion.model.Preparacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PreparacionRepository extends JpaRepository<Preparacion, Long> {
}