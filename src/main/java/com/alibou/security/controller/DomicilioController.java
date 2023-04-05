package com.alibou.security.controller;

import com.alibou.security.model.Domicilio;
import com.alibou.security.model.dto.DomicilioDTO;
import com.alibou.security.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/domicilio")
@CrossOrigin
public class DomicilioController {

    @Autowired
    IService<DomicilioDTO> domicilioService;
    @CrossOrigin
    @PostMapping("/registrar")
    public ResponseEntity<?>  guardar(@RequestBody DomicilioDTO domicilio){
         domicilioService.guardar(domicilio);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Domicilio> buscar(@PathVariable Long id){

        ResponseEntity response = null;

        Optional<DomicilioDTO> busqueda = domicilioService.buscar(id);

        if( busqueda.isEmpty()){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(busqueda,HttpStatus.OK);
        }

        return response;
    }
    @CrossOrigin
    @GetMapping("/buscarTodos")
    public ResponseEntity<List<Domicilio>> buscarTodos(){


        ResponseEntity response = null;

        Collection<DomicilioDTO> busquedaGeneral = domicilioService.buscarTodos();

        if( busquedaGeneral.isEmpty()|| busquedaGeneral.size() == 0 ){
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

        if( domicilioService.buscar(id).isEmpty()){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            domicilioService.eliminar(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return response;

    }
    @CrossOrigin
    @PutMapping("/actualizar")
    public ResponseEntity<Domicilio> update(@RequestBody DomicilioDTO domicilio){

        ResponseEntity response = null;

        if( domicilioService.buscar(domicilio.getId()).isEmpty() ){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            domicilioService.actualizar(domicilio);
            response = new ResponseEntity("Actualizado correctamente",HttpStatus.OK);
        }

        return response;

    }

}
