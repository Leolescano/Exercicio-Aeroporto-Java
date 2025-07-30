# Sistema de Gerenciamento de Aeroporto

> Projeto desenvolvido por Leonardo Lescano.

Este é um mini-projeto em Java que simula um sistema de controle de torre para um aeroporto. A aplicação, executada via console, permite gerenciar aeronaves e sua alocação em portões de embarque, seguindo os princípios de design SOLID e boas práticas de programação orientada a objetos.

## 🚀 Funcionalidades Principais

- **Gerenciamento de Aeronaves:**
  - **Criar:** Adicionar novas aeronaves ao sistema, informando modelo, registro, companhia aérea, capacidade e estado.
  - **Editar:** Modificar informações de uma aeronave existente.
  - **Excluir:** Remover uma aeronave do sistema.
  - **Listar:** Visualizar todas as aeronaves cadastradas com seus detalhes.
- **Gerenciamento de Portões de Embarque:**
  - **Atribuir:** Alocar uma aeronave a um portão de embarque disponível.
  - **Liberar:** Desocupar um portão de embarque.
  - **Listar:** Exibir o status de todos os portões (livre ou ocupado e por qual aeronave).
- **Interface de Usuário:**
  - Menu interativo e intuitivo via linha de comando.
  - Validação de entradas do usuário para garantir a integridade dos dados.

## 🛠️ Tecnologias Utilizadas

- **Java:** Linguagem principal do projeto.
- **Lombok:** Biblioteca para reduzir código boilerplate (getters, setters, construtores).
- **Maven:** Gerenciador de dependências e automação de build (implícito pela estrutura).

## 🏛️ Arquitetura e Design

O projeto foi estruturado para seguir os princípios **SOLID**, promovendo um código mais limpo, modular e de fácil manutenção.

### Estrutura de Pacotes

- `aplication`: Contém a classe `Program`, o ponto de entrada (main) da aplicação.
- `entities`: Classes de domínio que representam as entidades do sistema, como `Aircraft`, `Airplane`, `Airport` e `BoardingGate`.
- `exception`: Exceções customizadas, como `GatesException`, para um tratamento de erros mais específico.
- `interfaces`: Contratos que definem os comportamentos do sistema. Estão segregados por responsabilidade:
  - `interfacesUI`: Interfaces relacionadas à interface do usuário.
  - `interfacesairport`: Interfaces para as operações do aeroporto.
  - `validationinterfaces`: Interfaces para as lógicas de validação.
- `utilities`: Classes utilitárias, como a `UI`, que gerencia a interação com o usuário.

### Análise dos Princípios SOLID

- **(S) Princípio da Responsabilidade Única (SRP):**
  - **Aplicação:** A classe `UI` é responsável pela interação com o usuário, enquanto a `Airport` orquestra a lógica de negócio.
  - **Ponto de Melhoria:** A classe `Airplane` atualmente acumula responsabilidades de entidade (dados do avião) e de serviço (validação de input e criação via console). O ideal seria mover os métodos `validate...` e `createAircraft` para uma classe de serviço ou utilitária.

- **(O) Princípio Aberto/Fechado (OCP):**
  - **Aplicação:** O uso da classe abstrata `Aircraft` e de múltiplas interfaces permite que o sistema seja estendido (ex: adicionar um `Helicopter`) sem modificar o código existente que opera sobre a abstração `Aircraft`.

- **(L) Princípio da Substituição de Liskov (LSP):**
  - **Aplicação:** `Airplane` é um subtipo de `Aircraft` e pode substituí-lo.
  - **Ponto de Melhoria:** O código realiza alguns *downcasts* (ex: `(Airplane) this.plane` na classe `Airport`). Isso pode ser arriscado e violar o LSP se novas subclasses de `Aircraft` forem adicionadas no futuro. O ideal é evitar `downcasts` e trabalhar sempre com a classe base (`Aircraft`).

- **(I) Princípio da Segregação de Interfaces (ISP):**
  - **Aplicação:** Este é um ponto forte do projeto. As interfaces são pequenas e coesas, focadas em um único comportamento (ex: `AddAircraft`, `DeleteAircraft`, `ValidationCapacity`). Isso evita que as classes implementem métodos que não precisam.

- **(D) Princípio da Inversão de Dependência (DIP):**
  - **Aplicação:** O projeto utiliza interfaces para definir abstrações, o que é o primeiro passo para o DIP.
  - **Ponto de Melhoria:** As classes de alto nível (como `UI`) ainda instanciam suas dependências diretamente (`new Airport(5)`). Para um desacoplamento total, poderia ser utilizada a Injeção de Dependência (DI), seja manualmente ou com um framework como Spring ou Guice.

## 🚀 Como Executar o Projeto

1.  **Pré-requisitos:**
    - JDK (Java Development Kit) 17 ou superior.
    - Apache Maven.

2.  **Clone o repositório:**
    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd aeropuerto
    ```

3.  **Compile o projeto:**
    ```bash
    mvn compile
    ```

4.  **Execute a aplicação:**
    ```bash
    mvn exec:java -Dexec.mainClass="aplication.Program"
    ```

Após a execução, o menu interativo será exibido no console.

*Este README foi gerado com base na análise do código-fonte do projeto.*