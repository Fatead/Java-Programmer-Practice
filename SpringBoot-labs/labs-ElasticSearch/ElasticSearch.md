# Elastic Search

SpringBoot默认支持两种技术和ElasticSearch进行交互:Spring Data ElasticSearch和Jest

## 基本概念
### node 和 cluster
ES本质上是一个分布式数据库，允许多态服务器协同工作，每台服务器可以运行多个ES实例，每个ES实例策划称为一个节点（Node）。一组
节点构成一个集群（cluster）。

### Index
ES会索引所有的字段，经过处理后写入一个反向索引，查找数据的时候，直接查找该索引。所以ES数据管理的顶层单位就叫Index(索引)
它是单个数据库的同义词。

### Document
Index里面的记录成为Decument(文档)，许多条Document构成了一个Index。Documnet使用JSON格式表示，同一个Index里面的Document，不要求有相同的结构，
但是最好保持相同，这样有利于提高搜索效率。

### Type
Document可以分组，比如weather这个Index里面，可以按城市分组（北京上海），也可以按气候分组（晴天雨天），这种分组就叫做type，它是虚拟的逻辑分组，用来过滤Document。

