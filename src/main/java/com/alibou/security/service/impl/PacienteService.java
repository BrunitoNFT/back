package com.alibou.security.service.impl;

import com.alibou.security.model.Paciente;
import com.alibou.security.model.dto.PacienteDTO;
import com.alibou.security.repository.IPacienteRepository;
import com.alibou.security.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PacienteService implements IService<PacienteDTO> {

    private static final Logger logger = Logger.getLogger(PacienteService.class);

    @Autowired
    private  IPacienteRepository iPacienteRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public Collection<PacienteDTO> buscarTodos() {
        List<Paciente> listaTurno = iPacienteRepository.findAll();
        Set<PacienteDTO> DTO = new HashSet<PacienteDTO>();
        for(Paciente turno: listaTurno)
            DTO.add(mapper.convertValue(turno, PacienteDTO.class));
        logger.info("buscarTodos paciente");
        return DTO;
    }

    @Override
    public Optional<PacienteDTO> buscar(Long id) {
        Optional<Paciente> turno = iPacienteRepository.findById(id);
        logger.info("buscar paciente");

        return Optional.ofNullable(mapper.convertValue(turno, PacienteDTO.class));
    }

    @Override
    public void guardar(PacienteDTO estudiante) {
        Paciente turno = mapper.convertValue(estudiante, Paciente.class);
        logger.info("guardar paciente");

        iPacienteRepository.save(turno);
    }

    @Override
    public void eliminar(Long id) {
        logger.info("eliminar paciente");

        iPacienteRepository.deleteById(id);
    }

    @Override
    public void actualizar(PacienteDTO estudiante) {
        Paciente turno = mapper.convertValue(estudiante, Paciente.class);
        iPacienteRepository.save(turno);
        logger.info("actualizar paciente");

    }


}
