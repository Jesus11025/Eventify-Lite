package com.salesianos.edu.EventifyLite.Repository;

import com.salesianos.edu.EventifyLite.Models.Entrada;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;

public interface EntradaRepository extends JpaRepository<Entrada,Long> {

    @EntityGraph(attributePaths = {"asistente", "evento"})
    public Page<Entrada> findEntradasActivasByAsistenteId(Long asistenteId, Pageable pageable);

    @EntityGraph(attributePaths = {"evento", "asistente"})
    @Query("SELECT e FROM Entrada e JOIN FETCH e.asistente WHERE e.evento.id = :eventoId")
    Page<Entrada> findByEventoWithAsistente(@Param("eventoId") Long eventoId);
}
