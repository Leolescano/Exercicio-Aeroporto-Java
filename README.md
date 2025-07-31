# Sistema de Gerenciamento de Aeroporto

> Projeto desenvolvido por **Leonardo Lescano**.

## ✈️ Sobre o Projeto

Este é um mini-projeto em Java que simula um sistema de controle de torre para um aeroporto. A aplicação, executada via console, permite gerenciar aeronaves e sua alocação em portões de embarque, seguindo os princípios de design SOLID e boas práticas de programação orientada a objetos.

O objetivo é proporcionar uma experiência didática sobre arquitetura limpa, separação de responsabilidades e uso de interfaces, além de demonstrar como construir um sistema modular e extensível.

---

## 🚀 Funcionalidades Principais

- **Gerenciamento de Aeronaves:**
  - **Adicionar:** Cadastro de novas aeronaves, informando modelo, registro, companhia aérea, capacidade e estado.
  - **Editar:** Alteração dos dados de aeronaves já cadastradas.
  - **Excluir:** Remoção de aeronaves do sistema.
  - **Listar:** Visualização detalhada de todas as aeronaves registradas.

- **Gerenciamento de Portões de Embarque:**
  - **Atribuir:** Alocação de aeronaves a portões disponíveis.
  - **Liberar:** Desocupação de portões de embarque.
  - **Listar:** Exibição do status de todos os portões (livre ou ocupado, e por qual aeronave).

- **Interface de Usuário:**
  - Menu interativo e intuitivo via linha de comando.
  - Validação de entradas do usuário para garantir a integridade dos dados.
  - Mensagens de erro e sucesso claras para facilitar o uso.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+**: Linguagem principal do projeto.
- **Lombok**: Redução de código repetitivo (getters, setters, construtores).
- **Maven**: Gerenciamento de dependências e automação de build.
- **Paradigma Orientado a Objetos**: Foco em encapsulamento, herança, polimorfismo e abstração.

---

## 🏛️ Arquitetura e Design

O projeto foi estruturado para seguir os princípios **SOLID**, promovendo um código mais limpo, modular e de fácil manutenção.

### Estrutura de Pacotes

- `aplication`: Contém a classe `Program`, ponto de entrada da aplicação.
- `entities`: Classes de domínio (`Aircraft`, `Airplane`, `Airport`, `BoardingGate`).
- `exception`: Exceções customizadas, como `GatesException`.
- `interfaces`: Contratos de comportamento do sistema, segregados por responsabilidade:
  - `interfacesUI`: Interfaces para interação com o usuário.
  - `interfacesairport`: Interfaces para operações do aeroporto.
  - `validationinterfaces`: Interfaces para validações.
- `utilities`: Classes utilitárias, como `UI`, responsável pela interação com o usuário.

### Princípios SOLID Aplicados

- **SRP (Responsabilidade Única):** Cada classe tem uma responsabilidade bem definida.
- **OCP (Aberto/Fechado):** Fácil extensão do sistema sem modificar código existente.
- **LSP (Substituição de Liskov):** Subtipos podem substituir seus tipos base.
- **ISP (Segregação de Interfaces):** Interfaces pequenas e específicas.
- **DIP (Inversão de Dependência):** Uso de abstrações para desacoplamento.

#### Pontos de Melhoria
- Separar lógica de validação e criação de aeronaves da entidade `Airplane`.
- Evitar downcasts para garantir maior aderência ao LSP.
- Implementar injeção de dependências para maior flexibilidade.

---

## 📂 Exemplo de Fluxo de Uso

1. O usuário inicia o programa e visualiza um menu com opções.
2. Pode cadastrar uma nova aeronave, listar todas, editar ou excluir.
3. Pode atribuir uma aeronave a um portão de embarque ou liberar um portão.
4. O sistema valida todas as entradas e informa o resultado de cada operação.

---

## 🧪 Testes

> **Nota:** O projeto pode ser expandido com testes unitários utilizando JUnit, garantindo a robustez das funcionalidades principais.

---

## 🚦 Como Executar o Projeto

### Pré-requisitos

- JDK (Java Development Kit) 17 ou superior.
- Apache Maven.

### Passos para execução

1. **Clone o repositório:**
   ```bash
   git clone <URL_DO_SEU_REPOSITORIO>
   cd Java-Exercicio-Aeroporto
   ```

2. **Compile o projeto:**
   ```bash
   mvn compile
   ```

3. **Execute a aplicação:**
   ```bash
   mvn exec:java -Dexec.mainClass="aplication.Program"
   ```

Após a execução, o menu interativo será exibido no console.

---

## 👨‍💻 Autor

Desenvolvido por **Leonardo Lescano**  
[LinkedIn](https://www.linkedin.com/) | [GitHub](https://github.com/)

---

