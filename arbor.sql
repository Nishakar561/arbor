-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 13, 2020 at 04:32 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `arbor`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `email`, `password`) VALUES
(1, 'nishakar561@gmail.com', '$2y$10$zR9J.AOeTeosHmHRkMMlB.xpymlqT0.bL.8wcF3J.uXDbowBqyZR.');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `username` varchar(60) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_size` varchar(11) NOT NULL,
  `item_amount` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

CREATE TABLE `categories` (
  `id` int(11) NOT NULL,
  `name` varchar(60) NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `name`, `image`) VALUES
(1, 'Men\'s fashion', 'assets/images/categories/mens_fashion.png'),
(2, 'Women\'s fashion', 'assets/images/categories/womens_fashion.png'),
(3, 'Computer and mobiles', 'assets/images/categories/computer_and_mobiles.png'),
(4, 'Books', 'assets/images/categories/books.png'),
(5, 'Home appliances', 'assets/images/categories/home_appliances.png'),
(6, 'Games and accessories', 'assets/images/categories/games_and_accessories.png');

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `sub_category_id` int(11) NOT NULL,
  `image` text NOT NULL,
  `brand` varchar(60) NOT NULL,
  `details` text NOT NULL,
  `sizes` text NOT NULL,
  `stock` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `discount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`id`, `category_id`, `sub_category_id`, `image`, `brand`, `details`, `sizes`, `stock`, `price`, `discount`) VALUES
