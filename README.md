# ✔ Sistema Gerenciamento de Pedidos
## Situação de aprendizagem do curso Programador de sistemas
Este repositório tem por objetivo compartilhar a SA realizada no curso

### Sistema de Gerenciamento de Produtos, Fornecedores e Pedidos
A situação proposta é estruturar um sistema Web para o gerenciamento dos 3 temas da seguinte maneira: Um fornecedor pode
fornecer vários produtos, e um pedido pode incluir produtos de diferentes fornecedores.

### Tecnologias utilizadas
[![My Skills](https://skillicons.dev/icons?i=java,spring,hibernate,mysql,js,html,css,bootstrap)](https://skillicons.dev)

## Informações de Configuração
### Requisitos de Aplicativos Instalados
- **Xampp:** É necessário ter o Xampp instalado para que seja possível iniciar o servidor Apache e o MySQL. <a href="https://www.apachefriends.org/pt_br/download.html" target="_blank">Link para download</a>
- **VsCode** É necessário ter o VsCode instalado para conseguir executar o projeto. <a href="https://code.visualstudio.com/sha/download?build=stable&os=win32-x64-user">Link para dowload</a>
### Passos da Instalação
1. Passo 1: Faça o <a href="https://github.com/gabrielpiske/sa-psii/archive/refs/heads/main.zip">download</a>, o fork ou o clone do repositório.
2. Passo 2:  No xampp inicie o seguinte servidor: `MySQL`.
3. Em seu computador crie o banco de dados Mysql com o nome de `forndb`.
4. Na pasta `resources` do projeto, no arquivo `application.properties`, certifique-se de colocar o seu usuario e senha do Mysql da sua máquina igual imagem abaixo.
```java
spring.application.name=forn_ped

spring.datasource.url=jdbc:mysql://localhost:3306/forndb
spring.datasource.username=seu usuario
spring.datasource.password=sua senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
5. Abra o projeto utilizando o `vscode` e execute a aplicação. Certifique-se de ter as extensões necessárias para suporte do `Spring Boot`.
6. Acesse em seu navegador `http://localhost:8080/`. Se tudo estiver ok a aplicação estara em execução.
