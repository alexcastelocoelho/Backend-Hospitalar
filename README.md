<h1 align="center">Backend Hospitalar</h1>

<h5 align="center">
  Api rest no contexto de gestão hospitalar para agendamento e marcação de consultas
</h5>
<p align="center"> 
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white"/> 
 <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
 <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white"/> 
 <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge"/>
 <img src="http://img.shields.io/static/v1?label=License&message=MIT&color=green&style=for-the-badge"/>
  </p>



# Detalhes do projeto:

## Funcionalidades
  - ##### Paginação (Params: Pagina e elementos)  
  - ##### Usuário
  - ##### Autenticação
  - ##### Paciente Endpoints
  - ##### Responsavel Endpoints
  - ##### Medico Endpoints
  - ##### Consulta Endpoints
  

   
...
## 🛠️ Tecnologias Utilizadas

- Java 17+
- Spring Boot 3.4.5
- Spring Data JPA
- Spring Security
- Jwt
- Hibernate
- Postgresql
- Swagger (OpenAPI)
- Maven 

...

## 📋 Pré-requisitos

- Java 17
- Maven 
- Banco de dados configurado 


## ⚙️ Como rodar o projeto

1. Clone este repositório para uma pasta do seu computador:


```
git clone https://github.com/alexcastelocoelho/Backend-Hospitalar
```

 Obs: A duas formas para rodar o projeto, usando configuração de banco de dados local na sua máquina ou usando docker.
Irei demonstrar logo abaixo para melhor entendimento:
<br>

### 2.1 usando banco de dados localmente
Dentro do projeto existe um arquivo `env.properties.example`, renomeie para `env.properties`. Dentro dele configure suas credencias de banco de dados como no exemplo abaixo:

```
DB_DATABASE=mydatabase
DB_USER=myuser
DB_PASSWORD=mypassaword
```
Após fazer essa configuração o application-configLocal.properties estará inserindo essas variáveis de ambiente desse arquivo e configurando dentro dele conforme abaixo:
<br>

```
spring.application.name=Gestao-Hospital
server.port=8080

spring.config.import=file:env.properties
spring.datasource.url= jdbc:postgresql://localhost:5432/${DB_DATABASE}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
logging.level.org.springframework.web: DEBUG
spring.jpa.show-sql=true

api.security.token.secret=${JWT_SECRET:my-secret-key}
```

Feito isso, dentro do application.properties, insira a seguinte linha de codigo para configura o perfil *configLocal* para uso na aplicação:
```
spring.profiles.active=configLocal
```

Por fim, Execute o projeto pelo terminal com o comando abaixo ou usando a sua IDE de preferência:


`mvnw spring-boot:run`


### 2.2 Rodar usando docker
Dentro do arquivo application.properties, insira a seguinte linha de codigo para carregar a configução docker do perfil *configDocker*
```
spring.profiles.active=configDocker
```

Execute o seguinte comando para gerar o jar da aplicação:
```
mvn clean package -DskipTests
```

Depois, execute o docker-compose usando o comando:
```
docker-compose up --build
```



<br>

4. Acesso a API:


## Documentação


Projeto possui uma documentação Swagger que você pode acessar usando a rota
: 
`http://localhost:8080/v3/api-docs`


...

#### Usando o Insomnia ou Postman, faça as requisições para as seguintes rotas/endpoints:



#  Usuario endpoint
<details> <summary><strong> Criar usuário</strong></summary>
<br>

`POST`  

```
http://localhost:8080/auth/registro
```

Restrições para os campos 
- Informar um email valido
- Senha precisa ter pelo menos 6 digitos
- Informar a role do usuario  
roles permitidas[ADMIN, ATENDENTE, MEDICO, PACIENTE])



### Exemplo  requisição

```
{
    "email": "augustoJosue@gmail.com",
    "senha": "209_@ty",
    "role": "ADMIN"
}
```

`Status Code: 200 OK`   
Usuário cadastrado com sucesso

<br>

`Status Code: 400 Bad Request`
```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Informe um e-mail valido",
	"path": "/auth/registro",
	"api": "register",
	"timestamp": "2025-06-18T23:39:07.9691212-03:00"
}
```

