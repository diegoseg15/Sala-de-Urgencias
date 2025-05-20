package com.diegoseg15.urgencias.controllers;

import com.diegoseg15.urgencias.domain.Paciente;
import com.diegoseg15.urgencias.services.PacienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin
public class PacienteController {

    private final PacienteService service;

    public PacienteController(PacienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(service.save(paciente));
    }

    @GetMapping
    public List<Paciente> obtenerTodos() {
        return service.findAll();
    }

    @GetMapping("/{dni}")
    public ResponseEntity<Paciente> buscarPorDni(@PathVariable String dni) {
        return service.findByDni(dni)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/grado/{grado}")
    public List<Paciente> buscarPorGrado(@PathVariable String grado) {
        return service.findByGrado(grado);
    }

    @GetMapping("/estado/{estado}")
    public List<Paciente> buscarPorEstado(@PathVariable String estado) {
        return service.findByEstado(estado);
    }

    @PutMapping("/{dni}")
    public ResponseEntity<Paciente> actualizar(@PathVariable String dni, @RequestBody Paciente paciente) {
        if (!service.findByDni(dni).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        paciente.setDni(dni);
        return ResponseEntity.ok(service.update(paciente));
    }

    @DeleteMapping("/{dni}")
    public ResponseEntity<Void> eliminar(@PathVariable String dni) {
        if (!service.findByDni(dni).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteByDni(dni);
        return ResponseEntity.noContent().build();
    }
}
