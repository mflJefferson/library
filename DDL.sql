create schema library;

use library;

create user 'user'@'localhost' identified by 'pass123';

-- MySQL dump 10.13  Distrib 8.0.23, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: library
-- ------------------------------------------------------
-- Server version	8.0.23-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authors` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `name` varchar(255) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'George Orwell'),(2,'Markus Zusak'),(3,'Madeleine Engle'),(4,'Victoria Aveyard'),(5,'Beth O’Leary'),(6,'George R. R. Martin');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `author_id` bigint DEFAULT NULL,
                         `publisher_id` bigint DEFAULT NULL,
                         `title` varchar(255) NOT NULL,
                         `isbn` varchar(64) NOT NULL,
                         `description` text NOT NULL,
                         `cover` varchar(255) DEFAULT NULL,
                         `created_at` datetime NOT NULL,
                         `updated_at` datetime DEFAULT NULL,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `books_id_uindex` (`id`),
                         KEY `author` (`author_id`),
                         KEY `publisher` (`publisher_id`),
                         CONSTRAINT `author` FOREIGN KEY (`author_id`) REFERENCES `authors` (`id`) ON UPDATE CASCADE,
                         CONSTRAINT `publisher` FOREIGN KEY (`publisher_id`) REFERENCES `publishers` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,1,1,'Animal Farm','978-0451526342','A farm is taken over by its overworked, mistreated animals. With flaming idealism and stirring slogans, they set out to create a paradise of progress, justice, and equality. Thus the stage is set for one of most telling satiric fables ever penned - a -razor-edged fairy tale for grown-ups that records the evolution against tyranny to a totalitarianism just as terrible.',NULL,'2021-03-14 01:40:30',NULL),(2,1,2,'1984','978-1328869333','In 1984, London is a grim city in the totalitarian state of Oceania where Big Brother is always watching you and the Thought Police can practically read your mind. Winston Smith is a man in grave danger for the simple reason that his memory still functions. Drawn into a forbidden love affair, Winston finds the courage to join a secret revolutionary organization called The Brotherhood, dedicated to the destruction of the Party. Together with his beloved Julia, he hazards his life in a deadly match against the powers that be.',NULL,'2021-03-14 01:40:30',NULL),(8,2,3,'A Menina Que Roubava Livros','978-8598078175','A trajetória de Liesel Meminger é contada por uma narradora mórbida, surpreendentemente simpática.',NULL,'2021-03-14 01:40:30',NULL),(9,3,3,'Uma dobra no tempo','978-8595081758','Um clássico da fantasia e da ficção científica emerge!',NULL,'2021-03-14 01:40:30',NULL),(11,4,4,'A rainha vermelha','978-8565765695','O mundo de Mare Barrow é dividido pelo sangue: vermelho ou prateado.',NULL,'2021-03-14 01:40:30',NULL),(12,5,3,'Teto Para Dois','978-8551005415','Eles dividem um apartamento com uma cama só.',NULL,'2021-03-14 01:40:30',NULL),(16,6,5,'A Guerra dos Tronos : As Crônicas de Gelo e Fogo, volume 1','978-8556510785','A guerra dos tronos é o primeiro livro da série best-seller internacional As Crônicas de Gelo e Fogo',NULL,'2021-03-14 01:40:30',NULL),(17,6,5,'A fúria dos reis: As Crônicas de Gelo e Fogo, volume 2','978-8556510792','Nesta emocionante sequência de A guerra dos tronos',NULL,'2021-03-14 01:40:30',NULL),(18,6,5,'A tormenta de espadas: As Crônicas de Gelo e Fogo, volume 3','978-8556510808','Este é o terceiro volume da série As Crônicas de Gelo e Fogo',NULL,'2021-03-14 01:40:30',NULL),(19,6,5,'O Festim dos Corvos: As Crônicas de Gelo e Fogo, volume 4','978-8556510884','O festim dos corvos é o quarto livro da monumental saga de fantasia As Crônicas de Gelo',NULL,'2021-03-14 01:40:30',NULL),(20,6,5,'A dança dos dragões: As Crônicas de Gelo e Fogo, volume 5','978-8556510884','A dança dos dragões é o quinto livro da saga As Crônicas de Gelo e Fogo',NULL,'2021-03-14 01:40:30',NULL),(21,6,6,'Sonho febril','978-8544101391','Uma reinvenção original e fascinante das histórias de vampiros pelas mãos do mestre da fantasia moderna George R.R. Martin',NULL,'2021-03-14 01:40:30',NULL);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publishers`
--

DROP TABLE IF EXISTS `publishers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publishers` (
                              `id` bigint NOT NULL AUTO_INCREMENT,
                              `name` varchar(255) NOT NULL,
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publishers`
--

LOCK TABLES `publishers` WRITE;
/*!40000 ALTER TABLE `publishers` DISABLE KEYS */;
INSERT INTO `publishers` VALUES (1,'Signet Book'),(2,'Houghton Mifflin Harcourt'),(3,'Intriseca'),(4,'Editora Seguinte'),(5,'Suma'),(6,'Leya');
/*!40000 ALTER TABLE `publishers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-21 22:38:43
