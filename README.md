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
```JAVA
public enum BugTrackingType {
   BUGZILLA; // representa o sistema de bug tracking
} 
```

```JAVA
public class BugzillaServiceImpl implements BugTrackingService {

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

Criar uma enum onde ira conter informações da api do Bugzilla, como podemos ver no exemplo abaixo:
```JAVA
public class BugzillaConfig {

  URL("http://url.repositorio.com"); // URL do repositório que irá utilizar.
  
} 
```




