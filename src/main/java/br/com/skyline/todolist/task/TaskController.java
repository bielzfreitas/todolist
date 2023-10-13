package br.com.skyline.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.skyline.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    
    //Spring gerenciando a instancia do repo
    @Autowired
    private ITaskRepository taskRepository;

    //definido por Post acessando pelo tasks /
    @PostMapping("/")
    public ResponseEntity creat( @RequestBody TaskModel taskModel, HttpServletRequest request){
        
        //chamando a taskModel - gerando uma tarefa criada
        //setando o id no taskModel convertendo em UUID
        var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);
        
        //validando se a data passada na tarefa é maior do que a data atual
        var currentDate = LocalDateTime.now();
        
        //se a data atual for maior do que a data de inicio, lançar erro
        //currentDate - 12/11/2023
        //StartAt - 12/10/2023
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início / término deve ser maior do que a data atual");
        }

        //validando data de início ser depois da data de término
        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("A data de início deve ser menor do que a data de término");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }

    //lista de tarefas que o usuario possui baseado em suas credenciais
    //Mapping para trazer tudo o que for relacionado ao usuário
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request) {
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    /*fazendo update das tarefas, caso seja necessário
    * passando os dados que serão alterados e o usuário precisa estar autenticado
    * passar o id da task - variável do path (contido na nossa rota)
    * passando o "id" como parametro o nome do atributo que irá receber
    * o spring vai substituir o que está escrito depois do "/", procurando qual o id para alteração
    */
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request) {
        //retornar a task existente
        var task = this.taskRepository.findById(id).orElse(null);

        //validando se a tarefa realmente existe
        if(task == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Tarefa não encontrada");
        }

        //validando se o usuario que está tentando alterar um dado, é realmente dono da tarefa
        var idUser = request.getAttribute("idUser");
        
        if(!task.getIdUser().equals(idUser)) { //se ele for diferente "!" ele retorna um erro
             return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Usuário não tem permissão para alterar essa tarefa!");
        }

        //taksModel - source
        //task - target
        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdated);
    }
}

/*
 * DateTime no ApiDog possui uma estrutura diferente
 * - a estrutura é separada
 * - começando com os 4 numeros do ano - mês - dia
 * - após possui as horas, minutos e segundos
 * - Entre a data e a hora existe uma letra no meio da estrutura utilizando um "T"
 * separando as horas da data.
 * 
 * yyyy-mm-ddTHH:mm:ss
 * 
 * Para saber o idUser, precisa cadastrar um usuário primeiro
 * 
 * Restringir quem pode de fato criar uma tarefa
 * - permitir que somente o usuario, dono da tarefa, possa criar a tarefa
 * - utilizar um filtro que vá validar se o usuario está dentro da aplicação (cadastrado)
 * - utilizar a parte de autenticação (Auth) do APIDog
 * - utilizar o Basic Auth
 * 
 * A ideia é: filtro para que um usuário possa cadastrar uma tarefa, terá que 
 * informar suas credenciais de autenticação.
 * - vamos verificar se o usuario está realmente cadastrado na aplicação
 * - se estiver, poderá cadastrar a tarefa
 * - não vai mais precisar usar o idUser
 * 
 * Se não tiver cadastradadp ou senha incorreta, não permite o cadastro da tarefa
 * 
 * Tempo vídeo -24:37 (Filtro)
 * 
 * 
 * Pegar o ID da tarefa após a criação da mesma
 * - copiar e colar na url do "put"
 * 
 * Pro update não retornar "null"
 * - copiar toda a tarefa no Json, dentro do "Put"
 * - fazer somente a alteração do campo desejado
 * - mudar o "servletPath" na "FilterTaskAut.java"
 * - alterar de "equals" para "startsWith"
 * - assim todas as rotas terão exatamente o mesmo Path
 * 
 */