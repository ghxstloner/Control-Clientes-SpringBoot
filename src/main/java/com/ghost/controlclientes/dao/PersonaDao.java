package com.ghost.controlclientes.dao;

import com.ghost.controlclientes.domain.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaDao extends JpaRepository<Persona, Long> {
}
