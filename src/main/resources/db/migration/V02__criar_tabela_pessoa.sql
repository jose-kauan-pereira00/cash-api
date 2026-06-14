CREATE TABLE IF NOT EXISTS pessoa(
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo TINYINT(1) NOT NULL,
	
	-- Colunas referentes ao Endereço (@Embedded)
	logradouro VARCHAR(100),
	numero VARCHAR(20),
	complemento VARCHAR(50),
	bairro VARCHAR(50),
	cep VARCHAR(20),
	cidade VARCHAR(50),
	estado VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep) VALUES ('Nahs Golden', 1, 'Rua', 'Numero 50', 'Frente ao posto Medico', 'prata', '00000-000');
INSERT INTO pessoa (nome, ativo) VALUES ('Olavo de Carvalho', 0);
INSERT INTO pessoa (nome, ativo) VALUES ('Batman', 1);
INSERT INTO pessoa (nome, ativo) VALUES ('Arlequina', 1);
INSERT INTO pessoa (nome, ativo) VALUES ('Cleber joão', 1);
INSERT INTO pessoa (nome, ativo) VALUES ('Costa e Silva', 1);
INSERT INTO pessoa (nome, ativo) VALUES ('Gertulho Vargas', 1);
INSERT INTO pessoa (nome, ativo) VALUES
('Carlos Andrade', 1),
('Ana Beatriz', 1),
('Pedro Lucas', 0),
('Fernanda Lima', 1);
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento) VALUES ('Rebeca', 1, 'Rua', 'Numero 50', 'Frente ao posto Medico');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep) VALUES ('Golden', 1, 'Rua', 'Numero 50', 'Frente ao posto Medico', 'prata', '00000-000');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento) VALUES ('otavio andrade', 1, 'Rua', 'Numero 50', 'Frente ao posto Medico');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep) VALUES ('Naruto Uzumaki', 1, 'Rua', 'Numero 50', 'Frente ao posto Medico', 'prata', '00000-000');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento) VALUES ('Sasuke Uchirra', 1, 'Rua', 'Numero 50', 'Frente ao posto Medico');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep) VALUES ('Tenten', 1, 'Rua', 'Numero 50', 'Frente ao posto Medico', 'prata', '00000-000');