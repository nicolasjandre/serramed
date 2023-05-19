package br.com.serratec.serramed.domain.service.iCrud;

import java.util.List;

public interface ICRUDService<Req, Res> {

    Res create(Req dto);

    Res findById(Long id);

    List<Res> findAll();

    Res updateById(Req dto, Long id);

    void deleteById(Long id);
}
