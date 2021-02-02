<?php
    include("config.php");
    if(isset($_POST['loginButton'])){
        $username = $_POST['username'];
        $password = $_POST['password'];


        $check_admin = mysqli_query($con,"SELECT username FROM admin WHERE username='$username' AND password='$password'");

        if(mysqli_num_rows($check_admin) != 0){
            $_SESSION['username'] = $username;
            header("location: index.php");
        } else{
            array_push($error_array,"Username or password is incorrect");
            header("location: admin_login_page.php");
        }
    }
?>