`Status Code: 409 Conflict`
```
{
	"httpStatus": "CONFLICT",
	"message": "Email já registrado no sistema",
	"path": "/auth/registro",
	"api": "register",
	"timestamp": "2025-06-18T23:54:24.2435107-03:00"
}
```

</details>


#  Autenticação endpoint
<details> <summary><strong>Login no sistema</strong></summary>

`POST`  

```
http://localhost:8080/auth/login
```

Restrições para os campos 
- Informar o email cadastrado
- Informar sua senha cadastrada



### Exemplo  requisição

```
{
    "email": "augustoJosue@gmail.com",
    "senha": "209_@ty",
   
}
```
<br>

`Status Code: 200 OK`
```
 {
	"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnZXN0YW8taG9zcGl0YWwiLCJzdWIiOiJhdWd1c3RvSm9zdXNAZ21haWwuY29tYSIsImV4cCI6MTc1MDMwOTYzMH0.0GW6UEa5ngntv9hPMxQfFLUE0QDEnkC5ZeHj7bzxUnc"
}
```  
### OBS: Esse é o token que você deve copiar e colar nos Cabeçalhos das requisições

<br>


`Status Code: 403 Forbidden`  
Senha ou email incorreto, verificar e fazer uma nova requisição

<br>


</details>



#  Paciente Endpoints

<details> <summary><strong>Criar um paciente</strong></summary>

#### Usuários com role ADMIN ou ATENDENTE podem criar paciente

`POST`

```
http://localhost:8080/paciente
```
Restrições para os campos 
- Nome precisa ter pelo menos 3 caracteres
- Idade minima do paciente precisa ser 3 anos
- Informar um cpf valido(somente os numeros)
- dataNascimento precisa está no padrão (dd/MM/yyyy)
- Contato precisa ter o ddd junto com o número 
- Endereço precisa ter pelo menos 8 caracteres



### Exemplo  requisição

```
{
        "nome": "Joao Pereira",
        "idade": 17,
        "cpf": "63068188040",
        "dataNascimento": "12/04/2002",
        "contato": "91983299350",
        "endereco": "Rua oliveira bela"
}
```

...

`Status Code: 201 Created`

```
{
    	"id": "e7fcbb3b-0ba6-40b8-a681-0ce984a72f40",
        "nome": "Joao Pereira",
        "idade": 17,
        "cpf": "630.681.880-40",
        "dataNascimento": "12/04/2002",
        "contato": "91983299350",
        "endereco": "Rua oliveira bela",
        "consultas": []
}
```

...

`Status Code: 400 Bad Request`  
Obs: No atributo message ira aparecer o motivo do erro, como no exemplo

```

{
    "httpStatus": "BAD_REQUEST",
    "message": "Cpf invalido, verifique, por gentileza",
    "path": "/paciente",
    "api": "criarPaciente",
    "timestamp": "2025-06-17T15:59:48.5983918-03:00"
}
```
...

`Status Code: 409 Conflict`

```
{

    "httpStatus": "CONFLICT",
    "message": "Cpf já registrado no sistema",
    "path": "/paciente",
    "api": "criarPaciente",
    "timestamp": "2025-06-17T16:09:10.1794329-03:00"
}
```
</details>





<details> <summary><strong>Listar pacientes</strong></summary>
  
#### Usuários com role ADMIN, ATENDENTE ou MEDICO podem listar pacientes

`GET`

```
http://localhost:8080/paciente
```

...

`Status Code: 200 OK`

```
{
	"content": [
		{
			"id": "f4f0cfcd-0bc1-4278-a157-9caf6ad6472c",
			"nome": "Joao Pereira",
			"idade": 3,
			"cpf": "630.681.880-40",
			"dataNascimento": "12/04/2002",
			"contato": "91983299350",
			"endereco": "Rua oliveira bela",
			"consultas": []
		},
		{
			"id": "d2f9a54f-b0af-4fe7-8a49-37841e0aa91f",
			"nome": "Leandro gomes",
			"idade": 20,
			"cpf": "477.184.090-31",
			"dataNascimento": "21/08/2010",
			"contato": "91923657896",
			"endereco": "Rua paes de sousa",
			"consultas": []
		}
	],
	"page": 0,
	"size": 10,
	"totalElements": 2,
	"totalPages": 1,
	"first": true,
	"last": true
}
```

