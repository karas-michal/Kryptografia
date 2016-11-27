<?php
/**
 * Created by PhpStorm.
 * User: karas
 * Date: 21.11.2016
 * Time: 15:15
 */
    session_start();

    if(session_destroy()) {
        header("Location: login.php");
    }
?>