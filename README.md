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

Algo como as definições abaixo será exibido:

```
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: .../maven/apache-maven-3.6.3
Java version: 1.8.0_241, vendor: Oracle Corporation, runtime: /usr/lib/jvm/jdk1.8.0_241/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "linux", version: "4.19.0-8-amd64", arch: "amd64", family: "unix"
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

- Outras ferramentas de gerenciamento de build
	- Ant
	- Gradle

## 2. Maven no Eclipse

- Importando um projeto Maven no Eclipse
	- File > Import > Maven > Existing Maven Projects
	- Selecione o diretório
	- Finish

- Adicionando uma lib
	- Acesso o [mvn repository](http://mvnrepository.com/)
	- Copie o xml de sua lib
	- Adicione no arquivo pom.xml

Exemplo: 

```xml
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>3.8.1</version>
</dependency>
```

- Alternativa para gerenciamento de dependência: ivy

## 3. Repositório remoto/local


