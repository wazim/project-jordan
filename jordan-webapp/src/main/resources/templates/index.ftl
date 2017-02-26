<html>
<head>
<title>Project Jordan - inactive</title>
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
		padding: 0.7em 1em 0.7em 1.15em;
		text-shadow: 0 0 1px rgba(255,255,255,0.1);
		font-size: 1.4em;
		text-align: center;
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
	#email {
		position: fixed;
		top: 0;
		right: 0;
		width: 25%;
		background-color: white;
		color: black;
		box-shadow: 10px 10px 5px #888888;
		border: 2px solid;
        border-radius: 10px;
	}
</style>
</head>
<body>
<center><h1>Project Jordan</h1></center>
<center><h2>We currently have <span class="librarySize">${numOfBluRays}</span> Blu Rays in our library.<h2>
<h4>Currently displaying <em>

<#if displaying == "ALL" >
<a href="?include=select" title="Click to see Only Interested">${displaying}</a>
<#else>
<a href="?include=all" title="Click to see All">${displaying}</a>
</#if>

<div id="email">
<form action="email" method="POST">
	Movie Name:<br>
	<input type="text" name="title">
	<br>
	Email Address:<br>
	<input type="text" name="address">
	<br><br>
	<input type="submit" value="Submit">
</form>
</div>

</em></br></h4>
</center>

</br>

<center>
  <table class="flat-table flat-table-3" id="jordanTable">
  	<thead>
  		<th>Movie Title</th>
  		<th>New Price</th>
  		<th>Used Price</th>
  		<th>Display</th>
  		<!--<th>Metacritic Score</th>-->
  		<th>Manual Update</th>
  	</thead>
    <tbody>
  <#list blurays as bluray>
         <tr id="${bluray.id?c}">
            <td><a href="${bluray.url}" target="_blank">${bluray.name}</a></td>
            <td>&#163;${bluray.priceNew?c}</td>
            <td>&#163;${bluray.priceUsed?c}</td>

            <#if bluray.isInteresting == true>
                <td><a href="#" onclick='removeInterestFor("${bluray.id?c}")'>Not Interested</a></td>
            <#else>
                <td>Marked</td>
            </#if>

            <!--<td>
            <#if bluray.rating == 0>
                -
            <#else>
                ${bluray.rating}
            </#if>
            </td> -->
            <td><span class="manualupdate${bluray.id?c}"><a href="#" onclick='manuallyUpdate("${bluray.id?c}")'>Update</a></span></td>
        </tr>
  </#list>
  	</tbody>
</table>

</center>

<!-- DataTables CSS -->
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.3/css/jquery.dataTables.css">

<!-- jQuery -->
<script type="text/javascript" charset="utf8" src="//code.jquery.com/jquery-1.10.2.min.js"></script>

<!-- DataTables -->
<script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.3/js/jquery.dataTables.js"></script>

<script>
var dataTable;

$(document).ready( function () {
    dataTable = $('#jordanTable').DataTable();
} );

function removeInterestFor(movieId) {
    $.ajax({
      type: "GET",
      url: "not-interested",
      data: { movie: movieId }
    })
    .done(function( msg ) {
        $('#'+movieId).remove();
        $(".librarySize").text($(".librarySize").text()-1)
    });
}

function manuallyUpdate(movieId) {
	$(".manualupdate"+movieId).html("<img src='http://media.giphy.com/media/tEK2DZzAgY1l6/giphy.gif' border='0' align='center' />");
    $.ajax({
      type: "GET",
      url: "manual-update",
      data: { movie: movieId }
    })
    .done(function( msg ) {
    	$(".manualupdate"+movieId).html("<img src='http://cdn.flaticon.com/png/16/60778.png' border='0' align='center' />");
        if(msg.length > 1) {
        	alert(msg);
        	location.reload();
        }
    });
}
</script>
</body>
</html>
