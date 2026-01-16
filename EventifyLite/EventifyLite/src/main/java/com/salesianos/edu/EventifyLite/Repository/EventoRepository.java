package com.salesianos.edu.EventifyLite.Repository;

import com.salesianos.edu.EventifyLite.Models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
