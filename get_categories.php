<?php 
    include("config.php");

    $get_categories = mysqli_query($con, "SELECT * FROM categories");
    $result_array = array(); 

    while($row = mysqli_fetch_assoc($get_categories)){
        $result_array[] = $row;
    }

    echo json_encode($result_array);

    $con->close();
?>