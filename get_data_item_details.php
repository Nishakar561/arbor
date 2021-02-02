<?php 
    include("config.php");
    $id = $_GET['id'];

    $get_item_details = mysqli_query($con,"SELECT * FROM data WHERE id='$id'");

    $sizes = array();

    $row = mysqli_fetch_assoc($get_item_details);
    if($row['sizes'] != ","){
        $string = substr($row['sizes'],1);
        
        $sizes = explode(",",$string);
    }

    $row['sizes'] = $sizes;

    echo json_encode($row);

    $con->close();
?>