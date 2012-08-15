<!DOCTYPE HTML>
<html>
	<head>
		<title>Object Query</title>
		<link href="style.css" rel="stylesheet" type="text/css" />
		<link href="prettify/prettify.css" rel="stylesheet" type="text/css" />
		<script src="prettify/prettify.js" type="text/javascript"></script>
		<script type="text/javascript">
			var globalPages= {};
			function initPages()
			{
				var bd = document.getElementById("root");
				var els=  bd.childNodes;
				for(var i = 0;i<els.length; i++)
				{
					if(els.item(i).localName == "section")
						globalPages[els.item(i).getAttribute("id")]=els.item(i);
				}
			}
			function selectPage(pageId)
			{
				for(var pr in globalPages) {
					globalPages[pr].setAttribute("class","");
				}
				globalPages[pageId].setAttribute("class","active");
			}
		</script>
	</head>
	<body onload="prettyPrint();initPages();" id="root">
		<header>
			<nav>
				<ul>
					<li onclick="selectPage('overview')"><a href="?page=overview">Overview</a></li>
					<li onclick="selectPage('install')"><a href="?page=install">Install</a></li>
					<li onclick="selectPage('build-query')" ><a href="?page=build-query">Build A Query</a></li>
					<li onclick="selectPage('query-language')"><a href="?page=query-language">Query Language Support</a></li>
				</ul>
			</nav>
			<img src="img/logo.png">
		</header>
		<section id="overview" <?echo($_GET["page"]=="overview"||!isset($_GET["page"])?'class="active"':'')?>>
			<header>
				<h2>Overview</h2>
			</header>
			<p>
				Object Query is a simple query builder thinked for java, that allow to write TypeSafe and Refactor Resistent query,Without bond to persistence engine.
			</p>
		</section>
		<section id="install"  <?echo($_GET["page"]=="install"?'class="active"':'')?>>
			<header> 
				<h2>Install</h2>
			</header>
			<p>
				use maven to install or otherwise download the jar here:????
			</p>
		</section>
		<section id="build-query" <?echo($_GET["page"]=="build-query"?'class="active"':'')?>>
			<header>
				<h2>Build A query</h2>
			</header>

			<article>
				<header>
					<h3>Requirements</h3>
				</header>
				<p>
					The requirements for build a query with query builder is an java Object Oriented "Domain" that rapresent an group of persisted objects.
				</p>
			</article>
			<article>
				<header>
					<h3>Domain</h3>
				</header>
				<p>
<pre class="prettyprint lang-java">
public class Dog {
	private String name;
	private Person owner;
	private Home home;
	...gets sets...
};
</pre>
<pre class="prettyprint lang-java">
public class Person {
	private String name;
	private List&lt;Person&gt; friends;
	private Person mum;
	private Person dud;
	private Home home;
	private Dog dog;
	...gets sets...
}
</pre>
<pre class="prettyprint lang-java">
public class Home {
	public enum HomeType {KENNEL,HOUSE};
	private String address;
	private HomeType type;
	private int weight;
	private double price;
	...gets sets...
}
</pre>
				</p>
			</article>
			<article>
				<header>
					<h3>Query Building</h3>
				</header>
				<p>
					Search All person that live in "rue d'anton" with a mum with name "elisabeth" and order by name.
				</p>
<pre class="prettyprint lang-java">
ObjectQuery<Person> query = ...//Persistence dependent query factory.
Person toSearch = query.target();
query.eq(toSearch.getHome().getAddress(),"rue d'anton");
query.eq(toSearch.getMum().getName(),"elisabeth");
query.order(toSearch.getName());
<pre>
			</article>
		</section>
		<section id="query-language"  <?echo($_GET["page"]=="query-language"?'class="active"':'')?> >
			<header> 
				<h2>List of Supported languages</h2>
			</header>
			<p>
				nothing...........
			</p>
		</section>
		<footer>
			<p>Object Query the Java Mode to build query</p>
		</footer>
	</body>
</html>
