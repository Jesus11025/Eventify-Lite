package com.salesianos.edu.EventifyLite.Dto;

import com.salesianos.edu.EventifyLite.Models.Entrada;

public record EntradaDto(
        Long id,
        String fechaCompra,
        String estadoEntrada,
        Long asistenteId,
        Long eventoId
) {
    public static EntradaDto of(Entrada entrada) {
        return new EntradaDto(
                entrada.getId(),
                entrada.getFechaCompra().toString(),
                entrada.getEstadoEntrada().toString(),
                entrada.getAsistente().getId(),
                entrada.getEvento().getId()
        );
    }

}
