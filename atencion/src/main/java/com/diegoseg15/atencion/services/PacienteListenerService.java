package com.diegoseg15.atencion.services;

import com.diegoseg15.atencion.domain.PacienteMensaje;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PacienteListenerService {

    @RabbitListener(queues = "pacientes.leve")
    public void atenderLeve(PacienteMensaje paciente) throws InterruptedException {
        atender(paciente, "leve");
    }

    @RabbitListener(queues = "pacientes.grave")
    public void atenderGrave(PacienteMensaje paciente) throws InterruptedException {
        atender(paciente, "grave");
    }

    @RabbitListener(queues = "pacientes.critico")
    public void atenderCritico(PacienteMensaje paciente) throws InterruptedException {
        atender(paciente, "critico");
    }

    private void atender(PacienteMensaje paciente, String tipo) throws InterruptedException {
        System.out.println("-----Atendiendo paciente-----");
        System.out.println("🟢 Recibido paciente [" + paciente.getDni() + "] grado: " + tipo);
        Thread.sleep(10000); // Simula 10s
        paciente.setEstado("consulta");
        System.out.println("🟡 Paciente [" + paciente.getDni() + "] pasó a estado: consulta");
    }
}
