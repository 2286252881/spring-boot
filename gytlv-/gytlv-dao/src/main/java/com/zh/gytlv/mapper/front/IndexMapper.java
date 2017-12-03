package com.zh.gytlv.mapper.front;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.zh.gytlv.entity.Article;
import com.zh.gytlv.entity.ArticleVisitor;

public interface IndexMapper {
	/**
	 * 最新5条博客
	 * 
	 * @return
	 */
	@Select("SELECT a.id,a.articlename,a.articletime,a.articleclick,(SELECT t.articletypename FROM t_articletype t WHERE t.id=a.articletype) articletypename,(SELECT u.nickname FROM t_user u WHERE u.id=a.userid) nickname,a.articlecontent articlecontent FROM t_article a ORDER BY a.articletime DESC LIMIT 5;")
	public List<Article> getNewArticle();

	/**
	 * 博主推荐5条
	 * 
	 * @return
	 */
	@Select("SELECT a.id,a.articlename,a.articleclick FROM t_article a where a.articlesupport=1 ORDER BY a.articletime DESC LIMIT 5;")
	public List<Article> getTopArticle();

	/**
	 * 点击排行 8
	 * 
	 * @return
	 */
	@Select("SELECT a.id,a.articlename,a.articleclick FROM t_article a ORDER BY a.articleclick DESC LIMIT 8;")
	public List<Article> getClickArticle();

	/**
	 * 根据id查询详情
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT a.id,a.articlename,a.articletime,a.articleclick,(SELECT t.articletypename FROM t_articletype t WHERE t.id=a.articletype) articletypename,(SELECT u.nickname FROM t_user u WHERE u.id=a.userid) nickname,a.articlecontent FROM t_article a where id=#{id};")
	public Article getArticle(String id);
	
	
	
	@Select("select * from t_articlevisitor where visitorip=#{visitorip} and visitorarticleid=#{visitorarticleid} and DATEDIFF(visitortime,NOW())=0")
	public List<ArticleVisitor> getVisitors(@Param("visitorip")String visitorIp,@Param("visitorarticleid")String id);

	@Insert("insert into t_articlevisitor values(#{visitor.id},#{visitor.visitorip},#{visitor.visitortime},#{visitor.visitorarticleid})")
	public void insertVisitor(@Param("visitor")ArticleVisitor visitor);
	
	@Insert("UPDATE t_article SET articleclick=articleclick+1 WHERE id=#{id}")
	public void addReadNum(@Param("id")String id);
	
	@Delete("TRUNCATE t_articlevisitor")
	public void truncateVisitor();
	
	
	
	
	
}
