-- Segunda Chance - schema corrigido para MySQL / TiDB Cloud
-- Gerado a partir do SQL fornecido e do estado atual validado do backend.
--
-- Observações:
-- 1) Todas as referências antigas a `mydb` foram removidas.
-- 2) O schema usado é somente `segundachance`.
-- 3) `anuncio.descricao` foi ajustado para TEXT, conforme a entidade JPA atual.
-- 4) `anuncio.preco` foi ajustado para DECIMAL(10,2), conforme BigDecimal no backend.
-- 5) A tabela `imagens_anuncio` foi adicionada conforme o módulo ImagemAnuncio já testado.
-- 6) Roles e categorias iniciais são inseridas de forma idempotente.
-- 7) A tabela `solicitacao` NÃO é criada manualmente aqui porque a estrutura completa
--    da entidade não estava presente no SQL fornecido. No primeiro deploy, mantenha
--    SPRING_JPA_HIBERNATE_DDL_AUTO=update para o Hibernate criar/completar qualquer
--    tabela/coluna faltante exatamente conforme as entidades atuais do projeto.

CREATE DATABASE IF NOT EXISTS `segundachance`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE `segundachance`;

SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS;
SET UNIQUE_CHECKS = 0;

SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;

-- =========================================================
-- ROLES
-- =========================================================
CREATE TABLE IF NOT EXISTS `roles` (
  `perfil_id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`perfil_id`),
  UNIQUE KEY `uk_roles_nome` (`nome`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- USERS
-- =========================================================
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `role_id` INT NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_users_email` (`email`),
  KEY `idx_users_role_id` (`role_id`),
  CONSTRAINT `fk_users_roles`
    FOREIGN KEY (`role_id`)
    REFERENCES `roles` (`perfil_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- CATEGORY
-- =========================================================
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `uk_category_name` (`name`)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- ANUNCIO
-- =========================================================
CREATE TABLE IF NOT EXISTS `anuncio` (
  `anuncio_id` INT NOT NULL AUTO_INCREMENT,
  `titulo` VARCHAR(100) NOT NULL,
  `user_id` INT NOT NULL,
  `descricao` TEXT NOT NULL,
  `status` ENUM('DISPONIVEL', 'RESERVADO', 'FINALIZADO')
    NOT NULL DEFAULT 'DISPONIVEL',
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `preco` DECIMAL(10,2) NULL,
  `tipo` ENUM('VENDA', 'DOACAO') NOT NULL,
  `category_id` INT NOT NULL,
  PRIMARY KEY (`anuncio_id`),
  KEY `idx_anuncio_user_id` (`user_id`),
  KEY `idx_anuncio_category_id` (`category_id`),
  KEY `idx_anuncio_created_at` (`created_at`),
  CONSTRAINT `fk_anuncio_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`user_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_anuncio_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`category_id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- IMAGENS DO ANUNCIO
-- Estrutura compatível com os campos usados pelo ImagemAnuncioService:
-- id, url, ordem, anuncio, createdAt
-- =========================================================
CREATE TABLE IF NOT EXISTS `imagens_anuncio` (
  `imagem_id` INT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(500) NOT NULL,
  `ordem` INT NOT NULL,
  `anuncio_id` INT NOT NULL,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`imagem_id`),
  KEY `idx_imagens_anuncio_anuncio_id` (`anuncio_id`),
  KEY `idx_imagens_anuncio_ordem` (`anuncio_id`, `ordem`),
  CONSTRAINT `fk_imagens_anuncio_anuncio`
    FOREIGN KEY (`anuncio_id`)
    REFERENCES `anuncio` (`anuncio_id`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_unicode_ci;

-- =========================================================
-- DADOS INICIAIS
-- =========================================================

INSERT IGNORE INTO `roles` (`perfil_id`, `nome`)
VALUES
  (1, 'USER'),
  (2, 'ADMIN');

INSERT IGNORE INTO `category` (`name`)
VALUES
  ('Eletrônicos'),
  ('Livros'),
  ('Computação');

SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;

-- =========================================================
-- APÓS IMPORTAR
-- =========================================================
-- No primeiro deploy do Render:
-- SPRING_JPA_HIBERNATE_DDL_AUTO=update
--
-- Isso permite que o Hibernate:
-- - crie a tabela `solicitacao` com a estrutura exata da entidade atual;
-- - complete qualquer coluna/índice que tenha mudado no código;
-- - mantenha este script como base segura de produção.
