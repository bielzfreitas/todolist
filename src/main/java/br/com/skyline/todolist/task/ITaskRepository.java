package br.com.skyline.todolist.task;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ITaskRepository extends JpaRepository<TaskModel, UUID>{
    
    //pedir para trazer as tasks que são somende do id de usuário
    List<TaskModel> findByIdUser(UUID idUser);

    
    /*
    * Segunda maneira de validar usuário (primeira na TaskController)
    *
    * - validando se o usuario que está tentando alterar um dado, é realmente dono da tarefa
    * TaskModel findByIdAndIdUser(UUID id, UUID idUser);
    */
}
