-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_user_id` int(11) unsigned DEFAULT NULL,
  `article_title` varchar(255) DEFAULT NULL,
  `article_content` mediumtext,
  `article_view_count` int(11) DEFAULT '0',
  `article_comment_count` int(11) DEFAULT '0',
  `article_like_count` int(11) DEFAULT '0',
  `article_is_comment` int(1) unsigned DEFAULT NULL,
  `article_status` int(1) unsigned DEFAULT '1',
  `article_order` int(11) unsigned DEFAULT NULL,
  `article_update_time` datetime DEFAULT NULL,
  `article_create_time` datetime DEFAULT NULL,
  `article_summary` text,
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (4,1,'整合ssm项目','<p>&nbsp; 对于初学spring，springmvc，mybatis的人来说，最迫切的目标就是能够搭建起自己的ssm项目，最起码是使之实现基本的功能。接下来，我们将讲解一下如何搭建ssm项目。</p><p>&nbsp;<br></p>',8,0,1,1,1,1,'2020-03-02 13:41:26','2020-02-28 00:25:04','&nbsp; 对于初学spring，springmvc，mybatis的人来说，最迫切的目标就是能够搭建起自己的ssm项目，最起码是使之实现基本的功能。接下来，我们将讲解一下如何搭建ssm项目。&nbsp;'),(5,1,'游戏敏感词是如何过滤的？','<p>&nbsp;面试官：玩过王者荣耀吧？了解过<strong>敏感词过滤吗？</strong>，例如在游戏里，如果我们发送“你在干嘛？麻痹演员啊你？”，由于“麻痹”是一个敏感词，所以当你把聊天发出来之后，我们会用“**”来代表“麻痹”这次词，所以发送出来的聊天会变成这样：“你在干嘛？**演员啊你？”。</p>\r\n<p>小秋：听说过啊，在各大社区也经常看到，例如评论一个问题等，一些粗话经常被过滤掉了。</p>\r\n<p>面试官：嗯，如果我给你一段文字，以及给你一些需要过滤的敏感词，你会怎么来实现这个敏感词过滤的算法呢？例如我给你一段字符串“abcdefghi\",以及三个敏感词\"de\", \"bca\", \"bcf\"。</p>\r\n<p>小秋：（敏感词过来算法？？不就是字符串匹配吗？）我可以通过字符串匹配算法，例如在字符串”abcdefghi\"在查找是否存在字串“de\"，如果找到了就把”de“用\"**\"代替。通过三次匹配之后，接变成这样了：“abc ** fghi\"。</p>\r\n<p>面试官：可以说说你采用哪种字符串匹配算法吗？</p>\r\n<p>小秋：最简单的方法就是采用两个for循环保留求解了，不过每次匹配的都时间复杂度为O(n*m)，我可以采用 KMP 字符串匹配算法，这样时间复杂度是 O(m+n)。</p>',15,3,1,1,1,1,'2020-03-02 13:41:14','2020-02-28 08:05:42','&nbsp;面试官：玩过王者荣耀吧？了解过敏感词过滤吗？，例如在游戏里，如果我们发送“你在干嘛？麻痹演员啊你？”，由于“麻痹”是一个敏感词，所以当你把聊天发出来之后，我们会用“**”来代表“麻痹”这次词，所以发送出来的聊天会变成这样：“你在干嘛？**演员啊你？”。\r\n小秋：听说过啊，在各大社区也经常'),(6,1,'图的遍历算法(BFS/DFS)','<img src=\"/uploads/2020\\2/QQ图片20191003125620(1).gif\" alt=\"QQ图片20191003125620.gif\"><p>&nbsp;<span><strong><span><a title=\"last chapter\" href=\"http://www.cnblogs.com/edisonchou/p/4672188.html\" target=\"_blank\">上一篇</a>我们了解了图的基本概念、术语以及存储结构，还对邻接表结构进行了模拟实现。本篇我们来了解一下图的遍历，和树的遍历类似，从图的某一顶点出发访问图中其余顶点，并且使每一个顶点仅被访问一次，这一过程就叫做图的遍历（Traversing\r\n \r\nGraph）。如果只访问图的顶点而不关注边的信息，那么图的遍历十分简单，使用一个foreach语句遍历存放顶点信息的数组即可。但是，如果为了实现特定算法，就必须要根据边的信息按照一定的顺序进行遍历。图的遍历算法是求解图的连通性问题、拓扑排序和求解关键路径等算法的基础。</span></strong></span></p><p><span><strong><span><br></span></strong></span></p><p><br></p><h1>一、图的遍历</h1>\r\n<p><img src=\"https://images0.cnblogs.com/blog2015/381412/201507/260015448907668.jpg\" alt=\"\" width=\"394\" height=\"254\"></p>\r\n<p>　　图的遍历要比树的遍历复杂得多，由于图的任一顶点都可能和其余顶点相邻接，所以在访问了某顶点之后，可能顺着某条边又访问到了已访问过的顶点。因此，在图的遍历过程中，必须记下每个访问过的顶点，以免同一个顶点被访问多次。为此，给顶点附加一个访问标志isVisited，其初值为false，一旦某个顶点被访问，则将其isVisited标志设为true。</p>\r\n<div class=\"cnblogs_code\"><div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div>\r\n<pre>        <span>protected</span> <span>class</span> Vertex<tvalue><span>\r\n        {\r\n            </span><span>public</span> TValue data;     <span>//</span><span> 数据</span>\r\n            <span>public</span> Node firstEdge;  <span>//</span><span> 邻接点链表头指针</span>\r\n            <span>public</span> <span>bool</span> isVisited;  <span>//</span><span> 访问标志：遍历时使用</span>\r\n\r\n            <span>public</span><span> Vertex()\r\n            {\r\n                </span><span>this</span>.data = <span>default</span><span>(TValue);\r\n            }\r\n\r\n            </span><span>public</span><span> Vertex(TValue value)\r\n            {\r\n                </span><span>this</span>.data =<span> value;\r\n            }\r\n        }</span></tvalue></pre>\r\n<div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div></div>\r\n<p>　　在上面的顶点类的定义中，增加了一个bool类型的成员isVisited，用于在遍历时判断是否已经访问过了该顶点。一般在进行遍历操作时，会首先将所有顶点的isVisited属性置为false，于是可以写一个辅助方法InitVisited()，如下所示：</p><p>在上面的顶点类的定义中，增加了一个bool类型的成员isVisited，用于在遍历时判断是否已经访问过了该顶点。一般在进行遍历操作时，会首先将所有顶点的isVisited属性置为false，于是可以写一个辅助方法InitVisited()，如下所示：\r\n</p><div class=\"cnblogs_code\"><div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div>\r\n<pre>        <span>///</span> <span><summary>\r\n        <span>///</span><span> 辅助方法：初始化顶点的visited标志为false\r\n        </span><span>///</span> <span></span></summary></span>\r\n        <span>private</span> <span>void</span><span> InitVisited()\r\n        {\r\n            </span><span>foreach</span> (Vertex<t> v <span>in</span><span> items)\r\n            {\r\n                v.isVisited </span>= <span>false</span><span>;\r\n            }\r\n        }</span></t></pre>\r\n<div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div></div>\r\n<p>　　图的遍历方法主要有两种：一种是深度优先搜索遍历（Depth-First Search，DFS），另一种是广度优先搜索遍历（Breadth-First Search，BFS）。下面，我们就来仔细看看这两种图的遍历算法。</p>\r\n<h1>二、深度优先搜索遍历</h1>\r\n<h2>2.1 深度优先遍历原理</h2>\r\n<p>　　图的深度优先遍历类似于二叉树的深度优先遍历，其基本思想是：<strong><span>从图中某个顶点v出发，访问此顶点，然后从v的未被访问的邻接点出发深度优先遍历图，直至图中所有和v有路径相通的顶点都被访问到</span></strong>。显然，这是一个递归的搜索过程。</p>\r\n<p><img src=\"https://images0.cnblogs.com/blog2015/381412/201507/260044199214150.png\" alt=\"\" width=\"502\" height=\"186\"></p>\r\n<p>　　以上图为例，假定V1是出发点，首先访问V1。这时两个邻接点V2、V3均未被访问，可以选择V2作为新的出发点，访问V2之后，再找到V2的未访问过的邻接点。同V2邻接的有V1、V4和V5，其中V1已经访问过了，可以选择V4作为新的出发点。重复上述搜索过程，继续依次访问V8、V5。访问V5之后，由于与V5相邻的顶点均已被访问过，搜索退回到V8，访问V8的另一个邻接点V6.接下来依次访问V3和V7，最后得到的访问序列为V1→V2→V4→V8→V5→V6→V3→V7。</p>\r\n<h2>2.2 深度优先遍历实现</h2>\r\n<p>　　（1）实现代码</p>\r\n<div class=\"cnblogs_code\"><div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div>\r\n<pre>        <span>///</span> <span><summary>\r\n        <span>///</span><span> 深度优先遍历接口For连通图\r\n        </span><span>///</span> <span></span></summary></span>\r\n        <span>public</span> <span>void</span><span> DFSTraverse()\r\n        {\r\n            InitVisited(); </span><span>//</span><span> 首先初始化visited标志</span>\r\n            DFS(items[<span>0</span>]); <span>//</span><span> 从第一个顶点开始遍历</span>\r\n<span>        }\r\n\r\n        </span><span>///</span> <span><summary>\r\n        <span>///</span><span> 深度优先遍历算法\r\n        </span><span>///</span> <span></span></summary></span>\r\n        <span>///</span> <span><param name=\"v\"></span><span>顶点</span><span></span>\r\n        <span>private</span> <span>void</span> DFS(Vertex<t><span> v)\r\n        {\r\n            v.isVisited </span>= <span>true</span>; <span>//</span><span> 首先将访问标志设为true标识为已访问</span>\r\n            Console.Write(v.data.ToString() + <span>\"</span> <span>\"</span>); <span>//</span><span> 进行访问操作：这里是输出顶点data</span>\r\n            Node node =<span> v.firstEdge;\r\n\r\n            </span><span>while</span> (node != <span>null</span><span>)\r\n            {\r\n                </span><span>if</span> (node.adjvex.isVisited == <span>false</span>) <span>//</span><span> 如果邻接顶点未被访问</span>\r\n<span>                {\r\n                    DFS(node.adjvex); </span><span>//</span><span> 递归访问node的邻接顶点</span>\r\n<span>                }\r\n                node </span>= node.next; <span>//</span><span> 访问下一个邻接点</span>\r\n<span>            }\r\n        }</span></t></pre>\r\n<div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div></div>\r\n<p>　　深度优先遍历是一个典型的递归过程，这里也使用了递归的方式。</p>\r\n<p>　　（2）遍历测试</p>\r\n<p>　　这里的测试代码构造的图如下所示：</p>\r\n<p><img src=\"https://images0.cnblogs.com/blog2015/381412/201507/260044199214150.png\" alt=\"\" width=\"499\" height=\"186\"></p>\r\n<p>　　测试代码如下所示：</p>\r\n<div class=\"cnblogs_code\"><img id=\"code_img_closed_441300dc-0de4-4625-9e7a-33f289690eec\" class=\"code_img_closed\" src=\"https://images.cnblogs.com/OutliningIndicators/ContractedBlock.gif\" alt=\"\">\r\n\r\n<span class=\"cnblogs_code_collapse\">View Code</span></div>\r\n<p>　　运行结果如下图所示：</p>\r\n<p><img src=\"https://images0.cnblogs.com/blog2015/381412/201507/270004407016737.png\" alt=\"\"></p>\r\n<h1>三、广度优先搜索遍历</h1>\r\n<h2>3.1 广度优先遍历原理</h2>\r\n<p>　　图的广度优先遍历算法是一个分层遍历的过程，和二叉树的广度优先遍历类似，其基本思想在于：<span><strong>从图中的某一个顶点Vi触发，访问此顶点后，依次访问Vi的各个为层访问过的邻接点，然后分别从这些邻接点出发，直至图中所有顶点都被访问到</strong></span>。</p>\r\n<p><img src=\"https://images0.cnblogs.com/blog2015/381412/201507/270023174982248.png\" alt=\"\" width=\"582\" height=\"174\"></p>\r\n<p>　　对于上图所示的无向连通图，若从顶点V1开始，则广度优先遍历的顶点访问顺序是V1→V2→V3→V4→V5→V6→V7→V8。</p>\r\n<h2>3.2 广度优先遍历实现</h2>\r\n<p>　　（1）实现代码</p>\r\n<div class=\"cnblogs_code\"><div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div>\r\n<pre>        <span>///</span> <span><summary>\r\n        <span>///</span><span> 宽度优先遍历接口For连通图\r\n        </span><span>///</span> <span></span></summary></span>\r\n        <span>public</span> <span>void</span><span> BFSTraverse()\r\n        {\r\n            InitVisited(); </span><span>//</span><span> 首先初始化visited标志</span>\r\n            BFS(items[<span>0</span>]); <span>//</span><span> 从第一个顶点开始遍历</span>\r\n<span>        }\r\n\r\n        </span><span>///</span> <span><summary>\r\n        <span>///</span><span> 宽度优先遍历算法\r\n        </span><span>///</span> <span></span></summary></span>\r\n        <span>///</span> <span><param name=\"v\"></span><span>顶点</span><span></span>\r\n        <span>private</span> <span>void</span> BFS(Vertex<t><span> v)\r\n        {\r\n            v.isVisited </span>= <span>true</span>; <span>//</span><span> 首先将访问标志设为true标识为已访问</span>\r\n            Console.Write(v.data.ToString() + <span>\"</span> <span>\"</span>); <span>//</span><span> 进行访问操作：这里是输出顶点data</span>\r\n            Queue<vertex<t>&gt; verQueue = <span>new</span> Queue<vertex<t>&gt;(); <span>//</span><span> 使用队列存储</span>\r\n<span>            verQueue.Enqueue(v);\r\n\r\n            </span><span>while</span> (verQueue.Count &gt; <span>0</span><span>)\r\n            {\r\n                Vertex</span><t> w =<span> verQueue.Dequeue();\r\n                Node node </span>=<span> w.firstEdge;\r\n                </span><span>//</span><span> 访问此顶点的所有邻接节点</span>\r\n                <span>while</span> (node != <span>null</span><span>)\r\n                {\r\n                    </span><span>//</span><span> 如果邻接节点没有被访问过则访问它的边</span>\r\n                    <span>if</span> (node.adjvex.isVisited == <span>false</span><span>)\r\n                    {\r\n                        node.adjvex.isVisited </span>= <span>true</span>; <span>//</span><span> 设置为已访问</span>\r\n                        Console.Write(node.adjvex.data + <span>\"</span> <span>\"</span>); <span>//</span><span> 访问</span>\r\n                        verQueue.Enqueue(node.adjvex); <span>//</span><span> 入队</span>\r\n<span>                    }\r\n                    node </span>= node.next; <span>//</span><span> 访问下一个邻接点</span>\r\n<span>                }\r\n            }\r\n        }</span></t></vertex<t></vertex<t></t></pre>\r\n<div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div></div>\r\n<p>　　和树的层次遍历类似，借助了队列这一数据结构进行辅助，记录顶点的邻接点。</p>\r\n<p>　　（2）遍历测试</p>\r\n<p>　　这里构造的图如下所示，跟上面原理中的图一致：</p>\r\n<p><img src=\"https://images0.cnblogs.com/blog2015/381412/201507/270023174982248.png\" alt=\"\" width=\"582\" height=\"174\"></p>\r\n<p>　　测试代码如下所示：</p>\r\n<div class=\"cnblogs_code\"><img id=\"code_img_closed_3c818deb-b4a4-412c-9065-9cc725786bdd\" class=\"code_img_closed\" src=\"https://images.cnblogs.com/OutliningIndicators/ContractedBlock.gif\" alt=\"\">\r\n\r\n<span class=\"cnblogs_code_collapse\">View Code</span></div>\r\n<p>　　运行结果如下图所示：</p>\r\n<p><img src=\"https://images0.cnblogs.com/blog2015/381412/201507/270031043576587.png\" alt=\"\"></p>\r\n<h1>四、非连通图的遍历</h1>\r\n<p>以上讨论的图的两种遍历方法都是针对无向连通图的，它们都是从一个顶点触发就能访问到图中的所有顶点。若无方向图是非连通图，则只能访问到初始点所在连通分量中的所有顶点，其他分量中的顶点是无法访问到的。如下图所示，V6、V7以及V8三个顶点均访问不到。为此，需要从其他每个连通分量中选择初始点，分别进行遍历，才能够访问到图中的所有顶点。</p>\r\n<p><img src=\"https://images0.cnblogs.com/blog2015/381412/201507/270042080133836.png\" alt=\"\" width=\"481\" height=\"127\"></p>\r\n<h2>4.1 非连通图的深度优先遍历实现</h2>\r\n<div class=\"cnblogs_code\"><div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div>\r\n<pre>        <span>///</span> <span><summary>\r\n        <span>///</span><span> 深度优先遍历接口For非联通图\r\n        </span><span>///</span> <span></span></summary></span>\r\n        <span>public</span> <span>void</span><span> DFSTraverse4NUG()\r\n        {\r\n            InitVisited();\r\n            </span><span>foreach</span> (<span>var</span> v <span>in</span><span> items)\r\n            {\r\n                </span><span>if</span> (v.isVisited == <span>false</span><span>)\r\n                {\r\n                    DFS(v);\r\n                }\r\n            }\r\n        }</span></pre>\r\n<div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div></div>\r\n<p>　　这里DFS方法跟上面无向连通图的保持一致。</p>\r\n<h2>4.2 非连通图的广度优先遍历实现</h2>\r\n<div class=\"cnblogs_code\"><div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div>\r\n<pre>        <span>///</span> <span><summary>\r\n        <span>///</span><span> 广度优先遍历接口For非联通图\r\n        </span><span>///</span> <span></span></summary></span>\r\n        <span>public</span> <span>void</span><span> BFSTraverse4NUG()\r\n        {\r\n            InitVisited();\r\n            </span><span>foreach</span> (<span>var</span> v <span>in</span><span> items)\r\n            {\r\n                </span><span>if</span> (v.isVisited == <span>false</span><span>)\r\n                {\r\n                    BFS(v);\r\n                }\r\n            }\r\n        }</span></pre>\r\n<div class=\"cnblogs_code_toolbar\"><span class=\"cnblogs_code_copy\"><a title=\"复制代码\"><img src=\"https://common.cnblogs.com/images/copycode.gif\" alt=\"复制代码\"></a></span></div></div>\r\n<p>　　这里BFS方法跟上面无向连通图的保持一致。</p>\r\n<h2>4.3 非连通图的遍历测试</h2>\r\n<p>　　构造的图如上图所示，测试代码如下：</p>\r\n<div class=\"cnblogs_code\"><img id=\"code_img_closed_faa5c06b-ca91-4582-b143-8013f8cbd4de\" class=\"code_img_closed\" src=\"https://images.cnblogs.com/OutliningIndicators/ContractedBlock.gif\" alt=\"\">\r\n\r\n<span class=\"cnblogs_code_collapse\">View Code</span></div>\r\n<p>　　运行结果如下图所示：</p>\r\n<p><img src=\"https://images0.cnblogs.com/blog2015/381412/201507/270046511855826.png\" alt=\"\"></p>\r\n<div id=\"Copyright\"><img src=\"/uploads/2020\\2/QQ图片20200226130603.jpg\" alt=\"QQ图片20200226130603.jpg\"><br></div><p><span><strong><span></span></strong></span></p>',10,0,0,1,1,1,'2020-03-02 13:40:59','2020-02-28 08:55:04','&nbsp;上一篇我们了解了图的基本概念、术语以及存储结构，还对邻接表结构进行了模拟实现。本篇我们来了解一下图的遍历，和树的遍历类似，从图的某一顶点出发访问图中其余顶点，并且使每一个顶点仅被访问一次，这一过程就叫做图的遍历（Traversing\r\n \r\nGraph）。如果只访问图的顶点而不关注边的信'),(7,1,'ElasticSearch','<h1>0. 带着问题上路——ES是如何产生的？</h1>\r\n\r\n<h2><a name=\"t2\"></a><a name=\"t2\"></a><a name=\"t2\"></a>（1）思考：大规模数据如何检索？</h2>\r\n\r\n<p>如：当系统数据量上了10亿、100亿条的时候，我们在做系统架构的时候通常会从以下角度去考虑问题：&nbsp;<br>\r\n1）用什么数据库好？(mysql、sybase、oracle、达梦、神通、mongodb、hbase…)&nbsp;<br>\r\n2）如何解决单点故障；(lvs、F5、A10、Zookeep、MQ)&nbsp;<br>\r\n3）如何保证数据安全性；(热备、冷备、异地多活)&nbsp;<br>\r\n4）如何解决检索难题；(数据库代理中间件：mysql-proxy、Cobar、MaxScale等;)&nbsp;<br>\r\n5）如何解决统计分析问题；(离线、近实时)</p>\r\n\r\n<h2><a name=\"t3\"></a><a name=\"t3\"></a><a name=\"t3\"></a>（2）传统数据库的应对解决方案</h2>\r\n\r\n<p>对于关系型数据，我们通常采用以下或类似架构去解决查询瓶颈和写入瓶颈：&nbsp;<br>\r\n解决要点：&nbsp;<br>\r\n1）通过主从备份解决数据安全性问题；&nbsp;<br>\r\n2）通过数据库代理中间件心跳监测，解决单点故障问题；&nbsp;<br>\r\n3）通过代理中间件将查询语句分发到各个slave节点进行查询，并汇总结果&nbsp;<br><img alt=\"这里写图片描述\" class=\"has\" src=\"https://img-blog.csdn.net/20160818205837877\"></p>\r\n\r\n<h2><a name=\"t4\"></a><a name=\"t4\"></a><a name=\"t4\"></a>（3）非关系型数据库的解决方案</h2>\r\n\r\n<p>对于Nosql数据库，以mongodb为例，其它原理类似：&nbsp;<br>\r\n解决要点：&nbsp;<br>\r\n1）通过副本备份保证数据安全性；&nbsp;<br>\r\n2）通过节点竞选机制解决单点问题；&nbsp;<br>\r\n3）先从配置库检索分片信息，然后将请求分发到各个节点，最后由路由节点合并汇总结果&nbsp;<br><img alt=\"这里写图片描述\" class=\"has\" src=\"https://img-blog.csdn.net/20160818205852190\"></p>\r\n\r\n<h2><a name=\"t5\"></a><a name=\"t5\"></a><a name=\"t5\"></a>另辟蹊径——完全把数据放入内存怎么样？</h2>\r\n\r\n<p>我们知道，完全把数据放在内存中是不可靠的，实际上也不太现实，当我们的数据达到PB级别时，按照每个节点96G内存计算，在内存完全装满的数据情况下，我们需要的机器是：1PB=1024T=1048576G&nbsp;<br>\r\n节点数=1048576/96=10922个&nbsp;<br>\r\n实际上，考虑到数据备份，节点数往往在2.5万台左右。成本巨大决定了其不现实！</p>\r\n\r\n<p>从前面讨论我们了解到，把数据放在内存也好，不放在内存也好，都不能完完全全解决问题。&nbsp;<br>\r\n全部放在内存速度问题是解决了，但成本问题上来了。&nbsp;<br>\r\n为解决以上问题，从源头着手分析，通常会从以下方式来寻找方法：&nbsp;<br>\r\n1、存储数据时按有序存储；&nbsp;<br>\r\n2、将数据和索引分离；&nbsp;<br>\r\n3、压缩数据；&nbsp;<br>\r\n这就引出了Elasticsearch。</p>\r\n\r\n<h1><a name=\"t6\"></a><a name=\"t6\"></a><a name=\"t6\"></a>1. ES 基础一网打尽</h1>\r\n\r\n<h2><a name=\"t7\"></a><a name=\"t7\"></a><a name=\"t7\"></a>1.1 ES定义</h2>\r\n\r\n<p>ES=elaticsearch简写， Elasticsearch是一个开源的高扩展的分布式全文检索引擎，它可以近乎实时的存储、检索数据；本身扩展性很好，可以扩展到上百台服务器，处理PB级别的数据。&nbsp;<br>\r\nElasticsearch也使用Java开发并使用Lucene作为其核心来实现所有索引和搜索的功能，但是它的目的是通过简单的RESTful API来隐藏Lucene的复杂性，从而让全文搜索变得简单。</p>\r\n\r\n<h2><a name=\"t8\"></a><a name=\"t8\"></a><a name=\"t8\"></a>1.2 Lucene与ES关系？</h2>\r\n\r\n<p>1）Lucene只是一个库。想要使用它，你必须使用Java来作为开发语言并将其直接集成到你的应用中，更糟糕的是，Lucene非常复杂，你需要深入了解检索的相关知识来理解它是如何工作的。</p>\r\n\r\n<p>2）Elasticsearch也使用Java开发并使用Lucene作为其核心来实现所有索引和搜索的功能，但是它的目的是通过简单的RESTful API来隐藏Lucene的复杂性，从而让全文搜索变得简单。</p>\r\n\r\n<h2><a name=\"t9\"></a><a name=\"t9\"></a><a name=\"t9\"></a>1.3 ES主要解决问题：</h2>\r\n\r\n<p>1）检索相关数据；&nbsp;<br>\r\n2）返回统计结果；&nbsp;<br>\r\n3）速度要快。</p>\r\n\r\n<h2><a name=\"t10\"></a><a name=\"t10\"></a><a name=\"t10\"></a>1.4 ES工作原理</h2>\r\n\r\n<p>当ElasticSearch的节点启动后，它会利用多播(multicast)(或者单播，如果用户更改了配置)寻找集群中的其它节点，并与之建立连接。这个过程如下图所示：&nbsp;<br><img alt=\"这里写图片描述\" class=\"has\" src=\"https://img-blog.csdn.net/20160818205953345\"></p>\r\n\r\n<h2><a name=\"t11\"></a><a name=\"t11\"></a><a name=\"t11\"></a>1.5 ES核心概念</h2>\r\n\r\n<h3><a name=\"t12\"></a><a name=\"t12\"></a><a name=\"t12\"></a>1）Cluster：集群。</h3>\r\n\r\n<p>ES可以作为一个独立的单个搜索服务器。不过，为了处理大型数据集，实现容错和高可用性，ES可以运行在许多互相合作的服务器上。这些服务器的集合称为集群。</p>\r\n\r\n<h3><a name=\"t13\"></a><a name=\"t13\"></a><a name=\"t13\"></a>2）Node：节点。</h3>\r\n\r\n<p>形成集群的每个服务器称为节点。</p>\r\n\r\n<h3><a name=\"t14\"></a><a name=\"t14\"></a><a name=\"t14\"></a>3）Shard：分片。</h3>\r\n\r\n<p>当有大量的文档时，由于内存的限制、磁盘处理能力不足、无法足够快的响应客户端的请求等，一个节点可能不够。这种情况下，数据可以分为较小的分片。每个分片放到不同的服务器上。&nbsp;<br>\r\n当你查询的索引分布在多个分片上时，ES会把查询发送给每个相关的分片，并将结果组合在一起，而应用程序并不知道分片的存在。即：这个过程对用户来说是透明的。</p>\r\n\r\n<h3><a name=\"t15\"></a><a name=\"t15\"></a><a name=\"t15\"></a>4）Replia：副本。</h3>\r\n\r\n<p>为提高查询吞吐量或实现高可用性，可以使用分片副本。&nbsp;<br>\r\n副本是一个分片的精确复制，每个分片可以有零个或多个副本。ES中可以有许多相同的分片，其中之一被选择更改索引操作，这种特殊的分片称为主分片。&nbsp;<br>\r\n当主分片丢失时，如：该分片所在的数据不可用时，集群将副本提升为新的主分片。</p>\r\n\r\n<h3><a name=\"t16\"></a><a name=\"t16\"></a><a name=\"t16\"></a>5）全文检索。</h3>\r\n\r\n<p>全文检索就是对一篇文章进行索引，可以根据关键字搜索，类似于mysql里的like语句。&nbsp;<br>\r\n全文索引就是把内容根据词的意义进行分词，然后分别创建索引，例如”你们的激情是因为什么事情来的” 可能会被分词成：“你们“，”激情“，“什么事情“，”来“ 等token，这样当你搜索“你们” 或者 “激情” 都会把这句搜出来。</p>\r\n\r\n<h2><a name=\"t17\"></a><a name=\"t17\"></a><a name=\"t17\"></a>1.6 ES数据架构的主要概念（与关系数据库Mysql对比）</h2>\r\n\r\n<p><img alt=\"这里写图片描述\" class=\"has\" src=\"https://img-blog.csdn.net/20160818210034345\">&nbsp;<br>\r\n（1）关系型数据库中的数据库（DataBase），等价于ES中的索引（Index）&nbsp;<br>\r\n（2）一个数据库下面有N张表（Table），等价于1个索引Index下面有N多类型（Type），&nbsp;<br>\r\n（3）一个数据库表（Table）下的数据由多行（ROW）多列（column，属性）组成，等价于1个Type由多个文档（Document）和多Field组成。&nbsp;<br>\r\n（4）在一个关系型数据库里面，schema定义了表、每个表的字段，还有表和字段之间的关系。 \r\n与之对应的，在ES中：Mapping定义索引下的Type的字段处理规则，即索引如何建立、索引类型、是否保存原始索引JSON文档、是否压缩原始JSON文档、是否需要分词处理、如何进行分词处理等。&nbsp;<br>\r\n（5）在数据库中的增insert、删delete、改update、查search操作等价于ES中的增PUT/POST、删Delete、改_update、查GET.</p>\r\n\r\n<h2><a name=\"t18\"></a><a name=\"t18\"></a><a name=\"t18\"></a>1.7 ELK是什么？</h2>\r\n\r\n<p>ELK=elasticsearch+Logstash+kibana&nbsp;<br>\r\nelasticsearch：后台分布式存储以及全文检索&nbsp;<br>\r\nlogstash: 日志加工、“搬运工”&nbsp;<br>\r\nkibana：数据可视化展示。&nbsp;<br>\r\nELK架构为数据分布式存储、可视化查询和日志解析创建了一个功能强大的管理链。 三者相互配合，取长补短，共同完成分布式大数据处理工作。</p>\r\n\r\n<h1><a name=\"t19\"></a><a name=\"t19\"></a><a name=\"t19\"></a>2. ES特点和优势</h1>\r\n\r\n<p>1）分布式实时文件存储，可将每一个字段存入索引，使其可以被检索到。&nbsp;<br>\r\n2）实时分析的分布式搜索引擎。&nbsp;<br>\r\n分布式：索引分拆成多个分片，每个分片可有零个或多个副本。集群中的每个数据节点都可承载一个或多个分片，并且协调和处理各种操作；&nbsp;<br>\r\n负载再平衡和路由在大多数情况下自动完成。&nbsp;<br>\r\n3）可以扩展到上百台服务器，处理PB级别的结构化或非结构化数据。也可以运行在单台PC上（已测试）&nbsp;<br>\r\n4）支持插件机制，分词插件、同步插件、Hadoop插件、可视化插件等。</p>\r\n\r\n<h1><a name=\"t20\"></a><a name=\"t20\"></a><a name=\"t20\"></a>3、ES性能</h1>\r\n\r\n<h2><a name=\"t21\"></a><a name=\"t21\"></a><a name=\"t21\"></a>3.1 性能结果展示</h2>\r\n\r\n<p>（1）硬件配置：&nbsp;<br>\r\nCPU 16核 AuthenticAMD&nbsp;<br>\r\n内存 总量：32GB&nbsp;<br>\r\n硬盘 总量：500GB 非SSD</p>\r\n\r\n<p>（2）在上述硬件指标的基础上测试性能如下：&nbsp;<br>\r\n1）平均索引吞吐量： 12307docs/s（每个文档大小：40B/docs）&nbsp;<br>\r\n2）平均CPU使用率： 887.7%（16核，平均每核：55.48%）&nbsp;<br>\r\n3）构建索引大小： 3.30111 GB&nbsp;<br>\r\n4）总写入量： 20.2123 GB&nbsp;<br>\r\n5）测试总耗时： 28m 54s.</p>\r\n\r\n<h2><a name=\"t22\"></a><a name=\"t22\"></a><a name=\"t22\"></a>3.2 性能esrally工具（推荐）</h2>\r\n\r\n<p>使用参考：<a href=\"http://blog.csdn.net/laoyang360/article/details/52155481\">http://blog.csdn.net/laoyang360/article/details/52155481</a></p>\r\n\r\n<h1><a name=\"t23\"></a><a name=\"t23\"></a><a name=\"t23\"></a>4、为什么要用ES？</h1>\r\n\r\n<h2><a name=\"t24\"></a><a name=\"t24\"></a><a name=\"t24\"></a>4.1 ES国内外使用优秀案例</h2>\r\n\r\n<p>1） 2013年初，GitHub抛弃了Solr，采取ElasticSearch 来做PB级的搜索。 “GitHub使用ElasticSearch搜索20TB的数据，包括13亿文件和1300亿行代码”。</p>\r\n\r\n<p>2）维基百科：启动以elasticsearch为基础的核心搜索架构。&nbsp;<br>\r\n3）SoundCloud：“SoundCloud使用ElasticSearch为1.8亿用户提供即时而精准的音乐搜索服务”。&nbsp;<br>\r\n4）百度：百度目前广泛使用ElasticSearch作为文本数据分析，采集百度所有服务器上的各类指标数据及用户自定义数据，通过对各种数据进行多维分析展示，辅助定位分析实例异常或业务层面异常。目前覆盖百度内部20多个业务线（包括casio、云分析、网盟、预测、文库、直达号、钱包、风控等），单集群最大100台机器，200个ES节点，每天导入30TB+数据。</p>\r\n\r\n<h2><a name=\"t25\"></a><a name=\"t25\"></a><a name=\"t25\"></a>4.2 我们也需要</h2>\r\n\r\n<p>实际项目开发实战中，几乎每个系统都会有一个搜索的功能，当搜索做到一定程度时，维护和扩展起来难度就会慢慢变大，所以很多公司都会把搜索单独独立出一个模块，用ElasticSearch等来实现。</p>\r\n\r\n<p>近年ElasticSearch发展迅猛，已经超越了其最初的纯搜索引擎的角色，现在已经增加了数据聚合分析（aggregation）和可视化的特性，如果你有数百万的文档需要通过关键词进行定位时，ElasticSearch肯定是最佳选择。当然，如果你的文档是JSON的，你也可以把ElasticSearch当作一种“NoSQL数据库”，\r\n 应用ElasticSearch数据聚合分析（aggregation）的特性，针对数据进行多维度的分析。</p>\r\n\r\n<p>【知乎：热酷架构师潘飞】ES在某些场景下替代传统DB&nbsp;<br>\r\n个人以为Elasticsearch作为内部存储来说还是不错的，效率也基本能够满足，在某些方面替代传统DB也是可以的，前提是你的业务不对操作的事性务有特殊要求；而权限管理也不用那么细，因为ES的权限这块还不完善。&nbsp;<br>\r\n由于我们对ES的应用场景仅仅是在于对某段时间内的数据聚合操作，没有大量的单文档请求（比如通过userid来找到一个用户的文档，类似于NoSQL的应用场景），所以能否替代NoSQL还需要各位自己的测试。&nbsp;<br>\r\n如果让我选择的话，我会尝试使用ES来替代传统的NoSQL，因为它的横向扩展机制太方便了。</p>\r\n\r\n<h1><a name=\"t26\"></a><a name=\"t26\"></a><a name=\"t26\"></a>5. ES的应用场景是怎样的？</h1>\r\n\r\n<h2><a name=\"t27\"></a><a name=\"t27\"></a><a name=\"t27\"></a>通常我们面临问题有两个：</h2>\r\n\r\n<p>1）新系统开发尝试使用ES作为存储和检索服务器；&nbsp;<br>\r\n2）现有系统升级需要支持全文检索服务，需要使用ES。&nbsp;<br>\r\n以上两种架构的使用，以下链接进行详细阐述。&nbsp;<br><a href=\"http://blog.csdn.net/laoyang360/article/details/52227541\">http://blog.csdn.net/laoyang360/article/details/52227541</a></p>\r\n\r\n<h2><a name=\"t28\"></a><a name=\"t28\"></a><a name=\"t28\"></a>一线公司ES使用场景：</h2>\r\n\r\n<p>1）新浪ES 如何分析处理32亿条实时日志&nbsp;<a href=\"http://dockone.io/article/505\" rel=\"nofollow\">http://dockone.io/article/505</a>&nbsp;<br>\r\n2）阿里ES 构建挖财自己的日志采集和分析体系&nbsp;<a href=\"http://afoo.me/columns/tec/logging-platform-spec.html\" rel=\"nofollow\">http://afoo.me/columns/tec/logging-platform-spec.html</a>&nbsp;<br>\r\n3）有赞ES 业务日志处理&nbsp;<a href=\"http://tech.youzan.com/you-zan-tong-ri-zhi-ping-tai-chu-tan/\" rel=\"nofollow\">http://tech.youzan.com/you-zan-tong-ri-zhi-ping-tai-chu-tan/</a>&nbsp;<br>\r\n4）ES实现站内搜索&nbsp;<a href=\"http://www.wtoutiao.com/p/13bkqiZ.html\" rel=\"nofollow\">http://www.wtoutiao.com/p/13bkqiZ.html</a></p>\r\n\r\n<h1><a name=\"t29\"></a><a name=\"t29\"></a><a name=\"t29\"></a>6. 如何部署ES？</h1>\r\n\r\n<h2><a name=\"t30\"></a><a name=\"t30\"></a><a name=\"t30\"></a>6.1 ES部署（无需安装）</h2>\r\n\r\n<p>1）零配置，开箱即用&nbsp;<br>\r\n2）没有繁琐的安装配置&nbsp;<br>\r\n3）java版本要求：最低1.7&nbsp;<br>\r\n我使用的1.8&nbsp;<br>\r\n[root@laoyang config_lhy]# echo $JAVA_HOME&nbsp;<br>\r\n/opt/jdk1.8.0_91&nbsp;<br>\r\n4）下载地址：&nbsp;<br><a href=\"https://download.elastic.co/elasticsearch/release/org/elasticsearch/distribution/zip/elasticsearch/2.3.5/elasticsearch-2.3.5.zip\" rel=\"nofollow\">https://download.elastic.co/elasticsearch/release/org/elasticsearch/distribution/zip/elasticsearch/2.3.5/elasticsearch-2.3.5.zip</a>&nbsp;<br>\r\n5）启动&nbsp;<br>\r\ncd /usr/local/elasticsearch-2.3.5&nbsp;<br>\r\n./bin/elasticsearch&nbsp;<br>\r\nbin/elasticsearch -d(后台运行)</p>\r\n\r\n<h2><a name=\"t31\"></a><a name=\"t31\"></a><a name=\"t31\"></a>6.2 ES必要的插件</h2>\r\n\r\n<p>必要的Head、kibana、IK（中文分词）、graph等插件的详细安装和使用。&nbsp;<br><a href=\"http://blog.csdn.net/column/details/deep-elasticsearch.html\">http://blog.csdn.net/column/details/deep-elasticsearch.html</a></p>\r\n\r\n<h2><a name=\"t32\"></a><a name=\"t32\"></a><a name=\"t32\"></a>6.3 ES windows下一键安装</h2>\r\n\r\n<p>自写bat脚本实现windows下一键安装。&nbsp;<br>\r\n1）一键安装ES及必要插件（head、kibana、IK、logstash等）&nbsp;<br>\r\n2）安装后以服务形式运行ES。&nbsp;<br>\r\n3）比自己摸索安装节省至少2小时时间，效率非常高。&nbsp;<br>\r\n脚本说明：&nbsp;<br><a href=\"http://blog.csdn.net/laoyang360/article/details/51900235\">http://blog.csdn.net/laoyang360/article/details/51900235</a></p>\r\n\r\n<h1><a name=\"t33\"></a><a name=\"t33\"></a><a name=\"t33\"></a>7. ES对外接口（开发人员关注）</h1>\r\n\r\n<h2><a name=\"t34\"></a><a name=\"t34\"></a><a name=\"t34\"></a>1）JAVA API接口</h2>\r\n\r\n<p><a href=\"http://www.ibm.com/developerworks/library/j-use-elasticsearch-java-apps/index.html\" rel=\"nofollow\">http://www.ibm.com/developerworks/library/j-use-elasticsearch-java-apps/index.html</a></p>\r\n\r\n<h2><a name=\"t35\"></a><a name=\"t35\"></a><a name=\"t35\"></a>2）RESTful API接口</h2>\r\n\r\n<p>常见的增、删、改、查操作实现：&nbsp;<br><a href=\"http://blog.csdn.net/laoyang360/article/details/51931981\">http://blog.csdn.net/laoyang360/article/details/51931981</a></p>',16,0,2,1,1,1,'2020-03-02 13:40:42','2020-02-28 12:01:56','0. 带着问题上路——ES是如何产生的？\r\n\r\n（1）思考：大规模数据如何检索？\r\n\r\n如：当系统数据量上了10亿、100亿条的时候，我们在做系统架构的时候通常会从以下角度去考虑问题：&nbsp;\r\n1）用什么数据库好？(mysql、sybase、oracle、达梦、神通、mongodb、hbase'),(8,1,'操作系统基础','<h1 class=\"postTitle\">\r\n                \r\n<a id=\"cb_post_title_url\" class=\"postTitle2\" href=\"https://www.cnblogs.com/DrunkYouth/p/10633271.html\">操作系统基础知识总结（连载）</a>\r\n\r\n            </h1><div class=\"clear\"></div><p>\r\n            \r\n            </p><div class=\"postBody\">\r\n                \r\n<div id=\"cnblogs_post_body\" class=\"blogpost-body \">\r\n    <p>-------------------------------------------------------第一章------------------------------------------------------------------</p>\r\n<ul>\r\n<li>操作系统的定义：控制和管理计算机软硬件资源、合理组织计算机工作流程，以方便用户使用计算机的程序的集合。</li>\r\n<li>操作系统的目标：方便性、有效性、可扩充性、开放性</li>\r\n<li>操作系统的五个基本功能：存储管理、处理机管理、设备管理、文件管理、用户接口</li>\r\n<li>操作系统的发展过程：</li>\r\n</ul>\r\n<p>　　　　<strong>未配置操作系统的计算机系统：</strong>人工操作方式、脱机输入输出方式</p>\r\n<p>　　　　<strong>批处理系统：</strong>单打批处理系统、多道批处理系统</p>\r\n<p>　　　　<strong>分时系统：</strong>分时系统的特征：多路性，交互性，独立性，及时性</p>\r\n<p>　　　　<strong>实时系统：</strong>实时任务的类型:周期性实时任务/非周期性实时任务、硬实时任务/软实时任务</p>\r\n<p>　　　　　　　　　实时系统的特征：多路性、交互性、独立性、及时性、可靠性</p>\r\n<p>　　　　<strong>微机操作系统：</strong>单用户单任务操作系统、单用户多任务操作系统、多用户多任务操作系统</p>\r\n<p>　　　　<strong>多处理机操作系统：</strong>模式：非对称多处理模式、对称多处理模式</p>\r\n<p>　　　　<strong>网络操作系统：</strong>模式：客户-服务器模式（CS模式）、对等模式（P2P模式）</p>\r\n<p>　　　　<strong>分布式操作系统：</strong>与网络OS的比较：分布性、并行性、透明性、共享性、健壮性</p>\r\n<p>　　　　<strong>嵌入式操作系统</strong></p>\r\n<ul>\r\n<li>操作系统的五个基本特征：并发性、共享性、虚拟性、异步性、随机性</li>\r\n<li>操作系统结构：传统结构：无结构OS（第一代）、模块化OS（第二代）、分层式结构OS（第三式）</li>\r\n</ul>\r\n<p>　　　　　　　　　　现代结构：微内核结构OS</p>\r\n<p>　　<img src=\"https://img2018.cnblogs.com/blog/1599651/201903/1599651-20190331170052641-2033542191.png\" alt=\"\" width=\"464\" height=\"309\"></p>\r\n<p>-------------------------------------------------------第二章------------------------------------------------------------------</p>\r\n<ul>\r\n<li>前趋图：有向无环图</li>\r\n<li>程序顺序执行：特征：顺序性、封闭性、可再现性</li>\r\n<li>程序并发执行：只有不存在前趋关系的程序之间才有可能并发执行。</li>\r\n</ul>\r\n<p>　　　　　　　　　　特征：间断性、失去封闭性、不可再现性</p>\r\n<ul>\r\n<li>进程：一个具有一定独立功能的可并发执行的程序，在一个数据集合上的运行过程。</li>\r\n<li>进程的特征：动态性、并发性、独立性、制约性、异步性、结构特征</li>\r\n<li>进程的结构特征：进程=程序+数据+PCB</li>\r\n<li>进程控制块（PCB）：为了使参加并发执行的每个程序（含数据）都能独立的运行，在操作系统中为之配置的一个专门的数据结构。PCB是进程存在的唯一标志。</li>\r\n</ul>\r\n<p>　　　　　　PCB的组织方式：线性方式、链接方式、索引方式</p>\r\n<ul>\r\n<li>进程的三种基本状态：就绪状态（Ready）、执行状态（Running）、阻塞状态（Block）</li>\r\n</ul>\r\n<p>　　　　　&nbsp; &nbsp; &nbsp; &nbsp;新引入状态：创建状态（New）、终止状态（Teminated）</p>\r\n<p>　　&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;<img src=\"https://img2018.cnblogs.com/blog/1599651/201903/1599651-20190331174955199-1744227789.png\" alt=\"\" width=\"506\" height=\"250\">&nbsp;</p>\r\n<p>　　　　　　　新操作引入：挂起、激活。新操作的引入使得：就绪-&gt;活动就绪、静止就绪</p>\r\n<p>　　　　　　　　　　　　　　　　　　　　　　　　　　　　阻塞-&gt;活动阻塞、静止阻塞</p>\r\n<ul>\r\n<li>引起进程创建的事件：用户登录、作业调度、提供服务、应用请求</li>\r\n<li>进程的创建：申请空白PCB、为新进程分配资源、初始化PCB、将新进程插入就绪队列</li>\r\n<li>进程的终止：正常结束、异常结束、外界干预</li>\r\n</ul>\r\n<p>　　　　<img src=\"https://img2018.cnblogs.com/blog/1599651/201903/1599651-20190331180722760-534409376.png\" alt=\"\" width=\"520\" height=\"296\"></p>\r\n<ul>\r\n<li>线程的定义：进程中的一个实体，是能够被系统独立调度和分配的基本单位。线程是进程的一个组成部分，线程由进程创建，一个进程中至少存在一个线程，线程还可以创建其他线程。可以当作轻型进程为线程，重型进程为进程。</li>\r\n<li>线程的组成（NT为例）：唯一的标识：客户ID、一组处理及状态寄存器、分别在用户态和核心态下使用的两个堆栈、一个私用寄存器。</li>\r\n<li>线程的实现方式：内核支持线程KST、用户级线程ULT、组合方式</li>\r\n</ul>\r\n<p>　　<img src=\"https://img2018.cnblogs.com/blog/1599651/201903/1599651-20190331182315227-1516141241.png\" alt=\"\" width=\"571\" height=\"262\"></p>\r\n<p>-------------------------------------------------------第三章------------------------------------------------------------------</p>\r\n<ul>\r\n<li>进程同步：对多个相关进程在执行次序上进行协调，使并发执行的诸进程之间能按照一定的规则或时序共享系统资源。</li>\r\n<li>进程关系：间接制约/互斥关系、直接制约/同步关系</li>\r\n<li>临界资源：一次仅允许一个进程使用的资源。死锁就是在进程申请同时使用多个临界资源时候，临界资源处理不当的情况。</li>\r\n<li>临界区：进程中访问临界资源的代码成为临界区</li>\r\n<li>同步机制应遵循的规则：空闲让进、忙则等待、有限等待、让权等待</li>\r\n互斥问题的解决办法：（总体上可以分为硬件解决，信号量和管程，以下为详细展开）可以将临界区的标准看成是一个锁，显然为了防止多个进程同时测试大锁为打开的情况，测试和关锁必须是连续的，不允许分开进行。\r\n<ul>\r\n<li>软件方法：有一定难度并且有局限性</li>\r\n<li>关中断：实现互斥最简单办法</li>\r\n<li>TS实现互斥：借助一条硬件指令，这条指令可以看作一个函数过程，执行过程不可分割及一条原语。</li>\r\n<li>利用Swap指令进行互斥：有效，但是其他访问不停地进行测试，处于忙等状态，不符合让权等待的原则。</li>\r\n</ul>\r\n<li>&nbsp;信号量机制：\r\n<ul>\r\n<li>整型信号量：一个用于表示资源数目的整型量S，除初始化外，仅能通过两个标准的原子操作&nbsp; wait(S)&nbsp; 和&nbsp; signal(S) 来访问。这两个操作被称为PV操作。因为是两个原子操作，它们在执行时是不可中断的。</li>\r\n<li>记录型信号量：整型信号量的wait操作并未遵循让权等待而是忙等状态。记录型对其进行了改善。在信号量机制中除了一个用于代表资源数目的整型变量value还增加了进程链表指针list。</li>\r\n<li>AND型信号量：前面的情况都是多个进程共享一个临界资源，但现实往往是多个进场需要多个共享资源。显然进程要求共享的临界资源越多，发生进程死锁的可能性越大。</li>\r\n</ul>\r\n</li>\r\n</ul></div></div>',7,0,1,1,1,1,'2020-03-03 05:03:15','2020-03-03 05:03:15','\r\n                \r\n操作系统基础知识总结（连载）\r\n\r\n            \r\n            \r\n            \r\n                \r\n\r\n    ----------------------------------------------'),(9,1,'深入理解JVM内存模型','<div><div><h2>1 CPU和内存的交互</h2>\r\n<p>了解jvm内存模型前，了解下cpu和计算机内存的交互情况。【因为Java虚拟机内存模型定义的访问操作与计算机十分相似】</p>\r\n<p>有篇很棒的文章，从cpu讲到内存模型:<a href=\"https://www.jianshu.com/p/bf158fbb2432\" target=\"_blank\">什么是java内存模型</a></p>\r\n<hr>\r\n<p>在计算机中，cpu和内存的交互最为频繁，相比内存，磁盘读写太慢，内存相当于高速的缓冲区。</p>\r\n<p>但是随着cpu的发展，内存的读写速度也远远赶不上cpu。因此cpu厂商在每颗cpu上加上高速缓存，用于缓解这种情况。现在cpu和内存的交互大致如下。</p>\r\n<div class=\"image-package\">\r\n<div class=\"image-container\">\r\n<div class=\"image-container-fill\"></div>\r\n<div class=\"image-view\" data-width=\"750\" data-height=\"416\"><img data-original-src=\"//upload-images.jianshu.io/upload_images/10006199-d3fc8462f127a2c7.jpg\" data-original-width=\"750\" data-original-height=\"416\" data-original-format=\"image/jpeg\" data-original-filesize=\"70703\" data-image-index=\"0\" class=\"\" src=\"//upload-images.jianshu.io/upload_images/10006199-d3fc8462f127a2c7.jpg?imageMogr2/auto-orient/strip|imageView2/2/w/750\"></div>\r\n</div>\r\n<div class=\"image-caption\">cpu、缓存、内存</div>\r\n</div>\r\n<p>cpu上加入了高速缓存这样做解决了处理器和内存的矛盾(一快一慢)，但是引来的新的问题 - <strong>缓存一致性</strong></p>\r\n<p>在多核cpu中，每个处理器都有各自的高速缓存(L1,L2,L3)，而主内存确只有一个 。</p>\r\n<div class=\"_2Uzcx_\"></div></div><br><br>作者：Garwer<br>链接：https://www.jianshu.com/p/76959115d486<br>来源：简书<br>著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。</div>',13,1,2,1,1,1,'2020-03-08 09:59:52','2020-03-03 05:05:49','1 CPU和内存的交互\r\n了解jvm内存模型前，了解下cpu和计算机内存的交互情况。【因为Java虚拟机内存模型定义的访问操作与计算机十分相似】\r\n有篇很棒的文章，从cpu讲到内存模型:什么是java内存模型\r\n\r\n在计算机中，cpu和内存的交互最为频繁，相比内存，磁盘读写太慢，内存相当于高速的缓冲'),(10,1,'如何备战春招？','<img src=\"/uploads/2020\\3/H3OH3{FDE@@JY)TS(HANCX6.gif\" alt=\"H3OH3{FDE@@JY)TS(HANCX6.gif\">',16,0,3,1,1,1,'2020-03-08 09:42:07','2020-03-08 09:42:07',''),(11,1,'测试mysql于elasticsearch是否同步','<p>但愿成功！</p>',9,0,5,1,1,1,'2020-03-12 07:48:53','2020-03-12 07:48:53','但愿成功！'),(12,19,'TEST异步消息','&nbsp;测试异步消息能否发送成功！',3,0,3,1,1,1,'2020-06-19 06:26:39','2020-06-19 03:22:37','&nbsp;测试异步消息能否发送成功！'),(13,18,'测试es与mysql是不是实时同步','&nbsp;Test：es是不是和mysql实时同步',3,0,0,1,1,1,'2020-06-19 10:41:39','2020-06-19 10:41:39','&nbsp;Test：es是不是和mysql实时同步');
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_category_ref`
--

