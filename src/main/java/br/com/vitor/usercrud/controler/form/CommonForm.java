package br.com.vitor.usercrud.controler.form;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonForm<T> {

    public T converter();
    //public T atualizar(Long id, T jpa);
}
