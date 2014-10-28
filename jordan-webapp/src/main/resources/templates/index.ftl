<html>
<head>
<title>Project Jordan</title>
<link href='http://fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
<link rel="shortcut icon" type="image/png" href="http://icons.iconarchive.com/icons/miniartx/drinks/96/Beer-icon.png"/>

<style>
    body{
        background-color: #2ecc71;
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

<style type="text/css">
	.flat-table {
		margin-bottom: 20px;
		border-collapse:collapse;
		font-family: 'Lato', Calibri, Arial, sans-serif;
		border: none;
                border-radius: 3px;
               -webkit-border-radius: 3px;
               -moz-border-radius: 3px;
	}
	.flat-table th, .flat-table td {
		box-shadow: inset 0 -1px rgba(0,0,0,0.25),
			inset 0 1px rgba(0,0,0,0.25);
	}
	.flat-table th {
		font-weight: normal;
		-webkit-font-smoothing: antialiased;
		padding: 1em;
		color: rgba(0,0,0,0.45);
		text-shadow: 0 0 1px rgba(0,0,0,0.1);
		font-size: 1.5em;
	}
	.flat-table td {
		color: #f7f7f7;
		padding: 0.7em 1em 0.7em 1.15em;
		text-shadow: 0 0 1px rgba(255,255,255,0.1);
		font-size: 1.4em;
	}
	.flat-table tr {
		-webkit-transition: background 0.3s, box-shadow 0.3s;
		-moz-transition: background 0.3s, box-shadow 0.3s;
		transition: background 0.3s, box-shadow 0.3s;
		font-size: 12px;
	}
	.flat-table-1 {
		background: #336ca6;
	}
	.flat-table-1 tr:hover {
		background: rgba(0,0,0,0.19);
	}
	.flat-table-2 tr:hover {
		background: rgba(0,0,0,0.1);
	}
	.flat-table-2 {
		background: #f06060;
	}
	.flat-table-3 {
		background: #52be7f;
	}
	.flat-table-3 tr:hover {
		background: rgba(0,0,0,0.1);
	}
</style>
</head>
<body>
<center><h1>Project Jordan</h1></center>
<center><h2>We currently have ${numOfBluRays} Blu Rays in our library.<h2></center>

</br>

<center>
  <table class="flat-table flat-table-3">
  	<thead>
  		<th>Movie Title</th>
  		<th>New Price</th>
  		<th>Used Price</th>
  		<th>Display</th>
  	</thead>
    <tbody>
  <#list blurays as bluray>
      <#if bluray.isInteresting == true>
         <tr>
            <td><a href="${bluray.url}" target="_blank">${bluray.name}</a></td>
            <td>£${bluray.priceNew?c}</td>
            <td>£${bluray.priceUsed?c}</td>
            <td><a href="not-interested?movie=${bluray.name}">Not Interested</a></td>
        </tr>
      </#if>
  </#list>
  	</tbody>
</table>

</center>

</body>
</html>
