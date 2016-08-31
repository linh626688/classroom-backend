-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2016 at 12:59 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `208g2`
--

-- --------------------------------------------------------

--
-- Table structure for table `classroom_has_groupps`
--

CREATE TABLE IF NOT EXISTS `classroom_has_groupps` (
  `class_id` bigint(20) NOT NULL,
  `groupp_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `classroom_has_posts`
--

CREATE TABLE IF NOT EXISTS `classroom_has_posts` (
  `class_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `classroom_has_users`
--

CREATE TABLE IF NOT EXISTS `classroom_has_users` (
  `class_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `class_room`
--

CREATE TABLE IF NOT EXISTS `class_room` (
`id` bigint(20) NOT NULL,
  `class_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE IF NOT EXISTS `comment` (
`id` bigint(20) NOT NULL,
  `comment_content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `groupp`
--

CREATE TABLE IF NOT EXISTS `groupp` (
`id` bigint(20) NOT NULL,
  `group_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `groupp_has_user`
--

CREATE TABLE IF NOT EXISTS `groupp_has_user` (
  `group_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `group_has_posts`
--

CREATE TABLE IF NOT EXISTS `group_has_posts` (
  `group_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE IF NOT EXISTS `post` (
`id` bigint(20) NOT NULL,
  `post_content` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `class_id` bigint(20) DEFAULT NULL,
  `group_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `post_has_comments`
--

CREATE TABLE IF NOT EXISTS `post_has_comments` (
  `post_id` bigint(20) NOT NULL,
  `comment_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` bigint(20) NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `token_expiry` datetime DEFAULT NULL,
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `user_has_posts`
--

CREATE TABLE IF NOT EXISTS `user_has_posts` (
  `user_id` bigint(20) NOT NULL,
  `post_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classroom_has_groupps`
--
ALTER TABLE `classroom_has_groupps`
 ADD UNIQUE KEY `UK_15ugvks3npeqi48fhpfbmeb1d` (`groupp_id`), ADD KEY `FK_gaa9uvlqra46rdq38bff29ub0` (`class_id`);

--
-- Indexes for table `classroom_has_posts`
--
ALTER TABLE `classroom_has_posts`
 ADD UNIQUE KEY `UK_gbpwfyrt054vmy235yxan2gvs` (`post_id`), ADD KEY `FK_anifrccfo6x2n9hq87etn4lwc` (`class_id`);

--
-- Indexes for table `classroom_has_users`
--
ALTER TABLE `classroom_has_users`
 ADD KEY `FK_cjyblil2key6x6r9lxi99xwc5` (`user_id`), ADD KEY `FK_b0p3pfka1pxs2or26exdrmiq5` (`class_id`);

--
-- Indexes for table `class_room`
--
ALTER TABLE `class_room`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_pedem78eo9yxq10d088ucmh2y` (`user_id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_f1sl0xkd2lucs7bve3ktt3tu5` (`post_id`), ADD KEY `FK_mxoojfj9tmy8088avf57mpm02` (`user_id`);

--
-- Indexes for table `groupp`
--
ALTER TABLE `groupp`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_mtpwtkkctq99nt437nr6ew3h4` (`class_id`);

--
-- Indexes for table `groupp_has_user`
--
ALTER TABLE `groupp_has_user`
 ADD KEY `FK_7jkueoo4pu31952rmq73oobvn` (`user_id`), ADD KEY `FK_5ijfdtfl7q9yql0nn7soqphkg` (`group_id`);

--
-- Indexes for table `group_has_posts`
--
ALTER TABLE `group_has_posts`
 ADD UNIQUE KEY `UK_x0p8h7lerrs2gkoqqdayqinq` (`post_id`), ADD KEY `FK_r1tr27i8n4e4l4u9oqlvax8d7` (`group_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
 ADD PRIMARY KEY (`id`), ADD KEY `FK_a7pheunoff4lvybmpwqdlpo4l` (`class_id`), ADD KEY `FK_qrt4ofi87nq2jbts4f90xt1v1` (`group_id`), ADD KEY `FK_kpfnivid38f5bwx3yl1lxeeae` (`user_id`);

--
-- Indexes for table `post_has_comments`
--
ALTER TABLE `post_has_comments`
 ADD UNIQUE KEY `UK_rxm7l3i5gmkxbp1lgnmccayan` (`comment_id`), ADD KEY `FK_hxajagu1kfohqtwu4q9pslet6` (`post_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_has_posts`
--
ALTER TABLE `user_has_posts`
 ADD UNIQUE KEY `UK_g6vacpt5e5o9q5mae3lpbsb4l` (`post_id`), ADD KEY `FK_mjl8vl0vynpwl4x76jcqkwq8e` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `class_room`
--
ALTER TABLE `class_room`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `groupp`
--
ALTER TABLE `groupp`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `classroom_has_groupps`
--
ALTER TABLE `classroom_has_groupps`
ADD CONSTRAINT `FK_15ugvks3npeqi48fhpfbmeb1d` FOREIGN KEY (`groupp_id`) REFERENCES `groupp` (`id`),
ADD CONSTRAINT `FK_gaa9uvlqra46rdq38bff29ub0` FOREIGN KEY (`class_id`) REFERENCES `class_room` (`id`);

--
-- Constraints for table `classroom_has_posts`
--
ALTER TABLE `classroom_has_posts`
ADD CONSTRAINT `FK_anifrccfo6x2n9hq87etn4lwc` FOREIGN KEY (`class_id`) REFERENCES `class_room` (`id`),
ADD CONSTRAINT `FK_gbpwfyrt054vmy235yxan2gvs` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Constraints for table `classroom_has_users`
--
ALTER TABLE `classroom_has_users`
ADD CONSTRAINT `FK_b0p3pfka1pxs2or26exdrmiq5` FOREIGN KEY (`class_id`) REFERENCES `class_room` (`id`),
ADD CONSTRAINT `FK_cjyblil2key6x6r9lxi99xwc5` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `class_room`
--
ALTER TABLE `class_room`
ADD CONSTRAINT `FK_pedem78eo9yxq10d088ucmh2y` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
ADD CONSTRAINT `FK_f1sl0xkd2lucs7bve3ktt3tu5` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
ADD CONSTRAINT `FK_mxoojfj9tmy8088avf57mpm02` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `groupp`
--
ALTER TABLE `groupp`
ADD CONSTRAINT `FK_mtpwtkkctq99nt437nr6ew3h4` FOREIGN KEY (`class_id`) REFERENCES `class_room` (`id`);

--
-- Constraints for table `groupp_has_user`
--
ALTER TABLE `groupp_has_user`
ADD CONSTRAINT `FK_5ijfdtfl7q9yql0nn7soqphkg` FOREIGN KEY (`group_id`) REFERENCES `groupp` (`id`),
ADD CONSTRAINT `FK_7jkueoo4pu31952rmq73oobvn` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `group_has_posts`
--
ALTER TABLE `group_has_posts`
ADD CONSTRAINT `FK_r1tr27i8n4e4l4u9oqlvax8d7` FOREIGN KEY (`group_id`) REFERENCES `groupp` (`id`),
ADD CONSTRAINT `FK_x0p8h7lerrs2gkoqqdayqinq` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`);

--
-- Constraints for table `post`
--
ALTER TABLE `post`
ADD CONSTRAINT `FK_a7pheunoff4lvybmpwqdlpo4l` FOREIGN KEY (`class_id`) REFERENCES `class_room` (`id`),
ADD CONSTRAINT `FK_kpfnivid38f5bwx3yl1lxeeae` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
ADD CONSTRAINT `FK_qrt4ofi87nq2jbts4f90xt1v1` FOREIGN KEY (`group_id`) REFERENCES `groupp` (`id`);

--
-- Constraints for table `post_has_comments`
--
ALTER TABLE `post_has_comments`
ADD CONSTRAINT `FK_hxajagu1kfohqtwu4q9pslet6` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`) ON DELETE CASCADE,
ADD CONSTRAINT `FK_rxm7l3i5gmkxbp1lgnmccayan` FOREIGN KEY (`comment_id`) REFERENCES `comment` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `user_has_posts`
--
ALTER TABLE `user_has_posts`
ADD CONSTRAINT `FK_g6vacpt5e5o9q5mae3lpbsb4l` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
ADD CONSTRAINT `FK_mjl8vl0vynpwl4x76jcqkwq8e` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
