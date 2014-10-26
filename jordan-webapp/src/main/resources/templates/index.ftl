<html>
<head>
<title>Project Jordan</title>
<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>

<style>
    body{
        background-color: #3498db;
        color: black;
        font-family: 'Lato', sans-serif;
        font-weight: 100;
        }

    h1 {
        font-family: 'Lobster', cursive;
        font-size: 50px;
    }

    h2 {
        font-weight: 700;
    }

    h3{
        font-family: 'Lato', sans-serif;
        font-weght: 900;
        display:inline;
    }
</style>
</head>
<body>
<center><h1>Project Jordan</h1></center>
<center><h2>We currently have ${numOfBluRays} Blu Rays in our library.<h2></center>

</br>
<center>
<#list blurays as bluray>
    <p><h3><a href="${bluray.url}">${bluray.name}</a></h3></br>(<strong>New: </strong>£${bluray.priceNew?c}) - (<strong>Used: </strong>£${bluray.priceUsed?c})</p>
</#list>
</center>

</body>
</html>
