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
					<li onclick="selectPage('query-engine')"><a href="?page=query-engine">Query Engine Support</a></li>
				</ul>
			</nav>
			<img src="img/logo.png">
		</header>
		<section id="overview" <?echo($_GET["page"]=="overview"||!isset($_GET["page"])?'class="active"':'')?>>
			<header>
				<h2>Overview</h2>
			</header>
			<article>
				<header>
					<h3>Wath Is</h3>
				</header>
				<p>
					Object Query is a simple query builder thinked for java, that allow to write typesafe and refactor resistent query, without bound to persistence engine.
				</p>
			</article>
			<article>
				<header>
					<h3>License</h3>
				</header>
				<p>
					<a href="http://www.apache.org/licenses/LICENSE-2.0.html" >The Apache Software License, Version 2.0</a>
				</p>
			</article>
			<article>
				<header>
					<h3>Support</h3>
				</header>
				<p>
					issue tracker:<a href="https://github.com/organizations/objectquery/dashboard/issues" >https://github.com/organizations/objectquery/dashboard/issues</a>
				<p>
				<p>
					mailing list:<a href="https://groups.google.com/group/objectquery">https://groups.google.com/group/objectquery</a>
				</p>
				<p>
					source:<a href="https://github.com/organizations/objectquery" >https://github.com/organizations/objectquery</a>
				<p>
			</article>
			<article>
				<header><h3>Roadmap<h3></header>
				<h4>1.0.0</h4>
				<table class="roadmap">
					<tr><td>base select statement</td> <td class="road_done">DONE</td></tr>
					<tr><td>projections with grouping funcions</td> <td class="road_done">DONE</td></tr>
					<tr><td>conditions and nested conditions </td> <td class="road_done">DONE</td></tr>
					<tr><td>order by with grouping functions </td> <td class="road_done">DONE</td></tr>
					<tr><td>having clause </td> <td class="road_todo">TODO</td></tr>
					<tr><td>ignore case like condition type </td> <td class="road_todo">TODO</td></tr>
				</table>
				<h4>2.0.0</h4>
				<table class="roadmap">
					<tr><td>support for update operdation </td> <td class="road_todo">TODO</td></tr>
					<tr><td>support for delete operdation </td> <td class="road_todo">TODO</td></tr>
					<tr><td>support for insert operdation </td> <td class="road_todo">TODO</td></tr>
				</table>
			</article>
		</section>
		<section id="install"  <?echo($_GET["page"]=="install"?'class="active"':'')?>>
			<header> 
				<h2>Install</h2>
			</header>
				<article>
					<header>
						<h3>Maven</h3>
					</header>
					<p>
						Insert the specify maven dependecy for each implementation:<br><br>
						JPA:
						<pre class="prettyprint lang-xml">
&lt;dependency&gt;
	&lt;groupId>org.objectquery&lt;/groupId&gt;
	&lt;artifactId>jpaobjectquery&lt;/artifactId&gt;
	&lt;version>1.0.0-BETA1&lt;/version&gt;
&lt;/dependency&gt;
</pre>
						JDO:
						<pre class="prettyprint lang-xml">
&lt;dependency&gt;
	&lt;groupId>org.objectquery&lt;/groupId&gt;
	&lt;artifactId>jdoobjectquery&lt;/artifactId&gt;
	&lt;version>1.0.0-BETA1&lt;/version&gt;
&lt;/dependency&gt;
</pre>
					</p>
				</article>
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
					<h3>Simple Query Building</h3>
				</header>
				<p>
					Search All person that live in "rue d'anton" with a mum with name "elisabeth" and order by name.
				</p>
<pre class="prettyprint lang-java">
ObjectQuery&lt;Person&gt; query = new GenericObjectQuery&lt;Person&gt;(Person.class);
Person toSearch = query.target();
query.eq(toSearch.getHome().getAddress(),"rue d'anton");
query.eq(toSearch.getMum().getName(),"elisabeth");
query.order(toSearch.getName());
</pre>
			</article>
			<article>
				<header>
					<h3>Prjection</h3>
				</header>
				<p>
					Select name and address of person.
				</p>
				<pre class="prettyprint lang-java">
ObjectQuery&lt;Person&gt; query = new GenericObjectQuery&lt;Person&gt;(Person.class);
Person toSearch = query.target();
query.prj(toSearch.getName());
query.prj(toSearch.getHome().getAddress());
</pre>
			</article>
			<article>
				<header>
					<h3>Grouping Functions<h3>
				</header>
				<p>
					Count all person that live in "rue d'anton"
				</p>
