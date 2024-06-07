<?php
$host="localhost";
 $uname="root";
 $pass="";
 $dbname="RecyclingProject";
 $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
 mysqli_select_db($dbh, $dbname);

 $username = $_GET['Username'];
 $addedPoints = $_GET['Points'];  
 $quantityKG = $_GET['Quantity'];
 $material = $_GET['Material'];

if ($material == "Aluminium"){
 $sql = "UPDATE Users SET Points = Points + $addedPoints, Aluminium_kg = Aluminium_kg + $quantityKG WHERE Username ='$username'";
}
elseif ($material == "Glass"){
    $sql = "UPDATE Users SET Points = Points + $addedPoints, Glass_kg = Glass_kg + $quantityKG WHERE Username = '$username'";
}
 elseif ($material == "Paper"){
     $sql = "UPDATE Users SET Points = Points + $addedPoints, Paper_kg = Paper_kg + $quantityKG WHERE Username = '$username'";
 }
 else {
     $sql = "UPDATE Users SET Points = Points + $addedPoints, Plastic_kg = Plastic_kg + $quantityKG WHERE Username = '$username'";
 }

 
 

 if(mysqli_query($dbh, $sql)) {
        echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }
    
    $conn->close();
?>