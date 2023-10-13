package br.com.skyline.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

//usando o @Data do lombok, cria automaticamente os getters e setters
@Data
@Entity(name = "tb_users")
public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    
    @Column(unique = true)
    private String userName;
    private String name;
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

    /* métodos getters setters
     * Getters - todo atributo, quando quer buscar uma informação, recuperar info
     * Setters - toda vez que quiser inserir, definir um atributo dentro da classe
     *
     * Importar o lombok na pom.xml (em dependecys)
     * 
     * caso queira colocar só getters (ou setters), user @Getter / @Setter
     * 
     * @Entity (entidade/tabela) é usado para criar tabela "tb_users" no banco de dados
     * - Precisa criar uma chave primária (chave unica para representar aquele dado na apolicação)
     * - Usar "UUID" para chave primária 
     * - Usar o @id para mostar que é uma chave primária
     * - Se atentar no import (precisa ser do jakarta)
     * - @GeneratedValue cria os ids de forma automatica
     * 
     * @Column usando atributo unique
     * Validando um userName para não cadastrar um usuário já existente
     * - no banco de dados será uma coluna com uma restrição, definindo um valor unico
     * - se passar dado igual, dará erro
     * - método para verificar se existe ou não.. na Controller
     */ 

    
}
