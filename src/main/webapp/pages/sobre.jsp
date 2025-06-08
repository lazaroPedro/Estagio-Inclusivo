<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Sobre - Portal de Vagas Inclusivo</title>
  <style>
  body {
    margin: 0;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f9fafb;
    color: #1f2937;
  }

  .main-content {
    margin-left: 80px;
    padding: 40px 60px;
    background-color: white;
    border-radius: 20px;
    box-shadow: 0 10px 25px rgba(124, 58, 237, 0.3);
  }

  .content-wrapper {
    background-color: white;
    padding: 40px;
    border-radius: 20px;
    box-shadow: 0 6px 20px rgba(124, 58, 237, 0.2);
  }

  .sobre-header {
    margin-bottom: 30px;
    padding: 20px 0;
    border-bottom: 2px solid #e5e7eb;
  }

  .sobre-header h1 {
    font-size: 2.4rem;
    margin: 0;
    color: #4c1d95;
  }

  .descricao {
    max-width: 1000px;
    line-height: 1.8;
    font-size: 1.1rem;
  }

  .descricao p {
    margin-bottom: 20px;
  }

  .card-container {
    display: flex;
    gap: 20px;
    flex-wrap: wrap;
    margin: 40px 0;
  }

  .info-card {
    flex: 1;
    min-width: 280px;
    background-color: white;
    padding: 25px;
    border-radius: 16px;
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
    border-left: 6px solid #7c3aed;
  }

  .info-card h2 {
    font-size: 1.4rem;
    color: #7c3aed;
    margin-top: 0;
  }

  .info-card p,
  .info-card ul {
    font-size: 1rem;
    margin: 0;
  }

  .info-card ul {
    padding-left: 18px;
    margin-top: 10px;
  }

  .equipe-section {
    margin-top: 60px;
  }

  .equipe-title {
    font-size: 1.6rem;
    color: #4c1d95;
    margin-bottom: 20px;
    text-align: center;
  }

  .equipe-grid {
    display: flex;
    flex-wrap: wrap;
    gap: 20px;
    justify-content: center; 
  }

  .equipe-card {
    background-color: #ede9fe;
    border-radius: 12px;
    padding: 15px;
    width: 30%; /* 3 cards por linha */
    box-sizing: border-box;
    text-align: center;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.05);
    min-width: 0;
  }

  .equipe-card h3 {
    margin: 0;
    font-size: 1.1rem;
    font-weight: 600;
    color: #111827;
  }

  .equipe-card p {
    margin: 5px 0 0;
    font-size: 0.95rem;
    color: #374151;
  }

  footer {
    text-align: center;
    padding: 20px;
    background: #4c1d95;
    color: white;
    margin-top: 60px;
    font-size: 0.95rem;
    border-top-left-radius: 20px;
    border-top-right-radius: 20px;
  }

  /* Responsividade */
  @media (max-width: 768px) {
    .equipe-card {
      width: 45%; /* 2 cards por linha em telas médias */
    }
  }

  @media (max-width: 480px) {
    .equipe-card {
      width: 100%; /* 1 card por linha em telas pequenas */
    }
  }
</style>
  
</head>
<body>

  <%@ include file="/assets/components/header.jsp" %>

  <main class="main-content">
    <section class="content-wrapper" role="region" aria-labelledby="titulo-sobre">
      <header class="sobre-header">
        <h1 id="titulo-sobre">Sobre Nós</h1>
      </header>

      <div class="descricao">
        <p><strong>Portal de Vagas de Estágio Inclusivo</strong> é um projeto desenvolvido por alunos do curso de Tecnologia em Análise e Desenvolvimento de Sistemas no Instituto Federal Baiano,proposto pelo professor na disciplina de Programação Orientada a Objetos, e evoluiu com a colaboração de toda a equipe.</p>

        <p>Nosso objetivo é facilitar o acesso a vagas de estágio de forma simples, acessível e inclusiva, promovendo igualdade de oportunidades no ambiente profissional.</p>

        <p>Acreditamos que a tecnologia pode ser uma ponte entre talentos e oportunidades. Este portal foi criado para impactar positivamente a vida de estudantes com deficiência, conectando-os a vagas que respeitam suas individualidades.</p>
      </div>

      <section class="card-container" role="region" aria-labelledby="titulo-valores">
        <div class="info-card">
          <h2>Missão</h2>
          <p>Promover a inclusão social através de oportunidades de estágio acessíveis, igualitárias e que valorizem as potencialidades de cada estudante.</p>
        </div>
        <div class="info-card">
          <h2>Visão</h2>
          <p>Ser uma ferramenta confiável e útil para facilitar o acesso de estudantes com deficiência a estágios.</p>
        </div>
        <div class="info-card">
          <h2 id="titulo-valores">Valores</h2>
          <ul>
            <li>Inclusão</li>
            <li>Respeito à diversidade</li>
            <li>Inovação</li>
            <li>Responsabilidade social</li>
          </ul>
        </div>
      </section>

      <section class="equipe-section" role="region" aria-labelledby="titulo-equipe">
        <div class="equipe-title" id="titulo-equipe">Equipe do Projeto</div>
        <div class="equipe-grid">
          <div class="equipe-card">
            <h3>Lázaro Pedro</h3>
            <p>Desenvolvedor</p>
          </div>
          <div class="equipe-card">
            <h3>Jéssica Tainá</h3>
            <p>Desenvolvedora</p>
          </div>
          <div class="equipe-card">
            <h3>Caio Alves</h3>
            <p>Desenvolvedor</p>
          </div>
           <div class="equipe-card">
            <h3>Maria Clara</h3>
            <p>Desenvolvedora</p>
          </div>
          <div class="equipe-card">
            <h3>Luis Felipe</h3>
            <p>Desenvolvedor</p>
          </div>
          <div class="equipe-card">
            <h3>Woquiton Fernandes</h3>
            <p>Orientador</p>
          </div>
        </div>
      </section>
    </section>
  </main>

</body>
</html>
