<?php

include("config.php");

$email = $_POST['email'];

$email_check = mysqli_query($con,"SELECT id FROM users WHERE email='$email'");
$output = array();

if($row = mysqli_fetch_assoc($email_check)){
    $to_email = $email;
    $otp = rand(1111,9999);

    $subject = "Arbor OTP varification";
    $body = "Your OTP is $otp enter it into the app.";
    $headers = "From: nishakar561@gmail.com";

    if (mail($to_email, $subject, $body, $headers)) {
        $output['isSuccess'] = 1;
        $output['id'] = $otp;
        $output['message'] = "OTP has been sent on mail.";
        echo json_encode($output);

    } else {
        $output['isSuccess'] = 0;
        $output['message'] = "Email sending failed";
        echo json_encode($output);
    }
}

$con->close();
?>