package br.com.skyline.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Interface é um modelo  
 * - só define, não implemanta (apenas representação dos métodos)
 * 
 * <> - classe recebe um generator (atributos mais dinâmicos)
 * Primeiro parâmetro: passar qual a classe que o repositório está representando
 * Segundo parâmetro: qual tipo de id que a entidade tem
 * 
 * findByUserName - busca no select pela @Column 
 */

public interface IUserRepository extends JpaRepository<UserModel, UUID>{
    UserModel findByUserName(String userName);
}