...

</details>


<details> <summary><strong>Listar paciente por id</strong></summary>

#### Usuários com role ADMIN, ATENDENTE, MEDICO ou PACIENTE pode listar um paciente

`GET`

```
http://localhost:8080/paciente/id
```

...

`Status Code: 200 OK`

```
{
	"id": "7d635ab6-ca24-484d-bffd-6b1dad1c8b18",
	"nome": "Leandro gomes",
	"idade": 20,
	"cpf": "477.184.090-31",
	"dataNascimento": "21/08/2010",
	"contato": "91923657896",
	"endereco": "Rua paes de sousa",
	"consultas": []
}
```

...

`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Paciente não localizado",
	"path": "/paciente/7d635ab6-ca24-484d-bffd-6b1dad1c8b17",
	"api": "listarUmPaciente",
	"timestamp": "2025-06-17T23:13:01.4678098-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/paciente/7d635ab6-ca24-484d-bffd-6b1dad1c8b17ss",
	"api": "listarUmPaciente",
	"timestamp": "2025-06-17T23:13:30.3790446-03:00"
}
```
</details>


<details> <summary><strong>Atualizar paciente</strong></summary>

#### Usuários com role ADMIN ou ATENDENTE podem atualizar paciente 

`PATCH`

```
http://localhost:8080/paciente/id
```
campos que podem ser atualizados
- Nome
- idade
- contato
- endereco
```
{    	
    "nome": "Leandro gomes",
    "idade": 20,				
    "contato": "91923657896",
    "endereco": "Rua paes de sousa "
    
}
```

`Status Code: 204 No Content`  
Indica que foi atualizado com sucesso
<br>
<br>
<br>

`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Paciente não localizado",
	"path": "/paciente/7d635ab6-ca24-484d-bffd-6b1dad1c8b17",
	"api": "listarUmPaciente",
	"timestamp": "2025-06-17T23:13:01.4678098-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/paciente/7d635ab6-ca24-484d-bffd-6b1dad1c8b17ss",
	"api": "listarUmPaciente",
	"timestamp": "2025-06-17T23:13:30.3790446-03:00"
}
```

</details>



<details> <summary><strong>Deletar paciente</strong></summary>

#### Somente usuario com role ADMIN pode deletar paciente 

`DELETE`

```
http://localhost:8080/paciente/id
```

`Status Code: 204 No Content`  
Indica que foi deletado com sucesso
<br>
<br>

`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Paciente não localizado",
	"path": "/paciente/7d635ab6-ca24-484d-bffd-6b1dad1c8b17",
	"api": "listarUmPaciente",
	"timestamp": "2025-06-17T23:13:01.4678098-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/paciente/7d635ab6-ca24-484d-bffd-6b1dad1c8b17ss",
	"api": "listarUmPaciente",
	"timestamp": "2025-06-17T23:13:30.3790446-03:00"
}
```


</details>



---
<br>

#  Responsavel Endpoints
<details> <summary><strong>Criar um responsável</strong></summary>
  
#### Usuários com role ADMIN ou ATENDENTE podem criar um responsável

`POST`

```
http://localhost:8080/responsavel
```
Restrições para os campos 
- Nome precisa ter pelo menos 3 caracteres
- Informar um cpf valido(somente os numeros)
- dataNascimento precisa está no padrão (dd/MM/yyyy)
- informar o parentesco com o paciente  
 Opções permitidas
[ PAI,
    MAE,
    CONJUGE,
    COMPANHEIRO,
    FILHO,
    IRMAO,
    AVO,
    TUTOR_LEGAL,
    OUTRO]

- Contato precisa ter o ddd junto com o número 
- Endereço precisa ter pelo menos 8 caracteres



### Exemplo  requisição

