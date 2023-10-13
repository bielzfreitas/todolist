# todolist

### Curso Java feito com a RocketSeat

Construção back-end de uma aplicação de To-Do List
- Install Java 17+
- Install Maven
- VSCode
- Rest Client

Using render.com
- create arq "DockerFile"
- Indicar qual a imagem que queremos utilizar (ubuntu) / primeira versão
- Ele constroe dentro do render, uma imagem do ubuntu
- Pedir para configurar o java 17 (apt-get update / install jdk 17 / -y)

__________________________________________________________________________________________________________________________________

# Quiz Aula 01

### Question 1.
Qual é a função principal do Maven no desenvolvimento da aplicação?
- Resposta: Gerenciar dependências e fazer o build

### Question 2.
Qual é a diferença entre empacotamento via JAR e via WAR em uma aplicação Spring Boot?
- Resposta: O empacotamento via WAR requer um servidor para rodar a aplicação, enquanto o empacotamento via JAR não

### Question 3.
Qual é o propósito de uma controller no contexto do Spring Boot?
- Resposta: Processar as requisições HTTP e atuar como a primeira camada de acesso do usuário à aplicação

__________________________________________________________________________________________________________________________________

# Quiz Aula 02

### Question 1.
O que significa quando um atributo de uma classe está definido como "private"?
- Resposta: O atributo pode ser acessado apenas pela própria classe

### Question 2.
Qual é a forma de acessar atributos dentro de uma classe que estão definidos como "private"?
- Resposta: Acesso através de métodos públicos na classe que retornam ou modificam o atributo.

### Question 3.
O que são os métodos "getters" e "setters" em Java, de acordo com a explicação dada na aula?
- Resposta: "Getters" são usados para recuperar valores de atributos de uma classe, enquanto "setters" são usados para definir valores para esses atributos.

### Question 4.
Qual é a função da annotation @RequestBody no Spring Boot, conforme explicado na aula?
- Resposta: Indica que os dados da requisição estarão no corpo da mensagem e serão convertidos para o objeto especificado no parâmetro do método.

### Question 5.
O que é o Lombok no contexto do Java, de acordo com a explicação na aula?
- Resposta: Uma biblioteca que facilita a criação de getters e setters no Java, entre outros métodos.

### Question 6.
Qual é a função da annotation @Data do Lombok mencionada na aula?
- Resposta: Facilitar a criação de getters e setters para todos os atributos de uma classe.

### Question 7.
O que é o Spring Data JPA e qual é a sua função no contexto do desenvolvimento com Spring Boot?
- Resposta: É uma biblioteca de persistência que facilita a comunicação com o banco de dados em uma aplicação Spring Boot.

__________________________________________________________________________________________________________________________________

# Quiz Aula 03

### Question 1.
Qual é o objetivo de utilizar a biblioteca Bcrypt na aplicação, como mencionado na aula?
- Resposta: Criptografar as senhas antes de armazená-las no banco de dados.

### Question 2.
Qual é o objetivo do filtro criado na aula?
- Resposta: Autenticar o usuário antes de permitir o cadastro de uma tarefa.

### Question 3.
Qual é a função do método doFilter no filtro criado na aula?
- Resposta: Permitir que a requisição continue seu fluxo normal de processamento.

### Question 4.
O que é um header HTTP e qual foi a sua importância na aula?
- Resposta: Um header HTTP é uma parte da requisição ou resposta HTTP que contém informações sobre a requisição ou resposta.

__________________________________________________________________________________________________________________________________

# Quiz Aula 04

### Question 1.
Qual é a condição para que toda a operação seja realizada no código discutido na aula?
- Resposta: O servlet path deve ser igual a /tasks.

### Question 2.
Qual é a finalidade de utilizar o método setAttribute no contexto do filtro e do controlador na aplicação discutida na aula?
- Resposta: Enviar informações do filtro para o controlador.

### Question 3.
O que fizemos para garantir que apenas as tarefas relacionadas ao ID de usuário específico sejam retornadas na lista de tarefas?
- Resposta: Utiliza um método findByIdUser no repositório de tarefas, passando o ID do usuário como parâmetro.

### Question 4.
Como garantimos que a rota de atualização (PUT) contém o ID da tarefa que o usuário deseja alterar?
- Resposta: O ID da tarefa é passado como um parâmetro de caminho (path variable) na URL.

### Question 5.
Qual é o problema que o método copyNonNullProperties resolve no contexto da aula?
- Resposta: Permite ao usuário mesclar propriedades não nulas de dois objetos.

__________________________________________________________________________________________________________________________________

# Quiz Aula 05

### Question 1.
Qual é a vantagem de usar a validação do ID do usuário no método de atualização de tarefas?
- Resposta: Previne que usuários alterem tarefas que não pertencem a eles.

### Question 2.
O que fizemos para resolver o problema de validação de títulos com mais de 50 caracteres de forma mais amigável para o usuário?
- Resposta: Implementou um tratamento global de exceções usando @ControllerAdvice para mensagens de erro específicas.

### Question 3.
Como o Spring DevTools melhora a experiência de desenvolvimento?
- Resposta: Ele permite que a aplicação seja reiniciada automaticamente quando são feitas alterações no código-fonte.

### Question 4.
Por que criamos um arquivo Dockerfile para utilizar o Render.com?
- Resposta: Para definir as configurações do projeto e criar uma imagem Docker da aplicação.

__________________________________________________________________________________________________________________________________

