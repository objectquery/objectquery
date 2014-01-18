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
						initPages(cur);
						root.appendChild(cur);
						prettyPrint();
						globalPages[pageId]=cur;
						cur.setAttribute("class","active");
					}
				}
				req.open("GET","pgs/"+pageId);
				req.send();
			}
		}
		function initPages(base)
		{
			if(!base || base ==null) return;
			if(history)
			{
				if(!window.globalPages)
				{
					globalPages ={};
					var root = base.getElementById("root");
					var pages=  root.children;
					for(var i = 0;i<pages.length; i++)
						if(pages.item(i).localName == "section")
							globalPages[document.location.pathname]=pages.item(i);
					prettyPrint();
				}

				var els =  base.getElementsByTagName("a");
				for(var i = 0;i<els.length; i++)
				{
					var clazz =els.item(i).getAttribute("class");
					if(clazz != null && clazz.indexOf("auto")!= -1)
						els.item(i).addEventListener('click', handleClick, false);
				}
				window.addEventListener('popstate',function(event){
					if(event.state.page)
						selectPage(event.state.page);
					else 
						selectPage("overview.html");
				},false);
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
			history.pushState({"page":url},"Object Query "+ dest.textContent, dest.href);
			return event.preventDefault();
		}
		</script>
	</head>
	<body onload="initPages(document)" id="root">
		<header>
			<nav>
				<ul id="menu">
					<li><a class="auto" href="overview.html">Overview</a></li>
					<li><a class="auto" href="roadmap.html">Roadmap</a></li>
					<li><a class="auto" href="impl_status.html">Impl Status</a></li>
					<li><a class="auto" href="doc.html">Documentation</a></li>
					<li><a class="auto" href="support.html">Support</a></li>
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
