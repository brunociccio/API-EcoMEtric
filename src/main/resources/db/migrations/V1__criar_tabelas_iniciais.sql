-- Tabela Cadastro
CREATE TABLE T_ECOMETRIC_CADASTRO (
    id_cadastro NUMBER(10) PRIMARY KEY,
    nm_empresa VARCHAR2(255) NOT NULL,
    nr_cnpj VARCHAR2(20) NOT NULL,
    inscricao_estadual VARCHAR2(15),
    razao_social VARCHAR2(255),
    porte CHAR(1),
    dt_abertura DATE,
    email VARCHAR2(255),
    T_ECOMETRIC_LOGIN_id_login NUMBER(10),
    CONSTRAINT fk_login FOREIGN KEY (T_ECOMETRIC_LOGIN_id_login) REFERENCES T_ECOMETRIC_LOGIN(id_login)
);

-- Tabela Login
CREATE TABLE T_ECOMETRIC_LOGIN (
    id_login NUMBER(10) PRIMARY KEY,
    nm_login VARCHAR2(80) NOT NULL,
    nr_senha VARCHAR2(15) NOT NULL,
    st_login VARCHAR2(15),
    id_cadastro NUMBER(10),
    CONSTRAINT fk_cadastro FOREIGN KEY (id_cadastro) REFERENCES T_ECOMETRIC_CADASTRO(id_cadastro)
);

-- Tabela Contato
CREATE TABLE T_ECOMETRIC_CONTATO (
    id_contato NUMBER(10) PRIMARY KEY,
    nr_ddi NUMBER(3),
    nr_ddd NUMBER(3),
    nr_telefone NUMBER(10),
    tp_contato VARCHAR2(80),
    st_telefone VARCHAR2(15),
    id_cadastro NUMBER(10),
    CONSTRAINT fk_contato_cadastro FOREIGN KEY (id_cadastro) REFERENCES T_ECOMETRIC_CADASTRO(id_cadastro)
);

-- Tabela Endereco
CREATE TABLE T_ECOMETRIC_ENDERECO (
    id_endereco NUMBER(10) PRIMARY KEY,
    nr_cep VARCHAR2(10),
    nm_logradouro VARCHAR2(125),
    nr_logradouro NUMBER(5),
    ds_complemento VARCHAR2(125),
    nm_bairro VARCHAR2(80),
    nm_cidade VARCHAR2(80),
    nm_estado CHAR(2),
    id_cadastro NUMBER(10),
    CONSTRAINT fk_endereco_cadastro FOREIGN KEY (id_cadastro) REFERENCES T_ECOMETRIC_CADASTRO(id_cadastro)
);

-- Tabela ConsumoEnergia
CREATE TABLE T_ECOMETRIC_CONSUMO_ENERGIA (
    id_consumo_energia NUMBER(10) PRIMARY KEY,
    nr_unidade_consumidora VARCHAR2(5),
    nr_fatura VARCHAR2(50),
    dt_referencia_inicial DATE,
    dt_referencia_final DATE,
    dt_emissao DATE,
    dt_vencimento DATE,
    st_pagamento VARCHAR2(15),
    qtd_consumo_kwh NUMBER(10,3),
    tp_leitura VARCHAR2(30),
    qtd_leitura_anterior NUMBER(10,3),
    vl_total NUMBER(10,3),
    vl_tarifa_consumo NUMBER(10,3),
    vl_tarifas_impostos NUMBER(10,3),
    st_bandeira_tarifa VARCHAR2(10)
);

-- Tabela Monitoramento
CREATE TABLE T_ECOMETRIC_MONITORAMENTO (
    id_monitoramento NUMBER(10) PRIMARY KEY,
    id_login NUMBER(10),
    id_consumo_energia NUMBER(10),
    id_projeto NUMBER(10),
    dt_validade DATE,
    st_monitoramento VARCHAR2(15),
    ds_monitoramento VARCHAR2(225),
    dt_emissao DATE,
    vl_porcentagem_diferenca NUMBER(4,1),
    vl_porcentagem_expectativa_melhoria NUMBER(3,1),
    CONSTRAINT fk_monitoramento_login FOREIGN KEY (id_login) REFERENCES T_ECOMETRIC_LOGIN(id_login),
    CONSTRAINT fk_monitoramento_consumo FOREIGN KEY (id_consumo_energia) REFERENCES T_ECOMETRIC_CONSUMO_ENERGIA(id_consumo_energia),
    CONSTRAINT fk_monitoramento_projeto FOREIGN KEY (id_projeto) REFERENCES T_ECOMETRIC_PROJETOS(id_projeto)
);

-- Tabela Projetos
CREATE TABLE T_ECOMETRIC_PROJETOS (
    id_projeto NUMBER(10) PRIMARY KEY,
    nm_projeto VARCHAR2(80),
    ds_ideia_projeto VARCHAR2(225),
    ds_pontos_melhorias VARCHAR2(225),
    ds_topicos_principais VARCHAR2(225),
    vl_porcentagem_melhoria NUMBER(3,1),
    st_projeto VARCHAR2(15)
);

-- Tabela Relatorio
CREATE TABLE T_ECOMETRIC_RELATORIO (
    id_relatorio NUMBER(10) PRIMARY KEY,
    ds_melhorias VARCHAR2(225),
    ds_pontos_faltantes_melhorias VARCHAR2(225),
    st_relatorio VARCHAR2(15),
    id_login NUMBER(10),
    id_consumo_energia NUMBER(10),
    vl_diferenca_consumo NUMBER(10,3),
    ds_justificativa_aumento VARCHAR2(225),
    dt_emissao_relatorio DATE,
    CONSTRAINT fk_relatorio_login FOREIGN KEY (id_login) REFERENCES T_ECOMETRIC_LOGIN(id_login),
    CONSTRAINT fk_relatorio_consumo FOREIGN KEY (id_consumo_energia) REFERENCES T_ECOMETRIC_CONSUMO_ENERGIA(id_consumo_energia)
);
