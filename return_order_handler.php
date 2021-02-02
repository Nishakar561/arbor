<?php
    include("config.php"); 
    session_start();   
?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Arbor</title>
        <link rel="stylesheet" href="assets/css/style.css">
        <?php include("assets/css/links.php");?>
        <style>
            a{
                color: white;
            }

        </style>
        
    </head>
    <body>

    <?php
        $get_data = mysqli_query($con,"SELECT * FROM orders WHERE is_delivered='return' ORDER BY order_date ASC");
        ?>
            <div class="container d-flex justify-content-center mb-3">
                <form action="data_table_handler.php" method="POST">
                    <a href="index.php" class="btn btn-success mt-5">Back</a>
                </form> 
            </div>

            <div class='container-fluid d-flex justify-content-center'>

                <div class='table-responsive' >
                    <table class='table table-dark text-center shadow' style='border-radius:5px;'>
                        <tr>
                            <th width='5%' style=' border-color: transparent;'> Id</th>
                            <th width='15%' style=' border-color: transparent;'> Username</th>
                            <th width='5%' style=' border-color: transparent;'> Item Id</th>
                            <th width='5%' style=' border-color: transparent;'> Item size</th>
                            <th width='5%' style=' border-color: transparent;'> Item amount</th>
                            <th width='10%' style=' border-color: transparent;'> Price</th>
                            <th width='20%' style=' border-color: transparent;'> Payment method</th>
                            <th width='10%' style=' border-color: transparent;'> Order date</th>
                            <th width='10%' style=' border-color: transparent;'> Delivery date</th>
                            <th width='5%' style=' border-color: transparent;'> Is Delivered?</th>
                            <th width='10%' style=' border-color: transparent;'> Action</th>
                        </tr>
                        <?php
                        while($row = mysqli_fetch_assoc($get_data)){
                        ?>
                            <tr>
                            <td><?php echo $row['id']; ?></td>
                            <td><?php echo $row['username']; ?></td>
                            <td><?php echo $row['item_id']; ?></td>
                            <td><?php echo $row['item_size']; ?></td>
                            <td><?php echo $row['item_amount']; ?></td>
                            <td><?php echo $row['price']; ?></td>
                            <td><?php echo $row['payment_method']; ?></td>
                            <td><?php echo $row['order_date']; ?></td>
                            <td><?php echo $row['delivery_date']; ?></td>
                            <td><?php echo $row['is_delivered']; ?></td>
                            
                            <td>
                                <form action="handler.php" method="POST">
                                    <input type="hidden" name="id" value="<?php echo $row['id']; ?>">
                                    <input type="hidden" name="payment_method" value="<?php echo $row['payment_method']; ?>">
                                    <input type="submit" name="return" class="btn btn-info" value="Returned">
                                </form>    
                            </td>
                            </tr>
                        <?php
                        }
        
        ?>

    </body>
</html>