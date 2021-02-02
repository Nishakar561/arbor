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
        <script>
            $(document).ready(function(){
            $("#category_id").change(function(){
                let category_id = $(this).val();

                $.ajax({
                    url: "action.php",
                    method: "POST",
                    data:{categoryId: category_id},
                    success: function(data){
                        $("#sub_category_id").html(data);
                    }
                });
            }); 
            });
        </script>

    </head>
    <body>
        
    <?php 
        if(isset($_POST['add_data'])){
        ?>
            <div class=" container-fluid  p-0">
                    <div class="heading text-left float-left mb-5 text-uppercase text-white">
                        <div class="float-right mr-5 mt-1"> <a href="logout.php" class="btn btn-danger text-decoration-none" style="width:100px;">Logout</a></div>
                        <div class="ml-5">Arbor</div>
                    </div>
                    <div class="container d-flex flex-row justify-content-center mb-5" >
                        <div class="admin-form shadow p-2">
                            <div class="heading text-center text-white mb-3" width="10%">
                                Add new Data
                            </div>
                            <form action="handler.php" method="POST" enctype='multipart/form-data'>

                                <div class="form-group">
                                    <label for="category_id">Category</label>
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
                                    <label for="sub_category_id">Subcategory</label>
                                    <select name="sub_category_id" id="sub_category_id" class="form-control" required>
                                        <option value="" disabled selected>Select sub category</option>
                                    </select>
                                </div>                               

                                <div class="form-group">
                                    <label for="data_image">Image</label>
                                    <input type="file" name="data_image" class="form-control" id="data_image" required>
                                </div>
                                
                                <div class="form-group">
                                    <label for="brand">Brand</label>
                                    <input type="text" name="brand" class="form-control" id="brand" placeholder="Brand" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="details">Detais</label>
                                    <input type="text" name="details" class="form-control" id="details" placeholder="Details" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="sizes">Sizes</label>
                                    <input type="text" name="sizes" class="form-control" id="sizes" placeholder="Ex: size1,size2 or just , if no size" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="stock">Stock</label>
                                    <input type="number" name="stock" class="form-control" id="stock" placeholder="Stock" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="price">Price</label>
                                    <input type="number" name="price" class="form-control" id="price" placeholder="Price" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="discount">Discount</label>
                                    <input type="number" name="discount" class="form-control" id="discount" placeholder="Discount" autocomplete="off" required>
                                </div>

                                <input type="submit" name="add_data" value="Add data" class="btn btn-success">
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
            $get_data_query = $con->prepare("SELECT * FROM data WHERE id='$id'");
            $get_data_query->execute();
            $result1 = $get_data_query->get_result();
            $row1 = $result1->fetch_assoc();
           
        ?>
            <div class=" container-fluid  p-0">
                    <div class="heading text-left float-left mb-5 text-uppercase text-white">
                        <div class="float-right mr-5 mt-1"> <a href="logout.php" class="btn btn-danger text-decoration-none" style="width:100px;">Logout</a></div>
                        <div class="ml-5">Arbor</div>
                    </div>
                    <div class="container d-flex flex-row justify-content-center mb-5" >
                        <div class="admin-form shadow p-2">
                            <div class="heading text-center text-white mb-3" width="10%">
                                Edit Data
                            </div>
                            <form action="handler.php" method="POST" enctype='multipart/form-data'>
                                <input type="hidden" name="id" value="<?php echo $row1['id']; ?>">
                                <input type="hidden" name="old_image_path" value="<?php echo $row1['image']; ?>">
                                <div class="form-group">
                                    <label for="category_id">Category</label>
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
                                    <label for="sub_category_id">Subcategory</label>
                                    <select name="sub_category_id" id="sub_category_id" class="form-control" required>
                                        <option value="" disabled selected>Select sub category</option>
                                    </select>
                                </div>                               

                                <div class="form-group">
                                    <label for="data_image">Image</label>
                                    <input type="file" name="data_image" class="form-control" id="data_image" required>
                                </div>
                                
                                <div class="form-group">
                                    <label for="brand">Brand</label>
                                    <input type="text" name="brand" class="form-control" id="brand" value="<?php echo $row1['brand']?>" placeholder="Brand" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="details">Detais</label>
                                    <input type="text" name="details" class="form-control" id="details" value="<?php echo $row1['details']?>" placeholder="Details" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="sizes">Sizes</label>
                                    <input type="text" name="sizes" class="form-control" id="sizes" value="<?php echo $row1['sizes']?>" placeholder="Ex: size1,size2 or just , if no size" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="stock">Stock</label>
                                    <input type="number" name="stock" class="form-control" id="stock" value="<?php echo $row1['stock']?>" placeholder="Stock" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="price">Price</label>
                                    <input type="number" name="price" class="form-control" id="price" value="<?php echo $row1['price']?>" placeholder="Price" autocomplete="off" required>
                                </div>

                                <div class="form-group">
                                    <label for="discount">Discount</label>
                                    <input type="number" name="discount" class="form-control" id="discount" value="<?php echo $row1['discount']?>" placeholder="Discount" autocomplete="off" required>
                                </div>

                                <input type="submit" name="update_data" value="Update data" class="btn btn-success">
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

                $id = $_POST['id'];
                $get_image = mysqli_query($con,"SELECT image FROM data WHERE id='$id'");
                $row = mysqli_fetch_assoc($get_image);

                unlink($row['image']);

                $sql = "DELETE FROM data WHERE id='$id'";
                
                if(mysqli_query($con,$sql)){
                    header("location: index.php");
                } else{
                    echo "query not worked";
                }

            }   
        ?>
    
    </body>
</html>