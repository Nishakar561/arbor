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
            if(isset($_POST['add_sub_category'])){
            ?>
                <div class=" container-fluid  p-0">
                    <div class="heading text-left float-left mb-5 text-uppercase text-white">
                        <div class="float-right mr-5 mt-1"> <a href="logout.php" class="btn btn-danger text-decoration-none" style="width:100px;">Logout</a></div>
                        <div class="ml-5">Arbor</div>
                    </div>
                    <div class="container d-flex flex-row justify-content-center mb-5" >
                        <div class="admin-form shadow p-2">
                            <div class="heading text-center text-white mb-3" width="10%">
                                Add new sub-category
                            </div>
                            <form action="handler.php" method="POST" enctype='multipart/form-data'>
                                <div class="form-group">
                                    <label for="name">Subcategory Name</label>
                                    <input type="text" name="name" class="form-control" id="name" placeholder="Name" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="category_id">Category Id</label>
                                    <select name="category_id" id="category_id" class="form-control" required>
                                        <option value="" disabled selected>Select category</option>
                                        <?php
                                            $get_all_categories = mysqli_query($con,"SELECT id,name FROM categories");
                                            
                                            while($row = mysqli_fetch_assoc($get_all_categories)){
                                            ?>
                                                <option value="<?php echo $row['id'] ?>"> <?php echo $row['name'] ?></option>
                                            <?php    
                                            }

                                        ?>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="sub_category_image">Image</label>
                                    <input type="file" name="sub_category_image" class="form-control" id="sub_category_image" required>
                                </div>
                                <input type="submit" name="add_sub_category" value="Add subcategory" class="btn btn-success">
                                <a href="index.php" class="btn btn-danger">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>

        <?php }
        ?>

        <?php
            if(isset($_POST['edit'])){
                $id = $_POST['id'];
                $select = $con->prepare("SELECT * FROM sub_categories WHERE id=?");
                $select->bind_param("s",$id);
                $select->execute();
                $result = $select->get_result();
                $row = $result->fetch_assoc();
            ?>
                <div class=" container-fluid  p-0">
                    <div class="heading text-left float-left mb-5 text-uppercase text-white">
                        <div class="float-right mr-5 mt-1"> <a href="logout.php" class="btn btn-danger text-decoration-none" style="width:100px;">Logout</a></div>
                        <div class="ml-5">Arbor</div>
                    </div>
                    <div class="container d-flex flex-row justify-content-center mb-5" >
                        <div class="admin-form shadow p-2">
                            <div class="heading text-center text-white mb-3" width="10%">
                                Edit sub-category
                            </div>
                            <form action="handler.php" method="POST" enctype='multipart/form-data'>
                                <input type="hidden" name="id" value="<?php echo $row['id']; ?>">
                                <input type="hidden" name="old_image_path" value="<?php echo $row['image']; ?>">
                                <div class="form-group">
                                    <label for="name">Subcategory Name</label>
                                    <input type="text" name="name" class="form-control" id="name" placeholder="Name" value="<?php echo $row['name']; ?>" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="category_id">Category Id</label>
                                    <select name="category_id" id="category_id" class="form-control" required>
                                        <option value="" disabled selected>Select category</option>
                                        <?php
                                            $get_all_categories = $con->prepare("SELECT id,name FROM categories");
                                            $get_all_categories->execute();
                                            $result = $get_all_categories->get_result();
                                            
                                            while($row = $result->fetch_assoc()){
                                            ?>
                                                <option value="<?php echo $row['id'] ?>"> <?php echo $row['name'] ?></option>
                                            <?php    
                                            }

                                        ?>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="sub_category_image">Image</label>
                                    <input type="file" name="sub_category_image" class="form-control" value="<?php echo $row['image']; ?>" id="sub_category_image" required>
                                </div>
                                <input type="submit" name="update_sub_category" value="Update subcategory" class="btn btn-success">
                                <a href="index.php" class="btn btn-danger">Cancel</a>
                            </form>
                        </div>
                    </div>
                </div>

        <?php }
        ?>

        <?php 
            if(isset($_POST['delete'])){
                $id = $_POST['id'];
                $get_image = mysqli_query($con,"SELECT image FROM sub_categories WHERE id='$id'");
                $row = mysqli_fetch_assoc($get_image);
                unlink($row['image']);
                
                $sql = "DELETE FROM sub_categories WHERE id='$id'";
                
                if(mysqli_query($con,$sql)){
                    header("location: index.php");
                } else{
                    echo "query not worked";
                }
            }
        ?>
    </body>
</html>