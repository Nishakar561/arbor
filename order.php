<?php
    include("config.php");

    $decode = json_decode($_POST['cartItemList'],true);

    $username = $decode['username'];
    $cart_items = $decode['cartItemList'];
    $payment_method = $decode['payment_method'];
    $order_date = date("Y/m/d H:i:s");
    $delivery_date = date("Y/m/d H:i:s",strtotime("+1 hour"));
    $order_time = date("H:i:s");
    $closing_time = date("21:00:00");
    $output = array();
    $id = "";


    $order_time = new DateTime($order_time);
    $closing_time = new DateTime($closing_time);
    $interval = $order_time->diff($closing_time);
    $time = ($interval-> h *60) + $interval->i;

    if($time <= 0){
        $output['isSuccess'] = 0;
        $output['message'] = "You cannot order after 9 pm";
    } else{
        foreach($cart_items as $array){
        
            $cart_id = $array['id'];
            $item_id = $array['item_id'];
            $item_size = $array['item_size'];
            $item_amount = $array['item_amount'];
            $price = $array['price'];
            $is_delivered = 'no';

            $order_items = mysqli_query($con,"INSERT INTO orders VALUES('','$username','$item_id','$item_size','$item_amount','$price','$payment_method','$order_date','$delivery_date','$is_delivered')");

            $get_stock = mysqli_query($con,"SELECT stock FROM data WHERE id='$item_id'");
            $st = mysqli_fetch_assoc($get_stock);
            $stock = $st['stock'];
            
    
            $stock -= $item_amount;
    
            $update_stock = mysqli_query($con,"UPDATE data SET stock='$stock' WHERE id='$item_id'");
    
            $delete_from_cart = mysqli_query($con,"DELETE FROM cart WHERE id='$cart_id'");
        }
    
        $affected_row = mysqli_affected_rows($con);
    
        if($affected_row){
            $output['isSuccess'] = 1;
            $output['message'] = 'orderd successfully.';
            echo json_encode($output);       
        } else{
            $output['isSuccess'] = 0;
            $output['message'] = 'order failed';
            echo json_encode($output);
        }
    }

    $con->close();
?>