package com.alibou.security.service.impl;

import com.alibou.security.GlobalExceptionHandler;
import com.alibou.security.model.Domicilio;
import com.alibou.security.model.dto.DomicilioDTO;
import com.alibou.security.repository.IDomicilioRepository;
import com.alibou.security.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DomicilioService implements IService<DomicilioDTO> {

    private static final Logger logger = Logger.getLogger(DomicilioService.class);

    @Autowired
    private IDomicilioRepository iDomicilioRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public Collection<DomicilioDTO> buscarTodos() {
        List<Domicilio> listaTurno = iDomicilioRepository.findAll();
        Set<DomicilioDTO> DTO = new HashSet<DomicilioDTO>();
        for(Domicilio turno: listaTurno)
            DTO.add(mapper.convertValue(turno, DomicilioDTO.class));
        logger.info("Buscando Todos los domicilios");
        return DTO;
    }

    @Override
    public Optional<DomicilioDTO> buscar(Long id) {
        Optional<Domicilio> turno = iDomicilioRepository.findById(id);
        logger.info("Buscando domicilio");
        return Optional.ofNullable(mapper.convertValue(turno, DomicilioDTO.class));
    }

    @Override
    public void guardar(DomicilioDTO estudiante) {
        Domicilio turno = this.mapper.convertValue(estudiante, Domicilio.class);
        iDomicilioRepository.save(turno);
        logger.info("Guardando domicilio");
    }

    @Override
    public void eliminar(Long id) {
        iDomicilioRepository.deleteById(id);
        logger.info("Eliminando domicilio");
    }

    @Override
    public void actualizar(DomicilioDTO estudiante) {
        Domicilio turno = mapper.convertValue(estudiante, Domicilio.class);
        iDomicilioRepository.save(turno);
        logger.info("Actualizando domicilio");

    }
}
