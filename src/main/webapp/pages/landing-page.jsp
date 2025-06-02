<%--
  Created by IntelliJ IDEA.
  User: lazaropedro
  Date: 31/05/2025
  Time: 23:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Landing Page</title>
</head>
<style>




  .section-padding {
    padding: 80px 0;
  }

  .section-title {
    font-size: 2.5em;
    font-weight: 700;
    color: var(--dark-grey);
    margin-bottom: 15px;
  }

  .section-description {
    font-size: 1.1em;
    color: var(--medium-grey);
    max-width: 700px;
    margin: 0 auto 50px auto;
  }


  .hero-section {
    background-color: var(--background-accent);
    min-height: 70vh;
    display: flex;
    align-items: center;
  }


  .hero-section .main-title {
    font-size: 3.2em;
    font-weight: 700;
    color: var(--dark-grey);
    line-height: 1.2;
  }

  .hero-section .highlight-text {
    color: var(--primary-color);
  }

  .hero-section .description-text {
    font-size: 1.1em;
    color: var(--medium-grey);
    line-height: 1.6;
  }

  .search-box .input-group-text {
    background-color: var(--white);
    border-right: none;
    border-color: var(--border-light);
    color: var(--light-grey);
  }

  .search-box .form-control,
  .search-box .form-select {
    border-color: var(--border-light);
    box-shadow: none;
    padding: 0.75rem 1rem;
    color: var(--medium-grey);
  }

  .search-box .form-control::placeholder {
    color: var(--light-grey);
  }

  .search-box .form-select {
    border-left: 1px solid var(--border-light);
    border-right: 1px solid var(--border-light);
  }

  .search-box .search-button {
    background-color: var(--primary-color);
    color: var(--white);
    font-weight: 600;
    border: none;
    transition: background-color 0.3s ease;
  }

  .search-box .search-button:hover {
    background-color: var(--darker-accent);
  }



  .illustration-section img {

    max-width: 90%;
    height: auto;
  }

  .advantages-section {
    background-color: var(--white);
  }

  .advantage-card {
    background-color: var(--white);
    border-radius: 8px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .advantage-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
  }

  .card-icon-wrapper {
    width: 60px;
    height: 60px;
    background-color: var(--background-accent);
    border-radius: 5px;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .card-icon {
    font-size: 1.8em;
    color: var(--primary-color);
  }

  .card-title-text {
    font-size: 1.25em;
    font-weight: 600;
    color: var(--dark-grey);
    margin-bottom: 0.5rem;
  }

  .card-description-text {
    font-size: 0.95em;
    color: var(--medium-grey);
    line-height: 1.5;
    margin-bottom: 0;
  }



  .quota-law-section {
    background-color: var(--background-accent);
  }

  .quota-law-section .section-title {
    color: var(--primary-color);
    font-size: 2.8em;
    margin-bottom: 30px;
  }

  .quota-law-section .section-description-lg {
    font-size: 1.2em;
    color: var(--dark-grey);
    line-height: 1.7;
    margin-bottom: 0;
  }

  .quota-item {
    background-color: var(--white);
    border-radius: 8px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.05);
    transition: transform 0.3s ease, box-shadow 0.3s ease;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  .quota-item:hover {
    transform: translateY(-5px);
    box-shadow: 0 8px 25px rgba(0, 0, 0, 0.1);
  }

  .item-icon-wrapper {
    width: 50px;
    height: 50px;
    background-color: var(--background-accent);
    border-radius: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .item-icon {
    font-size: 1.5em;
    color: var(--primary-color);
  }

  .item-percentage {
    font-size: 2.5em;
    font-weight: 700;
    color: var(--primary-color);
    margin-bottom: 5px;
  }

  .item-description-text {
    font-size: 1em;
    color: var(--medium-grey);
    line-height: 1.4;
    margin-bottom: 0;
  }

  @media (max-width: 990px) {
    .content-wrap {
      margin-left: 0;
    }


    .hero-section .text-section {
      padding-right: 15px;
      padding-left: 15px;
      text-align: center;
    }
    .hero-section .main-title {
      font-size: 2.5em;
    }

    .search-box .form-control,
    .search-box .form-select,
    .search-box .search-button {
      flex-basis: 100%;
      margin-bottom: 10px;

    }
    .search-box .input-group-text {
      display: none;
    }

    .quota-law-section .section-title {
      font-size: 2.2em;
    }
    .quota-law-section .section-description-lg {
      font-size: 1.1em;
    }
    .item-percentage {
      font-size: 2em;
    }
    .item-description-text {
      font-size: 0.9em;
    }
  }

  @media (max-width: 768px) {
    .hero-section .main-title {
      font-size: 2em;
    }

    .section-title {
      font-size: 2em;
    }
    .section-description {
      font-size: 1em;
      margin-bottom: 30px;
    }

    .card-icon-wrapper {
      width: 50px;
      height: 50px;
    }
    .card-icon {
      font-size: 1.5em;
    }
    .card-title-text {
      font-size: 1.1em;
    }
    .card-description-text {
      font-size: 0.9em;
    }


    .quota-law-section {
      padding: 60px 0;
    }
    .quota-law-section .section-title {
      font-size: 1.8em;
      margin-bottom: 20px;
    }
    .quota-law-section .section-description-lg {
      font-size: 1em;
      padding: 0 15px;
    }
    .quota-item {
      margin-bottom: 1rem;
    }
    .item-icon-wrapper {
      width: 40px;
      height: 40px;
    }
    .item-icon {
      font-size: 1.2em;
    }
    .item-percentage {
      font-size: 1.8em;
    }
  }


</style>
<body>
    <%@ include file="/assets/components/header.jsp" %>


<main class="content-wrap">
  <section class="hero-section py-5">
    <div class="container-fluid py-5 container-xl">
      <div class="row align-items-center">
        <div class="col-lg-6 text-section px-5">
          <h1 class="main-title mb-4">Potencialize sua Empresa com a <span class="highlight-text">Inclusão</span>!</h1>
          <p class="description-text mb-4">
            Encontre empregos, crie currículos rastreáveis e enriqueça suas aplicações.
            Conecte-se a talentos diversos e transforme o seu ambiente de trabalho.
          </p>
          <form action="${pageContext.request.contextPath}/search" method="post" class="d-flex justify-content-center search-box input-group mb-3">
            <span class="input-group-text"><i class="fas fa-briefcase"></i></span>
            <input type="text" class="form-control" placeholder="Buscar..." required name="pesquisa">
            <select class="form-select" name="filtro" required>
              <option selected disabled value="">Selecione...</option>
              <option value="0">Vagas</option>
              <option value="1">Empresas</option>
              <option value="2">Curriculos</option>
            </select>
            <button class="btn search-button pd-0" type="submit"><i class="fas fa-search"></i></button>
          </form>
        </div>
        <div class="col-lg-6 illustration-section text-center d-none d-lg-block">
          <img src="/assets/images/banner.png" alt="Ilustração de Busca de Emprego" class="img-fluid">
        </div>
      </div>
    </div>
  </section>

  <section class="section-padding advantages-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-12 text-center">
          <h2 class="section-title">Por que Contratar Estagiários PCDs?</h2>
          <p class="section-description">
            Descubra os múltiplos benefícios e o valor que a inclusão de pessoas com deficiência
            traz para a sua equipe e para a sociedade.
          </p>
        </div>
      </div>

      <div class="row mt-5">
        <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
          <div class="advantage-card text-center p-4">
            <div class="card-icon-wrapper mb-3">
              <i class="fas fa-lightbulb card-icon"></i>
            </div>
            <h5 class="card-title-text">Diversidade e Inovação</h5>
            <p class="card-description-text">Novas perspectivas e soluções criativas para desafios.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
          <div class="advantage-card text-center p-4">
            <div class="card-icon-wrapper mb-3">
              <i class="fas fa-users card-icon"></i>
            </div>
            <h5 class="card-title-text">Clima Organizacional Positivo</h5>
            <p class="card-description-text">Ambiente mais inclusivo e empático para todos.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
          <div class="advantage-card text-center p-4">
            <div class="card-icon-wrapper mb-3">
              <i class="fas fa-balance-scale card-icon"></i>
            </div>
            <h5 class="card-title-text">Cumprimento da Lei de Cotas</h5>
            <p class="card-description-text">Atendimento à legislação e responsabilidade social.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
          <div class="advantage-card text-center p-4">
            <div class="card-icon-wrapper mb-3">
              <i class="fas fa-hands-helping card-icon"></i>
            </div>
            <h5 class="card-title-text">Melhoria da Reputação</h5>
            <p class="card-description-text">Marca valorizada por práticas de inclusão.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
          <div class="advantage-card text-center p-4">
            <div class="card-icon-wrapper mb-3">
              <i class="fas fa-seedling card-icon"></i>
            </div>
            <h5 class="card-title-text">Desenvolvimento de Talentos</h5>
            <p class="card-description-text">Investimento no potencial e crescimento profissional.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
          <div class="advantage-card text-center p-4">
            <div class="card-icon-wrapper mb-3">
              <i class="fas fa-heart card-icon"></i>
            </div>
            <h5 class="card-title-text">Engajamento e Fidelidade</h5>
            <p class="card-description-text">Funcionários mais motivados e leais à empresa.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
          <div class="advantage-card text-center p-4">
            <div class="card-icon-wrapper mb-3">
              <i class="fas fa-chart-line card-icon"></i>
            </div>
            <h5 class="card-title-text">Aumento da Produtividade</h5>
            <p class="card-description-text">Maior foco e comprometimento com os objetivos.</p>
          </div>
        </div>
        <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
          <div class="advantage-card text-center p-4">
            <div class="card-icon-wrapper mb-3">
              <i class="fas fa-universal-access card-icon"></i>
            </div>
            <h5 class="card-title-text">Acessibilidade e Inclusão</h5>
            <p class="card-description-text">Criação de um ambiente realmente acessível.</p>
          </div>
        </div>
      </div>

    </div>
  </section>

  <section class="section-padding quota-law-section">
    <div class="container">
      <div class="row">
        <div class="col-lg-10 offset-lg-1 text-center">
          <h2 class="section-title">A Lei de Cotas para Pessoas com Deficiência</h2>
          <p class="section-description-lg">
            A Lei nº 8.213/91, conhecida como Lei de Cotas, estabelece que empresas com 100 ou mais empregados
            devem preencher uma porcentagem de seus cargos com Pessoas com Deficiência (PCDs) ou beneficiários
            reabilitados pelo INSS. Esta medida visa promover a inclusão social e profissional, garantindo
            oportunidades e igualdade no mercado de trabalho.
          </p>
          <p class="section-description-lg mt-5">
            Os percentuais variam de acordo com o número total de empregados da empresa:
          </p>
          <div class="row justify-content-center mt-4 mb-5">
            <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
              <div class="quota-item text-center p-3">
                <div class="item-icon-wrapper mb-2">
                  <i class="fas fa-percent item-icon"></i>
                </div>
                <h3 class="item-percentage">2%</h3>
                <p class="item-description-text">De 100 a 200 empregados</p>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
              <div class="quota-item text-center p-3">
                <div class="item-icon-wrapper mb-2">
                  <i class="fas fa-percent item-icon"></i>
                </div>
                <h3 class="item-percentage">3%</h3>
                <p class="item-description-text">De 201 a 500 empregados</p>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
              <div class="quota-item text-center p-3">
                <div class="item-icon-wrapper mb-2">
                  <i class="fas fa-percent item-icon"></i>
                </div>
                <h3 class="item-percentage">4%</h3>
                <p class="item-description-text">De 501 a 1.000 empregados</p>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 col-sm-12 mb-4">
              <div class="quota-item text-center p-3">
                <div class="item-icon-wrapper mb-2">
                  <i class="fas fa-percent item-icon"></i>
                </div>
                <h3 class="item-percentage">5%</h3>
                <p class="item-description-text">Acima de 1.000 empregados</p>
              </div>
            </div>
          </div>
          <p class="section-description-lg mt-4">
            O não cumprimento dessas cotas pode acarretar em multas para a empresa. Mais do que uma obrigação legal,
            a contratação de estagiários e profissionais PCDs é uma oportunidade de enriquecer o ambiente de trabalho
            com diversidade e novas perspectivas, contribuindo para uma sociedade mais justa e inclusiva.
          </p>
        </div>
      </div>
    </div>
  </section>
</main>





</body>
</html>