<pre class="prettyprint lang-java">
ObjectQuery&lt;Person&gt; query = new GenericObjectQuery&lt;Person&gt;(Person.class);
Person toSearch = query.target();
query.prj(toSearch,ProjectionType.COUNT);
query.eq(toSearch.getHome().getAddress(),"rue d'anton");
</pre>
			</article>
			<article>
				<header>
					<h3>Condition group</3>
				</header>
				<p>
					Search al person with name elisabeth or jhon.
				</p>
<pre class="prettyprint lang-java">
ObjectQuery&lt;Person&gt; query = new GenericObjectQuery&lt;Person&gt;(Person.class);
Person toSearch = query.target();
QueryCondition or = oq.or();
or.eq(toSearch.getName(),"elisabeth");
or.eq(toSearch.getName(),"jhon");
</pre>
			</article>
			<article>
				<header>
					<h3>Condition Operator</h3>
				<header>
				<table>
				<tr><td><p>Simple</p></td><td><p>group</p></td></tr><tr><td>
				<table class="condition_table">
					<tr><td>eq</td></tr>
					<tr><td>notEq</td></tr>
					<tr><td>like</td></tr>
					<tr><td>notLike</td></tr>
					<tr><td>min</td></tr>
					<tr><td>minEq</td></tr>
					<tr><td>max</td></tr>
					<tr><td>maxEq</td></tr>
					<tr><td>in</td></tr>
					<tr><td>notIn</td></tr>
					<tr><td>contains</td></tr>
					<tr><td>notContains</td></tr>
				</table>
				</td>
				<td>
				<table class="condition_table">
					<tr><td>or</td></tr>
					<tr><td>and</td></tr>
				</table>
				</td></tr></table>
			</article>
			<article>
				<header>
					<h3>Projection </h3>
				<header>
				<table class="condition_table">
					<tr><td>MAX</td></tr>
					<tr><td>MIN</td></tr>
					<tr><td>AVG</td></tr>
					<tr><td>COUNT</td></tr>
				</table>
			</article>
			<article>
				<header>
					<h3>Order Operator</h3>
				<header>
				<table class="condition_table">
					<tr><td>ASC</td></tr>
					<tr><td>DESC</td></tr>
				</table>
			</article>
		</section>
		<section id="query-engine"  <?echo($_GET["page"]=="query-engine"?'class="active"':'')?> >
			<header> 
				<h2>Query Engine Support</h2>
			</header>
			<article>
				<header><h3>JPA</h3></header>
				<p>
				Direct execute:
				<pre class="prettyprint lang-java">
javax.persistence.EntityManager entityManager= ....
ObjectQuery&lt;Person&gt; query = new GenericObjectQuery&lt;Person&gt;(Person.class);
...
List&lt;Person&gt; res = (List&lt;Person&gt;)JPAObjectQuery.execute(query, entityManager);
...
</pre>
				JPA query generation:
<pre class="prettyprint lang-java">
javax.persistence.EntityManager entityManager= ....
ObjectQuery&lt;Person&gt; query = new GenericObjectQuery&lt;Person&gt;(Person.class);
...
javax.persistence.Query jpaQuery = JPAObjectQuery.buildQuery(query, entityManager);
...
</pre>
				JPQL string and parameters generation:
<pre class="prettyprint lang-java">
ObjectQuery&lt;Person&gt; query = new GenericObjectQuery&lt;Person&gt;(Person.class);
...
JPQLQueryGenerator jpqlGenerator = JPAObjectQuery.jpqlGenerator(query);
String jpql = jpqlGenerator.getQuery();
Map&lt;String,Object&gt; paramenters = jpqlGenerator.getParameters();
...
</pre>
				</p>
			</article>
			<article>
				<header><h3>JDO</h3></header>
				<p>
				Direct execute:
				<pre class="prettyprint lang-java">
javax.jdo.PersistenceManager peristenceManager= ....
ObjectQuery&lt;Person&gt; query = new GenericObjectQuery&lt;Person&gt;(Person.class);
...
List&lt;Person&gt; res = (List&lt;Person&gt;)JDOObjectQuery.execute(query, peristenceManager);
...
</pre>
				JDOQL string and parameters generation:
<pre class="prettyprint lang-java">
ObjectQuery&lt;Person&gt; query = new GenericObjectQuery&lt;Person&gt;(Person.class);
...
JDOQLQueryGenerator jdoqlGenerator = JDOObjectQuery.jdoqlGenerator(query);
String jpql = jdoqlGenerator.getQuery();
Map&lt;String,Object&gt; paramenters = jdoqlGenerator.getParameters();
...
</pre>
			</p>
			</article>
		</section>
		<footer>
			<div>Object Query the java mode to build query</div>
		</footer>
	</body>
</html>
