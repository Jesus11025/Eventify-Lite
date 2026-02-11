package com.salesianos.edu.EventifyLite.Controller;

import com.salesianos.edu.EventifyLite.Models.Entrada;
import com.salesianos.edu.EventifyLite.Repository.EntradaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EntradaRepository entradaRepository;

    @GetMapping("/{id}/entradas")
    public Page<Entrada> getEntradasByEventoId(@PathVariable Long eventoId) {
        if(eventoId == null) {
            throw new IllegalArgumentException("El ID del evento no puede ser nulo");
        }
        return entradaRepository.findByEventoWithAsistente(eventoId);
    }
}
