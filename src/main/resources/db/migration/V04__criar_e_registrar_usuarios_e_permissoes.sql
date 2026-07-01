CREATE TABLE usuario (
    codigo BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE permissao (
    codigo BIGINT(20) PRIMARY KEY,
    descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE usuario_permissao (
    codigo_usuario BIGINT NOT NULL,
    codigo_permissao BIGINT NOT NULL,
    PRIMARY KEY (codigo_usuario, codigo_permissao),
    FOREIGN KEY (codigo_usuario) REFERENCES usuario(codigo) ON DELETE CASCADE,
    FOREIGN KEY (codigo_permissao) REFERENCES permissao(codigo) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Inserções de exemplo: permissões
INSERT INTO permissao (codigo, descricao) VALUES
(1, 'ROLE_CADASTRAR_CATEGORIA'),
(2, 'ROLE_PESQUISAR_CATEGORIA'),
(3, 'ROLE_CADASTRAR_PESSOA'),
(4, 'ROLE_REMOVER_PESSOA'),
(5, 'ROLE_PESQUISAR_PESSOA'),
(6, 'ROLE_CADASTRAR_LANCAMENTO'),
(7, 'ROLE_REMOVER_LANCAMENTO'),
(8, 'ROLE_PESQUISAR_LANCAMENTO');

-- Inserções de exemplo: usuários (20 amostras)
INSERT INTO usuario (nome, email, senha) VALUES
('admin', 'admin@cash.com', 'admin'),
('Ana Silva', 'ana.silva@example.com', 'senha123'),
('Bruno Costa', 'bruno.costa@example.com', 'senha123'),
('Carla Pereira', 'carla.pereira@example.com', 'senha123'),
('Daniel Souza', 'daniel.souza@example.com', 'senha123'),
('Elisa Rocha', 'elisa.rocha@example.com', 'senha123'),
('Fábio Lima', 'fabio.lima@example.com', 'senha123'),
('Gisele Martins', 'gisele.martins@example.com', 'senha123'),
('Hugo Ribeiro', 'hugo.ribeiro@example.com', 'senha123'),
('Iara Fernandes', 'iara.fernandes@example.com', 'senha123'),
('João Almeida', 'joao.almeida@example.com', 'senha123'),
('Karen Gomes', 'karen.gomes@example.com', 'senha123'),
('Lucas Barbosa', 'lucas.barbosa@example.com', 'senha123'),
('Mariana Nunes', 'mariana.nunes@example.com', 'senha123'),
('Nicolas Pinto', 'nicolas.pinto@example.com', 'senha123'),
('Olga Melo', 'olga.melo@example.com', 'senha123'),
('Paulo Batista', 'paulo.batista@example.com', 'senha123'),
('Quésia Duarte', 'quesia.duarte@example.com', 'senha123'),
('Rafael Teixeira', 'rafael.teixeira@example.com', 'senha123'),
('Sofia Carvalho', 'sofia.carvalho@example.com', 'senha123'),
('Tiago Moreira', 'tiago.moreira@example.com', 'senha123');

-- Associação usuário_permissao (atribuições de exemplo)
-- Admin (usuario codigo 1) recebe todas as permissões
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8);

-- Usuários normais (codigo 2..21) recebem apenas permissões de PESQUISAR
INSERT INTO usuario_permissao (codigo_usuario, codigo_permissao)
SELECT u.codigo, p.codigo FROM usuario u
JOIN permissao p ON p.codigo IN (2,5,8)
WHERE u.codigo BETWEEN 2 AND 21;


