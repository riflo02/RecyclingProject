<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "RecyclingProject";

// Δημιουργία σύνδεσης
$conn = new mysqli($servername, $username, $password, $dbname);

// Έλεγχος σύνδεσης
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Λήψη δεδομένων από GET παράμετρος
$name = $_GET['Name'];
$email = $_GET['Email'];
$username = $_GET['Username'];
$password = $_GET['Password'];
$points = $_GET['Points'];
$aluminiumKg = $_GET['AluminiumKg'];
$glassKg = $_GET['GlassKg'];
$paperKg = $_GET['PaperKg'];
$plasticKg = $_GET['PlasticKg'];


// Εισαγωγή δεδομένων στη βάση
$sql = "INSERT INTO users (name, email, username, password, points, aluminium_kg, glass_kg, paper_kg, plastic_kg)
VALUES ('$name', '$email', '$username', '$password', '$points', '$aluminiumKg', '$glassKg', '$paperKg', '$plasticKg')";

if ($conn->query($sql) === TRUE) {
    echo "New record created successfully";
} else {
    echo "Error: " . $sql . "<br>" . $conn->error;
}

$conn->close();
?>