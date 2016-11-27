<?php
/**
 * Created by PhpStorm.
 * User: karas
 * Date: 22.11.2016
 * Time: 00:07
 */
include('session.php');
$sql = $_SESSION['order'];
$myiban = $_SESSION['iban'];
$mytytulem = $_SESSION['tytulem'];
$myamount = $_SESSION['amount'];
if($_SERVER["REQUEST_METHOD"] == "POST") {
    if($db->query($sql) == TRUE){
        printf("ok\n");
        header("location: welcome.php");
    }
    else{
        echo "Error: " . $sql . "<br>" . $db->error;
    }
}
?>
<html>

<head>
    <title>Potwierdzenie przelewu </title>
    <style type = "text/css">
        body {
            font-family:Arial, Helvetica, sans-serif;
            font-size:14px;
        }

        label {
            font-weight:bold;
            width:100px;
            font-size:14px;
            display: block;
        }

    </style>
</head>

<body>
<h1>Potwierdzenie</h1>
<div style = "margin: 10em;">

    <form action = "" method = "post">
        <p id="numer_konta">Numer konta:  <?php echo $myiban; ?></p>
        <p>tytulem:  <?php echo $mytytulem; ?></p>
        <p>Kwota:  <?php echo $myamount; ?></p>
        <input type = "submit" value = " Potwierdz "/><br />
    </form>


</div>
<h2><a href = "logout.php">Sign Out</a></h2>
</body>

</html>



