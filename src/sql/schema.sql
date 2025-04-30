create database estagioinclusivo;
use estagioinclusivo;
CREATE TABLE enderecos(
    id_endereco INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255) NOT NULL,
    bairro VARCHAR(255) NOT NULL,
    municipio VARCHAR(255) NOT NULL,
    estado VARCHAR(255) NOT NULL,
    cep CHAR(8) NOT NULL
);

CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    hashSenha TEXT NOT NULL,
    salt TEXT NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fk_endereco INT UNSIGNED NOT NULL,
    FOREIGN KEY (fk_endereco) REFERENCES enderecos(id_endereco)
);



CREATE TABLE candidatos(
    id_candidato INT UNSIGNED NOT NULL,
    cpf CHAR(11) NOT NULL,
    telefone CHAR(9) NOT NULL,

    FOREIGN KEY (id_candidato) references usuarios(id_usuario),
    PRIMARY KEY (id_candidato)

);


CREATE TABLE deficiencias(
    id_deficiencia INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    tipo_deficiencia ENUM("FISICA", "VISUAL", "AUDITIVA", "INTELECTUAL"
        ,"MENTAL", "SENSORIAL" ,"MULTIPLA","OUTRA") NOT NULL,
    tipo_apoio TEXT NOT NULL,
    fk_candidato INT UNSIGNED NOT NULL,
    FOREIGN KEY (fk_candidato) REFERENCES candidatos(id_candidato)
);

CREATE TABLE cursos(
    id_curso INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    fk_candidato INT UNSIGNED NOT NULL,
    FOREIGN KEY (fk_candidato) REFERENCES candidatos(id_candidato)
);

CREATE TABLE empresas(
    id_empresa INT UNSIGNED NOT NULL,
    cnpj CHAR(14) NOT NULL,
    razao_social VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_empresa) references usuarios(id_usuario),
    PRIMARY KEY (id_empresa)


);

CREATE TABLE vagas(
    id_vaga INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT NOT NULL,
    requisitos TEXT NOT NULL,
    beneficios TEXT NOT NULL,
    status ENUM("ATIVA", "FINALIZADA") NOT NULL,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    fk_endereco INT UNSIGNED NOT NULL,
    FOREIGN KEY (fk_endereco) REFERENCES enderecos(id_endereco)
);
CREATE TABLE candidato_vaga (
    fk_candidato INT UNSIGNED NOT NULL,
    fk_vaga INT UNSIGNED NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL ,
    FOREIGN KEY (fk_candidato) REFERENCES candidatos(id_candidato),
    FOREIGN KEY (fk_vaga) REFERENCES vagas(id_vaga),
    PRIMARY KEY(fk_candidato,fk_vaga)



);
