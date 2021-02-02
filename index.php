<?php 
    include("config.php");
    session_start();
    if(!isset($_SESSION['email'])){
        header("location: admin_login_page.php");
    }
    
    //session_destroy();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Arbor admin page</title>
    <link rel="stylesheet" href="assets/css/style.css">
    <?php include("assets/css/links.php");?>
    <style>
        a{
            color: white;
        }

        .order_menu {
            display: block;
            margin: auto;
            margin-top: 5px;
            margin-bottom: 5px;
        }

    </style>
</head>
<body>
    <div class=" container-fluid  p-0">
        <div class="heading text-left float-left mb-5 text-uppercase text-white">
            <div class="float-right mr-5 mt-1"> <a href="logout.php" class="btn btn-danger text-decoration-none" style="width:100px;">Logout</a></div>
            <div class="ml-5">Arbor admin page</div>
        </div>

        <?php
        if(isset($_SESSION['message'])){
            echo $_SESSION['message'];
            unset($_SESSION['message']);
        }
        ?>

        <div class="container-fluid row d-flex flex-row justify-content-center mb-5">
            <form class="container-fluid" action="index.php" method="POST">
            <input type="submit" name="admin" class="btn btn-success information text-decoration-none" value="Admin" />
            <input type="submit" name="users" class="btn btn-success information text-decoration-none" value="Users" />
            <input type="submit" name="categories" class="btn btn-success information text-decoration-none" value="Categories" />
            <input type="submit" name="sub_categories" class="btn btn-success information text-decoration-none" value="Sub Categories" />
            <input type="submit" name="data" class="btn btn-success information text-decoration-none" value="Data" />
            <input type="submit" name="cart" class="btn btn-success information text-decoration-none" value="Cart" />
            <input type="submit" name="orders" class="btn btn-success information text-decoration-none" value="Orders" />
            </form>
        </div>
        
        <?php 
            if(isset($_POST['admin'])){

                $get_admin = mysqli_query($con,"SELECT id,email FROM admin");
                ?>
                        <div class="container d-flex justify-content-center mb-3">
                            <form action="check_admin.php" method="POST">
                                <input type="hidden" name="goto" value="admin_table_handler.php">
                                <input type="hidden" name="form" value="add_admin">
                                <input type="submit" name="add_admin" class="btn btn-success" value="Add new admin">
                            </form> 
                        </div>
                        
                        <div class='container d-flex justify-content-center'>
                            <div class='table-responsive' style='width:600px;'>
                                <table class='table table-dark text-center shadow' style='border-radius:5px;'>
                                    <tr>
                                        <th width='10%' style=' border-color: transparent;'> Id</th>
                                        <th width='30%' style=' border-color: transparent;'> Email;</th>
                                        <th width='30%' style=' border-color: transparent;'> Passowrd</th>
                                        <th width='30%' style=' border-color: transparent;'> Action</th>
                                    </tr>
                                    <?php
                                    while($row = mysqli_fetch_assoc($get_admin)){
                                    ?>
                                        <tr>
                                        <td><?php echo $row['id']; ?></td>
                                        <td><?php echo $row['email']; ?></td>
                                        <td><?php echo "encrypted"; ?></td>
                                        <td>
                                            <form action="check_admin.php" method="POST">
                                                <input type="hidden" name="goto" value="admin_table_handler.php">
                                                <input type="hidden" name="form" value="edit">
                                                <input type="hidden" name="id" value="<?php echo $row['id'];?>" >
                                                <input type="submit" name="edit" class="btn btn-info float-left" value="Edit">
                                            </form>
                                            <form action="check_admin.php" method="POST">
                                                <input type="hidden" name="goto" value="admin_table_handler.php">
                                                <input type="hidden" name="form" value="delete">
                                                <input type="hidden" name="id" value="<?php echo $row['id'];?>" >
                                                <input type="submit" name="delete" class="btn btn-danger" value="Delete">
                                            </form>
                                        </td>
                                        </tr>   
                                        
                                    <?php
                                    } ?>
                                    
                                </table>
                            </div>
                        </div>
            
                <?php         
                }
        ?>

        <?php 
            if(isset($_POST['users'])){
                // $get_admin = $con->prepare("SELECT id,name,username,email,address FROM users");
                // $get_admin->execute();
                // $result = $get_admin->get_result();
                $get_users = mysqli_query($con,"SELECT id,name,username,email,address FROM users");
                ?>
                    <div class='container-fluid d-flex justify-content-center'>
                        <div class='table-responsive' >
                            <table class='table table-dark text-center shadow' style='border-radius:5px;'>
                                <tr>
                                    <th width='5%' style=' border-color: transparent;'> Id</th>
                                    <th width='15%' style=' border-color: transparent;'> Name</th>
                                    <th width='15%' style=' border-color: transparent;'> Username</th>
                                    <th width='20%' style=' border-color: transparent;'> Email</th>
                                    <th width='10%' style=' border-color: transparent;'> Password</th>
                                    <th width='30%' style=' border-color: transparent;'> Address</th>
                                    <th width='5%' style=' border-color: transparent;'> Action</th>
                                </tr>
                                <?php
                                while($row = mysqli_fetch_assoc($get_users)){
                                ?>
                                    <tr>
                                    <td><?php echo $row['id']; ?></td>
                                    <td><?php echo $row['name']; ?></td>
                                    <td><?php echo $row['username']; ?></td>
                                    <td><?php echo $row['email']; ?></td>
                                    <td><?php echo "encrypted"; ?></td>
                                    <td><?php echo $row['address']; ?></td>
                                    <td>
                                        <form action="check_admin.php" method="POST">
                                            <input type="hidden" name="id" value="<?php echo $row['id'];?>">
                                            <input type="hidden" name="goto" value="users_table_handler.php">
                                            <input type="hidden" name="form" value="delete">
                                            <input type="submit" class="btn btn-danger" name="delete" value="Delete" >
                                        </form>                    
                                    </td>
                                    </tr>
                                <?php
                                }
            }
        ?>

        <?php
            if(isset($_POST['categories'])){
                $get_categories = mysqli_query($con,"SELECT * FROM categories");
                ?>
                        <div class="container d-flex justify-content-center mb-3">
                            <form action="categories_table_handler.php" method="post">
                                <input type="submit" name="add_category" class="btn btn-success" value="Add new category">
                            </form> 
                        </div>

                        <div class='container d-flex justify-content-center'>

                            <div class='table-responsive' >
                                <table class='table table-dark text-center shadow' style='border-radius:5px;'>
                                    <tr>
                                        <th width='10%' style=' border-color: transparent;'> Id</th>
                                        <th width='30%' style=' border-color: transparent;'> Category Name</th>
                                        <th width='30%' style=' border-color: transparent;'> Image</th>
                                        <th width='30%' style=' border-color: transparent;'> Action</th>
                                    </tr>
                                    <?php
                                    while($row = mysqli_fetch_assoc($get_categories)){
                                    ?>
                                        <tr>
                                        <td><?php echo $row['id']; ?></td>
                                        <td><?php echo $row['name']; ?></td>
                                        <td><img src="<?php echo $row['image']; ?>" alt="image" style="width:100px; height:100px;"></td>
                                        <td>
                                            <form action="categories_table_handler.php" method="POST">
                                                <input type="hidden" name="id" value="<?php echo $row['id']; ?>">
                                                <input type="submit" name="edit" class="btn btn-info" value="Edit">
                                                <input type="submit" name="delete" class="btn btn-danger" value="Delete">
                                            </form>    
                                        </td>
                                        </tr>
                                    <?php
                                    }
            }
        ?>

        <?php
            if(isset($_POST['sub_categories'])){
                $get_admin = mysqli_query($con,"SELECT * FROM sub_categories");
                ?>
                        <div class="container d-flex justify-content-center mb-3">
                            <form action="sub_categories_table_handler.php" method="post">
                                <input type="submit" name="add_sub_category" class="btn btn-success" value="Add new sub category">
                            </form> 
                        </div>

                        <div class='container d-flex justify-content-center'>

                            <div class='table-responsive' >
                                <table class='table table-dark text-center shadow' style='border-radius:5px;'>
                                    <tr>
                                        <th width='10%' style=' border-color: transparent;'> Id</th>
                                        <th width='10%' style=' border-color: transparent;'> Category Id</th>
                                        <th width='30%' style=' border-color: transparent;'> Sub Category Name</th>
                                        <th width='30%' style=' border-color: transparent;'> Image</th>
                                        <th width='30%' style=' border-color: transparent;'> Action</th>
                                    </tr>
                                    <?php
                                    while($row = mysqli_fetch_assoc($get_admin)){
                                    ?>
                                        <tr>
                                        <td><?php echo $row['id']; ?></td>
                                        <td><?php echo $row['category_id']; ?></td>
                                        <td><?php echo $row['name']; ?></td>
                                        <td><img src="<?php echo $row['image']; ?>" alt="image" style="width:100px; height:100px;"></td>
                                        <td>
                                            <form action="sub_categories_table_handler.php" method="POST">
                                                <input type="hidden" name="id" value="<?php echo $row['id']; ?>">
                                                <input type="submit" name="edit" class="btn btn-info" value="Edit">
                                                <input type="submit" name="delete" class="btn btn-danger" value="Delete">
                                            </form>    
                                        </td>
                                        </tr>
                                    <?php
                                    }
            }
        ?>

        <?php
            if(isset($_POST['data'])){
                $get_data = mysqli_query($con,"SELECT * FROM data ORDER BY id DESC");
                ?>
                    <div class="container d-flex justify-content-center mb-3">
                        <form action="data_table_handler.php" method="POST">
                            <input type="submit" name="add_data" class="btn btn-success" value="Add new Data">
                        </form> 
                    </div>

                    <div class='container-fluid d-flex justify-content-center'>

                        <div class='table-responsive' >
                            <table class='table table-dark text-center shadow' style='border-radius:5px;'>
                                <tr>
                                    <th width='5%' style=' border-color: transparent;'> Id</th>
                                    <th width='5%' style=' border-color: transparent;'> Category Id</th>
                                    <th width='5%' style=' border-color: transparent;'> Sub Category id</th>
                                    <th width='10%' style=' border-color: transparent;'> Image</th>
                                    <th width='10%' style=' border-color: transparent;'> Brand</th>
                                    <th width='20%' style=' border-color: transparent;'> Details</th>
                                    <th width='10%' style=' border-color: transparent;'> Sizes</th>
                                    <th width='5%' style=' border-color: transparent;'> Stock</th>
                                    <th width='5%' style=' border-color: transparent;'> Price</th>
                                    <th width='5%' style=' border-color: transparent;'> Discount</th>
                                    <th width='20%' style=' border-color: transparent;'> Action</th>
                                </tr>
                                <?php
                                while($row = mysqli_fetch_assoc($get_data)){
                                ?>
                                    <tr>
                                    <td><?php echo $row['id']; ?></td>
                                    <td><?php echo $row['category_id']; ?></td>
                                    <td><?php echo $row['sub_category_id']; ?></td>
                                    <td><img src="<?php echo $row['image']; ?>" alt="image" style="width:100px; height:100px;"></td>
                                    <td><?php echo $row['brand']; ?></td>
                                    <td><?php echo $row['details']; ?></td>
                                    <td><?php echo $row['sizes']; ?></td>
                                    <td><?php echo $row['stock']; ?></td>
                                    <td><?php echo $row['price']; ?></td>
                                    <td><?php echo $row['discount']; ?></td>
                                    
                                    <td>
                                        <form action="data_table_handler.php" method="POST">
                                            <input type="hidden" name="id" value="<?php echo $row['id']; ?>">
                                            <input type="submit" name="edit" class="btn btn-info" value="Edit">
                                            <input type="submit" name="delete" class="btn btn-danger" value="Delete">
                                        </form>    
                                    </td>
                                    </tr>
                                <?php
                                }
            }
        ?>
        
        <?php
            if(isset($_POST['cart'])){
            $get_admin = mysqli_query($con,"SELECT * FROM cart");
            ?>
                <div class='container d-flex justify-content-center'>
                    <div class='table-responsive' >
                        <table class='table table-dark text-center shadow' style='border-radius:5px;'>
                            <tr>
                                <th width='10%' style=' border-color: transparent;'> Id</th>
                                <th width='18%' style=' border-color: transparent;'> Username</th>
                                <th width='18%' style=' border-color: transparent;'> Item Id</th>
                                <th width='18%' style=' border-color: transparent;'> Item Size</th>
                                <th width='18%' style=' border-color: transparent;'> Item Amount</th>
                                <th width='18%' style=' border-color: transparent;'> Price</th>
                            </tr>
                            <?php
                            while($row = mysqli_fetch_assoc($get_admin)){
                            ?>
                                <tr>
                                <td><?php echo $row['id']; ?></td>
                                <td><?php echo $row['username']; ?></td>
                                <td><?php echo $row['item_id']; ?></td>
                                <td><?php echo $row['item_size']; ?></td>
                                <td><?php echo $row['item_amount']; ?></td>
                                <td><?php echo $row['price']; ?></td>
                                </tr>
                            <?php
                            }
            }
        ?>

        <?php 
            if(isset($_POST['orders'])){
            ?>

            <div class="container-fluid row d-flex flex-row justify-content-center mb-5">
                <form class="container-fluid" action="not_delivered_order_handler.php" method="POST">
                    <input type="submit" name="not_delivered" class="btn btn-success order_menu text-decoration-none" value="Not delivered Orderes" />
                </form>
                
                <form class="container-fluid" action="delivered_order_handler.php" method="POST">
                    <input type="submit" name="delivered" class="btn btn-success order_menu text-decoration-none" value="Delivered Orders" />
                </form>

                <form class="container-fluid" action="cancel_order_handler.php" method="POST">
                    <input type="submit" name="canceled_by_cash" class="btn btn-success order_menu text-decoration-none" value="Canceled Orders By Cash on delivery" />
                </form>
                
                <form class="container-fluid" action="cancel_paypal_order_handler.php" method="POST">
                    <input type="submit" name="canceled_by_paypal" class="btn btn-success order_menu text-decoration-none" value="Canceled Orders By PayPal" />
                </form>
                    
                <form class="container-fluid" action="return_order_handler.php" method="POST">
                    <input type="submit" name="return" class="btn btn-success order_menu text-decoration-none" value="Orders to return" />
                </form>

                <form class="container-fluid" action="unpaid_order_handler.php" method="POST">
                    <input type="submit" name="returned_by_paypal" class="btn btn-success order_menu text-decoration-none" value="PayPal unpaid returns"/>
                </form>

                <form class="container-fluid" action="paid_order_handler.php" method="POST">
                    <input type="submit" name="paid" class="btn btn-success order_menu text-decoration-none" value="Paid returns"/>
                </form>
            </div>
            <?php    
            }
        ?>  
    </div>
    
    </div>
</body>
</html> 