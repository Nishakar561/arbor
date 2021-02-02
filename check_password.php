<?php
    include("config.php");

    $email = $_POST['email'];
    $password = $_POST['password'];
    $output = array();

    $check_password = mysqli_query($con,"SELECT * FROM users WHERE email='$email'");
    $row = mysqli_fetch_assoc($check_password);


    if(password_verify($password , $row['password'])){
        $output['isSuccess'] = 1;
        $output['message'] = "Change password";
        echo json_encode($output);
    } else{
        $output['isSuccess'] = 0;
        $output['message'] = "Your password was wrong";
        echo json_encode($output);
    }
?>