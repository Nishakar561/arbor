<?php
    session_start();
    include("config.php");
    if(isset($_POST['loginButton'])){
        $email = $_POST['email'];
        $password = $_POST['password'];
        $goto = $_POST['goto'];
        $form = $_POST['form'];
        $id = $_POST['id'];

        $check_admin = mysqli_query($con,"SELECT email,password FROM admin WHERE email='$email'");

        if(mysqli_num_rows($check_admin) != 0){
            $result = mysqli_fetch_assoc($check_admin);
            if(password_verify($password , $result['password'])){
                $_SESSION['form'] = $form;
                $_SESSION['id'] = $id;
                header("location: $goto");
            } else{
                $_SESSION['message'] = "<script> alert('username or password is incorrect'); </script>";
                header("location: index.php");    
            }  
        } else{
            $_SESSION['message'] = "<script> alert('username or password is incorrect'); </script>";
            header("location: index.php");
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="assets/css/style.css">
    <?php include("assets/css/links.php"); ?>
    <title>Arbor</title>
</head>
<body>
    <header> 
    
        <div class="container center-div shadow" style="width: 600px">
            <div class="heading text-center mb-5 text-uppercase text-white">
                Verify admin
            </div>

            <div class="container row d-flex flex-row justify-content-center mb-5">
                <div class="admin-form shadow p-2">
                    <form action="check_admin.php" method="POST">
                        <input type="hidden" name="goto" value="<?php echo $_POST['goto']; ?>">
                        <input type="hidden" name="form" value=<?php echo $_POST['form'] ?>>
                        
                        <?php
                            if(isset($_POST['id'])){
                            ?>
                                <input type="hidden" name="id" value="<?php echo $_POST['id']; ?>">
                            <?php    
                            }
                        ?>

                        <div class="form-group">
                            <label for="username">Email</label>
                            <input type="email" name="email" class="form-control" id="email" placeholder="Email" autocomplete="off" required>
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" class="form-control" id="password" placeholder="Password" autocomplete="off" required>
                        </div>
                        <input type="submit" name="loginButton" value="Login" class="btn btn-success">
                        <a href="index.php" class="btn btn-danger"> Cancel </a>
                    </form>
                </div>
            </div>
        </div>
    </header>
</body>
</html>