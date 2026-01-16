package com.salesianos.edu.EventifyLite.Controller;

import com.salesianos.edu.EventifyLite.Dto.CreateEntradaRequest;
import com.salesianos.edu.EventifyLite.Dto.EntradaDto;
import com.salesianos.edu.EventifyLite.Service.EntradaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/entradas")
public class EntradasController {

    private final EntradaService entradaService;

    @PostMapping
    public EntradaDto crearEntrada(Long idAsistente, Long idEvento) {
        if(idAsistente == null || idEvento == null) {
            throw new IllegalArgumentException("El ID del asistente y el ID del evento no pueden ser nulos");
        }
        return entradaService.crearEntrada(idAsistente, idEvento);
    }

    @PutMapping("/{id}/cancelar")
    public boolean cancelarEntrada(Long id) {
        if(entradaService.cancelarEntrada(id)) {
            return true;
        } else {
            throw new IllegalArgumentException("No se pudo cancelar la entrada con ID: " + id);
        }
    }

}
