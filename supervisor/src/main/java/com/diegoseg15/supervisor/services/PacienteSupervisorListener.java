package com.diegoseg15.supervisor.services;

import com.diegoseg15.supervisor.domain.PacienteMensaje;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PacienteSupervisorListener {

    @RabbitListener(queues = "pacientes.supervisor")
    public void recibirMensaje(PacienteMensaje paciente) {
        System.out.println("🔎 [SUPERVISOR] Recibido paciente:");
        System.out.println("    DNI:     " + paciente.getDni());
        System.out.println("    Nombre:  " + paciente.getNombre() + " " + paciente.getApellido());
        System.out.println("    Género:  " + paciente.getGenero());
        System.out.println("    Grado:   " + paciente.getGrado());
        System.out.println("    Estado:  " + paciente.getEstado());
        System.out.println("──────────────────────────────────────────────");
    }
}
