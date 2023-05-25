package br.com.serratec.serramed.controller.icrud;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ICRUDController<Req, Res> {
    
    ResponseEntity<Res> create(Req dto);

    ResponseEntity<Res> findById(Long id);

    ResponseEntity<List<Res>> findAll();

    ResponseEntity<Res> updateById(Req dto, Long id);
    
    ResponseEntity<Void> deleteById(Long id);
}
