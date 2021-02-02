<?php 
    include("config.php");

    $id = $_GET["id"];

    $get_sub_categories = mysqli_query($con,"SELECT * FROM sub_categories WHERE category_id='$id'");
    $result_array = array();

    while($row = mysqli_fetch_assoc($get_sub_categories)){
        $result_array[] = $row;
    }

    echo json_encode($result_array);

    $con->close();
?>