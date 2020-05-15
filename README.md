# Hello Maven

Meu primeiro contato com Maven.

## 1. Iniciando

- Instalando

Download de [Apache Maven Project](https://maven.apache.org/download.cgi)

- Configure o `bashrc`

```
export PATH=$PATH:/home/lucas/local/apache-maven-3.3.9/bin
```

Após configurar execute:

```
mvn -v
```

- Criando um primeiro projeto com Maven

```
mvn archetype:generate -DartifactId=produtos -DgroupId=net.oluciano.maven -DinteractiveMode=false -DarchetypeArtifactId=maven-archetype-quickstart
```

- Comandos básicos

```
mvn compile
mvn test
mvn clear
```

- Gerando relatórios

```
mvn surefire-report:report
```

- Gerar um package

```
mvn package
```

- Executar o .jar

Entre no diretório `target`, e execute:

```
java -cp produtos-1.0-SNAPSHOT.jar net.oluciano.maven.App
```
