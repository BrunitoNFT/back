package com.alibou.security.service;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {

    Collection<T> buscarTodos();
    Optional<T> buscar(Long id);
    void guardar(T estudiante);
    void eliminar(Long id);
    void actualizar(T estudiante);

}