```
{
	        
         "nome": "Rayssa gomes",
	
         "cpf": "21430262001",
	
         "dataNascimento": "08/04/2002",
					
        "parentesco": "IRMAO",

         "contato": "11935678642",
	
         "endereco": "Rua do congo"

}
```

...

`Status Code: 201 Created`

```
{
	"id": "bfe0bccb-25de-4e39-9bd0-808739c7e7d6",
	"nome": "Rayssa gomes",
	"cpf": "214.302.620-01",
	"dataNascimento": "08/04/2002",
	"parentesco": "IRMAO",
	"contato": "11935678642",
	"endereco": "Rua do congo"
}
```

...

`Status Code: 400 Bad Request`  
Obs: No atributo message ira aparecer o motivo do erro, como no exemplo

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Formato ou valores informados incorretos, verifique",
	"path": "/responsavel",
	"api": "criarResponsavelPaciente",
	"timestamp": "2025-06-17T23:36:49.9814071-03:00"
}
```
...

`Status Code: 409 Conflict`

```
{

    "httpStatus": "CONFLICT",
    "message": "Cpf já registrado no sistema",
    "path": "/paciente",
    "api": "criarPaciente",
    "timestamp": "2025-06-17T16:09:10.1794329-03:00"
}
```

</details>


<details> <summary><strong>Listar responsáveis</strong></summary>

#### Usuários com role ADMIN, ATENDENTE OU MEDICO podem listar os responsáveis

`GET`

```
http://localhost:8080/responsavel
```

...

`Status Code: 200 OK`

```
{
	"content": [
		{
			"id": "2f3d20a6-b44b-4d9d-9925-a8a3530f144e",
			"nome": "Aline gomes",
			"cpf": "304.740.810-60",
			"dataNascimento": "15/06/1980",
			"parentesco": "MAE",
			"contato": "11935678642",
			"endereco": "Rua dos santos drumonde"
		}
	],
	"page": 0,
	"size": 10,
	"totalElements": 1,
	"totalPages": 1,
	"first": true,
	"last": true
}
```



</details>

<details> <summary><strong>Listar responsavel por id </strong></summary>


#### Usuários com role ADMIN, ATENDENTE, MEDICO ou PACIENTE podem listar um responsável 

`GET`

```
http://localhost:8080/responsavel/id
```

...

`Status Code: 200 OK`

```
{
	"id": "3c9c37e1-e35b-47f8-bb20-00c0889ca35c",
	"nome": "Aline gomes",
	"cpf": "304.740.810-60",
	"dataNascimento": "15/06/1980",
	"parentesco": "MAE",
	"contato": "11935678642",
	"endereco": "Rua dos santos drumonde"
}
```

...

`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Responsavel não localizado",
	"path": "/responsavel/3c9c37e1-e35b-47f8-bb20-00c0889ca35a",
	"api": "listarUmResponsavelPaciente",
	"timestamp": "2025-06-17T23:40:48.6991833-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/responsavel/3c9c37e1-e35b-47f8-bb20-00c0889sca35a",
	"api": "listarUmResponsavelPaciente",
	"timestamp": "2025-06-17T23:41:04.8208872-03:00"
}
```
</details>


<details> <summary><strong>Atualizar responsável </strong></summary>

#### Usuários com role ADMIN, ATENDENTE OU MEDICO podem atualizar responsável 

`PATCH`

```
http://localhost:8080/responsavel/id
```

campos que podem ser atualizados
- Nome
- Contato
- Endereco

### Exemplo requisição

```
{		
		
	"endereco": "passsagem Assis de sousa franco"	
	
}
```

`Status Code: 204 No Content`  
Atualizado com sucesso
<br>
<br>


`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Responsavel não localizado",
	"path": "/responsavel/3c9c37e1-e35b-47f8-bb20-00c0889ca35a",
	"api": "atualizarResponsavelPaciente",
	"timestamp": "2025-06-17T23:46:12.186425-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/responsavel/3c9c37e1-e35b-47f8-bb20-00ac0889ca35a",
	"api": "atualizarResponsavelPaciente",
	"timestamp": "2025-06-17T23:46:22.6848884-03:00"
}

