package com.alibou.security.service.impl;

import com.alibou.security.model.Odontologo;
import com.alibou.security.model.Turno;
import com.alibou.security.model.dto.OdontologoDTO;
import com.alibou.security.model.dto.TurnoDTO;
import com.alibou.security.repository.IOdontologoRepository;
import com.alibou.security.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OdontologoService implements IService<OdontologoDTO> {

    private static final Logger logger = Logger.getLogger(OdontologoService.class);


    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public Collection<OdontologoDTO> buscarTodos() {
        List<Odontologo> listaTurno = odontologoRepository.findAll();
        Set<OdontologoDTO> DTO = new HashSet<OdontologoDTO>();
        for(Odontologo turno: listaTurno)
            DTO.add(mapper.convertValue(turno, OdontologoDTO.class));
        logger.info("Buscando odontologos");
        return DTO;
    }

    @Override
    public Optional<OdontologoDTO> buscar(Long id) {
        Optional<Odontologo> turno = odontologoRepository.findById(id);
        logger.info("Buscando odontologo");
        return Optional.ofNullable(mapper.convertValue(turno, OdontologoDTO.class));
    }

    @Override
    public void guardar(OdontologoDTO estudiante) {
        Odontologo turno = mapper.convertValue(estudiante, Odontologo.class);
        odontologoRepository.save(turno);
        logger.info("Guardando odontologo");

    }

    @Override
    public void eliminar(Long id) {
            odontologoRepository.deleteById(id);
        logger.info("Eliminando odontologo");

    }

    @Override
    public void actualizar(OdontologoDTO estudiante) {
        Odontologo turno = mapper.convertValue(estudiante, Odontologo.class);
        odontologoRepository.save(turno);
        logger.info("actualizar odontologo");

    }



}

