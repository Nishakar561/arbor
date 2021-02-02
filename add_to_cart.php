<?php
    include("config.php");

    $username = $_POST['username'];
    $item_id = $_POST['item_id'];
    $item_size = $_POST['item_size'];
    $item_amount = $_POST['item_amount'];
    $price = $_POST['price'];
    $output = array();

    $check_for_same_item = mysqli_query($con,"SELECT * FROM cart WHERE username='$username' AND item_id='$item_id' AND item_size='$item_size'");
    
    if(mysqli_num_rows($check_for_same_item) == 0 ){
        $add_to_cart = mysqli_query($con,"INSERT INTO cart VALUES('','$username','$item_id','$item_size','$item_amount','$price')");

        $output['isSuccess'] = 1;
        $output['message'] = "added to cart";

        echo json_encode($output);

    } else{
        $row = mysqli_fetch_assoc($check_for_same_item);
        $previous_amount = $row['item_amount'];
        $previous_price = $row['price'];

        $updated_price = $price + $previous_price;
        $updated_amount = $item_amount + $previous_amount;

        $update_cart = mysqli_query($con,"UPDATE cart SET item_amount='$updated_amount', price='$updated_price' WHERE item_id='$item_id'");

        $output['isSuccess'] = 1;
        $output['message'] = "updated into cart";

        echo json_encode($output);

        $con->close();
    }
?>