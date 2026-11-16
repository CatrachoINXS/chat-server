package edu.dosw.chat_server.application;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ChatMetrics {

    private final MeterRegistry registry;
    private final AtomicInteger conectados;

    public ChatMetrics(MeterRegistry registry) {
        this.registry = registry;
        this.conectados = registry.gauge("chat.usuarios.conectados", new AtomicInteger(0));
    }

    public void mensaje(String tipo) {
        registry.counter("chat.mensajes", "tipo", tipo).increment();
    }

    public void entra() { conectados.incrementAndGet(); }

    public void sale()  { conectados.decrementAndGet(); }
}
