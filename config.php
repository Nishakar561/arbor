<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname="arbor";


// Set default time zone
$timezone  = date_default_timezone_set("Asia/Calcutta");

// Create connection
$con = mysqli_connect($servername, $username, $password, $dbname);

// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $connect->connect_error);
} 
?>