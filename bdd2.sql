-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Lun 19 Octobre 2015 à 15:13
-- Version du serveur: 5.5.44-0ubuntu0.14.04.1
-- Version de PHP: 5.5.9-1ubuntu4.13
drop table if EXISTS `commandedProduct`;
drop table if EXISTS `commanded_product`;
drop table if EXISTS `command`;
drop table if EXISTS `card`;
drop table if EXISTS `ordered_product`;
drop table if EXISTS `customer_order`;
drop table if EXISTS `product`;
drop table if EXISTS `adress`;
drop table if EXISTS `user`;
drop table if EXISTS `category`;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+01:00";

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name_fr` varchar(45) NOT NULL,
  `name_eng` varchar(45) NOT NULL,
  `super_id` int(10) unsigned,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`super_id`) REFERENCES `category`(`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45),
  `lastname` varchar(45),
  `mail` varchar(45) NOT NULL UNIQUE,
  `username` varchar(45) NOT NULL UNIQUE,
  `password` varchar(255) NOT NULL,
  `phone` varchar(45),
  `type` varchar(45) NOT NULL DEFAULT "user",
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `adress` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `adress` varchar(45),
  `city_name` varchar(45),
  `country` varchar(45) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` decimal(6,2) NOT NULL,
  `description` tinytext NOT NULL,
  `last_update` timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `category_id` int(10) unsigned NOT NULL,
  `compagny_id` int(10) unsigned NOT NULL,
  `stock` int(10) unsigned NOT NULL,
  `image` varchar(255) NOT NULL,
  `author` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`category_id`) REFERENCES `category`(`id`),
  FOREIGN KEY (`compagny_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `ordered_product` (
  `product_id` int(10) unsigned NOT NULL,
  `customer_id` int(10) unsigned NOT NULL,
  `quantity` int(10) unsigned NOT NULL DEFAULT 1,
  PRIMARY KEY (`customer_id`,`product_id`),
  FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
  FOREIGN KEY (`customer_id`) REFERENCES `user`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


create table IF NOT EXISTS `card`(
    `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
    `user_id` int(10) unsigned NOT null,
    `name` varchar(16),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `command` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `amount` decimal(6,2) NOT NULL,
  `date_created` timestamp DEFAULT CURRENT_TIMESTAMP,
  `card_id` int(10) unsigned NOT NULL,
  `adress_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES `user`(`id`),
  FOREIGN KEY (`card_id`) REFERENCES `card`(`id`),
  FOREIGN KEY (`adress_id`) REFERENCES `adress`(`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;


CREATE TABLE IF NOT EXISTS `commanded_product` (
  `command_id` int(10) unsigned NOT NULL ,
  `product_id` int(10) unsigned NOT NULL,
  `quantity` int(10) unsigned NOT NULL DEFAULT 1,
  PRIMARY KEY (`command_id`,`product_id`),
  FOREIGN KEY (`product_id`) REFERENCES `product`(`id`),
  FOREIGN KEY (`command_id`) REFERENCES `command`(`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;



insert into `user` (id,firstname,password,mail,`type`,lastname,username) values  (3,'first','nunch','mail_nunch','user','last','nunch');
insert into `user` (id,firstname,password,mail,`type`,lastname,username) values  (4,'first','pass','mail','user','last','session');
insert into `user` (id,firstname,password,mail,`type`,lastname,username) values  (5,'first','nunch','mail_nunch2','compagny','last','nunch2');
insert into `user` (id,firstname,password,mail,`type`,lastname,username) values  (6,'first','admin','mail_admin','admin','last','admin');