(1, 1, 1, 'assets/images/data_images/shirtm1.jpg', 'MANQ', 'Mens\'s Checkered Regular Fit Casual Shirt', ',39,40,42,44', 19, 566, 10),
(2, 1, 1, 'assets/images/data_images/shirtm2.jpg', 'MANQ', 'Mens\'s Checkered Regular Fit Casual Shirt', ',39,40,42,44', 15, 566, 10),
(3, 1, 1, 'assets/images/data_images/shirtm3.jpg', 'MANQ', 'Mens\'s Stripped Regular Fit Casual Shirt', ',39,40,42,44', 15, 566, 10),
(4, 1, 1, 'assets/images/data_images/shirtm4.jpg', 'IndoPrimo', 'Men\'s Regular Fit Casual Shirt', ',39,40,42,44', 10, 699, 15),
(5, 1, 1, 'assets/images/data_images/shirtm5.jpg', 'House & Shields', 'Men\'s Regular fit Casual Shirt\r\n', ',39,40,42,44', 5, 390, 5),
(6, 1, 2, 'assets/images/data_images/t_shirtm1.jpg', 'Katso', 'Men\'s Cotton Hooded T-Shirt', ',39,40,42,44', 5, 356, 15),
(7, 1, 2, 'assets/images/data_images/t_shirtm2.jpg', 'Maniac', 'Men\'s Slim fit T-shirt', ',39,40,42,44', 6, 439, 15),
(8, 1, 2, 'assets/images/data_images/t_shirtm3.jpg', 'EYEBOGLER', 'Regular Fit Men\'s Auto-Striper Designer T-Shirt ($T305)', ',39,40,42,44', 5, 443, 16),
(9, 1, 2, 'assets/images/data_images/t_shirtm4.jpg', 'fanideaz', '\r\nMen\'s Cotton Half Sleeve Striped Polo T Shirt with Collar', ',39,40,42,44', 6, 749, 10),
(10, 1, 2, 'assets/images/data_images/t_shirtm5.jpg', 'Fenoix', '\r\nMen\'s T-Shirt', ',39,40,42,44', 8, 399, 5),
(11, 1, 3, 'assets/images/data_images/joggerm1.jpg', 'LEVIZO', 'Stretchable and Casual Joggers Lower Trackpants with Side Zip Pockets for Sports Workout for Men', ',M,L,XL', 5, 585, 10),
(12, 1, 3, 'assets/images/data_images/joggerm2.jpg', 'Alan Jones Clothing', 'Men\'s Slim Fit Trackpants', ',M,L,XL', 6, 699, 15),
(13, 1, 3, 'assets/images/data_images/joggerm3.jpg', 'BLIVE', 'Color Block Men Black, Grey Joggers Track Pants', ',M,L,XL', 6, 599, 10),
(14, 1, 3, 'assets/images/data_images/joggerm4.jpg', 'Generic', 'Ubof Men Joggers Track Pant Sports Lower', ',M,L,XL', 5, 389, 15),
(15, 1, 3, 'assets/images/data_images/joggerm5.jpg', 'Alan Jones Clothing', 'Men\'s Cotton Solid Slim Joggers Track Pants', ',M,L,XL', 8, 699, 15),
(16, 1, 4, 'assets/images/data_images/shoesm1.jpg', 'Ethics', 'Men\'s Ultra Lite Mesh (Black/Red) Casual Sports Shoes', ',6UK,7UK,8UK,9UK', 5, 330, 10),
(17, 1, 4, 'assets/images/data_images/shoesm2.jpg', 'layasa', 'Men\'s Air Series Mesh Sports Running Shoes', ',6UK,7UK,8UK,9UK', 7, 399, 15),
(18, 1, 4, 'assets/images/data_images/shoesm3.jpg', 'ASIAN', 'Shoes SuperfitNavy BlueGry Men Sports Shoes', ',6UK,7UK,8UK,9UK', 6, 499, 10),
(19, 1, 4, 'assets/images/data_images/shoesm4.jpg', 'ASIAN', 'Cosco Sports Running Shoes for Men', ',6UK,7UK,8UK,9UK', 6, 640, 10),
(20, 1, 4, 'assets/images/data_images/shoesm5.jpg', 'Sparx', 'Men SM-397 Sports Shoes', ',6UK,7UK,8UK,9UK', 8, 850, 15),
(21, 1, 5, 'assets/images/data_images/watchm1.jpg', 'EDDY HAGER', 'Time Teacher Black Dial Men\'s Watch', ',', 8, 329, 5),
(22, 1, 5, 'assets/images/data_images/watchm2.jpg', 'TIMEX', 'Analog Black Dial Men\'s Watch-TW00ZR264E', ',', 5, 1000, 20),
(23, 1, 5, 'assets/images/data_images/watchm3.jpg', 'Espoir', 'Mens ESP12457 Analog Blue Dial Watch', ',', 10, 299, 10),
(24, 1, 5, 'assets/images/data_images/watchm4.jpg', 'SWISSTYLE', 'Analogue Men\'s Watch (Black Dial Black Colored Strap)', ',', 0, 409, 15),
(25, 1, 5, 'assets/images/data_images/watchm5.jpg', 'SKMEI', 'Analog Digital Quartz Men\'s Watch with Silicone Strap - AD1155', ',', 6, 499, 10),
(26, 1, 6, 'assets/images/data_images/glassesm1.jpg', 'LA VICTORIE', 'Aviator UV Protected Unisex Black Frame Light Green Sunglasses', ',', 5, 589, 10),
(27, 1, 6, 'assets/images/data_images/glassesm2.jpg', 'ARZONAI', 'Trendy New Cateye Unique Shahid Kapoor Design Stylish Sunglasses for Men and Women (Silver-Silver)', ',', 5, 599, 5),
(28, 1, 6, 'assets/images/data_images/glassesm3.jpg', 'SIMSCO', 'Polarized Aviator Black Sunglasses with TAC Material Sunglasses For Men Latest and Sunglasses For men Stylish Wayfarer Sunglasses for Medium size ', ',', 8, 599, 10),
(29, 1, 6, 'assets/images/data_images/glassesm4.jpg', 'ELEGANTE', 'Branded Metal Body Square Inspired from Kabir Singh Sunglass for Men and Boys', ',', 8, 399, 5),
(30, 1, 6, 'assets/images/data_images/glassesm5.jpg', 'SIMSCO', 'Firebird with Device Polarized Aviator Men\'s Sunglasses(IP-SG01B|Black)', ',', 8, 349, 5),
(31, 1, 7, 'assets/images/data_images/innerm1.jpg', 'Dixcy Scott', 'Men\'s Trunks', ',S,M,L,XL', 8, 414, 10),
(32, 1, 7, 'assets/images/data_images/innerm2.jpg', 'Jockey', 'Men\'s Cotton Brief(Colors & Print May Vary)(color may vary)', ',S,M,L,XL', 8, 160, 5),
(33, 1, 7, 'assets/images/data_images/innerm3.jpg', 'Pepe Jeans Innerwear', 'Men\'s Solid Brief', ',S,M,L,XL', 5, 319, 10),
(34, 1, 7, 'assets/images/data_images/innerm4.jpg', 'Pepe Jeans Innerwear', 'Men\'s Printed Trunks', ',S,M,L,XL', 5, 379, 10),
(35, 1, 7, 'assets/images/data_images/innerm5.jpg', 'Dixcy Scott', 'Men\'s Trunk Slim Fit Solid Innerwear', ',S,M,L,XL', 5, 159, 10),
(36, 2, 8, 'assets/images/data_images/shirtw1.jpg', 'C.Cozami', 'Women\'s Casual Shirt', ',S,M,L,XL', 10, 499, 5),
(37, 2, 8, 'assets/images/data_images/shirtw2.jpg', 'DAMEN MODE', 'Women Red Checkered Cotton Shirt', ',S,M,L,XL', 10, 382, 5),
(38, 2, 9, 'assets/images/data_images/t_shirtw1.jpg', 'PrintOctopus', 'Women\'S Grey T-Shirts', ',S,M,L,XL', 10, 448, 10),
(39, 2, 9, 'assets/images/data_images/t_shirtw2.jpg', 'Pooplu', 'Graphic Printed Women Tshirt Unicorn Cotton Printed V Neck Half Sleeves Multicolour T Shirt. Animal, Cute Animal Tshirts', ',S,M,L,XL', 10, 419, 10),
(40, 2, 10, 'assets/images/data_images/joggerw1.jpg', 'FITG18', 'irls/Women/Ladies Stylish/Hot Shimmer Black legging with side striped track pant/jogger/Tights for gym/work out/casual & party (Size 26 inch to 36 inch) (black, Free Size)', ',', 20, 298, 5),
(41, 2, 10, 'assets/images/data_images/joggerw2.jpg', 'ROOLIUMS', '(Brand Factory Outlet Premium Supersoft Capri Pant Army Style, Army Joggers for Women, Army Track Lower for Sports Gym Athletic Training Workout - Green Camouflage Print - Free Size', ',', 10, 394, 5),
(42, 2, 11, 'assets/images/data_images/shoesw1.jpg', 'Bourge', 'Women\'s Micam-102 Running Shoes', ',6UK,7UK,8UK,9UK', 5, 659, 5),
(43, 2, 11, 'assets/images/data_images/shoesw2.jpg', 'Sparx', 'Women\'s Mesh Running Shoes', ',6UK,7UK,8UK,9UK', 5, 830, 5),
(44, 2, 12, 'assets/images/data_images/watchw1.jpg', 'Giordano', 'Analog White Dial Women\'s Watch - 2754-03', ',', 5, 3375, 10),
(45, 2, 12, 'assets/images/data_images/watchw2.jpg', 'Giordano', 'Multi Function Grey  Dial Women\'s Watch', ',', 5, 1098, 10),
(46, 2, 13, 'assets/images/data_images/glassesw1.jpg', 'Livhò', 'Retro Vintage Narrow Cat Eye Sunglasses for Women Clout Goggles Plastic Frame LI268/LI269 (Bright frame black gray piece)', ',', 5, 1098, 10),
(47, 2, 13, 'assets/images/data_images/glassesw2.jpg', 'ELEGANTE', 'Branded Honey Bee Oval Sunglasses for Women', ',', 5, 399, 5),
(48, 2, 14, 'assets/images/data_images/innerw1.jpg', 'FIMS - Fashion is my style', 'Satin Nylon Lycra Spandex (4WAY)|Women Innerwear|Lingerie for Women|Sexy Lingerie for Honeymoon Sex|Lingerie Set|Babydoll Nightwear|Lingeries for Women|Free Size|', ',', 20, 297, 2),
(49, 2, 14, 'assets/images/data_images/innerw2.jpg', 'Enamor', 'F024 Plush Comfort Full Support Bra - Non-Padded Wirefree High Coverage', ',32B,32C,34B,34DD', 15, 899, 10),
(50, 3, 15, 'assets/images/data_images/mobile1.jpg', 'Apple iPhone 11 (128GB) - Black', '6.1-inch (15.5 cm) Liquid Retina HD LCD display,Water and dust resistant (2 meters for up to 30 minutes, IP68),Dual-camera system with 12MP Ultra Wide and Wide cameras; Night mode, Portrait mode, and 4K video up to 60fps,12MP TrueDepth front camera with Portrait mode, 4K video, and Slo-Mo,Face ID for secure authentication and Apple Pay,A13 Bionic chip with third-generation Neural Engine,Fast-charge capable', ',', 15, 73600, 0),
(51, 3, 15, 'assets/images/data_images/mobile2.jpg', 'Samsung Galaxy M21 (Midnight Blue, 4GB RAM, 64GB Storage)', 'Triple Camera Setup - 48MP (F2.0) Main Camera +8MP (F2.2) Ultra Wide Camera +5MP(F2.2) Depth Camera and 20MP (F2.2) front facing Punch Hole Camera, 6.4-inch(16.21 centimeters) Super Amoled - Infinity U Cut Display , FHD+ Resolution (2340 x 1080) , 404 ppi pixel density and 16M color support, 6000 mAh Battery\r\n1 year manufacturer warranty for device and 6 months manufacturer warranty for in-box accessories including batteries from the date of purchase, Android 10.0 operating system with Exynos 9611,2.3GHz,1.7GHz Octa-Core processor, 4GB RAM, 64GB internal memory expandable up to 512GB and dual SIM', ',', 15, 15999, 13),
(52, 3, 16, 'assets/images/data_images/mobilea1.jpg', 'ELV Car Mount Adjustable Car Phone Holder Universal Long Arm', 'PRODUCT FEATURES : ELV Easy one touch mounting system locks and releases the device with just a push of a finger, Two step locking lever provides additional mounting support for multiple surfaces, EASY ACCESS : Redesigned bottom foot ensures access to all your device ports.\r\nSUPER POWER GRIP IN BOTTOM : Super sticky gel pad sticks securely to most surfaces (including textured surfaces),Yet is still removable, 360 DEGREE ROTATION : Fully adjuestable with 360 degree rotation for quick portrait and landscape views, TELESCOPIC ARM New telescopic arm adds 2 inches to allow for closer device viewing, LEGAL DISCLAIMER : If you have made a purchase and did not received the product as promised, please return the item and reorder from the seller - iSmart Technology. Other sellers are selling counterfeit products. Ismart Technology takes no responsibility of their poor quality until purchased from the original certified manufacturer and seller - Ismart Technology.', ',', 5, 650, 13),
(53, 3, 16, 'assets/images/data_images/mobilea2.jpg', 'Intellilens® Square Unisex Blue Cut Spectacles With Anti-gla', 'NOTICE THE DIFFERENCE IN DAYS - With these glasses, you’ll start to notice better screen viewing and greater clarity after just a few days of wearing them\r\nNO MORE TIRED EYES - Reduce eye strain, eye fatigue and sensitivity to light during long computer sessions. Allow your eyes to blink and function naturally. Great for office workers or anyone who has to look at a screen all day. Designed for all day comfort with a durable yet flexible and super lightweight frame. Whether you’re catching up on emails after a long day at work, or burning the midnight oil to finish a project - protect your eyes from excessive levels of blue light, WORKS WITH ALL SCREEN TYPES - The average Indian now spends over 7 hours a day looking at a screen. Whether it’s laptops, desktops, tablets or smartphones - our glasses help reduce harmful blue light that can causes eye issues and prevent ideal levels of sleep. Also effective against sensitivity to fluorescent lights\r\nIMPROVED VIEWING EXPERIENCE - We’re so confident that our glasses will give you better screen viewing because we offer you the best in class pair of lenses, INTERNATIONAL LAB TESTED- Our glasses are ISO 8980-3:2013 Certified by QIMA.', ',', 5, 2999, 15),
(54, 3, 17, 'assets/images/data_images/powerbank1.jpg', 'Aukey 10000mAh Dual Port Mini Power Bank Lithium Ion Portabl', 'Combined 3.1A output from dual USB ports - Simultaneously recharge your smartphone and tablet at max speed, AiPower Charging Technology – Get the safest maximum recharge rate for all of your USB powered devices, Built-in safeguards protect your devices against excessive current, overheating, and overcharging. Fast Charge in the back pocket of your jeans - 10000mAh of backup USB charging power, plus a flashlight just in case, Package Contents: AUKEY PB-N42 10000mAh Power Bank, 30cm - Micro-USB Cable, User Manual', ',', 5, 2999, 10),
(55, 3, 17, 'assets/images/data_images/powerbank2.jpg', 'Samsung EB-P1100BSNGIN 10000mAH Lithium Ion Power Bank (Silv', '10000mAH lithium-ion battery\r\n10000mAh power bank for fast charging in and out, Multiple fast charge -in and out, dual port support\r\nMetalling slim design charging points, 1 year manufacturer warranty, Country of Origin: India', ',', 5, 1499, 3),
(56, 3, 18, 'assets/images/data_images/laptop1.jpg', 'ASUS VivoBook S14 AMD Ryzen 5 4500U, 14-inch FHD Thin and Li', 'Processor: AMD Ryzen 5 4500U Processor, 2.3 GHz (8MB Cache, up to 4.0 GHz, 6 Cores, 6 Threads), Memory & Storage: 8GB DDR4 3200MHz onboard RAM | Storage: 512GB M.2 NVMe PCIe 3.0 SSD + empty 1x M.2 Slot for SSD Storage Expansion, Graphics: Integrated AMD Radeon RX Vega 6 Graphics\r\nDisplay: 14-inch LED-backlit Full HD (1920 x 1080) 16:9, Anti-glare panel, 3 sided NanoEdge bezels, with 85% screen-to-body ratio, IPS-level wide-view technology, Software Included: Pre-installed MS Office Home and Student 2019 | Operating System: Pre-loaded Windows 10 Home with lifetime validity, Design & battery: Metallic body |Thin and Light Laptop | Laptop weight: 1.40 kg | 50WHrs, 3-cell lithium-polymer battery | Up to 12 hours battery life ;Note: Battery life depends on conditions of usage, Keyboard: Full-size backlit, with 1.4mm key travel | Touchpad : Glass-covered, Precision Touchpad', ',', 5, 70999, 5),
(57, 3, 18, 'assets/images/data_images/laptop2.jpg', 'ASUS VivoBook 14 AMD Ryzen 3 3250U 14-inch FHD Compact and L', 'Processor: AMD Ryzen 3 3250U Processor, 2.6 GHz (4MB Cache, up to 3.5 GHz, 2 Core, 4 Threads), Memory & Storage: 4GB DDR4 2400MHz onboard RAM, upgradeable up to 12GB using empty 1x SO-DIMM Slot with | Storage: 1TB SATA 5400RPM 2.5-inch HDD + empty 1x M.2 Slot for SSD storage expansion, Graphics: Integrated AMD Radeon R3 Graphics, Display: 14.0-inch (16:9) LED-backlit FHD (1920x1080) 60Hz Anti-Glare Panel with 45% NTSC, Operating System: Pre-loaded Windows 10 Home with lifetime validity, Design & battery: NanoEdge Bezels | 23.1mm Thin body | Laptop weight 1.60 kg | Thin and Light Laptop | 32WHrs, Lithium-ion, 2-Cell battery, Keyboard: Full-size chiclet keyboard | 1.4mm key travel distance', ',', 5, 33990, 10),
(58, 3, 19, 'assets/images/data_images/tablet1.jpg', 'Apple iPad (10.2-inch, Wi-Fi, 32GB) - Space Grey (Latest Mod', '10.2-inch Retina display, A10 Fusion chip, Touch ID fingerprint sensor and Apple Pay\r\n8MP back camera, 1.2MP FaceTime HD front camera, Stereo speakers, 802.11ac Wi-Fi, Up to 10 hours of battery life', ',', 5, 29900, 0),
(59, 3, 19, 'assets/images/data_images/tablet2.jpg', 'Lenovo Tab M10 FHD REL Tablet (10.1-inch, 2GB, 32GB, WiFi + ', '8MP rear camera with auto focus | 5MP front camera 25.654 centimeters (10.1-inch) with 1920 x 1200 pixels resolution, Memory, Storage & SIM: 2GB RAM | 32GB internal memory expandable up to 32GB | Single Nano SIM, Android Pie v9.0 operating system with 1.8GHz Qualcomm Snapdragon 450 octa core processor, 7000mAH lithium-ion battery, 1 year manufacturer warranty for device and 6 months manufacturer warranty for in-box accessories including batteries from the date of purchase, Box also includes: Adapter, micro USB, quick starter guide, warranty, safety guide and SIM pin', ',', 5, 27500, 15),
(60, 3, 20, 'assets/images/data_images/desktop1.jpg', 'HP All in One AMD Ryzen 3 21.45-inch FHD Desktop (8GB/256 GB', '2.6 GhzGHz AMD Ryzen 3 3200u processor, 8GB DDR4 RAM, 1TB 7200rpm hard drive + 256GB SSD, 21.45-inch screen, AMD Radeon Vega 3 Graphics Graphics\r\nWindows 10, Home operating system, With the latest processor and enough storage for you and your family, seamlessly go from sending work emails to uploading vacation photos with ease, Includes an HD camera—equipped with a slide switch for privacy—plus, built-in speakers and easy Wi-Fi connectivity. Now, you can stay in touch while maintaining peace of mind', ',', 5, 47107, 10),
(61, 3, 20, 'assets/images/data_images/desktop2.jpg', 'HP All-in-One PC 20-c406il Desktop (Celeron J4005/4GB/1TB HD', '2 GhzGHz Intel Celeron J4005 processor, 4GB DDR4 RAM, 1TB 7200rpm hard drive, 19.5-inch screen, Intel UHD Graphics 600 Graphics, DOS operating system\r\nDon’t worry about growing your collection of digital movies, songs, and pictures. With massive storage options you can save it all, and still have plenty of room left over, The perfect combination of performance, power consumption, and value helps your device run smoothly and reliably with two processing cores to handle all your tasks', ',', 7, 27259, 6),
(62, 3, 21, 'assets/images/data_images/computera1.jpg', 'Quantum QHM6633 4 Port Hi-Speed USB Hub (Multicolor)', '4-Port ultra high speed USB Hub, Useful for laptop and desktop, Comes in red colour, Application: Truly Plug and play automatic with All OS including windows 98,2000,ME,XP,7,Mac Linux virtually ; Network Speed: 480, Packaging: 4 port USB Mini- Hub with attached Usb Cable, Ultra High Speed, Usb 2.0 High- speed 480 MBPS HUB, Sleek design, 4 ports work together, Country of Origin: India', ',', 5, 330, 10),
(63, 3, 21, 'assets/images/data_images/computera2.jpg', 'Xmate Zorro Pro 2.4GHz Wireless Gaming Mouse, 3200 DPI Optic', 'PC GAMING MOUSE COMPATIBILITY: Windows 10/8/7/Vista/XP/Win98SE/Win2000, Limited Mac OS support Works well with all major Computers/Laptop brands Asus, Dell, Hp, Lenovo, Dell, MSI and others, Mechanical Button: 6 Buttons with different functions like the previous page and next page. If you wish you can turn off the colorful lights using the button provided at the backside of the mouse, 4 DPI Levels: Four color RGB breathing light cycle replacement. 4-type DPI rating controlled by touching the DPI button - Violet 800/Blue 1200/Purple 1600/Red 3200, Excellent Performance with High Polling Rate: Zorro Pro wireless gaming mouse with 500Hz high polling rate ensures smooth movement offers reliable connectivity with smooth and responsive tracking\r\nErgonomic Gamer Mouse: Made of ABS plastic, leather collar, anti-skid scroll wheel, rubber finish with mechanical switches & 2.4GHz wireless bandwidth that stand up to tough gaming needs like FPS/PUBG/COD, etc, 1-year warranty from the date of purchase, contact us at 040-67138844 (9am - 6pm, Monday to Friday & Saturday 9am - 1pm), Country of Origin: China', ',', 5, 2499, 10),
(64, 3, 22, 'assets/images/data_images/pendrive1.jpg', 'SanDisk Cruzer Blade SDCZ50-008G-I35 8GB USB 2.0 Pen Drive', 'Keep your digital world in your pocket in our smallest package, Transfer and share photos, videos, songs and other files between computers with ease, Free up space on your OTG-enabled Android phone. Customer care:IndiaSupport@sandisk.com, Back up your mobile photos, videos and contacts, Manufacturer Detail: SanDisk InternationalLtd.c/o Unit 100 AirsideBusiness Park,Lakeshore Drive,Swords,Co. Dublin, Irelandmporter Details: Rashi Peripherals Pvt. Ltd. Rashi Complex,A Building,Survey186,Dongaripada,Poman Village,Vasai Bhiwandi Road, Dist. Thane,Maharastra 401208, India, Country of Origin: Malaysia', ',', 5, 450, 5),
(65, 3, 22, 'assets/images/data_images/pendrive2.jpg', 'SanDisk Ultra Dual Drive Go Type C Pendrive for Mobile 64GB,', 'The 2-in-1 Pendrive for Mobile with a reversible USB Type-C and a traditional Type-A connector, Seamlessly move content between your USB Type-C smartphone, tablets and Macs and USB Type-A computers, Free up space on your USB Type-C smartphone so you can take more photos, Automatically back up photos using the SanDisk Memory Zone app, High-performance USB 3.1 Gen 1 drive with 150MB/s read speeds lets you quickly move your files to your computer, Dual-purpose swivel design protects connectors and features a keyring hole to take your drive on the go, Country of Origin: Malaysia', ',', 5, 1600, 5),
(66, 3, 23, 'assets/images/data_images/office1.jpg', 'Faber-Castell Home and Office Statonary Kit', 'A kit for the stationery organizer, Assortment of 7 pcs: 2 ball pens, 1 ice text liner, 1 glue stick, 1 correction pen, 1 bold permanent marker, 1 fine permanent marker, Perfect as a joining kit, Country of Origin: India', ',', 5, 130, 5),
(67, 3, 23, 'assets/images/data_images/office2.jpg', 'ABTRIX WITH AB Office Desk Organizer. Pen & Pencil Holder. M', 'The desk organizer is divided into 6 divided compartments with drawer for desk, with dimensions for your desk accessories, small things, office accessories or home supplies, The pencil holder has enough capacity to store your pens, pencils, business cards, paper clips, stick notes, markers, scissors and more. But also this is a tools organizer, or makeup brushes organizer, you can storage cosmetics and make-up tools or other tools. Enough capacity and multifunctional, You can organize the desk easier and faster. With a blend of function and style, perfect storage organizer for students, teachers, home, school, and office professionals. Make your desktop clean and tidy, The desk organizers are made of plastic, acrylic with high quality, environmentally friendly materials, safe and non-toxic, very smooth, durable and sturdy, meeting all your demands for home and office using, school items, Dimension: 220*110*105 mm', ',', 5, 599, 5),
(68, 3, 24, 'assets/images/data_images/wearable1.jpg', 'WELROCK SmartBand_P-01 Activity Tracker', 'Only For Heart Rate Version Product, Waterproof: TPU material waterproof, not afraid of washing hands/ doing housework/ walking under rain, Compatibility: Requires Android 4.3 or above, iOS 7.1 or above, Bluetooth 4.0 or above, download APP \"Veryfit 2.0\" from Apple Store or Google Play, Compact, easy-to-use Pedometer, Intelligent monitoring of the sleep quality, Walk Tracker, Data Counting, Heartbeat', ',', 5, 2500, 10),
(69, 3, 24, 'assets/images/data_images/wearable2.jpg', 'SHOPTOSHOP Activity Tracker, Bluetooth - Black', 'All-day activity tracking: Track steps, distance, calories burned, active minutes, you can check daily activity and time on OLED display or APP, TFT HD LCD touch screen, there have four different operation interface for you to change, Resolution ratio: 128x64 pixel; 380MAH polymer lithium battery, standby time up to 2 Days fully charged, You will receive only notification to See Calls & Messages on Your Wrist: Receive/Rejection call, calendar, SMS and SNS, (Facebook, WhatsApp, LinkedIn, Instagram, and Twitter) notifications on display, fitness tracker band let you never miss the messages that matter, Need to download app in your smart phone and switch ON Bluetooth of your smart phone to connect the fitness band, NOTE- For Any Product Issue Please Contact Us Through Buyer Massage Please Install Any One App From Play Store for the device App Name YOHO,FIT PRO,LEFUN HEALTH', ',', 5, 999, 10),
(70, 4, 25, 'assets/images/data_images/science1.jpg', 'The Vault of Vishnu Paperback – 27 January 2020', 'A Pallava prince travels to Combodia to be crowned king, carrying with hin secrets that will be the cause of great wars many centuries later', ',', 5, 399, 0),
(71, 4, 25, 'assets/images/data_images/science2.jpg', 'The Theory of Everything Paperback – Special Edition, 25 Sep', 'Seven lectures by the brilliant theoretical physicist have been compiled into this book to try to explain to the common man, the complex problems of mathematics and the question that has been gripped everyone all for centuries ', ',', 5, 150, 0),
(72, 4, 26, 'assets/images/data_images/schoolt1.jpg', 'Science Textbook for Class - 9 - 964 Paperback – 1 January 2', 'Published by NCERT, this Science Textbook for Class 9 closely follows the guidelines and syllabus that has been laid out by CBSE. It comes in a paperback form and contains within itself different chapters that cover the syllabus in its entirety. The content of this book is written in a simple language, which will not only help the students to grasp the basic concepts but also will help them to learn the topics by heart.', ',', 10, 150, 0),
(73, 4, 26, 'assets/images/data_images/schoolt2.jpg', 'Marigold - Textbook in English for Class - 5 - 526 Paperback', 'Published by the NCERT, this Marigold - Textbook in English will be the best apparatus for the students of class 5 to learn English. It maintains close parity with the syllabus and the guidelines that have been set by the CBSE. It comes with literary content that will pave the foundation of the mastery of English language in the minds of the students', ',', 10, 60, 0),
(74, 4, 27, 'assets/images/data_images/childrenb1.jpg', 'Words Of Wonder A to Z : Inspirational Art & Stories For The', 'The book \'WOW: A to Z\' is the collaborative work of 26 amazingly talented artists and an author. It features 26 words, each starting with a different letter of the English alphabet. Each of the Words Of Wonder represents a human virtue and is accompanied with an original art, a thought provoking quotation and an engaging story. The book draws its inspiration from Aristotle\'s saying: The whole is greater than the sum of its parts. WOW : A to Z intends to inspire those who dream, and encourages them to pursue their goals and aspirations.', ',', 10, 499, 0),
(75, 4, 27, 'assets/images/data_images/childrenb2.jpg', 'The Dreamer: (Inspirational Story, Picture Book for Children', 'Even pigs can learn to fly: Once, there was a pig who admired birds. But he could never join them. Or could he? Thus begins the journey of a pig with big dreams, and the perseverance to make them come true. He develops flight plans, builds experimental contraptions, and has far-flung adventures, but at the end of the day, his favorite thing to do is sit and watch for those he loves best: birds. Il Sung Na creates a world at once whimsical and aspirational, where anything is possible and, yes, even pigs can learn to fly.', ',', 10, 1269, 0),
(76, 4, 28, 'assets/images/data_images/competitive1.jpg', 'Quantitative Aptitude for Competitive Examinations Paperback', 'Ever since its release in 1989, Quantitative Aptitude has come to acquire a special place of respect and acceptance among students and aspirants appearing for a wide gamut of competitive exams. Now, more than a quarter of a century later, with the ever changing environment of examinations, the book too reinvents itself while being resolute to its core concept of providing the best content with easily understandable solutions.', ',', 10, 580, 0),
(77, 4, 28, 'assets/images/data_images/competitive2.jpg', 'Quantitative Aptitude for Competitive Examinations with A Mo', 'Quantitative Aptitude for Competitive Examinations with A Modern Approach to Logical Reasoning R S Agarwal S Chand Publishing 2018 -2019 Latest Edition', ',', 10, 820, 0),
(78, 4, 29, 'assets/images/data_images/textbook1.jpg', 'A New Combined Text Book of History & Civics for Class 6 Pap', 'A New Combined Text Book of History & Civics for Class 6 Paperback – 1', ',', 10, 600, 0),
(79, 4, 29, 'assets/images/data_images/textbook2.jpg', 'Bhasha Tarun - 6 (Text Book for Checkpoint, Pre IGCSE, MYP) ', 'This Book is ideal for Pre IGCSE, Checkpoint, MYP for Grade – 6 students. It comprehensively covers All language skills (LSRW) Listening, Speaking, Reading and Writing skills. This book will also students for their linguistic growth. About the author Author has more than 15 Years of experience of teaching international curriculums in India. The author has a specialized Doctorate with Hindi, Master in Education and a Gold Medalist in the University.', ',', 10, 600, 0),
(80, 4, 30, 'assets/images/data_images/hindi1.jpg', 'Adhunik Bharat Ka Itihas by Spectrum 2019-20 Edition (Brief ', 'Several books have been written by justly famous authors and historians of India’s struggle for freedom which is the major strand in any consideration of the history of modern India. But these volumes are extensive and in-depth studies, and often suffer from an overemphasis on one aspect at the cost of another. The present small effort, however, brings together various aspects of the turbulent period (from the arrival of the Europeans on Indian soil and the establishment of British rule in India to the day India won independence and the early years of freedom) in a systematic and succinct manner: major and important details and milestones are effectively discussed while several relevant but little known details are also highlighted. It is not just the mainstream freedom struggle that has been considered; the disparate efforts—small but significant—of several groups have also been discussed. The political and socio-economic developments that have influenced the growth of modern India have been dealt with in independent chapters. The endeavour has been to present complex and truly vast material in a brief and easy-to-understand manner, and we hope our readers find the book of use and interest. The present edition includes chapters on the advent of the Europeans in India and the British consolidation of power in India besides incorporating additional information under several chapters. There are also chapters on the challenges that a newly independent nation faced in the wake of a brutal Partition. The Nehru era is also briefly discussed.A new br>Chapter on India after Nehru has been added that discusses various developments under the governments that came after 1964. A survey of personalities associated with various movements, peasant and tribal movements, tables and charts are also given for quick reference.', ',', 10, 400, 0),
(81, 4, 30, 'assets/images/data_images/hindi2.jpg', 'Open-Eyed Meditations: Practical Wisdom for Everyday Life (H', 'Open-eyed Meditations is a beautiful compilation of thoughts wherein each meditation takes you on a journey to the past, bringing a secret herb to heal a problem of the present.\r\nA true distillation of ancient wisdom tips for modern lives, this unique self-help book uses the wisdom of the Ramayana and the Mahabharata to solve your everyday problems.Beyond the storyline, something deeper is waiting to be discovered from these ancient texts. This book is an attempt to uncover the hidden layer of wealth that is cleverly packaged within the commonly known storylines.', ',', 5, 200, 0),
(82, 5, 31, 'assets/images/data_images/television1.jpg', 'LG 80 cm (32 Inches) HD Ready Smart LED TV 32LM560BPTC (Dark', 'Resolution: HD Ready (1366x768) | Refresh Rate: 50 hertz | BackLight Module: Slim LED, Connectivity: 2 HDMI ports to connect set top box, Blu Ray players, gaming console | 1 USB ports to connect hard drives and other USB devices, Sound : 10 Watts Output, Smart TV Features: Amazon Prime Video | Web OS smart TV | Quick access | Quad core processor | Screen share | Web browser | Cloud photo and video  Expandable memory, Display : IPS panel | Active HDR | DTS virtual: X, Warranty Information: 1 Year LG India Comprehensive Warranty and additional 1 year Warranty is applicable on panel/module, Installation: For requesting installation/wall mounting/demo of this product once delivered, please directly call LG support on 1800 180 9999/1800 315 9999 and provide product\'s model name as well as seller\'s details mentioned on the invoice, Easy returns: This product is eligible for replacement within 10 days of delivery in case of any product defects, damage or features not matching the description provided, Country of Origin: India', ',', 5, 26990, 5),
(83, 5, 31, 'assets/images/data_images/television2.jpg', 'Sanyo 61 cm (24 Inches) Full HD LED TV XT-24S7000F (Black)', 'Resolution: Full HD (1920x1080) | Refresh Rate: 60 hertz, Connectivity: 1 HDMI ports to connect set top box, Blu Ray players, gaming console | 1 USB ports to connect hard drives and other USB devices | 1 VGA, Sound: 10 Watts Output, Display: Full HD | A+ display | Blur-free picture clarity, Warranty Information: 1 year warranty provided by Sanyo from date of purchase, Installation: For requesting installation/wall mounting/demo of this product once delivered, please directly call Sanyo support on 1-800-419-5088 and provide product\'s model name as well as seller\'s details mentioned on the invoice, Easy returns: This product is eligible for replacement within 10 days of delivery in case of any product defects, damage or features not matching the description provided', ',', 5, 9999, 5),
(84, 5, 32, 'assets/images/data_images/airconditioner1.jpg', 'Blue Star 1.5 Ton 3 Star Inverter Split AC (Copper IC318MATU', 'Blue Star-Nobody Cools Better, Split AC; 1.5 ton capacity, Energy Rating: 3 Star, Warranty: 1 year on product, 1 year on condenser, 10 years on compressor, Acoustic Jacket On Compressor, Remote-LCD With Back Lit, Night Glow Function On Remote Buttons, iFeel, Turbo Cool, Anti Corrosive Blue Fin Protection, Country of Origin: China', ',', 5, 55500, 5),
(85, 5, 32, 'assets/images/data_images/airconditioner2.jpg', 'Blue Star 1.5 Ton 5 Star Inverter Split AC (Copper IC518DBTU', 'Split AC; 1.5 ton capacity, Energy Rating: 5 Star, Warranty: 1 year on product, 1 year on condenser, 10 years on compressor, Precision cooling technology (0.5 degree), Brushless DC motor, Multi flitration stages, High cooling performance, Climate control, Powerful mode', ',', 5, 63000, 5),
(86, 5, 33, 'assets/images/data_images/refrigerator1.jpg', 'Bosch 327 L 3 Star Inverter Frost-Free Double Door Refrigera', 'Frost Free Refrigerator: Auto defrost function to prevent ice-build up, Capacity: 327 litres, Suitable for families with 3 – 4 members, Energy Rating: 3 Star, Manufacturer Warranty: 1 year on product, 10 years on compressor, Inverter Compressor: For greater efficiency, durability and silent operation, Shelf Type: Spill proof toughened glass, Also included in the box: 1 unit Refrigerator & 1 Unit user manual and egg tray, Special Features: Biggest fridge space ratio, Multi-Airflow Technology, VitaFresh Technology, Airfresh Filter', ',', 5, 35999, 5),
(87, 5, 33, 'assets/images/data_images/refrigerator2.jpg', 'Haier 52 L 3 Star ( 2019 ) Direct Cool Single Door Refrigera', 'Mini Refrigerator : Cools slower than larger refrigerators, 52 Ltr -Suitable for Bachelors, 2 Star:  energy efficiency, 1 year warranty on product ; 5 years warranty on compressor, Compressor Type : Non Inverter, 52L, 3 star, Stabilizer Free, Recess Handle, 5 Years Warranty', ',', 5, 11500, 5),
(88, 5, 34, 'assets/images/data_images/washing_machine1.jpg', 'Samsung 6.2 kg Fully-Automatic Top load Washing Machine (WA6', 'Installation: For requesting an installation/demo for this product once delivered, please contact_us on: [ 1800 40 7267864 ] and provide the product\'s model name, Fully-automatic top load washing machine; 6.2 kg, Warranty: 2 years comprehensive warranty on product and 4 years on motor, Design - door, color - PM grey, panel display - red LED, Feature - air turbo, auto restart, child lock, magic filter, tempered glass window, drum type - diamond drum, pulsator- center jet, water level - 5 levels, Cycle - blanket, delicates, eco tub clean, additional cycle - normal, quick wash, soak plus normal, number of cycle - 6, Physical specification - voltage/frequency- 220/50, drum material- SS', ',', 5, 15100, 5),
(89, 5, 34, 'assets/images/data_images/washing_machine2.jpg', 'Samsung 6.0 Kg Fully-Automatic 5 Star Front Loading Washing ', 'Fully-automatic front-loading washing machine; 6.0 kg, Energy Rating: 5 Star, Warranty: 3 years on product, 10 years on motor, Fully-automatic washing machines, Capacity 6.0 kg: Suitable for families with 4 to 5 members, Special features: hexa Storm pulsator, magic filter, spin timer, Pulsator, air turbo, super dry, 3 wash programs, soak', ',', 5, 21999, 5),
(90, 5, 35, 'assets/images/data_images/microwave1.jpg', 'Morphy Richards 20 L Solo Microwave Oven (20MWS, White)', 'Capacity: 20 litres power: 1200 watts, Flexi power settings: for microwave cooking, there are 6 power levels to choose from which ensures Efficient and fast cooking as desired, Overheat protection: an automatic shut-off control to protect the microwave itself, preventing overheating that can lead to mechanical or electrical failure, Defrost: makes Meat thawing process faster and hassle free, Large 245mm turntable: with 245 mm larger turntable, you can cook large quantity of food items, or reheat multiple food pots with ease in one go, Warranty: 2 years on product, 30 minutes timer: with a 30 minimum timer, the Morphy Richards microwave oven helps prevents mishaps like over or under cooking', ',', 5, 7995, 5),
(91, 5, 35, 'assets/images/data_images/microwave2.jpg', 'Bajaj 17 L Solo Microwave Oven (1701 MT, White)', '17L Capacity: Suitable for bachelors or small families, Solo: Can be used for reheating, defrosting and cooking, TIMER/CLOCK: Yes, Brand does not provide a starter kit with this product, Control: Jog dials that are easy to use with a long life, Cooking complete alarm , Power Consumption: 1200 watts, Power Levels - Choose between 5 different power levels to cook as per your need.This feature allows you to control the temperature as per the cooking requirement, 30 minutes cooking time alarm', ',', 5, 6500, 5),
(92, 5, 36, 'assets/images/data_images/home1.jpg', 'boAt AAVANTE Bar 1700D 120W 2.1 Channel Bluetooth Soundbar w', '1 year warranty from the date of purchase. For other product related queries please contact us at +912249461882 or info@imaginemarketingindia.com, Its 2.1 Channel captivating sound with Dolby Digital / Digital Plus technology gets you set for an alternate dimension, with an ethereal sound quality that adds colour to your audio as well as visual experience, The sleek and premium styled soundbar adds to the beauty of your home as much as it adds to the immersive auditory experience, Control your playback via easy operational controls and the master remote control device, All your devices are made accessible by the wireless and wired forms of connectivity, such as Bluetooth V5.0/AUX/USB/Optical and HDMI(ARC) with the versatile Aavante 1700D, Experience the boAt Signature sound with the 60W R.M.S premium audio delivered by AAVANTE BAR 1700D and its 60W Wired Subwoofer, The soundbar is apt for multiple forms of entertainment as it offers different modes such as NEWS, MOVIES, MUSIC and 3D for a true listening experience.', ',', 5, 19990, 5),
(93, 5, 36, 'assets/images/data_images/home2.jpg', 'boAt AAVANTE Bar 1190 90W 2.2 Channel Bluetooth Soundbar, Bu', '1 year warranty from the date of purchase. For other product related queries please contact us at +912249461882 or info@imaginemarketingindia.com, Its 2.2 Channel captivating sound gets you set for an alternate dimension, with an ethereal sound quality that adds colour to your audio as well as visual experience, The exquisitely styled soundbar with its premium finish adds to the beauty of your home as much as it adds to the immersive auditory experience, Control your playback via easy operational controls and the master remote control device, All your devices are made accessible by the wireless and wired forms of connectivity, such as Bluetooth V5.0/AUX/USB/Coaxial and HDMI(ARC) with the versatile Aavante Bar 1190, Experience the boAt Signature sound with the 40W R.M.S premium audio delivered by AAVANTE BAR 1190 and its 50W Built-in Active Subwoofer, The soundbar is apt for multiple forms of entertainment as it offers different modes such as NEWS, MOVIES, MUSIC and 3D for a true listening experience.', ',', 5, 14990, 5),
(94, 5, 37, 'assets/images/data_images/speaker1.jpg', 'Sony SRS-XB23 Wireless Extra Bass Bluetooth Speaker with 12 ', 'Extra Bass: Enjoy deep, punchy sound wherever you like with Sony EXTRA BASS speaker, Dustproof, waterproof and washable: With an IP67 ratings, you can take your speaker to the beach, the SRS-XB23 will keep on playing, Long battery life: Up to 12 hours of battery life means the SRS-XB23 can party even longer than you can. Even in EXTRA BASS mode, you can party for up to 10 hours, Built-In Mic: The mic function offers a convenient way to talk hands-free, whether it\'s a conference call for work or a chat with friends, Party Connect: Enjoy the same song, multiplied, with Party Connect. Connect to 100 compatible wireless speakers with BLUETOOTH technology, X-Balanced Speaker Unit: Developed by Sony, the X-Balanced Speaker Unit achieves sound quality and powerful sound pressure for a richer, deeper, more rewarding listening experience, Voice Assistant: Supports Google Assistant and Siri via voice assistant features for convenient use, USB Type-C Charging: Exceptional battery back-up with type C charging for uninterrupted music, Portability: Whether you\'re camping with friends or relaxing in the park, the compact and lightweight SRS-XB23 fits into your plans as easily as it fits into your bag.', ',', 5, 10990, 5),
(95, 5, 37, 'assets/images/data_images/speaker2.jpg', 'Sony SRS-XB33 Wireless Extra Bass Bluetooth Speaker with 24 ', 'Extra Bass: Enjoy deep, punchy sound wherever you like with your EXTRA BASS speaker, Dustproof, waterproof and washable: With an IP67 ratings, you can take your speaker to the beach, the SRS-XB33 will keep on playing, Long battery life: Up to 24 hours of battery life means the SRS-XB33 can party even longer than you can. Even in EXTRA BASS mode, you can party for up to 14 hours, Built-In Mic: The mic function offers a convenient way to talk hands-free, whether it\'s a conference call for work or a chat with friends, Party Connect: Enjoy the same song, multiplied, with Party Connect. Connect up to 100 compatible wireless speakers with BLUETOOTH technology, X-Balanced Speaker Unit: Developed by Sony, the X-Balanced Speaker Unit achieves sound quality and powerful sound pressure for a richer, deeper, more rewarding listening experience, Voice Assistant: Supports Google Assistant and Siri via voice assistant features for convenient use, USB Type-C Charging: Exceptional battery back -up with type C charging for uninterrupted music, Fiestable app Support: Download the Fiestable app and you\'ll be able to control the lighting on the SRS-XB33, as well as using motion-control gestures on your smartphone screen.', ',', 5, 15990, 5),
(96, 5, 38, 'assets/images/data_images/headphones1.jpg', 'Cosmic Byte GS410 Headphones with Mic and for PS4, Xbox One,', 'Primary kind of gaming headset, perfect for playing games, listening music, etc. Single 3.5mm Jack for sound and mic, Soft cushion head-pad and ear-pad, as well as adjustable length hinges guarantee hours of gaming comfort, Delivers clear sound and deep bass for real game, little smart in-line Remote Control for sound and mic, Flexible microphone for exact positioning and mic with great sensitivity at picking up sounds, your partner can hear your words clearly, For any product related queries contact_us on: [1800 31300 7700]', ',', 5, 1149, 5),
(97, 5, 38, 'assets/images/data_images/headphones2.jpg', 'truke Fit Pro in-Ear True Wireless Bluetooth Headphones (TWS', 'NNOVATIVE DOLPHIN DESIGN: truke Fit Pro Bluetooth Earphones Unique Dolphin Shape Design are Ergonomically Engineered for Maximum Comfort. Earbuds fit Perfectly & Securely regardless of your Ear Shape & Size, ALL DAY BATTERY : truke Fit Pro Wireless Earbuds can provide up to 24hrs. of Total Music Playback with 500mAh Charging Case with Type-C Universal Charging Interface. Just Charge the Earbuds for 15mins and play for 1hour, Never miss your Important call or Favorite Part of Movie, INSTANT PARING SEAMLESS CONNECTION: These Bluetooth Wireless Headphones comes with Bluetooth 5.0. Advanced Bluetooth Earphone technology ensures Instant Pairing and Steady Wireless Connection upto 30 feet, 1 YEAR WARRANTY : truke Fit Pro Wireless Earphones come with 1 Year Warranty from date of purchase. Mandatory registration is required on truke website within 10 days of purchase., DEEP BASS: With 13mm Dynamic Driver Speakers truke Fit Pro Wireless Earbuds provides Crisp & Clear Sound with Deep Bass, STYLE WITH SLING : Always Carry Your Buds Along With Style, VOICE ASSISTANT : Access Google, Siri directly from truke Fit Pro Wireless Earphone by Pressing Left Earbud Button Twice, MULTI FUNTION BUTTON (MFB): truke Fit Pro True Wireless Headset provides Single Press Operation to Manage Calls and Music on any Bluetooth enabled Android device, iPhone, etc.', ',', 5, 4999, 5),
(98, 5, 39, 'assets/images/data_images/camera1.jpg', 'Panasonic LUMIX G7 16.00 MP 4K Mirrorless Interchangeable Le', 'PROFESSIONAL PHOTO AND VIDEO PERFORMANCE: 16 megapixel Micro Four Thirds sensor with no low pass filter to confidently capture sharp images with a high dynamic range and artifact free performance; WiFi enabled, 4K VIDEO CAPTURE: 4K QHD video recording (3840 x 2160) with three 4K Ultra HD capture at 25p and Full HD ( 1920 * 1080 ) at 50p, 4K PHOTO MODE : 8MP PHOTO BURST MODE at 30 fps, extracts individual high resolution images from 4K Ultra HD video filmed at 30 frames per second to capture split second moments, INTUITIVE CONTROLS: Easily control aperture and shutter settings with the front and rear dials while making white balance and ISO adjustments on the fly; Assign favorite settings to any of the six function buttons (six on body, five on menu), HIGH RESOLUTION VIEWFINDER AND LCD DISPLAY: High resolution (2,360K dot) OLED Live View Finder and rear touch enabled 3 inch tilt/swivel LCD display (1,040 dot) are clear even in bright sunlight, CONNECTIVITY AND PORTS: 3.5mm external mic port, 2.5mm remote port, USB 2.0 and micro HDMI Type D; Compatible with newer UHS I/UHS II SDXC/SDHC SD cards capable of storing high resolution 4K videos, Manufacturer Detail: Panasonic AVC Networks Xiamen Co. Ltd., Hi-Tech Industrial Development Zone, Xiamen, Fujian, China, Packer Detail: Panasonic AVC Networks Xiamen Co. Ltd., Hi-Tech Industrial Development Zone, Xiamen, Fujian, China, Importer Details: Panasonic India Pvt Ltd, 12th Floor, Ambience Tower, Ambience Island, NH-8, Gurgaon - 122002, Haryana, India, Country of Origin: China', ',', 5, 49770, 5),
(99, 5, 39, 'assets/images/data_images/camera2.jpg', 'Canon EOS 1500D 24.1 Digital SLR Camera (Black) with EF S18-', 'Sensor: APS-C CMOS Sensor with 24.1 MP (high resolution for large prints and image cropping), ISO: 100-6400 sensitivity range (critical for obtaining grain-free pictures, especially in low light), Image Processor: DIGIC 4+ with 9 autofocus points (important for speed and accuracy of autofocus and burst photography), Video Resolution: Full HD video with fully manual control and selectable frame rates (great for precision and high-quality video work), Connectivity: WiFi, NFC and Bluetooth built-in (useful for remotely controlling your camera and transferring pictures wirelessly as you shoot), Lens Mount: EF-S mount compatible with all EF and EF-S lenses (crop-sensor mount versatile and compact, especially when used with EF-S lenses), For any queries, please contact_us on: [1860-180-3366], Country of Origin: Taiwan', ',', 5, 34994, 5),
(100, 6, 40, 'assets/images/data_images/gamingc1.jpg', 'GSH Sup Video Game With Battery Handheld Console Classic Ret', 'Easy to use, Perfect gift for children, retro game lovers, collectors, etc, Play Longer: 1000mAh rechargeable lithium battery, support up to 3-4 hours of continuous game play, Built-in 400 Classical Games: It\'s a good gift for your children, friends, both boys and girls will like it, Adapted AV Cable: Applicable to TV system, so that you can easily enjoy vivid and exciting games on a big screen.', ',', 5, 1999, 5),
(101, 6, 40, 'assets/images/data_images/gamingc2.jpg', 'Techcom Infosys 400 in 1 Sup Game Box Rechargable Console LE', '400 RETRO CLASSIC GAMES -- The Game Console Built-in 400 classic games such as Red Fortress, Contra, Tank Wars, Tetris, etc.Taking you back to your happy childhood, USB RECHARGEABLE -- Built-in 800mAh lithium battery,come with a rechargeable lithium battery and a USB cable , charging time is about 1.5 hours,4 hours of continuous game play, EASY TO CARRY -- 3-inch color screen, the surface is made of leather, comfortable touch,small and easy to carry,convenient size and lightweight, perfect for playing travel or on the go, Colors :- Red: Black : White : Blue : Yellow, Random Colour will be sent', ',', 5, 1899, 5),
(102, 6, 41, 'assets/images/data_images/gaminga1.jpg', 'ZEBRONICS Gaming Multimedia USB Keyboard & USB Mouse Combo -', 'Multicolor LED ( 4 modes - 3 light modes and 1 off mode ),Integrated media control Windows key Disable / Enable function, All Keys Disable / Enable function , All Keys Disable / Enable function, 2-steps stand design, Laser keycaps, Aluminum body, Backlight LED On / Off function, Braided cable, high quality USB connector', ',', 5, 1999, 5),
(103, 6, 41, 'assets/images/data_images/gaminga2.jpg', 'Cosmic Byte H11 Gaming Headset with Microphone (Black/Purple', 'Driver size: 40mm, impedance: 32 ohms, Suitable for PC, laptops, mobiles, PS4, Xbox One\r\nSingle 3.5 mm audio mic jack, Volume rocker, 1 year warranty for manufacturing defects', ',', 5, 1099, 0),
(104, 6, 42, 'assets/images/data_images/digitals1.jpg', 'Digital Design | With an Introduction to the Verilog HDL, VH', 'A modern update to a classic, authoritative text, Digital Design, 6th Edition teaches the fundamental concepts of digital design in a clear, accessible manner. The text presents the basic tools for the design of digital circuits and provides procedures suitable for a variety of digital applications. Like the previous editions, this edition of Digital Design supports a multimodal approach to learning, with a focus on digital design, regardless of language. Recognizing that three public-domain languages Verilog, VHDL and System Verilog all play a role in design flows for today’s digital devices, the book offers parallel tracks of presentation of multiple languages.', ',', 5, 657, 0);
INSERT INTO `data` (`id`, `category_id`, `sub_category_id`, `image`, `brand`, `details`, `sizes`, `stock`, `price`, `discount`) VALUES
(105, 6, 42, 'assets/images/data_images/digitals2.jpg', 'Co-Synthesis of Hardware and Software for Digital Embedded S', 'Co-Synthesis of Hardware and Software for Digital Embedded Systems, with a Foreword written by Giovanni De Micheli, presents techniques that are useful in building complex embedded systems. These techniques provide a competitive advantage over purely hardware or software implementations of time-constrained embedded systems. Recent advances in chip-level synthesis have made it possible to synthesize application-specific circuits under strict timing constraints. This work advances the state of the art by formulating the problem of system synthesis using both application-specific as well as reprogrammable components, such as off-the-shelf processors. Timing constraints are used to determine what part of the system functionality must be delegated to dedicated application-specific hardware while the rest is delegated to software that runs on the processor. This co-synthesis of hardware and software from behavioral specifications makes it possible to realize real-time embedded systems using off-the-shelf parts and a relatively small amount of application-specific circuitry that can be mapped to semi-custom VLSI such as gate arrays. The ability to perform detailed analysis of timing performance provides the opportunity of improving the system definition by creating better phototypes. Co-Synthesis of Hardware and Software for Digital Embedded Systems is of interest to CAD researchers and developers who want to branch off into the expanding field of hardware/software co-design, as well as to digital system designers who are interested in the present power and limitations of CAD techniques and their likely evolution.', ',', 5, 3582, 0),
(106, 6, 43, 'assets/images/data_images/educationals1.jpg', 'AppBook ERP for Educational Institute/Coaching Management So', 'Format: License Key and Valid Login credential, OS: Run on Any OS, Ultra fast and secure, Mobile Friendly, Unlimited user, Unlimited Student, Warranty: Seller 1 Year from date of purchase, Features:Enquiry with Follow-up,Registration,Admission,Transport Management,Complain with Follow-up,I card,Attendance,Staff Login,Expense,Financial Report,Schedule Reminder,Mobile APP,Unlimited Login,Personalized Domain,Exam Module,Document Issue Management', ',', 5, 35500, 5),
(107, 6, 43, 'assets/images/data_images/educationals2.jpg', 'Optimum Eduactors Std 11 CBSE/NCERT - Biology 1 & 2 Pack Edu', '60 Hours of Detailed Explanation, Concept Building, helps you understand and build all concepts covered from the CBSE/NCERT text books, Learning made simple & easy, Learn at your own pace and get excellent results, Economically priced, save time & Effort, Follow the Simple procedure 1) Insert the pendrive & open the optimum folder to view the instructions video 2) Download the optimum Player on your PC Laptop 3) Put the LOGIN ID to Activate the product. Contact us on 9870002226 if any Help required', ',', 5, 5999, 5);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `username` varchar(60) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_size` varchar(25) NOT NULL,
  `item_amount` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `payment_method` varchar(25) NOT NULL,
  `order_date` datetime NOT NULL,
  `delivery_date` datetime NOT NULL,
  `is_delivered` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `username`, `item_id`, `item_size`, `item_amount`, `price`, `payment_method`, `order_date`, `delivery_date`, `is_delivered`) VALUES
(1, 'chandan_sharma', 1, '39', 1, 509, 'cash_on_delivery', '2020-08-31 19:15:27', '2020-08-31 19:15:43', 'return'),
(2, 'chandan_sharma', 1, '39', 1, 509, 'cash_on_delivery', '2020-08-31 19:28:33', '2020-08-31 20:28:33', 'cancel'),
(3, 'chandan_sharma', 1, '39', 1, 509, 'cash_on_delivery', '2020-08-31 19:28:35', '2020-08-31 20:28:35', 'cancel'),
(4, 'chandan_sharma', 1, '40', 1, 509, 'cash_on_delivery', '2020-09-01 09:30:13', '2020-09-01 10:30:13', 'cancel'),
(5, 'chandan_sharma', 1, '39', 1, 509, 'cash_on_delivery', '2020-09-01 10:31:15', '2020-09-01 10:32:34', 'yes');

-- --------------------------------------------------------

--
-- Table structure for table `sub_categories`
--

CREATE TABLE `sub_categories` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `sub_categories`
--

INSERT INTO `sub_categories` (`id`, `category_id`, `name`, `image`) VALUES
(1, 1, 'Shirts', 'assets/images/men_fashion/shirt_men.png'),
(2, 1, 'T-shirts', 'assets/images/men_fashion/tshirt_men.png'),
(3, 1, 'Tracks and joggers', 'assets/images/men_fashion/sport_wear_men.png'),
(4, 1, 'Footwear', 'assets/images/men_fashion/footwear_men.png'),
(5, 1, 'Watches', 'assets/images/men_fashion/watch_men.png'),
(6, 1, 'Sunglasses', 'assets/images/men_fashion/glasses_men.png'),
(7, 1, 'Innerwears', 'assets/images/men_fashion/innerwear_men.png'),
(8, 2, 'Shirts', 'assets/images/women_fashion/shirt_w.png'),
(9, 2, 'T-shirts', 'assets/images/women_fashion/tshirt_w.png'),
(10, 2, 'Tracks and joggers', 'assets/images/women_fashion/sport_wear_w.png'),
(11, 2, 'Footwear', 'assets/images/women_fashion/footwear_w.png'),
(12, 2, 'Watches', 'assets/images/women_fashion/watch_w.png'),
(13, 2, 'Sunglasses', 'assets/images/women_fashion/glasses_w.png'),
(14, 2, 'Innerwears', 'assets/images/women_fashion/innerwear_w.png'),
(15, 3, 'Mobiles', 'assets/images/mobile_and_computers/mobile.png'),
(16, 3, 'Mobile Accessories', 'assets/images/mobile_and_computers/mobile_accessories.png'),
(17, 3, 'Power bank', 'assets/images/mobile_and_computers/power_bank.png'),
(18, 3, 'Laptops', 'assets/images/mobile_and_computers/laptop.png'),
(19, 3, 'Tablets', 'assets/images/mobile_and_computers/tablet.png'),
(20, 3, 'Desktop', 'assets/images/mobile_and_computers/computer.png'),
(21, 3, 'Computer Accessories', 'assets/images/mobile_and_computers/computer_accessories.png'),
(22, 3, 'Pen drive and Storage', 'assets/images/mobile_and_computers/pen_drive.png'),
(23, 3, 'Office supplies and Stationary', 'assets/images/mobile_and_computers/office_supplies.png'),
(24, 3, 'Wearable devies', 'assets/images/mobile_and_computers/wearable_device.png'),
(25, 4, 'Science fiction Books', 'assets/images/books/fictional_book.png'),
(26, 4, 'School Textbooks', 'assets/images/books/school_textbook.png'),
(27, 4, 'Children\'s Books', 'assets/images/books/children_book.png'),
(28, 4, 'Exam Central', 'assets/images/books/competitive_book.jpg'),
(29, 4, 'Textbooks', 'assets/images/books/textbook.png'),
(30, 4, 'Indian language Books', 'assets/images/books/indian_language_book.jpg'),
(31, 5, 'Television', 'assets/images/home_appliances/television.png'),
(32, 5, 'Air Conditioner', 'assets/images/home_appliances/air_conditioner.png'),
(33, 5, 'Refrigerator', 'assets/images/home_appliances/refrigerator.png'),
(34, 5, 'Washing Machine', 'assets/images/home_appliances/washing_machine.png'),
(35, 5, 'Microwave', 'assets/images/home_appliances/microwave.png'),
(36, 5, 'Home Entertainment System', 'assets/images/home_appliances/home_entertainment_system.png'),
(37, 5, 'Speaker', 'assets/images/home_appliances/speaker.png'),
(38, 5, 'Headphones', 'assets/images/home_appliances/headphones.png'),
(39, 5, 'Camera', 'assets/images/home_appliances/camera.png'),
(40, 6, 'Gaming Consoles', 'assets/images/games_accessories/gaming_console.png'),
(41, 6, 'Gaming Accessories', 'assets/images/games_accessories/gaming_accessories.png'),
(42, 6, 'Digital Softwares', 'assets/images/games_accessories/digital_software.png'),
(43, 6, 'Educational Softwares', 'assets/images/games_accessories/educational_software.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(25) NOT NULL,
  `password` varchar(200) NOT NULL,
  `address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `username`, `email`, `mobile`, `password`, `address`) VALUES
(1, 'Chandan Sharma', 'chandan_sharma', 'nishakar561@gmail.com', '9807228008', '$2y$10$HNMQqvGQ1pW/m5VLxptcw.v6iGEZK50A0uySf4SySPjtFafpVExpS', 'House no. 102, Avas vikas colony, Hardoi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `categories`
--
ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sub_categories`
--
ALTER TABLE `sub_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54;

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `sub_categories`
--
ALTER TABLE `sub_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
