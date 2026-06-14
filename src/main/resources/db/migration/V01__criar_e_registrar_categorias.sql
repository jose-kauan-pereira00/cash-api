CREATE TABLE categoria(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;

INSERT INTO categoria (nome) VALUES 
('Lazer'),
('Alimentação'),
('Supermercado'),
('Farmácia'),
('Saúde e Beleza'),
('Alimentos e Bebidas'),
('Brinquedos e Jogos'),
('Esporte e Lazer'),
('Automotivo'),
('Outros');
