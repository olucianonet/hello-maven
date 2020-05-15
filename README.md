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

- Trabalhando offline

Para executar comandos no mvn offline:

```
mvn -o compile
```

- Quando houver novas dependências declaradas e executar comandos offline, 
poderá receber mensagens como esta:

```
[ERROR] Failed to execute goal on project produtos: Could not resolve dependencies for project net.oluciano.maven:produtos:jar:1.0-SNAPSHOT: Cannot access central (https://repo.maven.apache.org/maven2) in offline mode and the artifact com.google.code.gson:gson:jar:2.6 has not been downloaded from it before. -> [Help 1]
```

- Repositório local

Todas as dependências baixadas de um usuários serão guardados em seus 
repositório local.

```
user/.m2/repository/
```

## 4. Relatórios de qualidade e cobertura

### Qualidade

- O goal pmd

```
mvn pmd:pmd
```

O jar do plugin será encontrado automaticamente. Será gerado um arquivo de 
relatório, alertando sobre problemas no código.

Caso existam violações, será gerado um relatório em: target/site.

- Mais informações na [documentação do PMD](https://maven.apache.org/plugins/maven-pmd-plugin/index.html)

- O goal check

```
mvn pmd:check
```

Esse comando verifica o projeto em busca de problemas no código e falha o build
caso encontrar algum, exibindo um erro como este:

```
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-pmd-plugin:3.13.0:check (default-cli) on project produtos: You have 1 PMD violation. For more details see: .../hello-maven/produtos/target/pmd.xml -> [Help 1]
```

- Outras forma de rodar o pmd:check é executar o comando `verify`, do ciclo de 
vida do projeto. Mas para isso, primeiramente é preciso configurar o xml.

```
<build>
<plugins>
  <plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-pmd-plugin</artifactId>
    <version>3.13.0</version>
    <executions>
      <execution>
        <phase>verify</phase>
        <goals><goals>check</goals></goals>
      </execution>
    </executions>
  </plugin>
</plugins>
</build>
```

- O xml acima, descreve a execução de um goal, na fase de verify, durante o build do projeto, utilizando o plugin do pmd.
- É possível especificar os plugins a serem executados nas fases desejadas, 
alterando o ciclo de vida de um projeto.

### Cobertura

- [JaCoCo Maven](https://www.eclemma.org/jacoco/trunk/doc/maven.html)
- Adicione seu xml no pom.xml

```
<plugin>
<groupId>org.jacoco</groupId>
<artifactId>jacoco-maven-plugin</artifactId>       
<executions>
  <execution>            
    <goals>
      <goal>prepare-agent</goal>
      <goal>report</goal>              
    </goals>
  </execution>   
</executions>     
</plugin>
```

- Atualizar versões em grandes projetos [link](http://www.mojohaus.org/versions-maven-plugin/use-latest-versions-mojo.html):

```
mvn versions:use-latest-versions
```

- Esse goal somente verifica por novas versões, sem alterar o pom:

```
mvn versions:display-dependency-updates
```

- [Doc do plugin versions](http://www.mojohaus.org/versions-maven-plugin/index.html)
- Rode algum goal do plugin: `help`
- **Pode não ser uma boa prática deixar em aberto a versão de uma lib**
- Executar os goals do JaCoCo na sua fase padrão:
	- prepare-agent
	- report
- Pode ser necessário executar: Maven > Update > Project
- Atualizar o [junit](https://mvnrepository.com/artifact/junit/junit)
	- 4.12
- Criar um teste unitário

```java
package net.oluciano.maven;

import static org.junit.Assert.*;
import org.junit.Test;

public class ProdutoTest {

    @Test
    public void verificaPrecoComImposto() {
        Produto bala = new Produto("Cadeira", 500);
        assertEquals(550.0, bala.getPrecoComImposto(), 0.00001);
    }
    
    @Test
    public void verificaNome(){
    	Produto carro = new Produto("Carro", 50000.0);
    	assertEquals(null, carro.getNome(), "Carro");
    }
}
```

- Executar o maven, dentro e fora do Eclipse.

```bash
mvn verify
```

- Run as > Maven Build ... > Main > Goals: Verify

## 5. Criando um projeto web

### Criando um projeto com o Eclipse

- File > New Project > Maven Project > New Maven project (Default) > 
	maven-archetype-webapp
- Defina as propriedades do projeto:
	- Groud Id: net.oluciano.maven.webstore
	- Artifact Id: webstore
	- Version: 1.0.0-SNAPSHOT
	- Package: net.oluciano.maven.webstore
- Projeto criado. 
- Verifique no pom.xml, na tag <packaging>war</packaging>, indicando que é uma 
web archive.
- Suba um servidor de aplicações de teste:
	- Adicione o plugin do [Jetty]()

```xml
<plugin>
	<groupId>org.eclipse.jetty</groupId>
	<artifactId>jetty-maven-plugin</artifactId>
	<version>9.4.28.v20200408</version>
</plugin>
```

- Execute `mvn jetty:run` para baixar o Jetty.
- Acesse `localhost:8080`

### Modificando o web.xml

- Adicione a dependência do servlet no pom.xml

```xml
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>javax.servlet-api</artifactId>
    <version>3.1.0</version>
</dependency>
```

- Acesso o arquivo web.xml e atualie-o:

```xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
</web-app>
```

- Execute novamente o servidor Jetty para testar.

### Criando e executando um servlet

- Crie uma nova Servlet em src/main/java, no pacote net.oluciano.maven.webstore

```java
package br.com.alura.maven.lojaweb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/contato"})
public class ContatoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("<html><h2>Entre em contato</h2></html>");
        writer.close();
    }
}
```

- Adicionar no pom.xml um configuração para o Jetty restartar automaticamente
quando houver uma atualização no seu servlet.

```xml
<configuration>
	<scanIntervalSeconds>10</scanIntervalSeconds>
</configuration>
```

- Após realizar a alteração no pom.xml, altere o servlet e verifique se será
restartado automaticamente no período definido no scanInteval.
- Por fim, defina o contexto da aplicação, adicionando uma nova tag no pom.xml.

```xml
<webApp>
	<contextPath>/test</contextPath>
</webApp>
```

- Novamente, restarte o servidor e entre com a nova uri no navegador.