DROP TABLE IF EXISTS `article_category_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `article_category_ref` (
  `article_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_category_ref`
--

LOCK TABLES `article_category_ref` WRITE;
/*!40000 ALTER TABLE `article_category_ref` DISABLE KEYS */;
INSERT INTO `article_category_ref` VALUES (7,17),(7,21),(6,17),(6,20),(5,17),(5,20),(4,13),(4,15),(8,17),(8,18),(10,13),(10,16),(9,22),(9,23),(11,17),(11,21),(12,17),(12,21),(13,13),(13,14);
/*!40000 ALTER TABLE `article_category_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_tag_ref`
--

DROP TABLE IF EXISTS `article_tag_ref`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `article_tag_ref` (
  `article_id` int(11) NOT NULL,
  `tag_id` int(11) NOT NULL,
  PRIMARY KEY (`article_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_tag_ref`
--

LOCK TABLES `article_tag_ref` WRITE;
/*!40000 ALTER TABLE `article_tag_ref` DISABLE KEYS */;
INSERT INTO `article_tag_ref` VALUES (4,2),(5,4),(6,4),(7,5),(8,6),(9,2),(10,2),(11,5),(12,2),(13,2),(13,3);
/*!40000 ALTER TABLE `article_tag_ref` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `category_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `category_pid` int(11) DEFAULT NULL,
  `category_name` varchar(50) DEFAULT NULL,
  `category_description` varchar(255) DEFAULT NULL,
  `category_order` int(11) unsigned DEFAULT '1',
  `category_icon` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `category_name` (`category_name`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (10,8,'ElasticSearch','分布式数据库',NULL,'fa fa-collee'),(11,8,'mysql','基本数据库',NULL,'fa fa-coffee'),(13,0,'Java','java系列知识',NULL,'fa fa-coffee'),(14,13,'Java基础','java基础知识',NULL,'fa -fa coffee'),(15,13,'Java框架','spring，springboot，springmvc',NULL,'fa fa-coffee'),(16,13,'Java面试','java面试宝典',NULL,'fa fa-coffee'),(17,0,'计算机科学','计算机学习基础',NULL,'fa fa-cubes'),(18,17,'操作系统','计算机底层的知识',NULL,'fa fa-cubes'),(19,17,'计算机网络','网络知识',NULL,'fa fa-cubes'),(20,17,'数据结构和算法','后台必须的',NULL,'fa fa-cubes'),(21,17,'数据库','数据的存储环境',NULL,'fa fa-cubes'),(22,0,'Java高级篇','描述高级应用',NULL,'fa fa-th-large'),(23,22,'JVM虚拟机','java运行环境',NULL,'fa-th-large'),(24,0,'其他技术','计算机行业的其他技术',NULL,'fa fa-bookmark');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
  `comment_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `comment_pid` int(11) unsigned DEFAULT '0',
  `comment_pname` varchar(255) DEFAULT NULL,
  `comment_article_id` int(11) unsigned DEFAULT NULL,
  `comment_author_name` varchar(50) DEFAULT NULL,
  `comment_author_email` varchar(50) DEFAULT NULL,
  `comment_author_url` varchar(50) DEFAULT NULL,
  `comment_author_avatar` varchar(100) DEFAULT NULL,
  `comment_content` varchar(1000) DEFAULT NULL,
  `comment_agent` varchar(200) DEFAULT NULL,
  `comment_ip` varchar(50) DEFAULT NULL,
  `comment_create_time` datetime DEFAULT NULL,
  `comment_role` int(1) DEFAULT NULL,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,0,'',5,'代码，出来挨打','2409711401@qq.com','http://www.baidu.com','http://cn/gravatar.com/avatar/dc5e92070d988d0b0bbab9912c1aca9e?s=128&d=identicon&r=PG','讲的太好了，牛逼。',NULL,'0:0:0:0:0:0:0:1','2020-02-28 08:40:44',0),(3,0,'',5,'代码，出来挨打','2409711401@qq.com','http://www.baidu.com','http://cn/gravatar.com/avatar/dc5e92070d988d0b0bbab9912c1aca9e?s=128&d=identicon&r=PG','讲的太好了，牛逼。',NULL,'0:0:0:0:0:0:0:1','2020-02-28 08:41:02',0),(5,3,'代码，出来挨打',5,'pony','2409711401@qq.com','http://www.tencent.com',NULL,'小样，来我公司上班',NULL,'0:0:0:0:0:0:0:1','2020-03-02 09:48:27',1),(6,0,'',9,'','2409711401@qq.com','http://www.tencent.com','http://cn/gravatar.com/avatar/dc5e92070d988d0b0bbab9912c1aca9e?s=128&d=identicon&r=PG','very well',NULL,'0:0:0:0:0:0:0:1','2020-03-03 05:07:16',1);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link`
--

DROP TABLE IF EXISTS `link`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `link` (
  `link_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `link_url` varchar(255) DEFAULT NULL,
  `link_name` varchar(255) DEFAULT NULL,
  `link_image` varchar(255) DEFAULT NULL,
  `link_description` varchar(255) DEFAULT NULL,
  `link_owner_nickname` varchar(50) DEFAULT NULL,
  `link_owner_contact` varchar(255) DEFAULT NULL,
  `link_create_time` datetime DEFAULT NULL,
  `link_update_time` datetime DEFAULT NULL,
  `link_order` int(2) unsigned DEFAULT '1',
  `link_status` int(1) unsigned DEFAULT '1',
  PRIMARY KEY (`link_id`),
  UNIQUE KEY `link_name` (`link_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link`
--

LOCK TABLES `link` WRITE;
/*!40000 ALTER TABLE `link` DISABLE KEYS */;
INSERT INTO `link` VALUES (2,'http://www.cnblogs.com','博客园',NULL,'博客技术栈',NULL,'18392710807','2020-03-03 02:22:13','2020-03-03 02:22:13',1,1),(3,'http://www.jianshu.com','简书',NULL,'短小精悍的随笔',NULL,'18392710807','2020-03-03 02:23:29','2020-03-03 02:23:29',2,1),(4,'http://www.csdn.net','CSDN',NULL,'技术问题交流',NULL,'18392710807','2020-03-03 02:24:31','2020-03-03 02:24:31',1,1),(5,'http://www.stackoverflow.com','stackoverflow',NULL,'全球技术交流',NULL,'18392710807','2020-03-03 02:26:23','2020-03-03 02:26:23',3,1);
/*!40000 ALTER TABLE `link` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `menu_level` int(11) DEFAULT NULL,
  `menu_icon` varchar(255) DEFAULT NULL,
  `menu_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `menu_name` (`menu_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'留言板','/message',2,'fa fa-comment',1),(2,'LeetCode','https://leetcode.com/problemset/all',2,'ssss',3),(3,'关于本站','/aboutSite',1,'fa fa-info',1),(4,'文章归档','/articleFile',1,'fa-list-alt fa',2),(5,'申请友链','/applyLink',1,'fa fa-link',3);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_id` int(11) DEFAULT NULL,
  `to_id` int(11) DEFAULT NULL,
  `content` text,
  `created_date` datetime DEFAULT NULL,
  `has_read` int(11) DEFAULT NULL,
  `conversation_id` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `conversation_index` (`conversation_id`),
  KEY `created_date` (`created_date`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (45,16,16,'\'welcome\'','2020-06-19 01:41:08',0,'16_16'),(46,3,16,'用户wangli 赞了你的资讯,http://127.0.0.1:8080/news/12','2020-06-19 03:23:09',0,'13_1'),(47,3,16,'用户huangxin 赞了你的资讯,http://127.0.0.1:8080/news/12','2020-06-19 06:15:45',0,'13_19'),(48,3,19,'用户huangxin 赞了你的资讯,http://127.0.0.1:8080/news/12','2020-06-19 06:19:46',0,'13_19');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notice` (
  `notice_id` int(11) NOT NULL AUTO_INCREMENT,
  `notice_title` varchar(255) DEFAULT NULL,
  `notice_content` varchar(1000) DEFAULT NULL,
  `notice_create_time` datetime DEFAULT NULL,
  `notice_update_time` datetime DEFAULT NULL,
  `notice_status` int(1) unsigned DEFAULT '1',
  `notice_order` int(2) DEFAULT NULL,
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'本站下载地址','<p>本站后端基于 Spring+SpringMVC+Mybatis+JSP实现，对于初学SSM的小伙伴可以参考。</p><p><span>下载地址：</span><a target=\"_blank\" href=\"https://github.com/saysky/ForestBlog\">https://github.com/saysky/ForestBlog</a></p><p><br></p>','2017-10-06 23:44:35','2018-11-25 15:02:01',1,1),(3,'终于开始更新了','<p>距离上一次更新有一年了</p><p>最近抽时间更新一波</p><p><br></p><p><b>主要内容</b></p><p>1、去除 Custom 包</p><p>2、规范 MyBatis 语句</p><p>3、规范注释</p><p>4、删除不必要的字段</p><p>5、修改分类和标签关联关系</p><p>6、添加Redis，加快访问速度</p><p><br></p><p>下载地址：<a target=\"_blank\" href=\"https://github.com/saysky/ForestBlog\">https://github.com/saysky/ForestBlog</a></p><p>谢谢大家鼓励，帮忙点 star 和 fork 哦</p><p><br></p><p>更新时间：2018年11月25日</p>','2018-11-25 20:28:33','2018-11-25 20:28:43',1,2),(16,NULL,NULL,NULL,NULL,NULL,NULL),(17,'来和妲己一起做朋友吧','&nbsp;本人，西安科技大学本科生，目前大三，热衷于学习新鲜知识，学习Java知识，也对机器学习和游戏开发有一定的兴趣，如果有志同道合的人，可以联系微信：18392710807.一起努力，共同进步。<img src=\"http://localhost:8888/plugin/layui/images/face/1.gif\" alt=\"[嘻嘻]\">','2020-03-03 05:43:07','2020-03-03 05:44:45',1,1);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `options` (
  `option_id` int(11) NOT NULL AUTO_INCREMENT,
  `option_site_title` varchar(255) DEFAULT NULL,
  `option_site_descrption` varchar(255) DEFAULT NULL,
  `option_meta_descrption` varchar(255) DEFAULT NULL,
  `option_meta_keyword` varchar(255) DEFAULT NULL,
  `option_aboutsite_avatar` varchar(255) DEFAULT NULL,
  `option_aboutsite_title` varchar(255) DEFAULT NULL,
  `option_aboutsite_content` varchar(255) DEFAULT NULL,
  `option_aboutsite_wechat` varchar(255) DEFAULT NULL,
  `option_aboutsite_qq` varchar(255) DEFAULT NULL,
  `option_aboutsite_github` varchar(255) DEFAULT NULL,
  `option_aboutsite_weibo` varchar(255) DEFAULT NULL,
  `option_tongji` varchar(255) DEFAULT NULL,
  `option_status` int(1) DEFAULT '1',
  PRIMARY KEY (`option_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES (1,'腾云博客','The Greatest Scientist!','腾云博客，一个适合初学者做的java博客，立志成为马云和马化腾那样的人。','腾云博客,Java博客，SSM博客','/uploads/2017/10/202003022209.png','博客初心','程序人生，永不止步','/uploads/2017/10/202003022205.jpg','2409711401',NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `page` (
  `page_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `page_key` varchar(50) DEFAULT NULL,
  `page_title` varchar(50) DEFAULT NULL,
  `page_content` mediumtext,
  `page_create_time` datetime DEFAULT NULL,
  `page_update_time` datetime DEFAULT NULL,
  `page_view_count` int(11) unsigned DEFAULT '0',
  `page_comment_count` int(11) unsigned DEFAULT '0',
  `page_status` int(1) unsigned DEFAULT '1',
  PRIMARY KEY (`page_id`),
  UNIQUE KEY `page_key` (`page_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` VALUES (1,'tencent','join tencent','welcome to join&nbsp;Tencent.','2020-02-13 12:59:18','2020-02-13 12:59:18',NULL,NULL,NULL);
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tag` (
  `tag_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(11) DEFAULT NULL,
  `tag_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`tag_id`),
  UNIQUE KEY `tag_name` (`tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (2,'java','welcome to alibaba'),(3,'golang','编程语言'),(4,'数据结构和算法','must'),(5,'数据库','存储数据'),(6,'计算机基础','基础必备');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL DEFAULT '',
  `user_pass` varchar(255) NOT NULL DEFAULT '',
  `user_nickname` varchar(255) NOT NULL DEFAULT 'The Computer Scientist',
  `user_email` varchar(100) DEFAULT '',
  `user_url` varchar(100) DEFAULT '',
  `user_avatar` varchar(100) DEFAULT NULL,
  `user_last_login_ip` varchar(255) DEFAULT NULL,
  `user_register_time` datetime DEFAULT NULL,
  `user_status` int(11) unsigned DEFAULT '1',
  `user_last_login_time` datetime DEFAULT NULL,
  `user_salt` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `user_email` (`user_email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'hbquan','qhb1229','代码，出来打我','2409711401@qq.com','http://www.tencent.com','/uploads/2020\\3/hello.png','0:0:0:0:0:0:0:1','2020-01-04 13:07:26',1,'2020-06-17 10:34:27',NULL),(7,'alibaba','520521','The Greatest Computer Scientist','tencent@qq.com','http://www.alibaba.com','/uploads/2020\\3/QQ图片20190713225200.jpg','0:0:0:0:0:0:0:1','2020-03-08 12:45:17',1,'2020-03-08 12:45:47',NULL),(12,'tencent','123456','The Greatest Computer Scientist','pony@qq.com','http://www.baidu.com','/uploads/2020\\3/QQ图片20200220133146.jpg','0:0:0:0:0:0:0:1','2020-03-08 13:06:38',1,'2020-03-08 13:06:52',NULL),(13,'makemoretime','636af0ae645258ef1f3fece82c5373cb','The Greatest Computer Scientist','hbquan@tencent.com',NULL,NULL,'0:0:0:0:0:0:0:1','2020-06-18 00:58:04',NULL,'2020-06-18 00:58:04','af677'),(15,'yangming','70934ab2cc0f28d363eb96d421881ba6','The Greatest Computer Scientist','yangming@tencent.com','http://www.baidu.com','/uploads/2020\\6/QQ图片20200430220705.gif','0:0:0:0:0:0:0:1','2020-06-18 01:53:47',1,'2020-06-18 01:57:31','28fb5'),(16,'wangli','bf464ba146dee2fb5522946ea176a53b','The Greatest Computer Scientist','wangli@alibaba.com','','/uploads/2020\\6/QQ图片20190713225200.jpg','0:0:0:0:0:0:0:1','2020-06-18 03:22:21',1,'2020-06-19 14:12:31','9fe2c'),(17,'dance','0d506585c1b3170a8ec59223660aab10','The Greatest Computer Scientist','dance@qq.com',NULL,NULL,'0:0:0:0:0:0:0:1','2020-06-18 10:09:15',1,'2020-06-18 10:09:28','39987'),(18,'wangchengwu','906306fb8ff9d1316b065c1405a10ac4','The Greatest Computer Scientist','wang@qq.com',NULL,NULL,'0:0:0:0:0:0:0:1','2020-06-18 12:57:48',1,'2020-06-19 10:40:14','c3118'),(19,'jerry','c0d966cf202300afa29955e6c8426364','The Greatest Computer Scientist','jerry@qq.com',NULL,NULL,'0:0:0:0:0:0:0:1','2020-06-19 03:19:48',1,'2020-06-19 03:19:57','fbd3e'),(20,'huangxin','834e2659562a255da1b2e19b8401fb19','The Greatest Computer Scientist','huangxin@qq.com',NULL,NULL,'0:0:0:0:0:0:0:1','2020-06-19 06:15:28',1,'2020-06-19 06:19:39','d8408');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-19 22:34:13
