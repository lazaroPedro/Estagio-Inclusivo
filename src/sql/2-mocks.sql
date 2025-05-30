SET NAMES utf8mb4;

INSERT INTO enderecos (id_endereco, rua, bairro, municipio, estado, cep) VALUES
(20, 'Rua das Flores', 'Centro', 'Salvador', 'BA', '40010000'),
(21, 'Av. Brasil', 'Comércio', 'Feira de Santana', 'BA', '44001000'),
(22, 'Rua do Sol', 'Jardim', 'Vitória da Conquista', 'BA', '45001000'),
(23, 'Rua das Palmeiras', 'Barra', 'Salvador', 'BA', '40150000'),
(24, 'Av. Sete de Setembro', 'Campo Grande', 'Salvador', 'BA', '40060000'),
(25, 'Rua do Comércio', 'Centro', 'Ilhéus', 'BA', '45650000');

INSERT INTO usuarios (id_usuario, nome, email, hash_senha, salt, telefone, papel, fk_endereco) VALUES
-- Candidatos
(20, 'Ana Paula', 'ana.paula@gmail.com', 'KSHQb4qK4Iq4Varreom7tmxCTxHZuAgAeWuYvfwwGzM=', '/XVQW0V4NrX6jcT20Q9i1A==', '71999990001', 'CANDIDATO', 20),
(21, 'Carlos Eduardo', 'carlos.edu@gmail.com', '8UT7u5n5PrjSuGCd97rytjdZ1nmZTShfddiDzufo1kQ=', 'r211nBmbkvRqUbz9Cw4N3A==', '71999990002', 'CANDIDATO', 21),
(22, 'Juliana Lima', 'juliana.lima@gmail.com', 'sIS9DC4SvPVgXzA4cA+esQReiojfEFGfvmATK9T4poQ=', 'ou559pqaP+iItk3FfQQfVQ==', '71999990003', 'CANDIDATO', 22),
(23, 'Rafael Souza', 'rafael.souza@gmail.com', 'FYUzkGmG4JqJj3KkVgor8y+0R+Z+4S83hcVI858WgL4=', 'pU41ViL6MbMZ/ifivoUmNA==', '71999990004', 'CANDIDATO', 23),
-- Empresas
(24, 'Tech Solutions LTDA', 'contato@techsolutions.com', '9zCbxsy1gtGxRqLgomPb1KXVAMzqxqhZzeLaRepngBU=', '94Haw1nXzrNRbDBXjIsIFw==', '71999990005', 'EMPRESA', 24),
(25, 'Global Corp', 'rh@globalcorp.com', 'KuyFFPH8rHLxb+4b0e8pAWHgi7SB7jtY1ncAHQykKgM=', 'QCOA7U0LYLyvi2YnYocBjw==', '71999990006', 'EMPRESA', 25),
-- Admin
(26, 'Administrador', 'admin@estagio.com', '+142GMMHeJfGxu5fjo+iirH4VA3TaL47j/o/eKJz2Dw=', 'Au72ATpgi1ZKgWA8lU/1/g==', '71999990007', 'ADMIN', 20);


INSERT INTO candidatos (id_candidato, genero, nascimento, cpf) VALUES
(20, 'FEMININO', '1995-03-15', '12345678901'),
(21, 'MASCULINO', '1992-07-22', '23456789012'),
(22, 'FEMININO', '1998-11-30', '34567890123'),
(23, 'MASCULINO', '1990-05-05', '45678901234');


INSERT INTO empresas (id_empresa, cnpj, razao_social) VALUES
(24, '12345678000195', 'Tech Solutions LTDA'),
(25, '98765432000187', 'Global Corp');

INSERT INTO deficiencias (id_deficiencia, nome, descricao, tipo_deficiencia, tipo_apoio, fk_candidato) VALUES
(20, 'Deficiência Física', 'Mobilidade reduzida', 'FISICA', 'Cadeira de rodas', 20),
(21, 'Deficiência Visual', 'Cegueira total', 'VISUAL', 'Leitor de tela', 21),
(22, 'Deficiência Auditiva', 'Surdez bilateral', 'AUDITIVA', 'Intérprete de Libras', 22),
(23, 'Deficiência Intelectual', 'Dificuldade cognitiva', 'INTELECTUAL', 'Apoio pedagógico', 23);


INSERT INTO cursos (id_curso, nome, descricao, instituicao, data_inicio, data_fim, fk_candidato) VALUES
(20, 'Administração', 'Curso superior em Administração', 'UNEB', '2018-01-15', '2022-12-20', 20),
(21, 'Sistemas de Informação', 'Curso superior em SI', 'UFBA', '2017-03-01', '2021-12-15', 21),
(22, 'Design Gráfico', 'Curso superior em Design', 'UESC', '2019-02-20', '2023-12-10', 22),
(23, 'Engenharia Civil', 'Curso superior em Engenharia', 'UNIFACS', '2016-05-10', '2020-11-30', 23);

INSERT INTO vagas (id_vaga, descricao, requisitos, beneficios, titulo, status, qtd_vagas, fk_empresa, fk_endereco) VALUES
(20, 'Atendimento ao cliente, suporte via telefone e e-mail.', 'Boa comunicação, ensino médio completo.', 'Vale transporte, plano de saúde.', 'Assistente de Atendimento', 'ATIVA', 2, 24, 20),
(21, 'Desenvolvimento de sistemas web.', 'Conhecimento em Java, SQL.', 'Vale refeição, plano odontológico.', 'Desenvolvedor Júnior', 'ATIVA', 1, 24, 21),
(22, 'Criação de peças gráficas.', 'Photoshop, Illustrator.', 'Home office, plano de saúde.', 'Designer Gráfico', 'ATIVA', 1, 25, 22),
(23, 'Acompanhamento de obras e projetos.', 'AutoCAD, Excel.', 'Ajuda de custo, vale transporte.', 'Auxiliar de Engenharia', 'FINALIZADA', 1, 25, 23);

INSERT INTO candidato_vaga (fk_candidato, fk_vaga) VALUES
(20, 20),
(21, 21),
(22, 22),
(23, 23),
(20, 22),
(21, 20);

INSERT INTO curriculos (id_curriculo, objetivo, habilidades, experiencia, fk_candidato) VALUES
(20, 'Atuar na área administrativa.', 'Pacote Office, Comunicação', 'Estágio em escritório jurídico.', 20),
(21, 'Desenvolver sistemas e aplicativos.', 'Java, SQL, HTML', 'Projeto de extensão na UFBA.', 21),
(22, 'Trabalhar com design e criação.', 'Photoshop, Illustrator, Figma', 'Freelancer em design gráfico.', 22),
(23, 'Atuar na área de obras.', 'AutoCAD, Excel, Gestão de obras', 'Estágio na construtora Beta.', 23);