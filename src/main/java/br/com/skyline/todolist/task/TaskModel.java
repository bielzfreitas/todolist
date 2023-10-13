package br.com.skyline.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

/*
* - O que terá na tabela de tarefas
* ID
* Usuário (ID_USUARIO)
* Descrição
* Título
* Data de Início
* Data de Término
* Prioridade
*/

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    
    //tamanho máximo de caracteres
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    // id do usuário
    private UUID idUser;

    // informa quando a tarefa foi criada
    @CreationTimestamp
    private LocalDateTime createdAt;

    //customizando o setTitle para o usuário saber que não cabe mais do que 50 caracteres
    public void setTitle(String title) throws Exception {
        //mostrando erro com uma exception
        if(title.length() > 50) {
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }
}
