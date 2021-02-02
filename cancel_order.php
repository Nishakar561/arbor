<?php 
    include("config.php");

    $id = $_POST['id'];
    $is_delivered = "no";
    $cancel = "cancel";
    $cancel_date = date("Y/m/d H:i:s");
    $output = array();

    $get_items = mysqli_query($con,"SELECT * FROM orders WHERE id='$id' AND is_delivered='$is_delivered'");
    $row = mysqli_fetch_assoc($get_items);
        
    $order_date = new DateTime($row['order_date']);
    $cancel_date = new DateTime($cancel_date);

    $interval = $order_date->diff($cancel_date);

    if($interval->i > 5){
        $output['isSuccess'] = 0;
        $output['message'] = "You can canel within 5 min of order";
        echo json_encode($output);
    } else{

        $item_id = $row['item_id'];
        $item_amount = $row['item_amount'];

        $get_stock = mysqli_query($con,"SELECT stock FROM data WHERE id='$item_id'");
        $st = mysqli_fetch_assoc($get_stock);
        $stock = $st['stock'];
        

        $stock += $item_amount;

        $get_stock = mysqli_query($con,"UPDATE data SET stock='$stock' WHERE id='$item_id'");

        $cancel_order = mysqli_query($con,"UPDATE orders SET is_delivered='$cancel' WHERE id='$id'");
        
        $output['isSuccess'] = 1;
        $output['message'] = "Your order is canceled";
        echo json_encode($output);
    }
?>