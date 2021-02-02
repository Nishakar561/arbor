<?php 
    include("config.php");

    $id = $_POST['id'];
    $is_delivered = "yes";
    $return = "return";
    $return_date = date("Y/m/d H:i:s");
    $output = array();

    $get_items = mysqli_query($con,"SELECT * FROM orders WHERE id='$id' AND is_delivered='$is_delivered'");
    $row = mysqli_fetch_assoc($get_items);
        
    $delivery_date = new DateTime($row['delivery_date']);
    $return_date = new DateTime($return_date);

    $interval = $delivery_date->diff($return_date);

    if($interval->h > 24){
        $output['isSuccess'] = 0;
        $output['message'] = "You can return within 24 hours of delivery";
        echo json_encode($output);
    } else{
        $return_order = mysqli_query($con,"UPDATE orders SET is_delivered='$return' WHERE id='$id'");

        $item_id = $row['item_id'];
        $item_amount = $row['item_amount'];

        $get_stock = mysqli_query($con,"SELECT stock FROM data WHERE id='$item_id'");
        $st = mysqli_fetch_assoc($get_stock);
        $stock = $st['stock'];
        
        $stock += $item_amount;

        $get_stock = mysqli_query($con,"UPDATE data SET stock='$stock' WHERE id='$item_id'");
        
        if($row['payment_method'] == "cash_on_delivery"){
            $output['isSuccess'] = 1;
            $output['message'] = "The item will be taken in 24 hours";
        } else{
            $output['isSuccess'] = 1;
            $output['message'] = "Your payment will be returned within 24 hours";
        }

        echo json_encode($output);
    }
?>