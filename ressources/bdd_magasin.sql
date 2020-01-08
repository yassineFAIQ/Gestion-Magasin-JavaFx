-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Mer 08 Janvier 2020 à 10:13
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `bdd_magasin`
--
CREATE DATABASE IF NOT EXISTS `bdd_magasin` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bdd_magasin`;

-- --------------------------------------------------------

--
-- Structure de la table `categorie`
--

CREATE TABLE IF NOT EXISTS `categorie` (
  `id_cat` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(50) NOT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `categorie`
--

INSERT INTO `categorie` (`id_cat`, `libelle`) VALUES
(1, 'Télephone'),
(2, 'Ordinateur'),
(3, 'Imprimante'),
(5, 'Vetement'),
(6, 'Chaussure'),
(7, 'Casquette'),
(8, 'Veste'),
(9, 'Lunette'),
(10, 'Sandale'),
(11, 'T-shirt');

-- --------------------------------------------------------

--
-- Structure de la table `cheque`
--

CREATE TABLE IF NOT EXISTS `cheque` (
  `id_cheque` int(11) NOT NULL AUTO_INCREMENT,
  `titulaire_cheque` varchar(50) NOT NULL,
  PRIMARY KEY (`id_cheque`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=51 ;

--
-- Contenu de la table `cheque`
--

INSERT INTO `cheque` (`id_cheque`, `titulaire_cheque`) VALUES
(5, 'yassine faiq'),
(6, 'yassine'),
(7, 'faiq'),
(8, 'yassine'),
(9, 'yassine faiq'),
(10, 'aa'),
(11, 'hh'),
(12, 'olay'),
(13, 'yassineaa'),
(14, 'www'),
(15, 'qq'),
(16, 'vv'),
(17, 'aaa'),
(18, 'aaa'),
(19, 'aaa'),
(20, 'aaa'),
(21, 'yass'),
(22, 'hh'),
(23, 'hh'),
(24, 'hh'),
(25, 'nn'),
(26, 'mm'),
(27, 'll'),
(28, '55'),
(29, 'dzdz'),
(30, 'xx'),
(31, 'xx'),
(32, 'xx'),
(33, 'xx'),
(34, 'zaza'),
(35, 'mm'),
(36, 'bb'),
(37, 'ccc'),
(38, 'ww'),
(39, 'aaa'),
(40, 'bb'),
(41, 'bujb'),
(42, 'hh'),
(43, 'hh'),
(44, 'NULL'),
(45, 'sss'),
(46, 'sss'),
(47, 'sss'),
(48, 'bb'),
(49, 'bb'),
(50, 'hh');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `adresse` varchar(50) NOT NULL,
  `tel` int(11) NOT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`id_client`, `prenom`, `nom`, `email`, `adresse`, `tel`) VALUES
(1, 'test', '0', '5', '', 0),
(2, 'yassine', 'faiq', 'yassine@gmailcom', 'dkzdkzd555555555555n', 66);

-- --------------------------------------------------------

--
-- Structure de la table `lignedecommande`
--

CREATE TABLE IF NOT EXISTS `lignedecommande` (
  `id_ligne` int(11) NOT NULL AUTO_INCREMENT,
  `id_produit` int(11) NOT NULL,
  `id_vente` int(11) NOT NULL,
  `qte_ligne` int(11) NOT NULL,
  `sousTotal` double NOT NULL,
  PRIMARY KEY (`id_ligne`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=140 ;

--
-- Contenu de la table `lignedecommande`
--

INSERT INTO `lignedecommande` (`id_ligne`, `id_produit`, `id_vente`, `qte_ligne`, `sousTotal`) VALUES
(32, 89, 2, 1, 10),
(33, 84, 2, 1, 10),
(34, 89, 3, 1, 10),
(35, 84, 3, 1, 10),
(36, 85, 3, 1, 10),
(37, 91, 3, 56, 10),
(38, 89, 4, 1, 10),
(39, 84, 4, 1, 10),
(40, 84, 5, 1, 10),
(41, 84, 6, 1, 10),
(42, 85, 7, 1, 5),
(43, 91, 7, 1, 1),
(44, 84, 7, 1, 5),
(45, 89, 8, 1, 600),
(46, 84, 9, 1, 5),
(47, 85, 9, 1, 5),
(48, 84, 10, 1, 5),
(49, 85, 10, 1, 5),
(50, 84, 11, 1, 5),
(51, 85, 11, 1, 5),
(52, 85, 12, 1, 5),
(53, 89, 13, 1, 600),
(54, 89, 14, 1, 600),
(55, 89, 15, 5, 3000),
(56, 91, 15, 1, 1),
(57, 89, 16, 1, 600),
(58, 84, 16, 1, 5),
(59, 89, 17, 1, 600),
(60, 84, 17, 1, 5),
(61, 84, 18, 2, 10),
(62, 85, 18, 1, 5),
(63, 91, 18, 1, 1),
(64, 89, 19, 3, 1800),
(65, 85, 19, 1, 5),
(66, 89, 20, 1, 600),
(67, 84, 21, 2, 10),
(68, 84, 22, 2, 10),
(69, 84, 23, 1, 5),
(70, 85, 24, 1, 5),
(71, 84, 25, 1, 5),
(72, 84, 26, 1, 5),
(73, 85, 26, 1, 5),
(74, 84, 27, 1, 5),
(75, 85, 27, 1, 5),
(76, 84, 28, 1, 5),
(77, 85, 28, 1, 5),
(78, 85, 29, 1, 5),
(79, 89, 29, 1, 600),
(80, 84, 30, 1, 5),
(81, 84, 31, 1, 5),
(82, 85, 31, 1, 5),
(83, 84, 32, 1, 5),
(84, 85, 33, 1, 5),
(85, 85, 34, 2, 10),
(86, 84, 34, 1, 5),
(87, 85, 35, 3, 15),
(88, 84, 35, 1, 5),
(89, 89, 35, 1, 600),
(90, 84, 36, 1, 5),
(91, 85, 36, 1, 5),
(92, 84, 37, 1, 5),
(93, 85, 38, 1, 5),
(94, 84, 40, 1, 5),
(95, 84, 41, 1, 5),
(96, 89, 42, 1, 600),
(97, 85, 43, 1, 5),
(98, 89, 43, 4, 2400),
(99, 84, 44, 1, 5),
(100, 89, 46, 1, 600),
(101, 89, 47, 1, 600),
(102, 84, 48, 1, 5),
(103, 89, 49, 1, 600),
(104, 85, 50, 1, 5),
(105, 85, 51, 1, 5),
(106, 89, 52, 1, 600),
(107, 91, 52, 1, 1),
(108, 89, 53, 1, 600),
(109, 85, 53, 1, 5),
(110, 89, 54, 1, 600),
(111, 84, 56, 6, 30),
(112, 89, 57, 1, 600),
(113, 89, 58, 1, 600),
(114, 89, 59, 1, 600),
(115, 89, 60, 1, 600),
(116, 85, 61, 1, 5),
(117, 89, 62, 1, 600),
(118, 89, 63, 40, 24000),
(119, 89, 64, 120, 72000),
(120, 89, 65, 2, 1200),
(121, 89, 66, 1, 600),
(122, 89, 67, 1, 600),
(123, 89, 68, 1, 600),
(124, 89, 69, 1, 600),
(125, 89, 70, 1, 600),
(126, 89, 71, 1, 600),
(127, 89, 72, 1, 600),
(128, 89, 73, 2, 1200),
(129, 89, 74, 1, 600),
(130, 89, 75, 1, 600),
(131, 89, 76, 1, 600),
(132, 91, 76, 1, 1),
(133, 89, 77, 1, 600),
(134, 89, 78, 1, 600),
(135, 89, 79, 1, 600),
(136, 84, 80, 1, 5),
(137, 89, 80, 1, 600),
(138, 85, 81, 1, 5),
(139, 85, 82, 2, 10);

-- --------------------------------------------------------

--
-- Structure de la table `paiement`
--

CREATE TABLE IF NOT EXISTS `paiement` (
  `id_paiement` int(11) NOT NULL AUTO_INCREMENT,
  `montant_paiement` double NOT NULL,
  `type_paiement` varchar(30) NOT NULL,
  `date_paiement` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id_vente` int(11) NOT NULL,
  `id_cheque` int(11) DEFAULT '0',
  `etat` varchar(30) NOT NULL,
  PRIMARY KEY (`id_paiement`),
  KEY `fk_paiement1` (`id_vente`),
  KEY `fk_paiement2` (`id_cheque`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=148 ;

--
-- Contenu de la table `paiement`
--

INSERT INTO `paiement` (`id_paiement`, `montant_paiement`, `type_paiement`, `date_paiement`, `id_vente`, `id_cheque`, `etat`) VALUES
(36, 0, 'Espece', '2020-01-03 17:16:46', 53, NULL, ''),
(45, 120, 'Cheque', '2020-01-03 17:40:49', 54, 9, ''),
(48, 0, 'Espece', '2020-01-03 17:53:20', 2, NULL, ''),
(56, 120, 'Cheque', '2020-01-03 18:25:53', 56, 11, ''),
(57, 120, 'cheque', '2020-01-03 18:35:41', 2, 6, ''),
(60, 11, 'Cheque', '2020-01-03 18:39:33', 2, 5, ''),
(61, 200, 'Cheque', '2020-01-03 18:45:35', 2, 12, ''),
(62, 0, 'Espece', '2020-01-03 18:47:00', 55, NULL, ''),
(63, 140, 'Cheque', '2020-01-03 18:47:32', 6, 13, ''),
(64, 0, 'Espece', '2020-01-03 18:47:40', 6, NULL, ''),
(65, 52, 'Cheque', '2020-01-03 19:09:54', 2, 14, ''),
(66, 600, 'Espece', '2020-01-03 19:35:49', 57, NULL, ''),
(67, 600, 'Espece', '2020-01-03 19:36:26', 58, NULL, ''),
(68, 100, 'Cheque', '2020-01-03 19:36:26', 58, 15, ''),
(69, 100, 'Cheque', '2020-01-03 19:41:18', 58, 16, ''),
(70, 100, 'Cheque', '2020-01-03 19:43:56', 58, 17, ''),
(71, 200, 'Cheque', '2020-01-03 19:44:02', 58, 18, ''),
(72, 100, 'Cheque', '2020-01-03 19:44:09', 58, 19, ''),
(73, 1, 'Cheque', '2020-01-03 19:44:13', 58, 20, ''),
(74, 120, 'Cheque', '2020-01-03 20:03:15', 59, 21, ''),
(75, 1200, 'En ligne', '2020-01-03 20:59:07', 60, NULL, ''),
(76, 90, 'Cheque', '2020-01-03 22:01:34', 29, 22, ''),
(77, 1325, 'Cheque', '2020-01-03 22:02:17', 43, 23, ''),
(78, 1080, 'Cheque', '2020-01-03 22:02:25', 43, 24, ''),
(79, 125, 'Cheque', '2020-01-03 22:03:14', 63, 25, ''),
(80, 1400, 'Cheque', '2020-01-03 22:04:55', 64, 26, ''),
(81, 1000, 'Cheque', '2020-01-03 22:07:22', 65, 27, ''),
(82, 400, 'Cheque', '2020-01-03 22:08:33', 66, 28, ''),
(83, 600, 'Espece', '2020-01-04 01:32:57', 67, NULL, ''),
(84, 600, 'Espece', '2020-01-04 01:33:34', 68, NULL, ''),
(85, 120, 'Cheque', '2020-01-04 11:19:14', 66, 29, ''),
(86, 100, 'Cheque', '2020-01-04 11:24:18', 65, 30, ''),
(87, 50, 'Cheque', '2020-01-04 11:24:29', 65, 31, ''),
(88, 50, 'Cheque', '2020-01-04 11:25:09', 65, 32, ''),
(89, 80, 'Cheque', '2020-01-04 11:25:53', 66, 33, ''),
(90, 10, 'Cheque', '2020-01-04 11:27:18', 26, 34, ''),
(91, 605, 'Cheque', '2020-01-04 11:30:33', 53, 35, ''),
(92, 70600, 'Cheque', '2020-01-04 11:32:59', 64, 36, ''),
(93, 600, 'Cheque', '2020-01-04 11:35:18', 67, 37, ''),
(94, 600, 'Cheque', '2020-01-04 11:36:23', 68, 38, ''),
(95, 600, 'Espece', '2020-01-04 19:57:02', 69, NULL, ''),
(96, 100, 'Cheque', '2020-01-04 20:02:59', 70, 39, ''),
(97, 120, 'Cheque', '2020-01-04 20:04:25', 71, 40, ''),
(98, 600, 'En ligne', '2020-01-04 20:12:59', 62, NULL, ''),
(99, 600, 'En ligne', '2020-01-04 20:13:22', 62, NULL, ''),
(100, 600, 'En ligne', '2020-01-04 20:14:54', 67, NULL, ''),
(101, 700, 'Traites', '2020-01-04 20:17:19', 49, NULL, ''),
(102, 600, 'Traites', '2020-01-04 20:17:30', 49, NULL, ''),
(103, 120, 'Traites', '2020-01-04 20:20:33', 71, NULL, ''),
(104, 120, 'Traites', '2020-01-04 20:20:43', 71, NULL, ''),
(105, 120, 'Cheque', '2020-01-04 20:22:42', 71, 41, ''),
(106, 20, 'Cheque', '2020-01-04 20:22:55', 71, 42, ''),
(107, 120, 'Traites', '2020-01-04 20:23:10', 71, NULL, ''),
(108, 120, 'Traites', '2020-01-04 20:29:53', 20, NULL, ''),
(109, 120, 'Cheque', '2020-01-04 20:31:54', 49, 43, ''),
(110, 120, 'Traites', '2020-01-04 20:32:07', 49, NULL, ''),
(111, 120, 'Traites', '2020-01-04 20:40:09', 35, NULL, ''),
(112, 120, 'Traites', '2020-01-04 21:03:00', 55, NULL, ''),
(115, 120, 'Traites', '2020-01-04 23:47:11', 20, NULL, ''),
(116, 360, 'Traites', '2020-01-04 23:47:26', 20, NULL, ''),
(117, 2000, 'Traites', '2020-01-04 23:48:11', 19, NULL, ''),
(118, 5, 'Traites', '2020-01-05 00:10:00', 51, NULL, ''),
(119, 10, 'Traites', '2020-01-05 00:10:28', 31, NULL, ''),
(120, 4, 'Cheque', '2020-01-05 00:11:51', 24, 45, ''),
(121, 0.5, 'Cheque', '2020-01-05 00:12:02', 24, 46, ''),
(122, 0.5, 'Cheque', '2020-01-05 00:12:09', 24, 47, ''),
(123, 5, 'En ligne', '2020-01-05 00:18:46', 25, NULL, ''),
(124, 100, 'Traites', '2020-01-05 00:23:37', 72, NULL, ''),
(125, 100, 'Traites', '2020-01-05 00:24:09', 73, NULL, ''),
(126, 200, 'Traites', '2020-01-05 00:24:09', 73, NULL, ''),
(127, 600, 'Espece', '2020-01-05 00:54:53', 74, NULL, 'payé'),
(128, 12, 'Traites', '2020-01-05 00:57:14', 54, NULL, 'en cours'),
(129, 15, 'Traites', '2020-01-05 00:57:23', 54, NULL, 'en cours'),
(130, 453, 'Traites', '2020-01-05 00:57:30', 54, NULL, 'payé'),
(131, 12, 'Cheque', '2020-01-05 01:06:02', 34, 48, 'en cours'),
(132, 3, 'Cheque', '2020-01-05 01:06:12', 34, 49, 'payé'),
(133, 1500, 'Cheque', '2020-01-05 01:06:47', 73, 50, 'payé'),
(134, 600, 'En ligne', '2020-01-05 01:31:05', 75, NULL, 'payé'),
(135, 5, 'En ligne', '2020-01-05 01:31:56', 23, NULL, 'payé'),
(136, 601, 'En ligne', '2020-01-05 01:34:46', 76, NULL, 'payé'),
(137, 600, 'Espece', '2020-01-05 01:38:47', 77, NULL, 'payé'),
(138, 600, 'Espece', '2020-01-05 01:39:22', 78, NULL, 'payé'),
(139, 100, 'Traites', '2020-01-05 01:39:22', 78, NULL, 'en cours'),
(140, 100, 'Traites', '2020-01-05 01:45:16', 79, NULL, 'en cours'),
(141, 400, 'Traites', '2020-01-05 01:46:01', 79, NULL, 'en cours'),
(142, 100, 'Traites', '2020-01-05 01:46:09', 79, NULL, 'payé'),
(143, 120, 'Traites', '2020-01-06 07:50:13', 80, NULL, 'en cours'),
(144, 220, 'Traites', '2020-01-06 07:50:32', 80, NULL, 'en cours'),
(145, 265, 'Traites', '2020-01-06 07:50:37', 80, NULL, 'payé'),
(146, 5, 'Espece', '2020-01-07 16:15:58', 81, NULL, 'payé'),
(147, 10, 'Espece', '2020-01-07 16:16:40', 82, NULL, 'payé');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `id_produit` int(11) NOT NULL AUTO_INCREMENT,
  `designation` varchar(50) NOT NULL,
  `qte` int(11) NOT NULL,
  `prix_achat` int(11) NOT NULL,
  `prix_vente` int(11) NOT NULL,
  `id_cat` int(11) NOT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `fk_produit_categorie` (`id_cat`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=93 ;

--
-- Contenu de la table `produit`
--

INSERT INTO `produit` (`id_produit`, `designation`, `qte`, `prix_achat`, `prix_vente`, `id_cat`) VALUES
(84, 'yass44', 44444, 5, 5, 3),
(85, 'ckcj', 55, 5, 5, 3),
(89, 'ff', 55, 55, 600, 9),
(91, 'test', 1, 1, 1, 8);

-- --------------------------------------------------------

--
-- Structure de la table `vente`
--

CREATE TABLE IF NOT EXISTS `vente` (
  `id_vente` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) NOT NULL,
  `total` double NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_vente`),
  KEY `fk_vente_client` (`id_client`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=83 ;

--
-- Contenu de la table `vente`
--

INSERT INTO `vente` (`id_vente`, `id_client`, `total`, `date`) VALUES
(2, 1, 0, '2002-12-11 23:00:00'),
(3, 2, 0, '2002-12-11 23:00:00'),
(4, 2, 0, '2002-12-11 23:00:00'),
(5, 2, 0, '2019-12-21 23:00:00'),
(6, 1, 0, '2019-12-22 15:17:33'),
(7, 2, 0, '2019-12-22 22:30:38'),
(8, 2, 0, '2019-12-22 22:35:36'),
(9, 2, 0, '2019-12-22 22:38:19'),
(10, 2, 0, '2019-12-22 22:38:55'),
(11, 2, 0, '2019-12-22 22:38:55'),
(12, 2, 0, '2019-12-22 22:50:37'),
(13, 2, 0, '2019-12-22 22:53:09'),
(14, 2, 0, '2019-12-22 22:54:23'),
(15, 2, 0, '2019-12-22 22:55:18'),
(16, 2, 0, '2019-12-22 23:09:18'),
(17, 2, 605, '2019-12-22 23:11:45'),
(18, 1, 16, '2019-12-22 23:12:34'),
(19, 2, 1805, '2019-12-24 16:35:49'),
(20, 2, 600, '2019-12-24 20:48:23'),
(21, 2, 10, '2020-01-01 15:34:10'),
(22, 2, 10, '2020-01-01 15:36:54'),
(23, 2, 5, '2020-01-01 15:43:18'),
(24, 2, 5, '2020-01-01 15:44:54'),
(25, 2, 5, '2020-01-01 16:07:23'),
(26, 2, 10, '2020-01-01 16:20:03'),
(27, 2, 10, '2020-01-01 16:21:54'),
(28, 2, 10, '2020-01-01 16:36:05'),
(29, 2, 605, '2020-01-01 16:38:41'),
(30, 2, 5, '2020-01-01 16:41:02'),
(31, 2, 10, '2020-01-01 16:42:31'),
(32, 2, 5, '2020-01-01 16:45:16'),
(33, 2, 5, '2020-01-01 16:45:59'),
(34, 1, 15, '2020-01-01 16:47:28'),
(35, 2, 620, '2020-01-01 16:49:47'),
(36, 2, 10, '2020-01-01 16:52:36'),
(37, 2, 5, '2020-01-01 16:53:23'),
(38, 2, 5, '2020-01-01 16:56:10'),
(40, 2, 5, '2020-01-01 17:22:04'),
(41, 2, 5, '2020-01-01 17:25:17'),
(42, 2, 600, '2020-01-02 19:38:49'),
(43, 2, 2405, '2020-01-02 19:43:52'),
(44, 2, 5, '2020-01-02 19:44:19'),
(45, 2, 0, '2020-01-02 19:45:40'),
(46, 2, 600, '2020-01-03 09:57:14'),
(47, 2, 600, '2020-01-03 09:58:15'),
(48, 2, 5, '2020-01-03 10:05:24'),
(49, 1, 600, '2020-01-03 11:11:10'),
(50, 2, 5, '2020-01-03 11:17:20'),
(51, 1, 5, '2020-01-03 11:17:44'),
(52, 2, 601, '2020-01-03 16:10:20'),
(53, 2, 605, '2020-01-03 16:11:02'),
(54, 2, 600, '2020-01-03 17:40:48'),
(55, 2, 120, '2020-01-03 18:23:50'),
(56, 2, 30, '2020-01-03 18:25:52'),
(57, 2, 600, '2020-01-03 19:35:48'),
(58, 2, 600, '2020-01-03 19:36:24'),
(59, 2, 600, '2020-01-03 20:03:15'),
(60, 2, 1200, '2020-01-03 20:59:07'),
(61, 2, 5, '2020-01-03 21:36:14'),
(62, 2, 600, '2020-01-03 21:37:33'),
(63, 2, 24000, '2020-01-03 22:03:14'),
(64, 2, 72000, '2020-01-03 22:04:53'),
(65, 2, 1200, '2020-01-03 22:07:22'),
(66, 2, 600, '2020-01-03 22:08:33'),
(67, 2, 600, '2020-01-04 01:32:57'),
(68, 2, 600, '2020-01-04 01:33:34'),
(69, 2, 600, '2020-01-04 19:57:01'),
(70, 2, 600, '2020-01-04 20:02:59'),
(71, 2, 600, '2020-01-04 20:04:23'),
(72, 2, 600, '2020-01-05 00:23:37'),
(73, 2, 1800, '2020-01-05 00:24:09'),
(74, 2, 600, '2020-01-05 00:54:53'),
(75, 2, 600, '2020-01-05 01:31:05'),
(76, 2, 601, '2020-01-05 01:34:46'),
(77, 2, 600, '2020-01-05 01:38:47'),
(78, 2, 1200, '2020-01-05 01:39:22'),
(79, 2, 600, '2020-01-05 01:45:16'),
(80, 2, 605, '2020-01-06 07:50:12'),
(81, 2, 5, '2020-01-07 16:15:58'),
(82, 2, 10, '2020-01-07 16:16:40');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `paiement`
--
ALTER TABLE `paiement`
  ADD CONSTRAINT `fk_paiement1` FOREIGN KEY (`id_vente`) REFERENCES `vente` (`id_vente`),
  ADD CONSTRAINT `fk_paiement2` FOREIGN KEY (`id_cheque`) REFERENCES `cheque` (`id_cheque`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fk_produit_categorie` FOREIGN KEY (`id_cat`) REFERENCES `categorie` (`id_cat`);

--
-- Contraintes pour la table `vente`
--
ALTER TABLE `vente`
  ADD CONSTRAINT `fk_vente_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id_client`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
