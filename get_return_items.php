<?php
    include("config.php");

    $username = $_POST['username'];
    $is_delivered = "yes";
    $output = array();
    $final_array = array();

    $get_orders = mysqli_query($con, "SELECT * FROM orders WHERE username='$username' AND is_delivered='$is_delivered'");

    while($row = mysqli_fetch_assoc($get_orders)){
        $output['delivery_date'] = $row['delivery_date'];
        $output['id'] = $row['id'];
        $output['is_delivered'] = $row['is_delivered'];
        $output['item_amount'] = $row['item_amount'];
        $output['item_id'] = $row['item_id'];
        $output['item_size'] = $row['item_size'];
        $output['order_date'] = $row['order_date'];
        $output['payment_method'] = $row['payment_method'];
        $output['price'] = $row['price'];
        $output['username'] = $row['username'];
        
        $item_id = $row['item_id'];

        $get_image = mysqli_query($con,"SELECT brand,image FROM data WHERE id='$item_id'");
        $row1 = mysqli_fetch_assoc($get_image);
        $output['brand'] = $row1['brand'];
        $output['image'] = $row1['image'];

        $final_array[] = $output;
    }

    echo json_encode($final_array);

    $con->close();
?>