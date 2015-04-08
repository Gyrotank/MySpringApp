-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Апр 08 2015 г., 23:09
-- Версия сервера: 5.6.17
-- Версия PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `pizzadelivery`
--

-- --------------------------------------------------------

--
-- Структура таблицы `addresses`
--

CREATE TABLE IF NOT EXISTS `addresses` (
  `addresses_id` int(11) NOT NULL AUTO_INCREMENT,
  `postal_code` varchar(20) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `street` varchar(30) DEFAULT NULL,
  `bld` varchar(10) DEFAULT NULL,
  `apt` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`addresses_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `addresses`
--

INSERT INTO `addresses` (`addresses_id`, `postal_code`, `city`, `street`, `bld`, `apt`) VALUES
(1, '0123', 'Kyiv', 'Khreschatik', '1B', '3'),
(2, '01234', 'Kyiv', 'Vokzalna', '221B', '378');

-- --------------------------------------------------------

--
-- Структура таблицы `clients`
--

CREATE TABLE IF NOT EXISTS `clients` (
  `clients_id` int(11) NOT NULL AUTO_INCREMENT,
  `clients_name` varchar(50) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`clients_id`),
  KEY `address_id` (`address_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `clients`
--

INSERT INTO `clients` (`clients_id`, `clients_name`, `address_id`) VALUES
(1, 'Ivan Ivanov', 1),
(2, 'Petro Petrov', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `orders_id` int(11) NOT NULL AUTO_INCREMENT,
  `orders_name` varchar(20) DEFAULT NULL,
  `orders_date` date DEFAULT NULL,
  `orders_price` double(10,2) DEFAULT NULL,
  `orders_status_id` int(11) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`orders_id`),
  KEY `orders_status_id` (`orders_status_id`),
  KEY `client_id` (`client_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `orders`
--

INSERT INTO `orders` (`orders_id`, `orders_name`, `orders_date`, `orders_price`, `orders_status_id`, `client_id`) VALUES
(1, 'Order1', '2008-08-22', 0.00, 2, 1),
(2, 'Order2', '2015-10-15', 0.00, 4, 2);

-- --------------------------------------------------------

--
-- Структура таблицы `orderstatuses`
--

CREATE TABLE IF NOT EXISTS `orderstatuses` (
  `order_statuses_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_statuses_name` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`order_statuses_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `orderstatuses`
--

INSERT INTO `orderstatuses` (`order_statuses_id`, `order_statuses_name`) VALUES
(1, 'NEW'),
(2, 'IN PROGRESS'),
(3, 'CANCELLED'),
(4, 'DONE');

-- --------------------------------------------------------

--
-- Структура таблицы `pizzas`
--

CREATE TABLE IF NOT EXISTS `pizzas` (
  `pizzas_id` int(11) NOT NULL AUTO_INCREMENT,
  `pizzas_name` varchar(20) DEFAULT NULL,
  `pizzas_price` double(10,2) DEFAULT NULL,
  `pizzas_type` int(3) DEFAULT NULL,
  PRIMARY KEY (`pizzas_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- Дамп данных таблицы `pizzas`
--

INSERT INTO `pizzas` (`pizzas_id`, `pizzas_name`, `pizzas_price`, `pizzas_type`) VALUES
(1, 'Big Meat Pizza', 450.00, 2),
(2, 'Big Sea Pizza', 500.00, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `pizzasinorders`
--

CREATE TABLE IF NOT EXISTS `pizzasinorders` (
  `pizzas_in_orders_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `pizza_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`pizzas_in_orders_id`),
  KEY `order_id` (`order_id`),
  KEY `pizza_id` (`pizza_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Дамп данных таблицы `pizzasinorders`
--

INSERT INTO `pizzasinorders` (`pizzas_in_orders_id`, `order_id`, `pizza_id`, `quantity`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 2),
(3, 2, 1, 2);

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `clients_ibfk_1` FOREIGN KEY (`address_id`) REFERENCES `addresses` (`addresses_id`);

--
-- Ограничения внешнего ключа таблицы `pizzasinorders`
--
ALTER TABLE `pizzasinorders`
  ADD CONSTRAINT `pizzasinorders_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`orders_id`),
  ADD CONSTRAINT `pizzasinorders_ibfk_2` FOREIGN KEY (`pizza_id`) REFERENCES `pizzas` (`pizzas_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
