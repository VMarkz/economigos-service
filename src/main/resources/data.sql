INSERT INTO USUARIO(usuario, data_criacao, email, senha) VALUES('Aluno', '2021-03-01', 'aluno@email.com', '123456');
INSERT INTO CATEGORIA(categoria) VALUES ('Alimento'),('Eletrônicos'),('Casa'),('Locomoção'),('Salário'), ('Entretenimento');
INSERT INTO CONTA(apelido, banco, descricao, numero_conta) VALUES ('C6BANK', 'C6BANK', 'Contra Corrente', '1587389-032');
INSERT INTO CONTABIL(tipo, data_pagamento, descricao, fixo, valor, categoria_id, conta_id) VALUES ('R', '2021-03-01', 'Meu salário', true, 2000.00, 5, 1);
INSERT INTO CONTABIL(tipo, data_pagamento, descricao, fixo, valor, categoria_id, conta_id) VALUES ('G', '2021-05-01', 'Netflix', true, 32.90, 6, 1);
INSERT INTO CONTATO(email, mensagem) VALUES ('usuario@email.com', 'Gostei muito do app de vocês!');