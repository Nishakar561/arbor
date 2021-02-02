<?php
    include("config.php");
    $output="";
    $category_id = $_POST['categoryId'];

    $sub_categories = mysqli_query($con,"SELECT id,name FROM sub_categories WHERE category_id='$category_id'");
    $output .= "<option value='' disabled selected> Select subcategory </option>";
    
    while($row = mysqli_fetch_assoc($sub_categories)){
        $output .= "<option value=".$row['id'].">". $row['name']."</option>";
    }

    echo $output;
?>