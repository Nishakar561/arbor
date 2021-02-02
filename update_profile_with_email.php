<?php
    include("config.php");

    $name = $_POST['name'];
    $email = $_POST['email'];
    $mobile = $_POST['mobile'];
    $address = $_POST['address'];
    $username = $_POST['username'];
    $output = array();


    $check_mobile = mysqli_query($con,"SELECT * FROM users WHERE mobile='$mobile'");

    if(mysqli_num_rows($check_mobile) != 0){
        $update_profile = mysqli_query($con,"UPDATE users SET name='$name',email='$email',address='$address' WHERE username='$username'");

        $check_user = mysqli_query($con,"SELECT id,name,email,username,mobile,address FROM users WHERE username='$username'");
        $row = mysqli_fetch_assoc($check_user);
        
        $output["isSuccess"] = 1;
        $output["message"] = "Upadate Success";
        $output["id"] = $row["id"];
        $output["name"] = $row["name"];
        $output["email"] = $row["email"];
        $output["username"] = $row["username"];
        $output["mobile"] = $row["mobile"];
        $output["address"] = $row["address"];
        echo json_encode($output);                

    } else {
        $output["isSuccess"] = 0;
        $output["message"] = "email already exists";
        echo json_encode($output);
    }
?>