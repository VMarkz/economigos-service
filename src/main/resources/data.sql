INSERT INTO USUARIO(data_criacao, email, senha) VALUES('2021-03-01', 'vitormarques@email.com', 'senha@123');
INSERT INTO CATEGORIA(categoria) VALUES ('Alimento'),('Eletrônicos'),('Casa'),('Locomoção'),('Salário'), ('Entretenimento'), ('Investimento');
INSERT INTO CONTA(nome, banco, descricao, numero_conta, usuario_id, valor_atual) VALUES ('C6BANK', 'C6BANK', 'Contra Corrente', '1587389-032',1, 0.0);
INSERT INTO CARTAO(fechamento,limite,nome,pago,valor,vencimento, usuario_id) VALUES ('2021-03-15', 1000.0,'Cartão de crédito nacional C6Bank', true, 500.0, '2021-04-30',1);
INSERT INTO META(descricao, meta_gasto, nome, valor_inicial, valor_final,usuario_id) VALUES ('Viagem final de ano', true, 'Viagem', 0.00, 4000.00, 1);
INSERT INTO CONTATO(email, mensagem) VALUES ('usuario@email.com', 'Gostei muito do app de vocês!');
<<<<<<< HEAD
-- INSERT INTO RENDA(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id,recebido) VALUES (1,'2021-03-01', 'Meu salário', true, 'Renda', 2000.00, 5, 1, false);
-- INSERT INTO RENDA(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id,recebido) VALUES (2,'2021-04-01', 'Meu investimento', false, 'Renda', 2000.00, 7, 1, false);
INSERT INTO GASTO(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id, pago, cartao_id) VALUES (1,'2021-05-01', 'Netflix', false, 'Gasto', 32.90,6, 1, true, 1);
INSERT INTO GASTO(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id, pago, cartao_id) VALUES (2,'2021-05-01', 'Spotify', true, 'Gasto', 17.90, 6, 1, false, 1);
INSERT INTO GASTO(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id,pago, cartao_id) VALUES (3,'2021-05-01', 'Coxinha', true, 'Gasto', 17.90, 1, 1, false, 1);
=======
INSERT INTO RENDA(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id,recebido) VALUES (1,'2021-03-01', 'Meu salário', true, 'Renda', 2000.00, 5, 1, true);
-- INSERT INTO GASTO(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id, pago, cartao_id) VALUES (2,'2021-05-01', 'Netflix', false, 'Gasto', 32.90,6, 1, true, 1);
INSERT INTO RENDA(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id,recebido) VALUES (3,'2021-04-01', 'Meu investimento', false, 'Renda', 2000.00, 7, 1, true);
INSERT INTO GASTO(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id, pago, cartao_id) VALUES (4,'2021-05-01', 'Spotify', true, 'Gasto', 17.90, 6, 1, false, 1);
INSERT INTO GASTO(id,data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id,pago, cartao_id) VALUES (5,'2021-05-01', 'Coxinha', true, 'Gasto', 17.90, 1, 1, false, 1);
>>>>>>> main
