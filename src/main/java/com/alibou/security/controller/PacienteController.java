package com.alibou.security.controller;

import com.alibou.security.model.Odontologo;
import com.alibou.security.model.Paciente;
import com.alibou.security.model.dto.PacienteDTO;
import com.alibou.security.service.IService;
import com.alibou.security.service.impl.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
@CrossOrigin
public class PacienteController {

    @Autowired
    IService<PacienteDTO> pacienteService;


    @CrossOrigin
    @PostMapping("/registrar")
    public ResponseEntity<?> guardar(@RequestBody PacienteDTO estudiante){
         pacienteService.guardar(estudiante);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @CrossOrigin
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Paciente> buscar(@PathVariable Long id){

        ResponseEntity response = null;

        Optional<PacienteDTO> busqueda = pacienteService.buscar(id);

        if( !busqueda.isPresent() ){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(busqueda,HttpStatus.OK);
        }

        return response;
    }
    @CrossOrigin
    @GetMapping("/buscarTodos")
    public ResponseEntity<List<Paciente>> buscarTodos(){


        ResponseEntity response = null;

        Collection<PacienteDTO> busquedaGeneral = pacienteService.buscarTodos();

        if( busquedaGeneral.isEmpty() || busquedaGeneral.size() == 0 ){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(busquedaGeneral,HttpStatus.OK);
        }

        return response;

    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){

        ResponseEntity response = null;

        if( !pacienteService.buscar(id).isPresent() ){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            pacienteService.eliminar(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return response;

    }
    @CrossOrigin
    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDTO> update(@RequestBody PacienteDTO estudiante){

        ResponseEntity response = null;

        if( pacienteService.buscar(estudiante.getId()).isEmpty() ){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            pacienteService.actualizar(estudiante);
            response = new ResponseEntity("Actualizado correctamente",HttpStatus.OK);
        }

        return response;

    }


}
