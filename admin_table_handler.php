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
            if(!isset($_SESSION['form'])){
                echo "<div class='heading text-center text-uppercase text-white'>
                Session expired <br /> 
                <a href='index.php' class='btn btn-danger text-center'> Goto admin page</a>
                </div>";
            }

            else if($_SESSION["form"] == "add_admin"){
                unset($_SESSION['form']);
            ?>
                

                <div class=" container-fluid  p-0">
                    <div class="heading text-left float-left mb-5 text-uppercase text-white">
                        <div class="float-right mr-5 mt-1"> <a href="logout.php" class="btn btn-danger text-decoration-none" style="width:100px;">Logout</a></div>
                        <div class="ml-5">Arbor</div>
                    </div>
                    <div class="container d-flex flex-row justify-content-center mb-5" >
                        <div class="admin-form shadow p-2">
                            <div class="heading text-center text-white mb-3" width="10%">
                                Add new admin
                            </div>
                            <form action="handler.php" method="POST">
                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" name="email" class="form-control" id="email" placeholder="Email" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="text" name="password" class="form-control"  id="password" placeholder="Password" autocomplete="off" required>
                                </div>
                                <input type="submit" name="add_admin" value="Save" class="btn btn-success">
                                <a href="index.php" class="btn btn-danger">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>

            <?php
            }
        
            else if($_SESSION['form'] == "edit"){
                unset($_SESSION['form']);
                $id = $_SESSION['id']; 
                unset($_SESSION['id']);
                $check_username = mysqli_query($con,"SELECT email,id FROM admin WHERE id='$id'");
                $row = mysqli_fetch_assoc($check_username);
            ?>

                <div class=" container-fluid  p-0">
                    <div class="heading text-left float-left mb-5 text-uppercase text-white">
                        <div class="float-right mr-5 mt-1"> <a href="logout.php" class="btn btn-danger text-decoration-none" style="width:100px;">Logout</a></div>
                        <div class="ml-5">Arbor</div>
                    </div>
                    <div class="container d-flex flex-row justify-content-center mb-5" >
                        <div class="admin-form shadow p-2">
                            <div class="heading text-center text-white mb-3" width="10%">
                                Update admin
                            </div>
                            <form action="handler.php" method="POST">
                                <input type="hidden" name="id" value="<?php echo $row['id']?>">

                                <div class="form-group">
                                    <label for="email">Email</label>
                                    <input type="email" name="email" class="form-control" id="email" value="<?php echo $row['email'] ?>" placeholder="Username" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input type="text" name="password" class="form-control"  id="password"  placeholder="Password" autocomplete="off" required>
                                </div>
                                <input type="submit" name="update_admin" value="Update" class="btn btn-success">
                                <a href="index.php" class="btn btn-danger">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>

            <?php
            }
        
            else if($_SESSION['form'] == "delete"){
                unset($_SESSION['form']);
                $id = $_SESSION['id'];
                unset($_SESSION['id']);
                
                mysqli_query($con,"DELETE FROM admin WHERE id='$id'");

                header("location:index.php");
            }
        ?>

    </body>
</html>