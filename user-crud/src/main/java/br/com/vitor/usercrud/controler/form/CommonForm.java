package br.com.vitor.usercrud.controler.form;

import org.omg.CORBA.Object;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonForm<genericClass> {

    public genericClass converter();
    //Tirar dúvida com as Célia em relação ao recebimento de tipos genéricos.
    //public genericClass atualizar(Long id, JpaRepository jpa);

}
