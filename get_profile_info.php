<?php 
    include("config.php");

    $get_profile_info = mysqli_query($con,"SELECT * FROM users WHERE id=?");
    $row = mysqli_fetch_assoc($get_profile_info);

    echo json_encode($row);

    $con->close();
?>