FilaController
Este é um exemplo de aplicação Spring que demonstra o uso de um controlador REST para gerenciar filas de usuários.

Executando a aplicação
Para executar a aplicação, você precisará ter o Docker instalado em seu computador.

Iniciando o contêiner Docker
Para iniciar a aplicação dentro de um contêiner Docker, siga os seguintes passos:

Clone este repositório em sua máquina.
Navegue até a pasta raiz do projeto.
Execute o comando docker-compose up para construir o contêiner e iniciar a aplicação.
Isso irá criar o contêiner e iniciar a aplicação dentro dele. Em seguida, acesse http://localhost:8080 para interagir com a aplicação.

Banco de dados
O contêiner Docker inclui uma instância do banco de dados MySQL. A configuração de conexão com o banco de dados está definida no arquivo application.properties. Para conectar-se ao banco de dados, utilize as seguintes informações:
spring.datasource.url=jdbc:mysql://localhost:3306/fila?useSSL=false&serverTimezone=UTC
spring.datasource.username=user
spring.datasource.password=123

Usando a API REST
A API REST permite gerenciar filas de usuários. Os seguintes endpoints estão disponíveis:

GET /fila
Retorna uma lista de todos os usuários na fila, ordenados por ordem de chegada.

POST /fila
Adiciona um usuário à fila. O corpo da requisição deve conter um objeto JSON

PUT /fila/{id}
Atualiza as informações de um usuário na fila.

DELETE /fila/{id}
Remove um usuário da fila.