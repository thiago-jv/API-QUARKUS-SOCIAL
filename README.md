Projeto demonstrativo utilizando a nova stak do java Quakus - referente ao curso [Aprenda Quarkus e Desenvolva API's RESTful Poderosas em Java
](https://www.udemy.com/course/aprenda-quarkus-e-desenvolva-apis-restful-poderosas-em-java/) na plataforma de ensino udemy

# Pré-requisitos

O maven deve está instalado, caso não esteja segue o link [maven](https://dicasdejava.com.br/como-instalar-o-maven-no-windows/)

Instalar e configurar o java, caso não esteja segue o link [java](https://medium.com/beelabacademy/configurando-vari%C3%A1veis-de-ambiente-java-home-e-maven-home-no-windows-e-unix-d9461f783c26)

Instalar e configurar o docker link [docker](https://docs.docker.com/desktop/windows/install/) 

Instalar o IntelliJ [IntelliJ] (https://www.jetbrains.com/pt-br/idea/download/#section=windows)

# Tecnologias utilizadas e outros

 1- Java 11 [Sobre](https://www.zup.com.br/blog/java-11-principais-novidades)
 
 2- Maven 3.8.5 [Sobre](https://www.dclick.com.br/2010/09/15/o-que-e-o-maven-e-seus-primeiros-passos-com-a-ferramenta/)
 
 3- Docker [Sobre](https://docs.docker.com/desktop/windows/install/)
 
 4- IntelliJ [Sobre](https://www.jetbrains.com/pt-br/idea/download/#section=windows)
 
 5- Mysql [Sobre](https://www.mysql.com/)
 
 6- Hibernate-orm-panache [Sobre](https://quarkus.io/guides/hibernate-orm-panache)
 
 7- Jsonb [Sobre](https://quarkus.io/guides/rest-json)
 
 8- OpenApi [Sobre](https://quarkus.io/guides/openapi-swaggerui)
 
 9- Lombok [Sobre](https://projectlombok.org/setup/maven)
 
 10- Docker Compose [Sobre](https://docs.docker.com/compose/)
 
 
# Proceso de criação do projeto e para rodar o mesmo
```
1- Para criação desse projeto
-> https://quarkus.io/guides/maven-tooling

2- Comandos iniciais
-> mvn io.quarkus.platform:quarkus-maven-plugin:2.9.2.Final:create

3 Após a criação, entrar no projeto e verificar se está compilando
-> mvn compile quarkus:dev

4- Posso listar todas depencias que posso add no meu projeto
-> mvn quarkus:list-extensions

5- Add uma extenção
-> mvn quarkus:add-extension -Dextensions="hibernate-validator"
-> mvn quarkus:add-extension -Dextensions="jdbc-h2, hibernate-orm, hibernate-orm-panache, resteasy-jsonb"
-> mvn quarkus:add-extension -Dextensions="jdbc-mysql" 
-> mvn quarkus:add-extension -Dextensions="jdbc-h2"
-> mvn quarkus:add-extension -Dextensions="quarkus-smallrye-openapi"
-> mvn quarkus:add-extension -Dextensions="quarkus-redis-client"
-> mvn quarkus:add-extension -Dextensions="quarkus-flyway"

6- Limpar o projeto
-> mvn clean package -DskipTests=true

7- Add o project Lombok
https://projectlombok.org/setup/maven

8- Gerando o jar para rodar em prod
-> mvn clean package -DskipTests=true
-> java -jar ./target/quarkus-app/quarkus-run.jar

9- Subindo a applicação no docker
-> instalar o docker -> https://docs.docker.com/desktop/windows/install/

10- Criando a imagem
-> docker ps -> para ver se tem alguma imagem criada ou rodando

11- Criando a imagem 
-> docker build -f src/main/docker/Dockerfile.jvm -t quarkus-social:1.0 .

12- Vamos criar uma rede network para os containers e nossas aplicações se comunicarem
-> docker network create --driver bridge quarkus-social-network

13- Agora vamos criar um container mysql
-> docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --network quarkus-social-network --name quarkus-social-network-mysql mysql:8.0

14- Rodando a imagem criada no container docker
-> docker run -i --rm -p 8080:8080 -e DB_HOST=quarkus-social-network-mysql --network quarkus-social-network --name quarkus-social-container quarkus-social:1.0

15- Caso queira deletar e subir novamente o container do banco
-> docker container rm quarkus-social-network-mysql --force
-> docker container rm quarkus-social-container --force

```


