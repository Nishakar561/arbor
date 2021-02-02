<?php 
    include("config.php");

    $email = $_POST['email'];
    $password = $_POST['password'];
    $password = password_hash($password,PASSWORD_BCRYPT);
    
    $email_update = mysqli_query($con,"UPDATE users SET password='$password' WHERE email='$email'");
    $affected_row = mysqli_affected_rows($con);

    if($affected_row){
        $output['isSuccess'] = 1;
        $output['message'] = 'Password updated successfully';
        echo json_encode($output);       
    } else{
        $output['isSuccess'] = 0;
        $output['message'] = 'Password updation failed';
        echo json_encode($output);
    }

    $con->close();
?>