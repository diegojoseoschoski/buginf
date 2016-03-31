# BugInf 1.0.0-snapshot
BugInf é uma API de código aberto ([GNU LGPL](https://github.com/diegojoseoschoski/buginf/blob/master/LICENSE)) que auxilia na integração das informações de repositórios de sistemas de bugtracking.


## Requisitos
- Java 1.7 ou superior
- Apache Maven 3.0.5 ou superior
- Elasticsearch 4.0.2 ou superior
- Kibana 1.5.2 ou superior


## Instalação
definir

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



