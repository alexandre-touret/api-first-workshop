package info.touret.guitarheaven.domain.service;

import info.touret.guitarheaven.domain.model.Guitar;

import java.util.List;

public interface GuitarPort {

    List<Guitar> listAll();

    void save(Guitar guitar);

    Guitar update(Guitar guitar);

    boolean delete(Long guitarId);

}
