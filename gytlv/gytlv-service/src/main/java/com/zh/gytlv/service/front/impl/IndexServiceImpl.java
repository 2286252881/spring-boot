package com.zh.gytlv.service.front.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zh.gytlv.entity.Article;
import com.zh.gytlv.entity.ArticleVisitor;
import com.zh.gytlv.mapper.front.IndexMapper;
import com.zh.gytlv.service.front.IndexService;

@Service("indexService")
public class IndexServiceImpl implements IndexService {

	@Autowired
	private IndexMapper indexMapper;

	@Override
	public List<Article> getNewArticle() {
		List<Article> articles = indexMapper.getNewArticle();
		for (Article article : articles) {
			article.setArticlecontent(article.getArticlecontent().replaceAll("<img[^>]*>", ""));
			if (article.getArticlecontent().length() > 500) {
				article.setArticlecontent(article.getArticlecontent().substring(0, article.getArticlecontent().indexOf('。') + 1));
			}
		}
		return articles;
	}

	@Override
	public List<Article> getTopArticle() {
		return indexMapper.getTopArticle();
	}

	@Override
	public List<Article> getClickArticle() {
		return indexMapper.getClickArticle();
	}

	@Transactional
	@Override
	public Article getArticleDetail(String id, String visitorIp) {
		// 根据ip查询该文章当天是否访问过,没有则增加阅读量。
		List<ArticleVisitor> visitors = indexMapper.getVisitors(visitorIp, id);
		if (visitors.isEmpty()) {
			ArticleVisitor visitor = new ArticleVisitor(UUID.randomUUID().toString(), visitorIp, new Date(), id);
			indexMapper.insertVisitor(visitor);
			indexMapper.addReadNum(id);
		}
		return indexMapper.getArticle(id);
	}

	@Override
	public void truncateVisitor() {
		indexMapper.truncateVisitor();
	}

}
