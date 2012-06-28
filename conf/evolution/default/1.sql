-- MySQL dump 10.13  Distrib 5.5.23, for Linux (x86_64)
--
-- Host: localhost    Database: willy
-- ------------------------------------------------------
-- Server version	5.5.23

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
-- Table structure for table `employment_record`
--

DROP TABLE IF EXISTS `employment_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employment_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `person_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `emp_person_fk` (`person_id`),
  CONSTRAINT `emp_person_fk` FOREIGN KEY (`person_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='records of previous employment';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employment_record`
--

LOCK TABLES `employment_record` WRITE;
/*!40000 ALTER TABLE `employment_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `employment_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `managers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `current` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `managers_person_fk` (`person_id`),
  KEY `managers_project_fk` (`project_id`),
  CONSTRAINT `managers_person_fk` FOREIGN KEY (`person_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `managers_project_fk` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='project managers';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managers`
--

LOCK TABLES `managers` WRITE;
/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people`
--

DROP TABLE IF EXISTS `people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `designation` varchar(45) DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  `experience` decimal(3,1) DEFAULT NULL,
  `available` tinyint(1) NOT NULL,
  `location` varchar(45) DEFAULT NULL,
  `employee` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Records of all employees	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people`
--

LOCK TABLES `people` WRITE;
/*!40000 ALTER TABLE `people` DISABLE KEYS */;
/*!40000 ALTER TABLE `people` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people_managers`
--

DROP TABLE IF EXISTS `people_managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people_managers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `manager_id` int(11) NOT NULL,
  `current` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `people_managers_fk1` (`person_id`),
  KEY `people_managers_fk2` (`manager_id`),
  CONSTRAINT `people_managers_fk1` FOREIGN KEY (`person_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `people_managers_fk2` FOREIGN KEY (`manager_id`) REFERENCES `managers` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='person to manager mapping';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people_managers`
--

LOCK TABLES `people_managers` WRITE;
/*!40000 ALTER TABLE `people_managers` DISABLE KEYS */;
/*!40000 ALTER TABLE `people_managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people_project`
--

DROP TABLE IF EXISTS `people_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people_project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `current` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `people_project_fk1` (`person_id`),
  KEY `people_project_fk2` (`project_id`),
  CONSTRAINT `people_project_fk1` FOREIGN KEY (`person_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `people_project_fk2` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people_project`
--

LOCK TABLES `people_project` WRITE;
/*!40000 ALTER TABLE `people_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `people_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `people_skills`
--

DROP TABLE IF EXISTS `people_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `people_skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `level` enum('NOVICE','INTERMEDIATE','EXPERT') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `person_skill_fk1` (`person_id`),
  KEY `person_skill_fk2` (`skill_id`),
  CONSTRAINT `person_skill_fk1` FOREIGN KEY (`person_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `person_skill_fk2` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='mapping of people to their skills';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `people_skills`
--

LOCK TABLES `people_skills` WRITE;
/*!40000 ALTER TABLE `people_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `people_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `projects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text NOT NULL,
  `status` enum('ACTIVE','PIPELINE','CLOSED') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='projects table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualifications_records`
--

DROP TABLE IF EXISTS `qualifications_records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qualifications_records` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) NOT NULL,
  `qualification` varchar(45) NOT NULL,
  `institute` varchar(45) NOT NULL,
  `started` year(4) NOT NULL,
  `completed` year(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `qualifications_records_fk` (`person_id`),
  CONSTRAINT `qualifications_records_fk` FOREIGN KEY (`person_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualifications_records`
--

LOCK TABLES `qualifications_records` WRITE;
/*!40000 ALTER TABLE `qualifications_records` DISABLE KEYS */;
/*!40000 ALTER TABLE `qualifications_records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requirements`
--

DROP TABLE IF EXISTS `requirements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requirements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `project_id` int(11) NOT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `fill_by` date DEFAULT NULL,
  `priority` enum('LOW','NORMAL','CRUCIAL') DEFAULT NULL,
  `category` enum('BILLED','SHADOW','PITCH','TENTATIVE') DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `requirements_project_fk` (`project_id`),
  KEY `requirements_person_fk` (`contact_id`),
  CONSTRAINT `requirements_person_fk` FOREIGN KEY (`contact_id`) REFERENCES `people` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `requirements_project_fk` FOREIGN KEY (`project_id`) REFERENCES `projects` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='project requirements';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirements`
--

LOCK TABLES `requirements` WRITE;
/*!40000 ALTER TABLE `requirements` DISABLE KEYS */;
/*!40000 ALTER TABLE `requirements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requirements_skills`
--

DROP TABLE IF EXISTS `requirements_skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `requirements_skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `requirement_id` int(11) NOT NULL,
  `skill_id` int(11) NOT NULL,
  `skill_level` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `requirements_skills_fk1` (`requirement_id`),
  KEY `requirements_skills_fk2` (`skill_id`),
  CONSTRAINT `requirements_skills_fk1` FOREIGN KEY (`requirement_id`) REFERENCES `requirements` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `requirements_skills_fk2` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='maps requirements to skills';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requirements_skills`
--

LOCK TABLES `requirements_skills` WRITE;
/*!40000 ALTER TABLE `requirements_skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `requirements_skills` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `skills`
--

DROP TABLE IF EXISTS `skills`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `skills` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skill_name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `skill_name_UNIQUE` (`skill_name`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Skills master table';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `skills`
--

LOCK TABLES `skills` WRITE;
/*!40000 ALTER TABLE `skills` DISABLE KEYS */;
/*!40000 ALTER TABLE `skills` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-09  2:13:56