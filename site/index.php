<?php
$files =scandir("pgs");
$pages= array();
foreach($files as $cur)
{
	if(is_file("pgs/".$cur))
		$pages["/$cur"]="pgs/$cur";
		
}
?><!DOCTYPE HTML>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta name="author" content="tglman"/>
		<meta name="description" content="Java library that allow to write dbms query in java way"/>
		<meta name="keywords" content="Java,SQL,JPA,JDO,JPQL,JDOQL,HQL,OrientDB,Query,ObjectQuery,TypeSafe Query,DDD,Domain Driven Design,ORM,Hibernate"/>
		<title>Object Query</title>
		<link href="/favicon.ico" rel="shortcut icon" />
		<link href="style.css" rel="stylesheet" type="text/css" />
		<link href="prettify/prettify.css" rel="stylesheet" type="text/css" />
		<script src="prettify/prettify.js" type="text/javascript"></script>
		<script type="text/javascript">
		var globalPages= {};
		
		function selectPage(pageId)
		{
			for(var pr in globalPages) 
			       globalPages[pr].setAttribute("class","");
			var o = globalPages[pageId];
			if(o)o.setAttribute("class","active");
			else {
				var req = new XMLHttpRequest();
				req.onreadystatechange= function(){
					if(this.status == 200 && this.readyState == 4){
						var root = document.getElementById("root");
						var help = document.createElement("div");
						help.innerHTML=this.responseText;
						var cur =help.firstChild;
						root.appendChild(cur);
						globalPages[pageId]=cur;
						cur.setAttribute("class","active");
					}
				}
				req.open("GET","pgs/"+pageId);
				req.send();
			}
		}
		function initPages()
		{
			if(history)
			{
				var root = document.getElementById("root");
				var pages=  root.children;
				for(var i = 0;i<pages.length; i++)
					if(pages.item(i).localName == "section")
						globalPages[pages.item(i).getAttribute("id")+".html"]=pages.item(i);

				var bd = document.getElementById("menu");
				var els=  bd.children;
				for(var i = 0;i<els.length; i++)
				{
					els.item(i).addEventListener('click', handleClick, false);
				}
				window.addEventListener('popstate',function(event){
					console.log(event.state);
					selectPage(event.state.url);
				},false);
				var url=document.location.href;
				url =url.substring("http://www.objectquery.org".lenght);
				if(url.lenght < 2)
					url="overview.html";
				history.pushState({"url":url},"Object Query ",url);
			}
		}
		function handleClick(event)
		{
			if(!event.target)return;
			if(event.target.localName=="li")
				var dest =event.target.firstElementChild;
			else
				var dest=event.target;
			var url=dest.getAttribute("href");
			selectPage(url);
			history.pushState({"url":url},"Object Query "+ dest.textContent, dest.href);
			return event.preventDefault();
		}
		</script>
	</head>
	<body onload="prettyPrint();initPages()" id="root">
		<header>
			<nav>
				<ul id="menu">
					<li><a href="overview.html">Overview</a></li>
					<li><a href="roadmap.html">Roadmap/Status</a></li>
					<li><a href="doc.html">Documentation</a></li>
					<li><a href="support.html">Support</a></li>
				</ul>
			</nav>
			<img src="img/logo2.png" alt="Object Query">
		</header>
		<?php
			$page = $pages[$_SERVER['REQUEST_URI']];
			if(isset($_GET["page"]))
				$page = $pages["/".$_GET["page"]];
			if(!isset($page))$page="pgs/overview.html";
			include($page);
		?>
		<footer>
			<div>Object Query the Java way to build query</div>
		</footer>
	</body>
</html>
