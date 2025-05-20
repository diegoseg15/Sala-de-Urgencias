package com.diegoseg15.urgencias.services;

import com.diegoseg15.urgencias.config.RabbitMQConfig;
import com.diegoseg15.urgencias.domain.Paciente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final List<Paciente> pacientes = new ArrayList<>();
    private final RabbitTemplate rabbitTemplate;

    public PacienteService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Paciente save(Paciente paciente) {
        pacientes.removeIf(p -> p.getDni().equalsIgnoreCase(paciente.getDni()));
        pacientes.add(paciente);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                "pacientes." + paciente.getGrado().toLowerCase(),
                paciente
        );

        return paciente;
    }

    public List<Paciente> findAll() {
        return pacientes;
    }

    public Optional<Paciente> findByDni(String dni) {
        return pacientes.stream().filter(p -> p.getDni().equalsIgnoreCase(dni)).findFirst();
    }

    public List<Paciente> findByGrado(String grado) {
        return pacientes.stream().filter(p -> p.getGrado().equalsIgnoreCase(grado)).toList();
    }

    public List<Paciente> findByEstado(String estado) {
        return pacientes.stream().filter(p -> p.getEstado().equalsIgnoreCase(estado)).toList();
    }

    public void deleteByDni(String dni) {
        pacientes.removeIf(p -> p.getDni().equalsIgnoreCase(dni));
    }

    public Paciente update(Paciente paciente) {
        return save(paciente);
    }
}
