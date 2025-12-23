/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50051
Source Host           : localhost:3306
Source Database       : article_db

Target Server Type    : MYSQL
Target Server Version : 50051
File Encoding         : 65001

Date: 2018-02-05 18:24:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `username` varchar(20) NOT NULL default '',
  `password` varchar(32) default NULL,
  PRIMARY KEY  (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('a', 'a');

-- ----------------------------
-- Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `articleId` int(11) NOT NULL auto_increment COMMENT '文章id',
  `title` varchar(80) NOT NULL COMMENT '文章标题',
  `articleClassObj` int(11) NOT NULL COMMENT '文章类别',
  `articlePhoto` varchar(60) NOT NULL COMMENT '文章主图',
  `content` varchar(5000) NOT NULL COMMENT '文章内容',
  `author` varchar(20) default NULL COMMENT '作者',
  `comeFrom` varchar(50) default NULL COMMENT '来源',
  `addTime` varchar(20) default NULL COMMENT '添加时间',
  PRIMARY KEY  (`articleId`),
  KEY `articleClassObj` (`articleClassObj`),
  CONSTRAINT `t_article_ibfk_1` FOREIGN KEY (`articleClassObj`) REFERENCES `t_articleclass` (`classId`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', '新疆推进丝绸之路经济带国家大数据综合试验区建设', '1', 'upload/f41732fd-a8d4-46f2-ad52-dff894c881cc.jpg', ' &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 记者从自治区经信委获悉，今年我区将推进丝绸之路经济带国家大数据综合试验区建设，推进大数据技术创新、公共安全大数据研究院、大数据双创服务体系等重点工程建设。<br />\r\n<br />\r\n&nbsp;&nbsp;&nbsp; 据悉，为推进丝绸之路经济带国家大数据综合试验区建设，我区将依托乌鲁木齐、克拉玛依云计算产业园区等重点地区，编制《丝绸之路经济带国家大数据综合试验区实施方案》，积极争取丝绸之路经济带国家大数据综合试验区获批。同时，与自治区发改委、网信办、公安厅等部门和乌鲁木齐市、克拉玛依市等地州市建立联合工作机制，推进丝绸之路经济带国家大数据综合试验区建设。<br />\r\n<br />\r\n&nbsp;&nbsp;&nbsp; 目前，中国移动（新疆）数据中心、重要信息系统异地灾难备份中心、中科曙光乌鲁木齐云计算中心、中国电信丝路经济带云基地数据中心陆续建成投入运行。新疆投运的大中型数据中心共有6个，数据中心规模和能力居全国前列。<br />\r\n<br />\r\n&nbsp;&nbsp;&nbsp; 此外，今年我区还将加快推进亚欧高速信息公路基础设施建设。推进中俄跨境光缆的勘测、设计和实施，促进中俄光缆对接；推进中巴跨境光缆的对接和常态化运营；推进中吉、中塔光缆的对接，并完善乌鲁木齐区域性国际通信业务出入口局互联网国际转接点功能。同时，加快推进新一代信息基础设施建设。持续推进完善自治区东向信息网络传输通道，提高支撑云计算大数据应用发展的基础电信通信质量和网络出口带宽。持续推进乌鲁木齐软件园（云计算基地）、克拉玛依云计算产业园区建设，完善配套服务设施和服务机构，提升园区为企业服务能力和水平。\r\n', '--', '华夏经纬网', '2017-11-15 12:13:16');
INSERT INTO `t_article` VALUES ('2', '行走“一带一路”：英国商品，搭上中欧班列直通车', '2', 'upload/9218df1b-5cc3-4608-a829-65dbcb82a9bb.jpg', '<p>首趟伦敦&mdash;义乌中欧班列启程仪式现场。迪拜环球港务集团伦敦口岸供图</p>\r\n<p>列车的轰鸣声、车轮和钢轨碰撞发出的喀嚓声，恰似古丝绸之路的悠悠驼铃穿越千年。忙碌穿梭的中欧货运班列，在广袤的欧亚大陆续写新时代中西方友好交流、互联互通的精彩新篇。</p>\r\n<p>4月27日，首趟由英国驶往中国的中欧货运班列即将结束1.2万多公里的旅程，满载32个货柜的母婴用品、软饮料和维生素产品等，抵达目的地浙江省义乌市。</p>\r\n<p>新时代需要新思维。漫漫黄沙、大漠孤烟的萧索早已被高楼林立、车水马龙的繁华所取代，绵延千载的丝绸之路也被赋予了更为广阔而丰富的内涵&mdash;&mdash;</p>\r\n<p>&ldquo;&lsquo;一带一路&rsquo;是开放的，是穿越非洲、环连亚欧的广阔&lsquo;朋友圈&rsquo;，所有感兴趣的国家都可以添加进入&lsquo;朋友圈&rsquo;。&lsquo;一带一路&rsquo;是多元的，涵盖各个合作领域，合作形式也可以多种多样。&lsquo;一带一路&rsquo;是共赢的，各国共同参与，遵循共商共建共享原则，实现共同发展繁荣。&rdquo;</p>\r\n<p>2015年10月，习近平主席在伦敦金融城举行的中英工商峰会上，以&ldquo;开放&rdquo;&ldquo;多元&rdquo;&ldquo;共赢&rdquo;3个关键词点出了新时期丝路精神的要义。</p>\r\n<p>由英国驶往中国的中欧货运班列，正是新时期丝路精神的生动写照，全速行进，绘就&ldquo;一带一路&rdquo;建设的美好画卷。</p>\r\n<p>风很大、天很冷，但挡不住欢送人群的满腔热情</p>\r\n<p>由英国首都伦敦驱车东行，沿A13号公路进入埃塞克斯郡地界后，再有50多公里，便来到迪拜环球港务集团伦敦口岸。那里便是4月10日，首列伦敦&mdash;义乌中欧班列的发车地。</p>\r\n<p>&ldquo;中欧班列就是从我们这里发车开往中国的，当时的场面热闹极了！&rdquo;回忆起中欧班列从这里出发时的隆重场景，伦敦口岸的工作人员印象格外深刻。</p>\r\n<p>负责安保工作的尼尔森兴奋地向本报记者详细描述了发车当日的情景：&ldquo;为了赶上10点40分准时发车的历史性时刻，人们一大早就从各地赶来。风很大、天气很冷，但这都挡不住欢送人群的满腔热情。人们举着五星红旗，打出各种欢送标语，场面很是感人。&rdquo;</p>\r\n<p>伦敦港口岸物流园区经理奥利弗&middot;特勒内曼自豪地告诉本报记者：&ldquo;首趟伦敦&mdash;义乌班列发车后，不到两个星期时间，就有不少新顾客前来咨询，表达了对这一班列的浓厚兴趣。&rdquo;</p>\r\n<p>特勒内曼说，首趟伦敦&mdash;义乌班列经由该口岸驶出，对他们来说意义非凡。铁路货运为伦敦的制造商和零售商向中国直接运输产品提供了新方式。它比海运快速、比空运便宜，为英国产品的跨国长途运输提供了新途径。</p>\r\n<p>&ldquo;我们相信，这仅仅是个开始，以后将会多起来。英国的产品，可以通过英吉利海峡，进入欧洲大陆，穿越广袤的欧亚大陆，最后到达中国境内。它带去的是各类货物，换回的却是坚挺的硬通货。中国，那是我们在离开欧盟后寄予厚望的国家！&rdquo;伦敦口岸工作人员表示。</p>\r\n', '网络来源', '网络来源', '2017-11-15 12:13:16');
INSERT INTO `t_article` VALUES ('3', '中俄蒙国际飞镖赛开幕 加强草原“丝绸之路”建设', '1', 'upload/72b8b250-50a5-497a-a84b-6f49744c4553.jpg', '<p style=\"text-indent: 2em\">中新网北京12月29日电 记者今日获悉，为了更好实施向北开放战略，促进&ldquo;草原丝绸之路&rdquo;建设，发挥与俄罗斯、蒙古相邻的区位优势，加强三国之间文化经济交流，同时响应国家体育产业发展战略，由内蒙古体育总会主办的中俄蒙国际飞镖邀请赛昨天在内蒙古边陲城市满洲里落幕，这也是该赛事连续第二年在这里举行。</p>\r\n<p style=\"text-indent: 2em\">据悉，在为期两天的赛事里，共吸引了来自俄罗斯、蒙古国以及北京、黑龙江、上海、云南在内的中国11个省、市、自治区的110余名优秀飞镖选手参加比赛。本届赛事共设男子软式、硬式飞镖个人赛和女子软式、硬式飞镖个人赛四个项目。最终经过两天的精彩角逐，来自蒙古的选手包揽男女软式飞镖冠军，而男女硬式飞镖冠军则为中国选手囊括。</p>\r\n<p style=\"text-indent: 2em\"><strong>区位有独特优势</strong></p>\r\n<p style=\"text-indent: 2em\">今年3月28日，国家发改委、外交部、商务部联合发布了《推动共建丝绸之路经济带和21世纪海上丝绸之路的愿景与行动》，提出要&ldquo;发挥内蒙古联通俄蒙的区位优势&rdquo;。而&ldquo;丝绸之路经济带&rdquo;，恰恰同蒙方的草原之路倡议相契合。</p>\r\n<p style=\"text-indent: 2em\">横跨中国东北、华北、西北，毗邻八省区，与俄罗斯、蒙古国交界，边境线长达4200多公里，独特的区位优势，决定了内蒙古在&ldquo;一带一路&rdquo;的中俄蒙经济带中地位举足轻重、不可替代。内蒙古和俄罗斯、蒙古国之间边境经贸往来、地区合作、文化、体育交流十分密切，经济相互依存度及相互关联度较高，建设&ldquo;草原丝绸之路&rdquo;成为内蒙古向北开放的新动力。</p>\r\n', '--', '中国新闻网', '2017-11-15 12:13:16');
INSERT INTO `t_article` VALUES ('4', '【\"一带一路\"上海篇】\"一带一路\"带来的\"新要素禀赋\"', '3', 'upload/b62f15df-86ac-45e4-bc48-d958e8d48f6a.jpg', '<p style=\"border-bottom: medium none; text-align: left; border-left: medium none; padding-bottom: 10px; widows: 1; text-transform: none; text-indent: 0px; margin: 0px; padding-left: 0px; width: auto; padding-right: 0px; font: 14px/26px 宋体; white-space: normal; background: rgb(255,255,255); letter-spacing: normal; color: rgb(51,51,51); border-top: medium none; border-right: medium none; word-spacing: 0px; padding-top: 10px; -webkit-text-stroke-width: 0px\">　　距离&ldquo;一带一路&rdquo;国际合作高峰论坛召开还有半月，&ldquo;一带一路&rdquo;已经成为国际舆论场中的热词。如何理解&ldquo;一带一路&rdquo;？早在博鳌亚洲论坛2015年年会上，中国国家主席习近平就一语道出&ldquo;一带一路&rdquo;倡议的本质：&ldquo;&lsquo;一带一路&rsquo;建设秉持的是共商、共建、共享原则，不是封闭的，而是开放包容的；不是中国一家的独奏，而是参与合作国家的合唱。&rdquo;（2017年5月1日，人民日报海外版）</p>\r\n<p style=\"border-bottom: medium none; text-align: left; border-left: medium none; padding-bottom: 10px; widows: 1; text-transform: none; text-indent: 0px; margin: 0px; padding-left: 0px; width: auto; padding-right: 0px; font: 14px/26px 宋体; white-space: normal; background: rgb(255,255,255); letter-spacing: normal; color: rgb(51,51,51); border-top: medium none; border-right: medium none; word-spacing: 0px; padding-top: 10px; -webkit-text-stroke-width: 0px\">　　&ldquo;一带一路&rdquo;这项开放、包容、互惠的&ldquo;中国倡议&rdquo;，五年来结出了丰硕成果，朋友圈不断扩大，目前已有100多个国家和国际组织参与，40多个国家和国际组织与中国签署了合作协议。俄罗斯媒体称，&ldquo;所有人都想成为中国的朋友&rdquo;。</p>\r\n<p style=\"border-bottom: medium none; text-align: left; border-left: medium none; padding-bottom: 10px; widows: 1; text-transform: none; text-indent: 0px; margin: 0px; padding-left: 0px; width: auto; padding-right: 0px; font: 14px/26px 宋体; white-space: normal; background: rgb(255,255,255); letter-spacing: normal; color: rgb(51,51,51); border-top: medium none; border-right: medium none; word-spacing: 0px; padding-top: 10px; -webkit-text-stroke-width: 0px\">　　&ldquo;无数铃声遥过碛，应驮白练到安西。&rdquo;唐朝诗人张籍的《凉州词》描写了当年丝绸之路上的繁忙景象，当时驼队主要的贸易商品就是&ldquo;丝绸&rdquo;，因而被称作&ldquo;丝绸之路&rdquo;。&ldquo;一带一路&rdquo;是&ldquo;丝绸之路经济带&rdquo;和&ldquo;21世纪海上丝绸之路&rdquo;的简称，旨在借用古代丝绸之路的历史符号，促进当今世界经济发展、共同繁荣。</p>\r\n<p style=\"border-bottom: medium none; text-align: left; border-left: medium none; padding-bottom: 10px; widows: 1; text-transform: none; text-indent: 0px; margin: 0px; padding-left: 0px; width: auto; padding-right: 0px; font: 14px/26px 宋体; white-space: normal; background: rgb(255,255,255); letter-spacing: normal; color: rgb(51,51,51); border-top: medium none; border-right: medium none; word-spacing: 0px; padding-top: 10px; -webkit-text-stroke-width: 0px\">　　笔者认为，&ldquo;一带一路&rdquo;不同于普通的贸易合作，100多个国家和国际组织参与的&ldquo;朋友圈&rdquo;不只是为了买卖&ldquo;丝绸&rdquo;，不仅仅是纯粹物质商品、劳务等贸易，而是立足于经济合作，通过资金、技术、劳动力、信息等生产要素的流动和重新组合，形成&ldquo;新生产要素&rdquo;，共同将沿线国家打造世界经济新的&ldquo;增长极&rdquo;。</p>\r\n<p style=\"border-bottom: medium none; text-align: left; border-left: medium none; padding-bottom: 10px; widows: 1; text-transform: none; text-indent: 0px; margin: 0px; padding-left: 0px; width: auto; padding-right: 0px; font: 14px/26px 宋体; white-space: normal; background: rgb(255,255,255); letter-spacing: normal; color: rgb(51,51,51); border-top: medium none; border-right: medium none; word-spacing: 0px; padding-top: 10px; -webkit-text-stroke-width: 0px\">　　有外媒形容&ldquo;一带一路&rdquo;是&ldquo;最具雄心的中国倡议&rdquo;，&ldquo;一带一路&rdquo;贯穿亚欧非大陆，一头是活跃的东亚经济圈，一头是发达的欧洲经济圈，中间广大腹地国家经济发展潜力巨大。这是一个沿线各国开放合作的宏大经济愿景，孕育了无穷商机和巨大经济增长潜力。习近平主席在纳扎尔巴耶夫大学的演讲中指出：&ldquo;丝绸之路经济带总人口近30亿，市场规模和潜力独一无二。&rdquo;</p>\r\n<p style=\"border-bottom: medium none; text-align: left; border-left: medium none; padding-bottom: 10px; widows: 1; text-transform: none; text-indent: 0px; margin: 0px; padding-left: 0px; width: auto; padding-right: 0px; font: 14px/26px 宋体; white-space: normal; background: rgb(255,255,255); letter-spacing: normal; color: rgb(51,51,51); border-top: medium none; border-right: medium none; word-spacing: 0px; padding-top: 10px; -webkit-text-stroke-width: 0px\">　　人口规模并不只是简单&ldquo;1+1=2&rdquo;，来扩大市场，由此还带来劳动分工和激励创新。正如亚当&middot;斯密19776年写到&ldquo;劳动分工受到市场范围大小的限制&rdquo;，市场越大，从贸易和分工中获得的收益就越多，对创新的激励也就更为有效，因为潜在的收益更大。因而，&ldquo;一带一路&rdquo;赋予了&ldquo;古代丝绸之路&rdquo;更新、更丰富的内涵，不仅仅是买卖&ldquo;丝绸&rdquo;这么简单，更主要是促使生产要素在区域之间流动和重新组合，通过改善沿线国家的交通、通讯等基础设施，从而使得发展中国家获得劳动力智力投资、科技进步与创新、获取信息便利程度等新的要素禀赋。</p>\r\n<p style=\"border-bottom: medium none; text-align: left; border-left: medium none; padding-bottom: 10px; widows: 1; text-transform: none; text-indent: 0px; margin: 0px; padding-left: 0px; width: auto; padding-right: 0px; font: 14px/26px 宋体; white-space: normal; background: rgb(255,255,255); letter-spacing: normal; color: rgb(51,51,51); border-top: medium none; border-right: medium none; word-spacing: 0px; padding-top: 10px; -webkit-text-stroke-width: 0px\">　　优化地区生产要素禀赋所形成的&ldquo;新的要素禀赋&rdquo;，是现代经济发展的重要推动力，能极大提高地区生产力，也提升沿线国家民众的福利。例如，在印尼，由于缺少线下网点，许多人连充手机话费都很困难，由此还在印尼街头滋生了一个独特的职业：代用户充话费的摩托青年。蚂蚁金服到&ldquo;一带一路&rdquo;沿线的发展中国家，通过输出自己的技术和服务，让它们搭上移动互联网时代的&ldquo;中国快车&rdquo;和&ldquo;中国便车&rdquo;，让这里的小微企业和普通人都能享受到方便、平等的金融服务。</p>\r\n', '--', '东方网', '2017-11-15 12:13:16');
INSERT INTO `t_article` VALUES ('5', '境外经贸合作区： “一带一路”双赢平台', '4', 'upload/16ae4cfd-9e73-4c7e-905f-537ed286ea7b.jpg', ' &nbsp; 境外经贸合作区已成为推进&ldquo;一带一路&rdquo;建设和国际产能合作的重要载体，以及中国企业&ldquo;走出去&rdquo;的平台和名片。合作区不仅使我国优势产业在海外形成集聚效应，也降低了中国企业&nbsp;&ldquo;走出去&rdquo;的风险与成本。对于东道国而言，这些合作区吸引了更多的中国企业前来投资建厂，不仅在增加就业、提高税收、扩大出口创汇等方面发挥了重要作用，还有力地推动了其工业化进程并促进了相关产业的升级。由于缺乏可借鉴的国际经验，境外经贸合作区的建设也存在一些瓶颈和不足。</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&ldquo;如果能以这些经贸合作区作为支点，&lsquo;一带一路&rsquo;就能更加落到实处。&rdquo;&nbsp;商务部合作司副司长方蔚在接受媒体采访时曾这样说。他将中国建立的&ldquo;境外经贸合作区&rdquo;称为&ldquo;一带一路&rdquo;建设的&ldquo;承接点&rdquo;。</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;境外经贸合作区已成为推进&ldquo;一带一路&rdquo;建设和国际产能合作的重要载体，以及中国企业&ldquo;走出去&rdquo;的平台和名片。合作区不仅使我国汽车、摩托车、机械、电子、化工、纺织、服装等优势产业在海外形成集聚效应，也降低了中国企业&nbsp;&ldquo;走出去&rdquo;的风险与成本。</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;由于缺乏可借鉴的国际经验，境外经贸合作区的建设也存在一些瓶颈和不足，如园区定位不明确、盈利模式不可持续等。专家提醒，政策风险、区位选择等是中国企业在海外投资建设产业园时应当注意的问题。</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;推进产能合作</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;国际产能合作是推进&ldquo;一带一路&rdquo;建设的优先领域，而境外经贸合作区已成为我国企业在境外开展汽车、摩托车、机械、电子、化工、纺织、服装等优势产业合作的集聚式发展平台。</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;对于东道国而言，这些合作区吸引了更多的中国企业前来投资建厂，不仅在增加就业、提高税收、扩大出口创汇等方面发挥了重要作用，还有力地推动了其工业化进程并促进了相关产业的升级。</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;据商务部数据，截至2016年底，中国企业共在36个国家建成初具规模的合作区77个，累计投资241.9亿美元。其中，56个合作区分布在20个&ldquo;一带一路&rdquo;国家，占在建合作区总数的72.72%，累计投资185.5亿美元，入区企业1082家，总产值506.9亿美元，上缴东道国税费10.7亿美元，为当地创造就业岗位17.7万个。</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&ldquo;境外经贸合作区有效降低了中国企业走出去、东道国引进外资的交易成本。&rdquo;&nbsp;北京大学国家发展研究院教授、北大中国宏观经济研究中心主任卢锋对21世纪经济报道记者说。</p>\r\n<p>&nbsp;&nbsp;&nbsp;&nbsp;卢锋指出，当前吸引中国投资的&ldquo;一带一路&rdquo;沿线国家大多是欠发达国家，制度安排、政策措施和基础设施条件等相对欠缺和落后，而这样的投资环境难以在短时间内改善。合作区则提供一个现实的解决方案，降低制度性交易成本：在园区有限的空间点提供有利条件，实行降低关税、减少官僚主义等新制度，创造新的经济环境。&ldquo;这是借鉴了我们&lsquo;特区&rsquo;的做法&rdquo;。</p>\r\n', '--', '21世纪经济报道', '2017-11-15 12:14:52');
INSERT INTO `t_article` VALUES ('6', '“一带一路”上，央企把钱投向了这些项目', '5', 'upload/fd8976e7-f714-4756-996b-fd5ea004710e.jpg', '<p style=\"font: 16px/2em &quot;Microsoft YaHei&quot;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; margin: 15px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; box-sizing: border-box; widows: 1; -webkit-text-stroke-width: 0px;\">　　<span style=\"color: rgb(0, 0, 0);\">据<span id=\"stock_sh601800\"><font color=\"#113eaa\">中国交建</font></span>董事长刘起涛透露，该集团旗下企业在&ldquo;一带一路&rdquo;沿线已签约及在实施铁路2080公里。其中，即将通车的蒙内铁路全长480公里，该铁路建成以后，东非第一大港蒙巴萨港到首都内罗毕，运物流成本降低40%，运量从原来不足100万吨最终提升到2000万吨，将有效疏解蒙巴萨的物流运输，并给东非这几个国家提供一个便捷的入海通道。</span></p>\r\n<p style=\"font: 16px/2em &quot;Microsoft YaHei&quot;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; margin: 15px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; box-sizing: border-box; widows: 1; -webkit-text-stroke-width: 0px;\">　　<span style=\"color: rgb(0, 0, 0);\"><span id=\"stock_sh601766\"><font color=\"#113eaa\">中国中车</font></span>董事长刘化龙表示，不仅要具备为&ldquo;一带一路&rdquo;建设量身定做各类轨道交通装备的能力，还要不断推出新技术新产品，适应国际联运和互联互通。比如，中国中车正研发以时速400公里可变轨动车组为代表的可变轨机车车辆，方便跨境联运，降低换轨成本。</span></p>\r\n<p style=\"font: 16px/2em &quot;Microsoft YaHei&quot;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; margin: 15px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; box-sizing: border-box; widows: 1; -webkit-text-stroke-width: 0px;\">　　<span style=\"color: rgb(0, 0, 0);\">在公路方面，中巴喀喇昆仑公路等项目给当地交通带来明显改善，盘活了当地旅游资源，培育了零售业、宝石开采等一批新产业，有力促进了当地居民生产生活条件的改善。</span></p>\r\n<p style=\"font: 16px/2em &quot;Microsoft YaHei&quot;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; margin: 15px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; box-sizing: border-box; widows: 1; -webkit-text-stroke-width: 0px;\">　　<span style=\"color: rgb(0, 0, 0);\">刘起涛表示，中国交建及旗下公司已在&ldquo;一带一路&rdquo;沿线国家修建了10320公里的公路，此外还有95个深水码头，10座机场，152座大桥等。</span></p>\r\n<p style=\"font: 16px/2em &quot;Microsoft YaHei&quot;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; margin: 15px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; box-sizing: border-box; widows: 1; -webkit-text-stroke-width: 0px;\">　　<span style=\"color: rgb(0, 0, 0);\">在通讯网络方面，在东北亚、中亚、南亚、东南亚等周边区域建设跨境海底光缆、陆地光缆等大容量高速率通信设施，打造全方位立体结合的通信网络联通体系。</span></p>\r\n<p style=\"font: 16px/2em &quot;Microsoft YaHei&quot;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; margin: 15px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; box-sizing: border-box; widows: 1; -webkit-text-stroke-width: 0px;\">　　<span style=\"color: rgb(0, 0, 0);\">中国移动董事长尚冰表示，为加强巴基斯坦基础网络建设，率先开通4G无线宽带服务，有力促进了当地信息产业快速发展。</span></p>\r\n<p style=\"font: 16px/2em &quot;Microsoft YaHei&quot;, u5FAEu8F6Fu96C5u9ED1, Arial, SimSun, u5B8Bu4F53; margin: 15px 0px; padding: 0px; color: rgb(0, 0, 0); text-transform: none; text-indent: 0px; letter-spacing: normal; word-spacing: 0px; white-space: normal; box-sizing: border-box; widows: 1; -webkit-text-stroke-width: 0px;\">　　<span style=\"color: rgb(0, 0, 0);\">未来三年，中国移动还将在&ldquo;一带一路&rdquo;沿线的POP点将增加到61个，形成贯穿&ldquo;一带一路&rdquo;的带状&ldquo;信息驿站&rdquo;，同时还计划部署8个实体&ldquo;信息集散岛&rdquo;（数据中心）。</span></p>\r\n', '--', '中国新闻网', '2017-11-15 13:25:52');
INSERT INTO `t_article` VALUES ('7', '中国广邀世界领袖齐聚北京 聚焦“一带一路”合作', '4', 'upload/2a578d4f-28de-40f5-b816-aede1f06e954.jpg', '<p>　　据美国之音电台网站2月14日报道，中方明确表示，5月论坛的目的不仅仅是聚焦&ldquo;一带一路&rdquo;，他们还想讨论包括美国在内的一些国家日益增长的保护主义倾向。</p>\r\n<p>　　中国外交部长王毅表示，这将是中国主场外交的重头戏，中国将推动更多合作项目落地，为打造开放型经济格局、提振世界经济注入强劲动力。</p>\r\n<p>　　分析人士表示，特朗普的言行导致欧洲市场的不稳定，而在他与澳大利亚等国领导人通话之后，这些国家出现对其不满的声音，中国察觉到这是获得领导地位的契机。</p>\r\n<p>　　他们还表示，该论坛也是中国期望加强国际商贸和政治关系的一种努力。中国担心，如果特朗普总统遵守他的竞选承诺，对中国商品征收高关税并限制来自中国的投资，两国恐将上演贸易战。</p>\r\n<p>　　报道称，俄罗斯总统普京是首批接受论坛邀请的领导人之一。英国首相特雷莎&middot;梅的办公室也表示她将很快访华，而这被解读为她将出席此次论坛。中国的新朋友、2017年东盟轮值主席国菲律宾的总统杜特尔特也承诺将参加此次论坛。</p>\r\n<p>　　国际关系学院副教授储殷表示：&ldquo;全球化和世界经济的未来极其不确定，5月的论坛将重新恢复人们对世界经济的信心。&rdquo;</p>\r\n<p>　　杜克大学社会学教授高柏对此持不同看法。他说：&ldquo;我不认为这次论坛的目的是寻找反对特朗普的声音，我认为论坛是要对自由贸易达成共识，并加强对保护主义的反对声音。&rdquo;</p>\r\n<p>　　分析人士说，对外投资是中国的一个外交工具。预计在论坛上还会宣布更多项目。</p>\r\n<p>　　华盛顿智库战略与国际问题研究中心中国商业与政治经济项目主管斯科特&middot;肯尼迪说：&ldquo;让这次会议真正有意义的唯一途径是中国承诺让其经济更加自由化，而不是一贯的中国拿出更多钱，最后这些钱都进了国企的口袋。&rdquo;</p>\r\n', '--', '参考消息网', '2017-02-17');

-- ----------------------------
-- Table structure for `t_articleclass`
-- ----------------------------
DROP TABLE IF EXISTS `t_articleclass`;
CREATE TABLE `t_articleclass` (
  `classId` int(11) NOT NULL auto_increment COMMENT '类别id',
  `className` varchar(50) NOT NULL COMMENT '类别名称',
  `classDesc` varchar(500) default NULL COMMENT '类别描述',
  PRIMARY KEY  (`classId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_articleclass
-- ----------------------------
INSERT INTO `t_articleclass` VALUES ('1', '广西经济', '北方草原丝路 | 古代海上丝路 | 南方茶马古道丝路 | 西安西亚西欧丝路 | 当代海陆空丝路 |');
INSERT INTO `t_articleclass` VALUES ('2', '广西文化', '美食文化 | 历史文化 | 丝路艺术 | 文化交流 |');
INSERT INTO `t_articleclass` VALUES ('3', '丝路城市', '沿路城市 | 城市经济 | 城市发展 | 丝路驿站 ');
INSERT INTO `t_articleclass` VALUES ('4', '国际合作', '国际合作国际合作国际合作');
INSERT INTO `t_articleclass` VALUES ('5', '资源开发', '最新动态 | 优质项目 | 旅游开发 | 投资机构 |');

-- ----------------------------
-- Table structure for `t_leaveword`
-- ----------------------------
DROP TABLE IF EXISTS `t_leaveword`;
CREATE TABLE `t_leaveword` (
  `leaveId` int(11) NOT NULL auto_increment COMMENT '留言id',
  `title` varchar(80) NOT NULL COMMENT '留言标题',
  `leaveContent` varchar(2000) NOT NULL COMMENT '留言内容',
  `leaveTime` varchar(20) default NULL COMMENT '留言时间',
  `userObj` varchar(20) NOT NULL COMMENT '留言人',
  `replyContent` varchar(2000) default NULL COMMENT '回复内容',
  `replyTime` varchar(20) default NULL COMMENT '回复时间',
  PRIMARY KEY  (`leaveId`),
  KEY `userObj` (`userObj`),
  CONSTRAINT `t_leaveword_ibfk_1` FOREIGN KEY (`userObj`) REFERENCES `t_userinfo` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_leaveword
-- ----------------------------
INSERT INTO `t_leaveword` VALUES ('1', '我可以了解丝路文化了', '有了这个网站，我可以查询到各种有关丝路文化的知识', '2017-11-14 17:32:19', 'user1', '很不错的呢！', '2017-11-15 19:48:51');
INSERT INTO `t_leaveword` VALUES ('3', '大家好，初来咋到', '希望大家一起交流', '2017-11-15 19:56:00', 'user2', '--', '--');
INSERT INTO `t_leaveword` VALUES ('4', '大家好啊', '我是刚来的@！', '2017-11-20 19:16:57', 'user3', '很好', '2017-11-20 19:19:21');

-- ----------------------------
-- Table structure for `t_userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `t_userinfo`;
CREATE TABLE `t_userinfo` (
  `user_name` varchar(20) NOT NULL COMMENT 'user_name',
  `password` varchar(20) NOT NULL COMMENT '登录密码',
  `name` varchar(20) NOT NULL COMMENT '姓名',
  `sex` varchar(4) NOT NULL COMMENT '性别',
  `birthDate` varchar(20) default NULL COMMENT '生日',
  `userImage` varchar(60) NOT NULL COMMENT '用户照片',
  `qq` varchar(20) default NULL COMMENT '用户qq',
  `email` varchar(50) default NULL COMMENT '邮箱',
  `regTime` varchar(20) default NULL COMMENT '注册时间',
  PRIMARY KEY  (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_userinfo
-- ----------------------------
INSERT INTO `t_userinfo` VALUES ('user1', '123', '双鱼林', '男', '2017-11-01', 'upload/0faeaf5f-54a9-436e-93b4-9217262d97c7.jpg', '71516145151', 'dashen@163.com', '2017-11-15 17:27:53');
INSERT INTO `t_userinfo` VALUES ('user2', '123', '张小倩', '女', '2017-11-01', 'upload/NoImage.jpg', '15154141', '15154141@qq.com', '2017-11-15 19:53:34');
INSERT INTO `t_userinfo` VALUES ('user3', '123', '毛光明', '男', '2017-11-02', 'upload/8c75d331-e983-477a-9b33-cca7e432e046.jpg', '515156144', '515156144@qq.com', '2017-11-20 19:16:13');
