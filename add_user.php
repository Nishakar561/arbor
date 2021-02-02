<?php
    include("config.php");

    $name = $_POST['name'];
    $email = $_POST['email'];
    $mobile = $_POST['mobile'];
    $password = $_POST['password'];
    $password = password_hash($password,PASSWORD_BCRYPT);
    $address = $_POST['address'];
    $username = "";
    $output = array();

    $numberRegex = '/^([6-9]{1})([0-9]{9})$/';
    $emailRegex = '/^([a-zA-Z0-9_\-\.]+)@([a-zA-Z0-9_\-\.]+)\.([a-zA-Z]{2,5})$/';

    if(preg_match($numberRegex, $mobile)){ 
        if(preg_match($emailRegex, $email)){
            $check_number = mysqli_query($con,"SELECT * FROM users WHERE mobile='$mobile'");
        
            if(mysqli_num_rows($check_number) == 0){

                $check_email = mysqli_query($con,"SELECT * FROM users WHERE email='$email'");

                if(mysqli_num_rows($check_email) == 0){
                    $name = strtolower($name);
                    $array = explode(" ",$name);
                    $name = "";

                    foreach($array as $value){
                        $name .= ucfirst($value)." ";
                        $username .= $value."_"; 
                    }

                    $name = trim($name," ");
                    $username = trim($username,"_");

                    $check_user_exists = mysqli_query($con,"SELECT * FROM users WHERE username='$username'");
                    $result = mysqli_num_rows($check_user_exists);

                    $i=1;
                    $temp = $username;
                    while($result != 0){
                        $temp = $username."_".$i;

                        $check_user_exists = mysqli_query($con,"SELECT * FROM users WHERE username='$username'");
                        $result = mysqli_num_rows($check_user_exists);
                        $i++;
                    }

                    $st = mysqli_query($con,"INSERT INTO users VALUES('','$name','$username','$email','$mobile','$password','$address')");
                    
                    $check_user = mysqli_query($con,"SELECT id,name,email,username,mobile,address FROM users WHERE username='$username'");

                    $row = mysqli_fetch_assoc($check_user);
                    
                    $output["isSuccess"] = 1;
                    $output["message"] = "Register Success";
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
        } else {
            $output["isSuccess"] = 0;
            $output["message"] = "incorrect email pattern";
            echo json_encode($output);
        }
    } else {
            $output["isSuccess"] = 0;
            $output["message"] = "incorrect number pattern";
            echo json_encode($output);
        }

    $con->close();
?>