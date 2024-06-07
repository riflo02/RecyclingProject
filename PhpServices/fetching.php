<?php
 $data = [];
 $host="localhost";
 $uname="root";
 $pass="";
 $dbname="RecyclingProject";
 $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
 mysqli_select_db($dbh, $dbname);

 $sql = "SELECT ID,Name,Email,Username,Password,Points, Aluminium_kg, Glass_kg, Paper_kg, Plastic_kg FROM Users";
 $result = mysqli_query($dbh, $sql);

 while ($row = mysqli_fetch_array($result)) {

    $data[$row['ID']] = [
       'Name' => $row['Name'],
       'Email' => $row['Email'],
       'Username' =>$row['Username'],
       'Password' => $row['Password'],
       'Points' =>$row['Points'],
       'Aluminium_kg' =>$row['Aluminium_kg'],
       'Glass_kg' =>$row['Glass_kg'],
       'Paper_kg' =>$row['Paper_kg'],
       'Plastic_kg' =>$row['Plastic_kg']
    ];
}
 header("Content-Type: application/json");
 echo json_encode($data);
 mysqli_close($dbh);
?>