```

</details>


<details> <summary><strong>Deletar responsável </strong></summary>


#### Somente usuario com role ADMIN pode deletar responsável

`DELETE`

```
http://localhost:8080/responsavel/id
```

`Status Code: 204 No Content`  
Deletado com sucesso
<br>
<br>

`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Paciente não localizado",
	"path": "/paciente/7d635ab6-ca24-484d-bffd-6b1dad1c8b17",
	"api": "listarUmPaciente",
	"timestamp": "2025-06-17T23:13:01.4678098-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/paciente/7d635ab6-ca24-484d-bffd-6b1dad1c8b17ss",
	"api": "listarUmPaciente",
	"timestamp": "2025-06-17T23:13:30.3790446-03:00"
}
```

</details>


---
<br>

#  Medico Endpoints


<details> <summary><strong>Criar um medico </strong></summary>
  
#### Usuários com role ADMIN ou ATENDENTE podem criar um medico
`POST`

```
http://localhost:8080/medico
```
Restrições para os campos 
- Nome precisa ter pelo menos 3 caracteres
- Informar o conselho/CRM medico formatado
- Informar um cpf valido(somente os numeros)
- Contato precisa ter o ddd junto com o número
- Endereço precisa ter pelo menos 8 caracteres
- Precisa informar um e-mail valido
 



### Exemplo  requisição

```
{
	"nome": "Lucas Silva",
	"numeroConselho": "CRM-MA 457689",
	"cpf": "24298017010",
	"contato": "98925786491",
	"endereco": "passagem porto franco",
	"email": "Lucas@gmail.com"
	
}
```

...

`Status Code: 201 Created`

```
{
	"id": "358f3666-98c8-461c-bb62-4cd0cecb1a01",
	"nome": "Lucas Silva",
	"numeroConselho": "CRM-MA 457689",
	"cpf": "242.980.170-10",
	"contato": "98925786491",
	"endereco": "passagem porto franco",
	"email": "Lucas@gmail.com",
	"consultas": []
}
```

...

`Status Code: 400 Bad Request`  
Obs: No atributo message ira aparecer o motivo do erro, como no exemplo

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "informe um email valido",
	"path": "/medico",
	"api": "criarMedico",
	"timestamp": "2025-06-18T00:02:10.3589065-03:00"
}
```
...

`Status Code: 409 Conflict`

```
{
	"httpStatus": "CONFLICT",
	"message": "Cpf já registrado no sistema",
	"path": "/medico",
	"api": "criarMedico",
	"timestamp": "2025-06-18T00:02:36.9777904-03:00"
}
```

</details>

<details> <summary><strong>Listar medicos </strong></summary>
  
#### Usuários com role ADMIN ou ATENDENTE podem listar os medicos

`GET`

```
http://localhost:8080/medico
```

...

`Status Code: 200 OK`

```
{
	"content": [
		{
			"id": "1c5ea6da-2e01-4ea1-aa23-9178f7d74ab8",
			"nome": "Lucas Silva",
			"numeroConselho": "CRM-MA 457689",
			"cpf": "242.980.170-10",
			"contato": "98925786491",
			"endereco": "passagem porto franco",
			"email": "Lucas@gmail.com",
			"consultas": []
		}
	],
	"page": 0,
	"size": 10,
	"totalElements": 1,
	"totalPages": 1,
	"first": true,
	"last": true
}
```

</details>


<details> <summary><strong> Listar medico por id</strong></summary>

#### Usuários com role ADMIN, ATENDENTE OU MEDICO podem listar um medico

`GET`

```
http://localhost:8080/medico/id
```

...

`Status Code: 200 OK`

```
{
	"id": "93119e99-4b86-4548-9b2d-d1cf87593e8b",
	"nome": "Adriano Almeida",
	"numeroConselho": "CRM-PA 457689",
	"cpf": "017.650.960-73",
	"contato": "91934658792",
	"endereco": "Rua dos assis",
	"email": "meidaJunior@gmail.com",
	"consultas": []
}
```

