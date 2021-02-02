<?php 
    include("config.php");

    $name = $_POST['name'];
    $email = $_POST['email'];
    $mobile = $_POST['mobile'];
    $address = $_POST['address'];
    $username = $_POST['username'];
    $output = array();

    $check_number = mysqli_query($con,"SELECT * FROM users WHERE mobile='$mobile'");
        
            if(mysqli_num_rows == 0){
                $check_email = mysqli_query($con,"SELECT * FROM users WHERE email='$email'");

                if(mysqli_num_rows($check_email) == 0){
                    $update_profile = $con->prepare("UPDATE users SET name='$name',email='$email',mobile='$mobile',address='$address' WHERE username='$username'");
                    

                    $check_user = $con->prepare("SELECT id,name,email,username,mobile,address FROM users WHERE username='$username'");
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
            } else {
                $output["isSuccess"] = 0;
                $output["message"] = "number already exists";
                echo json_encode($output);
            }

    $con->close();
?>