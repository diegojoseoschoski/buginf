# buginf
API desenvolvida como parte do trabalho de conclusão de curso. Sistemas de Informação / Uniritter.

Esta API realiza a integração das informações de repositórios de sistemas de bugtracking com o ElasticSearch. 

Até o momento a API funciona para os seguintes sistemas de bugtracking:
<ul>
<li>Redmine</li>
</ul>
Arquitetura e componentes da ferramenta: 
![Alt text](https://cloud.githubusercontent.com/assets/6173960/10487633/3e4e2e1c-726b-11e5-99af-8656f6bc55a4.png?raw=true "Arquitetura dos componentes do BugInf")
## Módulos da ferramenta BugInf
1. Módulo de Bug Tracking: Este módulo é responsável por encapsular a API do sistema de bugtracking que deseja analisar.
2. Módulo BugInf: É o principal módulo desta ferramenta. Ele é respónsavel pela execução e integração com os demais módulos.
3. Módulo de visualização: É o módulo responsável por fornecer as visualizações desta ferramenta. Ele é composto pelas seguintes ferramentas:
<ul>
<li>[Elasticsearch 4.0.2](https://github.com/elastic/elasticsearch)</li>
<li>[Kibana 1.5.2](https://github.com/elastic/kibana)</li>
</ul>




Diagrama de classes da ferramenta:
![Alt text](https://cloud.githubusercontent.com/assets/6173960/10487631/3c588058-726b-11e5-8ca9-10afd59416a9.png?raw=true "Diagrama de classe da ferramenta")

## Tutorial de extensão da API do BugInf para outro sistema de bugtracking:

Neste tutorial será demonstrado como seria a extensão utilizando o sistema de bug tracking Bugzilla.
#### Passo 1º
```JAVA
public enum BugTrackingType {
   BUGZILLA; // representa o sistema de bug tracking
} 
```
#### Passo 2º
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
```JAVA
public class BugTrackingServiceFactory {

  	public static BugTrackingService getBugTrackingService(final BugTrackingType bugTrackingType) {
		switch (bugTrackingType) {
		case BUGZILLA:
			return new BugzillaServiceImpl();
		}
		return null;
	}
} 
```
#### Passo 4º
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
#### Passo 5º
Criar uma enum onde ira conter informações da api do Bugzilla, como podemos ver no exemplo abaixo:
```JAVA
public enum BugzillaConfig {
  
  URL("http://url.repositorio.com"); // URL do repositório que irá utilizar.
  
  
} 
```

VER ORDEM CERTA
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


E por último adicionar na classe BugInfClient o tipo do sistema de bug tracking que se quer integrar. Como pode ser visto no exemplo abaixo:
```JAVA
public class BugInfClient {
	
	public static void main(String[] args) {
		
		BugInfFacade bugInfFacade = new BugInfFacadeImpl(BugTrackingType.BUGZILLA);
		bugInfFacade.executarIntegracaoDefeitos();
		
	}

}
```




