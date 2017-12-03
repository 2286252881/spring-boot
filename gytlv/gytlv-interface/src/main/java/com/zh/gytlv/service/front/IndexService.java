package com.zh.gytlv.service.front;

import java.util.List;

import com.zh.gytlv.entity.Article;

public interface IndexService {
	public List<Article> getNewArticle();

	public List<Article> getTopArticle();

	public List<Article> getClickArticle();

	public Article getArticleDetail(String id,String visitorIp);
	
	public void truncateVisitor();
}
