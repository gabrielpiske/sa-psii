create schema forndb;
use forndb;

CREATE TABLE `fornecedor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contato` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `empresa` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `telefone` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `categoria` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `data_validade` date DEFAULT NULL,
  `descricao` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `fornecedor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo6c1dbi17sempey5dpnx6ovrj` (`fornecedor_id`),
  CONSTRAINT `FKo6c1dbi17sempey5dpnx6ovrj` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`)
);

CREATE TABLE `pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `status` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `valor_total` double DEFAULT NULL,
  `fornecedor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrq0u523hlb8rkqplxjidu0jv9` (`fornecedor_id`),
  CONSTRAINT `FKrq0u523hlb8rkqplxjidu0jv9` FOREIGN KEY (`fornecedor_id`) REFERENCES `fornecedor` (`id`)
);

CREATE TABLE `pedido_produto` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantidade` int DEFAULT NULL,
  `pedido_id` bigint DEFAULT NULL,
  `produto_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcsbxw0y9i3wfmiupq9eqfpdtc` (`pedido_id`),
  KEY `FKf8l3k06bmjhdwd79t0ndcw7tt` (`produto_id`),
  CONSTRAINT `FKcsbxw0y9i3wfmiupq9eqfpdtc` FOREIGN KEY (`pedido_id`) REFERENCES `pedido` (`id`),
  CONSTRAINT `FKf8l3k06bmjhdwd79t0ndcw7tt` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
);
