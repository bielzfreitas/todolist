package br.com.skyline.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

/*
componente para ser camada entre requisição e demais camadas
- primeira camada de acesso
- onde o usuário envia a requisição

duas formas de criar uma Controller
- @Controller (estrutura que exista páginas / templates - flexibilidade melhor)
- @RestController (construindo uma api e quer retornar rest)
*/

// criando uma rota (http://localhost:8080/-----)
/**
 * Modificar
 * public - qualquer um pode acessar a classe
 * private - somente alguns atributos serão permitidos
 * protected - só terá acesso quando está na mesma estrutura do pacote
 */
@RestController
@RequestMapping("/users")
public class UserController {
    /*
     - Métodos de acesso do HTTP
     * Get - Buscar uma informação
     * Post - Adicionar um dado/informação
     * Put - Alterar um dado/info
     * Delete - Remover um dado
     * Patch - Alterar somente uma parte da info/dado
     * 
     * String (texto)
     * Integer (int) números inteiros
     * Double (double) números 0.00000
     * Float (float) números 0.000 - diferença do double é a quantidade de caracteres 
     * char (caracteres) aceita um caracter
     * Date (data)
     * void (quando não tem retorno no método) - somente uma logica sendo executada
     * Body (usar @RequestBody)
     * 
     * Método (fundionalidade) de uma classe
     * url para test: http://localhost:8080/primeiraRota/
     * 
     * @Autowired - spring gerencia 
     * 
     * 
     * ResponseEntity - para ter retornos diferentes dentro de uma mesma requisição
     * - ao inves de retornar diretamente o objeto, retorna o responseentity
     * - retorna nos casos de sucesso e erro
     */

    //chamando a interface
    @Autowired
    private IUserRepository userRepository;


    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUserName(userModel.getUserName());

        //se exister um usuario, não cadastrar
        if(user != null) {
            //Mensagem de Erro
            //Status Code (numero dentro da req)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        /*
         * BCrypt - criptograf a senha no BD
         * - Transforma o getPassword num arraylist de caracteres
         * - Assim consegue fazer a criptografia
         * - Após isso, passar a senha criptografada para a Model
         * 
         */
        var passwordHashred = BCrypt.withDefaults()
            .hashToString(12, userModel.getPassword().toCharArray());
        
        userModel.setPassword(passwordHashred);    

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
}
