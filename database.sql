CREATE DATABASE  IF NOT EXISTS `student_information_system` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `student_information_system`;
-- MySQL dump 10.13  Distrib 5.7.30, for Linux (x86_64)
--
-- Host: localhost    Database: student_information_system
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `semester` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `year` tinyint(1) unsigned NOT NULL,
  `professor_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`course_id`),
  UNIQUE KEY `title_UNIQUE` (`title`),
  KEY `fk_course_professor_idx` (`professor_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'Algorithms and Data structures','II',2,1),(2,'Database systems','I',2,1),(3,'Artificial inteligence','I',3,1),(4,'Software testing and quality assurance','I',3,1),(5,'Internet technology and web services','II',3,1),(11,'Applied Calculus','I',1,2),(12,'Discrete Mathematics','II',1,2),(13,'Applied probability and statistics','I',3,2),(14,'Electric measurements and instrumentation','II',3,2),(15,'Sensor Systems','I',2,2),(20,'\nEconomy','I',1,3),(21,'\nMarketing in tourism and hospitality','I',2,3),(22,'\nHuman resource management','II',2,3),(23,'Tourist destination management','II',2,3),(24,'Practical research and business skills','II',4,3);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `professor`
--

DROP TABLE IF EXISTS `professor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `professor` (
  `professor_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(68) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`professor_id`),
  UNIQUE KEY `password_UNIQUE` (`password`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `professor`
--

LOCK TABLES `professor` WRITE;
/*!40000 ALTER TABLE `professor` DISABLE KEYS */;
INSERT INTO `professor` VALUES (1,'Marko','Markovic','marko.markovic@sis.com','{bcrypt}$2y$12$7NRKPNJ7f4PmRcs6VyHy/uqzjFm5Qmj/2TTNkkSsnOh0xtG32myCS'),(2,'Dusan','Dusanovic','dusan.dusanovic@sis.com','{bcrypt}$2y$12$nB91JxTAnXc9j.7w2Qelk.PEBXXjy.tgotu0Bt2EKJ3qlwoydHvoK'),(3,'Bojan','Bojanovic','bojan.bojanovic@sis.com','{bcrypt}$2y$12$NJcsDbhH4paRdsCWhd2ALORJUHJzkJsl1MxMEZx0Qt/8pljFKTZVC');
/*!40000 ALTER TABLE `professor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `index_number` int(10) NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `index_number_UNIQUE` (`index_number`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (28,'Ivana','Ivanovic',2020207451,'ivana.ivanovic@sis.com'),(29,'Jovan','Jovanovic',2020203623,'jovan.jovanovic@sis.com'),(30,'Dragan','Draganovic',2020253623,'dragan.draganovic@sis.com'),(31,'Marija','Marijanovic',2020204753,'marija.marijanovic@sis.com'),(32,'Stefan','Stefanovic',2020206345,'stefan.stefanovic@sis.com'),(33,'Marina','Marinovic',2020207423,'marina.marinovic@sis.com'),(34,'Lazar','Lazarovic',2020264235,'lazar.lazarovic@sis.com'),(35,'Katarina','Katarinovic',2020208242,'katarina.katarinovic@sis.com'),(36,'Tijana','Tijanovic',2020745235,'tijana.tijanovic@sis.com'),(37,'Filip','Filipovic',2020201645,'filip.filipovic@sis.com');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_course`
--

DROP TABLE IF EXISTS `student_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student_course` (
  `student_id` int(11) unsigned NOT NULL COMMENT '	',
  `course_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`student_id`,`course_id`),
  KEY `fk_student_course_course_id_idx` (`course_id`),
  CONSTRAINT `fk_student_course_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student_course_student_id` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_course`
--

LOCK TABLES `student_course` WRITE;
/*!40000 ALTER TABLE `student_course` DISABLE KEYS */;
INSERT INTO `student_course` VALUES (28,1),(28,2),(28,3),(28,4),(28,5),(28,11),(28,12),(28,13),(28,14),(28,15),(29,1),(29,2),(29,3),(29,4),(29,5),(29,11),(29,12),(29,13),(29,14),(29,15),(30,1),(30,2),(30,3),(30,4),(30,5),(30,11),(30,12),(30,13),(30,14),(30,15),(31,1),(31,2),(31,3),(31,4),(31,5),(31,11),(31,12),(31,13),(31,14),(31,15),(32,1),(32,2),(32,3),(32,4),(32,5),(32,11),(32,12),(32,13),(32,14),(32,15),(33,1),(33,2),(33,3),(33,4),(33,5),(33,11),(33,12),(33,13),(33,14),(33,15),(34,1),(34,2),(34,3),(34,4),(34,5),(34,11),(34,12),(34,13),(34,14),(34,15),(35,20),(35,21),(35,22),(35,23),(35,24),(36,20),(36,21),(36,22),(36,23),(36,24),(37,20),(37,21),(37,22),(37,23),(37,24);
/*!40000 ALTER TABLE `student_course` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-21 21:28:45