...

`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Medico não localizado",
	"path": "/medico/93119e99-4b86-4548-9b2d-d1cf87593e8a",
	"api": "listarUmMedico",
	"timestamp": "2025-06-18T00:10:44.1230536-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/medico/93119e99-4b86-4548-9b2d-d1cf8759a3e8a",
	"api": "listarUmMedico",
	"timestamp": "2025-06-18T00:11:00.6751327-03:00"
}
```

</details>

<details> <summary><strong>Atualizar medico </strong></summary>

#### Usuários com role ADMIN, ATENDENTE OU MEDICO podem atualizar um medico

`PATCH`

```
http://localhost:8080/medico/id
```

campos que podem ser atualizados
- Nome
- Contato
- Endereço
- Email

### Exemplo requisição

```
{		
		
	"endereco": "Rua dos pariquis"	
    "email: "Lucastuyr@gmail.com"
	
}
```

`Status Code: 204 No Content`  
Atualizado com sucesso
<br>
<br>


`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Medico não localizado",
	"path": "/medico/93119e99-4b86-4548-9b2d-d1cf87593e8a",
	"api": "atualizarMedico",
	"timestamp": "2025-06-18T00:14:13.1946737-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/medico/93119e99-4b86-4548-9b2d-d1cf87593e8as",
	"api": "atualizarMedico",
	"timestamp": "2025-06-18T00:14:24.4407959-03:00"
}

```

</details>



<details> <summary><strong>Deletar medico </strong></summary>
  
#### Somente usuario com role ADMIN pode deletar um medico

`DELETE`

```
http://localhost:8080/medico/id
```

`Status Code: 204 No Content`  
Deletado com sucesso
<br>
<br>

`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Medico não localizado",
	"path": "/medico/ebdb0056-bfae-43c5-99a6-420793cc29d1",
	"api": "deletarMedico",
	"timestamp": "2025-06-18T00:15:58.179216-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/medico/ebdb0056-bfae-43c5-99a6-420793cc29d1s",
	"api": "deletarMedico",
	"timestamp": "2025-06-18T00:16:19.5245882-03:00"
}
```

</details>

---
<br>



#  Consulta Endpoints

<details> <summary><strong> Criar uma consulta</strong></summary>

#### Usuários com role ADMIN ou ATENDENTE podem criar uma consulta

`POST`

```
http://localhost:8080/consulta
```
Restrições para os campos 
- Data da consulta precisa está no padrão  (dd/MM/yyyy)
- Hora da consulta precisa ser passada no padrão ("HH:mm")
- Informar o paciente da consulta passando o id
- Informar o medico da consulta passando o id
- Status da consulta pode ser [AGENDADA, CONFIRMADA, CANCELADA, CONCLUIDA]
 

### Exemplo  requisição

```
{
	"dataConsulta": "19/06/2025",
	"horaConsulta": "00:00",
	"paciente": {"id": "55cbc01c-cffa-4622-b6f0-90e1aea05e5c"},
	"medico": {"id": "93119e99-4b86-4548-9b2d-d1cf87593e8b"},
	"status": "CANCELADA"
	
}
```

...

`Status Code: 201 Created`

```
{
	"id": "c09874ab-378f-40e3-85c2-4f0075c2dcd6",
	"dataConsulta": "19/06/2025",
	"horaConsulta": "00:00",
	"status": "CANCELADA"
}
```

...

