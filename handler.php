<?php
    include("config.php");
    session_start();

    if(isset($_POST["add_admin"])){
        $email = $_POST['email'];
        $password = $_POST['password'];
        
        $password = password_hash($password,PASSWORD_BCRYPT);

        $check_email = mysqli_query($con,"SELECT * FROM admin WHERE email='$email'");


        if(mysqli_num_rows($check_email) == 0){
            mysqli_query($con,"INSERT INTO admin VALUES('','$email','$password')");
        } else{
            $_SESSION['message'] = "<script> alert('email already exists'); </script>";
        }

        header("location: index.php");
    }

    if(isset($_POST["update_admin"])){
        $id= $_POST['id'];
        $email = $_POST['email'];
        $password = $_POST['password'];

        $password = password_hash($password,PASSWORD_BCRYPT);
        
        mysqli_query($con,"UPDATE admin SET email='$email',password='$password' WHERE id='$id'");

        header("location: index.php");
    }

    if(isset($_POST['add_category'])){
        $name = $_POST['name'];
        $image = $_FILES["category_image"]['name'];

        if(file_exists("assets/images/categories/".$_FILES["category_image"]['name'])){
            $_SESSION['message'] = "<script> alert('Image already exists'); </script>";
            header("location: index.php");
        } 
        else{
            $image_location = "assets/images/categories/".$image;
            $sql = "INSERT INTO categories VALUES('','$name','$image_location')";

            if(mysqli_query($con,$sql)){
                move_uploaded_file($_FILES["category_image"]['tmp_name'], "assets/images/categories/".$_FILES["category_image"]['name']);
                header("location: index.php");
            } else{
                echo "<div class=' container row d-flex text-white text-center'> Query not worked </div>";
            }
        }

    }

    if(isset($_POST['update_category'])){
        $id = $_POST['id'];
        $name = $_POST['name'];
        $old_image_path = $_POST['old_image_path'];
        $image = $_FILES["category_image"]['name'];

        if(file_exists("assets/images/categories/".$_FILES["category_image"]['name'])){
            $_SESSION['message'] = "<script> alert('Image already exists'); </script>";
            header("location: index.php");
        } 
        else{
            $image_location = "assets/images/categories/".$image;
            $sql = "UPDATE categories SET name='$name',image='$image_location' WHERE id='$id'";

            if(mysqli_query($con,$sql)){
                unlink($old_image_path);
                move_uploaded_file($_FILES["category_image"]['tmp_name'], "assets/images/categories/".$_FILES["category_image"]['name']);
                header("location: index.php");
            } else{
                echo "<div class=' container row d-flex text-white text-center'> Query not worked </div>";
            }
        }
    }

    if(isset($_POST['add_sub_category'])){
        $name = $_POST['name'];
        $category_id = $_POST['category_id'];
        $image = $_FILES["sub_category_image"]['name'];

        if(file_exists("assets/images/sub_categories/".$_FILES["sub_category_image"]['name'])){
            $_SESSION['message'] = "<script> alert('Image already exists'); </script>";
            echo $_SESSION['message'];
            header("location: index.php");
        } 
        else{
            $id = "";
            $image_location = "assets/images/sub_categories/".$image;
            $sql = "INSERT INTO sub_categories VALUES('','$category_id','$name','$image_location')";

            if(mysqli_query($con,$sql)){
                move_uploaded_file($_FILES["sub_category_image"]['tmp_name'], "assets/images/sub_categories/".$_FILES["sub_category_image"]['name']);
                header("location: index.php");
            } else{
                echo "<div class=' container row d-flex text-white text-center'> Query not worked </div>";
            }
        }
    }

    if(isset($_POST['update_sub_category'])){
        echo "Hello";
        echo $id = $_POST['id'];
        echo $category_id = $_POST['category_id'];
        echo $name = $_POST['name'];
        echo $old_image_path = $_POST['old_image_path'];
        echo $image = $_FILES["sub_category_image"]['name'];

        if(file_exists("assets/images/sub_categories/".$_FILES["sub_category_image"]['name'])){
            $_SESSION['message'] = "<script> alert('Image already exists'); </script>";
            header("location: index.php");
        } 
        else{
            $image_location = "assets/images/sub_categories/".$image;
            $sql = "UPDATE sub_categories SET name='$name',image='$image_location' WHERE id='$id'";

            if(mysqli_query($con,$sql)){
                unlink($old_image_path);
                move_uploaded_file($_FILES["sub_category_image"]['tmp_name'], "assets/images/sub_categories/".$_FILES["sub_category_image"]['name']);
                header("location: index.php");
            } else{
                echo "<div class=' container row d-flex text-white text-center'> Query not worked </div>";
            }
        }
    }

    if(isset($_POST['add_data'])){
        $category_id = $_POST['category_id'];
        $sub_category_id = $_POST['sub_category_id'];
        $image = $_FILES["data_image"]['name'];
        $brand = $_POST['brand'];
        $details = $_POST['details'];
        $sizes = $_POST['sizes'];
        $stock = $_POST['stock'];
        $price = $_POST['price'];
        $discount = $_POST['discount'];
        $image_location = "assets/images/data_images/".$image;

        if(file_exists("assets/images/data_images/".$_FILES["data_image"]['name'])){
            $_SESSION['message'] = "<script> alert('Image already exists'); </script>";
            header("location: index.php");
        } 
        else{
            $sql = "INSERT into data VALUES('','$category_id','$sub_category_id','$image_location','$brand','$details','$sizes','$stock','$price','$discount')";
    
            if(mysqli_query($con,$sql)){
                move_uploaded_file($_FILES["data_image"]['tmp_name'], "assets/images/data_images/".$_FILES["data_image"]['name']);
                header("location: index.php");
            } else{
                $_SESSION['message'] = "<script> alert('Query not worked.');</script>";
                header("location: index.php");
            }
        }

    }

    if(isset($_POST['update_data'])){
        $id = $_POST['id'];
        $category_id = $_POST['category_id'];
        $sub_category_id = $_POST['sub_category_id'];
        $image = $_FILES["data_image"]['name'];
        $old_image_path = $_POST['old_image_path'] ;
        $brand = $_POST['brand'];
        $details = $_POST['details'];
        $sizes = $_POST['sizes'];
        $stock = $_POST['stock'];
        $price = $_POST['price'];
        $discount = $_POST['discount'] ;
        $image_location = "assets/images/data_images/".$image;

        if(file_exists("assets/images/data_images/".$_FILES["data_image"]['name'])){
            $_SESSION['message'] = "<script> alert('Image already exists'); </script>";
            header("location: index.php");
        } 
        else{
            $sql = "UPDATE data SET category_id='$category_id',sub_category_id='$sub_category_id',image='$image_location',brand='$brand',details='$details',sizes='$sizes',stock='$stock',price='$price',discount='$discount' WHERE id='$id'";
            // $update->bind_param("ssssssssss",$category_id,$sub_category_id,$image_location,$brand,$details,$sizes,$stock,$price,$discount,$id);
            
            if(mysqli_query($con,$sql)){
                unlink($old_image_path);
                move_uploaded_file($_FILES["data_image"]['tmp_name'], "assets/images/data_images/".$_FILES["data_image"]['name']);
                header("location: index.php");
            } else{
                $_SESSION['message'] = "<script> alert('Query not worked.');</script>";
                header("location: index.php");
            }
        }
    }

    if(isset($_POST['yes'])){
        $id = $_POST['id'];
        $date = date("Y/m/d H:i:s");
        mysqli_query($con,"UPDATE orders SET is_delivered='yes',delivery_date='$date' WHERE id='$id'");
        header("location: not_delivered_order_handler.php");
    }

    if(isset($_POST['paid'])){
        $id = $_POST['id'];
        $type = $_POST['type'];

        mysqli_query($con,"UPDATE orders SET is_delivered='paid' WHERE id='$id'");

        if($type == "cancel"){
            header("location: cancel_paypal_order_handler.php");
        } else if ($type == "unpaid"){
            header("location: unpaid_order_handler.php");
        }
    }

    if(isset($_POST['return'])){
        $id = $_POST['id'];
        $payment_method = $_POST['payment_method'];

        if($payment_method == "cash_on_delivery"){
            mysqli_query($con,"UPDATE orders SET is_delivered='paid' WHERE id='$id'");
        } else if($payment_method == "PayPal"){
            mysqli_query($con,"UPDATE orders SET is_delivered='unpaid' WHERE id='$id'");
        }
        
        if($type = "cancel"){
            header("location: return_order_handler.php");
        }
    }
?>
