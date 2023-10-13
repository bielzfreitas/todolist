package br.com.skyline.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.skyline.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//toda classe que preciso do spring gerenciando, precisa colocar o "@Component"
@Component
public class FilterTaskAut extends OncePerRequestFilter {

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)throws ServletException, IOException {
        
        var servletPath = request.getServletPath();
        
        //validando a task
        //Não pode mais falar que o "path" é "igual" e sem "começa com" - contempla o path e os ids
        if(servletPath.startsWith("/tasks/")) {
            // Pegar a autenticação (usuario e senha)
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();
                
            //cria um array de byts
            byte[] authDecode = Base64.getDecoder().decode(authEncoded);
                
            //convertendo o array em string
            var authString = new String(authDecode);

            //dividindo em dois pontos ["gabrielfreitas", "12345"]
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // Validar usuario
            var user = this.userRepository.findByUserName(username);
            if(user == null) {
                response.sendError(401);
            } 
            else {
                
                // Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()); //comparando com a senha do banco de dados e converte num array
                
                if(passwordVerify.verified) { //true or false
                    //pegando o idUser - recuperar no TaskController
                    // Segue viagem
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } 
                else {
                    response.sendError(401); //não tem acesso, erro.
                }  
            }
        } 
        else {
            filterChain.doFilter(request, response);  
        }
    }    
}

/*
 * Servlet é a base para qualquer framework web no Java
 * 
 * Utilizar o ctrl + . (ponto) e "Add unimplemented methods"
 * - faz tudo automático
 * 
 * filterChain é o que vai repassar para as camadas a frente
 * 
 * Toda requisição, antes de ir de fato para a rota onde foi chamada, irá
 * passar pelo filtro, validando se o usuário tem a permissão ou está no banco de dados.
 * 
 * - tirando o basic 64 e pegando somente os dados extraidos
* - .length() é para pegar o tamanho do meu basic (caracteres)
* 
* Substring recebe como parametro um int unico ou dois ints
* - se passa 1, recebe somente o start (quantidade de caracteres que eu quero remover)
* - se passa 2, passa qual é o limite (de tanto até tanto que quero remover)
* 
* - esta pegando o "basic", calcula o tamanho dela, tira tudo (poderia tirar o 5 direto) e depois remove todos os espaços 
* - corta somente o "basic"
*
* Por último, fazer um decode para conseguir ver os caracteres
*
*/
