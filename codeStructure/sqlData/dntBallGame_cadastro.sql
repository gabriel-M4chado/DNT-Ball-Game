-- MySQL dump 10.13  Distrib 8.0.32, for Linux (x86_64)
--
-- Host: localhost    Database: dntBallGame
-- ------------------------------------------------------
-- Server version	8.0.33-0ubuntu0.20.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cadastro`
--

DROP TABLE IF EXISTS `cadastro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cadastro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(60) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `email` varchar(80) COLLATE utf8mb4_general_ci NOT NULL,
  `sexo` enum('M','F') COLLATE utf8mb4_general_ci DEFAULT NULL,
  `complemento` varchar(200) COLLATE utf8mb4_general_ci NOT NULL,
  `isAdm` tinyint(1) DEFAULT '0',
  `idEndereco` int DEFAULT NULL,
  `idJogador` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  KEY `idEndereco` (`idEndereco`),
  KEY `fk_cadastro_player` (`idJogador`),
  CONSTRAINT `cadastro_ibfk_1` FOREIGN KEY (`idEndereco`) REFERENCES `endereco` (`id`),
  CONSTRAINT `cadastro_ibfk_2` FOREIGN KEY (`idJogador`) REFERENCES `playerScore` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cadastro`
--

LOCK TABLES `cadastro` WRITE;
/*!40000 ALTER TABLE `cadastro` DISABLE KEYS */;
INSERT INTO `cadastro` VALUES (1,'Gabriel','gabriel.machado.js@gmail.com.br','M','-',1,23,1),(14,'Leo','testeExclusao@mail.com.br','F','-',1,18,10),(21,'Humberto','h@h.com.br','M','Central',0,18,36),(22,'teste 2 ','ria@XX,com','M','teste casa ria',0,6,41),(23,'teste 3','teste@gmail.com','M','teste 23 rua',0,4,42),(25,'Teste t2','t2@gmail.com','M','teste ruia T2',0,7,44);
/*!40000 ALTER TABLE `cadastro` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-28 22:32:36
