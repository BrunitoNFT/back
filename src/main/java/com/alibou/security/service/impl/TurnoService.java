package com.alibou.security.service.impl;

import com.alibou.security.model.Turno;
import com.alibou.security.model.dto.TurnoDTO;
import com.alibou.security.repository.ITurnosRepository;
import com.alibou.security.service.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService implements IService<TurnoDTO> {

    private static final Logger logger = Logger.getLogger(TurnoService.class);



    @Autowired
    private  ITurnosRepository iTurnosRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public Collection<TurnoDTO> buscarTodos() {
        List<Turno> listaTurno = iTurnosRepository.findAll();
        Set<TurnoDTO> DTO = new HashSet<TurnoDTO>();
        for(Turno turno: listaTurno)
            DTO.add(mapper.convertValue(turno, TurnoDTO.class));
        logger.info("buscarTodos turnos");

        return DTO;

    }

    @Override
    public Optional<TurnoDTO> buscar(Long id) {
        Optional<Turno> turno = iTurnosRepository.findById(id);
        logger.info("buscar turnos");

        return Optional.ofNullable(mapper.convertValue(turno, TurnoDTO.class));
    }

    @Override
    public void guardar(TurnoDTO turnoDTO) {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        logger.info("guardar turnos");

        iTurnosRepository.save(turno);

    }

    @Override
    public void eliminar(Long id) {
        logger.info("eliminar turnos");

        iTurnosRepository.deleteById(id);
    }

    @Override
    public void actualizar(TurnoDTO turnoDTO) {
        logger.info("actualizar turnos");

        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        iTurnosRepository.save(turno);
    }
}
