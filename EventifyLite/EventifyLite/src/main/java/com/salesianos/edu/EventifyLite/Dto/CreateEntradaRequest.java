package com.salesianos.edu.EventifyLite.Dto;

import com.salesianos.edu.EventifyLite.Models.Entrada;

public record CreateEntradaRequest(
        Long asistenteId,
        Long eventoId
) {
    public static CreateEntradaRequest of(Entrada entrada) {
        return new CreateEntradaRequest(
                entrada.getAsistente().getId(),
                entrada.getEvento().getId()
        );
    }
}
