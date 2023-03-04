FROM openjdk:17-jdk-alpine3.15

WORKDIR /app

COPY target/minha-aplicacao.jar /app/app.jar

# Instala o cliente do MySQL
RUN apk add --no-cache mariadb-client

# Configurações para esperar a inicialização do banco de dados
COPY wait-for.sh /app/wait-for.sh
RUN chmod +x /app/wait-for.sh

CMD ["/app/wait-for.sh", "mysql:3306", "--", "java", "-jar", "app.jar"]
