<?php
/**
 * Created by PhpStorm.
 * User: karas
 * Date: 22.11.2016
 * Time: 00:33
 */
include('session.php');
$myiban = $_SESSION['iban'];
$mytytulem = $_SESSION['tytulem'];
$myamount = $_SESSION['amount'];
if($_SERVER["REQUEST_METHOD"] == "POST") {
    header("location: history.php");
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
<h1>Przelew wykonany</h1>
<div style = "margin: 10em;">

    <form action = "" method = "post">
        <p id="numer_konta">Numer konta:  <?php echo $myiban; ?></p>
        <p>tytulem:  <?php echo $mytytulem; ?></p>
        <p>Kwota:  <?php echo $myamount; ?></p>
        <input type = "submit" value = " K "/><br />
    </form>


</div>
<h2><a href = "logout.php">Sign Out</a></h2>
</body>

</html>