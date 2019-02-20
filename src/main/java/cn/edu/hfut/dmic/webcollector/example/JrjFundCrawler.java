/*
 * Copyright (C) 2015 hu
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package cn.edu.hfut.dmic.webcollector.example;

import java.util.Iterator;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;



/**
爬取http://fund.jrj.com.cn网站
 * @author huangyan
 */
public class JrjFundCrawler extends BreadthCrawler {

    /*
        该例子利用正则控制爬虫的遍历，
        另一种常用遍历方法可参考DemoTypeCrawler
    */
    
    public JrjFundCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
        
        addSeed("http://fund.jrj.com.cn");
        addRegex("http://fund.jrj.com.cn/.*");
       // addRegex("-.*#.*");
        
        //需要抓取图片时设置为true，并加入图片的正则规则
//        setParseImg(true);
        
        //设置每个线程的抓取间隔（毫秒）
//        setExecuteInterval(1000);
        getConf().setExecuteInterval(1000);
        
        //设置线程数
        setThreads(5);
    }

    /*
        可以往next中添加希望后续爬取的任务，任务可以是URL或者CrawlDatum
        爬虫不会重复爬取任务，从2.20版之后，爬虫根据CrawlDatum的key去重，而不是URL
        因此如果希望重复爬取某个URL，只要将CrawlDatum的key设置为一个历史中不存在的值即可
        例如增量爬取，可以使用 爬取时间+URL作为key。
    
        新版本中，可以直接通过 page.select(css选择器)方法来抽取网页中的信息，等价于
        page.getDoc().select(css选择器)方法，page.getDoc()获取到的是Jsoup中的
        Document对象，细节请参考Jsoup教程
    */
    @Override
    public void visit(Page page, CrawlDatums next) {
        if (page.matchUrl("http://fund.jrj.com.cn/.*")) {
        	Links ls=page.links(true);
            Iterator<String> it=ls.iterator();
            while(it.hasNext()) {
            	String url=it.next();
            	System.out.println(url);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        JrjFundCrawler crawler = new JrjFundCrawler("crawl", true);
        crawler.start(3);
    }

}
