-------------------
-- CRIA DATABASE --
-------------------
--DROP DATABASE IF EXISTS "trabalhoPi2";
CREATE DATABASE "trabalhoPi2"
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default       
       CONNECTION LIMIT = -1;

---------------------------------------------------
-- SELECIONAR MANUALMENTE A DATABASE NO POSTGRES --
-- SENAO VAI CRIAR EM OUTRA BASE :(              --
---------------------------------------------------

------------------
-- CRIA TABELAS --
------------------
-- usuario
DROP TABLE IF EXISTS usuario cascade;
CREATE TABLE usuario (
  ID serial  NOT NULL
  , nome VARCHAR(50)
  , email VARCHAR(50)
  , senha VARCHAR(50)
  , data DATE  
  , PRIMARY KEY(ID));

-- produto
DROP TABLE IF EXISTS produto cascade;
CREATE TABLE produto (
  IDProd serial  NOT NULL
  , descricao VARCHAR(50) 
  , PRIMARY KEY(IDProd));

-- Estoque
DROP TABLE IF EXISTS estoque cascade;
CREATE TABLE estoque (
  IDEsto serial  NOT NULL
  , IDProd INTEGER
  , PRIMARY KEY(IDEsto)
  , FOREIGN KEY(IDProd)
	REFERENCES produto(IDProd));

