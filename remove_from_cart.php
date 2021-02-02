<?php 
    include("config.php");

    $id = $_POST['id'];
    $output = array();

    mysqli_query($con,"DELETE FROM cart WHERE id='$id'");

    $output['isSuccess'] = 1;
    $output['message'] = "Item deleted from cart";

    echo json_encode($output);

    $con->close();
?>  