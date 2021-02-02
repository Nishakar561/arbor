<?php 
    include("config.php");
    include("assets/css/links.php");
    session_start();
    ?>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <link rel="stylesheet" href="assets/css/style.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
    <body>
    <?php
        if(!isset($_SESSION['form'])){
            echo "<div class='heading text-center text-uppercase text-white'>
            Session expired <br /> 
            <a href='index.php' class='btn btn-danger text-center'> Goto admin page</a>
            </div>";
        }
        else if($_SESSION['form'] == "delete"){
            $id = $_SESSION['id'];
            unset($_SESSION['id']);
            unset($_SESSION['form']);

            mysqli_query($con,"DELETE FROM users WHERE id='$id'");
            header("location: index.php");
        }
    ?>        
    </body>
</html>
