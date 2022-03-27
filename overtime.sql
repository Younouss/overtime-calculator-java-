-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 28 Janvier 2020 à 12:39
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `overtime`
--

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `registration_number` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `function` varchar(50) NOT NULL,
  `edited` int(2) NOT NULL,
  `status` int(2) NOT NULL,
  `date_created` date NOT NULL,
  `user_date_created` varchar(50) NOT NULL,
  `date_modified` date NOT NULL,
  `user_date_modified` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `holidays`
--

CREATE TABLE IF NOT EXISTS `holidays` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `holiday_date` date NOT NULL,
  `status` int(2) NOT NULL,
  `date_created` date NOT NULL,
  `user_date_created` varchar(50) NOT NULL,
  `date_modified` date NOT NULL,
  `user_date_modified` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

-- --------------------------------------------------------

--
-- Structure de la table `input`
--

CREATE TABLE IF NOT EXISTS `input` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `beginning_hour` time NOT NULL,
  `end_hour` time NOT NULL,
  `beginning_date` date NOT NULL,
  `end_date` date NOT NULL,
  `total_time` int(10) DEFAULT NULL,
  `total_overtime` int(10) DEFAULT NULL,
  `comment` varchar(50) DEFAULT NULL,
  `status` int(2) NOT NULL,
  `date_created` date NOT NULL,
  `user_date_created` varchar(50) NOT NULL,
  `date_modified` date NOT NULL,
  `user_date_modified` varchar(50) NOT NULL,
  `ID_employee` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_employee2` (`ID_employee`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=36 ;

-- --------------------------------------------------------

--
-- Structure de la table `overtime`
--

CREATE TABLE IF NOT EXISTS `overtime` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fifteenpercent` int(20) NOT NULL,
  `fiftypercent` int(20) NOT NULL,
  `seventyfivepercent` int(20) NOT NULL,
  `hundredpercent` int(20) NOT NULL,
  `beginning_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` int(2) NOT NULL,
  `date_created` date NOT NULL,
  `user_date_created` varchar(50) NOT NULL,
  `date_modified` date NOT NULL,
  `user_date_modified` varchar(50) NOT NULL,
  `ID_employee` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `fk_employee1` (`ID_employee`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `ID` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `status` int(2) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `user`
--

INSERT INTO `user` (`ID`, `username`, `password`, `status`) VALUES
(1, 'utilisateur1', 'optimum2019', 1),
(2, 'younouss', 'optimum2019', 1),
(3, 'koua ephrem', 'testephrem', 1),
(4, 'kouakou christian', 'back', 1),
(6, 'kongo honore', 'compta20', 1),
(7, 'mel kassi', 'info2000', 1);

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `input`
--
ALTER TABLE `input`
  ADD CONSTRAINT `fk_employee2` FOREIGN KEY (`ID_employee`) REFERENCES `employee` (`ID`) ON UPDATE CASCADE;

--
-- Contraintes pour la table `overtime`
--
ALTER TABLE `overtime`
  ADD CONSTRAINT `fk_employee1` FOREIGN KEY (`ID_employee`) REFERENCES `employee` (`ID`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
