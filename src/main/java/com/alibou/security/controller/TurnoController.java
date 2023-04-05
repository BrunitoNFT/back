package com.alibou.security.controller;

import com.alibou.security.model.Turno;
import com.alibou.security.model.dto.TurnoDTO;
import com.alibou.security.service.IService;
import com.alibou.security.service.impl.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turno")
@CrossOrigin
public class TurnoController {

    @Autowired
    IService<TurnoDTO> turnoService;

    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @CrossOrigin
    @PostMapping("/registrar")
    public ResponseEntity<?> guardar(@RequestBody TurnoDTO turno){
         turnoService.guardar(turno);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/buscar/{id}")
    public ResponseEntity<TurnoDTO> buscar(@PathVariable Long id){

        ResponseEntity response = null;

        Optional<TurnoDTO> busqueda = turnoService.buscar(id);
        if( busqueda.isEmpty() ){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            response = new ResponseEntity(busqueda,HttpStatus.OK);
        }

        return response;
    }
    @CrossOrigin
    @GetMapping("/buscarTodos")
    public ResponseEntity<Collection<TurnoDTO>> buscarTodos(){


        ResponseEntity response = null;

        Collection<TurnoDTO> busquedaGeneral = turnoService.buscarTodos();

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

        if( turnoService.buscar(id).isEmpty()){
            response = new ResponseEntity(HttpStatus.NOT_FOUND);
        }else{
            turnoService.eliminar(id);
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return response;

    }
    @CrossOrigin
    @PutMapping("/actualizar")
    public ResponseEntity<Turno> update(@RequestBody TurnoDTO turno){

            turnoService.actualizar(turno);
       return new ResponseEntity("Actualizado correctamente",HttpStatus.OK);

    }

}
