<?
$pages= array("roadmap"=>"roadmap","doc"=>"doc","support"=>"support","tutorial-simple"=>"tutorial-simple","vtutorial"=>"vtutorial");
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
			var o = document.getElementById(pageId);
			if(o)o.setAttribute("class","active");
		}
		function initPages()
		{
			if(history)
			{
				var root = document.getElementById("root");
				var pages=  root.children;
				for(var i = 0;i<pages.length; i++)
					if(pages.item(i).localName == "section")
						globalPages[pages.item(i).getAttribute("id")]=pages.item(i);

				var bd = document.getElementById("menu");
				var els=  bd.children;
				for(var i = 0;i<els.length; i++)
				{
					els.item(i).addEventListener('click', handleClick, false);
				}
				window.addEventListener('popstate',function(event){
					console.log(event.state);
					selectPage(event.state.id);
				});
				var url=document.location.href;
				if(url.indexOf("=")!= -1)
					var id = url.substr(url.indexOf("=")+1);
				else
					var id="overview";
				history.pushState({"id":id},"Object Query ","?page="+id);
			}
		}
		function handleClick(event)
		{
			if(!event.target)return;
			if(event.target.elementName=="li")
				var dest =event.target.firstElementChild;
			else
				var dest=event.target;
			var url=dest.getAttribute("href");
			var id = url.substr(url.indexOf("=")+1);
			selectPage(id);
			history.pushState({"id":id},"Object Query "+ dest.textContent, dest.href);
			return event.preventDefault();
		}
		</script>
	</head>
	<body onload="prettyPrint();" id="root">
		<header>
			<nav>
				<ul id="menu">
					<li onclick="selectPage('overview')"><a href="?page=overview">Overview</a></li>
					<li ><a href="?page=roadmap">Roadmap/Status</a></li>
					<li><a href="?page=doc">Documentation</a></li>
					<li onclick="selectPage('support')"><a href="?page=support">Support</a></li>
				</ul>
			</nav>
			<img src="img/logo2.png">
		</header>
		<?
			if(isset($_GET["page"]))
				$page = $pages[$_GET["page"]];
			if(!isset($page))$page="overview";
			include($page.".html");
		?>
		<footer>
			<div>Object Query the java mode to build query</div>
		</footer>
	</body>
</html>
