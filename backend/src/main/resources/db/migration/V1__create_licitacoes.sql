CREATE TABLE licitacoes (
    id SERIAL PRIMARY KEY,
    uasg_codigo VARCHAR(10) NOT NULL,
    numero_pregao VARCHAR(20) NOT NULL,
    descricao TEXT NOT NULL,
    data_abertura DATE
);