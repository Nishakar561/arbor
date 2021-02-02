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
        if(isset($_POST['add_category'])){
        ?>
            <div class=" container-fluid  p-0">
                <div class="heading text-left float-left mb-5 text-uppercase text-white">
                    <div class="float-right mr-5 mt-1"> <a href="logout.php" class="btn btn-danger text-decoration-none" style="width:100px;">Logout</a></div>
                    <div class="ml-5">Arbor</div>
                </div>
                <div class="container d-flex flex-row justify-content-center mb-5" >
                    <div class="admin-form shadow p-2">
                        <div class="heading text-center text-white mb-3" width="10%">
                            Add new category
                        </div>
                        <form action="handler.php" method="POST" enctype='multipart/form-data'>
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" name="name" class="form-control" id="name" placeholder="Name" autocomplete="off" required>
                            </div>

                            <div class="form-group">
                                <label for="category_image">Image</label>
                                <input type="file" name="category_image" class="form-control" id="category_image" required>
                            </div>
                            <input type="submit" name="add_category" value="Add category" class="btn btn-success">
                            <a href="index.php" class="btn btn-danger">Cancel</a>
                        </form>
                    </div>
                </div>
            </div>

        <?php
        }?>

        <?php
        if(isset($_POST['edit'])){
            $id = $_POST['id'];
            $get_data = mysqli_query($con,"SELECT * FROM categories WHERE id='$id'");
            $row = mysqli_fetch_assoc($get_data);
        ?>
            <div class=" container-fluid  p-0">
                <div class="heading text-left float-left mb-5 text-uppercase text-white">
                    <div class="float-right mr-5 mt-1"> <a href="logout.php" class="btn btn-danger text-decoration-none" style="width:100px;">Logout</a></div>
                    <div class="ml-5">Arbor</div>
                </div>
                <div class="container d-flex flex-row justify-content-center mb-5" >
                    <div class="admin-form shadow p-2">
                        <div class="heading text-center text-white mb-3" width="10%">
                            Change category credentials
                        </div>
                        <form action="handler.php" method="POST" enctype='multipart/form-data'>
                            <input type="hidden" name="id" value="<?php echo $row['id']; ?>">
                            <input type="hidden" name="old_image_path" value="<?php echo $row['image']; ?>">
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" name="name" class="form-control" id="name" value="<?php echo $row['name'];?>" placeholder="Name" autocomplete="off" required>
                            </div>

                            <div class="form-group">
                                <label for="category_image">Image</label>
                                <input type="file" name="category_image" class="form-control" id="category_image" value="<?php echo $row['image'] ?>" required>
                            </div>
                            <input type="submit" name="update_category" value="Save changes" class="btn btn-success">
                            <a href="index.php" class="btn btn-danger">Cancel</a>
                        </form>
                    </div>
                </div>
            </div>           
        <?php
        }?>

        <?php 
            if(isset($_POST['delete'])){
                $id = $_POST['id'];

                $get_image = mysqli_query($con,"SELECT image FROM categories WHERE id='$id'");
                $row = mysqli_fetch_assoc($get_image);
                unlink($row['image']);

                $sql = "DELETE FROM categories WHERE id='$id'";
                
                if(mysqli_query($con,$sql)){
                    header("location: index.php");
                } else{
                    echo "query not worked";
                }
            }
        ?>

    </body>
</html>
