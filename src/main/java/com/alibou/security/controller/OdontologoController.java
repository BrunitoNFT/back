package com.alibou.security.controller;


import com.alibou.security.model.Domicilio;
import com.alibou.security.model.Odontologo;
import com.alibou.security.model.dto.OdontologoDTO;
import com.alibou.security.service.IService;
import com.alibou.security.service.impl.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })

public class OdontologoController {

    @Autowired
    IService<OdontologoDTO> odontologoService;



    @PostMapping("/registrar")
    public ResponseEntity<?> guardar(@RequestBody OdontologoDTO estudiante){
         odontologoService.guardar(estudiante);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @GetMapping("/buscar/{id}")
    public ResponseEntity<Odontologo> buscar(@PathVariable Long id){

        ResponseEntity response = null;

        Optional<OdontologoDTO> busqueda = odontologoService.buscar(id);

        if( busqueda.isPresent() ){

            response = new ResponseEntity(busqueda,HttpStatus.OK);


        }else{

            response = new ResponseEntity(HttpStatus.NOT_FOUND);

        }

        return response;
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<Collection<OdontologoDTO>> buscarTodos(){


        ResponseEntity response = null;

        Collection<OdontologoDTO> busquedaGeneral = odontologoService.buscarTodos();

        if( busquedaGeneral.size() == 0 ){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(busquedaGeneral,HttpStatus.OK);
        }

        return response;

    }


    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id){

        ResponseEntity response = null;

        Optional<OdontologoDTO> odontologo = odontologoService.buscar(id);
        if( odontologo.isPresent() ){

            odontologoService.eliminar(id);
            return new ResponseEntity(HttpStatus.OK);

        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        }


    }

    @PutMapping("/actualizar")
    public ResponseEntity<Odontologo> update(@RequestBody OdontologoDTO estudiante){

        ResponseEntity response = null;

        Optional<OdontologoDTO> odontologo = odontologoService.buscar(estudiante.getId());

        if( odontologo.isPresent() ){

            odontologoService.actualizar(estudiante);
            response = new ResponseEntity("Actualizado correctamente",HttpStatus.OK);

        }else{
            response = new ResponseEntity(HttpStatus.NOT_FOUND);

        }

        return response;

    }



}
