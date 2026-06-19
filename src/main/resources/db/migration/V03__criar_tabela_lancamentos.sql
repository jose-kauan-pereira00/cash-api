CREATE TABLE lancamento(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10, 2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento, valor, observacao, tipo, codigo_categoria, codigo_pessoa) VALUES
('Salário Mensal', '2024-05-05', '2024-05-05', 5500.00, 'Pagamento regular', 'RECEITA', 1, 1),
('Supermercado Extra', '2024-05-10', '2024-05-09', 450.32, 'Compras do mês', 'DESPESA', 2, 1),
('Aluguel Apartamento', '2024-05-15', NULL, 1200.00, 'Vence dia 15', 'DESPESA', 3, 1),
('Conta de Luz', '2024-05-12', '2024-05-12', 180.50, NULL, 'DESPESA', 3, 2),
('Venda de Monitor', '2024-05-20', '2024-05-20', 850.00, 'Usado', 'RECEITA', 1, 3),
('Assinatura Streaming', '2024-05-01', '2024-05-01', 55.90, 'Netflix/Disney', 'DESPESA', 4, 1),
('Manutenção Carro', '2024-05-25', NULL, 1200.00, 'Troca de óleo e filtros', 'DESPESA', 5, 4),
('Freelance Website', '2024-05-28', '2024-05-30', 2500.00, 'Projeto Landing Page', 'RECEITA', 1, 2),
('Restaurante Japonês', '2024-05-02', '2024-05-02', 120.00, 'Jantar com amigos', 'DESPESA', 2, 5),
('Academia Trimestral', '2024-06-01', '2024-05-31', 300.00, NULL, 'DESPESA', 4, 1),
('Reembolso Viagem', '2024-05-18', '2024-05-20', 450.00, 'Despesas de Uber', 'RECEITA', 1, 4),
('Internet Fibra', '2024-05-10', '2024-05-10', 99.90, 'Mensalidade fixa', 'DESPESA', 3, 2);