package com.salesianos.edu.EventifyLite.Service;

import com.salesianos.edu.EventifyLite.Dto.EntradaDto;
import com.salesianos.edu.EventifyLite.Enum.EstadoEntrada;
import com.salesianos.edu.EventifyLite.Models.Asistente;
import com.salesianos.edu.EventifyLite.Models.Entrada;
import com.salesianos.edu.EventifyLite.Models.Evento;
import com.salesianos.edu.EventifyLite.Repository.AsistenteRepository;
import com.salesianos.edu.EventifyLite.Repository.EntradaRepository;
import com.salesianos.edu.EventifyLite.Repository.EventoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntradaService {

    private final EntradaRepository entradaRepository;
    private final EventoRepository eventoRepository;
    private final AsistenteRepository asistenteRepository;

    public EntradaDto crearEntrada(Long eventoId, Long asistenteId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));
        Asistente asistente = asistenteRepository.findById(asistenteId)
                .orElseThrow(() -> new RuntimeException("Asistente no encontrado"));

        if(evento.getEntradasVendidas() >= evento.getAforoMaximo()) {
            throw new RuntimeException("No hay entradas disponibles para este evento");
        }

        Entrada entrada = new Entrada();
        entrada.setEvento(evento);
        entrada.setAsistente(asistente);

        entrada.setEstadoEntrada(EstadoEntrada.ACTIVA);
        evento.setEntradasVendidas(evento.getEntradasVendidas() + 1);

        Entrada entradaGuardada = entradaRepository.save(entrada);
        return EntradaDto.of(entradaGuardada);
    }

    public boolean cancelarEntrada(Long entradaId) {
        boolean cancelada = false;
        Entrada entrada = entradaRepository.findById(entradaId)
                .orElseThrow(() -> new RuntimeException("Entrada no encontrada"));
        Evento evento = eventoRepository.findById(entrada.getEvento().getId())
                .orElseThrow(() -> new RuntimeException("Evento no encontrado"));

        if(entrada.getEstadoEntrada() == EstadoEntrada.CANCELADA) {
            throw new RuntimeException("La entrada ya esta cancelada");
        }
        entrada.setEstadoEntrada(EstadoEntrada.CANCELADA);
        evento.setEntradasVendidas(evento.getEntradasVendidas() - 1);
        entradaRepository.save(entrada);
        cancelada = true;
        return cancelada;
    }

}
