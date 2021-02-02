<?php
    session_start();
    $error_array = array();
    include("config.php");
    if(isset($_POST['loginButton'])){
        $email = $_POST['email'];
        $password = $_POST['password'];
        
        $check_admin = mysqli_query($con,"SELECT email,password FROM admin WHERE email='$email'");
        $result = mysqli_fetch_assoc($check_admin);

        if(mysqli_num_rows($check_admin) != 0){
            if(password_verify($password , $result['password'])){
                $_SESSION['email'] = $email;
                header("location: index.php");
            } else{
                array_push($error_array,"<div class='heading text-center text-uppercase text-white'> Username or password is incorrect </div>");    
            }
        } else{
            array_push($error_array,"<div class='heading text-center text-uppercase text-white'> Username or password is incorrect </div>");
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
    <?php if(in_array("<div class='heading text-center text-uppercase text-white'> Username or password is incorrect </div>", $error_array)) echo "<div class='heading text-center text-uppercase text-white'> Username or password is incorrect </div>"; ?>
        <div class="container center-div shadow" style="width: 600px">
            <div class="heading text-center mb-5 text-uppercase text-white">
                Arbor admin login
            </div>

            <div class="container row d-flex flex-row justify-content-center mb-5">
                <div class="admin-form shadow p-2">
                    <form action="admin_login_page.php" method="POST">
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input type="email" name="email" class="form-control" id="email" placeholder="Email" autocomplete="off" required>
                        </div>

                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" class="form-control" id="password" placeholder="Password" autocomplete="off" required>
                        </div>
                        <input type="submit" name="loginButton" value="Login" class="btn btn-success">
                    </form>
                </div>
            </div>
        </div>
    </header>
</body>
</html>