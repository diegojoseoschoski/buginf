# BugInf 1.0-snapshot
BugInf é uma API de código aberto ([GNU LGPL](https://github.com/diegojoseoschoski/buginf/blob/master/LICENSE)) que auxilia na integração das informações de repositórios de sistemas de bugtracking.


## Requisitos
- [Java 1.7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
- [Apache Maven 3.0.5](https://maven.apache.org/download.cgi) 
- [Elasticsearch 1.5.2](https://www.elastic.co/downloads/past-releases/elasticsearch-1-5-2) 
- [Kibana 4.0.2](https://www.elastic.co/downloads/past-releases/kibana-4-0-2) 

## Instalação
* Executar o Elasticsearch e depois o Kibana .
* Baixar os módulos do buginf para o diretório de sua preferência.
* Abrir o console de comandos do windows/linux.
* Navegar até o diretório onde foi baixado o projeto.
* Navegar até o módulo bug-inf-parent.
* Digitar o comando <b>mvn clean compile assembly:single</b>, este comando irá gerar o empacotamento dos módulos projeto no formato JAR, onde irá ser gerado um JAR para cada módulo do projeto.
* Copie o JAR <b>"buginf-api-1.0-SNAPSHOT-jar-with-dependencies" </b> que foi gerado no diretório buginf-parent\buginf-api\target para uma nova pasta.
* Para a mesma pasta que você copiou o JAR, copie o arquivo <b>buginf-api.properties </b>, este arquivo contem parâmetros de configuração da API que podem ser alterados.
* Navegue para essa nova pasta onde estão o arquivo .JAR e o arquivo <b>buginf-api.properties</b>.
* Altere o arquivo <b>buginf-api.properties</b> para mudar o repositório onde será feito a integração. Ex: <b>bugtracking.name=REDMINE</b> no momento só está implementado para repositórios do REDMINE(não alterar), <b>bugtracking.url = http://www.redmine.org </b> (Pode ser módificado para outro repositório redmine, porém alguns repositórios precisam da chave de acesso da API do repositório para retornar os dados, mas no momento o BugInf não contempla este cenário).
* Digitar o comando <b>java -jar buginf-api-1.0-SNAPSHOT-jar-with-dependencies.jar buginf-api.properties</b>, este comando irá executar a integração das informações do repositório informado.
* Após a execução da API ele irá criar um índice no elasticsearch chamado bug_inf_dashboard onde estão armazenadas as informações dos defeitos do repositório que foi escolhido.
* A criação do dashboard no kibana pode ser criada a partir da [documentação](https://www.elastic.co/guide/en/kibana/4.0/index.html) seguindo as métricas que constam na figura 4.

## Quick Start
Neste tutorial será demonstrado como seria a extensão utilizando o sistema de bug tracking Bugzilla.
#### Passo 1º
Criar uma constante na Enum BugTrackingType que represente o sistema de bug tracking.
```JAVA
public enum BugTrackingType {
   BUGZILLA; // representa o sistema de bug tracking
} 
```
#### Passo 2º
Criar uma classe que implemente a interface BugTrackingService e implementar a lógica para recuperar as informações do bug tracking.
```JAVA
public class BugzillaServiceImpl implements BugTrackingService {
 // O - tipo generico que deve ser conforme foi implementado 
 private BugzillaDefeitoMapper<O, Defeito> bugzillaMapper;
 BugzillaServiceImpl(BugTrackingMapper<O, Defeito> bugzillaMapper) {
	
	this.bugzillaMapper = bugzillaMapper;
	
 }

  @Override
  public List<Projeto> recuperarTodosProjetos() {
     // implementar lógica 
  }
  @Override
  public List<Defeito> recuperarTodosDefeitosProjeto(final Projeto projeto) {
    // implementar lógica
  }
} 
```
#### Passo 3º
Adicionar na classe BugTrackingServiceFactory uma condicional que verifique qual o sistema de bug tracking e instancie o service do mesmo.
```JAVA
public class BugTrackingServiceFactory {

  	public static BugTrackingService getBugTrackingService(final BugTrackingType bugTrackingType) {
		switch (bugTrackingType) {
		case BUGZILLA:
			return new BugzillaServiceImpl(new BugzillaDefeitoMapper());
		}
		return null;
	}
} 
```
#### Passo 4º
Criar uma enum onde ira conter informações da api do Bugzilla, como podemos ver no exemplo abaixo:
```JAVA
public enum BugzillaConfig {
  
  URL("http://url.repositorio.com"); // URL do repositório que irá utilizar.
  
  
} 
```
#### Passo 5º
Classe utilizada para converter o Objeto da API do bugzilla para o POJO da aplicação
O - deve se utilizar o tipo de objeto utilizado para representar o modelo de defeito na API do bugzilla
Defeito - POJO utilizado pela API para representar o defeito
```JAVA
public class BugzillaDefeitoMapper implements BugTrackingMapper<O, Defeito> {
	  /**
	 * Responsavel por converter os objetos das API's do 
	 * sistema de bugtracking para o POJO da API BugInf
	 * @param origem
	 * @return destino
	 */
	 @Override
	public Defeito convert(O defeito) {
	  // implementar conversão para o objeto defeito da API
	}
  
} 
```

#### Passo 6º
E por último adicionar na classe BugInfClient o tipo do sistema de bug tracking que se quer integrar. Como pode ser visto no exemplo abaixo:
```JAVA
public class BugInfClient {
	
	public static void main(String[] args) {
		
		BugInfFacade bugInfFacade = new BugInfFacadeImpl(BugTrackingType.BUGZILLA);
		bugInfFacade.executarIntegracaoDefeitos();
		
	}

}
```



