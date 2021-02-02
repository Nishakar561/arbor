<?php
    include("config.php");

    $mobile = $_POST["mobile"];
    $password = $_POST["password"];

    $output = array();
    $check_user = mysqli_query($con,"SELECT * FROM users WHERE mobile='$mobile'");

    if(mysqli_num_rows($check_user) != 0){
        $row = mysqli_fetch_assoc($check_user);

        if(password_verify($password , $row['password']))
        {
            $output["isSuccess"] = 1;
            $output["message"] = "Login Success";
            $output["id"] = $row["id"];
            $output["name"] = $row["name"];
            $output["username"] = $row["username"];
            $output["email"] = $row["email"];
            $output["mobile"] = $row["mobile"];
            $output["address"] = $row["address"];
            echo json_encode($output);
        } else{
            $output["isSuccess"] = 0;
            $output["message"] = "Wrong mobile or password";
            echo json_encode($output);
        }
    } else{
        $output["isSuccess"] = 0;
        $output["message"] = "Wrong mobile or password";
        echo json_encode($output);
    }

    $con->close();
?>