`Status Code: 400 Bad Request`  
Obs: No atributo message ira aparecer o motivo do erro, como no exemplo

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Formato ou valores informados incorretos, verifique",
	"path": "/consulta",
	"api": "criarConsulta",
	"timestamp": "2025-06-18T00:29:15.5796927-03:00"
}
```
...

`Status Code: 500 Internal Server Error`  
Obs: Principal causa é se for passado medico ou paciente que não existe no banco

```
{
	"timestamp": "2025-06-18T03:29:42.978+00:00",
	"status": 500,
	"error": "Internal Server Error",
	"path": "/consulta"
}
```

</details>


<details> <summary><strong>Listar consultas </strong></summary>
 
#### Usuários com role ADMIN, ATENDENTE OU MEDICO podem listar as consultas

`GET`

```
http://localhost:8080/consulta
```

...

`Status Code: 200 OK`

```
{
	"content": [
		{
			"id": "990ff32e-5044-403a-87aa-14076dc53bf0",
			"dataConsulta": "10/07/2025",
			"horaConsulta": "15:35",
			"status": "CONFIRMADA"
		}
	],
	"page": 0,
	"size": 10,
	"totalElements": 1,
	"totalPages": 1,
	"first": true,
	"last": true
}
```

</details>


<details> <summary><strong> Listar consulta por id </strong></summary>
  
#### Usuários com role ADMIN, ATENDENTE, MEDICO ou PACIENTE podem listar uma consulta 

`GET`

```
http://localhost:8080/consulta
```

...

`Status Code: 200 OK`

```
{
	"id": "c0768c7f-a47c-490f-8211-30b4c374f107",
	"dataConsulta": "25/06/2025",
	"horaConsulta": "15:35",
	"status": "AGENDADA"
}
```

...

`Status Code: 404 Not Found`

```
{
        "httpStatus": "NOT_FOUND",
        "message": "Consulta não localizado",
        "path": "/consulta/c0768c7f-a47c-490f-8211-30b4c374f106",
        "api": "listarUmaConsulta",
        "timestamp": "2025-06-18T00:34:38.9168777-03:00"
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/consulta/c0768c7f-a47c-490f-8211-30b4c374f106s",
	"api": "listarUmaConsulta",
	"timestamp": "2025-06-18T00:35:08.9356716-03:00"
}
```

</details>

<details> <summary><strong>Atualizar consulta </strong></summary>
  
#### Usuários com role ADMIN, ATENDENTE, MEDICO ou PACIENTE podem atualizar uma consulta 

`PATCH`

```
http://localhost:8080/medico/id
```

campos que podem ser atualizados
- dataConsulta
- horaConsulta
- Medico
- Status

### Exemplo requisição

```
{		

    "dataConsulta": "30/08/2025",

    "horaConsulta": "16:25",	

    "status": "CONFIRMADA"
	
}
```

`Status Code: 204 No Content`  
Atualizado com sucesso
<br>
<br>


`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Consulta não localizada",
	"path": "/consulta/c0768c7f-a47c-490f-8211-30b4c374f10a",
	"api": "atualizarConsulta",
	"timestamp": "2025-06-18T00:37:53.1894823-03:00"
}
```

`Status Code: 400 Bad Request`

```
{   
    "httpStatus": "BAD_REQUEST",
    "message": "Valor do id invalido ou longo demais, verifique",
    "path": "/consulta/c0768c7f-a47c-490f-8211-30b4c374f10aa",
    "api": "atualizarConsulta",
    "timestamp": "2025-06-18T00:38:15.170081-03:00"
}

```

</details>

<details> <summary><strong>Deletar consulta </strong></summary>

#### Somente usuario com role ADMIN pode deletar consulta 

`DELETE`

```
http://localhost:8080/consulta/id
```

`Status Code: 204 No Content`  
Deletado com sucesso
<br>
<br>

`Status Code: 404 Not Found`

```
{
	"httpStatus": "NOT_FOUND",
	"message": "Consulta não localizada",
	"path": "/consulta/e43a1646-b366-4a7c-acce-7d6b2d6be14",
	"api": "deletarConsulta",
	"timestamp": "2025-06-18T00:39:08.0149527-03:00
}
```

`Status Code: 400 Bad Request`

```
{
	"httpStatus": "BAD_REQUEST",
	"message": "Valor do id invalido ou longo demais, verifique",
	"path": "/consulta/e43a1646-b366-4a7c-acce-7d6b2d6be141s243",
	"api": "deletarConsulta",
	"timestamp": "2025-06-18T00:39:42.3648947-03:00"
}
```


</details>



---





## Desenvolvedor

#### Alex Coelho - [Github](https://github.com/alexcastelocoelho)


## License
The [MIT License](https://opensource.org/licenses/MIT) (MIT)
