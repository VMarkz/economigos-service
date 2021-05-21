INSERT INTO USUARIO(data_criacao, email, senha)
VALUES ('2021-03-01', 'vitormarques@email.com', 'senha@123');
INSERT INTO CATEGORIA(categoria)
VALUES ('Alimento'),
       ('Eletrônicos'),
       ('Casa'),
       ('Locomoção'),
       ('Salário'),
       ('Entretenimento'),
       ('Investimento');
INSERT INTO CONTA(apelido, banco, descricao, numero_conta, usuario_id, valor_atual)
VALUES ('C6BANK', 'C6BANK', 'Contra Corrente', '1587389-032', 1, 0.0);
INSERT INTO CARTAO(fechamento, limite, nome, pago, valor, vencimento, usuario_id)
VALUES ('2021-03-15', 1000.0, 'C6Bank', true, 500.0, '2021-04-30', 1);
INSERT INTO META(descricao, meta_gasto, nome, valor_atual, valor_final, usuario_id, ativa, finalizada)
VALUES ('Viagem final de ano', false, 'Viagem', 0.00, 4000.00, 1, true, false);
INSERT INTO CONTATO(email, mensagem)
VALUES ('usuario@email.com', 'Gostei muito do app de vocês!');
-- INSERT INTO RENDA(id, data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id, recebido)
-- VALUES (1, '2021-03-01 00:00:00', 'Meu salário', true, 'Renda', 2000.00, 5, 1, true);
-- INSERT INTO RENDA(id, data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id, recebido)
-- VALUES (2, '2021-03-01 00:00:01', 'Meu investimento', false, 'Renda', 2000.00, 7, 1, true);
-- INSERT INTO GASTO(id, data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id, pago, cartao_id)
-- VALUES (3, '2021-03-01 00:00:00', 'Spotify', true, 'Gasto', 17.90, 6, 1, false, 1);
-- INSERT INTO GASTO(id, data_pagamento, descricao, fixo, tipo, valor, categoria_id, conta_id, pago, cartao_id)
-- VALUES (4, '2021-03-01 00:00:01', 'Coxinha', true, 'Gasto', 17.90, 1, 1, true, 1);

