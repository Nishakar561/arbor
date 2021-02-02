<?php 
    include("config.php");

    $name = $_POST['name'];
    $address = $_POST['address'];
    $username = $_POST['username'];
    $output = array();

    $set_values = mysqli_query($con,"UPDATE users SET name='$name',address='$address' WHERE username='$username'");

    $affected_row = mysqli_affected_rows($con);

    if($affected_row){
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
    } else{
        $output['isSuccess'] = 0;
        $output['message'] = 'Updation failed.';
        echo json_encode($output);
    }

    $con->close();
?>