package com.salesianos.edu.EventifyLite.Models;

import com.salesianos.edu.EventifyLite.Enum.EstadoEntrada;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Entrada {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime fechaCompra;
    private EstadoEntrada estadoEntrada;

    @ManyToOne(fetch = FetchType.LAZY)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    private Asistente asistente;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Entrada entrada = (Entrada) o;
        return getId() != null && Objects.equals(getId(), entrada.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
