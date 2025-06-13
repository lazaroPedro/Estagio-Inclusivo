
# Estagio-Inclusivo

O projeto **"Estagio-Inclusivo"** é uma plataforma dedicada a conectar pessoas com deficiência a oportunidades de estágio. Ele visa facilitar a busca por vagas inclusivas para candidatos e a publicação de vagas por empresas que buscam talentos diversos.

## Funcionalidades

### Para Candidatos

- **Cadastro e Perfil**: Criação de conta e gerenciamento de informações pessoais, endereço, dados de acesso.
- **Currículo Online**: Criação e edição de currículos detalhados, incluindo objetivo, habilidades, experiência, cursos e informações sobre deficiências.
- **Busca e Candidatura**: Visualização de vagas ativas e possibilidade de se candidatar.
- **Acompanhamento de Candidaturas**: Visualização das vagas às quais o candidato se candidatou.

### Para Empresas

- **Cadastro e Perfil**: Criação de conta e gerenciamento de informações da empresa, endereço, dados de acesso.
- **Publicação de Vagas**: Criação, edição e exclusão de vagas de estágio, definindo requisitos, benefícios e status.
- **Gerenciamento de Candidatos**: Visualização dos candidatos que se aplicaram às suas vagas.
- **Pesquisa de Currículos**: Capacidade de buscar currículos de candidatos com base em habilidades e objetivos.

## Tecnologias Utilizadas

- **Backend**: Java (Servlets, JSP)
- **Banco de Dados**: MySQL
- **Build Tool**: Maven
- **Web Server**: Apache Tomcat 
- **Frontend**: HTML, CSS, JavaScript 
- **Validação**: Sistema de validação customizado baseado em anotações.

## Autores

Projeto realizado como atividade avaliativa da disciplina **Linguagem de Programação Orientada a Objetos**. Desenvolvido por **Caio Alves**, **Jessica**, **Lázaro Pedro**, **Luis Felipe** e **Maria Clara**, sob orientação do professor **Woquiton Lima Fernandes**.
## Como Rodar o Projeto Localmente

Este projeto utiliza **Docker** e **Docker Compose** para facilitar a configuração do ambiente de desenvolvimento, incluindo o banco de dados.

### Pré-requisitos

Certifique-se de ter o **Docker** e o **Docker Compose** instalados em sua máquina. Você pode verificar com:

```bash
docker --version
docker-compose --version  # ou 'docker compose version' para versões mais novas
```

Caso não estejam instalados, siga as instruções na [documentação oficial do Docker](https://docs.docker.com/get-docker/).

### Passos para Configuração

#### 1. Clone o Repositório

```bash
git clone https://github.com/lazaropedro/estagio-inclusivo.git
cd estagio-inclusivo
```

#### 2. Iniciar os Serviços com Docker Compose

Execute o seguinte comando na raiz do projeto:

```bash
docker-compose up --build
```

Esse comando irá:

- Construir a imagem Docker da aplicação.
- Iniciar um contêiner MySQL (serviço `db`).
- Executar os scripts SQL de esquema (`1-schema.sql`) e mocks (`2-mocks.sql`) para popular o banco de dados.
- Iniciar o contêiner da aplicação Java (serviço `app`).

#### 3. Acessar a Aplicação

Após os serviços serem iniciados, a aplicação estará disponível em:

- [http://localhost:8080/](http://localhost:8080/)

## Estrutura de Pastas Relevante

A estrutura do projeto segue um padrão comum para aplicações web Java, com as seguintes pastas principais:

* `/docs`: Contém os diagramas do projeto.
* `/src/main/java`: Contém todo o código-fonte Java da aplicação, incluindo modelos (models), DAOs (Data Access Objects), controladores (servlets) e utilitários.
* `/src/main/webapp`: Contém os recursos da aplicação web, como páginas JSP, arquivos JavaScript e folhas de estilo CSS.
* `/src/sql`: Contém os scripts SQL para a criação do esquema do banco de dados e dados de exemplo (mocks).
