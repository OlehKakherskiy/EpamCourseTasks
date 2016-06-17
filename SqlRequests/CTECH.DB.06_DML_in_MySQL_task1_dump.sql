-- phpMyAdmin SQL Dump
-- version 3.5.2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 03, 2012 at 01:13 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `task_site`
--

-- --------------------------------------------------------

--
-- Table structure for table `banners`
--

CREATE TABLE IF NOT EXISTS `banners` (
  `b_id` int(11) NOT NULL AUTO_INCREMENT,
  `b_url` varchar(255) NOT NULL,
  `b_show` int(11) NOT NULL DEFAULT '0',
  `b_click` int(11) NOT NULL DEFAULT '0',
  `b_text` varchar(255) DEFAULT NULL,
  `b_pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`b_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `m2m_banners_pages`
--

CREATE TABLE IF NOT EXISTS `m2m_banners_pages` (
  `b_id` int(11) NOT NULL,
  `p_id` int(11) NOT NULL,
  PRIMARY KEY (`b_id`,`p_id`),
  KEY `p_id` (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `news`
--

CREATE TABLE IF NOT EXISTS `news` (
  `n_id` int(11) NOT NULL AUTO_INCREMENT,
  `n_category` int(11) NOT NULL,
  `n_header` varchar(255) NOT NULL,
  `n_text` text NOT NULL,
  `n_dt` datetime NOT NULL,
  PRIMARY KEY (`n_id`),
  KEY `n_category` (`n_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `news_categories`
--

CREATE TABLE IF NOT EXISTS `news_categories` (
  `nc_id` int(11) NOT NULL AUTO_INCREMENT,
  `nc_name` varchar(255) NOT NULL,
  PRIMARY KEY (`nc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `pages`
--

CREATE TABLE IF NOT EXISTS `pages` (
  `p_id` int(11) NOT NULL AUTO_INCREMENT,
  `p_parent` int(11) DEFAULT NULL,
  `p_name` varchar(255) NOT NULL,
  PRIMARY KEY (`p_id`),
  KEY `p_parent` (`p_parent`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE IF NOT EXISTS `reviews` (
  `r_id` int(11) NOT NULL AUTO_INCREMENT,
  `r_category` int(11) NOT NULL,
  `r_header` varchar(255) NOT NULL,
  `r_text` text NOT NULL,
  `r_dt` datetime NOT NULL,
  PRIMARY KEY (`r_id`),
  KEY `r_category` (`r_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `reviews_categories`
--

CREATE TABLE IF NOT EXISTS `reviews_categories` (
  `rc_id` int(11) NOT NULL AUTO_INCREMENT,
  `rc_name` varchar(255) NOT NULL,
  PRIMARY KEY (`rc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `m2m_banners_pages`
--
ALTER TABLE `m2m_banners_pages`
  ADD CONSTRAINT `m2m_banners_pages_ibfk_2` FOREIGN KEY (`p_id`) REFERENCES `pages` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `m2m_banners_pages_ibfk_1` FOREIGN KEY (`b_id`) REFERENCES `banners` (`b_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `news`
--
ALTER TABLE `news`
  ADD CONSTRAINT `news_ibfk_1` FOREIGN KEY (`n_category`) REFERENCES `news_categories` (`nc_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pages`
--
ALTER TABLE `pages`
  ADD CONSTRAINT `pages_ibfk_1` FOREIGN KEY (`p_parent`) REFERENCES `pages` (`p_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `reviews_ibfk_1` FOREIGN KEY (`r_category`) REFERENCES `reviews_categories` (`rc_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
