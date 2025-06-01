CREATE TABLE licitacoes (
    id SERIAL PRIMARY KEY,
    uasg_codigo VARCHAR(10) NOT NULL,
    numero_pregao VARCHAR(20) NOT NULL,
    descricao TEXT NOT NULL,
    edital TEXT NOT NULL,
    endereco TEXT NOT NULL,
    telefone VARCHAR(30),
    fax VARCHAR(30),
    data_abertura DATE
);