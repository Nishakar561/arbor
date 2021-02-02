<?php
    include("config.php");

    $category_id = $_GET['category_id'];
    $sub_category_id = $_GET['sub_category_id'];

    $get_data = mysqli_query($con,"SELECT * FROM data WHERE category_id='$category_id' AND sub_category_id='$sub_category_id'");
    $sizes = array();
    $result_array = array();
    
    while($row = mysqli_fetch_assoc($get_data)){
        
        if($row['sizes'] != ","){
            $string = substr($row['sizes'],1);
            
            $sizes = explode(",",$string);
        }

        $row['sizes'] = $sizes;

        $result_array[] = $row;
    }

    echo json_encode($result_array);

    $con->close();
?>