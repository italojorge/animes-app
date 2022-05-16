<img height="480px" src="gif/gif_app.gif">

## Contexto do app:

Aplicativo de listagem de animes, onde é possível filtrar a lista através das categorias ou pesquisar um anime através de seu nome. Também é possível visualizar os detalhes do anime clicado. O App é composto por 4 telas.

## Telas do app:

- Tela Splash: Splash com animação Lottie

- Tela Login: Esta tela possui tratamentos de erro caso o usuário digite email ou senha inválidos (vazia, com dígitos repetidos ou com tamanho menor que 4). É possível digitar qualquer e-mail e senha para realizar autenticação no app para fazer login com sucesso. A tela também permite realizar o login através de biometria. Para isso, caso o device possua o hardware de biometria, será exibido um toggle na tela, permitindo salvar as credenciais de acesso caso o login seja realizado com sucesso e podendo realizar login por biometria do segundo acesso em diante.

- Home: Tela que apresenta a lista de animes com opções de filtro por categoria (tv, ova, filme...) ou pela busca de um anime através de seu título. A tela utiliza paginação para carregar a lista de animes e esta foi implementada através da lib "Paging 3" (parte do jetpack do android).

- Tela de detalhes de um anime: Nessa tela são exibidas em detalhes as informações de um anime clicado na lista da tela anterior (Home).

## Arquitetura do aplicativo:

O app utiliza Clean Architecture + MVVM para desenvolvimento das features, onde cada feature é dividida em 4 camadas: UI, Domain, Data e Data Remote.

## Características gerais da arquitetura do app:

- Utiliza a abordagem de single activity com gerenciamento de fragments através do navigation;
- App totalmente escrito em Kotlin, incluindo o Gradle. Para isso, utiliza Kotlin DSL para organização dos arquivos Gradle do app;
- Apresenta modularização, onde cada módulo tem sua responsabilidade única dentro do aplicativo.
- Possui testes instrumentados (utilizando Espresso) e testes unitários (utilizando JUnit5) que seguem o BDD para suas escritas.
- Possui internacionalização de strings em pt-Br e inglês.

Obs: No momento, a única feature coberta por testes é a Feature Auth.

## Abaixo serão listados os módulos existentes e suas respectivas funções

Módulos Compartilhados:

- Módulo app: Contém a application do app, a splash e a "AppActivity" (activity conteiner de todos os fragments e views do projeto).

- Módulos Bases: Responsáveis por conter o código compartilhado entre as features do app, sendo eles:

*Base Data Remote: Responsável por guardar o código compartilhado entre as camadas data remote das features do app. Contém os tratamentos de erros genéricos das requisições, interceptors, constantes utilizadas pelo data remote das features e etc...

*Base Domain: Responsável por conter o código compartilhado de domínio das features do app. Merece destaque a classe Result que é utilizada por todo o app para retorno de dado entre as camadas, que indica se a resposta da chamada foi sucesso ou falha. Também contém a interface do UseCase, utilizada por todos os use cases das features do app. Todas as camadas das features do app conhecem a camada Base Domain.
                  
*Core: Responsável por conter o código compartilhado pelas camadas de ui das features do app. Nele estão contidas as extensions de views, dialogs, liveData, navigation, customViews.
           
*Test Utils: Módulo utilitário que contém classes utilizadas para criação dos testes unitários e de UI do app.

- Módulo Data Local: Módulo compartilhado entre as features, responsável por salvar, obter e editar as informaçoes salvas localmente no app. Através de sharedPreferences para gerenciamneto de dados simples e Room para dados complexos. Optei por deixar este módulo compartilhado entre as features devido a alguns problemas comentados no Google I/O das abordagens de utilização do Room Híbrida e por feature, onde se torna complexo o gereciamento quando uma tabela deve ser utilizada por 2 features ao mesmo tempo.

- Módulo DI: Responsável por gerenciar as injeções de dependencia do aplicativo. Este módulo é independente de todos e precisa conhecer todos os outros para conseguir realizar a DI sem gerar dependência cíclica.

- Módulo Navigation: Responsável por gerenciar a navegação entre as features do app. Supondo que o usuário deseje navegar da feature A para feature B, porém essas features estão em módulos separados que não se conhecem, a navegação neste caso será feita através de navigation por deeplink. Cada feature contém uma interface de navegação (ex: AuthNavigation) que contém métodos para navegar para outra feature. O módulo navigation realiza a implementação destas interfaces, onde através de deeplink do navigation, ele consegue gerenciar a navegação entre as features. Ele contém um xml de navigation chamado "global-navigation" que contém todos os gráficos de navegação de cada feature, permitindo assim conhecê-las para realizar a navegação por deeplink.

- Módulo buildSrc: Responsável por conter arquivos utilitários para gerenciamento do gradle. Possui extensions de Kotlin DSL e constantes para facilitar o gerenciamento das dependencias de libs que as módulos precisam.

##

- Módulos de features: Como explicado anteriormente, cada feature possui 4 módulos: UI, Domain, Data e Data Remote.

*Módulo UI: Contém os xml e implementaçao dos respectivos fragments e views que a feature utiliza. Possui um xml de navigation que controlará a navegação interna da feature. Também contém o código relacionado a camada "Presentation" do Clean, normalmente realizado no ViewModel.
                  
*Módulo Domain: Contém as regras de negócios do aplicativo. É implementado através de UseCases e interface do Repository.
       
*Módulo Data: Responsável por gerenciar a obtenção do dado solicitado, se será localmente do app ou através de conexão remota. É o módulo que implementa a interface do repository contida na camada domain e possui as interfaces do remote e local data source para escolha e obtenção de seus dados.
                    
*Módulo Data Remote: Responsável por conter o service da feature e gerenciar as chamadas remotas para obtenção dos dados. No caso deste app, a implementaçao dessa camada utiliza a lib "Retrofit" para troca de informaçoes remotas.

1. Feature Auth: Contém todos os módulos de feature descritos anteriormente, é a feature responsável pela implementação da biometria e tela de login do app.

2. Feature Home: É a feature responsável pela implementação das telas de listagem dos animes e detalhes do anime clicado.

##