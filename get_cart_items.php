<?php 
    include("config.php");
    
    $username = $_POST['username'];
    $output = array();
    $final_array = array();

    $get_cart_items = mysqli_query($con,"SELECT * FROM cart WHERE username='$username'");

    while($row = mysqli_fetch_assoc($get_cart_items)){
        
        $item_id = $row['item_id'];
        $get_image_and_brand = mysqli_query($con,"SELECT brand,image FROM data WHERE id='$item_id'");
        $row1 = mysqli_fetch_assoc($get_image_and_brand);

        $output['id'] = $row['id'];
        $output['item_id'] = $row['item_id'];
        $output['item_size'] = $row['item_size'];
        $output['item_amount'] = $row['item_amount'];
        $output['price'] = $row['price'];
        $output['brand'] = $row1['brand'];
        $output['image'] = $row1['image'];

        $final_array[] = $output;
    }

    echo json_encode($final_array);

    $con->close();
?>